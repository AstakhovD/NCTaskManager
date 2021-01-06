package ua.edu.sumdu.j2se.astakhov.tasks.view;

import ua.edu.sumdu.j2se.astakhov.tasks.controller.Controller;

import java.time.LocalDateTime;

public class NotificationView {

    public int printInfo(LocalDateTime time, String title) {
        System.out.println("Уведомления задач");
        System.out.println("Задача " + title + " должна быть выполнена. Текущее время: " + time);
        return Controller.MAIN_MENU;
    }

}
