package com.flag.https.test;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

/**
 * 证书工具类
 *
 * @author xuj
 * @since 2017-05-05-10:23
 */
public class MyCertificateManager {

    private final CertificateFactory factory;

    private static final String HTTPS_KEYSTORE_PATH = "C:\\https.keystore";

    private static final String CRT_PATH = "C:\\lazylit.crt";

    MyCertificateManager(String type) throws CertificateException {
        this.factory = CertificateFactory.getInstance(type);
    }

    byte[] readCertificates() throws Exception {
        Certificate cate = factory.generateCertificate(Files.newInputStream(Paths.get(CRT_PATH)));
        return cate.getEncoded();
    }

    public byte[] readPrivateKey() throws Exception {
        KeyStore store = KeyStore.getInstance("JKS");
        store.load(Files.newInputStream(Paths.get(HTTPS_KEYSTORE_PATH)), "lazylit".toCharArray());
        PrivateKey pk = (PrivateKey) store.getKey("lazylit", "lazylit".toCharArray());
        return pk.getEncoded();
    }

    PrivateKey readPrivateKeys() throws Exception {
        KeyStore store = KeyStore.getInstance("JKS");
        store.load(Files.newInputStream(Paths.get(HTTPS_KEYSTORE_PATH)), "lazylit".toCharArray());
        return (PrivateKey) store.getKey("lazylit", "lazylit".toCharArray());

    }

    PublicKey readPublicKeys() throws Exception {
        Certificate cate = factory.generateCertificate(Files.newInputStream(Paths.get(CRT_PATH)));
        return cate.getPublicKey();
    }

    Certificate createCertificate(byte b[]) throws Exception {
        return factory.generateCertificate(new ByteArrayInputStream(b));
    }

    public String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (byte aB : b) {
            stmp = (Integer.toHexString(aB & 0XFF));
            if (stmp.length() == 1) {
                hs.append("0").append(stmp);
            } else {
                hs.append(stmp);
            }
        }
        return hs.toString().toUpperCase();
    }
}
