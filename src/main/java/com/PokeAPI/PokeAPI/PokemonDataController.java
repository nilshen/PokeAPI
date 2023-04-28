package com.PokeAPI.PokeAPI;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;


//
@RestController
public class PokemonDataController {

    //endpoint: http://localhost:8000/pokemondata/{name}
    @PostMapping("/pokemondata")
    public String retrievePokemonData(
//            @PathVariable String name
            @RequestBody String name) throws IOException, InterruptedException {
        //re-route: additional API call to get Pokemon details
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


        Pokemon pokemon = new Pokemon();
        pokemon.setHeight(height);
        pokemon.setWeight(weight);
        pokemon.setName(name);
        pokemon.setAbilities(abilityNameList);

        return "Hello, my name is " + pokemon.getName() + ". My weight is " + pokemon.getWeight() + ". My Height is " + pokemon.getHeight() +
                ". And my abilities are " +pokemon.getAbilities() + ".";
    }
}
