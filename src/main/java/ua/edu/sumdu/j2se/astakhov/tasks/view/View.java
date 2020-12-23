package ua.edu.sumdu.j2se.astakhov.tasks.view;

import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public interface View {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    public int printInfo(AbstractTaskList abstractTaskList);

}
