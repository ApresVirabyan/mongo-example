package com.example.mongo_example;

import com.example.mongo_example.mongo.Address;
import com.example.mongo_example.mongo.Gender;
import com.example.mongo_example.mongo.Student;
import com.example.mongo_example.mongo.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(MongoExampleApplication.class)
public class MongoExampleApplication {


	public static void main(String[] args) {
		SpringApplication.run(MongoExampleApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository studentRepository, MongoTemplate mongoTemplate){
		return args -> {
			Address address = new Address("England", "London", "Ne9");
			Student student = new Student(
					"Jamila",
					"Ahmed",
					"apres1987@.com",
					Gender.FEMALE,
					address,
					List.of("Computer Science"),
					BigDecimal.TEN,
					LocalDateTime.now());
			String email = student.getEmail();

			//usingMongoTemplateAndQuery(studentRepository, mongoTemplate, student, email);
			studentRepository.findStudentByEmail(email).ifPresentOrElse(s -> {
				System.out.println(student + "  already exist");
			}, () -> {
				System.out.println("Inserting student " + student);
				studentRepository.insert(student);
			});
		};
	}

	private static void usingMongoTemplateAndQuery(StudentRepository studentRepository, MongoTemplate mongoTemplate, Student student, String email) {
		Query query = new Query();

		query.addCriteria(Criteria.where("email").is(email));
		List<Student> students = mongoTemplate.find(query, Student.class);
		if(students.size() > 1){
			throw new IllegalStateException("found many students " + email);
		}

		if(students.isEmpty()){
			studentRepository.insert(student);
		} else{
			System.out.println(student + "  already exist");
		}
	}

}
