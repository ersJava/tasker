package com.company.tasker.service;

import com.company.tasker.dao.TaskerDao;
import com.company.tasker.model.Task;
import com.company.tasker.model.TaskViewModel;
import com.company.tasker.util.feign.AdserverServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class TaskerServiceLayer {

    TaskerDao dao;
    AdserverServiceClient client;

    @Autowired
    public TaskerServiceLayer(TaskerDao dao, AdserverServiceClient client) {
        this.dao = dao;
        this.client = client;
    }

    private TaskViewModel buildTaskViewModel(Task task) {

        TaskViewModel tvm = new TaskViewModel();

        tvm.setId(task.getId());
        tvm.setDescription(task.getDescription());
        tvm.setCreateDate(task.getCreateDate());
        tvm.setDueDate(task.getDueDate());
        tvm.setCategory(task.getCategory());
        tvm.setAdvertisement(client.getAd());

        return tvm;

    }

    @Transactional
    public TaskViewModel fetchTask(int id) {

        Task task = dao.getTask(id);

        if (task == null)
            throw new NoSuchElementException(String.format("No Task with id %s found", id));
        else
            return buildTaskViewModel(task);
    }

    public List<TaskViewModel> fetchAllTasks() {

        List<Task> taskList = dao.getAllTasks();

        List<TaskViewModel> taskViewModelList = new ArrayList<>();

        for (Task t : taskList) {
            TaskViewModel tvm = buildTaskViewModel(t);
            taskViewModelList.add(tvm);
        }

        return taskViewModelList;
    }

    public List<TaskViewModel> fetchTasksByCategory(String category) {

        List<Task> taskList = dao.getTasksByCategory(category);

        if (taskList != null && taskList.size() == 0)
            throw new NoSuchElementException(String.format("No task found with %s category found", category));
        else {
            List<TaskViewModel> taskViewModelList = new ArrayList<>();

            for (Task t : taskList) {
                TaskViewModel tvm = buildTaskViewModel(t);
                taskViewModelList.add(tvm);
            }
            return taskViewModelList;
        }
    }

    public TaskViewModel newTask(TaskViewModel taskViewModel) {

        Task task = new Task();
        task.setDescription(taskViewModel.getDescription()); // previous task.getDescription
        task.setCreateDate(taskViewModel.getCreateDate());
        task.setDueDate(taskViewModel.getDueDate());
        task.setCategory(taskViewModel.getCategory());

        task = dao.createTask(task);
        taskViewModel.setId(task.getId());
        taskViewModel.setAdvertisement(client.getAd());

        return taskViewModel;
    }

    public void deleteTask(int id) {

        Task task = dao.getTask(id);

        if (task == null)
            throw new NoSuchElementException(String.format("No Task with id %s found", id));
        else
            dao.deleteTask(id);
    }

    public void updateTask(TaskViewModel taskViewModel) {

        Task task = new Task();
        task.setId(taskViewModel.getId());
        task.setDescription(taskViewModel.getDescription());
        task.setCreateDate(taskViewModel.getCreateDate());
        task.setDueDate(taskViewModel.getDueDate());
        task.setCategory(taskViewModel.getCategory());
        taskViewModel.setAdvertisement(client.getAd());

        dao.updateTask(task);
    }

}
