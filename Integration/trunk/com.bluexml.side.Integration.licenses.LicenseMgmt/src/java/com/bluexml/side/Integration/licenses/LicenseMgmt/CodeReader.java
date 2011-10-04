package com.bluexml.side.Integration.licenses.LicenseMgmt;

import java.io.*;
import java.util.HashMap;

// Referenced classes of package keyManager:
//            GeneratorConstants

public class CodeReader
    implements GeneratorConstants
{

	private CodeReader()
    {
    }

    @SuppressWarnings("unchecked")
	private static HashMap makeHashTable()
    {
        HashMap tableOfCode = new HashMap();
        java.io.InputStream ipsCODE = null;
        try {
			ipsCODE = GetResourceAsStream("codes");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        InputStreamReader ipsrCODE = new InputStreamReader(ipsCODE);
        BufferedReader brCODE = new BufferedReader(ipsrCODE);
        String ligneCODE;
        try {
            while((ligneCODE = brCODE.readLine()) != null) {
            	try {
                	String parsedLine[] = ligneCODE.split("\\,");
                	tableOfCode.put(parsedLine[0], Integer.valueOf(Integer.parseInt(parsedLine[1])));
                } catch(ArrayIndexOutOfBoundsException a) {
                	//a.printStackTrace();
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        
        return tableOfCode;
        
    }

    public static Integer getCodes(String codeComponents)
    {
        Integer result = Integer.valueOf(0);
        String codes[] = codeComponents.split("\\,");
        for(int i = 0; i < codes.length; i++)
            result = Integer.valueOf(result.intValue() + getCode(codes[i]).intValue());
        return result;
    }

    public static Integer getCode(String code)
    {
        return (Integer)codeTable.get(code);
    }

    @SuppressWarnings("unchecked")
	private static HashMap codeTable = makeHashTable();

    public static InputStream GetResourceAsStream(String file) throws Exception {
    	InputStream myurl = CodeReader.class.getClassLoader().getResourceAsStream("com/bluexml/side/Integration/licenses/LicenseMgmt/resources/"+file);
		return myurl;
	} 
}
