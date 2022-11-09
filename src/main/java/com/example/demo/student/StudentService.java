package com.example.demo.student;

import com.example.demo.course.Course;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Object getStudents(long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BooksPU");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);

        root.join("courses", JoinType.LEFT);

//        List<Predicate> searchCriterias = new ArrayList<>();
//        searchCriterias.add(criteriaBuilder.like(root.get("name"), "%victor%"));

//        Join<Student, Course> joinCourse = root.join("courses", JoinType.INNER);
//        ListJoin<Student, Course> joinCourse = root.join("courses", JoinType.INNER);
//
//        searchCriterias.add(criteriaBuilder.equal(joinCourse.get("student_id"), root.get("id")));
//        searchCriterias.add(root.join("courses").in(rootCourses.get("student_id")));
//        searchCriterias.add(criteriaBuilder.equal(joinCourse.get("student_id"), root.get("courses")));

//        criteriaQuery.where(criteriaBuilder.and(searchCriterias.toArray(new Predicate[searchCriterias.size()]))).distinct(true);
//        criteriaQuery.select(root.get("name")).groupBy(root.get("name"));

        criteriaQuery.distinct(true);

//        criteriaQuery.multiselect(root.get("name"), criteriaBuilder.count(root)).groupBy(root.get("name"));

        Object result =  entityManager.createQuery(criteriaQuery).getResultList();
        return result;

//        criteriaQuery.select(studentRoot.get("name"));
//        criteriaQuery.groupBy(studentRoot.get("name"));
//        TypedQuery<Student> typedQuery = entityManager.createQuery(criteriaQuery);
//        List<Student> studentList = typedQuery.getResultList();
//        return studentList;
//        long number = 2;
//        return studentRepository.findById(id);
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentsByEmail(student.getEmail());

        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }

        studentRepository.save(student);
    }


    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("student with"+ studentId +"does not exist!"));

//        Student student = new Student();
//        student.setId(studentId);
        student.setName(name);
        studentRepository.save(student);

//        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
//            student.setName(name);
//        }
//
//        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
//           Optional<Student> studentOptional = studentRepository.findStudentsByEmail(email);
//           if (studentOptional.isPresent()) {
//               throw new IllegalStateException("email taken");
//           }
//
//           student.setEmail(email);
//        }
    }
}
