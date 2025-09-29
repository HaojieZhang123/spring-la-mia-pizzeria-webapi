package org.exercise.java.spring_la_mia_pizzeria_webapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.exercise.java.spring_la_mia_pizzeria_webapi.model.Ingredient;
import org.exercise.java.spring_la_mia_pizzeria_webapi.model.IngredientDTO;
import org.exercise.java.spring_la_mia_pizzeria_webapi.model.Pizza;
import org.exercise.java.spring_la_mia_pizzeria_webapi.service.IngredientService;
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
@RequestMapping("/api/ingredients")
public class IngredientRestController {
    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public List<Ingredient> index() {
        return ingredientService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> show(@PathVariable("id") Integer id) {
        Optional<Ingredient> ingredientOptional = ingredientService.findById(id);
        if (ingredientOptional.isEmpty()) {
            return new ResponseEntity<Ingredient>(HttpStatusCode.valueOf(404)); // 404 Not Found
        } else {
            return new ResponseEntity<Ingredient>(ingredientOptional.get(), HttpStatusCode.valueOf(200)); // 200 OK
        }
    }

    @PostMapping
    public ResponseEntity<Ingredient> create(@Valid @RequestBody IngredientDTO ingredient,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Ingredient>(HttpStatusCode.valueOf(400)); // 400 Bad Request
        } else {
            Ingredient newIngredient = new Ingredient();
            newIngredient.setName(ingredient.getName());

            List<Pizza> pizzas = new ArrayList<>();
            for (Integer pizzaId : ingredient.getPizzaIds()) {
                pizzas.add(pizzaService.getById(pizzaId));
            }
            newIngredient.setPizzas(pizzas);

            return new ResponseEntity<Ingredient>(ingredientService.store(newIngredient), HttpStatusCode.valueOf(201));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> update(@PathVariable("id") Integer id,
            @Valid @RequestBody IngredientDTO ingredient,
            BindingResult bindingResult) {
        if (ingredientService.findById(id).isEmpty()) {
            return new ResponseEntity<Ingredient>(HttpStatusCode.valueOf(404)); // 404 Not Found
        } else if (bindingResult.hasErrors()) {
            return new ResponseEntity<Ingredient>(HttpStatusCode.valueOf(400)); // 400 Bad Request
        } else {
            Ingredient newIngredient = new Ingredient();
            newIngredient.setId(id);
            newIngredient.setName(ingredient.getName());

            List<Pizza> pizzas = new ArrayList<>();
            for (Integer pizzaId : ingredient.getPizzaIds()) {
                pizzas.add(pizzaService.getById(pizzaId));
            }
            newIngredient.setPizzas(pizzas);

            return new ResponseEntity<Ingredient>(ingredientService.update(newIngredient), HttpStatusCode.valueOf(200));
        }
    }

    @DeleteMapping
    public ResponseEntity<Ingredient> delete(Integer id) {
        if (ingredientService.findById(id).isEmpty()) {
            return new ResponseEntity<Ingredient>(HttpStatusCode.valueOf(404)); // 404 Not Found
        } else {
            ingredientService.deleteById(id);
            return new ResponseEntity<Ingredient>(HttpStatusCode.valueOf(200)); // 200 OK
        }
    }

}
