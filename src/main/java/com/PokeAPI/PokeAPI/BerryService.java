package com.PokeAPI.PokeAPI;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BerryService {
    private int id;

    public JsonObject berryService (int id) throws IOException, InterruptedException {
        //second API call
        HttpRequest requestBerry = HttpRequest.newBuilder()
                .uri(URI.create("https://pokeapi.co/api/v2/berry/"+id))
                .header("content-Type", "application/json")
                .build();

        HttpClient clientBerry = HttpClient.newHttpClient();

        HttpResponse<String> responseBerry = clientBerry.send(requestBerry, HttpResponse.BodyHandlers.ofString());;
        String jsonStringBerry = responseBerry.body();

        Gson gsonBerry = new Gson();
        JsonObject jsonObjectBerry = gsonBerry.fromJson(jsonStringBerry, JsonObject.class);

        String berryName = jsonObjectBerry.get("name").getAsString();

        JsonObject pokenObj = new JsonObject();
        pokenObj.addProperty("Berries", berryName);

        return pokenObj;
    }
}
