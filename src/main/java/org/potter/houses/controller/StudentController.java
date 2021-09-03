package org.potter.houses.controller;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.potter.houses.entity.Student;
import org.potter.houses.request.StudentRequest;
import org.potter.houses.response.StudentResponse;
import org.potter.houses.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/student")
@RestController
public class StudentController {

    private final StudentService service;

    @GetMapping
    public Observable<StudentResponse> listAll() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public Single<StudentResponse> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/house/{id}")
    public Observable<StudentResponse> findByHouse(@PathVariable String id) {
        return service.findByHouse(id);
    }

    @PostMapping
    public Single<Student> insertStudent(@RequestBody StudentRequest studentRequest) {
        return service.addStudent(studentRequest);
    }
}
