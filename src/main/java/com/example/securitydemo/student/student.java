package com.example.securitydemo.student;

public class student {
    private final Integer studentId;
    private final String studentName;

    public student(Integer studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }
}
