package ua.edu.sumdu.j2se.pizniur.tasks;

import java.util.Arrays;

public class ArrayTaskList {

    private int size = 10;
    private Task[] tasks = new Task[size];

    // метод, що додає до списку вказану задачу.
    public void add(Task task){
        tasks = Arrays.copyOf(tasks, size + 1);
        tasks[size()] = task;
    }

    //метод, що видаляє задачу зі списку і повертає істину, якщо така задача була у списку.
    public boolean remove(Task task){
        int size = tasks.length;
        for (int i = 0; i < size; i++) {

            if (tasks[i].getTitle().equals(task.getTitle())) {
                for (int j = i + 1; j < size; j++) {
                    tasks[j - 1] = tasks[j];
                }
                return true;
            }
        }
        return false;
    }

    //метод, що повертає кількість задач у списку.
    public int size(){
        int sizeArray = 0;
        for (Task task : tasks){
            if (task != null) sizeArray++;
            else break;
        }
        return sizeArray;
    }

    // метод, що повертає задачу
    public Task getTask(int index){
        return tasks[index];
    }

    public ArrayTaskList incoming(int from, int to){
        if(from < 0 && to <= from) throw new IllegalArgumentException("Невірні параметри часу!");
        ArrayTaskList plannedTasks = new ArrayTaskList();
        for(int i = 0; i < size(); i++){
            Task task = getTask(i);
            if(task.nextTimeAfter(from) != -1 && task.nextTimeAfter(from) < to){
                plannedTasks.add(task);
            }
        }
        return plannedTasks;
    }
}
