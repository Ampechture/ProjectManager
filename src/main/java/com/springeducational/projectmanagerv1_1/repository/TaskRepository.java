package com.springeducational.projectmanagerv1_1.repository;

import com.springeducational.projectmanagerv1_1.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    boolean existsByprojectName(String projectName);

}
