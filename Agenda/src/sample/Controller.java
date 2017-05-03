package sample;
import com.sun.org.apache.xerces.internal.impl.dv.xs.DateTimeDV;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;

import javafx.scene.*;
import javafx.stage.Stage;
import sample.Calender;


public class Controller {
    @FXML private javafx.scene.layout.AnchorPane table;
    private int year;
    private int month;
    private sample.Calender calender = new Calender();
    private int ButtonsPositionY = 156;
    private ArrayList<Event> EventByMonth;

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

    public void setButtonsYearMonth(){
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

        EventByMonth = getEventsByMonth();
        setLabelDays();
        setDaysButtons();
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

    @FXML public void setDaysButtons() {
        int Y = 156;
        for (int  i = 1; i < calender.GetCountDaysOfMonth(calender.IsLeapYear(year)).get(month); i++) {
            String calculateDay = calender.CalculateDay(month + 1, i, year).toString();
            final Button button = new Button();
            button.setText(String.valueOf(i));
            button.setLayoutX(SetPositionX(calculateDay));
            button.setLayoutY(SetButtonPositionY(calculateDay, i));
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Event.fxml"));
                        Parent root = (Parent) loader.load();
                        EventController controller = loader.<EventController>getController();
                        Calendar cal = Calendar.getInstance();
                        cal.set(year, month + 1, Integer.parseInt(button.getText()));

                        controller.getDate(cal);

                        Stage stage = new Stage();
                        stage.setTitle(year + "/" + (month + 1) + "/" + button.getText());
                        stage.setScene(new Scene(root, 600, 400));
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
            setEventOnDay(button);
            this.table.getChildren().add(button);
        }
    }

    public int SetPositionX(String day){
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
    public int SetButtonPositionY(String dayName, int day){
        try {
            if(dayName == "Su"){
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

    public ArrayList<Event> getEventsByMonth(){

            Event event = new Event();
            Calendar calStartMonth = Calendar.getInstance();
            calStartMonth.set(Calendar.YEAR,year);
            calStartMonth.set(Calendar.MONTH,month);
            calStartMonth.set(Calendar.DAY_OF_MONTH,1);

            Calendar calEndMonth = Calendar.getInstance();
            calEndMonth.set(Calendar.YEAR,year);
            calEndMonth.set(Calendar.MONTH,month);
            calEndMonth.set(Calendar.DAY_OF_MONTH,calender.GetCountDaysOfMonth(calender.IsLeapYear(year)).get(month));


            return event.getEventsByMonth(calStartMonth, calEndMonth);
    }

    public void setEventOnDay(Button button){
        for(Event e:EventByMonth){

            if(e.getDateStart().get(e.getDateStart().DAY_OF_MONTH) == Integer.parseInt(button.getText())){
                button.setStyle("-fx-color: blue;");
            }
        }
    }
}
