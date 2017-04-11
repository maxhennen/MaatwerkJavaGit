package sample;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;

public class Controller {
    @FXML TextField tfGetal1;
    @FXML TextField tfGetal2;
    @FXML TextField tfUitkomst;

    public void Plus(ActionEvent actionEvent) {
        float uitkomst = Float.parseFloat(tfGetal1.getText()) + Float.parseFloat(tfGetal2.getText());
        tfUitkomst.setText(String.valueOf(uitkomst));
    }

    public void Min(ActionEvent actionEvent) {
        float uitkomst = Float.parseFloat(tfGetal1.getText()) - Float.parseFloat(tfGetal2.getText());
        tfUitkomst.setText(String.valueOf(uitkomst));
    }

    public void Devide(ActionEvent actionEvent) {
        float uitkomst = Float.parseFloat(tfGetal1.getText()) / Float.parseFloat(tfGetal2.getText());
        tfUitkomst.setText(String.valueOf(uitkomst));
    }

    public void Multipl(ActionEvent actionEvent) {
        float uitkomst = Float.parseFloat(tfGetal1.getText()) * Float.parseFloat(tfGetal2.getText());
        tfUitkomst.setText(String.valueOf(uitkomst));
    }
}
