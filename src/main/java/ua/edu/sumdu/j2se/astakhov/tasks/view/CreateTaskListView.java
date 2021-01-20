package ua.edu.sumdu.j2se.astakhov.tasks.view;

import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;

import static ua.edu.sumdu.j2se.astakhov.tasks.controller.Controller.MAIN_MENU;

/**
 * Class CreateTaskListView realizes create task list.
 *
 * @author Астахов Дмитрій
 */

public class CreateTaskListView implements View {

    /**
     * Method printInfo shows the result.
     *
     * @param abstractTaskList of type AbstractTaskList
     * @return back to the "MAIN_MENU" menu
     */

    @Override
    public int printInfo(AbstractTaskList abstractTaskList) {
        System.out.println("Просмотр задач");
        for (int i = 0; i < abstractTaskList.size(); i++) {
            if(abstractTaskList.getTask(i).isRepeated()) {
                System.out.println("Номер задачи " + i + ". Название задачи - " + abstractTaskList.getTask(i).getTitle() +
                        ", время начала - " + abstractTaskList.getTask(i).getStartTime() + ", время окончания - " + abstractTaskList.getTask(i).getEndTime() +
                        ", интервал - " + abstractTaskList.getTask(i).getRepeatInterval() + ", активность - " + abstractTaskList.getTask(i).isActive());
            } else {
                System.out.println("Номер задачи " + i + ". Название задачи - " + abstractTaskList.getTask(i).getTitle() +
                        ", время - " + abstractTaskList.getTask(i).getTime() + ", активность - " + abstractTaskList.getTask(i).isActive());
            }
        }
        return MAIN_MENU;
    }
}
