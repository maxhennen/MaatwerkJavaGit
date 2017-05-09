package sample;
import com.sun.org.apache.xerces.internal.impl.dv.xs.DateTimeDV;
import javafx.scene.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.*;
import java.awt.TextField;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by maxhe on 1-5-2017.
 */
public class EventController {
    private @FXML AnchorPane anchorPane;


    private Integer Year;
    private Integer Month;
    private Integer Day;
    String start;
    String end;
    public void setYear(int year){Year = year;}
    public void setMonth(int month){Month = month;}
    public void setDay(int day){Day = day;}

    @FXML public void initialize() {

    }


    public void setLayout(){
        ListView lstEvents = new ListView();
        lstEvents.setLayoutX(0);
        lstEvents.setLayoutY(0);
        lstEvents.setMaxWidth(286);
        lstEvents.setMaxHeight(372);

        Label lblName = new Label();
        lblName.setLayoutY(307);
        lblName.setLayoutX(12);
        lblName.setMaxWidth(43);
        lblName.setMaxHeight(20);
        lblName.setText("Name:");

        javafx.scene.control.TextField tfName = new javafx.scene.control.TextField();
        tfName.setLayoutX(307);
        tfName.setLayoutY(32);
        tfName.setMaxWidth(279);
        tfName.setMaxHeight(26);

        Label lblDescription = new Label();
        lblDescription.setLayoutX(307);
        lblDescription.setLayoutY(58);
        lblDescription.setMaxHeight(20);
        lblDescription.setMaxWidth(79);
        lblDescription.setText("Description: ");

        TextArea taDescription = new TextArea();
        taDescription.setLayoutX(307);
        taDescription.setLayoutY(78);
        taDescription.setMaxHeight(114);
        taDescription.setMaxWidth(279);

        Label lblStartDate = new Label();
        lblStartDate.setLayoutY(200);
        lblDescription.setLayoutX(307);
        lblDescription.setMaxWidth(70);
        lblDescription.setMaxHeight(20);
        lblDescription.setText("Start Date: ");

        javafx.scene.control.TextField tfStartDate = new javafx.scene.control.TextField();
        tfStartDate.setLayoutY(224);
        tfStartDate.setLayoutX(307);
        tfStartDate.setMaxHeight(26);
        tfStartDate.setMaxWidth(200);

        Label lblEndDate = new Label();
        lblEndDate.setLayoutX(307);
        lblEndDate.setLayoutY(250);
        lblEndDate.setMaxHeight(20);
        lblEndDate.setMaxHeight(64);
        lblEndDate.setText("End Date:");

        javafx.scene.control.TextField tfEndDate = new javafx.scene.control.TextField();
        tfEndDate.setLayoutX(307);
        tfEndDate.setLayoutY(270);
        tfEndDate.setMaxWidth(200);
        tfEndDate.setMaxHeight(26);

        Button btnSaveEvent = new Button();
        btnSaveEvent.setLayoutY(307);
        btnSaveEvent.setLayoutX(305);
        btnSaveEvent.setMaxHeight(27);
        btnSaveEvent.setMaxWidth(97);
        btnSaveEvent.setText("Save Event");
    }

    /*public void saveEvent(){
        try {
            Event event = new Event();
            event.setName(tfNameEvent.getText());
            event.setDescription(tfEventDescription.getText());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            Calendar startDate = Calendar.getInstance();
            startDate.setTime(sdf.parse(tfStartDate.getText()));
            event.setDateStart(startDate);

            Calendar endDate = Calendar.getInstance();
            endDate.setTime(sdf.parse(tfEndDate.getText()));
            event.setDateEnd(endDate);

            event.addEvent(event);
        }
        catch (ParseException e){
            System.out.println(e);
        }
    }*/

}
