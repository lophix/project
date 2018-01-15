package com.flag.algorithm.compress;

import com.flag.algorithm.io.stream.BinaryInputStream;
import com.flag.algorithm.io.stream.BinaryOutputStream;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author xuj
 * @since 2018-01-15-10:08
 */
public class PictureComp {

    public static void main(String[] args) {
        compress();
        expend();
    }

    public static void compress() {
        byte cnt = 0;
        byte b, old = 0;
        try {
            Path path = Paths.get("F:\\dirty\\bit_file");
            if (Files.notExists(path)) {
                Files.createFile(path);
            }
            InputStream input = new BinaryInputStream(Paths.get("F:\\dirty\\images.jpg"));
            OutputStream ops = Files.newOutputStream(path);
            b = (byte) input.read();
            while (b != -1) {
                if (b != old) {
                    ops.write(cnt);
                    cnt = 0;
                    old ^= 1;
                } else {
                    if (cnt == (byte) 0xFF) {
                        ops.write(cnt);
                        cnt = 0;
                        ops.write(cnt);
                    }
                }
                cnt++;
                b = (byte) input.read();
            }
            ops.write(cnt);
            ops.flush();
            ops.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void expend() {
        byte b = 0;
        try {
            Path path = Paths.get("F:\\dirty\\result_file");
            if (Files.notExists(path)) {
                Files.createFile(path);
            }
            byte[] bytes = Files.readAllBytes(Paths.get("F:\\dirty\\bit_file"));
            OutputStream ops = new BinaryOutputStream(path);
            for (byte by : bytes) {
                int size = Byte.toUnsignedInt(by);
                for (int i = 0; i < size; i++) {
                    ops.write(b);
                }
                b ^= 1;
            }
            ops.flush();
            ops.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
