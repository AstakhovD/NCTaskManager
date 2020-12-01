package ua.edu.sumdu.j2se.astakhov.tasks;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable {

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract int size();

    public abstract Task getTask(int index);

    public abstract AbstractTaskList incoming(int from, int to);

}

