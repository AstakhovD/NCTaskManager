package ua.edu.sumdu.j2se.astakhov.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.astakhov.tasks.view.InfoTaskView;
import ua.edu.sumdu.j2se.astakhov.tasks.view.View;

import java.io.IOException;
import java.time.LocalDateTime;

public class InfoTaskController extends Controller {

    private static final Logger logger = Logger.getLogger(NotificationController.class);

    public InfoTaskController(View view, int actionToDo) {
        super(view, actionToDo);
    }

    @Override
    public int process(AbstractTaskList abstractTaskList) throws IOException {
        int index;
        int taskChoose = ((InfoTaskView) view).taskChoose();
        if(taskChoose == 1) {
            index = ((InfoTaskView) view).index();
            if(index >= abstractTaskList.size() || index == Integer.MAX_VALUE || abstractTaskList.size() - 1 < index) {
                logger.error("Ошибка: задачи с таким номером не существует");
                System.out.println("Ошибка: задачи с таким номером не существует");
                return Controller.ACTIVE_TASK;
            } else {
                int mode = ((InfoTaskView) view).activityMode();
                if(mode == 1) {
                    abstractTaskList.getTask(index).setActive(true);
                    if((abstractTaskList.getTask(index).getEndTime()).isBefore(LocalDateTime.now())) {
                        abstractTaskList.remove(abstractTaskList.getTask(index));
                    }
                } else if(mode == 2) {
                    abstractTaskList.getTask(index).setActive(false);
                    if((abstractTaskList.getTask(index).getEndTime()).isBefore(LocalDateTime.now())) {
                        abstractTaskList.remove(abstractTaskList.getTask(index));
                    }
                } else {
                    logger.error("Ошибка: Вы ввели неверное число");
                    System.out.println("Ошибка: Вы ввели неверное число");
                    return ACTIVE_TASK;
                }
            }
        } else if(taskChoose == 2) {
            return MAIN_MENU;
        }
        return view.printInfo(abstractTaskList);
    }
}
