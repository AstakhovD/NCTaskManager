package ua.edu.sumdu.j2se.astakhov.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.astakhov.tasks.view.RemoveTask;
import ua.edu.sumdu.j2se.astakhov.tasks.view.View;

public class RemoveTaskController extends Controller {

    private static final Logger logger = Logger.getLogger(NotificationController.class);

    public RemoveTaskController(View view, int actionToDo) {
        super(view, actionToDo);
    }

    @Override
    public int process(AbstractTaskList abstractTaskList) {
        int taskChoose = ((RemoveTask) view).taskChoose();
        if(taskChoose == 1) {
            int index = ((RemoveTask) view).removeTask();
            if(index >= abstractTaskList.size() || index == Integer.MAX_VALUE || abstractTaskList.size() - 1 < index) {
                logger.error("Ошибка: задачи с таким номером не существует");
                System.out.println("Ошибка: задачи с таким номером не существует");
                return REMOVE_TASK;
            }
            abstractTaskList.remove(abstractTaskList.getTask(index));
        } else if(taskChoose == 2) {
            return Controller.MAIN_MENU;
        } else {
            logger.error("Ошибка: Вы ввели неверное число");
            System.out.println("Ошибка: Вы ввели неверное число");
            return Controller.REMOVE_TASK;
        }
        return view.printInfo(abstractTaskList);
    }
}

