package ua.edu.sumdu.j2se.astakhov.tasks;

import java.util.Arrays;
/***
 * Class ArrayTaskList
 *
 * @author Астахов Дмитрій
 */

public class ArrayTaskList extends AbstractTaskList{

    private final int DEFAULT_CAPACITY = 10;
    private Task[] list;
    private int size;

    public ArrayTaskList() {
        list = new Task[DEFAULT_CAPACITY];
        size = 0;
    }

    /***
     * Method add - adds the specified task to the list.
     *
     * @param task of type Task
     */

    public void add (Task task){
        if(size == list.length) {
            list = Arrays.copyOf(list, (int)(list.length * 1.5));
        }
        list[size] = task;
        size++;
    }

    /***
     * Method remove - deleted Task from list and return true if this Task was be in the list.
     * If in the list been more than one like this Task - delete any Task.
     *
     * @param task of type Task
     * @return false
     */

    public boolean remove(Task task) {
        for (int i = 0; i < size; i++) {
            if (list[i] == task) {
                for(int k = i; k < size - 1; k++) {
                    list[i] = list[k + 1];
                }
                list[size] = null;
                size--;
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
        return size;
    }

    /***
     * Method getTask - returns the index on array.
     *
     * @param index of type int
     * @return the index on array
     */

    public Task getTask(int index){
        if(list.length < index) {
            throw new IndexOutOfBoundsException("Index greater than the length of the array");
        }

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
        for (int i = 0; i < size; i++) {
            if (list[i].nextTimeAfter(from) != -1 && list[i].getEndTime() <= to) {
                arrayTaskList.add(list[i]);
            }
        }
        return arrayTaskList;
    }
}