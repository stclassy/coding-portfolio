package com.groceryListBuilder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Represents a single Recipe Object. Includes the recipe's id(unique identifier), name, user, and a recipe ingredient bridge table connection.
 *
 * @author Sydney St. Clair
 */

@Data
@AllArgsConstructor
@Entity(name = "Recipe")
@Table(name = "recipe")
public class Recipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "recipeId")
    private int recipeId;
    private LocalDateTime createDate;
    @Column(name = "name")
    private String recipeName;
    private LocalDateTime updateDate = null;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    private User user;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY)
    private Set<RecipeIngredient> ingredients = new HashSet<RecipeIngredient>();

    /**
     * Instantiates a new Recipe.
     */
    public Recipe() {
    }

    /**
     * Instantiates a new Recipe.
     *
     * @param createDate the Recipe creation date
     * @param recipeName the recipe name
     * @param user       the user that's attached to the recipe
     */
    public Recipe(LocalDateTime createDate, String recipeName, User user) {
        this.createDate = createDate;
        this.recipeName = recipeName;
        this.user = user;
    }
}
