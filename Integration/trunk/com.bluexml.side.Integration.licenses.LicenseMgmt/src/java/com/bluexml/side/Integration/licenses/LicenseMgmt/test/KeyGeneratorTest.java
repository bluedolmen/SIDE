package com.bluexml.side.Integration.licenses.LicenseMgmt.test;

import java.io.InputStream;
import java.util.ArrayList;
import org.xml.sax.InputSource;
import org.sidelabs.is.common.StringXPath;
import com.bluexml.side.util.security.license.KeyGenerator;
import junit.framework.TestCase;

public class KeyGeneratorTest extends TestCase {

	/*
	 * 
	 * private parameters for xml test file access
	 * 
	 */
	private String XML_FILE_NAME = "test02.xml";
	
	/*
	 * 
	 * private parameters for key generation
	 */
	private String codesComp;
	private Integer delay;
	private String idCompte;
	private String iDMach;
	
	/*
	 * 
	 * setParameters()
	 * 1- read values from xml file
	 * 2- set private parameters with retrieved values
	 * 
	 */
	private int setParameters() {

		int result1 = setCodesComp();
		int result2 = setDelay();
		int result3 = setIdCompte();
		int result4 = setIdMach();
		
		if (result1==0||result2==0||result3==0||result4==0) {
			return 0;
		} else {
			return 1;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private int setCodesComp() {
		
		try {
			InputStream inputStream = GetResourceAsStream(XML_FILE_NAME);
			InputSource inputSource = new InputSource(inputStream);
			
			String expressionCodesComp = "/root/key_gen/codes_comp/code_comp";
			String temp_codesComp = "";			
			StringXPath stringXPath = new StringXPath();
			ArrayList resultCodesComp = stringXPath.recuperationValAttribut(inputSource, expressionCodesComp);
			
			if (resultCodesComp.size()==0) {
				return 0;
			} else {
				//codesComp
				for (int i=0; i<resultCodesComp.size(); i++) {
					if (i==0) {
						temp_codesComp = (String) resultCodesComp.get(i);
					} else {
						temp_codesComp = temp_codesComp.concat(","+(String) resultCodesComp.get(i));
					}
				}
				codesComp = temp_codesComp;
				System.out.println("CodesComp: "+codesComp);
			}
			if (codesComp.length()==0) {
				return 0;
			} else {
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	private int setDelay() {
		
		try {
			InputStream inputStream = GetResourceAsStream(XML_FILE_NAME);
			InputSource inputSource = new InputSource(inputStream);
			String expressionDelay = "/root/key_gen/delay";
			String temp_delay = "";
			StringXPath stringXPath = new StringXPath();
			ArrayList resultDelay = stringXPath.recuperationValAttribut(inputSource, expressionDelay);
		
			if (resultDelay.size()==0) {
				return 0;
			} else {
				for (int i=0; i<resultDelay.size(); i++) {
					temp_delay = (String) resultDelay.get(i);
				}
				delay = Integer.parseInt(temp_delay);
				System.out.println("Delay: "+delay);
			}
			if (delay==0) {
				return 0;
			} else {
				return 1;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	private int setIdCompte() {
		
		try {
			InputStream inputStream = GetResourceAsStream(XML_FILE_NAME);
			InputSource inputSource = new InputSource(inputStream);
			String expressionIdCompte = "/root/key_gen/id_compte";
			String temp_idCompte = "";
			StringXPath stringXPath = new StringXPath();
			ArrayList resultIdCompte = stringXPath.recuperationValAttribut(inputSource, expressionIdCompte);
		
			if (resultIdCompte.size()==0) {
				return 0;
			} else {
				for (int i=0; i<resultIdCompte.size(); i++) {
					temp_idCompte = (String) resultIdCompte.get(i);
				}
				idCompte = temp_idCompte;
				System.out.println("IDCompte: "+idCompte);
			}
			if (idCompte.length()==0) {
				return 0;
			} else {
				return 1;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	private int setIdMach() {
		
		try {
			InputStream inputStream = GetResourceAsStream(XML_FILE_NAME);
			InputSource inputSource = new InputSource(inputStream);
			String expressionIdMach = "/root/key_gen/id_mach";
			String temp_idMach = "";
			StringXPath stringXPath = new StringXPath();
			ArrayList resultIdMach = stringXPath.recuperationValAttribut(inputSource, expressionIdMach);
		
			if (resultIdMach.size()==0) {
				return 0;
			} else {
				for (int i=0; i<resultIdMach.size(); i++) {
					temp_idMach = (String) resultIdMach.get(i);
				}
				iDMach = temp_idMach;
				System.out.println("IDMach: "+iDMach);
			}
			if (iDMach.length()==0) {
				return 0;
			} else {
				return 1;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/*
	 * 
	 * testGenerateKey()
	 * 0- set parameters
	 * 1- generate key
	 * 
	 */
	public final void testGenerateKey() throws Exception {
		
		/*
		 * 0- set parameters
		 */
		int result0 = this.setParameters();
		assertEquals(result0,1);
		System.out.println("Parameters set.");
		
		/*
		 * 1- generate key
		 */
		String result1 = KeyGenerator.generateKey(
				this.codesComp,
				this.delay,
				this.idCompte,
				this.iDMach);
		assertEquals(result1.length(),62);
		System.out.println("Key generated: "+result1);
		
		/*
		 * finished
		 */
		System.out.println("Test done.");
	}
	
	public static InputStream GetResourceAsStream(String file) throws Exception {
    	InputStream myurl = KeyGeneratorTest.class.getClassLoader().getResourceAsStream("com/bluexml/side/Integration/licenses/LicenseMgmt/test/xml/"+file);
		return myurl;
	}

}
