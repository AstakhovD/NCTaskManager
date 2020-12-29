package ua.edu.sumdu.j2se.astakhov.tasks.view;

import ua.edu.sumdu.j2se.astakhov.tasks.controller.Controller;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;

import java.io.IOException;

public class InfoTaskView implements View {

    @Override
    public int printInfo(AbstractTaskList abstractTaskList) {
        System.out.println("Информация о задачах закрыта");
        return Controller.MAIN_MENU;
    }

    public int taskChoose() {
        System.out.println("Информация о задачах");
        System.out.println("Выберите действие");
        System.out.println("1 - просмотреть задачу по индексу");
        System.out.println("2 - установить активной/неактивной задачу");
        System.out.println("3 - вернуться в главное меню");
        int task = 0;
        try {
            String taskName = bufferedReader.readLine();
            task = Integer.parseInt(taskName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return task;
    }

    public int index() {
        int index = 0;
        try {
            System.out.println("Выберите задачу по индексу");
                String taskName = bufferedReader.readLine();
                index = Integer.parseInt(taskName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return index;
    }

    public int activityMode() {
        int mode = 0;
        try {
            System.out.println("Установить режим");
            System.out.println("1 - активной");
            System.out.println("2 - неактивной");
            String taskName = bufferedReader.readLine();
            mode = Integer.parseInt(taskName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mode;
    }
}

