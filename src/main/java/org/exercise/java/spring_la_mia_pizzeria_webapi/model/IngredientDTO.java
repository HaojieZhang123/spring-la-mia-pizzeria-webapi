package org.exercise.java.spring_la_mia_pizzeria_webapi.model;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class IngredientDTO {
    @NotBlank(message = "Il nome dell'ingrediente non può essere vuoto")
    private String name;

    private List<Integer> pizzaIds;

    public IngredientDTO(String name, List<Integer> pizzaIds) {
        this.name = name;
        this.pizzaIds = pizzaIds;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getPizzaIds() {
        return pizzaIds;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPizzaIds(List<Integer> pizzaIds) {
        this.pizzaIds = pizzaIds;
    }
}