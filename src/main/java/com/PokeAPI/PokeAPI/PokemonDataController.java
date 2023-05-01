package com.PokeAPI.PokeAPI;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;



//
@RestController
public class PokemonDataController {

    private PokemonService pokemonService;
    private BerryService berryService;

//    @Autowired
//    public PokemonDataController(PokemonService pokemonService, BerryService berryService) {
//        this.pokemonService = pokemonService;
//        this.berryService = berryService;
//    }

    //endpoint: http://localhost:8000/pokemondata/{name}
    @PostMapping("/pokemondata")
    public String retrievePokemonData(
//            @PathVariable String name
            @RequestHeader String name,
            @RequestHeader int id) throws IOException, InterruptedException {
        //re-route: additional API call to get Pokemon details

        PokemonService pokemonService = new PokemonService();
        BerryService berryService = new BerryService();

        JsonObject listPokemon = pokemonService.pokemonService(name);
        JsonObject listBerry =   berryService.berryService(id);

        JsonObject mergedJsonObject = mergeJsonObjects(listPokemon, listBerry);

        // Convert the JSON object to a JSON string
        String pokeString = new Gson().toJson(mergedJsonObject);

        return pokeString;

    }

    public static JsonObject mergeJsonObjects(JsonObject jsonObject1, JsonObject jsonObject2) {
        JsonObject mergedJsonObject = new JsonObject();

        // Copy key-value pairs from the first JsonObject
        for (String key : jsonObject1.keySet()) {
            mergedJsonObject.add(key, jsonObject1.get(key));
        }

        // Copy key-value pairs from the second JsonObject
        for (String key : jsonObject2.keySet()) {
            mergedJsonObject.add(key, jsonObject2.get(key));
        }

        return mergedJsonObject;
    }
}
