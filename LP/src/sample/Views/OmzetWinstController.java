package sample.Views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.DomainClasses.Bestelling;
import sample.ViewModel.BestellingUIRepo;

/**
 * Created by maxhe on 7-6-2017.
 */
public class OmzetWinstController extends IController {
    private AnchorPane Anchorpane;
    private TextField tfDatum;
    private Button btnOK;
    private Label lblOmzetWinst;


    public void setAnchorpane(AnchorPane anchorpane){Anchorpane = anchorpane;}

    public void setLayout(){
        tfDatum = new TextField();
        tfDatum.setLayoutY(10);
        tfDatum.setLayoutX(10);
        tfDatum.setPrefWidth(100);
        tfDatum.setPrefHeight(27);
        tfDatum.setPromptText("yyyy-mm-dd");
        Anchorpane.getChildren().add(tfDatum);

        btnOK = new Button();
        btnOK.setLayoutY(55);
        btnOK.setLayoutX(10);
        btnOK.setPrefWidth(100);
        btnOK.setPrefHeight(27);
        btnOK.setText("OK!");
        btnOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BestellingUIRepo repo = new BestellingUIRepo(new Bestelling());
                lblOmzetWinst.setText(repo.OmzetWinst(tfDatum.getText()));
            }
        });
        Anchorpane.getChildren().add(btnOK);

        lblOmzetWinst = new Label();
        lblOmzetWinst.setLayoutX(120);
        lblOmzetWinst.setLayoutY(10);
        lblOmzetWinst.setPrefHeight(27);
        lblOmzetWinst.setPrefWidth(200);
    Anchorpane.getChildren().add(lblOmzetWinst);
    }
}
