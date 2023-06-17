package com.groceryListBuilder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
    /**
     * A class to represent a User. Stores a single user's id, username, create date and update date.
     *
     * @author Sydney St. Clair
     */
    @Data
    @AllArgsConstructor
    @Entity(name = "User")
    @Table(name = "users")
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
        @GenericGenerator(name = "native", strategy = "native")
        @Column(name = "userId")
        private int userId;
        private String userName;
        private LocalDateTime updateDate;
        private LocalDateTime createDate;

        /**
         * Instantiates a new User.
         */
        public User() {
        }

        /**
         * Instantiates a new User.
         *
         * @param userName   the user's username
         * @param createDate the creation date of the account
         */
        public User(String userName, LocalDateTime createDate) {
            this.userName = userName;
            this.createDate = createDate;
        }
    }
