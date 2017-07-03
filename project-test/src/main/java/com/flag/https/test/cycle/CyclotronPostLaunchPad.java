package com.flag.https.test.cycle;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author xuj
 * @since 2017-07-03-11:21
 */
public class CyclotronPostLaunchPad {

    private static final ScheduledExecutorService SCHEDULED_EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor();
    private static final Logger LOGGER = LogManager.getLogger(CyclotronPostLaunchPad.class);

    public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, UnsupportedEncodingException {
        launching();
    }

    private static void launching() throws KeyManagementException, NoSuchAlgorithmException {
        DefaultSSLClient httpClient = new DefaultSSLClient();
        SCHEDULED_EXECUTOR_SERVICE.scheduleAtFixedRate(() -> {
                    try {
                        HttpResponse response = httpClient.execute(prepareRequest());
                        if (response != null) {
                            HttpEntity resEntity = response.getEntity();
                            if (resEntity != null) {
                                LOGGER.info("received message is {}", EntityUtils.toString(resEntity, "utf8"));
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                0,
                2,
                TimeUnit.SECONDS);
    }

    private static HttpPost prepareRequest() throws IOException {
        HttpPost request = new HttpPost("https://127.0.0.1:8443/timesharing");
        request.addHeader("Accept", "*/*");
        request.addHeader("content-type", "application/x-www-form-urlencoded");
        request.addHeader("cache-control", "no-cache");
        request.addHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("app_id", "201704282300007148"));
        nvps.add(new BasicNameValuePair("method", "pt104"));
        nvps.add(new BasicNameValuePair("charset", "utf-8"));
        nvps.add(new BasicNameValuePair("version", "2017-07-03 03:07:50"));
        nvps.add(new BasicNameValuePair("biz_content", bizContent()));
        nvps.add(new BasicNameValuePair("sign", "ff8c067c71d7b620dbe832917ffc6f80"));
        request.setEntity(new UrlEncodedFormEntity(nvps, "utf8"));
        return request;
    }

    private static String bizContent() throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("carPlate", "粤B00000");
        map.put("device_id", "3184025");
        map.put("order_id", UUID.randomUUID().toString());
        map.put("order_auth", "admin");
        map.put("order_live", "20170703191919");
        map.put("opt", "1");
        map.put("notify_url", "https://127.0.0.1:8443/timesharing");
        return new ObjectMapper().writeValueAsString(map);
    }

}
