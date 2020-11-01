package ua.edu.sumdu.j2se.Astakhov.tasks;

/***
 * Class ArrayTaskList
 *
 * @author Астахов Дмитрій
 */

public class ArrayTaskList {

    private Task[] list = new Task[0];

    /***
     * Method add - adds the specified task to the list.
     *
     * @param task of type Task
     */

    public void  add (Task task){
            Task[] tasks = list;
            list = new Task[tasks.length + 1];
            for(int i = 0; i < tasks.length; i++) {
                list[i] = tasks[i];
            }
        list[list.length - 1] = task;
    }

    /***
     * Method remove - deleted Task from list and return true if this Task was be in the list.
     * If in the list been more than one like this Task - delete any Task.
     *
     * @param task of type Task
     * @return false
     */

    public boolean remove(Task task) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == task) {
                Task[] tasks = list;
                list = new Task[tasks.length - 1];
                for (int j = 0; j < i; j++) {
                    list[j] = tasks[j];
                }
                for (int j = i + 1; j < tasks.length; j++) {
                    list[j - 1] = tasks[j];
                }
                return true;
            }
        }
        return false;
    }

    /***
     * The method returns the size of the list.
     *
     * @return the size of the list
     */

    public int size() {
        return list.length;
    }

    /***
     * Method getTask - returns the index on array..
     *
     * @param index of type int
     * @return the index on array
     */

    public Task getTask(int index){
        return list[index];
    }

    /***
     * Method ArrayTaskList incoming - returns a subset of tasks that were scheduled to run at least once after "from" and no later than "to".
     *
     * @param from of type int
     * @param to of type int
     * @return the arrayTaskList
     */

    public ArrayTaskList incoming (int from, int to){
        ArrayTaskList arrayTaskList = new ArrayTaskList();
        for (int i = 0; i < list.length; i++) {
            if (list[i].nextTimeAfter(from) != -1 && list[i].getEndTime() <= to) {
                if (list[i].isActive()) {
                    arrayTaskList.add(list[i]);
                }
            }
        }
        return arrayTaskList;
    }
}
