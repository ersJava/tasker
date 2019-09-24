package com.company.tasker.dao;


import com.company.tasker.model.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaskerDaoJdbcTemplateImplTest {

    @Autowired
    protected TaskerDao taskerDao;

    @Before
    public void setUp() throws Exception {

        List<Task> taskList = taskerDao.getAllTasks();
        taskList.stream().forEach(task -> taskerDao.deleteTask(task.getId()));

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addGetDeleteBook() {

        Task task = new Task();
        task.setDescription("Learn Java");
        task.setCreateDate(LocalDate.of(2019, 5, 15));
        task.setDueDate(LocalDate.of(2019, 10,15));
        task.setCategory("Education");

        task = taskerDao.createTask(task);

        Task task1 = taskerDao.getTask(task.getId());

        assertEquals(task, task1);

        taskerDao.deleteTask(task.getId());

        task1 = taskerDao.getTask(task.getId());

        assertNull(task1);

    }

    @Test
    public void getAllTask() {

        Task task = new Task();
        task.setDescription("Learn Java");
        task.setCreateDate(LocalDate.of(2019, 5, 15));
        task.setDueDate(LocalDate.of(2019, 10,15));
        task.setCategory("Education");

        task = taskerDao.createTask(task);

        task = new Task();
        task.setDescription("Learn Spring");
        task.setCreateDate(LocalDate.of(2019, 7, 31));
        task.setDueDate(LocalDate.of(2019, 10,15));
        task.setCategory("Education");

        task = taskerDao.createTask(task);

        List<Task> taskList = taskerDao.getAllTasks();

        assertEquals(2, taskList.size());
    }

    @Test
    public void updateTask() {

        Task task = new Task();
        task.setDescription("Learn Java");
        task.setCreateDate(LocalDate.of(2019, 5, 15));
        task.setDueDate(LocalDate.of(2019, 10,15));
        task.setCategory("Education");

        task = taskerDao.createTask(task);

        task.setDescription("UPDATE");
        task.setCreateDate(LocalDate.of(2019, 6, 12));
        task.setDueDate(LocalDate.of(2019, 11,12));
        task.setCategory("UPDATE");

        taskerDao.updateTask(task);

        Task task1 = taskerDao.getTask(task.getId());

        assertEquals(task, task1);
    }

    @Test
    public void getTasksByCategory() {

        Task task = new Task();
        task.setDescription("Learn Java");
        task.setCreateDate(LocalDate.of(2019, 5, 15));
        task.setDueDate(LocalDate.of(2019, 10,15));
        task.setCategory("Education");

        task = taskerDao.createTask(task);

        task = new Task();
        task.setDescription("Learn Spring");
        task.setCreateDate(LocalDate.of(2019, 7, 31));
        task.setDueDate(LocalDate.of(2019, 10,15));
        task.setCategory("Education");

        task = taskerDao.createTask(task);

        List<Task> taskList = taskerDao.getTasksByCategory("Education");

        assertEquals(2, taskList.size());

    }

}
