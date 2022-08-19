package service;

import com.google.gson.Gson;
import models.Order;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

public class StoreService {

    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();

    public void addNewOrder(Order newOrder) throws IOException, InterruptedException {


        MediaType JSON
                = MediaType.get("application/json; charset=utf-8");

        String json = gson.toJson(newOrder);

        String url = "https://petstore.swagger.io/v2/store/order";

        RequestBody body = RequestBody.create(json, JSON);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            int result = response.code();
            System.out.println("result = " + result);
        }
    }

    public void findOrderById(String orderId) throws IOException {

        String url = "https://petstore.swagger.io/v2/store/order/" + orderId;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            String result = Objects.requireNonNull(response.body()).string();
            System.out.println("Result = " + result);
        }
    }

    public void deleteOrderById(String orderId) throws IOException {
        String url = "https://petstore.swagger.io/v2/store/order/" + orderId;

        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();

        try (Response response = client.newCall(request).execute()) {
            int result = response.code();

            System.out.println("Result code = " + result);
        }
    }


    public void getInventoriesByStatus() throws IOException {

        String url = "https://petstore.swagger.io/v2/store/inventory";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            String result = Objects.requireNonNull(response.body()).string();

            System.out.println("Result = " + result);
        }
    }

}