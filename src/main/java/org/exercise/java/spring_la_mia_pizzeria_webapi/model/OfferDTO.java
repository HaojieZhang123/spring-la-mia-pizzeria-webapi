package org.exercise.java.spring_la_mia_pizzeria_webapi.model;

import java.time.LocalDate;

public class OfferDTO {

    private LocalDate startDate;
    private LocalDate endDate;
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
