package com.groceryListBuilder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * An object containing ingredients for a recipe uploaded by a link.
 *
 * @author Sydney St. Clair
 */
@Data
@AllArgsConstructor
public class LinkRecipe {
    private String name;
    private List<String> ingredients;
    private String url;

    public LinkRecipe() {
        this.ingredients = new ArrayList<>();
    }
}
