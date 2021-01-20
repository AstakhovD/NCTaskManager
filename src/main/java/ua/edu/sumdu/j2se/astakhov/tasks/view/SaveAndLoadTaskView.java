package ua.edu.sumdu.j2se.astakhov.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;

import java.io.IOException;

import static ua.edu.sumdu.j2se.astakhov.tasks.controller.Controller.MAIN_MENU;
import static ua.edu.sumdu.j2se.astakhov.tasks.controller.Errors.FILE_NOT_FOUND;
import static ua.edu.sumdu.j2se.astakhov.tasks.controller.Errors.WRONG_NUMBER;

/**
 * Class SaveAndLoadTaskView realizes save/load task in file.
 *
 * @author Астахов Дмитрій
 */

public class SaveAndLoadTaskView implements View, TaskChoose {

    int checkYourChoose;

    private static final Logger logger = Logger.getLogger(SaveAndLoadTaskView.class);

    /**
     * Method printInfo shows result.
     *
     * @param abstractTaskList of type AbstractTaskList
     * @return back to the "MAIN_MENU" menu
     */

    @Override
    public int printInfo(AbstractTaskList abstractTaskList) {
        if(checkYourChoose == 1) {
            System.out.println("Задача была сохранена");
            return MAIN_MENU;
        } else {
            System.out.println("Задача была загружена");
        }
        return MAIN_MENU;
    }

    /**
     * Method fileName - returns the name of file
     *
     * @return the file name of this object
     */

    public String fileName() {
        String file = "";
        try {
            System.out.println("Введите имя файла");
            file = bufferedReader.readLine();
        } catch (IOException e) {
            logger.error(FILE_NOT_FOUND);
            System.out.println(FILE_NOT_FOUND);
        }
        return file;
    }

    /**
     * Method taskChoose - returns the taskType of Task
     *
     * @return taskType of this object
     */

    @Override
    public int taskChoose() {
        System.out.println("Выберите что нужно сделать");
        System.out.println("1 - сохранить задачу");
        System.out.println("2 - загрузить задачу");
        System.out.println("3 - Вернуться в главное меню");
        int taskType = 0;
        try {
            String taskName = bufferedReader.readLine();
            taskType = Integer.parseInt(taskName);
        } catch (IOException e) {
            logger.error(WRONG_NUMBER);
            System.out.println(WRONG_NUMBER);
        }
        this.checkYourChoose = taskType;
        return taskType;
    }
}
