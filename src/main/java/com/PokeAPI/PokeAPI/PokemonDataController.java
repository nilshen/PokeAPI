package com.PokeAPI.PokeAPI;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;



//
@RestController
@Configuration
public class PokemonDataController {
    @Autowired
    public PokemonService pokemonService;

    @Autowired
    public BerryService berryService;

    @Autowired
    public PokemonDataController(PokemonService pokemonService, BerryService berryService) {
        this.pokemonService = pokemonService;
        this.berryService = berryService;
    }


    //endpoint: http://localhost:8000/pokemondata/{name}

    @PostMapping("/pokemondata")
    public String retrievePokemonData(
//            @PathVariable String name
            @RequestHeader String name,
            @RequestHeader int id) throws IOException, InterruptedException {

        JsonObject listPokemon = pokemonService.pokemonApi(name);
//        System.out.println(name);
//        System.out.println(listPokemon);
//        System.out.println(pokemonService.pokemonApi(name));


        JsonObject listBerry = berryService.berryApi(id);
//        System.out.println(id);
//        System.out.println(berryService.berryApi(id));

        String pokeString = new String();

        JsonObject mergedJsonObject = mergeJsonObjects(listPokemon, listBerry);

        // Convert the JSON object to a JSON string
        pokeString = new Gson().toJson(mergedJsonObject);


        return pokeString;

    }
    @Autowired
    public static JsonObject mergeJsonObjects(JsonObject jsonObject1, JsonObject jsonObject2) {
        JsonObject mergedJsonObject = new JsonObject();

        // Copy key-value pairs from the first JsonObject
//        if (jsonObject1 != null) {
            for (String key : jsonObject1.keySet()) {
            mergedJsonObject.add(key, jsonObject1.get(key));
            }
//        }

        // Copy key-value pairs from the second JsonObject
//        if (jsonObject2 != null) {
            for (String key : jsonObject2.keySet()) {
                mergedJsonObject.add(key, jsonObject2.get(key));
            }
//        }

        return mergedJsonObject;
    }
}
