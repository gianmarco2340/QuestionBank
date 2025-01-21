/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package InsertQuestion;
import DataBase.DataBaseUtil;
import Question.Question;
import javafx.scene.image.Image;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author brato
 */
public class InsertQuestionController implements Initializable {

    @FXML
    private TextField passageText;
    
    @FXML
    private Button btnCancel;
    
    
    private DataBaseUtil DB = new DataBaseUtil();
    
    private Question newQuestion;
    
    @FXML
    private Button btnHome;
    @FXML
    private Button btnAddQuestion;
    @FXML
    private Button btnUploadQuestion;
    @FXML
    private Button btnEditQuestion;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnDownloadQuestion;
    @FXML
    private Button btnProfile;
    @FXML
    private Button btnLogOut;
    @FXML
    private ComboBox<String> difficultyComboBox;
    @FXML
    private ComboBox<String> skillComboBox;
    @FXML
    private TextField contentIDTextField;
    @FXML
    private TextField instructionTextTextField;
    @FXML
    private TextField questionTextTextField;
    @FXML
    private TextField optionsTextField;
    @FXML
    private TextField imageTextField;
    @FXML
    private TextField audioTextField;
    @FXML
    private TextField optionATextField;
    @FXML
    private TextField optionBTextField;
    @FXML
    private TextField optionCTextField;
    @FXML
    private Button btnAccept;
    @FXML
    private TextField optionDTextField;
    @FXML
    private TextField correctOption;
    @FXML
    private Button btnSearchGenQuestion;
    @FXML
    private Button btnSearchIndQuestions;
    @FXML
    private Button btnLogs;
    @FXML
    private TextField cferLevelTextField;
    @FXML
    private Text difficulty;
    @FXML
    private Text skill;
    @FXML
    private Text contentID;
    @FXML
    private Text instructionText;
    @FXML
    private Text questionText;
    @FXML
    private Text passage;
    @FXML
    private Text optionsText;
    @FXML
    private Text image;
    @FXML
    private Text audio;
    @FXML
    private Text optionA;
    @FXML
    private Text optionB;
    @FXML
    private Text optionC;
    @FXML
    private Text optionD;
    @FXML
    private Text cferLevel;
    @FXML
    private Text correctOptionText;
    @FXML
    private Text insertQuestionIDLabel;
    @FXML
    private Text optionC1;
    @FXML
    private ComboBox<String> examComboBox;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //Combo box tiene las tres skills
        skillComboBox.getItems().addAll("Listening comprehension", "Reading comprehension", "Language use", "Writing", "Speaking");
        difficultyComboBox.getItems().addAll("Pre A1.1", "Pre A1.2", "Pre A1.3" , "A1.1", "A1.2", "A2.1", "A2.2", "B1.1", "B1.2", "B2.1", "B2.2", "C1.1", "C1.2", "C2.1", "C2.2");
        examComboBox.getItems().addAll("GEP INTEGRATED EXAM LU-LC-SP- ERV - 2024 - AIP", "GEP C1 EXTENDED ENGLISH EXAM - ERV - 2024 - AIP", 
                "GEP C1 EXTENDED ENGLISH EXAM - ERV - 2024 - AIP", "GEP B1 STANDARD ENGLISH EXAM - RV - 2024 - AIP", "GEP B1 STANDARD ENGLISH EXAM - RV - 2024",
                "GEP B1 EXTENDED ENGLISH EXAM - ERV - 2024 - AIP", "GEP YL PLACEMENT TEST STANDARD VERSION - 75Q", "GEP A1 STANDARD ENGLISH EXAM - RV - 2024", 
                "GEP A1 STANDARD ENGLISH EXAM - RV - AIP - 2024", "GEP A1 EXTENDED ENGLISH EXAM - ERV - 2024", "GEP A1 EXTENDED ENGLISH EXAM - ERV - AIP - 2024", 
                "GEP A2 STANDARD ENGLISH EXAM - RV - 2024", "GEP A2 EXTENDED ENGLISH EXAM - ERV - 2024", "GEP A2 STANDARD ENGLISH EXAM - RV - 2024 - AIP", 
                "GEP A2 EXTENDED ENGLISH EXAM - ERV - 2024 - AIP", "GEP A1 READING AND LISTENING ENGLISH EXAM - ERV - AIP - TWEETALIG", "GEP A2 READING AND LISTENING ENGLISH EXAM - ERV- AIP - TWEETALIG",
                "GEP B1 READING AND LISTENING ENGLISH EXAM - ERV - AIP - TWEETALIG", "GEP B2 READING AND LISTENING ENGLISH EXAM - ERV - AIP - TWEETALIG",
                "PRACTICE EXAM GEP YL A1 STANDARD ENGLISH EXAM 2024", "GEP YL A2 STANDARD ENGLISH EXAM - ERV - 2024", "GEP YL A1 STANDARD ENGLISH EXAM - ERV - 2024", 
                "GEP PRACTICE EXAM PRE A1-3 - 2024", "GEP B2 EXTENDED ENGLISH EXAM - ERV - 2024 - AIP M. MILAGROSA", "GEP C1 EXTENDED ENGLISH EXAM - ERV - 2023 - AIP M. MILAGROSA", 
                "PLACEMENT TEST 50 -RV- AIP", "GEP DOLPHINS ENGLISH EXAM (PRE A1-1) - RV - 2024", "GEP BEARS ENGLISH EXAM (PRE A1-2) - RV - 2024",
                "GEP LIONS ENGLISH EXAM (PRE A1-3) - RV - 2024", "GEP PRACTICE EXAM PRE A1-1 - 2024", "GEP PRACTICE EXAM PRE A1-2 - 2024", "GEP B1 3 SKILLS (RC-LS-W) - RV - AIP - 2024",
                "GEP PRACTICE EXAM PRE A1-3 EXTENDED 2024", "GEP INTEGRATED EXTENDED EXAM - RV - 2024 - AIP - TWEETALIG", "GEP INTEGRATED 2 SKILLS (RC AND LU) ENGLISH EXAM 2024 AIP - TWEETALIG",
                "GEP B2 STANDARD ENGLISH EXAM - RV - 2024 - AIP", "GEP B2 EXTENDED ENGLISH EXAM - ERV - 2024 - AIP", "GEP INTEGRATED EXTENDED EXAM - ERV - 2024 - AIP",
                "GEP B2 STANDARD ENGLISH EXAM - RV - 2024", "GEP B2 EXTENDED ENGLISH EXAM - ERV - 2024", "GEP INTEGRATED STANDARD EXAM - RV - 2024", "GEP INTEGRATED STANDARD EXAM - RV - 2024 - AIP",
                "GEP B1 EXTENDED ENGLISH EXAM - ERV - 2024", "GEP B1 EXTENDED ENGLISH EXAM - ERV - LIC. BENALCÁZAR", "GEP INTEGRATED EXTENDED EXAM - ERV - 2024",
                "GEP B2 3 SKILLS (LU - RC - W) - RV - AIP", "GEP C1 STANDARD ENGLISH EXAM - RV - 2024 - AIP", "GEP YL A2 EXTENDED ENGLISH EXAM - ERV - AIP - 2024", "GEP YL A1 EXTENDED ENGLISH EXAM - ERV - 2024",
                "GEP INTEGRATED EXAM - ERV - SPEAKING & LANGUAGE USE AIP", "PLACEMENT TEST 50RQ 6PQ UPB -ERV-", "GEP C1 EXTENDED ENGLISH EXAM - ERV - READING & SPEAKING - AIP", "GEP C1 ENGLISH EXAM - 4 SKILLS - AIP",
                "GEP PLACEMENT TEST - LEAH App - 130Q V4", "GEP PLACEMENT TEST - LEAH App - 130Q V3", "GEP EXTENDED PLACEMENT TEST - B2 - TWEETALIG", "GEP EXTENDED PLACEMENT TEST - B1 - TWEETALIG", "GEP EXTENDED PLACEMENT TEST - A2 - TWEETALIG", 
                "GEP EXTENDED PLACEMENT TEST - A1 - TWEETALIG", "GEP INTEGRATED EXTENDED EXAM - ERV - AIP", "PLACEMENT TEST 50 - RV -", "PLACEMENT TEST 50 - RV - AIP", "PLACEMENT TEST 50RQ 6PQ UPB -ERV- AIP (SEB)",
                "GEP Placement Test - 50Q AIP U. Compensar", "GEP Placement Test - 50Q U. Compensar", "PLACEMENT TEST 50RQ 6PQ -ERV- AIP", "GEP YL PLACEMENT TEST STANDARD VERSION - 75Q - AIP",
                "GEP YL PLACEMENT TEST EXTENDED VERSION - 80Q - AIP", "GEP INTEGRATED STANDARD ENGLISH EXAM 2024 AIP", "GEP INTEGRATED STANDARD ENGLISH EXAM 2024 AIP - TWEETALIG", "GEP INTEGRATED STANDARD ENGLISH EXAM 2024",
                "PRACTICE EXAM GEP B1 EXTENDED VERSION - LIC. BENALCÁZAR", "PRACTICE EXAM GEP B2 EXTENDED VERSION - LIC. BENALCÁZAR", "PRACTICE EXAM GEP C1 EXTENDED VERSION - LIC. BENALCÁZAR", "GEP INTEGRATED EXTENDED ENGLISH EXAM 2024", 
                "GEP INTEGRATED PRODUCTIVE SKILLS ENGLISH EXAM 2024", "GEP B1 STANDARD ENGLISH EXAM - RV2 - AIP", "GEP C1 EXTENDED ENGLISH EXAM - ERV - 2023 - AIP", "PRACTICE GEP YL A2 EXTENDED ENGLISH EXAM 2024", "PLACEMENT TEST 50RQ -SRV- AIP",
                "GEP YL A2 STANDARD ENGLISH EXAM 2024", "PRACTICE EXAM GEP YL A1 EXTENDED ENGLISH EXAM 2024", "GEP INTEGRATED EXTENDED ENGLISH EXAM 2024 AIP", "GEP C1 STANDARD ENGLISH EXAM - RV - 2024",
                "GEP C1 EXTENDED ENGLISH EXAM - ERV - 2023", "ADAPTIVE PLACEMENT TEST - 5L", "ADAPTIVE PLACEMENT TEST - YL 4L", "ADAPTIVE PLACEMENT TEST - YL 4L copy", "ADAPTIVE PLACEMENT TEST - YL", 
                "PRACTICE EXAM GEP INTEGRATED STANDARD VERSION", "GEP A2 EXTENDED ENGLISH EXAM - ERV- AIP", "GEP PLACEMENT TEST - LEAH App - 130Q V2 - AIP", "GEP PLACEMENT TEST STANDARD VERSION -50Q-",
                "GEP PLACEMENT TEST - STANDARD VERSION", "GEP PLACEMENT TEST - EXTENDED VERSION", "GEP PLACEMENT TEST - LEAH App - 130Q V1", "GEP PLACEMENT TEST - LEAH App - 130Q V2", 
                "GEP PLACEMENT TEST - LEAH App - 130Q V1 - AIP", "PRACTICE EXAM GEP A1 STANDARD VERSION", "PRACTICE EXAM GEP A1 EXTENDED VERSION", "GEP A1 STANDARD ENGLISH EXAM - RV -", "GEP A1 STANDARD ENGLISH EXAM - RV - AIP", 
                "GEP A1 EXTENDED ENGLISH EXAM - ERV - AIP", "PRACTICE EXAM GEP A2 STANDARD VERSION", "PRACTICE EXAM GEP A2 EXTENDED VERSION - 4 SKILLS", "GEP A2 STANDARD ENGLISH EXAM -RV-", "GEP A2 STANDARD ENGLISH EXAM -RV- AIP",
                "GEP A2 EXTENDED ENGLISH EXAM - ERV-", "PRACTICE EXAM GEP B1 STANDARD VERSION", "PRACTICE EXAM GEP B1 EXTENDED VERSION", "GEP B1 STANDARD ENGLISH EXAM - RV -", "GEP B1 STANDARD ENGLISH EXAM - RV - AIP",
                "GEP B1 EXTENDED ENGLISH EXAM - ERV -", "GEP B1 EXTENDED ENGLISH EXAM - ERV - AIP", "PRACTICE EXAM GEP B2 STANDARD VERSION", "PRACTICE EXAM GEP B2 EXTENDED VERSION", "GEP B2 STANDARD ENGLISH EXAM - RV -",
                "GEP B2 STANDARD ENGLISH EXAM - RV - AIP", "GEP B2 EXTENDED ENGLISH EXAM - ERV -", "GEP B2 EXTENDED ENGLISH EXAM - ERV - AIP", "PRACTICE EXAM GEP C1 STANDARD VERSION", "PRACTICE EXAM GEP C1 EXTENDED VERSION", 
                "GEP C1 STANDARD ENGLISH EXAM - RV -", "GEP INTEGRATED STANDARD ENGLISH EXAM - IAP", "GEP A2 EXTENDED ENGLISH EXAM 4 SKILLS - ERV -", "GEP C1 EXTENDED ENGLISH EXAM - ERV - AIP", 
                "PRACTICE EXAM GEP B1 - 4 SKILLS -", "GEP B1 EXTENDED ENGLISH EXAM 4 SKILLS - ERV -", "GEP B2 EXTENDED ENGLISH EXAM LURCW - ERV -", "PRACTICE EXAM GEP INTEGRATED EXTENDED VERSION");
        
