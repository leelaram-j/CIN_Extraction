package com.txt.write;

import java.io.FileOutputStream;
import java.io.IOException;

public class writeData
{
    public static void write2Txt(String opFileName,String splitID,String inName,String mcaName, String CIN, String address, String status) throws IOException
    {
        String str =splitID+"\t"+inName+"\t"+mcaName+"\t"+CIN+"\t"+address+"\t"+status+"\n";
    	FileOutputStream fos = new FileOutputStream(opFileName,true);
        byte[] strToBytes = str.getBytes();
        fos.write(strToBytes);
        fos.close();
    }
}
