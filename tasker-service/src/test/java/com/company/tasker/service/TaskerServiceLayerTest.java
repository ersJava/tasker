package com.company.tasker.service;

import com.company.tasker.dao.TaskerDao;
import com.company.tasker.dao.TaskerDaoJdbcTemplateImpl;
import com.company.tasker.model.Task;
import com.company.tasker.model.TaskViewModel;
import com.company.tasker.util.feign.AdserverServiceClient;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TaskerServiceLayerTest {

    TaskerServiceLayer serviceLayer;

    TaskerDao dao;

    AdserverServiceClient client;

    @Before
    public void setUp() throws Exception {

        setUpTaskerDaoMock();
        setUpAdserverServiceMock();

       serviceLayer = new TaskerServiceLayer(dao, client);

    }

    private void setUpAdserverServiceMock() {
        client = mock(AdserverServiceClient.class);
        doReturn("Home Equity Loans @ 3.87% APR").when(client).getAd();
    }

    private void setUpTaskerDaoMock() {

        dao = mock(TaskerDaoJdbcTemplateImpl.class);

        Task task = new Task();
        task.setId(1);
        task.setDescription("Learn Java");
        task.setCreateDate(LocalDate.of(2019, 5, 15));
        task.setDueDate(LocalDate.of(2019, 10,15));
        task.setCategory("Education");

        Task task1 = new Task();
        task1.setDescription("Learn Java");
        task1.setCreateDate(LocalDate.of(2019, 5, 15));
        task1.setDueDate(LocalDate.of(2019, 10,15));
        task1.setCategory("Education");

        List<Task> taskList = new ArrayList<>();
        taskList.add(task);

        // mock newTask
        doReturn(task).when(dao).createTask(task1);

        // mock fetchTask
        doReturn(task).when(dao).getTask(1);

        // mock fetchAllTasks
        doReturn(taskList).when(dao).getAllTasks();

        // mock fetchTaskByCategory
        doReturn(taskList).when(dao).getTasksByCategory("Education");

        // update Mock data
        Task updateTask = new Task();
        updateTask.setId(2);
        updateTask.setDescription("Learn Spring");
        updateTask.setCreateDate(LocalDate.of(2019, 7, 31));
        updateTask.setDueDate(LocalDate.of(2019, 10,15));
        updateTask.setCategory("Education");

        // mock updateTask
        doNothing().when(dao).updateTask(updateTask);
        doReturn(updateTask).when(dao).getTask(2);

        // mock deleteTask
        doNothing().when(dao).deleteTask(3);
        doReturn(null).when(dao).getTask(3);
    }

    @Test
    public void addGetTaskViewModel() {

        Task task1 = new Task();
        task1.setDescription("Learn Java");
        task1.setCreateDate(LocalDate.of(2019, 5, 15));
        task1.setDueDate(LocalDate.of(2019, 10,15));
        task1.setCategory("Education");

        TaskViewModel tvm = new TaskViewModel();
        tvm.setDescription("Learn Java");
        tvm.setCreateDate(LocalDate.of(2019, 5, 15));
        tvm.setDueDate(LocalDate.of(2019, 10,15));
        tvm.setCategory("Education");
        tvm.setAdvertisement(client.getAd());

        tvm = serviceLayer.newTask(tvm);

        TaskViewModel tvm1 = serviceLayer.fetchTask(tvm.getId());

        assertEquals(tvm, tvm1);

    }

    @Test
    public void fetchAllTaskViewModels() {

        List<TaskViewModel> taskViewModelList = serviceLayer.fetchAllTasks();
        assertEquals(1, taskViewModelList.size());

    }

    @Test
    public void fetchTaskViewModelsByCategory() {

        List<TaskViewModel> taskViewModelList = serviceLayer.fetchTasksByCategory("Education");
        assertEquals(1, taskViewModelList.size());

    }

    @Test(expected = NoSuchElementException.class)
    public void deleteTaskViewModel() {

        serviceLayer.deleteTask(3);

        TaskViewModel tvm = serviceLayer.fetchTask(3);
    }

    @Test
    public void updateTaskViewModel() {

        TaskViewModel tvmUpdate = new TaskViewModel();

        Task updateTask = new Task();
        updateTask.setId(2);
        updateTask.setDescription("Learn Spring");
        updateTask.setCreateDate(LocalDate.of(2019, 7, 31));
        updateTask.setDueDate(LocalDate.of(2019, 10,15));
        updateTask.setCategory("Education");

        tvmUpdate.setId(updateTask.getId());
        tvmUpdate.setDescription(updateTask.getDescription());
        tvmUpdate.setCreateDate(updateTask.getCreateDate());
        tvmUpdate.setDueDate(updateTask.getDueDate());
        tvmUpdate.setCategory(updateTask.getCategory());
        tvmUpdate.setAdvertisement(client.getAd());

        serviceLayer.updateTask(tvmUpdate);

        TaskViewModel tvmAfterUpdate = serviceLayer.fetchTask(tvmUpdate.getId());

        assertEquals(tvmAfterUpdate, tvmUpdate);

    }

}
