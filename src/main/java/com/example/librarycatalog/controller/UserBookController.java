package com.example.librarycatalog.controller;

import com.example.librarycatalog.model.dto.UserBookDto;
import com.example.librarycatalog.model.dto.UserWithBooksDto;
import com.example.librarycatalog.model.enumeration.ReadingStatus;
import com.example.librarycatalog.service.UserBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/userbooks")
@CrossOrigin(origins = "http://localhost:8080")
public class UserBookController {


    private final UserBookService userBookService;

    public UserBookController(UserBookService userBookService) {
        this.userBookService = userBookService;
    }

    @PostMapping
    public ResponseEntity<UserBookDto> addBookToUser(@Valid @RequestBody UserBookDto userBookDto) {
        UserBookDto createdUserBook = userBookService.addBookToUser(userBookDto);
        return createdUserBook != null ? ResponseEntity.ok(createdUserBook) : ResponseEntity.badRequest().build();
    }


    @GetMapping
    public ResponseEntity<List<UserBookDto>> getUserBooks(@PathVariable Long userId,
                                                          @RequestParam(required = false) ReadingStatus status) {
        List<UserBookDto> userBooks;

        if (status != null) {
            userBooks = userBookService.getUserBooksByStatus(userId, status);
        } else {
            userBooks = userBookService.getUserBooks(userId);
        }

        return ResponseEntity.ok(userBooks);
    }

    @GetMapping("/{userBookId}")
    public ResponseEntity<UserBookDto> getUserBookById(@PathVariable Long userBookId) {
        UserBookDto userBook = userBookService.getUserBookById(userBookId);
        if (userBook != null) {
            return ResponseEntity.ok(userBook);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{userBookId}")
    public ResponseEntity<UserBookDto> updateUserBook(@PathVariable Long userBookId, @Valid @RequestBody UserBookDto userBookDto) {
        UserBookDto updatedUserBook = userBookService.updateUserBook(userBookId, userBookDto);
        if (updatedUserBook != null) {
            return ResponseEntity.ok(updatedUserBook);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userBookId}")
    public ResponseEntity<Void> removeBookFromUser(@PathVariable Long userBookId) {
        boolean deleted = userBookService.removeBookFromUser(userBookId);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/with-users")
    public ResponseEntity<List<UserWithBooksDto>> getUsersWithBooks() {
        return ResponseEntity.ok(userBookService.getAllUsersWithBooks());
    }

}