package com.PokeAPI.PokeAPI;

import java.util.ArrayList;

public class Pokemon {
    private Integer weight;
    private Integer height;
    private ArrayList abilities;

    public Pokemon() {

    }


    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public ArrayList getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList abilities) {
        this.abilities = abilities;
    }
}
