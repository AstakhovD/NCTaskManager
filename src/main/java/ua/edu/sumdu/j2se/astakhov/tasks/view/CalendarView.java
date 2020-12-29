package ua.edu.sumdu.j2se.astakhov.tasks.view;

import ua.edu.sumdu.j2se.astakhov.tasks.controller.Controller;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.astakhov.tasks.model.Tasks;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


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
            System.out.println("Ошибка: неожиданное время начало задачи");
            return Controller.CALENDAR;
        }
        System.out.println("Не повторяющиеся: ");
        for(int i = 0; i < abstractTaskList.size(); i++) {
            if(abstractTaskList.getTask(i).getRepeatInterval() == 0) {
                System.out.println(abstractTaskList.getTask(i).getTime() + " = " + abstractTaskList.getTask(i));
            }
        }
        System.out.println("");
        System.out.println("Повторяющиеся: ");
        Tasks.calendar(abstractTaskList, start, end).forEach((localDateTime, tasks1) ->
                System.out.println(localDateTime + " = " + abstractTaskList.getTask(0)));
        return Controller.MAIN_MENU;
    }

    public LocalDateTime taskStart() {
        System.out.println("Укажите дату начала (Пример: 2021-07-15 14:32)");
        String date = "";
        LocalDateTime start;
        try {
            date = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm");
            start = LocalDateTime.parse(date, dateTimeFormatter);
        } catch (DateTimeException e) {
            return LocalDateTime.ofEpochSecond(1, 1, ZoneOffset.UTC).minusYears(999);
        }
        return start;
    }

    public LocalDateTime taskEnd() {
        System.out.println("Укажите дату конца (Пример: 2021-07-15 14:32)");
        String date = "";
        LocalDateTime end;
        try {
            date = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm");
            end = LocalDateTime.parse(date, dateTimeFormatter);
        } catch (DateTimeException e) {
            return LocalDateTime.now().minusYears(999);
        }
        return end;
    }

    @Override
    public int taskChoose() {
        System.out.println("Выберите что нужно сделать");
        System.out.println("1 - проверить задачи");
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
}
