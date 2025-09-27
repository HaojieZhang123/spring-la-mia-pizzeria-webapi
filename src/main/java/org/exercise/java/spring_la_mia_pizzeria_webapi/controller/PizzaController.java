package org.exercise.java.spring_la_mia_pizzeria_webapi.controller;

import java.util.List;

import org.exercise.java.spring_la_mia_pizzeria_webapi.model.Pizza;
import org.exercise.java.spring_la_mia_pizzeria_webapi.repository.IngredientRepository;
import org.exercise.java.spring_la_mia_pizzeria_webapi.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping()
    public String index(Model model, @RequestParam(required = false) String name) {

        List<Pizza> pizzas;

        if (name != null) {
            pizzas = pizzaRepository.findByNameContainingIgnoreCase(name);
        } else {
            pizzas = pizzaRepository.findAll();
        }

        if (pizzas.isEmpty()) {
            model.addAttribute("message", "Non ci sono pizze disponibili.");
        } else {
            model.addAttribute("pizzas", pizzas);
        }

        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {

        Pizza pizza = pizzaRepository.findById(id).get();

        // .get() will throw an exception if the pizza is not found, not null.
        // will wait for future lessons to handle this.
        // if (pizza == null) {
        // model.addAttribute("message", "Pizza non trovata.");
        // } else {
        // model.addAttribute("pizza", pizza);
        // }

        model.addAttribute("pizza", pizza);

        return "pizzas/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "pizzas/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "pizzas/create";
        }

        pizzaRepository.save(formPizza);

        return "redirect:/pizzas";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {

        model.addAttribute("pizza", pizzaRepository.findById(id).get());
        model.addAttribute("ingredients", ingredientRepository.findAll());
        model.addAttribute("edit", true);

        return "pizzas/create-or-edit";

    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "pizzas/create-or-edit";
        }

        pizzaRepository.save(formPizza);

        return "redirect:/pizzas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        pizzaRepository.deleteById(id);

        return "redirect:/pizzas";
    }

}
