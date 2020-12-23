package ua.edu.sumdu.j2se.astakhov.tasks.view;

import ua.edu.sumdu.j2se.astakhov.tasks.controller.Controller;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;

public class CreateTaskListView implements View {
    @Override
    public int printInfo(AbstractTaskList abstractTaskList) {
        System.out.println("Просмотр задач");
        for (int i = 0; i < abstractTaskList.size(); i++) {
            System.out.println(i + "." + abstractTaskList.getTask(i));
        }
        return Controller.MAIN_MENU;
    }
}