        String username = DB.getUserName();
        insertQuestionIDLabel.setText(username);

        skillComboBox.valueProperty().addListener((observable, oldValue, newValue) ->{
            if("Listening comprehension".equals(newValue)){
                audioTextField.disableProperty().set(false);
                contentIDTextField.disableProperty().set(false);
                cferLevelTextField.disableProperty().set(false);
                difficultyComboBox.disableProperty().set(false);
                skill.disableProperty().set(false);
                instructionTextTextField.disableProperty().set(false);
                questionTextTextField.disableProperty().set(false);
                optionsTextField.disableProperty().set(false);
                passageText.disableProperty().set(false);
                image.disableProperty().set(false);
                optionATextField.disableProperty().set(false);
                optionBTextField.disableProperty().set(false);
                optionCTextField.disableProperty().set(false);
                optionDTextField.disableProperty().set(false);
                correctOption.disableProperty().set(false);
            }
            if("Language use".equals(newValue)){
                passageText.disableProperty().set(true);
                audioTextField.disableProperty().set(true);
                
                contentIDTextField.disableProperty().set(false);
                cferLevelTextField.disableProperty().set(false);
                difficultyComboBox.disableProperty().set(false);
                skill.disableProperty().set(false);
                instructionTextTextField.disableProperty().set(false);
                questionTextTextField.disableProperty().set(false);
                optionsTextField.disableProperty().set(false);
                image.disableProperty().set(false);
                optionATextField.disableProperty().set(false);
                optionBTextField.disableProperty().set(false);
                optionCTextField.disableProperty().set(false);
                optionDTextField.disableProperty().set(false);
                correctOption.disableProperty().set(false);
                examComboBox.disableProperty().set(false);
            }
            if("Reading comprehension".equals(newValue)){
                audioTextField.disableProperty().set(true);
                
                contentIDTextField.disableProperty().set(false);
                cferLevelTextField.disableProperty().set(false);
                difficultyComboBox.disableProperty().set(false);
                skill.disableProperty().set(false);
                instructionTextTextField.disableProperty().set(false);
                questionTextTextField.disableProperty().set(false);
                optionsTextField.disableProperty().set(false);
                passageText.disableProperty().set(false);
                image.disableProperty().set(false);
                optionATextField.disableProperty().set(false);
                optionBTextField.disableProperty().set(false);
                optionCTextField.disableProperty().set(false);
                optionDTextField.disableProperty().set(false);
                correctOption.disableProperty().set(false);
                examComboBox.disableProperty().set(false);
            }
            if("Writing".equals(newValue)){
                audioTextField.disableProperty().set(false);
                contentIDTextField.disableProperty().set(false);
                cferLevelTextField.disableProperty().set(false);
                difficultyComboBox.disableProperty().set(false);
                skill.disableProperty().set(false);
                instructionTextTextField.disableProperty().set(false);
                questionTextTextField.disableProperty().set(false);
                optionsTextField.disableProperty().set(false);
                passageText.disableProperty().set(false);
                image.disableProperty().set(false);
                optionATextField.disableProperty().set(true);
                optionBTextField.disableProperty().set(true);
                optionCTextField.disableProperty().set(true);
                optionDTextField.disableProperty().set(true);
                correctOption.disableProperty().set(true);
                examComboBox.disableProperty().set(false);
            }
            if("Speaking".equals(newValue)){
                audioTextField.disableProperty().set(false);
                contentIDTextField.disableProperty().set(false);
                cferLevelTextField.disableProperty().set(false);
                difficultyComboBox.disableProperty().set(false);
                skill.disableProperty().set(false);
                instructionTextTextField.disableProperty().set(false);
                questionTextTextField.disableProperty().set(false);
                optionsTextField.disableProperty().set(false);
                passageText.disableProperty().set(false);
                image.disableProperty().set(false);
                optionATextField.disableProperty().set(true);
                optionBTextField.disableProperty().set(true);
                optionCTextField.disableProperty().set(true);
                optionDTextField.disableProperty().set(true);
                correctOption.disableProperty().set(true);
                examComboBox.disableProperty().set(false);
            }
        });
        
        
    }    

    

    @FXML
    private void accept(ActionEvent event) throws IOException, SQLException {
        int contentIDDB = Integer.parseInt(contentIDTextField.getText());
        String cferLevelDB = cferLevelTextField.getText();
        String skillDB = skillComboBox.getValue();
        String difficultyDB = difficultyComboBox.getValue();
        String instructionTextDB = instructionTextTextField.getText();
        String questionTextDB = questionTextTextField.getText();
        String optionsTextDB = optionsTextField.getText();
        String passageTextDB = passageText.getText();
        String audioDB = audioTextField.getText();
        String optionADB = optionATextField.getText();
        String optionBDB = optionBTextField.getText();
        String optionCDB = optionCTextField.getText();
        String optionDDB = optionDTextField.getText();
        String correctOptionDB = correctOption.getText();
        String imagen = imageTextField.getText();
        String examDB = examComboBox.getValue();
        
        
        boolean registeredQuestion = false;
        
        
        //Language use doesn't have passage
        if(skillDB.equalsIgnoreCase("Language use")){
            newQuestion = new Question(contentIDDB, cferLevelDB, difficultyDB, skillDB, instructionTextDB, questionTextDB, optionsTextDB, imagen, optionADB, optionBDB, optionCDB, optionDDB, correctOptionDB, examDB);
            registeredQuestion = DB.registerQuestionInDataBase(newQuestion);
            DB.setUserNameActionAdditionQuestion(DB.getUserName());
            
        }
        //Questions with Images and no passage and audio
        if(skillDB.equalsIgnoreCase("Listening comprehension")){
            newQuestion = new Question(contentIDDB, cferLevelDB,difficultyDB, skillDB, instructionTextDB, questionTextDB, passageTextDB, optionsTextDB, imagen, audioDB, optionADB, optionBDB, optionCDB, optionDDB, correctOptionDB, examDB);
            registeredQuestion = DB.registerQuestionInDataBase(newQuestion);
            DB.setUserNameActionAdditionQuestion(DB.getUserName());
        }
        if(skillDB.equalsIgnoreCase("Reading comprehension")){
            newQuestion = new Question(contentIDDB, cferLevelDB, difficultyDB, skillDB, instructionTextDB, questionTextDB, passageTextDB, optionsTextDB, imagen, audioDB, optionADB, optionBDB, optionCDB, optionDDB, correctOptionDB, examDB);
            registeredQuestion = DB.registerQuestionInDataBase(newQuestion);
            DB.setUserNameActionAdditionQuestion(DB.getUserName());
            
        }
        
        if(skillDB.equalsIgnoreCase("Writing")){
            newQuestion = new Question(contentIDDB, cferLevelDB,difficultyDB, skillDB, instructionTextDB, questionTextDB, passageTextDB, optionsTextDB, imagen, audioDB, examDB);
            try{
                registeredQuestion = DB.registerQuestionInDataBase(newQuestion);
            }catch(SQLException e){
                e.printStackTrace();
            }
            
            DB.setUserNameActionAdditionQuestion(DB.getUserName());
        }
        
        if(skillDB.equalsIgnoreCase("Speaking")){
            newQuestion = new Question(contentIDDB, cferLevelDB,difficultyDB, skillDB, instructionTextDB, questionTextDB, passageTextDB, optionsTextDB, imagen, audioDB, examDB);
            try{
                registeredQuestion = DB.registerQuestionInDataBase(newQuestion);
            }catch(SQLException e){
                e.printStackTrace();
            }
           
            DB.setUserNameActionAdditionQuestion(DB.getUserName());
        }
        
        if(registeredQuestion){
            mostrarAlerta("Pregunta registrada con exito");
        }else{
            mostrarAlerta("Pregunta no registrada");
        }
          
    }
    
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Log in");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void cancel(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/InternApplication/InternApplication.fxml"));
        Parent root = cargadorApp.load();
        btnAccept.getScene().setRoot(root);
    }

    @FXML
    private void goHome(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/InternApplication/InternApplication.fxml"));
        Parent root = cargadorApp.load();
        btnCancel.getScene().setRoot(root);
    }

    @FXML
    private void goAddQuestion(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/InsertQuestion/InsertQuestion.fxml"));
        Parent root = cargadorApp.load();
        btnCancel.getScene().setRoot(root);
    }

    @FXML
    private void goUploadQuestion(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/MassUpload/MassUploader.fxml"));
        Parent root = cargadorApp.load();
        btnCancel.getScene().setRoot(root);
    }

    

    @FXML
    private void goEditQuestion(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
        Parent root = cargadorApp.load();
        btnCancel.getScene().setRoot(root);
    }

    @FXML
    private void goDeleteQuestion(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/DeleteQuestion/deleteQuestion.fxml"));
        Parent root = cargadorApp.load();
        btnCancel.getScene().setRoot(root);
    }

    @FXML
    private void goDownloadQuestion(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/DownloadQuestion/DownloadQuestion.fxml"));
        Parent root = cargadorApp.load();
        btnCancel.getScene().setRoot(root);
    }

    @FXML
    private void goProfile(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/Profile/profile.fxml"));
        Parent root = cargadorApp.load();
        btnCancel.getScene().setRoot(root);
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
    private void goSearchGenQuestion(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/SearchQuestion/searchQuestionGeneral.fxml"));
        Parent root = cargadorApp.load();
        btnCancel.getScene().setRoot(root);
    }

    @FXML
    private void goSearchIndQuestion(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/SearchQuestion/searchQuestion.fxml"));
        Parent root = cargadorApp.load();
        btnCancel.getScene().setRoot(root);
    }

    @FXML
    private void goToLogs(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/logsDB/logsDB.fxml"));
        Parent root = cargadorApp.load();
        btnCancel.getScene().setRoot(root);
    }

    
}
