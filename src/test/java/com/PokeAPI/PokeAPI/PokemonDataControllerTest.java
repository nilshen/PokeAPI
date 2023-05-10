package com.PokeAPI.PokeAPI;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
@ComponentScan
public class PokemonDataControllerTest {

    @Mock
    private PokemonDataController pokemonDataController;

    @Mock
    private PokemonService pokemonService;

    @Mock
    private BerryService berryService;

    @Autowired
    private MockMvc mockMvc;

//    PokemonService pokemonService = new PokemonService();
//    BerryService berryService = new BerryService();

    @Test
    public void PokemonDataController () throws Exception {

        ResultActions resultActions =
                mockMvc.perform(
                        post("/pokemondata")
                                .header("content-Type", "application/json")
                                .header("name", "pikachu")
                                .header("id", 1)
                );

        MvcResult mvcResult = resultActions.andReturn();
        String responseContent = mvcResult.getResponse().getContentAsString();

        assertEquals("{\"name\":\"pikachu\",\"weight\":60,\"height\":4,\"abilities\":[\"static\",\"lightning-rod\"],\"Berries\":\"cheri\"}", responseContent);

    }
}