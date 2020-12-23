package ua.edu.sumdu.j2se.astakhov.tasks.controller;


import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.astakhov.tasks.model.Task;
import ua.edu.sumdu.j2se.astakhov.tasks.view.AddTask;
import ua.edu.sumdu.j2se.astakhov.tasks.view.View;

import java.time.LocalDateTime;

public class AddTaskContrl extends Controller {

    public AddTaskContrl(View view, int actionToDo) {
        super(view, actionToDo);
    }

    @Override
    public int process(AbstractTaskList abstractTaskList) {
        int taskChoose = ((AddTask)view).taskChoose();
        if(taskChoose == 1) {
            String taskTitle = ((AddTask)view).taskTitle();
            LocalDateTime time = ((AddTask)view).taskTime();
            Task task = new Task(taskTitle, time);
            abstractTaskList.add(task);
        } else if(taskChoose == 2) {
            String taskTitle = ((AddTask)view).taskTitle();
            LocalDateTime timeStart = ((AddTask)view).startTime();
            LocalDateTime timeEnd = ((AddTask)view).endTime();
            int interval = ((AddTask)view).repeatableOfTask();
            Task task2 = new Task(taskTitle, timeStart, timeEnd, interval);
            abstractTaskList.add(task2);
        } else {
            System.out.println("Ошибка: Вы ввели неверное число");
            return Controller.MAIN_MENU;
        }
        return view.printInfo(abstractTaskList);
    }
}
