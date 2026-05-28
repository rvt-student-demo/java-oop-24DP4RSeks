package rvt.Todolist;

import java.util.ArrayList;
import java.util.List;

public class TodoList {

    private final TodoDB db;
    private final ArrayList<Integer> ids;
    private final ArrayList<String> tasks;

    public TodoList() {
        this.tasks = new ArrayList<>();
        this.ids = new ArrayList<>();
        this.db = new TodoDB();
        loadFromDb();
    }

    private void loadFromDb() {
        List<TodoDB.Task> rows = db.getAllTasks();
        for (TodoDB.Task row : rows) {
            ids.add(row.id());
            tasks.add(row.task());
        }
    }

    public boolean checkEventStrings(String value) {
        if (value == null || value.trim().length() < 3) {
            return false;
        }
        return value.matches("^[\\p{L}\\p{N} ]*$");
    }

    public void add(String taskName) {
        String cleanTaskName = taskName == null ? "" : taskName.trim();

        if (!checkEventStrings(cleanTaskName)) {
            System.out.println("Task must contain only letters/numbers and be at least 3 characters long.");
            return;
        }

        int dbId = db.addTask(cleanTaskName);
        if (dbId == -1) {
            System.out.println("Could not save task.");
            return;
        }

        ids.add(dbId);
        tasks.add(cleanTaskName);
        System.out.println("Added task number " + tasks.size() + ".");
    }

    public List<String> getTasks() {
        return new ArrayList<>(tasks);
    }

    public List<String> getTasksWithIds() {
        ArrayList<String> tasksWithIds = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            tasksWithIds.add((i + 1) + ". " + tasks.get(i));
        }

        return tasksWithIds;
    }

    public void printLastId() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks.");
            return;
        }
        System.out.println(tasks.size());
    }

    public void print() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void remove(int number) {
        int indexToRemove = number - 1;

        if (indexToRemove >= 0 && indexToRemove < tasks.size()) {
            int dbId = ids.remove(indexToRemove);
            tasks.remove(indexToRemove);
            db.removeById(dbId);
            System.out.println("Removed task number " + number + ".");
        } else {
            System.out.println("Invalid task number.");
        }
    }
}
