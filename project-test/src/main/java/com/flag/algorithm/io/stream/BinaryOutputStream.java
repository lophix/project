package com.flag.algorithm.io.stream;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author xuj
 * @since 2018-01-15-14:16
 */
public class BinaryOutputStream extends OutputStream {

    private OutputStream out;

    private byte oneByte = 0;
    private int bitLen = 0;

    public BinaryOutputStream(Path path) throws IOException {
        out = Files.newOutputStream(path);
    }

    @Override
    public void write(int b) throws IOException {
        if (b > 1) {
            throw new IOException("wrong binary number");
        }
        oneByte |= b << (7 - bitLen);
        if (++bitLen == 8) {
            out.write(oneByte);
            bitLen = 0;
            oneByte = 0;
        }
    }

    @Override
    public void close() throws IOException {
        out.flush();
        out.close();
    }
}
