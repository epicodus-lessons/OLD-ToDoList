package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class TaskTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        Task.clearAllTasks(); //I clear out allll the posts before each test.
    }

    @Test
    public void NewPostObjectGetsCorrectlyCreated_true() throws Exception {
        Task task = setupNewPost();
        assertEquals(true, task instanceof Task);
    }

    @Test
    public void PostInstantiatesWithContent_true() throws Exception {
        Task task = setupNewPost();
        assertEquals("Mow the lawn", task.getDescription());
    }

    @Test
    public void AllPostsAreCorrectlyReturned_true() throws Exception {
        Task task = setupNewPost();
        Task otherTask = new Task("Brush the cat");
        assertEquals(2, Task.getAll().size());
    }

    @Test
    public void AllPostsContainsAllPosts_true() throws Exception {
        Task task = setupNewPost();
        Task otherTask = new Task("Brush the cat");
        assertTrue(Task.getAll().contains(task));
        assertTrue(Task.getAll().contains(otherTask));
    }

    @Test
    public void isPublishedPropertyIsFalseAfterInstantiation() throws Exception {
        Task task = setupNewPost();
        assertEquals(false, task.getCompleted()); //should never start as completed
    }

    @Test
    public void getCreatedAtInstantiatesWithCurrentTimeToday() throws Exception {
    Task task = setupNewPost();
        assertEquals(LocalDateTime.now().getDayOfWeek(), task.getCreatedAt().getDayOfWeek());
    }

    @Test
    public void postsInstantiateWithId() throws Exception {
        Task task = setupNewPost();
        assertEquals(1, task.getId());
    }

    @Test
    public void findReturnsCorrectPost() throws Exception {
        Task task = setupNewPost();
        assertEquals(1, Task.findById(task.getId()).getId());
    }


    @Test
    public void findReturnsCorrectPostWhenMoreThanOnePostExists() throws Exception {
        Task task = setupNewPost();
        Task otherTask = new Task("Brush the cat");
        assertEquals(2, Task.findById(otherTask.getId()).getId());
    }

    @Test
    public void updateChangesPostContent() throws Exception {
        Task task = setupNewPost();
        String formerContent = task.getDescription();
        LocalDateTime formerDate = task.getCreatedAt();
        int formerId = task.getId();

        task.update("Floss the cat");

        assertEquals(formerId, task.getId());
        assertEquals(formerDate, task.getCreatedAt());
        assertNotEquals(formerContent, task.getDescription());
    }

    @Test
    public void deleteDeletesASpecificPost() throws Exception {
        Task task = setupNewPost();
        Task otherTask = new Task("Brush the cat");
        task.deleteTask();
        assertEquals(1, Task.getAll().size()); //one is left
        assertEquals(Task.getAll().get(0).getId(), 2); //the one that was deleted has the id of 2
    }

    @Test
    public void deleteAllPostsDeletesAllPosts() throws Exception {
        Task task = setupNewPost();
        Task otherTask = setupNewPost();
        Task.clearAllTasks();
        assertEquals(0, Task.getAll().size());
    }


    //helper methods
    public Task setupNewPost(){
        return new Task("Mow the lawn");
    }
}