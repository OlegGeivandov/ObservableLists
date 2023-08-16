package com.example.observablelists;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class HelloController {
    ArrayList<PrimitiveCar> cars1 = new ArrayList<>();
    ObservableList<PrimitiveCar> cars2 = FXCollections.observableArrayList();
    Map<PrimitiveCar, Label> labelMap = new HashMap<>();
    Map<PrimitiveCar, HBox> hBoxMap = new HashMap<>();

    @FXML
    TextField txtSpeed, txtName, txtGruz;
    @FXML
    VBox boxForCars;

    @FXML
    public void addCar(){
        int sp = Integer.parseInt(txtSpeed.getText());
        int gr = Integer.parseInt(txtGruz.getText());
        PrimitiveCar car = new PrimitiveCar(txtName.getText(),sp, gr);
        //cars1.add(car);
        //boxForCars.getChildren().add(new Label(car.toString()));
        //repaintCars();

        cars2.add(car);
    }

    public void repaintCars()
    {
        boxForCars.getChildren().clear();
        for (PrimitiveCar pc: cars1     ) {
            boxForCars.getChildren().add(new Label(pc.toString()));
        }
    }

    public void initialize(){
        cars2.addListener(new ListChangeListener<PrimitiveCar>() {
            @Override
            public void onChanged(Change<? extends PrimitiveCar> change) {
                while (change.next())
                {
                    if(change.wasAdded())
                    {
                        System.out.println("что-то добавилось в список");
                        for(PrimitiveCar pc: change.getAddedSubList()) {
                            Label lab = new Label(pc.toString());
                            Button but = new Button("-");
                            but.setOnAction(q->cars2.remove(pc));
                            HBox hBox=new HBox();
                            hBox.getChildren().add(but);
                            hBox.getChildren().add(lab);
                            boxForCars.getChildren().add(hBox);
                            hBoxMap.put(pc, hBox);
                        }
                    }
                    if(change.wasRemoved())
                    {
                        System.out.println("что-то удалилось из списка");
                        for(PrimitiveCar pc: change.getRemoved()) {
                            System.out.println("удалилась машинка "+pc);
                            boxForCars.getChildren().remove(hBoxMap.get(pc));
                            hBoxMap.remove(pc);
                        }
                    }
                }
            }
        });
    }
}