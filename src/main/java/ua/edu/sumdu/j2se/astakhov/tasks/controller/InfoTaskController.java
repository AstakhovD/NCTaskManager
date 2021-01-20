package ua.edu.sumdu.j2se.astakhov.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.astakhov.tasks.view.InfoTaskView;
import ua.edu.sumdu.j2se.astakhov.tasks.view.View;

import java.time.LocalDateTime;

import static ua.edu.sumdu.j2se.astakhov.tasks.controller.Errors.UNEXPECTED_INDEX;
import static ua.edu.sumdu.j2se.astakhov.tasks.controller.Errors.WRONG_NUMBER;

/**
 * Class InfoTaskController realizes change task activity.
 *
 * @author Астахов Дмитрій
 */

public class InfoTaskController extends Controller {

    private static final Logger logger = Logger.getLogger(InfoTaskController.class);

    /**
     * Constructor InfoTaskController
     *
     * @param view of type View
     * @param actionToDo of type int
     */

    public InfoTaskController(View view, int actionToDo) {
        super(view, actionToDo);
    }

    /**
     * Method process realizes change task activity.
     *
     * @param abstractTaskList of type AbstractTaskList
     * @return shows the result of your choose
     */

    @Override
    public int process(AbstractTaskList abstractTaskList) {
        int index;
        int taskChoose = ((InfoTaskView) view).taskChoose();
        if(taskChoose == 1) {
            index = ((InfoTaskView) view).index();
            if(index >= abstractTaskList.size() || abstractTaskList.size() - 1 < index) {
                logger.error(UNEXPECTED_INDEX);
                view.exception(UNEXPECTED_INDEX);
                return ACTIVE_TASK;
            } else {
                int mode = ((InfoTaskView) view).activityMode();
                if(mode == 1) {
                    abstractTaskList.getTask(index).setActive(true);
                    if((abstractTaskList.getTask(index).getEndTime()).isBefore(LocalDateTime.now())) {
                        abstractTaskList.remove(abstractTaskList.getTask(index));
                    }
                } else if(mode == 2) {
                    abstractTaskList.getTask(index).setActive(false);
                    if((abstractTaskList.getTask(index).getEndTime()).isBefore(LocalDateTime.now())) {
                        abstractTaskList.remove(abstractTaskList.getTask(index));
                    }
                } else {
                    logger.error(WRONG_NUMBER);
                    view.exception(WRONG_NUMBER);
                    return ACTIVE_TASK;
                }
            }
        } else if(taskChoose == 2) {
            return MAIN_MENU;
        } else {
            logger.error(WRONG_NUMBER);
            view.exception(WRONG_NUMBER);
            return ACTIVE_TASK;
        }
        return view.printInfo(abstractTaskList);
    }
}
