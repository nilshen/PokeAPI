package com.PokeAPI.PokeAPI;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BerryServiceTest {
    @Test
    public void berryServiceCall() throws IOException, InterruptedException {
        BerryService berryService = new BerryService();
        String result = new Gson().toJson(berryService.berryApi(1));
        assertEquals("{\"Berries\":\"cheri\"}", result);
    }


}
