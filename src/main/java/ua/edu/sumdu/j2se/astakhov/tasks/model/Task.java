package ua.edu.sumdu.j2se.astakhov.tasks.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/***
 * Class Task
 *
 * @author Астахов Дмитрій
 *
 */

public class Task implements Cloneable, Serializable {

    private String title;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private int repeatInterval;
    private int interval;
    private boolean active;
    private boolean repeated;

    /***
     * Constructor Task
     *
     * @param title of type String
     * @param time of type LocalDateTime
     */

    public Task(String title, LocalDateTime time) {
        setTitle(title);
        setTime(time);
        if(time == null) {
            throw new IllegalArgumentException("Time can not be a null");
        }
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /***
     * Constructor Task
     *
     * @param title of type String
     * @param start of type LocalDateTime
     * @param end of type LocalDateTime
     * @param interval of type int
     */

    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.repeatInterval = interval;
        setTime(start, end, interval);
        if(start == null || end == null) {
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

    public LocalDateTime getTime() {
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

    public void setTime(LocalDateTime time) {
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

    public LocalDateTime getStartTime() {
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

    public LocalDateTime getEndTime() {
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
        return repeatInterval;
    }

    public void setRepeatInterval(int repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

    /***
     * Method setTime sets repeated, if the task does not repeat.
     *
     * @param start of type LocalDateTime
     * @param end of type LocalDateTime
     * @param interval of type int
     */

    public void setTime(LocalDateTime start, LocalDateTime end, int interval) {
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

    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        if(!isActive()){
            return null;
        }
        if (isRepeated()) {
            if(current.isBefore(getStartTime()) ){
                return getStartTime();
            }
            if(current.isAfter(getEndTime())){
                return null;
            }
            else {
                LocalDateTime time = getStartTime().plusSeconds(0);
                while (time.isBefore(current) || time.equals(current)){
                    time = time.plusMinutes(getRepeatInterval());
                }
                if(getEndTime().isAfter(time) || getEndTime().isEqual(time)) {
                    return time;
                }
                else {
                    return null;
                }
            }
        }
        else {
            if(getTime().isAfter(current)){
                return getTime();
            }
            else {
                return null;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;

        Task task = (Task) o;

        return  (getTime() != task.getTime()) &&
         (getStartTime() != task.getStartTime()) && (getEndTime() != task.getEndTime()) &&
         (getRepeatInterval() != task.getRepeatInterval()) && (isRepeated() != task.isRepeated()) && (isActive() == task.isActive())
                && Objects.equals(getTitle(), task.getTitle());
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
