package sample;
import com.sun.org.apache.xerces.internal.impl.dv.xs.DateTimeDV;
import javafx.scene.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

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
    private @FXML
    AnchorPane anchorPane;
    private @FXML
    javafx.scene.control.TextField tfEndDate;
    private @FXML
    javafx.scene.control.TextField tfStartDate;
    private @FXML
    javafx.scene.control.TextField tfNameEvent;
    private @FXML
    TextArea tfEventDescription;
    private Calendar Date;
    @FXML public void initialize() {
        tfStartDate.setText(Date.get(Date.YEAR) + "-" + Date.get(Date.MONTH) + "-" + Date.get(Date.DATE) + " 00:00");
        tfEndDate.setText(Date.get(Date.YEAR) + "-" + Date.get(Date.MONTH) + "-" + Date.get(Date.DATE) + " 23:59");
    }

    public void getDate(Calendar date){
        Date = date;
    }

    public void saveEvent(){
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
    }

    public void addEventsToListBox(ArrayList<Event> events){
        for (Event e:events){

            if(e.getDateStart() == Date){

            }
        }
    }
}
