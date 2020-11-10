package ua.edu.sumdu.j2se.Astakhov.tasks;

/***
 * Class ArrayTaskList
 *
 * @author Астахов Дмитрій
 */

public class LinkedTaskList {

    private Node head;
    private int size;

    private class Node {
        public Task task;
        public Node next;

        public Node(Task task) {
            this.task = task;
            next = null;
        }
    }

    public void add(Task task) {
        Node node = new Node(task);

        if(head == null){
            head = node;
        }
        else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
        }
        size++;
    }

    public int size(){
        return size;
    }

    public boolean remove(Task task) {
        if(head != null) {
            Node current = head;
            Node previous = head;
            for (int i = 0; i < size; i++) {
                if (task == (getTask(i))) {
                    if (previous == current && head.next != null) {
                        head = current.next;
                    }else {
                        previous.next = current.next;
                    }
                            size--;

                    int index = 0;
                    current = head;

                    while (current != null) {
                        current = current.next;
                        index++;
                    }
                    return true;
                }
                previous = current;
                current = current.next;
            }

        }
        return false;
    }

    public Task getTask(int index) {
        if(index > size) {
            throw new IndexOutOfBoundsException();
        }
        int i = 0;
        Node current = head;
        while(i < index) {
            current = current.next;
            i++;
        }
        return current.task;
    }

    public LinkedTaskList incoming(int from, int to) {
        LinkedTaskList linkedTaskList = new LinkedTaskList();
        Node current = head;

        while(current.next != null) {
            if (current.task.nextTimeAfter(from) != -1 && current.task.getEndTime() <= to) {
                linkedTaskList.add(current.task);
            }
            current = current.next;
        }
        return linkedTaskList;
    }
}