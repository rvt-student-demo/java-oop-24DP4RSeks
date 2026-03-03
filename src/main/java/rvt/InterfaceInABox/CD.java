package rvt.InterfaceInABox;

public class CD implements Packable{
    private String artist;
    private String name;
    private double weight;

    public CD(String artist, String name, int year){
        this.artist = artist;
        this.name = name;
        this.weight = weight;
    }

    @Override
    public double weight() {
        return this.weight;
    }

    @Override
    public String toString() {
        return this.artist + ": " + this.name;
    }

}
