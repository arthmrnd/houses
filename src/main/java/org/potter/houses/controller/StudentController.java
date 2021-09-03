package org.potter.houses.controller;

import lombok.RequiredArgsConstructor;
import org.potter.houses.entity.Student;
import org.potter.houses.request.StudentRequest;
import org.potter.houses.service.StudentService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/Student")
@RestController
public class StudentController {

    private final StudentService service;

    @GetMapping
    public List<Student> listAll() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public Optional<Student> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/house/{id}")
    public List<Student> findByHouse(@PathVariable String id) {
        return service.findByHouse(id);
    }

    @PostMapping
    public Student insertStudent(@RequestBody StudentRequest studentRequest) {
        return service.insertStudent(studentRequest);
    }
}
