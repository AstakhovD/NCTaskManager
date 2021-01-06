package ua.edu.sumdu.j2se.astakhov.tasks.view;

import ua.edu.sumdu.j2se.astakhov.tasks.controller.Controller;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;

import java.io.IOException;

public class RemoveTask implements View, TaskChoose {
    @Override
    public int printInfo(AbstractTaskList abstractTaskList) {
        System.out.println("Задача удалена");
        return Controller.MAIN_MENU;
    }

    public int removeTask() {
        System.out.println("Укажите номер задачи");
        int task = 0;
        try {
            String taskName = bufferedReader.readLine();
            task = Integer.parseInt(taskName);
        } catch (IOException e ) {
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public int taskChoose() {
        System.out.println("Удалить задачу?");
        System.out.println("1 - удалить");
        System.out.println("2 - вернуться в главное меню");
        int task = 0;
        try {
            String taskName = bufferedReader.readLine();
            task = Integer.parseInt(taskName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return task;
    }
}
