package rvt.srs;

import java.util.Scanner;

public class Interface {
    
    public void start() {
        FileFunc list = new FileFunc();
        Scanner scanner = new Scanner(System.in);
        while (true){
            String answer;
            System.out.print("Command: ");
            answer = scanner.nextLine();
            if (answer.equals("register")){
                System.out.print("Name: ");
                String name = scanner.nextLine();
                System.out.print("Surname: ");
                String surname = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();
                System.out.print("Person Code: ");
                String personCode = scanner.nextLine();
                list.add(name, surname, email, personCode);
            } else if (answer.equals("remove")){
                System.out.print("To remove: ");
                int number = Integer.valueOf(scanner.nextLine());
                list.remove(number);
            } else if (answer.equals("show")){
                list.print();
            } else if (answer.equals("exit")){
                break;
            } else if (answer.equals("edit")){
                list.edit();
            }
            
        }
    }
}
