package com.dailycodework.sbqrcdoedemo.model.student;

import com.dailycodework.sbqrcdoedemo.utils.QRCodeGenerator;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() throws IOException, WriterException {
        List<Student> students = studentService.getStudents();
        if (students.size() != 0){
            for (Student student : students){
                QRCodeGenerator.generateQRCode(student);
            }
        }
        return ResponseEntity.ok(studentService.getStudents());
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable("id") Long id){
        return studentService.findById(id);
    }
}
