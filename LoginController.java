/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Login;

import DataBase.DataBaseUtil;
import Session.SessionApp;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javafx.scene.control.TextInputDialog;


/**
 * FXML Controller class
 *
 * @author brato
 */
public class LoginController implements Initializable {

    @FXML
    private Button btnLogIn;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    
    private DataBaseUtil DB = new DataBaseUtil();
    @FXML
    private VBox contentVBox;
    
    private String emailDestination = "";
    
    private double codeEmail;
    
    private double valueD;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }   
    
    

    @FXML
    private void logIn(ActionEvent event) throws IOException {
        String user = username.getText();
        String passwordu = password.getText();
        boolean loginSuccessFull = DB.logInUser(user, passwordu);

        if (loginSuccessFull) {
            // Establecer el usuario en la sesión
            SessionApp session = SessionApp.getInstance();
            session.setLoggedUser(user);

            mostrarAlerta("Log in successful, redirecting to app.");

            // Cargar la nueva vista
            FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/InternApplication/InternApplication.fxml"));
            Parent root = cargadorApp.load();
            btnLogIn.getScene().setRoot(root);
        } else {
            mostrarAlerta("Log in unsuccessful, check username or password");
        }
    }

    
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Log in");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    
}
