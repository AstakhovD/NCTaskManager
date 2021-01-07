package ua.edu.sumdu.j2se.astakhov.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.astakhov.tasks.view.CalendarView;
import ua.edu.sumdu.j2se.astakhov.tasks.view.View;

public class CalendarController extends Controller {

    private static final Logger logger = Logger.getLogger(CalendarController.class);

    public CalendarController(View view, int actionToDo) {
        super(view, actionToDo);
    }

    @Override
    public int process(AbstractTaskList abstractTaskList) {
        int taskChoose = ((CalendarView) view).taskChoose();
        if(taskChoose == 1) {
           return view.printInfo(abstractTaskList);
        } else if (taskChoose == 2){
        return Controller.MAIN_MENU;
        } else {
            logger.error("Ошибка: Вы ввели неверное число");
            System.out.println("Ошибка: Вы ввели неверное число");
            return Controller.CALENDAR;
        }
    }
}
