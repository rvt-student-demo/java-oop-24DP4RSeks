package rvt.InterfaceInABox;

import java.util.ArrayList;

public class Box implements Packable{
    public double maxWeight;
    private ArrayList<Packable> items;
    private int itemsAmount;

    public Box(int maxWeight){
        this.maxWeight = maxWeight;
        this.items = new ArrayList<>();
    }


    @Override
    public double weight() {
        double total = 0;
        for (Packable item : items) {
            total += item.weight();
        }
        return total;
    }


    public void add(Packable item) {
        if (this.weight() + item.weight() <= this.maxWeight) {
            this.items.add(item);
        }

        this.itemsAmount += 1;
    }

    @Override
    public String toString() {
        return "Box: " + itemsAmount + " items, total weight " + this.weight();
    }
    
}
