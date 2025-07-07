package com.example.librarycatalog.service;

import com.example.librarycatalog.model.dto.UserBookDto;
import com.example.librarycatalog.model.dto.UserWithBooksDto;
import com.example.librarycatalog.model.entity.Book;
import com.example.librarycatalog.model.entity.User;
import com.example.librarycatalog.model.entity.UserBook;
import com.example.librarycatalog.model.enumeration.ReadingStatus;
import com.example.librarycatalog.repository.BookRepository;
import com.example.librarycatalog.repository.UserBookRepository;
import com.example.librarycatalog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class UserBookService {


    private final UserBookRepository userBookRepository;


    private final UserRepository userRepository;


    private final BookRepository bookRepository;

    public UserBookService(UserBookRepository userBookRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.userBookRepository = userBookRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public UserBookDto addBookToUser(UserBookDto userBookDto) {
        User user = userRepository.findById(userBookDto.getUserId()).orElse(null);
        Book book = bookRepository.findById(userBookDto.getBookId()).orElse(null);

        if (user != null && book != null) {
            UserBook userBook = new UserBook();
            userBook.setUser(user);
            userBook.setBook(book);
            userBook.setStatus(userBookDto.getStatus());
            userBook.setRating(userBookDto.getRating());
            userBook.setNotes(userBookDto.getNotes());

            UserBook savedUserBook = userBookRepository.save(userBook);
            return convertToDto(savedUserBook);
        }
        return null;
    }

    public List<UserBookDto> getUserBooks(Long userId) {
        List<UserBook> userBooks = userBookRepository.findByUserId(userId);
        List<UserBookDto> userBookDtos = new ArrayList<>();

        for (UserBook userBook : userBooks) {
            userBookDtos.add(convertToDto(userBook));
        }

        return userBookDtos;
    }

    public List<UserBookDto> getUserBooksByStatus(Long userId, ReadingStatus status) {
        List<UserBook> userBooks = userBookRepository.findByUserIdAndStatus(userId, status);
        List<UserBookDto> userBookDtos = new ArrayList<>();

        for (UserBook userBook : userBooks) {
            userBookDtos.add(convertToDto(userBook));
        }

        return userBookDtos;
    }

    public List<UserWithBooksDto> getAllUsersWithBooks() {
        List<User> users = userRepository.findAll();
        List<UserWithBooksDto> result = new ArrayList<>();

        for (User user : users) {
            List<UserBook> userBooks = userBookRepository.findByUserId(user.getId());
            List<UserBookDto> bookDtos = new ArrayList<>();

            for (UserBook userBook : userBooks) {
                UserBookDto dto = new UserBookDto();
                dto.setId(userBook.getId());
                dto.setUserId(user.getId());
                dto.setBookId(userBook.getBook().getId());
                dto.setStatus(userBook.getStatus());
                dto.setRating(userBook.getRating());
                dto.setNotes(userBook.getNotes());

                dto.setBookTitle(userBook.getBook().getTitle());
                dto.setBookAuthor(userBook.getBook().getAuthor());
                dto.setUserName(user.getFirstName() + " " + user.getLastName());

                bookDtos.add(dto);
            }

            UserWithBooksDto userDto = new UserWithBooksDto();
            userDto.setUserId(user.getId());
            userDto.setUserName(user.getFirstName() + " " + user.getLastName());
            userDto.setBooks(bookDtos);

            result.add(userDto);
        }

        return result;
    }

    public UserBookDto getUserBookById(Long id) {
        UserBook userBook = userBookRepository.findById(id).orElse(null);
        if (userBook != null) {
            return convertToDto(userBook);
        }
        return null;
    }

    public UserBookDto updateUserBook(Long id, UserBookDto userBookDto) {
        UserBook userBook = userBookRepository.findById(id).orElse(null);
        if (userBook != null) {
            userBook.setStatus(userBookDto.getStatus());
            userBook.setRating(userBookDto.getRating());
            userBook.setNotes(userBookDto.getNotes());

            UserBook updatedUserBook = userBookRepository.save(userBook);
            return convertToDto(updatedUserBook);
        }
        return null;
    }

    public boolean removeBookFromUser(Long id) {
        if (userBookRepository.existsById(id)) {
            userBookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private UserBookDto convertToDto(UserBook userBook) {
        UserBookDto dto = new UserBookDto();
        dto.setId(userBook.getId());
        dto.setUserId(userBook.getUser().getId());
        dto.setBookId(userBook.getBook().getId());
        dto.setStatus(userBook.getStatus());
        dto.setRating(userBook.getRating());
        dto.setNotes(userBook.getNotes());

        dto.setBookTitle(userBook.getBook().getTitle());
        dto.setBookAuthor(userBook.getBook().getAuthor());
        dto.setUserName(userBook.getUser().getFirstName() + " " + userBook.getUser().getLastName());

        return dto;
    }
}