package ua.edu.sumdu.j2se.astakhov.tasks.controller;


import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.astakhov.tasks.model.Task;
import ua.edu.sumdu.j2se.astakhov.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.astakhov.tasks.view.AddTask;
import ua.edu.sumdu.j2se.astakhov.tasks.view.View;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class AddTaskController extends Controller {

    private static final Logger logger = Logger.getLogger(AddTaskController.class);

    public AddTaskController(View view, int actionToDo) {
        super(view, actionToDo);
    }

    @Override
    public int process(AbstractTaskList abstractTaskList) throws IOException {
        int taskChoose = ((AddTask) view).taskChoose();
        if(taskChoose == 1) {
            String taskName = ((AddTask) view).taskTitle();
            LocalDateTime start = ((AddTask) view).startTime();
            LocalDateTime end = ((AddTask) view).endTime();
            int interval = ((AddTask) view).repeatableOfTask();
            if(start.isBefore(LocalDateTime.now())) {
                logger.error("Ошибка: неожиданное время начала задачи");
                System.out.println("Ошибка: неожиданное время начала задачи");
                return Controller.ADD_TASK;
            }
            if(end.isBefore(LocalDateTime.now())) {
                logger.error("Ошибка: неожиданное время окончания задачи");
                System.out.println("Ошибка: неожиданное время окончания задачи");
                return Controller.ADD_TASK;
            }
            if(interval == Integer.MAX_VALUE || interval <= 0) {
                logger.error("Ошибка: неожиданный интервал выполнения задачи");
                System.out.println("Ошибка: неожиданный интервал выполнения задачи");
                return Controller.ADD_TASK;
            }
            Task task = new Task(taskName, start, end, interval);
            task.setActive(true);
            abstractTaskList.add(task);
            saveTasks(abstractTaskList);
            return MAIN_MENU;
        } else if(taskChoose == 2) {
            String taskName = ((AddTask) view).taskTitle();
            LocalDateTime time = ((AddTask) view).taskTime();
            if(time.isBefore(LocalDateTime.now())) {
                logger.error("Ошибка: неожиданное время");
                System.out.println("Ошибка: неожиданное время");
                return Controller.ADD_TASK;
            }
            Task task = new Task(taskName, time);
            task.setActive(true);
            abstractTaskList.add(task);
            saveTasks(abstractTaskList);
            return MAIN_MENU;
        } else if(taskChoose == 3) {
            return Controller.MAIN_MENU;
        }
        return view.printInfo(abstractTaskList);
    }

    private void saveTasks(AbstractTaskList abstractTaskList) {
        try {
            File file = new File("saves");
            file.mkdir();
            TaskIO.write(abstractTaskList, new FileWriter(new File("saves/autoSave.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
