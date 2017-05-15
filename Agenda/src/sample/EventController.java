package sample;
import com.sun.org.apache.xerces.internal.impl.dv.xs.DateTimeDV;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;

import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Calender;

/**
 * Created by maxhe on 1-5-2017.
 */
public class EventController {
    private @FXML AnchorPane AnchorPane;
    private String Calendar;
    private Controller controller;
    private ListView lstEvents;
    private Label lblName;
    private TextField tfName;
    private Label lblDescription;
    private TextArea taDescription;
    private Label lblStartDate;
    private TextField tfStartDate;
    private Label lblEndDate;
    private TextField tfEndDate;
    private String[] getEventID;
    private ArrayList<Event> listEvents = new ArrayList<>();

    public void setListEvents(ArrayList<Event> events){passEventsList(events);}
    public void setAnchorPane(AnchorPane anchorPane){AnchorPane = anchorPane;}
    public void setCalendar(String calendar){Calendar = calendar;}

    public EventController(AnchorPane anchorPane){
        setAnchorPane(anchorPane);
    }

    public void setLayout(){
        lstEvents = new ListView();
        lstEvents.setLayoutX(0);
        lstEvents.setLayoutY(0);
        lstEvents.setMaxWidth(286);
        lstEvents.setMaxHeight(372);
        lstEvents.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectEvent();
            }
        });
        this.AnchorPane.getChildren().add(lstEvents);
        lblName = new Label();
        lblName.setLayoutY(12);
        lblName.setLayoutX(307);
        lblName.setMaxWidth(43);
        lblName.setMaxHeight(20);
        lblName.setText("Name:");
        this.AnchorPane.getChildren().add(lblName);

        tfName = new javafx.scene.control.TextField();
        tfName.setLayoutX(307);
        tfName.setLayoutY(32);
        tfName.setMaxWidth(279);
        tfName.setMaxHeight(26);
        this.AnchorPane.getChildren().add(tfName);

        lblDescription = new Label();
        lblDescription.setLayoutX(307);
        lblDescription.setLayoutY(58);
        lblDescription.setMaxHeight(20);
        lblDescription.setMinWidth(200);
        lblDescription.setText("Description: ");
        this.AnchorPane.getChildren().add(lblDescription);

        taDescription = new TextArea();
        taDescription.setLayoutX(307);
        taDescription.setLayoutY(78);
        taDescription.setMaxHeight(114);
        taDescription.setMaxWidth(279);
        this.AnchorPane.getChildren().add(taDescription);

        lblStartDate = new Label();
        lblStartDate.setLayoutY(200);
        lblStartDate.setLayoutX(307);
        lblStartDate.setMinWidth(70);
        lblStartDate.setMaxHeight(20);
        lblStartDate.setText("Start Date: ");
        this.AnchorPane.getChildren().add(lblStartDate);

        tfStartDate = new javafx.scene.control.TextField();
        tfStartDate.setLayoutY(224);
        tfStartDate.setLayoutX(307);
        tfStartDate.setMaxHeight(26);
        tfStartDate.setMaxWidth(200);
        tfStartDate.setText(Calendar + " 00:00");
        this.AnchorPane.getChildren().add(tfStartDate);

        lblEndDate = new Label();
        lblEndDate.setLayoutX(307);
        lblEndDate.setLayoutY(250);
        lblEndDate.setMaxHeight(20);
        lblEndDate.setMaxHeight(64);
        lblEndDate.setText("End Date:");
        this.AnchorPane.getChildren().add(lblEndDate);

        tfEndDate = new javafx.scene.control.TextField();
        tfEndDate.setLayoutX(307);
        tfEndDate.setLayoutY(270);
        tfEndDate.setMaxWidth(200);
        tfEndDate.setMaxHeight(26);
        tfEndDate.setText(Calendar + " 23:59");
        this.AnchorPane.getChildren().add(tfEndDate);

        Button btnSaveEvent = new Button();
        btnSaveEvent.setLayoutY(307);
        btnSaveEvent.setLayoutX(305);
        btnSaveEvent.setMaxHeight(27);
        btnSaveEvent.setMaxWidth(97);
        btnSaveEvent.setText("Save Event");
        btnSaveEvent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveEvent();
            }
        });
        this.AnchorPane.getChildren().add(btnSaveEvent);

        Button btnUpdateEvent = new Button();
        btnUpdateEvent.setLayoutY(307);
        btnUpdateEvent.setLayoutX(410);
        btnUpdateEvent.setMaxHeight(27);
        btnUpdateEvent.setMaxWidth(97);
        btnUpdateEvent.setText("Update");
        btnUpdateEvent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateEvent();
            }
        });
        AnchorPane.getChildren().add(btnUpdateEvent);

        Button btnDeleteEvent = new Button();
        btnDeleteEvent.setLayoutY(307);
        btnDeleteEvent.setLayoutX(510);
        btnDeleteEvent.setMaxHeight(27);
        btnDeleteEvent.setMaxWidth(97);
        btnDeleteEvent.setText("Update");
        btnDeleteEvent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteEvent();
            }
        });
        AnchorPane.getChildren().add(btnDeleteEvent);

        updateListBox();
    }

    public void saveEvent(){
        try {
            Event event = new Event();
            event.setName(tfName.getText());
            event.setDescription(taDescription.getText());

            event.setDateStart(tfStartDate.getText());
            event.setDateEnd(tfEndDate.getText());

            event.addEvent(event);
            JOptionPane.showMessageDialog(null,"Event is toegevoegd");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void updateListBox(){

        for (Event event:listEvents) {
            String[] splitDateTime = event.getDateStart().split(" ");
            if(splitDateTime[0].equals(Calendar)) {
                ObservableList<String> items = FXCollections.observableArrayList(event.ToString(event.getID(), event.getName(),
                        event.getDateStart(), event.getDateEnd()));
                lstEvents.setItems(items);
            }
        }
    }

    public void selectEvent(){
        getEventID = lstEvents.getSelectionModel().getSelectedItem().toString().split(":");
        for (Event e:listEvents){
            if (e.getID() == Integer.parseInt(getEventID[0])){

                tfName.setText(e.getName());
                taDescription.setText(e.getDescription());
                tfStartDate.setText(e.getDateStart());
                tfEndDate.setText(e.getDateEnd());
            }
        }
    }

    public void passEventsList(ArrayList<Event> events){
        for (Event e:events){
            listEvents.add(e);
        }
    }

    public void updateEvent(){
        try {
            Event event = new Event();
            event.setID(Integer.parseInt(getEventID[0]));
            event.setName(tfName.getText());
            event.setDescription(taDescription.getText());

            event.setDateStart(tfStartDate.getText());
            event.setDateEnd(tfEndDate.getText());

            event.updateEvent(event);
            JOptionPane.showMessageDialog(null,"Event is geupdatet");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void deleteEvent(){
        Event event = new Event();
        event.deleteEvent(Integer.parseInt(getEventID[0]));
        JOptionPane.showMessageDialog(null,"Event is verwijderd");
    }
}
