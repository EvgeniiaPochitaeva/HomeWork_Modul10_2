package homeWork_Modul10_2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String myFail = "C:\\Users\\eshap\\IdeaProjects\\HomeWork_Modul10_2\\src\\homeWork_Modul10_2\\file.txt";

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(myFail);
        checkIfFileAvailable(file);
        List<User> users = metodRead(file);
        writeUsersToJson(users);
    }

    private static void checkIfFileAvailable(File file) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static List<User> metodRead(File file) throws FileNotFoundException {
        List<User> users = new ArrayList<>();
        FileInputStream fls = new FileInputStream(myFail);
        Scanner scanner = new Scanner(fls);
        // Пропускаем первую строку, так как она содержит заголовки столбцов
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] words = line.split(" ");
            if (words.length == 2) {
                String name = words[0];
                int age = Integer.parseInt(words[1]);
                users.add(new User(name, age));
            }
        }
        return users;
    }

    private static void writeUsersToJson(List<User> users) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(users);
        try (FileWriter writer = new FileWriter("user.json")) {
            writer.write(json);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}


