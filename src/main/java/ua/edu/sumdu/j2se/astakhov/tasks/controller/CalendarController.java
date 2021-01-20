package ua.edu.sumdu.j2se.astakhov.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.astakhov.tasks.view.CalendarView;
import ua.edu.sumdu.j2se.astakhov.tasks.view.View;

import static ua.edu.sumdu.j2se.astakhov.tasks.controller.Errors.WRONG_NUMBER;

/**
 * Class CalendarController realizes calendar for Tasks.
 *
 * @author Астахов Дмитрій
 */

public class CalendarController extends Controller {

    private static final Logger logger = Logger.getLogger(CalendarController.class);

    /**
     * Constructor CalendarController.
     *
     * @param view of type View
     * @param actionToDo of type int
     */

    public CalendarController(View view, int actionToDo) {
        super(view, actionToDo);
    }

    /**
     * Method process realizes calendar for Tasks.
     *
     * @param abstractTaskList of type AbstractTaskList
     * @return back to the "CALENDAR" menu
     */

    @Override
    public int process(AbstractTaskList abstractTaskList) {
        int taskChoose = ((CalendarView) view).taskChoose();
        if(taskChoose == 1) {
           return view.printInfo(abstractTaskList);
        } else if (taskChoose == 2){
        return MAIN_MENU;
        } else {
            logger.error(WRONG_NUMBER);
            view.exception(WRONG_NUMBER);
            return CALENDAR;
        }
    }
}
