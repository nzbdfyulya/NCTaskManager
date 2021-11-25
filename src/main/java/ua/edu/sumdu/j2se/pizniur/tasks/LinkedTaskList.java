package ua.edu.sumdu.j2se.pizniur.tasks;

import java.util.Arrays;

public class LinkedTaskList {
    private int size = 0;
    private Node head;

    /* вкладений клас Node для формування вузлів зв'язного списку LinkedTaskList
       (кожен вузол має значення Task та посилання на наступний вузол списку)
        */
    private static class Node {
        public Task element;
        public Node next;

        public Node (Task element) {
            this.element = element;
        }
    }

    // метод, що додає до списку вказану задачу.
    public void add(Task task){
        if (head == null){
            head = new Node(task);
        }else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node(task);
        }
        size++;
    }

    /*метод, що видаляє задачу зі списку і повертає істину, якщо така задача була у списку.
    Якщо у списку було декілька таких задач, необхідно видалити одну
    будь-яку*/
    public boolean remove(Task task){
        Node current = head;
        Node prev = null;
        for (int i = 0; i < size; i++) {
            if (current.element.equals(task)) {
                if (prev == null){
                    head = current.next;
                }else {
                    prev.next = current.next;
                }
                size--;
                return true;
            } else {
                prev = current;
                current = current.next;
            }
        }
        return false;
    }

    //метод, що повертає кількість задач у списку.
    public int size(){
        return size;
    }

    // метод, що повертає задачу
    public Task getTask(int index){
        if (index < 0||index > size) {
            throw new IndexOutOfBoundsException("Невірне значення індексу!");
        }
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.element;
    }

    public LinkedTaskList incoming(int from, int to){
        if(from < 0 && to <= from) throw new IllegalArgumentException("Невірні параметри часу!");
        LinkedTaskList plannedTasks = new LinkedTaskList();
        for(int i = 0; i < size(); i++){
            Task task = getTask(i);
            if(task.nextTimeAfter(from) != -1 && task.nextTimeAfter(from) < to){
                plannedTasks.add(task);
            }
        }
        return plannedTasks;
    }
}
