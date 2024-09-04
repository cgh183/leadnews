package com.heima.schedule.service.impl;

import com.heima.model.schedule.dtos.Task;
import com.heima.schedule.ScheduleApplication;
import com.heima.schedule.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ScheduleApplication.class)
@RunWith(SpringRunner.class)
public class TaskServiceImplTest {

    @Autowired
    private TaskService taskService;

    @Test
     void addTask() {
        Task task = new Task();
        task.setTaskType(100);
        task.setPriority(50);
        task.setParameters("task tesk".getBytes());
        task.setExecuteTime(new Date().getTime());
        long taskId = taskService.addTask(task);
        System.out.println(taskId);
    }

    @Test
    void cancelTask() {
        taskService.cancelTask(1830996363047514114L);
    }

    @Test
    void pollTask() {
        Task task = taskService.poll(100, 50);
        System.out.println(task.toString());
    }



}