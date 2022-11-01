package com.revature.controllers;

import com.revature.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TaskController {


    private TaskService ts;

    @Autowired
    public TaskController(TaskService ts){
        System.out.println("TaskController created!");
        this.ts = ts;
    }
}
