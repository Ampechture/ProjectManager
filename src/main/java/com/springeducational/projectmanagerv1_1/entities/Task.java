package com.springeducational.projectmanagerv1_1.entities;

import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name = "tasks")
public class Task {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Subtask> subtasks) {
        this.subtasks = subtasks;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectName;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Subtask> subtasks;

    // геттеры и сеттеры
}

