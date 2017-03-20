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

import org.perf4j.LoggingStopWatch;
import org.perf4j.StopWatch;

public class StringConcatPerformance {
    private static final int OUTER_ITERATION=20;
    private static final int INNER_ITERATION=50000;
    
    public static void main (String args []) throws Exception {
        String addTestStr = "";
        String concatTestStr = "";
        StringBuffer concatTestSb = null;
        StringBuilder concatTestSbu = null;

        for (int outerIndex=0;outerIndex<=OUTER_ITERATION;outerIndex++) {
            StopWatch stopWatch = new LoggingStopWatch("StringAddConcat");
            addTestStr = "";
            for (int innerIndex=0;innerIndex<=INNER_ITERATION;innerIndex++)
                addTestStr += "*";
            stopWatch.stop();
        }        

        for (int outerIndex=0;outerIndex<=OUTER_ITERATION;outerIndex++) {
            StopWatch stopWatch = new LoggingStopWatch("StringConcat");
            concatTestStr = "";
            for (int innerIndex=0;innerIndex<=INNER_ITERATION;innerIndex++)
                concatTestStr = concatTestStr.concat("*");
            stopWatch.stop();
        }

        for (int outerIndex=0;outerIndex<=OUTER_ITERATION;outerIndex++) {
            StopWatch stopWatch = new LoggingStopWatch("StringBufferConcat");
            concatTestSb = new StringBuffer();
            for (int innerIndex=0;innerIndex<=INNER_ITERATION;innerIndex++)
                concatTestSb.append("*");
            stopWatch.stop();
        }

        for (int outerIndex=0;outerIndex<=OUTER_ITERATION;outerIndex++) {
            StopWatch stopWatch = new LoggingStopWatch("StringBuilderConcat");
            concatTestSbu = new StringBuilder();
            for (int innerIndex=0;innerIndex<=INNER_ITERATION;innerIndex++)
                concatTestSbu.append("*");
            stopWatch.stop();
        }
    }
}
