package com.example.librarycatalog.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserWithBooksDto {
    private Long userId;
    private String userName;
    private List<UserBookDto> books;
}

