package ua.edu.sumdu.j2se.astakhov.tasks.model;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.*;

public class Tasks {
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        return new Iterable<Task>() {
            @Override
            public Iterator<Task> iterator() {
                Stream<Task> streamTask = StreamSupport.stream(tasks.spliterator(), false);
                Iterator<Task> iterator = streamTask.filter(task -> task != null && task.nextTimeAfter(start) != null && !task.nextTimeAfter(start).isAfter(end))
                        .iterator();
                return iterator;
            }
        };
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        SortedMap<LocalDateTime, Set<Task>> sortedTask = new TreeMap<>();
        for (Task task : tasks) {
            LocalDateTime current = task.nextTimeAfter(start.minusSeconds(1));
            while (current != null && !current.isAfter(end)) {
                if(sortedTask.containsKey(current)) {
                    sortedTask.get(current).add(task);
                }
                else {
                    Set<Task> set = new HashSet<>();
                    set.add(task);
                    sortedTask.put(current, set);
                }
                current = task.nextTimeAfter(current);
            }
        }
        return sortedTask;
    }
}
