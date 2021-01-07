package ua.edu.sumdu.j2se.astakhov.tasks.controller;

import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.astakhov.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.astakhov.tasks.view.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController extends Controller {

    private final AbstractTaskList abstractTaskList;
    private final List<Controller> controllers = new ArrayList<>();

    public MainController(AbstractTaskList abstractTaskList, View view) {
        super(view, Controller.MAIN_MENU);
        this.abstractTaskList = abstractTaskList;

        controllers.add(this);
        controllers.add(new CreateTaskListController(new CreateTaskListView(), Controller.TASK_LIST));
        controllers.add(new AddTaskController(new AddTask(), Controller.ADD_TASK));
        controllers.add(new RemoveTaskController(new RemoveTask(), Controller.REMOVE_TASK));
        controllers.add(new CalendarController(new CalendarView(), Controller.CALENDAR));
        controllers.add(new SaveAndLoadTaskController(new SaveAndLoadTaskView(), Controller.SAVE_TASK));
        controllers.add((new InfoTaskController(new InfoTaskView(), Controller.ACTIVE_TASK)));
        controllers.add((new ChangeTaskController(new ChangeTaskView(), Controller.CHANGE_TASK)));
        NotificationController notificationController = new NotificationController(new NotificationView(), abstractTaskList);
        notificationController.setDaemon(true);
        notificationController.start();
        File myFile = new File("saves/autoSave.json");
        if (myFile.exists()) {
            try {
                TaskIO.read(abstractTaskList, new FileReader("saves/autoSave.json"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int process(AbstractTaskList abstractTaskList) throws IOException {
        int action = view.printInfo(abstractTaskList);
        if(action == 1 || action == 2 || action == 3 || action == 4 || action == 5 || action == 6 || action == 7 || action == 8) {
            do {
                for (Controller controller : controllers) {
                    if (controller.canProcess(action)) {
                        action = controller.process(this.abstractTaskList);
                    }
                }
            } while (action != EXIT);
        }
        return EXIT;
    }
}
