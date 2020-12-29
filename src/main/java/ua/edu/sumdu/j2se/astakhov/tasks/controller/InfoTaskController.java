package ua.edu.sumdu.j2se.astakhov.tasks.controller;

import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.astakhov.tasks.view.InfoTaskView;
import ua.edu.sumdu.j2se.astakhov.tasks.view.View;

import java.io.IOException;
import java.time.LocalDateTime;

public class InfoTaskController extends Controller {

    public InfoTaskController(View view, int actionToDo) {
        super(view, actionToDo);
    }

    @Override
    public int process(AbstractTaskList abstractTaskList) throws IOException {
        int index;
        int taskChoose = ((InfoTaskView) view).taskChoose();
        if(taskChoose == 1) {
            index = ((InfoTaskView) view).index();
            if(index >= abstractTaskList.size()) {
                System.out.println("Ошибка: задачи с таким индексом не существует");
                return Controller.INFO_TASK;
            }
            System.out.println(abstractTaskList.getTask(index));
            return Controller.INFO_TASK;
        } else if(taskChoose == 2) {
            index = ((InfoTaskView) view).index();
            if(index >= abstractTaskList.size()) {
                System.out.println("Ошибка: задачи с таким индексом не существует");
                return Controller.INFO_TASK;
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
                    System.out.println("Ошибка: Вы ввели неверное число");
                    return Controller.INFO_TASK;
                }
            }
        }
        return view.printInfo(abstractTaskList);
    }
}
