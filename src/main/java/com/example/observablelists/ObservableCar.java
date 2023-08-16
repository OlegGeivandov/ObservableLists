package com.example.observablelists;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ObservableCar {
    SimpleStringProperty name;
    SimpleIntegerProperty speed = new SimpleIntegerProperty();
    SimpleIntegerProperty gruz=new SimpleIntegerProperty();

    public ObservableCar(String name, int speed, int gruz) {
        this.name = new SimpleStringProperty( name);
        this.speed.set( speed);
        this.gruz.set( gruz);
    }

    public int getSpeed() {
        return speed.get();
    }

    public SimpleIntegerProperty speedProperty() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed.set(speed);
    }

    @Override
    public String toString() {
        return "ObservableCar{" +
                "name=" + name.get() +
                ", speed=" + getSpeed() +
                ", gruz=" + gruz.get() +
                '}';
    }
}
