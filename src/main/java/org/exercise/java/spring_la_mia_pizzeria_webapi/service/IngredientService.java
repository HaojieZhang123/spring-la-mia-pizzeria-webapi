package org.exercise.java.spring_la_mia_pizzeria_webapi.service;

import java.util.List;
import java.util.Optional;

import org.exercise.java.spring_la_mia_pizzeria_webapi.model.Ingredient;
import org.exercise.java.spring_la_mia_pizzeria_webapi.model.Pizza;
import org.exercise.java.spring_la_mia_pizzeria_webapi.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private PizzaService pizzaService;

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    public Optional<Ingredient> findById(Integer id) {
        return ingredientRepository.findById(id);
    }

    public Ingredient getById(Integer id) {
        return ingredientRepository.findById(id).get();
    }

    public Ingredient store(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient update(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public void deleteById(Integer id) {
        // delete associated ingredients in pizzas
        Ingredient ingredientToDelete = ingredientRepository.findById(id).get();
        for (Pizza pizza : ingredientToDelete.getPizzas()) {
            pizza.getIngredients().remove(ingredientToDelete);
        }
        ingredientRepository.deleteById(id);

    }

    public void delete(Ingredient ingredient) {
        // delete associated ingredients in pizzas
        for (Pizza pizza : ingredient.getPizzas()) {
            pizza.getIngredients().remove(ingredient);
        }
        ingredientRepository.delete(ingredient);
    }
}
