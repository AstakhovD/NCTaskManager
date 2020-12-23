package ua.edu.sumdu.j2se.astakhov.tasks.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;


/***
 * Class ArrayTaskList
 *
 * @author Астахов Дмитрій
 */

public class ArrayTaskList extends AbstractTaskList {

    private final int DEFAULT_CAPACITY = 10;
    private Task[] list;
    private int size;

    public ArrayTaskList() {
        list = new Task[DEFAULT_CAPACITY];
    }

    /***
     * Method add - adds the specified task to the list.
     *
     * @param task of type Task
     */

    public void add (Task task){
        if (task == null) {
            throw new NullPointerException();
        }
        if(size == list.length) {
            list = Arrays.copyOf(list, (int)(list.length * 1.5));
        }
        list[size++] = task;
    }

    /***
     *  Method remove - deleted Task from list and return true if this Task was be in the list.
     *  If in the list been more than one like this Task - delete any Task.
     * @param task of type Task
     * @return removed
     */

    public boolean remove(Task task) {
        boolean removeTask = false;
        for (int i = 0, k = i; k < size; i++, k++) {
            if (!removeTask && list[i].equals(task)) {
                k++;
                removeTask = true;
            }
            if (k < size) {
                list[i] = list[k];
            }
        }
        if (removeTask) {
            list[size - 1] = null;
            size--;
        }
        return removeTask;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrayTaskList arrayTaskList = (ArrayTaskList) o;

        if (size != arrayTaskList.size) return false;
        return Arrays.equals(list, arrayTaskList.list);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(list);
    }

    public ArrayTaskList clone() {
        try {
            ArrayTaskList arrayClone = (ArrayTaskList) super.clone();
            arrayClone.list = Arrays.copyOf(list, list.length);
            return arrayClone;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
            private int current = 0;

            @Override
            public boolean hasNext() {
                return current < size;
            }

            @Override
            public Task next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return list[current++];
            }

            @Override
            public void remove() throws IllegalStateException {
                if(current <= 0) {
                    throw new IllegalStateException();
                }
                    ArrayTaskList arrayRemove = new ArrayTaskList();
                    arrayRemove.list = list;
                    arrayRemove.size = size;
                    Task tasks = list[--current];
                    arrayRemove.remove(tasks);
                    size--;
                }
        };
    }

    public Stream<Task> getStream() {
        return Stream.of(this.list);
    }

    @Override
    public String toString() {
        return "ArrayTaskList{" +
                "DEFAULT_CAPACITY=" + DEFAULT_CAPACITY +
                ", list=" + Arrays.toString(list) +
                ", size=" + size +
                '}';
    }
}