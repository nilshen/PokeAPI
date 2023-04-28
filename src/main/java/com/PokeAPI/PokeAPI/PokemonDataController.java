package com.PokeAPI.PokeAPI;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


//
@RestController
public class PokemonDataController {

    @GetMapping("/pokemondata/{name}")
    public void retrievePokemonData(
            @PathVariable String name) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://pokeapi.co/api/v2/pokemon/"+name))
                .header("content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Pokemon pokemon = new Pokemon();
        Gson gson = new Gson();
        pokemon = gson.fromJson(response.body(),Pokemon.class);;

        Integer weight = pokemon.getWeight();
        Integer height = pokemon.getHeight();
        ArrayList abilities = pokemon.getAbilities();

        HashMap<String, Integer> pokeArr = new HashMap<String, Integer>();
        pokeArr.put("Weight", weight);
        pokeArr.put("Height", height);

        System.out.println(name + "'s height is " + height + ", weight is " + weight + ".");

//        for (int i = 0; i < abilities.size(); i++) {
//            Ability ability = (Ability) abilities.get(0);
//            String url = ability.getAbility().getUrl();

//            System.out.println(ability);
//            System.out.println(url);

//        }




    }
}
