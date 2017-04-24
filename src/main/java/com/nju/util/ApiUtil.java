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
import org.json.JSONArray;
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

    public static String parseText(JSONObject json){
        JSONArray results = json.getJSONArray("ParsedResults");
        String ret = "";
        for (int i = 0; i < results.length(); i++) {
            JSONObject result = results.getJSONObject(i);
            JSONArray lines = result
                    .getJSONObject("TextOverlay")
                    .getJSONArray("Lines");
            //preProcess for all lines
            int avgHeight = 0;
            int avgWidth = 0;
            int minLeft = -1;
            int maxLeft = 0;
            for (int j = 0; j < lines.length(); j++) {
                JSONObject line = lines.getJSONObject(j);
                avgHeight += line.getInt("MaxHeight");
                JSONArray words = line.getJSONArray("Words");
                int fl = words.getJSONObject(0).getInt("Left");
                int ll = words.getJSONObject(words.length()-1).getInt("Left");
                if (minLeft == -1 || fl < minLeft) minLeft = fl;
                if (maxLeft < ll) maxLeft = ll;
                int tmp = 0;
                for (int k = 0; k < words.length(); k++) {
                    tmp += words.getJSONObject(k).getInt("Width");
                }
                tmp /= words.length();
                avgWidth += tmp;
            }
            avgHeight /= lines.length();
            avgWidth /= lines.length();
            //concat
            int j = 0;
            while (j < lines.length()){
                JSONObject line = lines.getJSONObject(j);
                JSONArray words = line.getJSONArray("Words");
                JSONObject firstWord = line.getJSONArray("Words").getJSONObject(0);
                JSONObject lastWord = words.getJSONObject(words.length()-1);
                if (isLeftIn(firstWord.getInt("Left"), minLeft, avgWidth)
                        && ret.length()>0
                        && ret.charAt(ret.length()-1)!='\n'){
                    ret = ret.concat("\n");
                }
                if (j>0 && isTopIn(line.getInt("MinTop"), lines.getJSONObject(j-1).getInt("MinTop"), avgHeight)
                        && ret.length()>0
                        && ret.charAt(ret.length()-1)!='\n'){
                    ret = ret.concat("\n");
                }
                for (int k = 0; k < line.getJSONArray("Words").length(); k++) {
                    JSONObject word = line.getJSONArray("Words").getJSONObject(k);
                    ret = ret.concat(word.getString("WordText"));
                }
                if (isLeftIn(maxLeft, lastWord.getInt("Left"), avgWidth)){
                    ret = ret.concat("\n");
                }
                j++;
            }
        }
        ret = ret.replaceAll("\\n", "\n\n");
        return ret;
    }

    private static boolean isLeftIn(int left, int minLeft, int width){
        if (left - minLeft > width) return true;
        return false;
    }

    private static boolean isTopIn(int top, int lastTop, int height){
        if (top - lastTop > height * 2) return true;
        return false;
    }

    public static void main(String[] args) {
        String response = postFile("src/main/resources/test2.png", "chs");
        JSONObject res = new JSONObject(response);
        String text = parseText(res);
        System.out.println(text);
    }
}
