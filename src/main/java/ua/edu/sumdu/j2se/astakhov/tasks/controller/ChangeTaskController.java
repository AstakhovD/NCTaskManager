package ua.edu.sumdu.j2se.astakhov.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.astakhov.tasks.view.ChangeTaskView;
import ua.edu.sumdu.j2se.astakhov.tasks.view.View;

import java.io.IOException;
import java.time.LocalDateTime;


public class ChangeTaskController extends Controller {

    private static final Logger logger = Logger.getLogger(NotificationController.class);

    public ChangeTaskController(View view, int actionToDo) {
        super(view, actionToDo);
    }
    @Override
    public int process(AbstractTaskList abstractTaskList) throws IOException {
        int taskChoose = ((ChangeTaskView) view).taskChoose();
        if(taskChoose == 1) {
            int index = ((ChangeTaskView) view).index();
            if(index == Integer.MAX_VALUE || abstractTaskList.size() <= 0 || abstractTaskList.size() - 1 < index) {
                logger.error("Ошибка: Неожиданный индекс");
                System.out.println("Ошибка: Неожиданный индекс");
                return CHANGE_TASK;
            }
            if(abstractTaskList.getTask(index).isRepeated()) {
                int taskChooseRepeatable = ((ChangeTaskView) view).taskChooseRepeatable();
                if(taskChooseRepeatable == 1) {
                    String newTitle = ((ChangeTaskView) view).newTitle();
                    abstractTaskList.getTask(index).setTitle(newTitle);
                } else if(taskChooseRepeatable == 2) {
                    LocalDateTime start = ((ChangeTaskView) view).start();
                    if(start.isBefore(LocalDateTime.now())) {
                        timeErrors();
                        return CHANGE_TASK;
                    }
                    LocalDateTime end = ((ChangeTaskView) view).end();
                    if(end.isBefore(LocalDateTime.now())) {
                        timeErrors();
                        return CHANGE_TASK;
                    }
                    abstractTaskList.getTask(index).setStart(start);
                    abstractTaskList.getTask(index).setEnd(end);
                } else if(taskChooseRepeatable == 3) {
                    int interval = ((ChangeTaskView) view).interval();
                    if(interval == Integer.MAX_VALUE || interval <= 0) {
                        logger.error("Ошибка: Неожиданный интервал");
                        System.out.println("Ошибка: Неожиданный интервал");
                        return CHANGE_TASK;
                    }
                    abstractTaskList.getTask(index).setRepeatInterval(interval);
                } else if(taskChooseRepeatable == 4) {
                    return CHANGE_TASK;
                } else {
                    System.out.println("Ошибка: Вы ввели неверное число");
                    return CHANGE_TASK;
                }
            } else if(!abstractTaskList.getTask(index).isRepeated()) {
                int taskChooseNotRepeatable = ((ChangeTaskView) view).taskChooseNotRepeatable();
                if(taskChooseNotRepeatable == 1) {
                    String newTitle = ((ChangeTaskView) view).newTitle();
                    abstractTaskList.getTask(index).setTitle(newTitle);
                } else if(taskChooseNotRepeatable == 2) {
                    LocalDateTime time = ((ChangeTaskView) view).time();
                    if(time.isBefore(LocalDateTime.now())) {
                        timeErrors();
                        return CHANGE_TASK;
                    }
                    abstractTaskList.getTask(index).setTime(time);
                    return CHANGE_TASK;
                } else if(taskChooseNotRepeatable == 3) {
                    return CHANGE_TASK;
                } else {
                    System.out.println("Ошибка: Вы ввели неверное число");
                    return CHANGE_TASK;
                }
            }
        } else if(taskChoose == 2) {
            return MAIN_MENU;
        }
        return view.printInfo(abstractTaskList);
    }

    public void timeErrors()  {
        logger.error("Ошибка: Неожиданное время");
        System.out.println("Ошибка: Неожиданное время");
    }
}
