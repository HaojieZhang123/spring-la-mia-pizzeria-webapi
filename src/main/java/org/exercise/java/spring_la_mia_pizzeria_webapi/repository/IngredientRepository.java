package org.exercise.java.spring_la_mia_pizzeria_webapi.repository;

import org.exercise.java.spring_la_mia_pizzeria_webapi.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

}
