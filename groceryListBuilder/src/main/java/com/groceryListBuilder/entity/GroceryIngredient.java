package com.groceryListBuilder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The dao for Grocery list ingredients.
 *
 * @author Sydney St Clair
 */
@Data
@AllArgsConstructor
@Entity(name = "GroceryIngredient")
@Table(name = "grocery_ingredients")
public class GroceryIngredient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @ManyToOne
    @JoinColumn(name = "listId", referencedColumnName = "listId")
    private GroceryList groceryList;

    @ManyToOne
    @JoinColumn(name = "ingredientId", referencedColumnName = "ingredientId")
    private Ingredient ingredient;
    private String quantity;
    private String measurementUnit;

    /**
     * Instantiates a new Grocery ingredients.
     */
    public GroceryIngredient() {
    }

    /**
     * Instantiates a new Grocery ingredients.
     *
     * @param groceryList     the grocery list
     * @param ingredient      the ingredient
     * @param quantity        the quantity
     * @param measurementUnit the measurement unit
     */
    public GroceryIngredient(GroceryList groceryList, Ingredient ingredient, String quantity, String measurementUnit) {
        this.groceryList = groceryList;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.measurementUnit = measurementUnit;
    }
}