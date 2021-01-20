package ua.edu.sumdu.j2se.astakhov.tasks.controller;

import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.astakhov.tasks.view.View;

/**
 * Class CreateTaskListController realizes create task list.
 *
 * @author Астахов Дмитрій
 */

public class CreateTaskListController extends Controller {

    /**
     * Method process realizes create task list.
     *
     * @param abstractTaskList of type AbstractTaskList
     * @return shows result
     */

    @Override
    public int process(AbstractTaskList abstractTaskList) {
        return view.printInfo(abstractTaskList);
    }

    /**
     * Constructor CreateTaskListController
     *
     * @param view of type View
     * @param actionToDo of type int
     */

    public CreateTaskListController(View view, int actionToDo) {
        super(view, actionToDo);
    }
}
