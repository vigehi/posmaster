package sample;

import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Base64;

public class webComm  {
    public static String auth(String myURL, String appKey, String appPass) {
        String auth = null;
        if(appKey == null || appPass == null) return auth;

        try {
            String authUser = Base64.getEncoder().encodeToString(appKey.getBytes("UTF-8"));
            String authPass = Base64.getEncoder().encodeToString(appPass.getBytes("UTF-8"));
            System.out.println("Base64 Encode Username and password : " + authUser + " : " + authPass);

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(myURL)
                    .get()
                    .addHeader("action", "authorization")
                    .addHeader("authUser", authUser)
                    .addHeader("authPass", authPass)
                    .addHeader("cache-control", "no-cache")
                    .build();
            Response response = client.newCall(request).execute();
            String rBody = response.body().string();

            JSONObject jResp = new JSONObject(rBody);
            int ResultCode = jResp.getInt("ResultCode");
            if(jResp.has("ResultCode") && (jResp.getInt("ResultCode") == 0)) {
                auth = jResp.getString("access_token");
                System.out.println("Authenticated : " + auth);
            } else {
                System.out.println("Not Authenticated");
            }
        } catch(IOException | JSONException ex) {
            System.out.println("IO Error : " + ex);
        }

        return auth;
    }

    public static String sendData(String myURL, String auth, String action, String data) {
        String resp = null;

        try {
            System.out.println("BASE URL : " + myURL);
            System.out.println("BASE ACTION : " + action);
            System.out.println("BASE DATA : " + data);

            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, data);
            if(auth == null) {
                Request request = new Request.Builder()
                        .url(myURL)
                        .post(body)
                        .addHeader("action", action)
                        .addHeader("content-type", "application/json")
                        .build();
                Response response = client.newCall(request).execute();
                resp = response.body().string();
            } else {
                Request request = new Request.Builder()
                        .url(myURL)
                        .post(body)
                        .addHeader("action", action)
                        .addHeader("authorization", auth)
                        .addHeader("content-type", "application/json")
                        .build();
                Response response = client.newCall(request).execute();
                resp = response.body().string();
            }

            System.out.println(resp);
        } catch(IOException ex) {
            System.out.println("IO Error : " + ex);
        }

        return resp;
    }
}
