package rvt.srs;

import java.util.Scanner;

public class Interface {
    
    public void start() {
        FileFunc list = new FileFunc();
        Scanner scanner = new Scanner(System.in);
        while (true){
            String answer;
            System.out.print("Command(enter list to see commands): ");
            answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("list")){
                System.out.println("Commands:");
                System.out.println("Register \nRemove \nShow \nEdit \nExit");
            }else if (answer.equalsIgnoreCase("register")){
                System.out.print("Name: ");
                String name = scanner.nextLine();
                System.out.print("Surname: ");
                String surname = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();
                System.out.print("Person Code: ");
                String personCode = scanner.nextLine();
                list.add(name, surname, email, personCode);
            } else if (answer.equalsIgnoreCase("remove")){
                list.remove();
            } else if (answer.equalsIgnoreCase("show")){
                list.print();
            } else if (answer.equalsIgnoreCase("exit")){
                break;
            } else if (answer.equalsIgnoreCase("edit")){
                list.edit();
            }
            
        }
    }
}
