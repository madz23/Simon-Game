package streak.game;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class IO {

    private final String BASE_URL = "http://localhost:8080/api/";

    public void post(Result result) throws IOException {

        Gson gson = new Gson();
        String jsonObject = gson.toJson(result);

        OkHttpClient client = new OkHttpClient();

        RequestBody requestJsonBody = RequestBody.create(
                MediaType.parse("application/json"),
                jsonObject
                );

        Request postRequest = new Request.Builder()
                .url(BASE_URL + "post")
                .post(requestJsonBody)
                .build();

        try {
            Response response = client.newCall(postRequest).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String testGet() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(BASE_URL + "test")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "No Response from Server";
        }
    }
}
