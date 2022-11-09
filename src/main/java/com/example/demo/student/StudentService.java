package com.example.demo.student;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

    public List<Student> getStudents() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BooksPU");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);

        List<Predicate> searchCriterias = new ArrayList<>();
        searchCriterias.add(criteriaBuilder.like(root.get("name"), "%victor%"));
        criteriaQuery.where(criteriaBuilder.and(searchCriterias.toArray(new Predicate[searchCriterias.size()])));
//        criteriaQuery.select(root.get("name")).groupBy(root.get("name"));

//        criteriaQuery.multiselect(root.get("name"), criteriaBuilder.count(root)).groupBy(root.get("name"));

        return entityManager.createQuery(criteriaQuery).getResultList();

//        criteriaQuery.select(studentRoot.get("name"));
//        criteriaQuery.groupBy(studentRoot.get("name"));
//        TypedQuery<Student> typedQuery = entityManager.createQuery(criteriaQuery);
//        List<Student> studentList = typedQuery.getResultList();
//        return studentList;
//        return studentRepository.findAll();
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
