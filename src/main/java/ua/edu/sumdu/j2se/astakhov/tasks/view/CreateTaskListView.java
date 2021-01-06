package ua.edu.sumdu.j2se.astakhov.tasks.view;

import ua.edu.sumdu.j2se.astakhov.tasks.controller.Controller;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;

public class CreateTaskListView implements View {
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
        return Controller.MAIN_MENU;
    }
}
