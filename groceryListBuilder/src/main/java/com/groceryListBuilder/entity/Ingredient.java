package com.groceryListBuilder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * A class to represent an Ingredient. Hold a unique identifier, the ingredient name, and a connection to the RecipeIngredient and
 * GroceryIngredient entities.
 *
 * @author Sydney St Clair
 */
@Data
@AllArgsConstructor
@Entity(name = "Ingredient")
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @Column(name = "ingredientId")
    private int ingredientId;
    private String ingredientName;

    @OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY)
    private Set<RecipeIngredient> recipes = new HashSet<RecipeIngredient>();

    // @OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY)
    // private Set<GroceryIngredient> groceryList = new HashSet<GroceryIngredient>();

    /**
     * Instantiates a new Ingredients.
     */
    public Ingredient() {
    }

    /**
     * Instantiates a new Ingredients.
     *
     * @param ingredientName the ingredient name
     */
    public Ingredient(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}