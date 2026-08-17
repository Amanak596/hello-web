package hello_web.controller;

import org.springframework.web.bind.annotation.PutMapping;
import hello_web.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import hello_web.model.Student;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

     @Autowired
     private StudentRepository studentRepository;
    
    @GetMapping("/")
    public String home() {
        return "Hello World!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Spring Boot!";
    }

    @GetMapping("/about")
    public String about() {
        return "This is my first Spring Boot application.";
    }

    @GetMapping("/api/info")
    public Student info() {
        Student student = new Student(
            "Aman",
            "Spring Boot",
            "Beginner"
        );

        return student;
    }

    @GetMapping("/api/student/{name}")
    public String student(@PathVariable String name) {
        return "Hello " + name + "!";
    }

   @GetMapping("/api/search")
   public String search(@RequestParam String name) {
    return "Searching for: " + name;
    }


   @PostMapping("/api/student")
   public Student createStudent(@RequestBody Student student) {
    return studentRepository.save(student);
    }

   @PutMapping("/api/student")
   public Student updateStudent(@RequestBody Student student) {
    return student;
   }
   
  @GetMapping("/api/students")
   public java.util.List<Student> getStudents() {
    return studentRepository.findAll();
}

   @PutMapping("/api/student/{name}")
    public Student updateStudent(
        @PathVariable String name,
        @RequestBody Student student) {

    student.setName(name);
    return studentRepository.save(student);
   }


   @DeleteMapping("/api/student/{name}")
   public String deleteStudent(@PathVariable String name) {

    studentRepository.deleteById(name);
    return "Student " + name + " deleted";
  }

}

