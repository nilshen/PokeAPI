package com.PokeAPI.PokeAPI;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class PokemonService {
        private String name;

        public JsonObject pokemonService (String name) throws IOException, InterruptedException {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://pokeapi.co/api/v2/pokemon/"+name))
                    .header("content-Type", "application/json")
                    .build();

            HttpClient client = HttpClient.newHttpClient();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());;
            String jsonString = response.body();



            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);

            int weight = jsonObject.get("weight").getAsInt();
            int height = jsonObject.get("height").getAsInt();

            JsonArray abilitiesArray = jsonObject.getAsJsonArray("abilities");

            ArrayList<String> abilityNameList = new ArrayList<String>();

            for (JsonElement abilityElement : abilitiesArray) {
                JsonObject abilityObject = abilityElement.getAsJsonObject();
                String abilityName = abilityObject.getAsJsonObject("ability").get("name").getAsString();
                abilityNameList.add(abilityName);
            }

            JsonObject pokenObj = new JsonObject();
            // Add key-value pairs to the JSON object
            pokenObj.addProperty("name", name);
            pokenObj.addProperty("weight", weight);
            pokenObj.addProperty("height", height);
            // Add the ArrayList to the JsonObject
            JsonArray jsonArray = new Gson().toJsonTree(abilityNameList).getAsJsonArray();
            pokenObj.add("abilities", jsonArray);

            return pokenObj;

        }
}
