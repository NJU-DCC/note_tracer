package com.nju.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.*;

/**
 * Created by raychen on 2017/4/22.
 */
public class ApiUtil {

    public static String url = "http://api.ocr.space/parse/image";

    public static String postFile(String filePath, String lang){
        HttpClient httpClient = HttpClients.createDefault();
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addPart("apikey", new StringBody("9ec03cace988957", ContentType.TEXT_PLAIN))
                .addPart("language", new StringBody(lang, ContentType.TEXT_PLAIN))
                .addPart("isOverlayRequired", new StringBody("true", ContentType.TEXT_PLAIN))
                .addPart("file", new FileBody(new File(filePath)));
        HttpEntity entity = builder.build();
        HttpPost post = new HttpPost(url);
        post.setEntity(entity);
        try {
            HttpResponse response = httpClient.execute(post);
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                String charset = "UTF-8";
                return EntityUtils.toString(response.getEntity(), charset);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String response = postFile("src/main/resources/test.png", "chs");
        JSONObject res = new JSONObject(response);
        String text = res.getJSONArray("ParsedResults").getJSONObject(0).getString("ParsedText");
        System.out.println(text);
    }
}
