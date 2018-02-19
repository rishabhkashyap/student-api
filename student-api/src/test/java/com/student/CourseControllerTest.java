package com.student;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.controller.CourseController;
import com.student.controller.StudentController;
import com.student.entity.Course;
import com.student.service.CourseService;

@RunWith(SpringRunner.class)
@SpringBootTest

public class CourseControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Mock
	CourseService courseService;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetCourseData() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/course/101")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("courseId").value(101))
				.andExpect(jsonPath("courseName").value("Defend against dark arts"))
				.andExpect(jsonPath("courseDescription").value("Dark arts defence"))
				.andExpect(jsonPath("instructor").value("Professor Dumbeldor"));

	}

	@Test
	public void testGetDataWithNoData() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/course/234")).andExpect(status().is4xxClientError())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("apierror.httpStatus", is("NOT_FOUND")))
				.andExpect(jsonPath("apierror.message").value("Resource with id  234 not found"))
				.andExpect(jsonPath("apierror.debugMessage").value("Resource with id  234 not found"));

	}

	// Test case for delete operation

	@Test
	public void testDeleteCourse() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/course/111")).andExpect(status().isOk());

	}

	@Test
	public void testDeleteDataWithNoDataAvaialble() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/course/111")).andExpect(status().is4xxClientError())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("apierror.httpStatus", is("NOT_FOUND")))
				.andExpect(jsonPath("apierror.message").value("Resource with id  111 not found"))
				.andExpect(jsonPath("apierror.debugMessage").value("Resource with id  111 not found"));

	}

	// Test cases for post operation
	@Test
	public void testCreateCourse() throws Exception {
		Course course = new Course("JS101", "JS for beginers", "Mike Shinoda");
		mockMvc.perform(MockMvcRequestBuilders.post("/course/create").contentType("application/json;charset=UTF-8")
				.content(asJsonString(course)).accept("application/json;charset=UTF-8"))
				.andExpect(status().isCreated());

	}

	@Test
	public void testCourseAlreadyExist() throws Exception {
		Course course = new Course("JS101", "JS for beginers", "Mike Shinoda");
		mockMvc.perform(MockMvcRequestBuilders.post("/course/create").contentType("application/json;charset=UTF-8")
				.content(asJsonString(course)).accept("application/json;charset=UTF-8"))
				.andExpect(status().isConflict()).andExpect(jsonPath("apierror.httpStatus", is("CONFLICT")))
				.andExpect(jsonPath("apierror.message").value("Course already exist.Try adding new course"))
				.andExpect(jsonPath("apierror.debugMessage").value("Course already exist.Try adding new course"))

		;

	}

	public String asJsonString(Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
