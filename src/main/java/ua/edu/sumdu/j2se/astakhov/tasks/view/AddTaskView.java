package ua.edu.sumdu.j2se.astakhov.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import static ua.edu.sumdu.j2se.astakhov.tasks.controller.Controller.MAIN_MENU;
import static ua.edu.sumdu.j2se.astakhov.tasks.controller.Errors.*;

/**
 * Class AddTaskView realizes add new task.
 *
 * @author Астахов Дмитрій
 */

public class AddTaskView implements View, TaskChoose {

    private static final Logger logger = Logger.getLogger(AddTaskView.class);

    /**
     * Method printInfo shows result.
     *
     * @param abstractTaskList of type AbstractTaskList
     * @return back to the "MAIN_MENU" menu
     */

    @Override
    public int printInfo(AbstractTaskList abstractTaskList) {
        System.out.println("Новая задача была добавлена");
        return MAIN_MENU;
    }

    /**
     * Method taskTitle - returns the name of Task.
     *
     * @return name of this object
     */

    public String taskTitle() {
        System.out.println("Введите название задачи");
        String name;
        Scanner scanner = new Scanner(System.in);
        name = scanner.nextLine();
        if(name.isEmpty()) {
            logger.error(UNEXPECTED_TITLE);
            System.out.println(UNEXPECTED_TITLE);
            return taskTitle();
        }
        return name;
    }

    /**
     * Method taskChoose - returns the typeOfYourTask of Task.
     *
     * @return typeOfYourTask of this object
     */

    public int taskChoose() {
        System.out.println("Выберите тип вашей задачи");
        System.out.println("1 - Повторяемая");
        System.out.println("2 - Не повторяемая");
        System.out.println("3 - Вернуться в главное меню");

        int typeOfYourTask;
        try {
            String task = bufferedReader.readLine();
            typeOfYourTask = Integer.parseInt(task);

        } catch (IOException e ) {
            logger.error(WRONG_NUMBER);
            System.out.println(WRONG_NUMBER);
            return taskChoose();
        }
        return typeOfYourTask;
    }

    /**
     * Method taskTime - returns the times of Task.
     *
     * @return times of this object
     */

    public LocalDateTime taskTime() {
        System.out.println("Введите дату (Пример: 2021-07-15 14:32)");
        return times();
    }

    /**
     * Method startTime - returns the times of Task.
     *
     * @return times of this object
     */

    public LocalDateTime startTime() {
        System.out.println("Введите дату начала Вашей задачи (Пример: 2021-07-15 14:32)");
        return times();
    }

    /**
     * Method endTime - returns the times of Task.
     *
     * @return times of this object
     */

    public LocalDateTime endTime() {
        System.out.println("Введите дату окончания Вашей задачи(Пример: 2021-07-15 14:32)");
        return times();
    }

    /**
     * Method repeatableOfTask - returns the interval multiplied by 60 of Task.
     *
     * @return interval of this object
     */

    public int repeatableOfTask() {
        System.out.println("Введите интервал выполнения Вашей задачи");
        int interval;
        Scanner scanner = new Scanner(System.in);
        try {
            interval = scanner.nextInt();
            if(interval < 1) {
                logger.error(UNEXPECTED_INTERVAL);
                System.out.println(UNEXPECTED_INTERVAL);
                return repeatableOfTask();
            }
        } catch (InputMismatchException e) {
            interval = Integer.MAX_VALUE;
        }
        return interval * 60;
    }

    /**
     * Method times - returns the time of Task.
     *
     * @return time of this object
     */

    public LocalDateTime times() {
        String date;
        LocalDateTime time;
        Scanner scanner = new Scanner(System.in);
        date = scanner.nextLine();
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm");
            time = LocalDateTime.parse(date, dateTimeFormatter);
        } catch (DateTimeException e) {
            return LocalDateTime.ofEpochSecond(1, 1, ZoneOffset.UTC).minusYears(999);
        }
        return time;
    }
}

