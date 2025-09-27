package org.exercise.java.spring_la_mia_pizzeria_webapi.controller;

import org.exercise.java.spring_la_mia_pizzeria_webapi.model.Offer;
import org.exercise.java.spring_la_mia_pizzeria_webapi.repository.OfferRepository;
import org.exercise.java.spring_la_mia_pizzeria_webapi.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/offers")
public class OfferController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private OfferRepository offerRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("offers", offerRepository.findAll());
        return "offers/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("offer", offerRepository.findById(id).get());
        return "offers/show";
    }

    @GetMapping("/create/{id}")
    public String create(@PathVariable("id") Integer id, Model model) {
        Offer newOffer = new Offer();
        newOffer.setPizza(pizzaRepository.findById(id).get());

        model.addAttribute("offer", newOffer);

        return "offers/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("offer") Offer formOffer,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "offers/create-or-edit";
        } else {
            // formOffer.setPizza(pizzaRepository.findById(id).get());
            offerRepository.save(formOffer);
            return "redirect:/pizzas/" + formOffer.getPizza().getId();
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("offer", offerRepository.findById(id).get());
        model.addAttribute("edit", true);

        return "offers/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("offer") Offer formOffer,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "offers/create-or-edit";
        } else {
            offerRepository.save(formOffer);
            return "redirect:/pizzas/" + formOffer.getPizza().getId();
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Offer offerToDelete = offerRepository.findById(id).get();
        offerRepository.delete(offerToDelete);
        return "redirect:/pizzas/" + offerToDelete.getPizza().getId();
    }

}
