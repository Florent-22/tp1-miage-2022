package com.acme.todolist;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.acme.todolist.domain.TodoItem;

@SpringBootTest
public class TodoItemTests {

	static final String LATE = "[LATE!] ";
	
	@Test
	public void test_isLate_expect_lateString() {
		DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());
		Instant result = Instant.from(formatter.parse("2022-01-01 00:00:00"));
		TodoItem test = new TodoItem("test1", result, "Test Todo Late");

		assertTrue(test.finalContent().contains(LATE));
	}

	@Test
	public void test_isLate_expect_notLateString() {
		Instant result = Instant.now();
		TodoItem test = new TodoItem("test2", result, "Test Todo Not Late");

		assertTrue(!test.finalContent().contains(LATE));
	}
	
}
