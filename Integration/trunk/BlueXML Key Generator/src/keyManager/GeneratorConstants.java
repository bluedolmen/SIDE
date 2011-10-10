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


/**
 * Constants used int the KeyGenerator and the KeyInformation
 * @author <a href="mailto:pbertrand@bluexml.com">Pierre BERTRAND</a>
 */
public interface GeneratorConstants {
	// The path of the file that contains the codes of the components
	public static String FILE_NAME = "codes.csv";
	// used with Cipher class
	public static byte[] iv = { (byte) 0xf8, (byte) 0x53, (byte) 0xd9,
		(byte) 0xa8, (byte) 0x0e, (byte) 0x99, (byte) 0xee, (byte) 0x7a };
	// Separator used in the creation of the key
	public static String TEXT_SEPARATOR = "|";
	// Separator used with the codes
	public static String CODE_SEPARATOR = ",";
	public static int KEY_SIZE = 512; // [512..2048]
	//public static String SECRET_KEY = "efQqf$FaiCiG((�8";
	//Key used to crypt informations
	public static String SECRET_KEY = "bLu3XmLcRyPt1nGB";
	// the format of the Date stored in the key
	public static String FORMAT_DATE = "yyyyddMM";
	// Algorithm used to crypt data
	public static String ALGORYTHM = "Blowfish/CBC/PKCS5Padding";
		
	//Index of 
	public static int INDEX_CODES = 0;
	public static int INDEX_DATE = 1;
	public static int INDEX_ID = 2;
	public static int INDEX_ID_MACH = 3;
	public static int NB_CHAMPS_CLE = 4;
	
	public static String CODE_ALL = "CODE_ALL_FEATURES"; 
}
