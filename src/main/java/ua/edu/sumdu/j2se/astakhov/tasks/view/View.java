package ua.edu.sumdu.j2se.astakhov.tasks.view;

import ua.edu.sumdu.j2se.astakhov.tasks.model.AbstractTaskList;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public interface View {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    int printInfo(AbstractTaskList abstractTaskList);

    /**
     * Method exception - to display error messages to the user.
     *
     * @param message of type String
     */
    default void exception(String message) {
        System.out.println(message);
    }
}
