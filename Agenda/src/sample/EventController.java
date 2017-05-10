package sample;
import com.sun.org.apache.xerces.internal.impl.dv.xs.DateTimeDV;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Calender;

/**
 * Created by maxhe on 1-5-2017.
 */
public class EventController {
    private @FXML AnchorPane AnchorPane;
    private Calendar Calendar;

    private ListView lstEvents;
    private Label lblName;
    private TextField tfName;
    private Label lblDescription;
    private TextArea taDescription;
    private Label lblStartDate;
    private TextField tfStartDate;
    private Label lblEndDate;
    private TextField tfEndDate;

    public void setAnchorPane(AnchorPane anchorPane){AnchorPane = anchorPane;}
    public void setCalendar(Calendar calendar){Calendar = calendar;}

    public EventController(AnchorPane anchorPane){
        setAnchorPane(anchorPane);
    }

    public void setLayout(){
        lstEvents = new ListView();
        lstEvents.setLayoutX(0);
        lstEvents.setLayoutY(0);
        lstEvents.setMaxWidth(286);
        lstEvents.setMaxHeight(372);
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
        tfStartDate.setText(Calendar.get(Calendar.YEAR) + "-" + Calendar.get(Calendar.MONTH)+ "-" + Calendar.get(Calendar.DAY_OF_MONTH) + " 00:00");
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
        tfEndDate.setText(Calendar.get(Calendar.YEAR) + "-" + Calendar.get(Calendar.MONTH)+ "-" + Calendar.get(Calendar.DAY_OF_MONTH) + " 23:59");
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
    }

    public void saveEvent(){
        try {
            Event event = new Event();
            event.setName(tfName.getText());
            event.setDescription(taDescription.getText());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            Calendar startDate = Calendar.getInstance();
            startDate.setTime(sdf.parse(tfStartDate.getText()));
            event.setDateStart(startDate);

            Calendar endDate = Calendar.getInstance();
            endDate.setTime(sdf.parse(tfEndDate.getText()));
            event.setDateEnd(endDate);

            event.addEvent(event);
            JOptionPane.showMessageDialog(null,"Event is toegevoegd");
        }
        catch (ParseException e){
            System.out.println(e);
        }
    }

    public void updateListBox(){

    }
}
