package rvt.ProduktuKategorija;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductDB db = new ProductDB();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1 - Pievienot kategoriju");
            System.out.println("2 - Pievienot produktu");
            System.out.println("3 - Paradit visas kategorijas");
            System.out.println("4 - Paradit visus produktus");
            System.out.println("5 - Meklet produktus pec kategorijas");
            System.out.println("0 - Iziet");
            System.out.print("Izvele: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.print("Kategorijas nosaukums: ");
                String name = scanner.nextLine();
                db.addCategory(name);
                System.out.println("Kategorija pievienota.");
            } else if (choice.equals("2")) {
                System.out.print("Produkta nosaukums: ");
                String name = scanner.nextLine();

                System.out.print("Cena: ");
                double price = Double.parseDouble(scanner.nextLine());

                db.printCategories();
                System.out.print("Kategorijas ID: ");
                int categoryId = Integer.parseInt(scanner.nextLine());

                db.addProduct(name, price, categoryId);
                System.out.println("Produkts pievienots.");
            } else if (choice.equals("3")) {
                db.printCategories();
            } else if (choice.equals("4")) {
                db.printProducts();
            } else if (choice.equals("5")) {
                System.out.print("Kategorijas ID: ");
                int categoryId = Integer.parseInt(scanner.nextLine());
                db.printProductsByCategory(categoryId);
            } else if (choice.equals("0")) {
                break;
            } else {
                System.out.println("Nepareiza izvele.");
            }

            System.out.println();
        }
    }
}
