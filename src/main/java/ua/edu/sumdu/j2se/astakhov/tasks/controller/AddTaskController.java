package ua.edu.sumdu.j2se.astakhov.tasks.controller;



import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.astakhov.tasks.model.Task;
import ua.edu.sumdu.j2se.astakhov.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.astakhov.tasks.view.AddTaskView;
import ua.edu.sumdu.j2se.astakhov.tasks.view.View;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import static ua.edu.sumdu.j2se.astakhov.tasks.controller.Errors.*;

/**
 * Class AddTaskController realizes add new task.
 *
 * @author Астахов Дмитрій
 */

public class AddTaskController extends Controller {

    private static final Logger logger = LogManager.getLogger(AddTaskController.class);

    /**
     * Constructor AddTaskController
     *
     * @param view of type View
     * @param actionToDo of type int
     */

    public AddTaskController(View view, int actionToDo) {
        super(view, actionToDo);
    }

    /**
     * Method process realizes add new Task.
     *
     * @param abstractTaskList of type AbstractTaskList
     * @return back to the "ADD_TASK" menu if you choose 1 or 2.
     * If you choose 3 - return back to the "MAIN_MENU" menu
     */

    @Override
    public int process(AbstractTaskList abstractTaskList) {
        int taskChoose = ((AddTaskView) view).taskChoose();
        if(taskChoose == 1) {
            String taskTitle = ((AddTaskView) view).taskTitle();
            LocalDateTime start = ((AddTaskView) view).startTime();
            LocalDateTime end = ((AddTaskView) view).endTime();
            int interval = ((AddTaskView) view).repeatableOfTask();
            if(start.isBefore(LocalDateTime.now())) {
                logger.error(UNEXPECTED_START_TIME);
                view.exception(UNEXPECTED_START_TIME);
                return ADD_TASK;
            }
            if(end.isBefore(LocalDateTime.now())) {
                logger.error(UNEXPECTED_END_TIME);
                view.exception(UNEXPECTED_END_TIME);
                return ADD_TASK;
            }
            Task task = new Task(taskTitle, start, end, interval);
            task.setActive(true);
            abstractTaskList.add(task);
            saveTasks(abstractTaskList);
            return view.printInfo(abstractTaskList);
        } else if(taskChoose == 2) {
            String taskTitle = ((AddTaskView) view).taskTitle();
            LocalDateTime time = ((AddTaskView) view).taskTime();
            if(time.isBefore(LocalDateTime.now())) {
                logger.error(UNEXPECTED_TIME);
                view.exception(UNEXPECTED_TIME);
                return ADD_TASK;
            }
            Task task = new Task(taskTitle, time);
            task.setActive(true);
            abstractTaskList.add(task);
            saveTasks(abstractTaskList);
            return view.printInfo(abstractTaskList);
        } else if(taskChoose == 3) {
            return MAIN_MENU;
        } else {
            logger.error(WRONG_NUMBER);
            view.exception(WRONG_NUMBER);
            return ADD_TASK;
        }
    }

    /**
     * Method saveTasks realizes autosaves of Tasks.
     *
     * @param abstractTaskList of type AbstractTaskList
     */

    private void saveTasks(AbstractTaskList abstractTaskList) {
        try {
            File file = new File("saves");
            file.mkdir();
            TaskIO.write(abstractTaskList, new FileWriter(new File("saves/autoSave.json")));
        } catch (IOException e) {
            logger.error(FILE_NOT_FOUND);
            System.out.println(FILE_NOT_FOUND);
        }
    }
}
