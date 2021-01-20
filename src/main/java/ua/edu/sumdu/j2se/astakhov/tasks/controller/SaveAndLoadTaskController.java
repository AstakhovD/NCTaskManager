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

import static ua.edu.sumdu.j2se.astakhov.tasks.controller.Errors.FILE_NOT_FOUND;
import static ua.edu.sumdu.j2se.astakhov.tasks.controller.Errors.WRONG_NUMBER;

/**
 * Class SaveAndLoadTaskController realizes save/load task in file.
 *
 * @author Астахов Дмитрій
 */

public class SaveAndLoadTaskController extends Controller {

    private static final Logger logger = Logger.getLogger(SaveAndLoadTaskController.class);

    /**
     * Constructor SaveAndLoadTaskController
     *
     * @param view of type View
     * @param actionToDo of type int
     */

    public SaveAndLoadTaskController(View view, int actionToDo) {
        super(view, actionToDo);
    }

    /**
     * Method process realizes save/load task in file.
     *
     * @param abstractTaskList of type AbstractTaskList
     * @return shows the result of your choose
     */

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
                logger.error(FILE_NOT_FOUND);
                view.exception(FILE_NOT_FOUND);
                return SAVE_TASK;
            }
        } else if (checkYourChoose == 2) {
            try {
                String fileName = ((SaveAndLoadTaskView) view).fileName();
                TaskIO.read(abstractTaskList, new FileReader("saves/" + fileName + ".json"));
            } catch (IOException e) {
                logger.error(FILE_NOT_FOUND);
                view.exception(FILE_NOT_FOUND);
                return SAVE_TASK;
            }
        } else if(checkYourChoose == 3) {
            return MAIN_MENU;
        } else {
            logger.error(WRONG_NUMBER);
            view.exception(WRONG_NUMBER);
            return SAVE_TASK;
        }
        return view.printInfo(abstractTaskList);
    }
}
