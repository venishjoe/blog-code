/*******************************************************************************
 Copyright (c) 2017 Venish Joe Clarence (http://venishjoe.net)

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.

 ******************************************************************************/

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.InstructionPrinter;
import javassist.bytecode.Mnemonic;

import java.io.FileInputStream;
import java.io.PrintStream;

public class ByteCodeEditor {
    public static void main (String args[]) throws Exception {
        ByteCodeEditor _byteCodeEditor = new ByteCodeEditor();

        //Load test class
        ClassPool _classPool = ClassPool.getDefault();
        CtClass _ctClass = _classPool.makeClass(new FileInputStream("ByteCodeEditorTest.class"));

        //Call Manipulate method to change OPCODE
        _byteCodeEditor.manipulateComparision(_ctClass);

        //Other basic static analyis and manipulation functions
        _byteCodeEditor.addNewMethod(_ctClass);
        _byteCodeEditor.insertCodeInMethod(_ctClass);
        _byteCodeEditor.printMethodCode(_ctClass);
        _byteCodeEditor.printByteCode(_ctClass);
    }

    public static void manipulateComparision (CtClass _ctClass) throws Exception {
        for(CtMethod _ctMethods:_ctClass.getDeclaredMethods()){
            System.out.println("Starting Byte Code Editor. Inside Method: " + _ctMethods.getName());
            CodeAttribute _codeAttribute = _ctMethods.getMethodInfo().getCodeAttribute();
            CodeIterator _codeIterator = _codeAttribute.iterator();
            while (_codeIterator.hasNext()) {
                int _indexOfCode = _codeIterator.next();
                int _valueOfIndex8Bit = _codeIterator.byteAt(_indexOfCode);
                if(_valueOfIndex8Bit==153 && _indexOfCode==6) {
                    System.out.println("Trying to update " + _valueOfIndex8Bit + " on index " + _indexOfCode);
                    _codeIterator.writeByte(154, _indexOfCode);
                }
            }
        }
        _ctClass.writeFile();
        System.out.println("Update Successful!\n");
    }

    public static void addNewMethod (CtClass _ctClass) throws Exception {
        _ctClass.defrost();
        System.out.println("Starting to add new method");
        CtMethod _ctMethod = CtNewMethod.make("public int newMethodFromJA() { return 1; }", _ctClass);
        _ctClass.writeFile();
        System.out.println("Addition Successful!\n");
    }

    public static void printMethodCode (CtClass _ctClass) throws Exception {
        PrintStream ps = new PrintStream(System.out);
        InstructionPrinter instructionPrinter = new InstructionPrinter(ps);
        for(CtMethod method:_ctClass.getDeclaredMethods()){
            System.out.println("Method: " + method.getName());
            instructionPrinter.print(method);
        }
    }

    public static void printByteCode (CtClass _ctClass) throws Exception {
        for(CtMethod _ctMethods:_ctClass.getDeclaredMethods()){
            _ctClass.defrost();
            System.out.println("Method: " +_ctMethods.getName());
            CodeAttribute _codeAttribute = _ctMethods.getMethodInfo().getCodeAttribute();
            CodeIterator _codeIterator = _codeAttribute.iterator();
            while (_codeIterator.hasNext()) {
                int _indexOfInstruction = _codeIterator.next();
                int _indexValue8Bit = _codeIterator.byteAt(_indexOfInstruction);
                System.out.println(Mnemonic.OPCODE[_indexValue8Bit]);
            }
        }
    }

    public static void insertCodeInMethod (CtClass _ctClass) throws Exception {
        System.out.println("Starting to insert new code");
        for(CtMethod method:_ctClass.getDeclaredMethods()){
            _ctClass.defrost();
            method.insertBefore("System.out.println(\"Before every method call....\");");
            _ctClass.writeFile();
        }
        System.out.println("Addition Successful!\n");
    }

}
