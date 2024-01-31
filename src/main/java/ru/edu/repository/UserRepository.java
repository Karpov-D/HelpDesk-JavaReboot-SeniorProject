package ru.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.edu.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //@Query("select u.login from User u join Book b where b.id = a.id")
    //List<User> getAuthorsByBookId(@Param("id") Long id);
}
