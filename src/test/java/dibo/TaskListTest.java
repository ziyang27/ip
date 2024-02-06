package dibo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dibo.exception.DiboException;
import dibo.task.Task;
import dibo.task.ToDo;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        ArrayList<Task> store = new ArrayList<>();
        taskList = new TaskList(store);
    }

    @Test
    public void testAddTask() {
        Task task1 = new ToDo("A");
        taskList.addTask(task1);
        assertEquals(1, taskList.getSize());
    }

    @Test
    public void testDeleteTask() {
        Task task1 = new ToDo("A");
        taskList.addTask(task1);
        try {
            taskList.deleteTask(1);
        } catch (DiboException c) {
            //Ignore
        }
        assertEquals(0, taskList.getSize());
    }

    @Test
    public void testDeleteTask_exceptionThrown() {
        Task task1 = new ToDo("A");
        taskList.addTask(task1);

        assertThrows(DiboException.class, () -> {
            taskList.deleteTask(2);
        });
    }

    @Test
    public void testMarkTask() {
        Task task1 = new ToDo("A");
        taskList.addTask(task1);
        try {
            taskList.markTask(1);
        } catch (DiboException c) {
            //Ignore
        }
        assertTrue(task1.isDone());
    }

    @Test
    public void testMarkTask_exceptionThrown() {
        Task task1 = new ToDo("A");
        taskList.addTask(task1);

        assertThrows(DiboException.class, () -> {
            taskList.markTask(2);
        });
    }
    @Test
    public void testUnmarkTask() {
        Task task1 = new ToDo("A");
        task1.markAsDone();
        taskList.addTask(task1);
        try {
            taskList.unmarkTask(1);
        } catch (DiboException c) {
            //Ignore
        }
        assertFalse(task1.isDone());
    }

    @Test
    public void testUnMarkTask_exceptionThrown() {
        Task task1 = new ToDo("A");
        taskList.addTask(task1);

        assertThrows(DiboException.class, () -> {
            taskList.unmarkTask(2);
        });
    }

    @Test
    public void testGetTasksWithKeyword() {
        Task task1 = new ToDo("A");
        Task task2 = new ToDo("Aa");
        Task task3 = new ToDo("Ba");
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);
        try {
            assertEquals(taskList.getTasksWithKeyword(new String[]{"A"}), "1.[T][ ] A\n2.[T][ ] Aa\n");
        } catch (DiboException c) {
            //Ignore
        }
    }

}
