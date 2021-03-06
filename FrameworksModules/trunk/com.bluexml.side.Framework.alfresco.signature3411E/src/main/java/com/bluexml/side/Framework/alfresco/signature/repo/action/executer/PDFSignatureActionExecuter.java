/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.bluexml.side.Framework.alfresco.signature.repo.action.executer;

import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.List;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.action.ParameterDefinitionImpl;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ParameterDefinition;
import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.util.TempFileProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;

public class PDFSignatureActionExecuter extends BasePDFStampActionExecuter

{

    /**
     * The logger
     */
    private static Log logger = LogFactory
            .getLog(PDFSignatureActionExecuter.class);

    /**
     * Action constants
     */
    public static final String NAME = "pdf-signature";
    public static final String PARAM_PRIVATE_KEY="private-key";
    public static final String PARAM_DESTINATION_FOLDER = "destination-folder";
    public static final String PARAM_VISIBILITY = "visibility";
    public static final String PARAM_LOCATION = "location";
    public static final String PARAM_REASON = "reason";
    public static final String PARAM_KEY_PASSWORD = "key-password";
    public static final String PARAM_WIDTH = "width";
    public static final String PARAM_HEIGHT = "height";
    public static final String PARAM_KEY_TYPE = "key-type";
    public static final String PARAM_EXCEPTION = "exception";
    public static final String PARAM_SIGNED_NAME = "signed-name";
    
    //Aditional parameters to accessing the keystore file 
    public static final String PARAM_ALIAS = "alias";
    public static final String PARAM_STORE_PASSWORD = "store-password";
    
    public static final String VISIBILITY_HIDDEN = "hidden";
    public static final String VISIBILITY_VISIBLE = "visible";

    public static final String KEY_TYPE_PKCS12 = "pkcs12";
    public static final String KEY_TYPE_DEFAULT = "default";
    
    /**
     * Add parameter definitions
     */
    @Override
    protected void addParameterDefinitions(List<ParameterDefinition> paramList)
    {
    	super.addParameterDefinitions(paramList);
    	
        paramList.add(new ParameterDefinitionImpl(PARAM_DESTINATION_FOLDER,
                DataTypeDefinition.NODE_REF, true,
                getParamDisplayLabel(PARAM_DESTINATION_FOLDER)));
        paramList.add(new ParameterDefinitionImpl(PARAM_PRIVATE_KEY,
                DataTypeDefinition.NODE_REF, false,
                getParamDisplayLabel(PARAM_PRIVATE_KEY)));
        
    }

    /**
     * @see org.alfresco.repo.action.executer.ActionExecuterAbstractBase#executeImpl(org.alfresco.service.cmr.repository.NodeRef,
     *      org.alfresco.service.cmr.repository.NodeRef)
     */
    
    protected void executeImpl(Action ruleAction, NodeRef actionedUponNodeRef)
    { 
    	@SuppressWarnings("unchecked")
		ArrayList<String> myException = (ArrayList<String>) ruleAction.getParameterValue(PARAM_EXCEPTION);
        
        ContentReader actionedUponContentReader = getReader(actionedUponNodeRef);
        
        if (actionedUponContentReader != null)
        {
            // Add the signature to the PDF
            try {
				doSignature(ruleAction, actionedUponNodeRef, actionedUponContentReader);
			} catch (Exception e) {
				myException.add("Error on document " + actionedUponNodeRef.getStoreRef() + "/" + actionedUponNodeRef.getId());
			}
            
            {
                if (logger.isDebugEnabled())
                {
                    logger.debug("Can't execute rule: \n" + "   node: "
                            + actionedUponNodeRef + "\n" + "   reader: "
                            + actionedUponContentReader + "\n" + "   action: " + this);
                }
            }
        }
    }

