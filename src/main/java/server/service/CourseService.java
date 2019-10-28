package server.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import server.model.Field;
import server.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.persistance.repository.CourseRepository;
import server.util.FieldUtil;

import java.io.IOException;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    protected ObjectMapper mapper;

    public Iterable<Course> getAll() {
        return courseRepository.findByActive(false);
    }

    public Iterable<Course> courses() {
        return courseRepository.findByActive(true);
    }

    public Iterable<String> courseCategories() {
        Iterable<String> categories = courseRepository.findDistinctCategories();
        return categories;
    }

    public Iterable<Course> coursesByCategory(@PathVariable String category) {
        Iterable<Course> categories = courseRepository.findByCategory(category);
        return categories;
    }

    public Course courseDetails(@PathVariable Integer id) throws IOException {
        Course course = courseRepository.findById(id).orElse(new Course());
        List<Field> fields = mapper.readValue(course.getDetails(), new TypeReference<List<Field>>() {
        });
        course.setFields(fields);
        return course;
    }

    public Course saveCourse(@RequestBody Course t) throws Exception {
        String jsonStr = mapper.writeValueAsString(t);
        Course course = mapper.readValue(jsonStr, Course.class);
        course.setDetails(mapper.writeValueAsString(FieldUtil.filterFields(course.getFields())));
        courseRepository.save(course);
        return course;
    }

    public void deleteCourse(@PathVariable Integer id) {
        courseRepository.deleteById(id);
    }

}
