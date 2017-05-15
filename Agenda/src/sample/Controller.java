package sample;
import com.sun.org.apache.xerces.internal.impl.dv.xs.DateTimeDV;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;

import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Calender;


public class Controller {
    @FXML private javafx.scene.layout.AnchorPane table;
    private int year;
    private int month;
    private sample.Calender calender = new Calender();
    private int ButtonsPositionY = 156;
    private ArrayList<Event> EventByMonth;

    public ArrayList<Event> getLstEventByMonth(){return EventByMonth;}
    @FXML public void initialize() {
        try {
            year = Calendar.getInstance().get(Calendar.YEAR);
            month = Calendar.getInstance().get(Calendar.MONTH);
            setButtonsYearMonth();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML public void btnYearNextClick(ActionEvent event){
        year = year + 1;
        setButtonsYearMonth();
    }

    @FXML public void btnYearPreviousClick(ActionEvent event){
        year = year - 1;
        setButtonsYearMonth();
    }

    @FXML public void btnMonthNextClick(ActionEvent event){
        if (month == 11){
            month = 0;
            year = year + 1;
        }
        else {
            month = month + 1;
        }
        setButtonsYearMonth();
    }

    public void setButtonsYearMonth() {
        this.table.getChildren().clear();
        Button monthtNext = new Button();
        monthtNext.setText("Next");
        monthtNext.setLayoutY(74);
        monthtNext.setLayoutX(414);
        monthtNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnMonthNextClick(event);
            }
        });
        this.table.getChildren().add(monthtNext);

        Button monthPrevious = new Button();
        monthPrevious.setText("Previous");
        monthPrevious.setLayoutX(135);
        monthPrevious.setLayoutY(74);
        monthPrevious.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnMonthPreviousClick(event);
            }
        });
        this.table.getChildren().add(monthPrevious);

        Button yearNext = new Button();
        yearNext.setText("Next");
        yearNext.setLayoutY(38);
        yearNext.setLayoutX(414);
        yearNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnYearNextClick(event);
            }
        });
        this.table.getChildren().add(yearNext);

        Button yearPrevious = new Button();
        yearPrevious.setText("Previous");
        yearPrevious.setLayoutX(135);
        yearPrevious.setLayoutY(38);
        yearPrevious.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                 btnYearPreviousClick(event);
            }
        });
        this.table.getChildren().add(yearPrevious);

        javafx.scene.control.Label lblYear = new javafx.scene.control.Label();
        lblYear.setText(String.valueOf(year));
        lblYear.setLayoutY(38);
        lblYear.setLayoutX(278);
        this.table.getChildren().add(lblYear);

        javafx.scene.control.Label lblMonth = new javafx.scene.control.Label();
        lblMonth.setText(String.valueOf(Months.values()[month]));
        lblMonth.setLayoutX(278);
        lblMonth.setLayoutY(76);
        this.table.getChildren().add(lblMonth);

        Button btnRefresh = new Button();
        btnRefresh.setText("Refresh");
        btnRefresh.setLayoutX(38);
        btnRefresh.setLayoutY(76);
        btnRefresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setButtonsYearMonth();
            }
        });
        table.getChildren().add(btnRefresh);

        EventByMonth = getEventsByMonth();
        setLabelDays();
        try
        {
        setDaysButtons();
        }
        catch (InvocationTargetException e){
            System.out.println(e);
        }
        ButtonsPositionY = 156;


    }

    @FXML public void btnMonthPreviousClick(ActionEvent event){
        if(month == 0){
            month = 11;
            year = year - 1;
        }
        else {
            month = month - 1;
        }
        setButtonsYearMonth();
    }



    @FXML public void setLabelDays(){
        for(int i = 0; i < 7; i++){
            javafx.scene.control.Label label = new javafx.scene.control.Label();
            label.setText(Days.values()[i].toString());
            label.setLayoutX(SetPositionX(Days.values()[i].toString()));
            label.setLayoutY(123);
            this.table.getChildren().add(label);
        }
    }

    @FXML public void setDaysButtons() throws InvocationTargetException{
        int Y = 156;
        for (int  i = 1; i < calender.GetCountDaysOfMonth(calender.IsLeapYear(year)).get(month) + 1; i++) {
            final String calculateDay = calender.CalculateDay(month + 1, i, year).toString();
            final Button button = new Button();
            button.setText(String.valueOf(i));
            button.setLayoutX(SetPositionX(calculateDay));
            button.setLayoutY(SetButtonPositionY(calculateDay, i));
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Event.fxml"));
                        AnchorPane root = (AnchorPane) loader.load();
                        loader.setController(new EventController(root));
                        EventController controller = loader.getController();

                        controller.setCalendar(year + "-" + ("0" +(month + 1)) + "-" + button.getText());
                        controller.setListEvents(EventByMonth);
                        controller.setLayout();

                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();

                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                }
            });
            if (i == Calendar.getInstance().get(Calendar.DATE) && month == Calendar.getInstance().get(Calendar.MONTH) && year == Calendar.getInstance().get(Calendar.YEAR)) {
                button.setStyle("-fx-border-color: red;");
            }
            button.setStyle(setEventOnDay(i));
            this.table.getChildren().add(button);
        }
    }


    private int SetPositionX(String day){
        try {
            switch (day) {
                case "Su":
                    return 172;
                case "Mo":
                    return 211;
                case "Tu":
                    return 250;
                case "We":
                    return 289;
                case "Th":
                    return 328;
                case "Fr":
                    return 367;
                case "Sa":
                    return 406;
                default:
                    throw new NullPointerException();
            }
        }
        catch (NullPointerException e){
            JOptionPane.showMessageDialog(null,"Er is iets misgegaan");
        }
        return 0;
    }
    private int SetButtonPositionY(String dayName, int day){
        try {
            if(dayName.equals("Su")){
                if(day == 1){
                    return ButtonsPositionY;
                }
                else {
                    ButtonsPositionY = ButtonsPositionY + 39;
                    return ButtonsPositionY;
                }
            }
            else {
                return ButtonsPositionY;
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Er is iets misgegaan");
        }
        return 0;
    }

    private ArrayList<Event> getEventsByMonth(){
            Event event = new Event();
            String startMonth = year + "-" + month + "-" + 1;
            String endMonth = year + "-" + month + "-" + calender.GetCountDaysOfMonth(calender.IsLeapYear(year)).get(month);
            EventByMonth = event.getEventsByMonth(startMonth,endMonth);

            return EventByMonth;
    }

    public String setEventOnDay(int day){
        for(Event e:EventByMonth){
            String[] splitDateTime = e.getDateStart().split(" ");
            String[] getDay = splitDateTime[0].split("-");
            if(Integer.parseInt(getDay[2]) == day && getDay[1].equals("0" + (month + 1)) && Integer.parseInt(getDay[0]) == year){
                return "-fx-color: blue";
            }
        }
        return null;
    }
}
