package org.potter.houses.service;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.potter.houses.entity.Student;
import org.potter.houses.repository.StudentRepository;
import org.potter.houses.request.StudentRequest;
import org.potter.houses.response.StudentResponse;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    public Single<StudentResponse> findById(Long id) {
        return Single.create(singleSubscriber -> {
            var studentOpt = studentRepository.findById(id);
            if (studentOpt.isPresent()){
                var student = studentOpt.get();
                var response = new StudentResponse(student.getName(),
                        houseService.getHouseFromApi(student.getHouseId()));
                singleSubscriber.onSuccess(response);
            }
            else {
                singleSubscriber.onError(new EntityNotFoundException());
            }
        });
    }

    public Observable<StudentResponse> findByHouse(String id) {
        var students = studentRepository.findByHouseId(id);
        return getResponseObservable(students);
    }

    private Observable<StudentResponse> getResponseObservable(List<Student> students) {
        return Observable.fromIterable(students).map(student -> new StudentResponse(student.getName(),
                houseService.getHouseFromApi(student.getHouseId())));
    }

    public Single<Student> addStudent(StudentRequest studentRequest) {
        return addStudentToRepository(studentRequest);
    }

    private Single<Student> addStudentToRepository(StudentRequest studentRequest) {
        return Single.create(singleSubscriber -> {
            var student = studentRepository.save(buildStudent(studentRequest));
            singleSubscriber.onSuccess(student);
        });
    }

    public Student buildStudent(StudentRequest studentRequest) {
        return new Student(studentRequest.getName(), houseService.getHouseId());
    }

}
