package ua.edu.sumdu.j2se.astakhov.tasks.view;

import java.time.LocalDateTime;

import static ua.edu.sumdu.j2se.astakhov.tasks.controller.Controller.MAIN_MENU;

/**
 * Class NotificationView realizes notification for Tasks.
 *
 * @author Астахов Дмитрій
 */

public class NotificationView {

    /**
     * Method printInfo shows the result.
     *
     * @param time of type LocalDateTime
     * @param title of type String
     * @return back to the "MAIN_MENU" menu
     */

    public int printInfo(LocalDateTime time, String title) {
        System.out.println("Уведомления задач");
        System.out.println("Задача " + title + " должна быть выполнена. Текущее время: " + time);
        return MAIN_MENU;
    }
}
