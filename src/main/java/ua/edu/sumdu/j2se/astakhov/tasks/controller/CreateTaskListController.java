package ua.edu.sumdu.j2se.astakhov.tasks.controller;

import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.astakhov.tasks.view.View;

import java.io.IOException;

public class CreateTaskListController extends Controller {

    @Override
    public int process(AbstractTaskList abstractTaskList) throws IOException {
        return view.printInfo(abstractTaskList);
    }

    public CreateTaskListController(View view, int actionToDo) {
        super(view, actionToDo);
    }
}
