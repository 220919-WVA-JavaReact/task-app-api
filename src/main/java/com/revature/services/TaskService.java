package com.revature.services;

import com.revature.services.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private TaskRepository tr;

    @Autowired
    public TaskService(TaskRepository tr) {
        this.tr = tr;
    }
}
