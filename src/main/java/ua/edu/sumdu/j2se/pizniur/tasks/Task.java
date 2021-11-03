package ua.edu.sumdu.j2se.pizniur.tasks;

/**
 * Task class
 * @version     1.0
 * @author      Julia Pizniur
 */

public class Task {

    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;

    /**
     * Конструктор - неактивнa задача, яка виконується без повторення
     * @param title     задана назва
     * @param time      заданий час
     */
    public Task(String title, int time){
        this.title = title;
        this.time = time;
    }

    /**
     * Конструктор - неактивнa задача, яка виконується з повторенням
     * @param title     задана назва
     * @param start     початок
     * @param end       кінець
     * @param interval  інтервал повторення задачі
     */
    public Task(String title, int start, int end, int interval){
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    /**
     * Метод для зчитування назви задачі
     */
    public String getTitle(){
        return title;
    }

    /**
     * Метод для встановлення назви задачі
     * @param title     задана назва задачі
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * @return      повертає стан активності задачі
     */
    public boolean isActive(){
        return active;
    }

    /**
     * Встановлення стану задачі
     * @param active - true означає, що задача буде активною, false - не активною
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     *
     * Якщо задача неповторювана, метод робить її такою, що повторюється
     */
    public int getTime() {
        if (interval == 0){
            return time;
        }else {
            return start;
        }
    }

    /**
     * Якщо задача повторювана, метод перетворює її на неповторювану
     * @param time - новий час виконання задачі.
     */
    public void setTime(int time){
        interval = 0;
        start = 0;
        end = 0;
        this.time = time;
    }

    /**
     * Якщо задача неповторювана, метод робить її такою, що повторюється
     * @param start     початок
     * @param end       кінець
     * @param interval  інтервал повторення задачі
     */
    public void setTime(int start, int end, int interval){
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    /**
     * Метод для повторюваної задачі
     * @return   задача повторювана - повертає початок, якщо ні - повертає час виконання
     */
    public int getStartTime() {
        if (interval == 0){
            return time;
        } else {
            return start;
        }
    }

    /**
     * Метод для повторюваної задачі
     * @return   задача повторювана - повертає кінець, якщо ні - повертає час виконання
     */
    public int getEndTime() {
        if(isRepeated()) {
            return end;
        } else {
            return time;
        }
    }

    /**
     * Метод для повторюваної задачі
     * @return   задача повторювана - повертає інтервал, якщо ні - повертає 0
     */
    public int getRepeatInterval() {
        return interval;
    }

    /**
     * Метод для перевірки повторюваності
     * Задача неповторювана якщо інтервал дорівнює 0
     * @return   повторювана задача чи ні
     */
    public boolean isRepeated() {
        return interval != 0;
    }

    /**
     * Метод повертає час наступного виконання задачі після вказаного часу current, якщо після вказаного часу задача не виконується, то метод має повертати -1
     * @param current   вказаний поточний час
     * @return          повертає наступний час виконання задачі; якщо після current задача не виконується, то метод повертає -1
     */
    public int nextTimeAfter(int current) {
        int nextTime = 0;
        if (!active){
            nextTime = -1;
        } else if (current < time) {
            nextTime = time;
        } else if (current < start) {
            nextTime = start;
        } else if (current + interval > end) {
            nextTime = -1;
        } else if (current < end) {
            for (int i = 1; (nextTime) <= current; i++) {
                nextTime = start + interval * i;
            }
        } else {
            nextTime = -1;
        }
        return nextTime;
    }

}
