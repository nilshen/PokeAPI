package com.PokeAPI.PokeAPI;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokemonServiceTest {

    @Test
    public void pokemonServiceApi() throws IOException, InterruptedException {
        PokemonService pokemonService = new PokemonService();
//        String result = new Gson().toJson(pokemonService.pokemonService("pikachu"));
//        assertEquals("{\"name\":\"pikachu\",\"weight\":60,\"height\":4,\"abilities\":[\"static\",\"lightning-rod\"]}",result);
        JsonObject expect = new Gson().fromJson("{\"name\":\"pikachu\",\"weight\":60,\"height\":4,\"abilities\":[\"static\",\"lightning-rod\"]}", JsonObject.class);
        assertEquals(expect,pokemonService.pokemonApi("pikachu"));
    }
}
