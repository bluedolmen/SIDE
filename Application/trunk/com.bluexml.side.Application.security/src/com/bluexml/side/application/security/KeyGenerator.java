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

package com.bluexml.side.application.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

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
	 * Create the string to encrypt <components codes ( base 36)>|<Date
	 * ddMMyyyy (base 36)>|<ID Account (base 36)>
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
	 * @return the String ready to be encrypted
	 */
	private static String generateStringToCrypt(Integer codes, Date date,
			Integer delay, String idCompte) {
		// Creation of the codes
		String strCodes = Encoder.encode(codes.toString());
		// Creation of the end date
		DateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setGregorianChange(new Date());
		gregorianCalendar.add(GregorianCalendar.DAY_OF_YEAR, delay);
		// Encoding elements of the key
		String dateFin = Encoder.encode(dateFormat.format(gregorianCalendar
				.getTime()));
		idCompte = Encoder.encode(idCompte);
		// String creation
		String strToScript = strCodes + TEXT_SEPARATOR + dateFin + TEXT_SEPARATOR
				+ idCompte;
		return strToScript;
	}

	/**
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
	 * @return The key encrypted with the public key in the GeneratorConstants
	 * @throws UnsupportedEncodingException 
	 * @throws Exception
	 *             Exceptions made by the initialisation and the cryptation of
	 *             the cypher used to generate the key
	 */
	private static String generateKey(Integer codes, Integer delay,
			String idCompte) throws UnsupportedEncodingException {
		// Generation of the string to crypt
		String strKey = generateStringToCrypt(codes, new Date(), delay, idCompte);
		// Initialisation of the Cipher
		IvParameterSpec salt = new IvParameterSpec(iv);
		byte[] raw = SECRET_KEY.getBytes("ISO8859_1");
		SecretKey skeySpec = new SecretKeySpec(raw, "Blowfish");
		Cipher c;
		byte[] buf_crypt = null;
		String result = new String();
		// Cryptation
		try {
			c = Cipher.getInstance(ALGORYTHM);
			c.init(Cipher.ENCRYPT_MODE, skeySpec, salt);
			buf_crypt = c.doFinal(strKey.getBytes("ISO8859_1"));
			
		//Exceptions due to the cipher
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
	 * 
	 * @param codes
	 *            Codes in String separated with comas
	 * @param delay
	 *            The period of validity of the key in days
	 * @param idCompte
	 *            The id of the asker account in Silverstripe
	 * @return
	 */
	public static String generateKey(String codesComp,
			Integer delay, String idCompte) {	
		Integer codeProduitPlusComposants = CodeReader.getCodes(codesComp);
		try {
			return generateKey(codeProduitPlusComposants,delay,idCompte);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 *  Main function that generate the key with the parameters <codesComposants(code1,code2,code3)> <delai> <ID Compte>"
	 */
	public static void main(String[] args) {
		if (args.length != NB_CHAMPS_CLE)
			System.out
					.println("Usage : <codesComposants(code1,code2,code3)> <delai> <ID Compte>");
		else {
			String codedText = "";
			try {
				codedText = KeyGenerator.generateKey(args[0],//CodesComposants
						Integer.parseInt(args[1]),// delai
						     args[2]);// ID compte 
			} catch (Exception e1) {
				e1.printStackTrace();
			}
//			Decodage d'une clef
//			KeyInformation kf = new KeyInformation(codedText);
//			String decodedString = kf.getDecodedString();
//
//			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"/* FORMAT_DATE */);
//			System.out.println("Codes de la clef : " + kf.getCodes());
//			System.out.println("Date de fin de clef : "
//					+ dateFormat.format(kf.getValidationDate()));
//			System.out.println("ID du compte de la clef : " + kf.getidCompte());
//			System.out.println("Validité: " + kf.getValidity());
//			kf.hasCode("CODE_GED_M_WORKFLOW");
//			kf.hasCode("CODE_GED_G_W_ALFRESCO_30L");
//			System.out.println("decodedText = " + decodedString);
//			System.out.println("codedText = ");
			System.out.println(codedText);
		}

	}
}
