package com.javaproject.backend;

import org.hibernate.annotations.TimeZoneStorage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.javaproject.backend.repository.RepoCategory;
import org.springframework.test.context.ActiveProfiles;
import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class BackendApplicationTests {

	@Autowired
	DataSource ds;

	@Test
	void printDataSource() {
		System.out.println(ds);
	}
}
