package ua.edu.sumdu.j2se.astakhov.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import java.io.IOException;

import static ua.edu.sumdu.j2se.astakhov.tasks.controller.Errors.WRONG_NUMBER;

/**
 * Class AddTaskView realizes main view.
 *
 * @author Астахов Дмитрій
 */

public class MainView implements View {

    private static final Logger logger = Logger.getLogger(MainView.class);

    /**
     * Method printInfo displays functionality of application.
     *
     * @param abstractTaskList of type AbstractTaskList
     * @return taskManager of this object
     */

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
            logger.error(WRONG_NUMBER);
            System.out.println(WRONG_NUMBER);
            return printInfo(abstractTaskList);
        }
        return taskManager;
    }
}
