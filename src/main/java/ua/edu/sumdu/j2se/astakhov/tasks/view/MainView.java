package ua.edu.sumdu.j2se.astakhov.tasks.view;

import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import java.io.IOException;

public class MainView implements View{

    @Override
    public int printInfo(AbstractTaskList abstractTaskList) {
        System.out.println("Выберите что нужно сделать");
        System.out.println("1 - просмотреть задачи");
        System.out.println("2 - добавить задачу");
        System.out.println("3 - удалить задачу");
        System.out.println("4 - календарь");
        System.out.println("5 - выйти из программы");
        int taskManager = 0;
        try {
            taskManager = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskManager;
    }
}
