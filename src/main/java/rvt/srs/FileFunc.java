package rvt.srs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileFunc {
    

  private ArrayList<String> students;
    private final String filePath = "data/students.csv";

    private int nextId = 1;
    public FileFunc() {
        this.students = new ArrayList<>();
        loadFromFile();
        
    }

    public boolean checkEventStrings(String value) {
        if (value == null || value.length() < 3) {
            return false;
        }
        return value.matches("^[a-zA-Z0-9āčēģīķļņšūžĀČĒĢĪĶĻŅŠŪŽ ]*$");
    }
    private void loadFromFile() {
        java.io.File file = new java.io.File(filePath);
    
    // Check if the file actually exists on your disk
    if (!file.exists()) {
        System.out.println("DEBUG: File does not exist at " + file.getAbsolutePath());
        return; // Stop here so we don't trigger the catch block
    }
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                // If the line starts with an ID (like 1,Romans...), 
                // you might want to strip the ID or handle it.
                // For now, let's store the raw CSV data:
                this.students.add(line); 
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading file.");
        }
    }


    public void add(String name, String surname, String email, String personCode) {
        int id = this.students.size() + 1;

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy-HH:mm");
        String timestamp = now.format(formatter);

        String studentData = String.format("%s,%s,%s,%s,%d,%s", 
                                            name, surname, email, personCode, id, timestamp);
        this.students.add(studentData);
        saveTaskToFile(studentData);
        
        this.nextId = this.students.size() + 1;

        System.out.println("Student added successfully!");
}

    private void saveTaskToFile(String taskLine) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(taskLine);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public String getStudentName(int index){
        if (index >= 0 && index < students.size()) {
        return students.get(index);
        }
        return "Student not found";
    }

    public void printIdName(){
        for (int i = 0; i < students.size(); i++){
            System.out.println((i+1) + ":" + students.get(i));
        }
    }
    public void edit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Here are students list:");
        print();

        System.out.print("Who you want to change? ID: ");
        int id = Integer.valueOf(scanner.nextLine());
        int index = id - 1;

        if (index < 0 || index >= students.size()) {
        System.out.println("Invalid ID.");
        return;
        }

        System.out.println("What do you want to change?");
        System.out.println("1.Name \n2.Surname \n3.Email \n4.Person-code");
        System.out.print("Your choice:");
        int choice = Integer.valueOf(scanner.nextLine());

        System.out.print("Enter new value: ");
        String newValue = scanner.nextLine();

        String currentData = students.get(index);
        String[] parts = currentData.split(",");

        switch (choice) {
            case 1: parts[0] = newValue; break; // Name
            case 2: parts[1] = newValue; break; // Surname
            case 3: parts[2] = newValue; break; // Email
            case 4: parts[3] = newValue; break; // Person-code
            default: System.out.println("Invalid choice."); return;
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy-HH:mm");
        parts[5] = now.format(formatter);

        String updatedLine = String.join(",", parts);
        students.set(index, updatedLine);

        rewriteFile();
        System.out.println("Student updated successfully!");
    }

    public void print() {
        String border = "+-----------------+-----------------+--------------------------------+-----------------+-----------------+-----------------+";
    String format = "| %-15s | %-15s | %-30s | %-15s | %-15s | %-15s |%n";

    // 1. Print Header
    System.out.println(border);
    System.out.printf(format, "Name", "Surname", "Email", "Peson-code", "Registration ID", "Time and Date");
    System.out.println(border);

    // 2. Print Rows
    for (String studentData : students) {
        String[] parts = studentData.split(",");
        
        // Ensure we have enough parts to avoid ArrayIndexOutOfBounds
        String name = parts.length > 0 ? parts[0] : "";
        String surname = parts.length > 1 ? parts[1] : "";
        String email = parts.length > 2 ? parts[2] : "";
        String pesonCode = parts.length > 3 ? parts[3] : "";
        String registration = parts.length > 4 ? parts[4] : "";
        String timeAndDate = parts.length > 5 ? parts[5] : "";

        System.out.printf(format, name, surname, email, pesonCode, registration, timeAndDate);
    }

    // 3. Print Bottom Border
    System.out.println(border);
    }

    public void remove(int number) {
        int indexToRemove = number - 1;

        if (indexToRemove >= 0 && indexToRemove < students.size()) {
            students.remove(indexToRemove);
            rewriteFile();
            this.nextId = students.size() + 1;
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private void rewriteFile() {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
        for (String studentData : students) {
            bw.write(studentData); // studentData already contains Name,Surname,Email,Code,ID,Time
            bw.newLine();
        }
    } catch (IOException e) {
        System.out.println("Error updating file.");
    }
}


}