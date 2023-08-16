package com.example.observablelists;

public class PrimitiveCar {
    String name;
    int speed;
    int gruz;

    public PrimitiveCar(String name, int speed, int gruz) {
        this.name = name;
        this.speed = speed;
        this.gruz = gruz;
    }

    @Override
    public String toString() {
        return "PrimitiveCar{" +
                "name='" + name + '\'' +
                ", speed=" + speed +
                ", gruz=" + gruz +
                '}';
    }
}
