package ua.edu.sumdu.j2se.astakhov.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;

import java.io.IOException;

import static ua.edu.sumdu.j2se.astakhov.tasks.controller.Controller.MAIN_MENU;
import static ua.edu.sumdu.j2se.astakhov.tasks.controller.Errors.UNEXPECTED_INDEX;
import static ua.edu.sumdu.j2se.astakhov.tasks.controller.Errors.WRONG_NUMBER;

/**
 * Class InfoTaskView realizes change task activity.
 *
 * @author Астахов Дмитрій
 */

public class InfoTaskView implements View {

    private static final Logger logger = Logger.getLogger(InfoTaskView.class);

    /**
     * Method printInfo shows result.
     *
     * @param abstractTaskList of type AbstractTaskList
     * @return back to the "MAIN_MENU" menu
     */

    @Override
    public int printInfo(AbstractTaskList abstractTaskList) {
        System.out.println("Информация о задачах закрыта");
        return MAIN_MENU;
    }

    /**
     * Method taskChoose - returns the taskType of Task.
     *
     * @return taskType of this object
     */

    public int taskChoose() {
        System.out.println("Информация о задачах");
        System.out.println("Выберите действие");
        System.out.println("1 - установить режим задачи");
        System.out.println("2 - вернуться в главное меню");
        int taskType = 0;
        try {
            String taskName = bufferedReader.readLine();
            taskType = Integer.parseInt(taskName);
        } catch (IOException e) {
            logger.error(WRONG_NUMBER);
            System.out.println(WRONG_NUMBER);
            return taskChoose();
        }
        return taskType;
    }

    /**
     * Method index - returns the index of Task.
     *
     * @return index of this object
     */

    public int index() {
        int index = 0;
        try {
            System.out.println("Введите номер задачи");
                String taskName = bufferedReader.readLine();
                index = Integer.parseInt(taskName);
            } catch (IOException e) {
            logger.error(UNEXPECTED_INDEX);
            System.out.println(UNEXPECTED_INDEX);
            return index();
            }
            return index;
    }

    /**
     * Method activityMode - returns the mode of Task.
     *
     * @return mode of this object
     */

    public int activityMode() {
        int mode = 0;
        try {
            System.out.println("Установить режим");
            System.out.println("1 - активный режим");
            System.out.println("2 - режим ожидания");
            String taskName = bufferedReader.readLine();
            mode = Integer.parseInt(taskName);
        } catch (IOException e) {
            logger.error(WRONG_NUMBER);
            System.out.println(WRONG_NUMBER);
            return activityMode();
        }
        return mode;
    }
}

