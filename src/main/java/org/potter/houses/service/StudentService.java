package org.potter.houses.service;

import lombok.RequiredArgsConstructor;
import org.potter.houses.entity.Student;
import org.potter.houses.repository.StudentRepository;
import org.potter.houses.request.StudentRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final HouseService houseService;

    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public List<Student> findByHouse(String id) {
        var house = houseService.findById(id);
        return studentRepository.findByHouse(house);
    }

    public Student insertStudent(StudentRequest studentRequest) {
        var student = buildStudent(studentRequest);
        studentRepository.save(student);
        return student;
    }

    public Student buildStudent(StudentRequest studentRequest) {
        return new Student(studentRequest.getName(), houseService.getHouse());
    }

}
