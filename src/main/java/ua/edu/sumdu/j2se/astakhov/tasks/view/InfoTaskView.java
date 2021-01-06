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
        System.out.println("1 - установить режим задачи");
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

    public int index() {
        int index = 0;
        try {
            System.out.println("Введите номер задачи");
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
            System.out.println("1 - активный режим");
            System.out.println("2 - режим ожидания");
            String taskName = bufferedReader.readLine();
            mode = Integer.parseInt(taskName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mode;
    }
}

