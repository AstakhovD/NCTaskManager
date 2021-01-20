package ua.edu.sumdu.j2se.astakhov.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.astakhov.tasks.model.Task;
import ua.edu.sumdu.j2se.astakhov.tasks.model.Tasks;
import ua.edu.sumdu.j2se.astakhov.tasks.view.NotificationView;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

import static ua.edu.sumdu.j2se.astakhov.tasks.controller.Errors.ERROR;

/**
 * Class NotificationController realizes notification for Tasks.
 *
 * @author Астахов Дмитрій
 */

public class NotificationController extends Thread {

    private static final Logger logger = Logger.getLogger(NotificationController.class);
    NotificationView view;
    private final AbstractTaskList abstractTaskList;

    /**
     * Constructor NotificationController
     *
     * @param view of type NotificationView
     * @param abstractTaskList of AbstractTaskList
     */

    public NotificationController(NotificationView view, AbstractTaskList abstractTaskList) {
        this.view = view;
        this.abstractTaskList = abstractTaskList;
    }

    /**
     * Method run starts check tasks.
     */

    @Override
    public void run() {
        while (true) {
            String taskName = "";
            LocalDateTime timeCurrent = LocalDateTime.now().withSecond(0).withNano(0);
            LocalDateTime endTime = LocalDateTime.now().withSecond(0).withNano(0).plusYears(200);
            SortedMap<LocalDateTime, Set<Task>> tasks = Tasks.calendar(abstractTaskList, timeCurrent, endTime);
            for(SortedMap.Entry<LocalDateTime, Set<Task>> elements : tasks.entrySet()) {
                for(Task task: elements.getValue()) {
                    taskName = task.getTitle();
                    if(elements.getKey().isEqual(timeCurrent)) {
                        view.printInfo(elements.getKey(), taskName);
                    }
                }
            } try {
                Thread.sleep(Duration.between(LocalDateTime.now(), LocalDateTime.now().plusMinutes(1)).toMillis());
            } catch (InterruptedException e) {
                logger.error(ERROR);
            }
        }
    }
}
