package com.groceryListBuilder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A class to represent a recipe ingredient connection. This class is a bridge between the Recipe and Ingredient classes.
 * One RecipeIngredient holds a unique identifier, a reference to the corresponding recipe, a reference to the corresponding ingredient,
 * the amount of the ingredient, and the measurement unit used.
 *
 * @author Sydney St Clair
 */
@Data
@AllArgsConstructor
@Entity(name = "RecipeIngredient")
@Table(name = "recipe_ingredients")
public class RecipeIngredient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @ManyToOne
    @JoinColumn(name = "recipeId", referencedColumnName = "recipeId")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredientId", referencedColumnName = "ingredientId")
    private Ingredient ingredient;
    private String measurementUnit;
    private String quantity;

    /**
     * Instantiates a new Recipe ingredients.
     */
    public RecipeIngredient() {
    }

    /**
     * Instantiates a new Recipe ingredients.
     *
     * @param recipe the recipe being looked at.
     * @param ingredient the ingredient being looked at.
     */
    public RecipeIngredient(Recipe recipe, Ingredient ingredient) {
        this.recipe = recipe;
        this.ingredient = ingredient;
    }
}

