package com.library.assignment.book;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private LocalDate releaseDate;
    @Column
    private boolean borrowed = false;

    @Column
    private LocalDate borrowedDate;

    @Column
    private Long loanerId;

}
