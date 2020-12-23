package ua.edu.sumdu.j2se.astakhov.tasks.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable, Serializable {

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract int size();

    public abstract Task getTask(int index);

    public abstract Stream<Task> getStream();

    public final AbstractTaskList incoming(LocalDateTime from, LocalDateTime to) {
        if(this.getClass().equals(ArrayTaskList.class)) {
            ArrayTaskList arrayTaskList = new ArrayTaskList();
            this.getStream().filter(task -> task != null && task.nextTimeAfter(from) != null && task.getEndTime().isBefore(to)) // isBefore - <=
                    .forEach(arrayTaskList :: add);
            return arrayTaskList;
        }
        if (this.getClass().equals(LinkedTaskList.class)) {
            LinkedTaskList linkedTaskList = new LinkedTaskList();
            this.getStream().filter(task -> task != null && task.nextTimeAfter(from) != null && task.getEndTime().isBefore(to))
                    .forEach(linkedTaskList :: add);
            return linkedTaskList;
        }
        return null;
    }

}

