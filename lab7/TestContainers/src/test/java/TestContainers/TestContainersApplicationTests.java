package TestContainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import TestContainers.model.Student;
import TestContainers.repository.StudentRepository;


@Testcontainers
@SpringBootTest
class TestContainersApplicationTests {
	private Date date = new Date(System.currentTimeMillis());
	ArrayList<Student> expectedStudentsList = new ArrayList<>();

	@Container
	public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:12")
		.withUsername("duke")
		.withPassword("password")
		.withDatabaseName("test");

	@Autowired
	private StudentRepository studentRepository;

	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", container::getJdbcUrl);
		registry.add("spring.datasource.password", container::getPassword);
		registry.add("spring.datasource.username", container::getUsername);
	}

	@Order(1)
	@Test
	void saveStudent(){
		Student student1 = new Student("Matilde Almeida", date, 'B');
		Student student2 = new Student("Orlando Caetano", date, 'B');
		expectedStudentsList.add(student1);
		expectedStudentsList.add(student2);

		studentRepository.save(student1);
		studentRepository.save(student2);
		
		System.out.println("Saved students!");

		assertEquals(1, studentRepository.findByName("Matilde Almeida").size());
		assertEquals(1, studentRepository.findByName("Orlando Caetano").size());
	}

	@Order(2)
	@Test
	void getAllStudents(){
		List<Student> studentList = studentRepository.findAll();
		System.out.println("Students found!");
		assertEquals(3, studentList.size());
		studentList.stream().forEach(System.out::println);

	}

	@Order(2)
	@Test
	void getAllStudentsByClassLetter(){
		List<Student> studentList = studentRepository.findByClassLeter('C');
		System.out.println("Students With same birth date found!");
		assertEquals(0, expectedStudentsList.size());
		studentList.stream().forEach(System.out::println);
	}


}
