package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import server.model.Course;
import server.service.CourseService;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
public class CourseRestController {

    @Autowired
    CourseService courseService;

    @RequestMapping("/coursecategories")
    public List<String> coursecategories(Model model) {
        List<String> categories = courseService.courseCategories();
        return categories;
    }

    @RequestMapping("/coursecategory/{category}")
    public List<Course> coursesByCategory(@PathVariable String category) {
        List<Course> categories = courseService.coursesByCategory(category);
        categories.forEach(course -> {
            course.setDetails(null);
        });
        return categories;
    }

    @RequestMapping(value = "/coursedetails/{id}", method = RequestMethod.GET)
    public Course courseDetails(@PathVariable Integer id) throws IOException {
        Course course = courseService.courseDetails(id);
        return course;
    }

}
