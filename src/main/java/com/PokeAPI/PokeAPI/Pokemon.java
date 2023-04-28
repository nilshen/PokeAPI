package com.PokeAPI.PokeAPI;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    private String name;
    private Integer weight;
    private Integer height;
    private ArrayList abilities;

    public Pokemon() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHeight() {
        return height;
    }


    public Integer getWeight() {
        return weight;
    }


    public ArrayList getAbilities() {
        return abilities;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setAbilities(ArrayList abilities) {
        this.abilities = abilities;
    }
}
