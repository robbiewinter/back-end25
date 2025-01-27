package lists.backend.web;

import lists.backend.domain.Student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.List;


@Controller
public class StudentController {
    @GetMapping("/hello")
    public String getStudent(Model model) {
        List<Student> students = List.of(
                new Student("Mike", "Smith"),
                new Student("Pekka", "Lehto"),
                new Student("Peter", "Peterson")
        );

        model.addAttribute("message", "Welcome to the Haaga-Helia");
        model.addAttribute("students", students);

        return "studentlist";
    }
}
