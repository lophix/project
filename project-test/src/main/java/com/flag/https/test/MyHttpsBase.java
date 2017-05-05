package com.flag.https.test;

import javax.crypto.Cipher;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Random;

/**
 * @author xuj
 * @since 2017-05-05-11:03
 */
public class MyHttpsBase {
    static PrivateKey privateKey;
    static PublicKey publicKey;
    protected MyCertificateManager manager;

    public MyHttpsBase(String type) throws CertificateException {
        this.manager = new MyCertificateManager(type);
    }

    public static boolean byteEquals(byte a[], byte[] b) {
        boolean equals = true;
        if (a == null || b == null) {
            equals = false;
        }

        if (a != null && b != null) {
            if (a.length != b.length) {
                equals = false;
            } else {
                for (int i = 0; i < a.length; i++) {
                    if (a[i] != b[i]) {
                        equals = false;
                        break;
                    }
                }
            }

        }
        return equals;
    }

    public static byte[] decrypt(byte data[]) throws Exception {
        // 对数据解密
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte data[], SecureRandom seed) throws Exception {
        // 对数据解密
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey, seed);
        return cipher.doFinal(data);
    }

    public byte[] decryptByPublicKey(byte data[], SecureRandom seed) throws Exception {
        if (publicKey == null) {
            publicKey = manager.readPublicKeys();
        }
        // 对数据解密
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
        if (seed == null) {
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
        } else {
            cipher.init(Cipher.DECRYPT_MODE, publicKey, seed);
        }

        return cipher.doFinal(data);
    }

    public byte[] decryptByDes(byte data[], SecureRandom seed) throws Exception {
        if (publicKey == null) {
            publicKey = manager.readPublicKeys();
        }
        // 对数据解密
        Cipher cipher = Cipher.getInstance("DES");
        if (seed == null) {
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
        } else {
            cipher.init(Cipher.DECRYPT_MODE, publicKey, seed);
        }

        return cipher.doFinal(data);
    }


    public byte[] encryptByPublicKey(byte[] data, SecureRandom seed)
            throws Exception {
        if (publicKey == null) {
            publicKey = manager.readPublicKeys();
        }
        // 对数据加密
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
        if (seed == null) {
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        } else {
            cipher.init(Cipher.ENCRYPT_MODE, publicKey, seed);
        }

        return cipher.doFinal(data);
    }

    public static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (byte aB : b) {
            stmp = (Integer.toHexString(aB & 0XFF));
            if (stmp.length() == 1) {
                hs.append("0").append(stmp);
            } else {
                hs.append("  ").append(stmp);
            }
        }
        return hs.toString().toUpperCase();
    }

    public static byte[] cactHash(byte[] bytes) {
        byte[] _bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(bytes);
            _bytes = md.digest();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return _bytes;
    }


    static String random() {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        int seedLength = 10;
        for (int i = 0; i < seedLength; i++) {
            builder.append(digits[random.nextInt(seedLength)]);
        }

        return builder.toString();
    }

    static char[] digits = {
            '0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e',
            'f', 'g', 'h', 'i', 'j'
    };
}
