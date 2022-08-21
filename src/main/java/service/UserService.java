package service;

import com.google.gson.Gson;
import models.User;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class UserService {
    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();
    private final static String BASE_URL = "https://petstore.swagger.io/v2/user/";

    public void createUser(User newUser) throws IOException, InterruptedException {

        MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");

        String json = gson.toJson(newUser);

        RequestBody body = RequestBody.create(json, mediaType);

        Request request = new Request.Builder()
                .url(BASE_URL)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            int result = response.code();
            System.out.println("result = " + result);
        }
    }

    public void deleteUserByName(String userName) throws IOException {
        String url = BASE_URL + userName;

        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();

        try (Response response = client.newCall(request).execute()) {
            int result = response.code();

            System.out.println("Result code = " + result);
        }
    }

    public void getByNameUser(String userName) throws IOException {
        String url = BASE_URL + userName;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            String result = Objects.requireNonNull(response.body()).string();
            System.out.println("Result = " + result);
        }
    }

    public void updateUser(User newUser, String userName) throws IOException {

        MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");

        String json = gson.toJson(newUser);

        String url = BASE_URL + userName;

        RequestBody body = RequestBody.create(json, mediaType);

        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            int result = response.code();
            System.out.println("Result code = " + result);
        }
    }

    public void logsIn(String userName, String password) throws IOException {

        String url = BASE_URL + "login?username=" + userName + "&password=" + password;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            int result = response.code();
            System.out.println("Result code = " + result);
        }
    }

    public void logsOut() throws IOException {
        String url = BASE_URL + "logout";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            int result = response.code();
            System.out.println("Result code = " + result);
        }
    }

    public void createUsersList(List<User> users) throws IOException {

        MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");

        String json = gson.toJson(users);

        String url = BASE_URL + "createWithList";

        RequestBody body = RequestBody.create(json, mediaType);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            int result = response.code();
            System.out.println("result = " + result);
        }
    }

    public void createWithArray(User[] users) throws IOException {

        MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");

        String json = gson.toJson(users);

        String url = BASE_URL + "createWithArray";

        RequestBody body = RequestBody.create(json, mediaType);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            int result = response.code();
            System.out.println("result = " + result);
        }
    }
}
