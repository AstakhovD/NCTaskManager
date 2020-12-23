package ua.edu.sumdu.j2se.astakhov.tasks.controller;

import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.astakhov.tasks.view.RemoveTask;
import ua.edu.sumdu.j2se.astakhov.tasks.view.View;

public class RemoveTaskContrl extends Controller {

    public RemoveTaskContrl(View view, int actionToDo) {
        super(view, actionToDo);
    }

    @Override
    public int process(AbstractTaskList abstractTaskList) {
        int index = ((RemoveTask)view).removeTask();
        abstractTaskList.remove(abstractTaskList.getTask(index));
        return view.printInfo(abstractTaskList);
    }
}

