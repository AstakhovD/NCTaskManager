package ua.edu.sumdu.j2se.astakhov.tasks;

import java.util.Objects;

/***
 * Class Task
 *
 * @author Астахов Дмитрій
 *
 */

public class Task implements Cloneable {

    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;
    private boolean repeated;

    /***
     * Constructor Task
     *
     * @param title of type String
     * @param time of type int
     */

    public Task(String title, int time) {
        this.title = title;
        this.time = time;
        if(time < 0) {
            throw new IllegalArgumentException("Time can not be a 0");
        }
    }

    /***
     * Constructor Task
     *
     * @param title of type String
     * @param start of type int
     * @param end of type int
     * @param interval of type int
     */

    public Task(String title, int start, int end, int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        repeated = true;
        if(start < 0 || end < 0) {
            throw new IllegalArgumentException("Time can not be a 0");
        }
    }

    /***
     * Method getTitle returns the title of this object.
     *
     * @return the title of this object.
     */

    public String getTitle() {
        return title;
    }

    /***
     * Method setTitle sets the title of this object.
     *
     * @param title - the title of this object.
     */

    public void setTitle(String title) {
        this.title = title;
    }

    /***
     *Method isActive returns the active of this object.
     *
     * @return the active of this object.
     */

    public boolean isActive() {
        return active;
    }

    /***
     * Method setActive sets the active of this object.
     *
     * @param active - the active of this object.
     */

    public void setActive(boolean active) {
        this.active = active;
    }

    /***
     * Method getTime returns the execution time of the Task.
     * If the task repeats, the repeat start time is returned.
     *
     * @return the time of this object.
     */

    public int getTime() {
        if (repeated) {
            return start;
        }
        return time;
    }

    /***
     * Method setTime sets the time for the task to run. If the task is repeated , then it will be converted
     * to a non-recurring.
     *
     * @param time - the time of this object.
     */

    public void setTime(int time) {
        if (repeated) {
            repeated = false;
        }
        this.time = time;
    }

    /***
     * Method getStartTime returns the start time of the task.
     * If the task is not repeated, then returns the task completion time.
     *
     * @return the start of this object
     */

    public int getStartTime() {
        if (!repeated) {
            return time;
        }
        return start;
    }

    /***
     * Method getEndTime returns the end time of the task.
     * If the task is not repeated, then returns the time the task completion time.
     *
     * @return the end of this object.
     */

    public int getEndTime() {
        if (!repeated) {
            return time;
        }
        return end;
    }

    /***
     * Method getRepeatInterval returns the time interval between executions of tasks, which repeats.
     * If the task is not repeated, then returns 0.
     *
     * @return the interval of this object.
     */

    public int getRepeatInterval() {
        if (!repeated) {
            return 0;
        }
        return interval;
    }

    /***
     * Method setTime sets repeated, if the task does not repeat.
     *
     * @param start of type int
     * @param end of type int
     * @param interval of type int
     */

    public void setTime(int start, int end, int interval) {
        if (!repeated) {
            repeated = true;
        }
    }

    /***
     * Method isRepeated returns the repeated of this object.
     *
     * @return the repeated of this object.
     */

    public boolean isRepeated() {
        return repeated;
    }

    /***
     * Method nextTimeAfter returns the next task execution time.
     *
     * @param current of type int
     * @return the current of this object
     */

    public int nextTimeAfter(int current) {
        if (isActive()) {
            if (getStartTime() > current) {
                return getStartTime();
            } else if ((getEndTime() <= current)) {
                return -1;
            }
            if (current + getRepeatInterval() > getEndTime()) {
                return -1;
            }
            if (getRepeatInterval() > 0) {
                if (getStartTime() >= getEndTime()) {
                    return -1;
                }
                if (getStartTime() <= current) {
                    int time = getStartTime();
                    while (time <= current) {
                        time += getRepeatInterval();
                    }
                    return time;
                }
                if (current >= getEndTime()) {
                    return -1;
                }
            }
        } else if (!isActive()) {
            return -1;
        }
        return current;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (time != task.time) return false;
        if (start != task.start) return false;
        if (end != task.end) return false;
        if (interval != task.interval) return false;
        if (active != task.active) return false;
        if (repeated != task.repeated) return false;
        return Objects.equals(title, task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), isActive(), getTime(), getRepeatInterval(), isRepeated(), getStartTime(), getEndTime());
    }

    public Task clone() {
        try {
            Task task = (Task) super.clone();
            return task;
        } catch (CloneNotSupportedException e ) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", time=" + time +
                ", start=" + start +
                ", end=" + end +
                ", interval=" + interval +
                ", active=" + active +
                ", repeated=" + repeated +
                '}';
    }
}