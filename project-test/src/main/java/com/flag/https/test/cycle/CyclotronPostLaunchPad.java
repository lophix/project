package com.flag.https.test.cycle;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author xuj
 * @since 2017-07-03-11:21
 */
public class CyclotronPostLaunchPad {

    private static final int TREAD_NUM = 50;
    private static final ScheduledExecutorService SCHEDULED_EXECUTOR_SERVICE = Executors.newScheduledThreadPool(TREAD_NUM);
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();
    private static final Logger LOGGER = LogManager.getLogger(CyclotronPostLaunchPad.class);
    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(TREAD_NUM);

    public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, UnsupportedEncodingException {
        for (int i = 0; i < TREAD_NUM; i++) {
//            launching();
            launchingSchedule();
        }
//        EXECUTOR_SERVICE.shutdown();

//        SCHEDULED_EXECUTOR_SERVICE.shutdown();
    }

    private static void launchingSchedule() {
        SCHEDULED_EXECUTOR_SERVICE.scheduleAtFixedRate(() -> {
            HttpClient httpClient = new DefaultHttpClient();
            try {
                long start = System.currentTimeMillis();
                HttpPost post = prepareRequest();
                HttpResponse response = httpClient.execute(post);
                if (response != null) {
                    HttpEntity resEntity = response.getEntity();
//                    if (resEntity != null) {
//
//                    }
                  LOGGER.info("received message is {},time {}", resEntity.getContentLength(),System.currentTimeMillis() - start);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, 0, 10, TimeUnit.MILLISECONDS);
    }

    private static void launching() throws KeyManagementException, NoSuchAlgorithmException {
//        DefaultSSLClient httpClient = new DefaultSSLClient();
        HttpClient httpClient = new DefaultHttpClient();
        EXECUTOR_SERVICE.submit(() -> {
                    try {
                        HttpPost post = prepareRequest();
                        COUNT_DOWN_LATCH.countDown();
                        COUNT_DOWN_LATCH.await();
                        HttpResponse response = httpClient.execute(post);
                        if (response != null) {
                            HttpEntity resEntity = response.getEntity();
                            if (resEntity != null) {
                                LOGGER.info("received message is {}", EntityUtils.toString(resEntity, "utf8"));
                            }
                        }
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
        });
    }

    private static HttpPost prepareRequest() throws IOException {
        HttpPost request = new HttpPost("http://192.168.10.60:8445/timesharing");
        request.addHeader("Accept", "*/*");
        request.addHeader("content-type", "application/x-www-form-urlencoded");
        request.addHeader("cache-control", "no-cache");
        request.addHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("app_id", "201704282300007148"));
        nvps.add(new BasicNameValuePair("method", "pt101"));
        nvps.add(new BasicNameValuePair("charset", "utf-8"));
        nvps.add(new BasicNameValuePair("version", "2017-07-03 03:07:50"));
        nvps.add(new BasicNameValuePair("timestamp", new Date().toString()));
        nvps.add(new BasicNameValuePair("biz_content", bizContent()));
        nvps.add(new BasicNameValuePair("sign", sign(nvps)));
        request.setEntity(new UrlEncodedFormEntity(nvps, "utf8"));
        return request;
    }

    private static String bizContent() throws IOException {
        Map<String, String> map = new HashMap<>();
//        map.put("carPlate", "粤B00000");
        map.put("pot_type","1");

        map.put("device_id", "116502100005188,0001,028000003883,1,11,11001,110010,1102,11111111111111,115262100000501,116232100003372,116232100003741,116232100003742,116232100003743,116232100003744,116232100003745,116232100003746,116232100003747,116232100003749,116232100003750,116232100003751,116232100003752,116232100003753,116232100003755,116232100003756,116232100003758,116232100003759,116232100003760,116232100003761,116232100003762,116232100003999,116232100004000,116232100004001,116232100004002,116232100004003,116232100004004,116232100004005,116232100004006,116232100004007,116232100004008,116232100004009,116232100004010,116232100004011,116232100004012,116232100004013,116232100004014,116232100004015,116232100004016,116232100004017,116232100004018,116232100004019,116232100004020,116232100004021,116232100004022,116232100004023,116232100004024,116232100004025,116232100004026,116232100004027,116232100004028,116232100004596,116232100004597,116232100004599,116232100004601,116232100004603,116232100004604,116232100004607,116232100004610,116232100004611,11623210");
//        map.put("device_id", "116502100005188,6150015");
//        map.put("order_id", UUID.randomUUID().toString());
//        map.put("order_auth", "admin");
//        map.put("order_live", "20170703191919");
//        map.put("opt", "1");
//        map.put("notify_url", "https://127.0.0.1:8443/timesharing");
        return new ObjectMapper().writeValueAsString(map);
    }

    private static String sign(List<NameValuePair> nvps) throws UnsupportedEncodingException {
        List<String> keys = new ArrayList<>();
        nvps.forEach(nvp -> keys.add(nvp.getName()));
        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        root : for (String k : keys) {
            for (NameValuePair nvp : nvps) {
                if (nvp.getName().equals(k)){
                    sb.append(k).append("=").append(nvp.getValue()).append("&");
                    continue root;
                }
            }
        }
        String source = URLEncoder.encode(sb.substring(0, sb.length() - 1), "utf-8");

        byte[] b = sign(source, "");
        return new String(Base64.encode(b));
    }


    private static final String MAC_NAME = "HmacSHA1";
    private static final String ENCODING = "UTF-8";

    public static byte[] sign(String encryptText, String encryptKey){
        if(encryptText == null || encryptKey == null){
            return null;
        }

        try {
            byte[] data = encryptKey.getBytes(ENCODING);
            SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
            Mac mac = Mac.getInstance(MAC_NAME);
            mac.init(secretKey);

            byte[] text = encryptText.getBytes(ENCODING);
            return mac.doFinal(text);
        } catch (Exception e) {
            throw new RuntimeException("HmacSha1Signature 加密出错", e);
        }

    }

}
