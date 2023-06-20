package com.groceryListBuilder.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A class to represent a Grocery list.
 * There is a unique identifier, creation and edit dates, and a
 * connection to the User class.
 *
 * @author Sydney St. Clair
 */
@Data
@AllArgsConstructor
@Entity(name = "GroceryList")
@Table(name = "grocery_list")
public class GroceryList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @Column(name = "listId")
    private int listId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    private User user;

    /**
     * Instantiates a new Grocery list.
     */
    public GroceryList() {
    }

    /**
     * Instantiates a new Grocery list with a date and user passed in.
     *
     * @param createDate the creation date
     * @param user       the user
     */
    public GroceryList(LocalDateTime createDate, User user) {
        this.createDate = createDate;
        this.user = user;
    }
}
