package ua.edu.sumdu.j2se.astakhov.tasks;

public class TaskListFactory {
    public static AbstractTaskList createTaskList(ListTypes.types type) {
        if (type.equals(ListTypes.types.ARRAY)) {
            return new ArrayTaskList();
        }
        if (type.equals(ListTypes.types.LINKED)) {
            return new LinkedTaskList();
        }
        return null;
    }
}
