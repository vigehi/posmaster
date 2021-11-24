package sample;

import okhttp3.*;
import java.io.IOException;
import java.util.Base64;

public class webComm  {

    public static String auth( String url,String userName, String password) throws IOException {

        String authUser = Base64.getEncoder().encodeToString(userName.getBytes("UTF-8"));
        String authPass = Base64.getEncoder().encodeToString(password.getBytes("UTF-8"));

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                //this is just a test endpoint to make sure that the login actually works
                .url(url)
                .method("POST", body)
                .addHeader("Accept", "application/json")
                .addHeader("action", "authorization")
                .addHeader("authUser", authUser)
                .addHeader("authPass", authPass)
                .build();

        String resp = null;
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            resp = response.body().string();

            System.out.println(resp);
        }

        return resp;
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
