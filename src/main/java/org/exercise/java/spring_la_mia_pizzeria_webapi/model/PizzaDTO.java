package org.exercise.java.spring_la_mia_pizzeria_webapi.model;

import java.math.BigInteger;
import java.util.List;

public class PizzaDTO {

    private String name;
    private String description;
    private String photo;
    private BigInteger price;

    private List<Offer> offers;
    private List<Ingredient> ingredients;

    public PizzaDTO(String name, String description, String photo, BigInteger price, List<Offer> offers,
            List<Ingredient> ingredients) {
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.price = price;
        this.offers = offers;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

}
