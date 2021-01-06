package ua.edu.sumdu.j2se.astakhov.tasks.view;

import ua.edu.sumdu.j2se.astakhov.tasks.controller.Controller;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.astakhov.tasks.model.Task;
import ua.edu.sumdu.j2se.astakhov.tasks.model.Tasks;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.SortedMap;


public class CalendarView implements View, TaskChoose {

    @Override
    public int printInfo(AbstractTaskList abstractTaskList) {
        System.out.println("Просмотр календаря");
        LocalDateTime start = taskStart();
        LocalDateTime end = taskEnd();
        if(start.isEqual(LocalDateTime.ofEpochSecond(1, 1, ZoneOffset.UTC).minusYears(999))) {
            System.out.println("Ошибка: неожиданное время начало задачи");
            return Controller.CALENDAR;
        }
        if(end.isBefore(LocalDateTime.now())) {
            System.out.println("Ошибка: неожиданное время окончания задачи");
            return Controller.CALENDAR;
        }
        SortedMap<LocalDateTime, Set<Task>> tasks = Tasks.calendar(abstractTaskList, start, end);
        for(SortedMap.Entry<LocalDateTime, Set<Task>> elements : tasks.entrySet()) {
            for(Task task : elements.getValue()) {
                System.out.println("Название - " + task.getTitle());
            }
            System.out.println(elements.getKey() + "\n");
        }
        return Controller.MAIN_MENU;
    }

    public LocalDateTime taskStart() {
        System.out.println("Укажите дату начала Вашей задачи (Пример: 2021-07-15 14:32)");
        return times();
    }

    public LocalDateTime taskEnd() {
        System.out.println("Укажите дату окончания Вашей задачи (Пример: 2021-07-15 14:32)");
        return times();
    }

    @Override
    public int taskChoose() {
        System.out.println("Выберите что нужно сделать");
        System.out.println("1 - просмотреть активность задач");
        System.out.println("2 - вернуться в главное меню");

        int taskType = 0;

        try {
            String taskName = bufferedReader.readLine();
            taskType = Integer.parseInt(taskName);
        } catch (IOException e) {
            return Controller.CALENDAR;
        }
        return taskType;
    }

    public LocalDateTime times() {
        String date = "";
        LocalDateTime time;
        try {
            date = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm");
            time = LocalDateTime.parse(date, dateTimeFormatter);
        } catch (DateTimeException e) {
            return LocalDateTime.ofEpochSecond(1, 1, ZoneOffset.UTC).minusYears(999);
        }
        return time;
    }
}
