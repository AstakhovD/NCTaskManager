package ua.edu.sumdu.j2se.astakhov.tasks.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;

import java.io.IOException;

import static ua.edu.sumdu.j2se.astakhov.tasks.controller.Controller.REMOVE_TASK;
import static ua.edu.sumdu.j2se.astakhov.tasks.controller.Errors.UNEXPECTED_INDEX;
import static ua.edu.sumdu.j2se.astakhov.tasks.controller.Errors.WRONG_NUMBER;

/**
 * Class RemoveTaskView realizes remove task from list.
 *
 * @author Астахов Дмитрій
 */

public class RemoveTaskView implements View, TaskChoose {

    private static final Logger logger = LogManager.getLogger(RemoveTaskView.class);

    /**
     * Method printInfo shows result.
     *
     * @param abstractTaskList of type AbstractTaskList
     * @return back to the "REMOVE_TASK" menu
     */

    @Override
    public int printInfo(AbstractTaskList abstractTaskList) {
        System.out.println("Задача была удалена");
        return REMOVE_TASK;
    }

    /**
     * Method removeTask realizes remove task from list.
     *
     * @return task of this object
     */

    public int removeTask() {
        System.out.println("Укажите номер задачи");
            int task = 0;
            try {
                String taskName = bufferedReader.readLine();
                task = Integer.parseInt(taskName);
                if (task < 0) {
                    System.out.println(UNEXPECTED_INDEX);
                    return removeTask();
                }
            } catch (IOException e) {
                logger.error(UNEXPECTED_INDEX);
            }
            return task;
    }

    /**
     * Method taskChoose - returns the taskType of Task
     *
     * @return taskType of this object
     */

    @Override
    public int taskChoose() {
        System.out.println("Удалить задачу?");
        System.out.println("1 - удалить");
        System.out.println("2 - вернуться в главное меню");
        int taskType = 0;
        try {
            String taskName = bufferedReader.readLine();
            taskType = Integer.parseInt(taskName);
        } catch (IOException e) {
            System.out.println(WRONG_NUMBER);
        }
        return taskType;
    }
}
