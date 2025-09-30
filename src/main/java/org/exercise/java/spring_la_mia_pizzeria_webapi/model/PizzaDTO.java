package org.exercise.java.spring_la_mia_pizzeria_webapi.model;

import java.math.BigInteger;
import java.util.List;

import jakarta.persistence.Lob;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PizzaDTO {

    @NotBlank(message = "Il nome della pizza non può essere vuoto")
    @Size(min = 2, max = 50, message = "Il nome della pizza deve essere tra 2 e 50 caratteri")
    private String name;

    @Lob
    private String description;

    @NotBlank(message = "La foto della pizza non può essere vuota")
    private String photo;

    @NotNull(message = "Il prezzo della pizza non può essere vuoto")
    @Min(value = 0, message = "Il prezzo della pizza non puo' essere negativo")
    private BigInteger price;

    private List<Integer> offerIds;
    private List<Integer> ingredientIds;

    public PizzaDTO(String name, String description, String photo, BigInteger price, List<Integer> offerIds,
            List<Integer> ingredientIds) {
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.price = price;
        this.offerIds = offerIds;
        this.ingredientIds = ingredientIds;
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

    public List<Integer> getOfferIds() {
        return offerIds;
    }

    public void setOfferIds(List<Integer> offerIds) {
        this.offerIds = offerIds;
    }

    public List<Integer> getIngredientIds() {
        return ingredientIds;
    }

    public void setIngredientIds(List<Integer> ingredientIds) {
        this.ingredientIds = ingredientIds;
    }

}
