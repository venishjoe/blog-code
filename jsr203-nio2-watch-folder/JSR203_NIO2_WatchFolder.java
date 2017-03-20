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

import java.nio.file.*;
import static java.nio.file.StandardWatchEventKind.*;

public class JSR203_NIO2_WatchFolder {

    static <T> WatchEvent<T> castEvent(WatchEvent<?> event) {
        return (WatchEvent<T>)event;
    }

    public static void main (String args[]) throws Exception {
        Path _directotyToWatch = Paths.get(args[0]);
        WatchService watcherSvc = FileSystems.getDefault().newWatchService();
        WatchKey watchKey = _directotyToWatch.register(watcherSvc, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

        while (true) {
            watchKey=watcherSvc.take();
            for (WatchEvent<?> event: watchKey.pollEvents()) {
                WatchEvent<Path> watchEvent = castEvent(event);
                System.out.println(event.kind().name().toString() + " " + _directotyToWatch.resolve(watchEvent.context()));
                watchKey.reset();
            }
        }
    }
}
