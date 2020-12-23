package ua.edu.sumdu.j2se.astakhov.tasks.model;

import com.google.gson.Gson;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TaskIO {
    public static void write(AbstractTaskList tasks, OutputStream out) throws IOException {
        DataOutputStream outputStream = new DataOutputStream(out);
        try {
            outputStream.writeInt(tasks.size());
            for(int i = 0; i < tasks.size(); i++) {
                outputStream.writeUTF(tasks.getTask(i).getTitle());
                outputStream.writeInt(tasks.getTask(i).getTime().getSecond());
                outputStream.writeInt(tasks.getTask(i).getStartTime().getSecond());
                outputStream.writeInt(tasks.getTask(i).getEndTime().getSecond());
                outputStream.writeInt(tasks.getTask(i).getRepeatInterval());
                outputStream.writeBoolean(tasks.getTask(i).isActive());
                outputStream.writeBoolean(tasks.getTask(i).isRepeated());
            }
        } finally {
            outputStream.close();
        }
    }

    public static void read(AbstractTaskList tasks, InputStream in) throws IOException {
        DataInputStream inputStream = new DataInputStream(in);
        try {
            int amountOfTasks = inputStream.readInt();
            for(int i = 0; i < amountOfTasks; i++) {
                String title = inputStream.readUTF();
                LocalDateTime time = LocalDateTime.ofEpochSecond(inputStream.readInt(), 0, ZoneOffset.UTC);
                LocalDateTime to = LocalDateTime.ofEpochSecond(inputStream.readInt(), 0, ZoneOffset.UTC);
                LocalDateTime from = LocalDateTime.ofEpochSecond(inputStream.readInt(), 0,ZoneOffset.UTC);
                int interval = inputStream.readInt();
                boolean isActive = inputStream.readBoolean();
                boolean repeated = inputStream.readBoolean();
                Task task;
                if (repeated) {
                    task = new Task(title, to, from, interval);
                } else {
                    task = new Task(title, time);
                }
                task.setActive(isActive);
                tasks.add(task);
            }

        } finally {
            inputStream.close();
        }
    }

    public static void writeBinary(AbstractTaskList tasks, File file) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        try {
            write(tasks, outputStream);
        } finally {
            outputStream.close();
        }
    }

    public static void readBinary(AbstractTaskList tasks, File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        try {
            read(tasks, inputStream);
        } finally {
            inputStream.close();
        }
    }

    public static void write(AbstractTaskList tasks, Writer out) throws IOException {
        Gson writer = new Gson();
        try {
            writer.toJson(tasks, out);
        } finally {
            out.close();
        }
    }

    public static void read(AbstractTaskList tasks, Reader in) throws IOException {
        Gson reader = new Gson();
        AbstractTaskList task;
        try {
            task = reader.fromJson(in, tasks.getClass());
            for (Task task1 : task) {
                tasks.add(task1);
            }
        } finally {
            in.close();
        }
    }

    public static void writeText(AbstractTaskList tasks, File file) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        try {
            write(tasks, outputStream);
        } finally {
            outputStream.close();
        }
    }

    public static void readText(AbstractTaskList tasks, File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        try {
            read(tasks, inputStream);
        } finally {
            inputStream.close();
        }
    }
}
