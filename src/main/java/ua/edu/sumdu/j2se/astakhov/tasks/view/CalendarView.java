package ua.edu.sumdu.j2se.astakhov.tasks.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.edu.sumdu.j2se.astakhov.tasks.controller.Controller;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.astakhov.tasks.model.Task;
import ua.edu.sumdu.j2se.astakhov.tasks.model.Tasks;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;

import static ua.edu.sumdu.j2se.astakhov.tasks.controller.Controller.MAIN_MENU;
import static ua.edu.sumdu.j2se.astakhov.tasks.controller.Errors.*;

/**
 * Class CalendarView realizes calendar for Tasks.
 *
 * @author Астахов Дмитрій
 */

public class CalendarView implements View, TaskChoose {

    private static final Logger logger = LogManager.getLogger(CalendarView.class);

    /**
     * Method printInfo shows result.
     *
     * @param abstractTaskList of type AbstractTaskList
     * @return back to the "MAIN_MENU" menu
     */

    @Override
    public int printInfo(AbstractTaskList abstractTaskList) {
        System.out.println("Просмотр календаря");
        LocalDateTime start = taskStart();
        LocalDateTime end = taskEnd();
        if(start.isEqual(LocalDateTime.ofEpochSecond(1, 1, ZoneOffset.UTC).minusYears(999))) {
            logger.error(UNEXPECTED_START_TIME);
            System.out.println(UNEXPECTED_START_TIME);
            return Controller.CALENDAR;
        }
        if(end.isBefore(LocalDateTime.now())) {
            logger.error(UNEXPECTED_END_TIME);
            System.out.println(UNEXPECTED_END_TIME);
            return Controller.CALENDAR;
        }
        SortedMap<LocalDateTime, Set<Task>> tasks = Tasks.calendar(abstractTaskList, start, end);
        for(SortedMap.Entry<LocalDateTime, Set<Task>> elements : tasks.entrySet()) {
            for(Task task : elements.getValue()) {
                System.out.println("Название - " + task.getTitle());
            }
            System.out.println(elements.getKey() + "\n");
        }
        return MAIN_MENU;
    }

    /**
     * Method taskStart - returns the times of Task.
     *
     * @return times of this object
     */

    public LocalDateTime taskStart() {
        System.out.println("Укажите дату начала Вашей задачи (Пример: 2021-07-15 14:32)");
        return times();
    }

    /**
     * Method taskEnd - returns the times of Task.
     *
     * @return times of this object
     */

    public LocalDateTime taskEnd() {
        System.out.println("Укажите дату окончания Вашей задачи (Пример: 2021-07-15 14:32)");
        return times();
    }

    /**
     * Method taskChoose - returns the taskType of Task.
     *
     * @return taskType of this object
     */

    @Override
    public int taskChoose() {
        System.out.println("Выберите что нужно сделать");
        System.out.println("1 - просмотреть активность задач");
        System.out.println("2 - вернуться в главное меню");

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