    /**
     * 
     * @param ruleAction
     * @param actionedUponNodeRef
     * @param actionedUponContentReader
     * @throws Exception 
     */
    protected void doSignature(Action ruleAction, NodeRef actionedUponNodeRef,
            ContentReader actionedUponContentReader) throws Exception
    {
    	
        NodeRef privateKey = (NodeRef)ruleAction.getParameterValue(PARAM_PRIVATE_KEY);
        String location = (String)ruleAction.getParameterValue(PARAM_LOCATION);
        String reason = (String)ruleAction.getParameterValue(PARAM_REASON);
        String visibility = (String)ruleAction.getParameterValue(PARAM_VISIBILITY);
        String keyPassword = (String)ruleAction.getParameterValue(PARAM_KEY_PASSWORD);
        String keyType = (String)ruleAction.getParameterValue(PARAM_KEY_TYPE);
        String signedName = (String)ruleAction.getParameterValue(PARAM_SIGNED_NAME);
        int height = Integer.parseInt((String)ruleAction.getParameterValue(PARAM_HEIGHT));
        int width = Integer.parseInt((String)ruleAction.getParameterValue(PARAM_WIDTH));
        
        // New keystore parameters
        String alias = (String) ruleAction.getParameterValue(PARAM_ALIAS);
        String storePassword = (String) ruleAction.getParameterValue(PARAM_STORE_PASSWORD);
        
        // Ugly and verbose, but fault-tolerant
        String locationXStr = (String)ruleAction.getParameterValue(PARAM_LOCATION_X);
        String locationYStr = (String)ruleAction.getParameterValue(PARAM_LOCATION_Y);
        int locationX = 0;
        int locationY = 0;
        try 
        {
        	locationX = locationXStr != null ? 
        			Integer.parseInt(locationXStr) : 0;
        }
        catch (NumberFormatException e)
        {
        	locationX = 0;
        }
        try 
        {
        	locationY = locationXStr != null ? 
        			Integer.parseInt(locationYStr) : 0;
        }
        catch (NumberFormatException e)
        {
        	locationY = 0;
        }
        
        File tempDir = null;
        ContentWriter writer = null;
        KeyStore ks = null;
        
		try {
			// get a keystore instance by
			if(keyType == null || keyType.equalsIgnoreCase(KEY_TYPE_DEFAULT)) {
				ks = KeyStore.getInstance(KeyStore.getDefaultType());
			}else if (keyType.equalsIgnoreCase(KEY_TYPE_PKCS12)) {
				ks = KeyStore.getInstance("pkcs12");
			} else {
				throw new Exception("Unknown key type " + keyType + " specified");
			}

			// open the reader to the key and load it
			ContentReader keyReader = serviceRegistry.getContentService().getReader(privateKey, ContentModel.PROP_CONTENT);
			ks.load(keyReader.getContentInputStream(), storePassword.toCharArray());

			// set alias
//			String alias = (String) ks.aliases().nextElement();
			
			PrivateKey key = (PrivateKey) ks.getKey(alias, keyPassword.toCharArray());
			Certificate[] chain = ks.getCertificateChain(alias);
			
			//open original pdf
			ContentReader pdfReader = getReader(actionedUponNodeRef);
			PdfReader reader = new PdfReader(pdfReader.getContentInputStream());
			
			// create temp dir to store file
			File alfTempDir = TempFileProvider.getTempDir();
        	tempDir = new File(alfTempDir.getPath() + File.separatorChar + actionedUponNodeRef.getId());
            tempDir.mkdir();
            File file = new File(tempDir, serviceRegistry.getFileFolderService().getFileInfo(actionedUponNodeRef).getName());
            
			FileOutputStream fout = new FileOutputStream(file);
			PdfStamper stamp = PdfStamper.createSignature(reader, fout, '\0');
			PdfSignatureAppearance sap = stamp.getSignatureAppearance();
			sap.setCrypto(key, chain, null, PdfSignatureAppearance.WINCER_SIGNED);
		
			// set reason for signature and location of signer
			sap.setReason(reason);
			sap.setLocation(location);

			if (visibility.equalsIgnoreCase(PDFSignatureActionExecuter.VISIBILITY_VISIBLE)) {
				sap.setVisibleSignature(new Rectangle(locationX + width, locationY - height, locationX, locationY), 1, null);
			}

			stamp.close();
			

	    	String[] splitedFilename = file.getName().split("\\.");
	    	String name = "-" + signedName + "." + splitedFilename[splitedFilename.length -1];
	    	for(int i = splitedFilename.length - 2; i >= 0; i--) {
	    		if (name.equals("-" + signedName + "." + splitedFilename[splitedFilename.length -1])) {
	    			name =  splitedFilename[i] +  name;
	    		} else {
	    			name =  splitedFilename[i] + "." + name;
	    		}
	    	}
	    	
            writer = getWriter(name, (NodeRef) ruleAction.getParameterValue(PARAM_DESTINATION_FOLDER));
            writer.setEncoding(actionedUponContentReader.getEncoding());
            writer.setMimetype(FILE_MIMETYPE);
            writer.putContent(file);

            file.delete();
		}
		catch (Exception e)
        {
            throw e;
        }
        finally 
        {
        	if(tempDir != null) {try {tempDir.delete();} catch(Exception ex){}}
        }
    }
}
