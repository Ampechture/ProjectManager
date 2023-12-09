package com.springeducational.projectmanagerv1_1.controllers;

import com.springeducational.projectmanagerv1_1.entities.Subtask;
import com.springeducational.projectmanagerv1_1.entities.Task;
import com.springeducational.projectmanagerv1_1.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        model.addAttribute("newTask", new Task());
        model.addAttribute("newSubtask", new Subtask());
        return "index";
    }

    @PostMapping("/addTask")
    public String addTask(Task task) {
        if (taskRepository.existsByprojectName(task.getProjectName())){return "WrongLoginData";
        }else {
            taskRepository.save(task);
            return "redirect:/";
        }
    }

    @PostMapping("/addSubtask/{projectId}")
    public String addSubtask(@PathVariable Long projectId, Subtask subtask) {
        Optional<Task> taskOptional = taskRepository.findById(projectId);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            subtask.setProject(task);
            task.getSubtasks().add(subtask);
            taskRepository.save(task);
        }
        return "redirect:/project/{projectId}";
    }
    @PostMapping("/addSubtask/delete")
    public void deleteTask(@PathVariable Task task) {
        Optional<Task> taskOptional = taskRepository.findById(task.getId());
        if (taskOptional.isPresent()){
            taskRepository.deleteById(task.getId());
        }
    }


    @GetMapping("/project/{projectId}")
    public String projectPage(@PathVariable Long projectId, Model model) {
        Optional<Task> taskOptional = taskRepository.findById(projectId);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            model.addAttribute("task", task);
            model.addAttribute("newSubtask", new Subtask());
            return "project";
        } else {
            return "redirect:/";
        }
    }
}

