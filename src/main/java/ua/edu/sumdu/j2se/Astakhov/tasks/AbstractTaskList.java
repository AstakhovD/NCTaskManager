package ua.edu.sumdu.j2se.Astakhov.tasks;

public abstract class AbstractTaskList {

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract int size();

    public abstract Task getTask(int index);

    public final AbstractTaskList incoming(int from, int to) {
        if(this.getClass().equals(LinkedTaskList.class)) {
            LinkedTaskList linkedTaskList = new LinkedTaskList();
        }

        return null;
    }

}

