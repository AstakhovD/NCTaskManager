package ua.edu.sumdu.j2se.astakhov.tasks.controller;

import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.astakhov.tasks.view.View;

import java.io.IOException;

public abstract class Controller {
    public static final int MAIN_MENU = 0;
    public static final int TASK_LIST = 1;
    public static final int ADD_TASK = 2;
    public static final int REMOVE_TASK = 3;
    public static final int CALENDAR = 4;
    public static final int SAVE_TASK = 5;
    public static final int ACTIVE_TASK = 6;
    public static final int CHANGE_TASK = 7;
    public static final int EXIT = 8;

    protected View view;
    protected int actionToDo = 0;

    public Controller(View view, int actionToDo) {
        this.view = view;
        this.actionToDo = actionToDo;
    }

    public boolean canProcess(int action) {
        return action == actionToDo;
    }

    public abstract int process(AbstractTaskList abstractTaskList) throws IOException;
}
