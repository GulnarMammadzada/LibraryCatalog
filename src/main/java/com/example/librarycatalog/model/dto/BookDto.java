package com.example.librarycatalog.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDto {

    private Long id;

    @NotBlank(message = "Kitab adı boş ola bilməz")
    private String title;

    @NotBlank(message = "Müəllif adı boş ola bilməz")
    private String author;

    private String genre;

    @Min(value = 1, message = "Səhifə sayı 1-dən az ola bilməz")
    private Integer pages;

    @Min(value = 1000, message = "Nəşr ili 1000-dən az ola bilməz")
    @Max(value = 2030, message = "Nəşr ili 2030-dan çox ola bilməz")
    private Integer publicationYear;

    private String description;

    // İstəyə bağlı: xüsusi konstruktor saxlanılıb
    public BookDto(String title, String author) {
        this.title = title;
        this.author = author;
    }
}
