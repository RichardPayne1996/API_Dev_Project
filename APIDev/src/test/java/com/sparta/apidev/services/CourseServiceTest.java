package com.sparta.apidev.services;

import com.sparta.apidev.dtos.CourseDTO;
import com.sparta.apidev.dtos.CourseMapper;
import com.sparta.apidev.entities.Course;
import com.sparta.apidev.repositories.CourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

class CourseServiceTest {

    private final CourseRepository mockRepository =
            Mockito.mock(CourseRepository.class);

    private final CourseMapper mockMapper =
            Mockito.mock(CourseMapper.class);

    private final CourseService sut =
            new CourseService(mockRepository, mockMapper);

    @Test
    @DisplayName("Construct Course Service")
    void constructServiceTest() {

        Assertions.assertInstanceOf(CourseService.class, sut);
    }

    @Test
    @DisplayName("Get All Courses")
    void getAllCoursesTest() {

        // Arrange
        Course course1 = new Course();
        course1.setId(1);
        course1.setCourseName("Java");

        Course course2 = new Course();
        course2.setId(2);
        course2.setCourseName("Spring");

        List<Course> courses = List.of(course1, course2);

        CourseDTO dto1 = new CourseDTO();
        dto1.setCourseName("Java");

        CourseDTO dto2 = new CourseDTO();
        dto2.setCourseName("Spring");

        Mockito.when(mockRepository.findAll())
                .thenReturn(courses);

        Mockito.when(mockMapper.toDTO(course1))
                .thenReturn(dto1);

        Mockito.when(mockMapper.toDTO(course2))
                .thenReturn(dto2);

        // Act
        List<CourseDTO> result = sut.getAllCourses();

        // Assert
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Java", result.get(0).getCourseName());
        Assertions.assertEquals("Spring", result.get(1).getCourseName());
    }

    @Test
    @DisplayName("Get Course By ID - Success")
    void getCourseByIdSuccessTest() {

        // Arrange
        Course course = new Course();
        course.setId(1);
        course.setCourseName("Java");

        CourseDTO dto = new CourseDTO();
        dto.setCourseName("Java");

        Mockito.when(mockRepository.findById(1))
                .thenReturn(Optional.of(course));

        Mockito.when(mockMapper.toDTO(course))
                .thenReturn(dto);

        // Act
        CourseDTO result = sut.getCourseById(1);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Java", result.getCourseName());
    }

    @Test
    @DisplayName("Get Course By ID - Not Found")
    void getCourseByIdFailureTest() {

        // Arrange
        Mockito.when(mockRepository.findById(1))
                .thenReturn(Optional.empty());

        // Act
        CourseDTO result = sut.getCourseById(1);

        // Assert
        Assertions.assertNull(result);
    }

    @Test
    @DisplayName("Save Course")
    void saveCourseTest() {

        // Arrange
        CourseDTO dto = new CourseDTO();
        dto.setCourseName("Java");

        Course course = new Course();
        course.setCourseName("Java");

        Course saved = new Course();
        saved.setCourseName("Java");

        CourseDTO savedDTO = new CourseDTO();
        savedDTO.setCourseName("Java");

        Mockito.when(mockMapper.toEntity(dto))
                .thenReturn(course);

        Mockito.when(mockRepository.save(course))
                .thenReturn(saved);

        Mockito.when(mockMapper.toDTO(saved))
                .thenReturn(savedDTO);

        // Act
        CourseDTO result = sut.saveCourse(dto);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Java", result.getCourseName());
    }
    @Test
    @DisplayName("Delete Course - Success")
    void deleteCourseSuccessTest() {

        // Arrange
        Mockito.when(mockRepository.existsById(1))
                .thenReturn(true);
        // Act
        boolean result = sut.deleteCourse(1);
        // Assert
        Assertions.assertTrue(result);

        Mockito.verify(mockRepository, Mockito.times(1))
                .deleteById(1);
    }

    @Test
    @DisplayName("Delete Course - Not Found")
    void deleteCourseFailureTest() {

        // Arrange
        Mockito.when(mockRepository.existsById(1))
                .thenReturn(false);

        // Act
        boolean result = sut.deleteCourse(1);

        // Assert
        Assertions.assertFalse(result);

        Mockito.verify(mockRepository, Mockito.never())
                .deleteById(1);
    }

    @Test
    @DisplayName("Update Course - Success")
    void updateCourseSuccessTest() {

        // Arrange
        CourseDTO dto = new CourseDTO();
        dto.setCourseName("Updated Java");

        Course course = new Course();
        course.setId(1);
        course.setCourseName("Java");

        Course updated = new Course();
        updated.setCourseName("Updated Java");

        CourseDTO updatedDTO = new CourseDTO();
        updatedDTO.setCourseName("Updated Java");

        Mockito.when(mockRepository.findById(1))
                .thenReturn(Optional.of(course));

        Mockito.when(mockRepository.save(course))
                .thenReturn(updated);

        Mockito.when(mockMapper.toDTO(updated))
                .thenReturn(updatedDTO);

        // Act
        CourseDTO result = sut.updateCourse(1, dto);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Updated Java", result.getCourseName());
    }

    @Test
    @DisplayName("Update Course - Not Found")
    void updateCourseFailureTest() {

        // Arrange
        CourseDTO dto = new CourseDTO();
        dto.setCourseName("Java");

        Mockito.when(mockRepository.findById(1))
                .thenReturn(Optional.empty());

        // Act + Assert
        Assertions.assertThrows(
                RuntimeException.class,
                () -> sut.updateCourse(1, dto)
        );
    }
}