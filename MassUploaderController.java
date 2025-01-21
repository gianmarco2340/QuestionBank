/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package MassUpload;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import DataBase.DataBaseUtil;
import Question.Question;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MassUploaderController implements Initializable {


    private File selectedFileNew;

    private DataBaseUtil DB = new DataBaseUtil();
    @FXML
    private Button btnHome;
    @FXML
    private Button btnAddQuestion;
    @FXML
    private Button btnUploadQuestion;
    @FXML
    private Button btnSearchQuestion;
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
    private Button btnAccept;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSelectFile;
    @FXML
    private Label selectedFile;
    @FXML
    private Button btnDeleteFile;
    @FXML
    private Button btnSearchGenQuestion;
    private Stage stage;
    @FXML
    private Text idUserMassUploader;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        skillComboBox.getItems().addAll("Listening comprehension", "Reading comprehension", "Language use", "Speaking", "Writing");
        difficultyComboBox.getItems().addAll("Pre A1.1", "Pre A1.2", "Pre A1.3","A1.1", "A1.2", "A2.1", "A2.2", "B1.1", "B1.2", "B2.1", "B2.2", "C1.1", "C1.2", "C2.1", "C2.2");
        String username = DB.getUserName();
        idUserMassUploader.setText(username);
    }
    
    @FXML
    private void selectFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        selectedFileNew = fileChooser.showOpenDialog(btnSelectFile.getScene().getWindow());
        if (selectedFileNew != null) {
            selectedFile.setText(selectedFileNew.getAbsolutePath());
            System.out.println("File selected: " + selectedFileNew.getAbsolutePath());
        } else {
            System.out.println("File selection cancelled.");
            
        }
    }
    
    @FXML
    private void accept(ActionEvent event) throws IOException, SQLException {
        if (selectedFileNew == null) {
            System.out.println("No file selected.");
            return;
        }
        String skill = skillComboBox.getValue();
        boolean success = false;
        try{
            List<Question> questions = readCSVFile(selectedFileNew.getAbsolutePath(), skill);
            for (Question question : questions) {
                question.setDifficulty(difficultyComboBox.getValue());
                if (question != null) {
                    System.out.println("Registering question with Content ID: " + question.getContentId());
                    System.out.println("Exam value: " + question.getExam());
                    success = DB.registerQuestionInDataBase(question);
                }
                if (!success) {
                    System.out.println("Failed to insert question" + question.getContentId());
                }
            }
            if (success) {
                System.out.println("Successfully registered");
                mostrarAlerta("Questions successfully registered");
            } else {
                System.out.println("Failed to register questions");
            }
        }catch(IOException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

    public List<Question> readCSVFile(String filePath, String skill) {
        List<Question> questions = new ArrayList<>();
        String line = "";
        String csvSplitBy = ",";
        boolean isFirstLine = true;
        boolean isSkillInCorrect = false;
        boolean isDifficultyInCorrect = false;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] values = line.split(csvSplitBy);
                System.out.println("CSV Line: " + line);
                System.out.println("Parsed Values: " + Arrays.toString(values));
                switch (skill) {
                    case "Language use":
                        if (values.length >= 14) {
                            int contentIDLU = Integer.parseInt(values[0]);
                            String cferLevelLU = values[1];
                            String difficultyLU = values[2];
                            String skillLU = skill;
                            String instructionTextLU = values[4];
                            String questionTextLU = values[5];
                            String optionsTextLU = values[6];
                            String imageLU = values[7];
                            String optionALU = values[8];
                            String optionBLU = values[9];
                            String optionCLU = values[10];
                            String optionDLU = values[11];
                            String correctOptionLU = values[12];
                            String examLU = values[13];
                            
                            Question question = new Question(contentIDLU, cferLevelLU, difficultyLU, skillLU, instructionTextLU, questionTextLU, optionsTextLU, imageLU, optionALU, optionBLU, optionCLU, optionDLU, correctOptionLU, examLU);
                            if(!skillComboBox.getValue().equals(values[3])){
                                isSkillInCorrect = true;
                                break;
                                
                            }
                            if(!difficultyComboBox.getValue().equals(values[2])){
                                isDifficultyInCorrect = true;
                                break;
                            }
                            
                            if(!isSkillInCorrect && !isDifficultyInCorrect){
                                questions.add(question);
                            }
                            
                            

                        }
                        break;
                    case "Listening comprehension":
                        if (values.length >= 16) {
                            int contentIDL = Integer.parseInt(values[0]);
                            String cferLevelL = values[1];
                            String difficultyL = values[2];
                            String skillL = skill;
                            String instructionTextL = values[4];
                            String questionTextL = values[5];
                            String passageL = values[6];
                            String optionsTextL = values[7];
                            String imageLU = values[8];
                            String audioL = values[9];
                            String optionAL = values[10];
                            String optionBL = values[11];
                            String optionCL = values[12];
                            String optionDL = values[13];
                            String correctOptionL = values[14];
                            String examL = values[15];
                            Question question = new Question(contentIDL, cferLevelL, 
                                    difficultyL, skillL, instructionTextL, 
                                    questionTextL, passageL, optionsTextL, imageLU, audioL, 
                                    optionAL, optionBL, optionCL, optionDL, correctOptionL, examL);
                            if(!skillComboBox.getValue().equals(values[3])){
                                isSkillInCorrect = true;
                                break;
                                
                            }
                            if(!difficultyComboBox.getValue().equals(values[2])){
                                isDifficultyInCorrect = true;
                                break;
                            }
                            if(!isSkillInCorrect && !isDifficultyInCorrect){
                                questions.add(question);
                            }
                        }
                        break;
                    case "Reading comprehension":
                        if(values.length >= 15){
                            int contentIDR = Integer.parseInt(values[0]);
                            String cferLevelR = values[1];
                            String difficultyR = values[2];
                            String skillR = skill;
                            String instructionTextR = values[4];
                            String questionTextR = values[5];
                            String passageR = values[6];
                            String optionsTextR = values[7];
                            String imageR = values[8];
                            String optionAR = values[9];
                            String optionBR = values[10];
                            String optionCR = values[11];
                            String optionDR = values[12];
                            String correctOptionR = values[13];
                            String examR = values[14];
                            System.out.println("Exam: " + examR);
                            Question question = new Question(contentIDR, cferLevelR, difficultyR, skillR, 
                                    instructionTextR, questionTextR, passageR, optionsTextR, 
                                    imageR, optionAR, optionBR, optionCR, optionDR, correctOptionR, examR);
                            System.out.println("Question: " + question.toString());
                            if(!skillComboBox.getValue().equals(values[3])){
                                isSkillInCorrect = true;
                                break;
                                
                            }
                            if(!difficultyComboBox.getValue().equals(values[2])){
                                isDifficultyInCorrect = true;
                                break;
                            }
                            if(!isSkillInCorrect && !isDifficultyInCorrect){
                                questions.add(question);
                            }
                        }
                        break;
                    case "Speaking":
                        if(values.length >= 11){
                            int contentIDS = Integer.parseInt(values[0]);
                            String cferLevelS = values[1];
                            String difficultyS = values[2];
                            String skillS = skill;
                            String instructionTextS = values[4];
                            String questionTextS = values[5];
                            String passageS = values[6];
                            String optionTextS = values[7];
                            String imageS = values[8];
                            String audioS = values[9];
                            String examS = values[10];
                            Question question = new Question(contentIDS, cferLevelS, difficultyS, skillS, instructionTextS, questionTextS, passageS, optionTextS, imageS, audioS, examS);
                            System.out.println("Question: " + question.toString());
                            if(!skillComboBox.getValue().equals(values[3])){
                                isSkillInCorrect = true;
                                break;
                                
                            }
                            if(!difficultyComboBox.getValue().equals(values[2])){
                                isDifficultyInCorrect = true;
                                break;
                            }
                            if(!isSkillInCorrect && !isDifficultyInCorrect){
                                questions.add(question);
                            }
                        }
                        break;
                        
                    case "Writing":
                        if (values.length >= 11) {
                            int contentIDWS = Integer.parseInt(values[0]);
                            String cferLevelWS = values[1];
                            String difficultyWS = values[2];
                            String skillWS = skill;
                            String instructionTextWS = values[4];
                            String questionTextWS = values[5];
                            String passageWS = values[6];
                            String optionsTextWS = values[7];
                            String imageWS = values[8];
                            String audioWS = values[9];
                            String examW = values[10];
                            Question question = new Question(contentIDWS, cferLevelWS, difficultyWS, 
                                    skillWS, instructionTextWS, questionTextWS, passageWS, optionsTextWS, imageWS, audioWS, examW);
                            if(!skillComboBox.getValue().equals(values[3])){
                                isSkillInCorrect = true;
                                break;
                                
                            }
                            if(!difficultyComboBox.getValue().equals(values[2])){
                                isDifficultyInCorrect = true;
                                break;
                            }
                            if(!isSkillInCorrect && !isDifficultyInCorrect){
                                questions.add(question);
                            }
                        }
                        break;
                    
                    default:
                        break;
                }
                if (isSkillInCorrect || isDifficultyInCorrect) {
                    break;
                }
            }
            
            if(isSkillInCorrect){
                mostrarAlerta("Skill selected: " + skillComboBox.getValue() + ", is different from the Skill of the file");
                
            }
            if(isDifficultyInCorrect){
                mostrarAlerta("Difficulty selected: " + difficultyComboBox.getValue() + ", is different from the Difficulty of the file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }
    
    @FXML
    private void cancel(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/InternApplication/InternApplication.fxml"));
        Parent root = cargadorApp.load();
        btnDeleteFile.getScene().setRoot(root);
    }
    
    @FXML
    private void deleteFile(ActionEvent event) {
        if (selectedFileNew != null) {
            System.out.println("Attempting to remove file from app context: " + selectedFileNew.getAbsolutePath());

            // Just disassociate the file from the application context
            selectedFile.setText("No file selected");
            selectedFileNew = null;
           
            mostrarAlerta("File removed from app context successfully");
            System.out.println("File removed from app context successfully: " + selectedFileNew.getAbsolutePath());
        }else {
            mostrarAlerta("No file selected to remove");
            System.out.println("No file selected to remove.");
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
    private void goSearchQuestion(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/SearchQuestion/searchQuestion.fxml"));
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

    

    

    
    
}


