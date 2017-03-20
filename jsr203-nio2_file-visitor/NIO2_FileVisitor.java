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
import java.nio.file.*;
import java.nio.file.attribute.*;

public class NIO2_FileVisitor extends SimpleFileVisitor<Path> {
    private PathMatcher pathMatcher;
    
    @Override
    public FileVisitResult visitFile(Path filePath, BasicFileAttributes basicFileAttributes) {        
        if (filePath.getName() != null && pathMatcher.matches(filePath.getName()))
            System.out.println("FILE: " + filePath);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path directoryPath) {
        if (directoryPath.getName() != null && pathMatcher.matches(directoryPath.getName()))
            System.out.println("DIR: " + directoryPath);
        return FileVisitResult.CONTINUE;
    }

    public static void main(String[] args) throws IOException {
        NIO2_FileVisitor fileVisitor = new NIO2_FileVisitor();
        fileVisitor.pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + "*txt*");
        Files.walkFileTree(Paths.get("D://Search"), fileVisitor);
    }
}

