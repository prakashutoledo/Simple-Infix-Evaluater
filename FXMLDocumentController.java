package infixevaluatergui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FXMLDocumentController implements Initializable {

    private Label label;
    @FXML
    private JFXTextField prefixTf;
    @FXML
    private JFXButton evaluateButton;
    @FXML
    private JFXButton crearButton;
    @FXML
    private JFXTextArea textArea1;
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXButton quitButton;
    @FXML
    private Label resultLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textArea1.setVisible(false);
        resultLabel.setVisible(false);
        evaluateButton.setDefaultButton(true);
    }

    @FXML
    private void evaluateButtonAction(ActionEvent event) {
        Alert alert1, alert2, alert3, alert4, alert5;
        String input;
        
        input = prefixTf.getText();
        input = input.trim();
        
        if (input.isEmpty()) {
            prefixTf.cancelEdit();
            alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setContentText("Expression can't be empty.");
            alert1.showAndWait();
            
        } else if (Pattern.compile("\\D\\Z").matcher(input).find() == true) {
            prefixTf.cancelEdit();
            alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText("Not a valid expression with an extra operator.");
            alert2.showAndWait();
            
        } else if (Pattern.compile("\\A\\D").matcher(input).find() == true) { 
            prefixTf.cancelEdit();
            alert3 = new Alert(Alert.AlertType.ERROR);
            alert3.setContentText("Not a valid expression with an extra operator.");
            alert3.showAndWait();
            
        } else {
            evaluateButton.setVisible(false);
            prefixTf.setEditable(false);
            resultLabel.setVisible(true);
            
            try {
                InfixEvaluater infix = new InfixEvaluater(input);
                String result = infix.getResult();
                print(infix.line.toString());
                print("The prefix evaluation of " + input + " = " + result);
                textArea1.setVisible(true);
                
            } catch (EmptyStackException | PrefixException e) {
                alert4 = new Alert(Alert.AlertType.ERROR);
                alert4.setContentText(e.getMessage());
                alert4.showAndWait();
                textArea1.setVisible(false);
                resultLabel.setVisible(false);
                evaluateButton.setVisible(true);
                
            } catch (NumberFormatException exp) {
                alert5 = new Alert(Alert.AlertType.ERROR);
                alert5.setContentText("Not a valid expression: " + exp.getMessage());
                alert5.showAndWait();
                textArea1.setVisible(false);
                resultLabel.setVisible(false);
                evaluateButton.setVisible(true);
            }
        }
    }

    @FXML
    private void clearButtonAction(ActionEvent event) {
        loadWindow("FXMLDocument.fxml", "");
        pane.getScene().getWindow().hide();
    }

    public void print(String line) {
        textArea1.appendText("\n" + line);
    }

    void loadWindow(String title, String header) {
        Parent parent;
        Stage stage;
        try {
            parent = FXMLLoader.load(getClass().getResource(title));
            stage  = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.setTitle(header);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void quitButtonAction(ActionEvent event) {
        System.exit(0);
    }

}
