package com.example.demo.course;

import com.example.demo.student.Student;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

//    private String student_id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="student_id")
    @JsonBackReference
    private Student student_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }

    public Student getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Student student_id) {
        this.student_id = student_id;
    }
}
