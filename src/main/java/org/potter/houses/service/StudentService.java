package org.potter.houses.service;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.potter.houses.entity.Student;
import org.potter.houses.exception.StudentNotFoundException;
import org.potter.houses.repository.StudentRepository;
import org.potter.houses.request.StudentRequest;
import org.potter.houses.response.StudentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final HouseService houseService;

    public Observable<StudentResponse> listAll() {
        var students = studentRepository.findAll();
        return getResponseObservable(students);
    }

    public Observable<StudentResponse> findByHouse(String id) {
        var students = studentRepository.findByHouseId(id);
        return getResponseObservable(students);
    }

    public Observable<StudentResponse> findByName(String name) {
        var students = studentRepository.findByName(name);
        return getResponseObservable(students);
    }

    private Observable<StudentResponse> getResponseObservable(List<Student> students) {
        return Observable.fromIterable(students).map(student -> new StudentResponse(student.getName(),
                houseService.getHouseFromApi(student.getHouseId())));
    }

    public Single<StudentResponse> findById(Long id) {
        return Single.create(singleSubscriber -> {
            var studentOpt = studentRepository.findById(id);
            if (studentOpt.isPresent()){
                var student = studentOpt.get();
                StudentResponse response = getStudentResponse(student);
                singleSubscriber.onSuccess(response);
            }
            else {
                singleSubscriber.onError(new StudentNotFoundException(id));
            }
        });
    }

    private StudentResponse getStudentResponse(Student student) {
        return new StudentResponse(student.getName(),
                houseService.getHouseFromApi(student.getHouseId()));
    }

    public Single<StudentResponse> addStudent(StudentRequest studentRequest) {
        return addStudentToRepository(studentRequest);
    }

    private Single<StudentResponse> addStudentToRepository(StudentRequest studentRequest) {
        return Single.create(singleSubscriber -> {
            var student = studentRepository.save(buildStudent(studentRequest));
            singleSubscriber.onSuccess(getStudentResponse(student));
        });
    }

    public Student buildStudent(StudentRequest studentRequest) {
        return new Student(studentRequest.getName(), houseService.getHouseId());
    }

}
