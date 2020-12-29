package ua.edu.sumdu.j2se.astakhov.tasks.controller;

import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.astakhov.tasks.view.RemoveTask;
import ua.edu.sumdu.j2se.astakhov.tasks.view.View;

public class RemoveTaskContrl extends Controller {

    public RemoveTaskContrl(View view, int actionToDo) {
        super(view, actionToDo);
    }

    @Override
    public int process(AbstractTaskList abstractTaskList) {
        int taskChoose = ((RemoveTask) view).taskChoose();
        if(taskChoose == 1) {
            int index = ((RemoveTask) view).removeTask();
            if(index >= abstractTaskList.size()) {
                System.out.println("Ошибка: задачи с таким индексом не существует");
                return Controller.REMOVE_TASK;
            }
            abstractTaskList.remove(abstractTaskList.getTask(index));
        } else if(taskChoose == 2) {
            return Controller.MAIN_MENU;
        } else {
            System.out.println("Ошибка: Вы выбрали неправильную цифру");
            return Controller.REMOVE_TASK;
        }
        return view.printInfo(abstractTaskList);
    }
}

