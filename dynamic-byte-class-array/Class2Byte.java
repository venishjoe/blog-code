/*
 * Copyright (c) 2009. Venish Joe Clarence (http://venishjoe.net)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import java.io.*;

public class Class2Byte {  
    public static void main (String args []) throws Exception {
        int _offset=0;
        int _read=0;

        File fileName = new File(args [0]);
        InputStream fileInputStream = new FileInputStream(fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(args[1]);
        PrintStream printStream = new PrintStream(fileOutputStream);
        StringBuffer bytesStringBuffer = new StringBuffer();
        
        byte[] byteArray = new byte[(int)fileName.length()];
        while (_offset < byteArray.length && (_read=fileInputStream.read(byteArray, _offset, byteArray.length-_offset)) >= 0)
            _offset += _read;    

        fileInputStream.close();
        for (int index = 0; index < byteArray.length; index++)
            bytesStringBuffer.append(byteArray[index]+",");

        printStream.print(bytesStringBuffer.length()==0 ? "" :  bytesStringBuffer.substring(0, bytesStringBuffer.length()-1));
    }
}
