package ua.edu.sumdu.j2se.astakhov.tasks.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static ua.edu.sumdu.j2se.astakhov.tasks.controller.Controller.CHANGE_TASK;
import static ua.edu.sumdu.j2se.astakhov.tasks.controller.Errors.*;

/**
 * Class ChangeTaskView realizes changes parameters for Tasks.
 *
 * @author Астахов Дмитрій
 */

public class ChangeTaskView implements View, TaskChoose {

    private static final Logger logger = LogManager.getLogger(ChangeTaskView.class);

    /**
     * Method taskChoose - returns the taskType of Task.
     *
     * @return taskType of this object
     */

    @Override
    public int taskChoose() {
        System.out.println("1 - Изменить параметр задачи");
        System.out.println("2 - Вернуться в главное меню");
        int taskType;
        try {
            String taskName = bufferedReader.readLine();
            taskType = Integer.parseInt(taskName);
        } catch (IOException e) {
            logger.error(WRONG_NUMBER);
            System.out.println(WRONG_NUMBER);
            return taskChoose();
        }
        return taskType;
    }

    /**
     * Method printInfo shows result.
     *
     * @param abstractTaskList of type AbstractTaskList
     * @return back to the "CHANGE_TASK" menu
     */

    @Override
    public int printInfo(AbstractTaskList abstractTaskList) {
        System.out.println("Изменения были применены");
        return CHANGE_TASK;
    }

    /**
     * Method taskChooseRepeatable - returns the taskType of Task.
     *
     * @return taskType of this object
     */

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
            logger.error(WRONG_NUMBER);
            System.out.println(WRONG_NUMBER);
            return taskChooseRepeatable();
        }
        return taskType;
    }

    /**
     * Method taskChooseNotRepeatable - returns the taskType of Task.
     *
     * @return taskType of this object
     */

    public int taskChooseNotRepeatable() {
        System.out.println("1 - Изменить название задачи");
        System.out.println("2 - Изменить время задачи");
        System.out.println("3 - вернуться назад");
        int taskType;
        try {
            String taskName = bufferedReader.readLine();
            taskType = Integer.parseInt(taskName);
        } catch (IOException e) {
            logger.error(WRONG_NUMBER);
            System.out.println(WRONG_NUMBER);
            return taskChooseNotRepeatable();
        }
        return taskType;
    }

    /**
     * Method interval - returns the interval multiplied by 60 of Task.
     *
     * @return interval multiplied by 60 of this object
     */

    public int interval() {
        System.out.println("Укажите новый интервал выполнение задачи: ");
        int interval;
        try {
            String taskName = bufferedReader.readLine();
            interval = Integer.parseInt(taskName);
        } catch (IOException e) {
            return Integer.MAX_VALUE;
        }
        return interval * 60;
    }

    /**
     * Method index - returns the index of Task.
     *
     * @return index of this object
     */

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

    /**
     * Method time - returns the times of Task.
     *
     * @return times of this object
     */

    public LocalDateTime time() {
        System.out.println("Введите дату (Пример: 2021-07-15 14:32)");
        return times();
    }

    /**
     * Method start - returns the times of Task.
     *
     * @return times of this object
     */

    public LocalDateTime start() {
        System.out.println("Введите дату начала Вашей задачи (Пример: 2021-07-15 14:32)");
        return times();
    }

    /**
     * Method end - returns the times of Task.
     *
     * @return times of this object
     */

    public LocalDateTime end() {
        System.out.println("Введите дату окончания Вашей задачи (Пример: 2021-07-15 14:32)");
        return times();
    }

    /**
     * Method newTitle - returns the new name of Task.
     *
     * @return name of this object
     */

    public String newTitle() {
        System.out.println("Введите новое название Вашей задачи");
        String name;
        Scanner scanner = new Scanner(System.in);
        name = scanner.nextLine();
        if(name.isEmpty()) {
            logger.error(UNEXPECTED_TITLE);
            System.out.println(UNEXPECTED_TITLE);
            return newTitle();
        }
        return name;
    }

    /**
     * Method times - returns the time of Task.
     *
     * @return time of this object
     */

    public LocalDateTime times() {
        String date = "";
        LocalDateTime time;
        try {
            date = bufferedReader.readLine();
        } catch (IOException e) {
            logger.error(UNEXPECTED_TIME);
            System.out.println(UNEXPECTED_TIME);
            return times();
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