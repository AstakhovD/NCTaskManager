package ua.edu.sumdu.j2se.astakhov.tasks.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.astakhov.tasks.view.RemoveTaskView;
import ua.edu.sumdu.j2se.astakhov.tasks.view.View;

import static ua.edu.sumdu.j2se.astakhov.tasks.controller.Errors.UNEXPECTED_INDEX;
import static ua.edu.sumdu.j2se.astakhov.tasks.controller.Errors.WRONG_NUMBER;

/**
 * Class RemoveTaskController realizes remove task from list.
 *
 * @author Астахов Дмитрій
 */

public class RemoveTaskController extends Controller {

    private static final Logger logger = LogManager.getLogger(RemoveTaskController.class);

    /**
     * Constructor RemoveTaskController
     *
     * @param view of type View
     * @param actionToDo of type int
     */

    public RemoveTaskController(View view, int actionToDo) {
        super(view, actionToDo);
    }

    /**
     * Method process realizes remove task from list.
     *
     * @param abstractTaskList of type AbstractTaskList
     * @return back to menu "REMOVE_TASK" if we choose 1 or back to main menu if we choose 2
     */

    @Override
    public int process(AbstractTaskList abstractTaskList)  {
        int taskChoose = ((RemoveTaskView) view).taskChoose();
        if(taskChoose == 1) {
            int index = ((RemoveTaskView) view).removeTask();
            if(index >= abstractTaskList.size() || abstractTaskList.size() - 1 < index) {
                logger.error(UNEXPECTED_INDEX);
                view.exception(UNEXPECTED_INDEX);
                return REMOVE_TASK;
            }
            abstractTaskList.remove(abstractTaskList.getTask(index));
            return view.printInfo(abstractTaskList);
        }
     else if(taskChoose == 2) {
            return MAIN_MENU;
        } else {
            logger.error(WRONG_NUMBER);
            view.exception(WRONG_NUMBER);
            return REMOVE_TASK;
        }
    }
}

