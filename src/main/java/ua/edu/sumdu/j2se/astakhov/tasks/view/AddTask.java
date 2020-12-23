package ua.edu.sumdu.j2se.astakhov.tasks.view;

import ua.edu.sumdu.j2se.astakhov.tasks.controller.Controller;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddTask implements View {
    @Override
    public int printInfo(AbstractTaskList abstractTaskList) {
        System.out.println("Новая задача была добавлена");
        return Controller.MAIN_MENU;
    }

    public String taskTitle() {
        System.out.println("Введите название задачи");
        String name = " ";
        try {
            name = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }

    public int taskChoose() {
        System.out.println("Выберите тип вашей задачи");
        System.out.println("Повторяемая - 1");
        System.out.println("Не повторяемая - 2");

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
        String date = " ";
        try {
            date = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm");
        LocalDateTime time = LocalDateTime.parse(date, dateTimeFormatter);
        return time;
    }

    public LocalDateTime startTime() {
        System.out.println("Введите дату (Пример: 2021-07-15 14:32)");
        String date = " ";
        try {
            date = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm");
        LocalDateTime start = LocalDateTime.parse(date, dateTimeFormatter);
        return start;
    }

    public LocalDateTime endTime() {
        System.out.println("Введите дату (Пример: 2021-07-15 14:32)");
        String date = " ";
        try {
            date = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm");
        LocalDateTime end = LocalDateTime.parse(date, dateTimeFormatter);
        return end;
    }

    public int repeatableOfTask() {
        System.out.println("Введите интервал выполнения задачи");
        int interval = 0;
        try {
            String task = bufferedReader.readLine();
            interval = Integer.parseInt(task);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return interval;
    }
}
