package org.exercise.java.spring_la_mia_pizzeria_webapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.exercise.java.spring_la_mia_pizzeria_webapi.model.Ingredient;
import org.exercise.java.spring_la_mia_pizzeria_webapi.model.Offer;
import org.exercise.java.spring_la_mia_pizzeria_webapi.model.Pizza;
import org.exercise.java.spring_la_mia_pizzeria_webapi.model.PizzaDTO;
import org.exercise.java.spring_la_mia_pizzeria_webapi.service.IngredientService;
import org.exercise.java.spring_la_mia_pizzeria_webapi.service.OfferService;
import org.exercise.java.spring_la_mia_pizzeria_webapi.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/pizzas")
public class PizzaRestController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private OfferService offerService;

    @Autowired
    private IngredientService ingredientService;

    // index
    @GetMapping
    public List<Pizza> index() {
        return pizzaService.findAll();
    }

    // show
    @GetMapping("/{id}")
    public ResponseEntity<Pizza> show(@PathVariable("id") Integer id) {
        Optional<Pizza> pizzaOptional = pizzaService.findById(id);
        if (pizzaOptional.isEmpty()) {
            return new ResponseEntity<Pizza>(HttpStatusCode.valueOf(404)); // 404 Not Found
        } else {
            return new ResponseEntity<Pizza>(pizzaOptional.get(), HttpStatusCode.valueOf(200)); // 200 OK
        }
    }

    // create
    @PostMapping
    public ResponseEntity<Pizza> create(@Valid @RequestBody PizzaDTO pizza, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Pizza>(HttpStatusCode.valueOf(400)); // 400 Bad Request
        } else {
            Pizza newPizza = new Pizza();
            newPizza.setName(pizza.getName());
            newPizza.setDescription(pizza.getDescription());
            newPizza.setPhoto(pizza.getPhoto());
            newPizza.setPrice(pizza.getPrice());

            List<Offer> newOffers = new ArrayList<>();
            for (Integer offerId : pizza.getOfferIds()) {
                newOffers.add(offerService.findById(offerId).get());
            }
            newPizza.setOffers(newOffers);

            List<Ingredient> newIngredients = new ArrayList<>();
            for (Integer ingredientId : pizza.getIngredientIds()) {
                newIngredients.add(ingredientService.findById(ingredientId).get());
            }
            newPizza.setIngredients(newIngredients);

            return new ResponseEntity<Pizza>(pizzaService.store(newPizza), HttpStatusCode.valueOf(201)); // 201 Created
        }
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<Pizza> update(@PathVariable("id") Integer id, @Valid @RequestBody PizzaDTO pizza,
            BindingResult bindingResult) {
        if (pizzaService.findById(id).isEmpty()) {
            return new ResponseEntity<Pizza>(HttpStatusCode.valueOf(404)); // 404 Not Found
        } else if (bindingResult.hasErrors()) {
            return new ResponseEntity<Pizza>(HttpStatusCode.valueOf(400)); // 400 Bad Request
        } else {
            Pizza existingPizza = pizzaService.findById(id).get();

            existingPizza.setName(pizza.getName());
            existingPizza.setDescription(pizza.getDescription());
            existingPizza.setPhoto(pizza.getPhoto());
            existingPizza.setPrice(pizza.getPrice());

            // Get offers from DTO
            List<Offer> newOffers = new ArrayList<>();
            for (Integer offerId : pizza.getOfferIds()) {
                newOffers.add(offerService.findById(offerId).get());
            }

            // Get ingredients from DTO
            List<Ingredient> newIngredients = new ArrayList<>();
            for (Integer ingredientId : pizza.getIngredientIds()) {
                newIngredients.add(ingredientService.findById(ingredientId).get());
            }

            existingPizza.setOffers(newOffers);
            existingPizza.setIngredients(newIngredients);

            return new ResponseEntity<Pizza>(pizzaService.update(existingPizza), HttpStatusCode.valueOf(200)); // 200 OK
        }
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Pizza> delete(@PathVariable("id") Integer id) {
        if (pizzaService.findById(id).isEmpty()) {
            return new ResponseEntity<Pizza>(HttpStatusCode.valueOf(404)); // 404 Not Found
        } else {
            pizzaService.deleteById(id);
            return new ResponseEntity<Pizza>(HttpStatusCode.valueOf(200)); // 200 OK
        }
    }
}
