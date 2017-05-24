package sample.Views;

import com.sun.org.apache.xpath.internal.operations.Variable;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.DomainClasses.Bestelling;

import java.awt.*;
import java.awt.Button;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Observable;

public class Controller extends IController {
    private AnchorPane AnchorPane;
    public void setAnchorpane(AnchorPane anchorPane){AnchorPane = anchorPane;}
    public void setLayout(){

        javafx.scene.control.Button btnBestelling = new javafx.scene.control.Button();
        btnBestelling.setLayoutX(0);
        btnBestelling.setLayoutY(0);
        btnBestelling.setMaxWidth(170);
        btnBestelling.setMaxHeight(342);
        btnBestelling.setText("Nieuwe Bestelling");
        btnBestelling.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            newStage("Bestelling.fxml",new BestellingController());
            }
        });
        AnchorPane.getChildren().add(btnBestelling);

        javafx.scene.control.Button btnVeranderen = new javafx.scene.control.Button();
        btnVeranderen.setLayoutX(0);
        btnVeranderen.setLayoutY(100);
        btnVeranderen.setText("Verander Product");
        AnchorPane.getChildren().add(btnVeranderen);

        javafx.scene.control.Button btnWinstOmzet = new javafx.scene.control.Button();
        btnWinstOmzet.setLayoutY(50);
        btnWinstOmzet.setLayoutX(0);
        btnWinstOmzet.setMinWidth(170);
        btnWinstOmzet.setText("Omzet Bekijken");
        AnchorPane.getChildren().add(btnWinstOmzet);
    }

    public void newStage(String filename, IController controller){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(filename));
            AnchorPane anchorPane = (AnchorPane) loader.load();
            controller = new BestellingController();
            controller.setAnchorpane(anchorPane);
            controller.setLayout();
            Stage stage = new Stage();
            stage.setTitle("Nieuwe Bestelling");
            stage.setScene(new Scene(anchorPane));
            stage.show();
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
}
