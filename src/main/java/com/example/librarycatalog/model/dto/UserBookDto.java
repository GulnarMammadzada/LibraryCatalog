package com.example.librarycatalog.model.dto;

import com.example.librarycatalog.model.enumeration.ReadingStatus;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserBookDto {

    private Long id;

    private Long userId;

    @NotNull(message = "Kitab ID-si boş ola bilməz")
    private Long bookId;

    @NotNull(message = "Oxuma statusu seçilməlidir")
    private ReadingStatus status;

    @Min(value = 1, message = "Reytinq ən azı 1 olmalıdır")
    @Max(value = 5, message = "Reytinq ən çox 5 olmalıdır")
    private Integer rating;

    private String notes;

    // Book və User məlumatları
    private String bookTitle;
    private String bookAuthor;
    private String userName;

    // Xüsusi konstruktor (əgər saxlanılacaqsa)
    public UserBookDto(Long userId, Long bookId, ReadingStatus status) {
        this.userId = userId;
        this.bookId = bookId;
        this.status = status;
    }
}
