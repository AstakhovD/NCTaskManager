package ua.edu.sumdu.j2se.astakhov.tasks.view;

import ua.edu.sumdu.j2se.astakhov.tasks.controller.Controller;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;

import java.io.IOException;

public class SaveAndLoadTaskView implements View, TaskChoose {

    int checkYourChoose;

    @Override
    public int printInfo(AbstractTaskList abstractTaskList) {
        if(checkYourChoose == 1) {
            System.out.println("Задача была сохранена");
            return Controller.MAIN_MENU;
        } else {
            System.out.println("Задача была загружена");
        }
        return Controller.MAIN_MENU;
    }

    public String fileName() {
        String file = "";
        try {
            System.out.println("Введите имя файла");
            file = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public int taskChoose() {
        System.out.println("Выберите что нужно сделать");
        System.out.println("1 - сохранить задачу");
        System.out.println("2 - загрузить задачу");
        System.out.println("3 - Вернуться в главное меню");
        int task = 0;
        try {
            String taskName = bufferedReader.readLine();
            task = Integer.parseInt(taskName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.checkYourChoose = task;
        return task;
    }
}
