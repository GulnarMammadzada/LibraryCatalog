package com.example.librarycatalog.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Kitab adı boş ola bilməz")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Müəllif adı boş ola bilməz")
    @Column(nullable = false)
    private String author;

    private String genre;

    @Min(value = 1, message = "Səhifə sayı 1-dən az ola bilməz")
    private Integer pages;

    @Min(value = 1000, message = "Nəşr ili 1000-dən az ola bilməz")
    @Max(value = 2030, message = "Nəşr ili 2030-dan çox ola bilməz")
    @Column(name = "publication_year")
    private Integer publicationYear;

    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<UserBook> userBooks;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
