package rvt.InterfaceInABox;

public class App {
    public static void main(String[] args) {
        Box smallBox = new Box(10);
        Box bigBox = new Box(50);
        smallBox.add(new Book("Fyodor Dostoevsky", "Crime and Punishment", 2)) ;
        smallBox.add(new Book("Robert Martin", "Clean Code", 1));
        smallBox.add(new Book("Kent Beck", "Test Driven Development", 0.7));

        bigBox.add(new CD("Pink Floyd", "Dark Side of the Moon", 1973));
        bigBox.add(new CD("Wigwam", "Nuclear Nightclub", 1975));
        bigBox.add(new CD("Rendezvous Park", "Closer to Being Here", 2012));

        bigBox.add(smallBox);

    System.out.println(bigBox);
    }
}
