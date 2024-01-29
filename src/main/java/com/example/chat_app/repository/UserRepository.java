package com.example.chat_app.repository;
import com.example.chat_app.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByNameStartingWithIgnoreCase(String prefix);
}
