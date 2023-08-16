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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class HelloController {
   // ArrayList<PrimitiveCar> cars1 = new ArrayList<>();
    //ObservableList<PrimitiveCar> cars2 = FXCollections.observableArrayList();
    ObservableList<ObservableCar> cars3 = FXCollections.observableArrayList();
   // Map<PrimitiveCar, Label> labelMap = new HashMap<>();
    //Map<PrimitiveCar, HBox> hBoxMap = new HashMap<>();
    HashMap<ObservableCar, HBox> hBoxMap3 = new HashMap<>();

    @FXML
    TextField txtSpeed, txtName, txtGruz;
    @FXML
    VBox boxForCars;

    @FXML
    public void addCar(){
        int sp = Integer.parseInt(txtSpeed.getText());
        int gr = Integer.parseInt(txtGruz.getText());
        //PrimitiveCar car = new PrimitiveCar(txtName.getText(),sp, gr);
        //cars1.add(car);
        //boxForCars.getChildren().add(new Label(car.toString()));
        //repaintCars();

        //cars2.add(car);

        ObservableCar car = new ObservableCar(txtName.getText(),sp, gr);
        cars3.add(car);
        car.name.addListener((val, o, n)-> System.out.println("имя машинки изменилось с "+o+" на "+n));
        car.speed.addListener((val, o, n)-> System.out.println("скорость машинки изменилась с "+o+" на "+n));
        car.gruz.addListener((val, o, n)-> System.out.println("грузоподъемность машинки изменилась с "+o+" на "+n));
    }

   /* public void repaintCars()
    {
        boxForCars.getChildren().clear();
        for (PrimitiveCar pc: cars1     ) {
            boxForCars.getChildren().add(new Label(pc.toString()));
        }
    }*/

    public void initialize(){
        cars3.addListener((ListChangeListener<ObservableCar>) change -> {
            while (change.next())  {
                if(change.wasAdded()){
                    System.out.println("что-то добавилось в список");
                    for(ObservableCar oc: change.getAddedSubList()) {
                        paintCar(oc);
                    }
                }
                if(change.wasRemoved())  {
                    System.out.println("что-то удалилось из списка");
                    for(ObservableCar oc: change.getRemoved()) {
                        eraseCar(oc);
                    }
                }
            }
        });
    }

   /* private void eraseCar(PrimitiveCar pc) {
        System.out.println("удалилась машинка "+ pc);
        boxForCars.getChildren().remove(hBoxMap.get(pc));
        hBoxMap.remove(pc);
    }*/

    /*private void paintCar(PrimitiveCar pc) {
        Label lab = new Label(pc.toString());
        Button but = new Button("-");
        but.setOnAction(q->cars2.remove(pc));
        HBox hBox=new HBox();
        hBox.getChildren().add(but);
        hBox.getChildren().add(lab);
        boxForCars.getChildren().add(hBox);
        hBoxMap.put(pc, hBox);
    }*/
    private void paintCar(ObservableCar oc) {
        //Label lab = new Label(pc.toString());
        TextField tname = new TextField();
        tname.textProperty().bindBidirectional(oc.name);
        TextField tspeed = new TextField();
        tspeed.textProperty().bindBidirectional(oc.speedProperty(), new DecimalFormat());
        TextField tgruz = new TextField();
        tgruz.textProperty().bindBidirectional(oc.gruz, new DecimalFormat());
        Button but = new Button("-");
        but.setOnAction(q->cars3.remove(oc));
        HBox hBox=new HBox();
        hBox.getChildren().add(but);
        hBox.getChildren().add(tname);
        hBox.getChildren().add(tspeed);
        hBox.getChildren().add(tgruz);
        boxForCars.getChildren().add(hBox);
        hBoxMap3.put(oc, hBox);
    }

    private void eraseCar(ObservableCar oc) {
        boxForCars.getChildren().remove(hBoxMap3.get(oc));
        hBoxMap3.remove(oc);
    }
}