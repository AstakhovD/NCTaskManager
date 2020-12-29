package ua.edu.sumdu.j2se.astakhov.tasks.controller;

import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.astakhov.tasks.view.CalendarView;
import ua.edu.sumdu.j2se.astakhov.tasks.view.View;

import java.io.IOException;

public class CalendarContrl extends Controller {

    public CalendarContrl(View view, int actionToDo) {
        super(view, actionToDo);
    }

    @Override
    public int process(AbstractTaskList abstractTaskList) throws IOException {
        int taskChoose = ((CalendarView) view).taskChoose();
        if(taskChoose == 1) {
           return view.printInfo(abstractTaskList);
        } else if (taskChoose == 2){
        return Controller.MAIN_MENU;
        } else {
            System.out.println("Ошибка: Вы ввели неверное число");
            return Controller.CALENDAR;
        }
    }
}
