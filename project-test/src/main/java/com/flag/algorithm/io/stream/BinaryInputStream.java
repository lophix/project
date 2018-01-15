package com.flag.algorithm.io.stream;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author xuj
 * @since 2018-01-15-14:36
 */
public class BinaryInputStream extends InputStream {

    private InputStream in;
    private byte oneByte;
    private int offset = -1;

    public BinaryInputStream(Path path) throws IOException {
        in = Files.newInputStream(path);
    }

    @Override
    public int read() throws IOException {
        if (offset < 0 || offset > 7) {
            int tmp = in.read();
            if (tmp == -1) {
                offset = -1;
                return -1;
            }
            oneByte = (byte) tmp;
            offset = 0;
        }
        return (oneByte >>> (7 - offset++)) & 1;
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}
