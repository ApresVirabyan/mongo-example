package com.example.mongo_example.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

    Optional<Student> findStudentByEmail(String email);

    @Query("{'email' : ?0, 'totalSpentInBooks' : {$gt:  ?1}}")
    List<Student> findByEmailAndTotalSpentInBooksGreaterThan(String email, BigDecimal totalSpentInBooks);
    
}
