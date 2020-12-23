package ua.edu.sumdu.j2se.astakhov.tasks.view;

import ua.edu.sumdu.j2se.astakhov.tasks.controller.Controller;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;

import java.io.IOException;

public class RemoveTask implements View {
    @Override
    public int printInfo(AbstractTaskList abstractTaskList) {
        System.out.println("Задача удалена");
        return Controller.MAIN_MENU;
    }

    public int removeTask() {
        System.out.println("Укажите задачу");
        int task = 0;
        try {
            String taskName = bufferedReader.readLine();
            task = Integer.parseInt(taskName);
        } catch (IOException e ) {
            e.printStackTrace();
        }
        return task;
    }
}
