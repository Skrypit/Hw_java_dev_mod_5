package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Pet;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class PetService {

    OkHttpClient client = new OkHttpClient();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final String baseUrl = "https://petstore.swagger.io/v2/pet/";

    public void uploadImg(File file, String id) throws IOException {


        MediaType mediaTypeImg = MediaType.parse("image/*");

        String url = baseUrl + id + "/uploadImage";

        RequestBody fileBody = RequestBody.create(file, mediaTypeImg);
        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), fileBody)
                .build();

                Request request = new Request.Builder()
                .url(url)
                .post(multipartBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            int result = response.code();

            if (result >= 200 && result < 300) {
                System.out.println("Image uploaded!");
            }
            System.out.println("result = " + result);
        }
    }


    public void add(Pet newPet) throws IOException {
        MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");

        String json = gson.toJson(newPet);

        String url = baseUrl;

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

    public void update(Pet pet, int petId) throws IOException {
        MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");

        String json = gson.toJson(pet);

        //String id = String.valueOf(petId);

        String url = baseUrl + petId;

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

    public void findByStatus(String status) throws IOException {

        String url = baseUrl + "findByStatus?status=" + status;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            String result = Objects.requireNonNull(response.body()).string();
            System.out.println("Result = " + result);
        }
    }

    public void findById(String id) throws IOException {
        String url = baseUrl + id;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            String result = Objects.requireNonNull(response.body()).string();
            System.out.println("Result = " + result);
        }
    }


    public void updateWithFormData(Pet pet, int petId) throws IOException {
        MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");

        String json = gson.toJson(pet);

        String url = baseUrl + petId;

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

    public void delete(String id) throws IOException {
        String url = baseUrl + id;

        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();

        try (Response response = client.newCall(request).execute()) {
            int result = response.code();

            System.out.println("Result code = " + result);
        }
    }
}

