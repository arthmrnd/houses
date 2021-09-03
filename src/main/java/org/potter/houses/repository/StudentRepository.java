package org.potter.houses.repository;

import org.potter.houses.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByHouseId(String houseId);
    List<Student> findByName(String name);
}
