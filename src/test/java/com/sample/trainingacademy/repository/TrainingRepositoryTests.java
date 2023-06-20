package com.sample.trainingacademy.repository;

import com.sample.trainingacademy.common.Store;
import com.sample.trainingacademy.entity.Training;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@ActiveProfiles("test")
@DataJpaTest
@TestPropertySource(locations="classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TrainingRepositoryTests {
	@Autowired
	private TrainingRepository trainingRepository;

	@Test
	@Order(1)
	public void whenSaveAndRetreiveTrainingById_thenOK() {
		Training savedTraining = trainingRepository.save(Training.builder()
				.id(1L)
				.name("TestTraining1")
				.description("TestDescription1")
				.lecturer("TestLecturer1")
				.price(BigDecimal.ONE)
				.startDate(Date.valueOf("2023-01-01"))
				.endDate(Date.valueOf("2023-01-30"))
				.bookedCustomer(Store.CUSTOMERS).build());
		Optional<Training> foundEntity = trainingRepository.findById(1L);
		assertTrue(foundEntity.isPresent());
		assertTrue(savedTraining.getId() == foundEntity.get().getId());
	}

	@Test
	@Order(2)
	public void whenSaveAndRetreiveTrainingByName_thenOK() {
		Training savedTraining = trainingRepository.save(Training.builder()
				.id(2L)
				.name("TestTraining2")
				.description("TestDescription2")
				.lecturer("TestLecturer2")
				.price(BigDecimal.ONE)
				.startDate(Date.valueOf("2023-01-15"))
				.endDate(Date.valueOf("2023-02-14"))
				.bookedCustomer(Store.CUSTOMERS).build());
		List<Training> foundEntity = trainingRepository.findAllByName("TestTraining2");
		assertNotNull(foundEntity);
		assertFalse(foundEntity.isEmpty());
		assertEquals(1, foundEntity.size());
		assertEquals(savedTraining.getName(), foundEntity.get(0).getName());
	}

	@Test
	@Order(3)
	public void whenSaveAndRetreiveTrainingByNameAndByExactDates_thenOK() {
		Training savedTraining = trainingRepository.save(Training.builder()
				.id(3L)
				.name("TestTraining3")
				.description("TestDescription3")
				.lecturer("TestLecturer3")
				.price(BigDecimal.ONE)
				.startDate(Date.valueOf("2023-02-01"))
				.endDate(Date.valueOf("2023-02-28"))
				.bookedCustomer(Store.CUSTOMERS).build());
		List<Training> foundEntity = trainingRepository.findAllByNameWithExactTime("TestTraining3",
				Date.valueOf("2023-02-01"),
				Date.valueOf("2023-02-28"));
		assertNotNull(foundEntity);
		assertFalse(foundEntity.isEmpty());
		assertEquals(1, foundEntity.size());
		assertEquals("TestTraining3", foundEntity.get(0).getName());
	}

	@Test
	@Order(4)
	public void whenRetreiveFalseTrainingByNameAndByExactDates_thenNotOK() {
		List<Training> notFoundEntity = trainingRepository.findAllByNameWithExactTime("TestTraining3",
				Date.valueOf("2023-11-05"),
				Date.valueOf("2023-11-10"));

		assertNotNull(notFoundEntity);
		assertTrue(notFoundEntity.isEmpty());
		assertEquals(0, notFoundEntity.size());
	}

	@Test
	@Order(5)
	public void whenSaveMultipleTrainingsAndRetreiveTrainingByDateRangeWithinOneTraining_thenOK() {
		List<Training> savedTrainings = trainingRepository.saveAll(List.of(
				Training.builder()
						.id(1L)
						.name("TestTraining1")
						.description("TestDescription1")
						.lecturer("TestLecturer1")
						.price(BigDecimal.ONE)
						.startDate(Date.valueOf("2023-01-01"))
						.endDate(Date.valueOf("2023-01-30"))
						.bookedCustomer(Store.CUSTOMERS).build(),
				Training.builder()
						.id(2L)
						.name("TestTraining2")
						.description("TestDescription2")
						.lecturer("TestLecturer2")
						.price(BigDecimal.ONE)
						.startDate(Date.valueOf("2023-01-15"))
						.endDate(Date.valueOf("2023-02-14"))
						.bookedCustomer(Store.CUSTOMERS).build(),
				Training.builder()
						.id(3L)
						.name("TestTraining3")
						.description("TestDescription3")
						.lecturer("TestLecturer3")
						.price(BigDecimal.ONE)
						.startDate(Date.valueOf("2023-02-01"))
						.endDate(Date.valueOf("2023-02-28"))
						.bookedCustomer(Store.CUSTOMERS).build()));
		List<Training> foundEntities = trainingRepository.findAllBetweenTimeframe(
				Date.valueOf("2023-01-01"),
				Date.valueOf("2023-01-10"));
		assertNotNull(foundEntities);
		assertFalse(foundEntities.isEmpty());
		assertEquals(1, foundEntities.size());
	}

	@Test
	@Order(6)
	public void whenSaveMultipleTrainingsAndRetreiveTrainingByDateRangeWithinTwoTrainings_thenOK() {
		List<Training> savedTrainings = trainingRepository.saveAll(List.of(
				Training.builder()
						.id(1L)
						.name("TestTraining1")
						.description("TestDescription1")
						.lecturer("TestLecturer1")
						.price(BigDecimal.ONE)
						.startDate(Date.valueOf("2023-01-01"))
						.endDate(Date.valueOf("2023-01-30"))
						.bookedCustomer(Store.CUSTOMERS).build(),
				Training.builder()
						.id(2L)
						.name("TestTraining2")
						.description("TestDescription2")
						.lecturer("TestLecturer2")
						.price(BigDecimal.ONE)
						.startDate(Date.valueOf("2023-01-15"))
						.endDate(Date.valueOf("2023-02-14"))
						.bookedCustomer(Store.CUSTOMERS).build(),
				Training.builder()
						.id(3L)
						.name("TestTraining3")
						.description("TestDescription3")
						.lecturer("TestLecturer3")
						.price(BigDecimal.ONE)
						.startDate(Date.valueOf("2023-02-01"))
						.endDate(Date.valueOf("2023-02-28"))
						.bookedCustomer(Store.CUSTOMERS).build()));
		List<Training> foundEntities = trainingRepository.findAllBetweenTimeframe(
				Date.valueOf("2023-01-05"),
				Date.valueOf("2023-01-20"));
		assertNotNull(foundEntities);
		assertFalse(foundEntities.isEmpty());
		assertEquals(2, foundEntities.size());
	}
}