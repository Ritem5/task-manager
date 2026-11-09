package main.java;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();


        while (true) {
        System.out.print("> ");
        String cmd = scanner.nextLine();

        if (cmd.equals("exit")){
            break;
        }else if(cmd.equals("list")) {
            for(int i = 0; i <= tasks.size() ; i++){
                System.out.println((i+1) + ". " + tasks.get(i));
            }
        }else if(cmd.contains("add")){
            String text = cmd.substring(1);
            tasks.add(text);
        }else {
            System.out.println("Неизвестная команда");
        }
        }

    }
}
