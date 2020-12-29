package ua.edu.sumdu.j2se.astakhov.tasks;

import ua.edu.sumdu.j2se.astakhov.tasks.controller.Controller;
import ua.edu.sumdu.j2se.astakhov.tasks.controller.MainController;
import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.astakhov.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.astakhov.tasks.view.MainView;
import ua.edu.sumdu.j2se.astakhov.tasks.view.View;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		System.out.println("Менеджер задач запущен");
		AbstractTaskList abstractTaskList = new ArrayTaskList();
		View mainView = new MainView();
		Controller mainController = new MainController(abstractTaskList, mainView);
		mainController.process(null);
		System.out.println("Менеджер закрыт");

	}
}
