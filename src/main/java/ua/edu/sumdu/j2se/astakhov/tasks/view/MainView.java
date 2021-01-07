package ua.edu.sumdu.j2se.astakhov.tasks.view;

import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import java.io.IOException;

public class MainView implements View{

    @Override
    public int printInfo(AbstractTaskList abstractTaskList) {
        System.out.println("Выберите что нужно сделать");
        System.out.println("1 - Просмотреть задачи");
        System.out.println("2 - Добавить новую задачу");
        System.out.println("3 - Удалить задачу");
        System.out.println("4 - Календарь");
        System.out.println("5 - Сохранить/загрузить задачу");
        System.out.println("6 - Изменение активность задачи");
        System.out.println("7 - Редактирование задач");
        System.out.println("8 - Выйти из программы");
        int taskManager;
        try {
            taskManager = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            return 0;
        }
        return taskManager;
    }
}
