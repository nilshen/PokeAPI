package com.PokeAPI.PokeAPI;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;


//
@RestController
public class PokemonDataController {

    @GetMapping("/pokemondata/{name}")
    public ArrayList<String> retrievePokemonData(
            @PathVariable String name) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://pokeapi.co/api/v2/pokemon/"+name))
                .header("content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());;
        String jsonString = response.body();

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);

        JsonArray abilitiesArray = jsonObject.getAsJsonArray("abilities");

        ArrayList<String> urlList = new ArrayList<String>();

        for (JsonElement abilityElement : abilitiesArray) {
            JsonObject abilityObject = abilityElement.getAsJsonObject();
            String url = abilityObject.getAsJsonObject("ability").get("url").getAsString();
            System.out.println(url);
            urlList.add(url);
//            HttpRequest requestAbility = HttpRequest.newBuilder()
//                    .uri(URI.create(url))
//                    .header("content-Type", "application/json")
//                    .build();
//
//            HttpClient clientAbility = HttpClient.newHttpClient();
//            HttpResponse<String> responseAbility = client.send(request, HttpResponse.BodyHandlers.ofString());;
//
//            System.out.println(responseAbility.body());
        }

        return urlList;
//        System.out.println(jsonObject.getClass());


//        Pokemon pokemon = new Pokemon();
//        Gson gson = new Gson();
//        pokemon = gson.fromJson(jsonString,Pokemon.class);;

//        Integer weight = pokemon.getWeight();
//        Integer height = pokemon.getHeight();
//        ArrayList abilities = pokemon.getAbilities();

//        HashMap<String, Integer> pokeArr = new HashMap<String, Integer>();
//        pokeArr.put("Weight", weight);
//        pokeArr.put("Height", height);

//        System.out.println(name + "'s height is " + height + ", weight is " + weight + ".");


    }
}
