package com.bluexml.side.Integration.licenses.LicenseMgmt;

import java.math.BigInteger;

public class Encoder
{

    private Encoder()
    {
    }

    public static String encode(String s)
    {
        BigInteger temp = new BigInteger(s);
        return temp.toString(ENCODE_BASE);
    }

    public static String desencode(String s)
    {
        BigInteger temp = new BigInteger(s, ENCODE_BASE);
        return temp.toString();
    }

    public static int ENCODE_BASE = 36;

}
