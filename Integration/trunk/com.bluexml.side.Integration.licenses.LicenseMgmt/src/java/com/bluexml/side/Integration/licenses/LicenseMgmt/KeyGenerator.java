package com.bluexml.side.Integration.licenses.LicenseMgmt;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

// Referenced classes of package keyManager:
//            GeneratorConstants, Encoder, CodeReader

public class KeyGenerator
    implements GeneratorConstants
{

	@SuppressWarnings("unused")
	private String codesComp;
	@SuppressWarnings("unused")
	private Integer delay;
	@SuppressWarnings("unused")
	private String idCompte;
	@SuppressWarnings("unused")
	private String iDMach;
	
    public KeyGenerator(String codesComp, Integer delay, String idCompte, String iDMach)
    {
    	this.codesComp = codesComp;
    	this.delay = delay;
    	this.idCompte = idCompte;
    	this.iDMach = iDMach;
    }

    private static String generateStringToCrypt(Integer codes, Integer delay, String idCompte, String iDMach)
    {
        String strCodes = Encoder.encode(codes.toString());
        Date date = new Date();
        date.setTime(date.getTime() + delay.longValue() * 0x5265c00L);
        DateFormat dateFormat = new SimpleDateFormat("yyyyddMM");
        String dateFin = Encoder.encode(dateFormat.format(date));
        idCompte = Encoder.encode(idCompte);
        String strToScript = (new StringBuilder(String.valueOf(strCodes))).append("|").append(dateFin).append("|").append(idCompte).append("|").append(iDMach).toString();
        return strToScript;
    }

    private static String generateKey(Integer codes, Integer delay, String idCompte, String iDMach)
        throws UnsupportedEncodingException {
        
    	String strKey = generateStringToCrypt(codes, delay, idCompte, iDMach);
        IvParameterSpec salt = new IvParameterSpec(iv);
        byte raw[] = "bLu3XmLcRyPt1nGB".getBytes("UTF-8");
        javax.crypto.SecretKey skeySpec = new SecretKeySpec(raw, "Blowfish");
        byte buf_crypt[] = (byte[])null;
        String result = new String();
        try {
            Cipher c = Cipher.getInstance("Blowfish/CBC/PKCS5Padding");
            c.init(1, skeySpec, salt);
            buf_crypt = c.doFinal(strKey.getBytes("UTF-8"));
        } catch(InvalidKeyException e) {
            e.printStackTrace();
        } catch(InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch(IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch(BadPaddingException e) {
            e.printStackTrace();
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch(NoSuchPaddingException e) {
            e.printStackTrace();
        }
        
        for(int i = 0; i < buf_crypt.length; i++)
            result = (new StringBuilder(String.valueOf(result))).append(Integer.toString(buf_crypt[i] + 255)).toString();

        return Encoder.encode(result);
    }

    public static String generateKey(String codesComp, Integer delay, String idCompte, String iDMach)
    {
        Integer codeComposants = CodeReader.getCodes(codesComp);
        try {
            return generateKey(codeComposants, delay, idCompte, iDMach);
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
        return "";
    }

    public static void main(String args[])
    {
        if(args.length != 4) {
            System.out.println("Usage : <codesComposants(code1,code2,code3)> <delai> <ID Compte> <IDMach>");
        } else {
            String codedText = "";
            try {
                codedText = generateKey(args[0], Integer.valueOf(Integer.parseInt(args[1])), args[2], args[3]);
            } catch(Exception e1) {
                e1.printStackTrace();
            }
            System.out.println(codedText);
        }
    }

}
