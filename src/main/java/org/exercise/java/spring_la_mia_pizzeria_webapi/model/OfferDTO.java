package org.exercise.java.spring_la_mia_pizzeria_webapi.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OfferDTO {

    @NotNull(message = "La data di inizio non può essere vuota")
    private LocalDate startDate;

    @NotNull(message = "La data di fine non può essere vuota")
    private LocalDate endDate;

    @NotBlank(message = "Il titolo non può essere vuoto")
    private String title;

    private Integer pizzaId;

    public OfferDTO(LocalDate startDate, LocalDate endDate, String title, Integer pizzaId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.pizzaId = pizzaId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPizzaId() {
        return pizzaId;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPizzaId(Integer pizzaId) {
        this.pizzaId = pizzaId;
    }
}
