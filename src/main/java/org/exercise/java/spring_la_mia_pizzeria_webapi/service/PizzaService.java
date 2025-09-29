package org.exercise.java.spring_la_mia_pizzeria_webapi.service;

import java.util.List;
import java.util.Optional;

import org.exercise.java.spring_la_mia_pizzeria_webapi.model.Offer;
import org.exercise.java.spring_la_mia_pizzeria_webapi.model.Pizza;
import org.exercise.java.spring_la_mia_pizzeria_webapi.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private OfferService offerService;

    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    public Optional<Pizza> findById(Integer id) {
        return pizzaRepository.findById(id);
    }

    public Pizza getById(Integer id) {
        Optional<Pizza> pizzaOptional = pizzaRepository.findById(id);
        if (pizzaOptional.isEmpty()) {
            return null;
        } else {
            return pizzaOptional.get();
        }
    }

    public List<Pizza> findByName(String name) {
        return pizzaRepository.findByNameContainingIgnoreCase(name);
    }

    public Pizza store(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public Pizza update(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public void deleteById(Integer id) {
        // delete related offers
        List<Offer> offers = pizzaRepository.findById(id).get().getOffers();
        for (Offer offer : offers) {
            offerService.deleteById(offer.getId());
        }
        pizzaRepository.deleteById(id);
    }

    public void delete(Pizza pizza) {
        // delete related offers
        List<Offer> offers = pizza.getOffers();
        for (Offer offer : offers) {
            offerService.deleteById(offer.getId());
        }
        pizzaRepository.delete(pizza);
    }
}
