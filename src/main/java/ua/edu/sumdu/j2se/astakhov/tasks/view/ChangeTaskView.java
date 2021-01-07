package ua.edu.sumdu.j2se.astakhov.tasks.view;

import ua.edu.sumdu.j2se.astakhov.tasks.controller.Controller;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class ChangeTaskView implements View, TaskChoose {

    @Override
    public int taskChoose() {
        System.out.println("1 - Изменить параметр задачи");
        System.out.println("2 - Вернуться в главное меню");
        int taskType;
        try {
            String taskName = bufferedReader.readLine();
            taskType = Integer.parseInt(taskName);
        } catch (IOException e) {
            return -1;
        }
        return taskType;
    }

    @Override
    public int printInfo(AbstractTaskList abstractTaskList) {
        return Controller.CHANGE_TASK;
    }

    public int taskChooseRepeatable() {
        System.out.println("1 - Изменить название задачи");
        System.out.println("2 - Изменить время задачи");
        System.out.println("3 - Изменить интервал выполнения задачи");
        System.out.println("4 - вернуться назад");
        int taskType;
        try {
            String taskName = bufferedReader.readLine();
            taskType = Integer.parseInt(taskName);
        } catch (IOException e) {
            return -1;
        }
        return taskType;
    }

    public int taskChooseNotRepeatable() {
        System.out.println("1 - Изменить название задачи");
        System.out.println("2 - Изменить время задачи");
        System.out.println("3 - вернуться назад");
        int taskType;
        try {
            String taskName = bufferedReader.readLine();
            taskType = Integer.parseInt(taskName);
        } catch (IOException e) {
            return -1;
        }
        return taskType;
    }

    public int interval() {
        System.out.println("Укажите новый интервал выполнение задачи: ");
        int interval;
        try {
            String taskName = bufferedReader.readLine();
            interval = Integer.parseInt(taskName);
        } catch (IOException e) {
            return Integer.MAX_VALUE;
        }
        return interval;
    }

    public int index() {
        System.out.println("Укажите номер задачи которую нужно изменить: ");
        int index;
        try {
            String taskName = bufferedReader.readLine();
            index = Integer.parseInt(taskName);
        } catch (IOException e) {
            return Integer.MAX_VALUE;
        }
        return index;
    }

    public LocalDateTime time() {
        System.out.println("Введите дату (Пример: 2021-07-15 14:32)");
        return times();
    }

    public LocalDateTime start() {
        System.out.println("Введите дату начала Вашей задачи (Пример: 2021-07-15 14:32)");
        return times();
    }

    public LocalDateTime end() {
        System.out.println("Введите дату окончания Вашей задачи (Пример: 2021-07-15 14:32)");
        return times();
    }

    public String newTitle() {
        System.out.println("Введите новое название Вашей задачи");
        String date = " ";
        try {
            date = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return date;
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