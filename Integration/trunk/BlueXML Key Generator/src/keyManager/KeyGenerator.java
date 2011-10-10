/*******************************************************************************
 * 	Copyright (C) BlueXML 2005-2009
 *
 * This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 ******************************************************************************/

package keyManager;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Keygenerator based on the blowfish algorythm with a public key stocked in the
 * GeneratorConstants interface.
 * 
 * @author <a href="mailto:pbertrand@bluexml.com">Pierre BERTRAND</a>
 */
public class KeyGenerator implements GeneratorConstants {

	/**
	 * Utility classes don't need to (and shouldn't) be instantiated.
	 */
	private KeyGenerator() {
		// prevents instantiation
	}

	/**
	 * Create the string to encrypt <components codes ( base 36)>|<Date ddMMyyyy
	 * (base 36)>|<ID Account (base 36)>
	 * 
	 * @param codes
	 *            Codes in binary of the components who are activated
	 * @param date
	 *            The actual date of the key which will be incremented with the
	 *            delay
	 * @param delay
	 *            The period of validity of the key in days
	 * @param idCompte
	 *            The id of the asker account in Silverstripe
	 * @param iDMach
	 *            The id of the asker's machine
	 * @return the String ready to be encrypted
	 */
	private static String generateStringToCrypt(Integer codes, Integer delay, String idCompte, String iDMach) {
		// Creation of the codes
		String strCodes = Encoder.encode(codes.toString());
		// Creation of the end date
		Date date = new Date();
		date.setTime(date.getTime() + (delay.longValue() * 86400000));
		DateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
		// Encoding elements of the key
		String dateFin = Encoder.encode(dateFormat.format(date));
		idCompte = Encoder.encode(idCompte);
		// String creation
		String strToScript = strCodes + TEXT_SEPARATOR + dateFin + TEXT_SEPARATOR + idCompte + TEXT_SEPARATOR + iDMach;
		return strToScript;
	}

	/**
	 * @param codes
	 *            Codes in binary of the components who are activated
	 * @param date
	 *            The actual date of the key which will be incremented with the
	 *            delay
	 * @param delay
	 *            The period of validity of the key in days
	 * @param idCompte
	 *            The id of the asker account in Silverstripe
	 * @param iDMach
	 *            The id of the asker's machine
	 * @return The key encrypted with the public key in the GeneratorConstants
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 *             Exceptions made by the initialisation and the cryptation of
	 *             the cypher used to generate the key
	 */
	private static String generateKey(Integer codes, Integer delay, String idCompte, String iDMach) throws UnsupportedEncodingException {
		// Generation of the string to crypt
		String strKey = generateStringToCrypt(codes, delay, idCompte, iDMach);
		// Initialisation of the Cipher
		IvParameterSpec salt = new IvParameterSpec(iv);
		byte[] raw = SECRET_KEY.getBytes("UTF-8");
		SecretKey skeySpec = new SecretKeySpec(raw, "Blowfish");
		Cipher c;
		byte[] buf_crypt = null;
		String result = new String();
		// Cryptation
		try {
			c = Cipher.getInstance(ALGORYTHM);
			c.init(Cipher.ENCRYPT_MODE, skeySpec, salt);
			buf_crypt = c.doFinal(strKey.getBytes("UTF-8"));

			// Exceptions due to the cipher
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}

		// we add +255 to avoid negative values
		for (int i = 0; i < buf_crypt.length; i++) {
			result += Integer.toString(buf_crypt[i] + 255);
		}

		return Encoder.encode(result);
	}

	/**
	 * @param codes
	 *            Codes in String separated with comas
	 * @param delay
	 *            The period of validity of the key in days
	 * @param idCompte
	 *            The id of the asker account in Silverstripe
	 * @param iDMach
	 *            The id of the asker's machine
	 * @return
	 */
	public static String generateKey(String codesComp, Integer delay, String idCompte, String iDMach) {
		if (codesComp.equals(GeneratorConstants.CODE_ALL)) {
			// need to get all codes			
			Collection<String> codeKeys = CodeReader.getCodeKeys();			
			codesComp = codeKeys.toString().replaceAll("\\[([^\\]]*)\\]", "$1");
		}
		Integer codeComposants = CodeReader.getCodes(codesComp);
		try {
			return generateKey(codeComposants, delay, idCompte, iDMach);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Main function that generate the key with the parameters
	 * <codesComposants(code1,code2,code3)> <delai> <ID Compte>"
	 */
	public static void main(String[] args) {
		// example : java jar Keygeneration.jar
		// CODE_GED_M_WORKFLOW,CODE_GED_G_C_ALFRESCO_31E,CODE_GED_G_W_ALFRESCO_31E
		// 31 3
		if (args.length != NB_CHAMPS_CLE)
			System.out.println("Usage : <codesComposants(code1,code2,code3)> <delai> <ID Compte> <IDMach>");
		else {
			String codedText = "";
			try {
				codedText = KeyGenerator.generateKey(args[0],// CodesComposants
						Integer.parseInt(args[1]),// delai
						args[2],// ID compte
						args[3]); // Nom Mach
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			// return the key
			System.out.println(codedText);
		}

	}
}
