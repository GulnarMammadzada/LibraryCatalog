package com.example.librarycatalog.repository;


import com.example.librarycatalog.model.entity.UserBook;
import com.example.librarycatalog.model.enumeration.ReadingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserBookRepository extends JpaRepository<UserBook, Long> {
    List<UserBook> findByUserId(Long userId);
    List<UserBook> findByUserIdAndStatus(Long userId, ReadingStatus status);
}