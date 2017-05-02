package sample;
import com.sun.org.apache.xerces.internal.impl.dv.xs.DateTimeDV;
import javafx.scene.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.awt.*;
import java.awt.TextField;
import java.io.IOException;
import java.sql.SQLException;
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
    private Calendar Date;
    @FXML public void initialize() {

    }

    public void getDate(Calendar date){
        tfStartDate.setText(date.getInstance().get(Calendar.YEAR) + "/" + date.getInstance().get(Calendar.MONTH) + "/" + date.getInstance().get(Calendar.DATE) + " 00:00");
        tfEndDate.setText(date.getInstance().get(Calendar.YEAR) + "/" + date.getInstance().get(Calendar.MONTH) + "/" + date.getInstance().get(Calendar.DATE) + " 23:59");
    }
}
