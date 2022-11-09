package com.example.demo.student;

import com.example.demo.course.Course;
import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "student")
@DynamicUpdate(value = true)
@DynamicInsert
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )

    private Long id;
    private String name;
    private Integer age;
    private LocalDate doB;
    private String email;
    private String address;

    @OneToMany(mappedBy = "student_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @JsonManagedReference
    @JsonIgnoreProperties({"student_id"})
    private List<Course> courses;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Student() {
    }

    public Student(Long id, String name, Integer age, LocalDate doB, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.doB = doB;
        this.email = email;
    }

    public String getAddress() {
        return  address == null ? "vacio" : address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Student(String name, Integer age, LocalDate doB, String email) {
        this.name = name;
        this.age = age;
        this.doB = doB;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDoB() {
        return doB;
    }

    public void setDoB(LocalDate doB) {
        this.doB = doB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", doB=" + doB +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", courses=" + courses +
                '}';
    }
}
