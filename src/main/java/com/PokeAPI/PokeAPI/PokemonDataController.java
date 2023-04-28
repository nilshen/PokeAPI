package com.PokeAPI.PokeAPI;

import com.google.gson.Gson;
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

        ArrayList<Integer> pokeArr = new ArrayList<Integer>();
        pokeArr.add(weight);
        pokeArr.add(height);

        System.out.println(abilities.get(0));


//        return pokeArr;
//        System.out.println(name);
//        System.out.println(request);
//        System.out.println(response.body());
//        System.out.println(responseBody);
//        System.out.println(new ResponseBody.getAbilities());


    }
}
