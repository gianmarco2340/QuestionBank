/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package InternApplication;

import DataBase.DataBaseUtil;
import Session.SessionApp;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author brato
 */
public class InternApplicationController implements Initializable {

    @FXML
    private Button btnAddQuestion;
    @FXML
    private Button btnProfile;
    @FXML
    private Button btnMassUpload;
    
    private DataBaseUtil DB = new DataBaseUtil();
    @FXML
    private Button btnDownLoadQuestions;
    @FXML
    private Button btnEditQuestion;
    @FXML
    private ImageView btnDashBoard;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnLogHistory;
    @FXML
    private Button btnLogOut;
    @FXML
    private Button insertQuestionBtnDashboard;
    @FXML
    private Button massUploadBTNdashboard;
    @FXML
    private Button btnEditQuestionDashboard;
    @FXML
    private Button btnDeleteQuestionDashboard;
    @FXML
    private Button btnDownloadQuestionDashboard;
    @FXML
    private Button btnLogHistoryDashboard;
    @FXML
    private Button btnSIQ;
    @FXML
    private Button btnSGQ;
    @FXML
    private Button btnSQIDB;
    @FXML
    private Button btnSQGDB;
    @FXML
    private Text internAppIDLabel;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        String user = DB.getUserName();
        System.out.println("User Intern App: " + user);
        
        internAppIDLabel.setText(user);
        
         
    }    


    @FXML
    private void profile(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/Profile/profile.fxml"));
        Parent root = cargadorApp.load();
        
        // Get the current stage and set the new scene
        Scene scene = btnAddQuestion.getScene();
        if (scene != null) {
            scene.setRoot(root);
        } else {
            Stage stage = (Stage) btnAddQuestion.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }

    @FXML
    private void massUpLoad(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/MassUpload/MassUploader.fxml"));
        Parent root = cargadorApp.load();
        
        // Get the current stage and set the new scene
        Scene scene = btnAddQuestion.getScene();
        if (scene != null) {
            scene.setRoot(root);
        } else {
            Stage stage = (Stage) btnAddQuestion.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }

    @FXML
    private void downloadQuestions(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/DownloadQuestion/DownloadQuestion.fxml"));
        Parent root = cargadorApp.load();
        
        // Get the current stage and set the new scene
        Scene scene = btnAddQuestion.getScene();
        if (scene != null) {
            scene.setRoot(root);
        } else {
            Stage stage = (Stage) btnAddQuestion.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }

    @FXML
    private void addQuestion(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/InsertQuestion/InsertQuestion.fxml"));
        Parent root = cargadorApp.load();
        
        // Get the current stage and set the new scene
        Scene scene = btnAddQuestion.getScene();
        if (scene != null) {
            scene.setRoot(root);
        } else {
            Stage stage = (Stage) btnAddQuestion.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }


    @FXML
    private void editQuestionSingle(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
        Parent root = cargadorApp.load();
        
        // Get the current stage and set the new scene
        Scene scene = btnAddQuestion.getScene();
        if (scene != null) {
            scene.setRoot(root);
        } else {
            Stage stage = (Stage) btnAddQuestion.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }

    private void searchQuestionsGeneral(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/SearchQuestion/searchQuestionGeneral.fxml"));
        Parent root = cargadorApp.load();
        
        // Get the current stage and set the new scene
        Scene scene = btnAddQuestion.getScene();
        if (scene != null) {
            scene.setRoot(root);
        } else {
            Stage stage = (Stage) btnAddQuestion.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }

    @FXML
    private void dashBoard(MouseEvent event) {
    }

    @FXML
    private void deleteQuestion(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/DeleteQuestion/deleteQuestion.fxml"));
        Parent root = cargadorApp.load();
        
        // Get the current stage and set the new scene
        Scene scene = btnAddQuestion.getScene();
        if (scene != null) {
            scene.setRoot(root);
        } else {
            Stage stage = (Stage) btnAddQuestion.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }

    @FXML
    private void logHistory(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/logsDB/logsDB.fxml"));
        Parent root = cargadorApp.load();
        
        // Get the current stage and set the new scene
        Scene scene = btnAddQuestion.getScene();
        if (scene != null) {
            scene.setRoot(root);
        } else {
            Stage stage = (Stage) btnAddQuestion.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }

    @FXML
    private void logOut(ActionEvent event) {
        String user = DB.getUserName();
        DB.setUserNameLogOut(user);
        Stage stage = (Stage) btnAddQuestion.getScene().getWindow();
        stage.close();
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void insertQuestion(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/InsertQuestion/InsertQuestion.fxml"));
        Parent root = cargadorApp.load();
        
        // Get the current stage and set the new scene
        Scene scene = btnAddQuestion.getScene();
        if (scene != null) {
            scene.setRoot(root);
        } else {
            Stage stage = (Stage) btnAddQuestion.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }

    @FXML
    private void searchGeneralQuestions(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/SearchQuestion/searchQuestionGeneral.fxml"));
        Parent root = cargadorApp.load();
        
        // Get the current stage and set the new scene
        Scene scene = btnAddQuestion.getScene();
        if (scene != null) {
            scene.setRoot(root);
        } else {
            Stage stage = (Stage) btnAddQuestion.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }

    @FXML
    private void searchIndividualQuestions(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/SearchQuestion/searchQuestion.fxml"));
        Parent root = cargadorApp.load();
        
        // Get the current stage and set the new scene
        Scene scene = btnAddQuestion.getScene();
        if (scene != null) {
            scene.setRoot(root);
        } else {
            Stage stage = (Stage) btnAddQuestion.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
        
    }

    @FXML
    private void searchQuestionIndividualDashBoard(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/SearchQuestion/searchQuestion.fxml"));
        Parent root = cargadorApp.load();
        
        // Get the current stage and set the new scene
        Scene scene = btnAddQuestion.getScene();
        if (scene != null) {
            scene.setRoot(root);
        } else {
            Stage stage = (Stage) btnAddQuestion.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }

    @FXML
    private void searchGeneralQuestionsDashBoard(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/SearchQuestion/searchQuestionGeneral.fxml"));
        Parent root = cargadorApp.load();
        
        // Get the current stage and set the new scene
        Scene scene = btnAddQuestion.getScene();
        if (scene != null) {
            scene.setRoot(root);
        } else {
            Stage stage = (Stage) btnAddQuestion.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }
    
}
