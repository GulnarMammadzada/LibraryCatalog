package com.example.librarycatalog.service;

import com.example.librarycatalog.model.dto.BookDto;
import com.example.librarycatalog.model.entity.Book;
import com.example.librarycatalog.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class BookService {


    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDto createBook(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setGenre(bookDto.getGenre());
        book.setPages(bookDto.getPages());
        book.setPublicationYear(bookDto.getPublicationYear());
        book.setDescription(bookDto.getDescription());

        Book savedBook = bookRepository.save(book);
        return convertToDto(savedBook);
    }

    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            return convertToDto(book);
        }
        return null;
    }

    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookDto> bookDtos = new ArrayList<>();

        for (Book book : books) {
            bookDtos.add(convertToDto(book));
        }

        return bookDtos;
    }

    public List<BookDto> searchBooksByTitle(String title) {
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase(title);
        List<BookDto> bookDtos = new ArrayList<>();

        for (Book book : books) {
            bookDtos.add(convertToDto(book));
        }

        return bookDtos;
    }

    public List<BookDto> searchBooksByAuthor(String author) {
        List<Book> books = bookRepository.findByAuthorContainingIgnoreCase(author);
        List<BookDto> bookDtos = new ArrayList<>();

        for (Book book : books) {
            bookDtos.add(convertToDto(book));
        }

        return bookDtos;
    }

    public List<BookDto> getBooksByGenre(String genre) {
        List<Book> books = bookRepository.findByGenre(genre);
        List<BookDto> bookDtos = new ArrayList<>();

        for (Book book : books) {
            bookDtos.add(convertToDto(book));
        }

        return bookDtos;
    }

    public BookDto updateBook(Long id, BookDto bookDto) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            book.setTitle(bookDto.getTitle());
            book.setAuthor(bookDto.getAuthor());
            book.setGenre(bookDto.getGenre());
            book.setPages(bookDto.getPages());
            book.setPublicationYear(bookDto.getPublicationYear());
            book.setDescription(bookDto.getDescription());

            Book updatedBook = bookRepository.save(book);
            return convertToDto(updatedBook);
        }
        return null;
    }

    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private BookDto convertToDto(Book book) {
        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setGenre(book.getGenre());
        dto.setPages(book.getPages());
        dto.setPublicationYear(book.getPublicationYear());
        dto.setDescription(book.getDescription());
        return dto;
    }
}
