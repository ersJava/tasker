package com.company.tasker.dao;

import com.company.tasker.model.Task;

import java.util.List;

public interface TaskerDao {

    public Task createTask(Task task);
    public Task getTask(int id);
    public List<Task> getAllTasks();
    public List<Task> getTasksByCategory(String category);
    public void updateTask(Task task);
    public void deleteTask(int id);

}
