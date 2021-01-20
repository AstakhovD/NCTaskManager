package ua.edu.sumdu.j2se.astakhov.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.astakhov.tasks.view.ChangeTaskView;
import ua.edu.sumdu.j2se.astakhov.tasks.view.View;

import java.time.LocalDateTime;

import static ua.edu.sumdu.j2se.astakhov.tasks.controller.Errors.*;

/**
 * Class ChangeTaskController realizes changes parameters for Tasks.
 *
 * @author Астахов Дмитрій
 */

public class ChangeTaskController extends Controller {

    private static final Logger logger = Logger.getLogger(ChangeTaskController.class);

    /**
     * Constructor ChangeTaskController.
     *
     * @param view of type View
     * @param actionToDo of type int
     */

    public ChangeTaskController(View view, int actionToDo) {
        super(view, actionToDo);
    }

    /**
     * Method process realizes changes parameters for Tasks.
     *
     * @param abstractTaskList of type AbstractTaskList
     * @return shows the result of your choose
     */


    @Override
    public int process(AbstractTaskList abstractTaskList) {
        int taskChoose = ((ChangeTaskView) view).taskChoose();
        if(taskChoose == 1) {
            int index = ((ChangeTaskView) view).index();
            if(index == Integer.MAX_VALUE || abstractTaskList.size() <= 0 || abstractTaskList.size() - 1 < index) {
                logger.error(UNEXPECTED_INDEX);
                view.exception(UNEXPECTED_INDEX);
                return CHANGE_TASK;
            }
            if(abstractTaskList.getTask(index).isRepeated()) {
                int taskChooseRepeatable = ((ChangeTaskView) view).taskChooseRepeatable();
                if(taskChooseRepeatable == 1) {
                    String newTitle = ((ChangeTaskView) view).newTitle();
                    abstractTaskList.getTask(index).setTitle(newTitle);
                } else if(taskChooseRepeatable == 2) {
                    LocalDateTime start = ((ChangeTaskView) view).start();
                    if(start.isBefore(LocalDateTime.now())) {
                        timeErrors();
                        return CHANGE_TASK;
                    }
                    LocalDateTime end = ((ChangeTaskView) view).end();
                    if(end.isBefore(LocalDateTime.now())) {
                        timeErrors();
                        return CHANGE_TASK;
                    }
                    abstractTaskList.getTask(index).setStart(start);
                    abstractTaskList.getTask(index).setEnd(end);
                } else if(taskChooseRepeatable == 3) {
                    int interval = ((ChangeTaskView) view).interval();
                    if(interval == Integer.MAX_VALUE || interval <= 0) {
                        logger.error(UNEXPECTED_INTERVAL);
                        view.exception(UNEXPECTED_INTERVAL);
                        return CHANGE_TASK;
                    }
                    abstractTaskList.getTask(index).setRepeatInterval(interval);
                } else if(taskChooseRepeatable == 4) {
                    return CHANGE_TASK;
                } else {
                    logger.error(WRONG_NUMBER);
                    view.exception(WRONG_NUMBER);
                    return CHANGE_TASK;
                }
            } else if(!abstractTaskList.getTask(index).isRepeated()) {
                int taskChooseNotRepeatable = ((ChangeTaskView) view).taskChooseNotRepeatable();
                if(taskChooseNotRepeatable == 1) {
                    String newTitle = ((ChangeTaskView) view).newTitle();
                    abstractTaskList.getTask(index).setTitle(newTitle);
                } else if(taskChooseNotRepeatable == 2) {
                    LocalDateTime time = ((ChangeTaskView) view).time();
                    if(time.isBefore(LocalDateTime.now())) {
                        timeErrors();
                        return CHANGE_TASK;
                    }
                    abstractTaskList.getTask(index).setTime(time);
                    return CHANGE_TASK;
                } else if(taskChooseNotRepeatable == 3) {
                    return CHANGE_TASK;
                } else {
                    logger.error(WRONG_NUMBER);
                    view.exception(WRONG_NUMBER);
                    return CHANGE_TASK;
                }
            }
        } else if(taskChoose == 2) {
            return MAIN_MENU;
        } else {
            logger.error(WRONG_NUMBER);
            view.exception(WRONG_NUMBER);
            return CHANGE_TASK;
        }
        return view.printInfo(abstractTaskList);
    }

    /**
     *  Method timeErrors describes a time error.
     */

    public void timeErrors()  {
        logger.error(UNEXPECTED_TIME);
    }
}
