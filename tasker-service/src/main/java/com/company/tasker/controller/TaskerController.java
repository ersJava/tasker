package com.company.tasker.controller;

import com.company.tasker.model.TaskViewModel;
import com.company.tasker.service.TaskerServiceLayer;
import com.company.tasker.util.feign.AdserverServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RefreshScope
public class TaskerController {

    @Autowired
    TaskerServiceLayer service;

    @Autowired
    private final AdserverServiceClient client;

    public TaskerController(TaskerServiceLayer service, AdserverServiceClient client) {
        this.service = service;
        this.client = client;
    }

    @RequestMapping(value = "/ad", method =RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getAd() {
        return client.getAd();
    }

    @RequestMapping(value = "/tasks", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public TaskViewModel createTask(@RequestBody @Valid TaskViewModel tvm) {
        return service.newTask(tvm);
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TaskViewModel> getAllTasks() {
        return service.fetchAllTasks();
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public TaskViewModel getTask(@PathVariable int id) {
        return service.fetchTask(id);
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTask(@PathVariable int id, @RequestBody @Valid TaskViewModel tvm) {

        if (id != tvm.getId())
            throw new NoSuchElementException(String.format("No Task with id %s found", id));
        else
        service.updateTask(tvm);
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable int id) {
        service.deleteTask(id);
    }

    @RequestMapping(value = "tasks/category/{category}", method =RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TaskViewModel> getTasksByCategory(@PathVariable String category) {
        return service.fetchTasksByCategory(category);
    }
}
