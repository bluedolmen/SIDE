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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import keyManager.GeneratorConstants;

/**
 * This class decodes the SIDE Key and extract important informations from it
 * 
 * @author <a href="mailto:pbertrand@bluexml.com">Pierre BERTRAND</a>
 */
public class KeyInformation implements GeneratorConstants {

	// The decoded key
	private String decodedString;
	// Program codes extracted from the key
	private Integer codes;
	// Validation date extracted from the key
	private Date validationDate;
	// ID of the count in the silverstripe database
	private String idCompte;
	// Tells if the key is valid or not
	private String iDMach;
	// Tells if the key is valid or not
	private Boolean validity;

	/**
	 * Extract information from the SIDE crypted key
	 * 
	 * @param criptedKey
	 *            The crypted key to extract informations
	 */
	public KeyInformation(String hostID,String criptedKey) {
		validity = true;
		decodedString = "";
		codes = 0;
		validationDate = new Date(0);
		idCompte = "";
		iDMach="";
		try {
			decodedString = decodeKey(criptedKey);
			String[] result = decodedString.split("\\" + TEXT_SEPARATOR);
			if (result.length == NB_CHAMPS_CLE) {
				String dateTmp = Encoder.desencode(result[INDEX_DATE]);
				DateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
				try {
					validationDate = dateFormat.parse(dateTmp);
					Date actuelle = new Date();
					if (validationDate.before(actuelle)){
						validity = false;
					}
				} catch (ParseException e1) {
					validity = false;
				}
				codes = Integer.parseInt(Encoder.desencode(result[INDEX_CODES]));
				idCompte = Encoder.desencode(result[INDEX_ID]);
				iDMach = result[INDEX_ID_MACH];
				if (!iDMach.equals(hostID)){
					validity = false;
				}
			} else {
				validity = false;
			}
		} catch (Exception e) {
			validity = false;
		}
	}

	/**
	 * Decode the crypted key given in parameter
	 * 
	 * @param strKey
	 *            Key to decrypt
	 * @return The key Decrypted
	 * @throws Exception
	 *             Exceptions made by the Cypher while decrypting
	 */
	private String decodeKey(String strKey) throws Exception {
		IvParameterSpec salt = new IvParameterSpec(iv);
		strKey = Encoder.desencode(strKey);

		byte[] raw = SECRET_KEY.getBytes("UTF-8");
		SecretKey skeySpec = new SecretKeySpec(raw, "Blowfish");
		// Decryption
		Cipher c = Cipher.getInstance(ALGORYTHM);
		c.init(Cipher.DECRYPT_MODE, skeySpec, salt);
		String sM255 = "";
		Integer length = (strKey.length() / 3);
		byte[] buf_crypt = new byte[length];
		for (int i = 0; i < length; i++) {
			String temp = strKey.substring(i * 3, (i + 1) * 3);
			Integer itemp = Integer.parseInt(temp) - 255;
			sM255 += Integer.toString(Integer.parseInt(temp) - 255);
			buf_crypt[i] = itemp.byteValue();
		}
		buf_crypt = c.doFinal(buf_crypt);
		String result = new String(buf_crypt);
		return result;
	}

	/* Getters */
	public String getDecodedString() {
		return decodedString;
	}

	public Integer getCodes() {
		return codes;
	}

	public Date getValidationDate() {
		return validationDate;
	}

	public String getidCompte() {
		return idCompte;
	}

	public Boolean getValidity() {
		return validity;
	}

	/**
	 * Return true if the key possessed the code given in parameter
	 * 
	 * @param code
	 *            the code to test
	 * @return True if the key has the code, False if it doesn't
	 */
	public Boolean hasCode(String code) {
		Integer code2 = CodeReader.getCode(code);
		if (code2 == null) {
			return null;
		}
		Boolean result = ((codes & code2) != 0);
		return result;
	}

	public String getIDMach() {
		return iDMach;
	}
}
