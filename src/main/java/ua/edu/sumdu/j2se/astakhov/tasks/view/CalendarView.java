package ua.edu.sumdu.j2se.astakhov.tasks.view;

import ua.edu.sumdu.j2se.astakhov.tasks.controller.Controller;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;


public class CalendarView implements View {
    @Override
    public int printInfo(AbstractTaskList abstractTaskList) {
        System.out.println("Просмотр календаря задач");
        return Controller.MAIN_MENU;
    }
}
