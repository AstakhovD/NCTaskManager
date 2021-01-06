package ua.edu.sumdu.j2se.astakhov.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.astakhov.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.astakhov.tasks.view.SaveAndLoadTaskView;
import ua.edu.sumdu.j2se.astakhov.tasks.view.View;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaveAndLoadTaskController extends Controller {

    private static final Logger logger = Logger.getLogger(NotificationController.class);

    public SaveAndLoadTaskController(View view, int actionToDo) {
        super(view, actionToDo);
    }

    @Override
    public int process(AbstractTaskList abstractTaskList) {
        int checkYourChoose = ((SaveAndLoadTaskView) view).taskChoose();
        File file = new File("saves");
        file.mkdir();
        if (checkYourChoose == 1) {
            try {
                String fileName = ((SaveAndLoadTaskView) view).fileName();
                TaskIO.write(abstractTaskList, new FileWriter(new File("saves/" + fileName + ".json")));
            } catch (IOException e) {
                System.out.println("Ошибка: Файл не был найден");
                return Controller.SAVE_TASK;
            }
        } else if (checkYourChoose == 2) {
            try {
                String fileName = ((SaveAndLoadTaskView) view).fileName();
                TaskIO.read(abstractTaskList, new FileReader("saves/" + fileName + ".json"));
            } catch (IOException e) {
                logger.error("Ошибка: Файл не был найден");
                System.out.println("Ошибка: Файл не был найден");
                return Controller.SAVE_TASK;
            }
        } else if(checkYourChoose == 3) {
            return Controller.MAIN_MENU;
        } else {
            logger.error("Ошибка: Файл не был найден");
            System.out.println("Ошибка: Вы ввели неверное число");
            return Controller.SAVE_TASK;
        }
        return view.printInfo(abstractTaskList);
    }
}
