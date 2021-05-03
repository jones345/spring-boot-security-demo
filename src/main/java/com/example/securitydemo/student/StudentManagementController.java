package com.example.securitydemo.student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class StudentManagementController {

    private  static final List<student> STUDENTS = Arrays.asList(
            new student(1,"jones"),
            new student(2,"mike"),
            new student(4,"james")
    );


    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public static List<student> getSTUDENTS() {
        return STUDENTS;
    }

    @PostMapping
    @PreAuthorize("hasRole('student:write')")
    public void register(student student){
   System.out.println(student);
    }

    @DeleteMapping(path = "{StudentID}")
    @PreAuthorize("hasRole('student:write')")
    public void delete(@PathVariable("StudentID") Integer StudentID){
        System.out.println(StudentID);
    }

    @PatchMapping(path = "{StudentID}")
    @PreAuthorize("hasRole('student:write')")
    public void update( @PathVariable("StudentID")Integer StudentID, @RequestBody student student){
            System.out.println(String.format("%s %s", StudentID, student));
    }
}
