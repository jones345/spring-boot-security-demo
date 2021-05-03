package com.example.securitydemo.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class studentController {

    private  static final List<student> STUDENTS = Arrays.asList(
        new student(1,"jones"),
         new student(2,"mike"),
         new student(4,"james")
    );

    @GetMapping("{studentId}")
    public student getStudent(@PathVariable("studentId") Integer studentId){
   return STUDENTS.stream()
           .filter(student -> studentId.equals(student.getStudentId()))
           .findFirst()
           .orElseThrow(() -> new IllegalStateException("student" + studentId+"does not exits"));
//           .orElseThrow(()-> new IllegalStateException("student")+ studentId+" it does not exit");
    }
}
