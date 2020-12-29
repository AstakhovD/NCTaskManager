package ua.edu.sumdu.j2se.astakhov.tasks.controller;

import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.astakhov.tasks.view.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController extends Controller {

    private AbstractTaskList abstractTaskList;
    private List<Controller> controllers = new ArrayList<>();

    public MainController(AbstractTaskList abstractTaskList, View view) {
        super(view, Controller.MAIN_MENU);
        this.abstractTaskList = abstractTaskList;

        controllers.add(this);
        controllers.add(new CreateTaskListController(new CreateTaskListView(), Controller.TASK_LIST));
        controllers.add(new AddTaskContrl(new AddTask(), Controller.ADD_TASK));
        controllers.add(new RemoveTaskContrl(new RemoveTask(), Controller.REMOVE_TASK));
        controllers.add(new CalendarContrl(new CalendarView(), Controller.CALENDAR));
        controllers.add(new SaveAndLoadTaskController(new SaveAndLoadTaskView(), Controller.SAVE_TASK));
        controllers.add((new InfoTaskController(new InfoTaskView(), Controller.INFO_TASK)));

    }

    @Override
    public int process(AbstractTaskList abstractTaskList) throws IOException {
        int action = view.printInfo(abstractTaskList);
        if(action == 1 || action == 2 || action == 3 || action == 4 || action == 5 || action == 6 || action == 7) {
            for(; ;) {
                for (Controller controller : controllers) {
                    if(controller.canProcess(action)) {
                        action = controller.process(this.abstractTaskList);
                    }
                }
                if(action == EXIT) {
                    break;
                }
            }
        }
        return EXIT;
    }
}
