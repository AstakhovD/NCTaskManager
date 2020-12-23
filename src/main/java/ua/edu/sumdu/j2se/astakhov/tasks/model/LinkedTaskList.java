package ua.edu.sumdu.j2se.astakhov.tasks.model;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

/***
 * Class LinkedTaskList
 *
 * @author Астахов Дмитрій
 */

public class LinkedTaskList extends AbstractTaskList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        private Task task;
        private Node next;

        public Node(Task task, Node next) {
            this.task = task;
            this.next = next;
        }

        public Task getTask() {
            return this.task;
        }

        public Node getNext() {
            return this.next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "task=" + task +
                    ", next=" + next +
                    '}';
        }
    }

    public void add(Task task) {
        if (isEmpty()) {
            head = new Node(task, null);
            tail = head;
        } else {
            tail.setNext(new Node(task, null));
            tail = tail.getNext();
        }
        size++;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public boolean remove(Task task) {
        Node current = head;
        Node previous = null;
        for (int i = 0; i < size; i++) {
            if (getTask(i).equals(task)) {
                if (previous != null) {
                    previous.next = current.next;
                    if (current.next == null)
                        tail = previous;
                } else {
                    head = head.next;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }


    public int size() {
        return this.size;
    }

    public Task getTask(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        if (index < size() - 1) {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return current.getTask();
        }
        return tail.getTask();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedTaskList that = (LinkedTaskList) o;
        if (size != that.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!getTask(i).equals(that.getTask(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 31 * Objects.hashCode(size);
        for(int i = 0; i < size; i++) {
            hash = getTask(i).hashCode();
        }
        return hash;
    }

    public LinkedTaskList clone() {
        try {
            LinkedTaskList linkedClone = (LinkedTaskList) super.clone();
            linkedClone.size = 0;
            for(int i = 0; i < size; i++) {
                linkedClone.add(getTask(i));
            }
            return linkedClone;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    @Override
    public Iterator<Task> iterator() {
        Iterator<Task> linkedIterator = new Iterator<Task>() {
            Node current = head;
            Node previous = null;
            Node previous2 = null;
            boolean removeTask = false;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Task next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                Task task = current.getTask();
                previous2 = previous;
                previous = current;
                current = current.getNext();
                removeTask = false;
                return task;
            }

            @Override
            public void remove() {
                if (previous == null || removeTask) {
                    throw new IllegalStateException();
                }
                if (previous2 == null) {
                    head = current;
                } else {
                    previous2.setNext(current);
                }
                size--;
                removeTask = true;
            }
        };
        return linkedIterator;
    }

    public Stream<Task> getStream() {
        ArrayTaskList arrayTaskList = new ArrayTaskList();
        for (int i = 0; i < size; i++) {
            arrayTaskList.add(getTask(i));
        }
        return arrayTaskList.getStream();
    }

    @Override
    public String toString() {
        return "LinkedTaskList{" +
                "head=" + head +
                ", end=" + tail +
                ", cout=" + size +
                '}';
    }
}
