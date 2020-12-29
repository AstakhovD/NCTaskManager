package ua.edu.sumdu.j2se.astakhov.tasks.controller;


import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.astakhov.tasks.model.Task;
import ua.edu.sumdu.j2se.astakhov.tasks.view.AddTask;
import ua.edu.sumdu.j2se.astakhov.tasks.view.View;

import java.io.IOException;
import java.time.LocalDateTime;

public class AddTaskContrl extends Controller {

    public AddTaskContrl(View view, int actionToDo) {
        super(view, actionToDo);
    }

    @Override
    public int process(AbstractTaskList abstractTaskList) throws IOException {
        int taskChoose = ((AddTask) view).taskChoose();
        if(taskChoose == 1) {
            String taskTitle = ((AddTask) view).taskTitle();
            LocalDateTime time = ((AddTask) view).taskTime();
            if(time.isBefore(LocalDateTime.now())) {
                System.out.println("Ошибка: неожиданное время");
                return Controller.ADD_TASK;
            }
            Task task = new Task(taskTitle, time);
            abstractTaskList.add(task);
        } else if(taskChoose == 2) {
            String taskTitle = ((AddTask) view).taskTitle();
            LocalDateTime timeStart = ((AddTask) view).startTime();
            LocalDateTime timeEnd = ((AddTask) view).endTime();
            int interval = ((AddTask) view).repeatableOfTask();
            if(timeStart.isBefore(LocalDateTime.now())) {
                System.out.println("Ошибка: неожиданное время");
                return Controller.ADD_TASK;
            }
            if((timeEnd.isBefore(LocalDateTime.now()))) {
                System.out.println("Ошибка: неожиданное время");
                return Controller.ADD_TASK;
            }
            if(interval == Integer.MAX_VALUE) {
                System.out.println("Ошибка: неожиданный интервал");
                return Controller.ADD_TASK;
            }
            Task task2 = new Task(taskTitle, timeStart, timeEnd, interval);
            abstractTaskList.add(task2);
        } else {
            System.out.println("Ошибка: Вы ввели неверное число");
            return Controller.ADD_TASK;
        }
        return view.printInfo(abstractTaskList);
    }
}
