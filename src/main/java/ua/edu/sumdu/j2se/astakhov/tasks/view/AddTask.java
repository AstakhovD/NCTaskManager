package ua.edu.sumdu.j2se.astakhov.tasks.view;

import ua.edu.sumdu.j2se.astakhov.tasks.controller.Controller;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class AddTask implements View, TaskChoose {
    @Override
    public int printInfo(AbstractTaskList abstractTaskList) {
        System.out.println("Новая задача была добавлена");
        return Controller.ADD_TASK;
    }

    public String taskTitle() {
        System.out.println("Введите название задачи");
        String name = "";
        try {
            name = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }

    public int taskChoose() {
        System.out.println("Выберите тип вашей задачи");
        System.out.println("1 - Повторяемая");
        System.out.println("2 - Не повторяемая");
        System.out.println("3 - Вернуться в главное меню");

        int typeOfYourTask = 0;
        try {
            String task = bufferedReader.readLine();
            typeOfYourTask = Integer.parseInt(task);

        } catch (IOException e ) {
            e.printStackTrace();
        }
        return typeOfYourTask;
    }

    public LocalDateTime taskTime() {
        System.out.println("Введите дату (Пример: 2021-07-15 14:32)");
        return times();
    }

    public LocalDateTime startTime() {
        System.out.println("Введите дату начала Вашей задачи (Пример: 2021-07-15 14:32)");
        return times();
    }

    public LocalDateTime endTime() {
        System.out.println("Введите дату окончания Вашей задачи(Пример: 2021-07-15 14:32)");
        return times();
    }

    public int repeatableOfTask() {
        System.out.println("Введите интервал выполнения Вашей задачи");
        int interval = 0;
        try {
            String task = bufferedReader.readLine();
            interval = Integer.parseInt(task);
        } catch (IOException e) {
            interval = Integer.MAX_VALUE;
        }
        return interval;
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
