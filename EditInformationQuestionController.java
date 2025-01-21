/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package EditQuestion;

import DataBase.DataBaseUtil;
import Question.Question;
import java.io.IOException;
import java.sql.Connection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author brato
 */
public class EditInformationQuestionController implements Initializable {
    @FXML
    private TextField contentIDLabel;
    @FXML
    private TextField InstructionTextLabel;
    @FXML
    private TextField QuestionTextLabel;
    @FXML
    private TextField passageLabel;
    @FXML
    private TextField optionsTextLabel;
    @FXML
    private TextField imageLabel;
    @FXML
    private TextField audioLabel;
    @FXML
    private TextField optionALabel;
    @FXML
    private TextField optionBLabel;
    @FXML
    private TextField optionCLabel;
    @FXML
    private TextField optionDLabel;
    @FXML
    private TextField CorrectOptionLabel;
    @FXML
    private Button btnAccept;
    @FXML
    private Button btnCancel;
    
    private Question question;
    
    private DataBaseUtil DB = new DataBaseUtil();
    
    
    
    private int originalContentId;
    private Button btnAddQuestion;
    @FXML
    private Text userIDEditInformation;
    @FXML
    private Button btnHome1;
    @FXML
    private Button btnAddQuestion1;
    @FXML
    private Button btnUploadQuestion1;
    @FXML
    private Button btnSearchQuestion1;
    @FXML
    private Button btnSearchGenQuestion;
    @FXML
    private Button btnEditQuestion1;
    @FXML
    private Button btnDelete1;
    @FXML
    private Button btnDownloadQuestion1;
    @FXML
    private Button btnLog;
    @FXML
    private Button btnProfile1;
    @FXML
    private Button btnLogOut1;
    @FXML
    private ComboBox<String> exam;
    @FXML
    private ComboBox<String> difficultyLabel;
    @FXML
    private ComboBox<String> skillLabel;
    @FXML
    private ComboBox<String> cferLevelTextField;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String username = DB.getUserName();
        userIDEditInformation.setText(username);
        cferLevelTextField.getItems().setAll("Pre A1", "A1", "A2", "B1", "B2", "C1", "C2");
        difficultyLabel.getItems().setAll("Pre A1.1", "Pre A1.2", "Pre A1.3","A1.1", "A1.2","A2.1", "A2.2","B1.1", "B1.2", "B2.1", "B2.2", "C1.1", "C1.2");
        skillLabel.getItems().setAll("Language use", "Reading comprehension", "Listening comprehension", "Speaking", "Writing");
        exam.getItems().setAll("GEP INTEGRATED EXAM LU-LC-SP- ERV - 2024 - AIP", "GEP C1 EXTENDED ENGLISH EXAM - ERV - 2024 - AIP", 
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
        
        
        
        skillLabel.valueProperty().addListener((observable, oldValue, newValue) ->{
            if("Listening comprehension".equals(newValue)){
                cferLevelTextField.disableProperty().set(true);
                
                audioLabel.disableProperty().set(false);
                contentIDLabel.disableProperty().set(false);
                InstructionTextLabel.disableProperty().set(false);
                QuestionTextLabel.disableProperty().set(false);
                optionsTextLabel.disableProperty().set(false);
                passageLabel.disableProperty().set(false);
                imageLabel.disableProperty().set(false);
                optionALabel.disableProperty().set(false);
                optionBLabel.disableProperty().set(false);
                optionCLabel.disableProperty().set(false);
                optionDLabel.disableProperty().set(false);
                CorrectOptionLabel.disableProperty().set(false);
            }
            if("Language use".equals(newValue)){
                passageLabel.disableProperty().set(true);
                audioLabel.disableProperty().set(true);
                cferLevelTextField.disableProperty().set(true);
                
                contentIDLabel.disableProperty().set(false);
                InstructionTextLabel.disableProperty().set(false);
                QuestionTextLabel.disableProperty().set(false);
                optionsTextLabel.disableProperty().set(false);
                imageLabel.disableProperty().set(false);
                optionALabel.disableProperty().set(false);
                optionBLabel.disableProperty().set(false);
                optionCLabel.disableProperty().set(false);
                optionDLabel.disableProperty().set(false);
                CorrectOptionLabel.disableProperty().set(false);
            }
            if("Reading comprehension".equals(newValue)){
                audioLabel.disableProperty().set(true);
                cferLevelTextField.disableProperty().set(true);
                
                contentIDLabel.disableProperty().set(false);
                InstructionTextLabel.disableProperty().set(false);
                QuestionTextLabel.disableProperty().set(false);
                optionsTextLabel.disableProperty().set(false);
                passageLabel.disableProperty().set(false);
                imageLabel.disableProperty().set(false);
                optionALabel.disableProperty().set(false);
                optionBLabel.disableProperty().set(false);
                optionCLabel.disableProperty().set(false);
                optionDLabel.disableProperty().set(false);
                CorrectOptionLabel.disableProperty().set(false);
            }
            if("Writing".equals(newValue)){
                cferLevelTextField.disableProperty().set(true);
                
                audioLabel.disableProperty().set(false);
                contentIDLabel.disableProperty().set(false);
                InstructionTextLabel.disableProperty().set(false);
                QuestionTextLabel.disableProperty().set(false);
                optionsTextLabel.disableProperty().set(false);
                passageLabel.disableProperty().set(false);
                imageLabel.disableProperty().set(false);
                optionALabel.disableProperty().set(true);
                optionBLabel.disableProperty().set(true);
                optionCLabel.disableProperty().set(true);
                optionDLabel.disableProperty().set(true);
                CorrectOptionLabel.disableProperty().set(true);
            }
            if("Speaking".equals(newValue)){
                audioLabel.disableProperty().set(false);
                cferLevelTextField.disableProperty().set(true);
                contentIDLabel.disableProperty().set(false);
                InstructionTextLabel.disableProperty().set(false);
                QuestionTextLabel.disableProperty().set(false);
                optionsTextLabel.disableProperty().set(false);
                passageLabel.disableProperty().set(false);
                imageLabel.disableProperty().set(false);
                optionALabel.disableProperty().set(true);
                optionBLabel.disableProperty().set(true);
                optionCLabel.disableProperty().set(true);
                optionDLabel.disableProperty().set(true);
                CorrectOptionLabel.disableProperty().set(true);
            }
        });
    }   
    
    
    public void setQuestion(Question question) {
        this.question = question;
        originalContentId = question.getContentId();
        cferLevelTextField.setValue(question.getCferlevel());
        contentIDLabel.setText(String.valueOf(question.getContentId()));
        difficultyLabel.setValue(question.getDifficulty());
        skillLabel.setValue(question.getSkill());
        InstructionTextLabel.setText(question.getInstructionText());
        QuestionTextLabel.setText(question.getQuestionText());
        passageLabel.setText(question.getPassage());
        optionsTextLabel.setText(question.getOptionsText());
        imageLabel.setText(question.getImage());
        audioLabel.setText(question.getAudio());
        optionALabel.setText(question.getOptionA());
        optionBLabel.setText(question.getOptionB());
        optionCLabel.setText(question.getOptionC());
        optionDLabel.setText(question.getOptionD());
        CorrectOptionLabel.setText(question.getCorrectOption());
        exam.setValue(question.getExam());
    }
    
    

    @FXML
    private void acceptEdit(ActionEvent event) throws SQLException {
        question.setContentId(Integer.parseInt(contentIDLabel.getText()));
        question.setDifficulty(difficultyLabel.getValue());
        question.setSkill(skillLabel.getValue());
        question.setInstructionText(InstructionTextLabel.getText());
        question.setQuestionText(QuestionTextLabel.getText());
        question.setPassage(passageLabel.getText());
        question.setOptionsText(optionsTextLabel.getText());
        question.setImage(imageLabel.getText());
        question.setAudio(audioLabel.getText());
        question.setOptionA(optionALabel.getText());
        question.setOptionB( optionBLabel.getText());
        question.setOptionC(optionCLabel.getText());
        question.setOptionD(optionDLabel.getText());
        question.setCorrectOption(CorrectOptionLabel.getText());
        question.setExam(exam.getValue());
        question.setCferlevel(cferLevelTextField.getValue());
        System.out.println("Question: " + question.toString());
        dataBaseUpdate(question);
        String user = DB.getUserName();
        DB.setUserNameActionEditQuestion(user);
        
    }
    private void dataBaseUpdate(Question question) throws SQLException {
        
        if (difficultyLabel.getValue().equalsIgnoreCase("Pre A1.1") || 
            difficultyLabel.getValue().equalsIgnoreCase("Pre A1.2") || 
            difficultyLabel.getValue().equalsIgnoreCase("Pre A1.3")){
            if (skillLabel.getValue().equalsIgnoreCase("Language use")) {
                String queryLU = "UPDATE prea1_language_use SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, Options_text = ?, "
                        + "Image = ?, Option_a = ?, Option_b = ?, Option_c = ?, Option_d = ?, Correct_Option = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionPreA1();
                     PreparedStatement pstmt = conn.prepareStatement(queryLU)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, optionsTextLabel.getText());
                    pstmt.setString(8, imageLabel.getText());
                    pstmt.setString(9, optionALabel.getText());
                    pstmt.setString(10, optionBLabel.getText());
                    pstmt.setString(11, optionCLabel.getText());
                    pstmt.setString(12, optionDLabel.getText());
                    pstmt.setString(13, CorrectOptionLabel.getText());
                    pstmt.setString(14, exam.getValue());
                    pstmt.setInt(15, originalContentId);
                    

                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                        
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("SQL Error: " + e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillLabel.getValue().equalsIgnoreCase("Reading comprehension")) {
                System.out.println("Entro a reading comprehension");
                String queryR = "UPDATE prea1_reading SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, Passage = ?, "
                        + "Options_text = ?, Image = ?, Option_a = ?, Option_b = ?, Option_c = ?, Option_d = ?, "
                        + "Correct_Option = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionPreA1();
                    PreparedStatement pstmt = conn.prepareStatement(queryR)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, passageLabel.getText());
                    pstmt.setString(8, optionsTextLabel.getText());
                    pstmt.setString(9, imageLabel.getText());
                    pstmt.setString(10, optionALabel.getText());
                    pstmt.setString(11, optionBLabel.getText());
                    pstmt.setString(12, optionCLabel.getText());
                    pstmt.setString(13, optionDLabel.getText());
                    pstmt.setString(14, CorrectOptionLabel.getText());
                    pstmt.setString(15, exam.getValue());
                    pstmt.setInt(16, originalContentId);

                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    System.out.println("Rows affected: " + rowsAffected);
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("SQL Error: " + e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillLabel.getValue().equalsIgnoreCase("Listening comprehension")) {
                String queryR = "UPDATE prea1_listening SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, Passage = ?, "
                        + "Options_text = ?, Image = ?, Audio = ?, Option_a = ?, Option_b = ?, Option_c = ?, Option_d = ?, "
                        + "Correct_Option = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionPreA1(); 
                     PreparedStatement pstmt = conn.prepareStatement(queryR)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, passageLabel.getText());
                    pstmt.setString(8, optionsTextLabel.getText());
                    pstmt.setString(9, imageLabel.getText());
                    pstmt.setString(10, audioLabel.getText());
                    pstmt.setString(11, optionALabel.getText());
                    pstmt.setString(12, optionBLabel.getText());
                    pstmt.setString(13, optionCLabel.getText());
                    pstmt.setString(14, optionDLabel.getText());
                    pstmt.setString(15, CorrectOptionLabel.getText());
                    pstmt.setString(16, exam.getValue());
                    pstmt.setInt(17, originalContentId);

                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("SQL Error: " + e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (skillLabel.getValue().equalsIgnoreCase("Speaking")) {
                skillLabel.valueProperty().addListener((observable, oldValue, newValue ) ->{
                    if("Speaking".equals(newValue)){
                        passageLabel.setDisable(true);
                        audioLabel.setDisable(true);
                        
                    }
                });
                
                String queryS = "UPDATE prea1_speaking SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, "
                        + "Passage = ?, Options_text = ?, Image = ?, Audio = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionPreA1(); PreparedStatement pstmt = conn.prepareStatement(queryS)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, passageLabel.getText());
                    pstmt.setString(8, optionsTextLabel.getText());
                    pstmt.setString(9, imageLabel.getText());
                    pstmt.setString(10, audioLabel.getText());
                    pstmt.setString(11, exam.getValue());
                    pstmt.setInt(12, originalContentId);
                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillLabel.getValue().equalsIgnoreCase("Writing")) {
                skillLabel.valueProperty().addListener((observable, oldValue, newValue ) ->{
                    if("Writing".equals(newValue)){
                        passageLabel.setDisable(true);
                        audioLabel.setDisable(true);
                        
                    }
                });
                String queryS = "UPDATE prea1_writing SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, "
                        + "Passage = ?, Options_text = ?, Image = ?, Audio = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionPreA1(); PreparedStatement pstmt = conn.prepareStatement(queryS)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, passageLabel.getText());
                    pstmt.setString(8, optionsTextLabel.getText());
                    pstmt.setString(9, imageLabel.getText());
                    pstmt.setString(10, audioLabel.getText());
                    pstmt.setString(11, exam.getValue());
                    pstmt.setInt(12, originalContentId);
                     System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                        
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (difficultyLabel.getValue().equalsIgnoreCase("A1.1") || difficultyLabel.getValue().equalsIgnoreCase("A1.2")){
            if (skillLabel.getValue().equalsIgnoreCase("Language use")) {
                String queryLU = "UPDATE a1_language_use SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_Text = ?, Question_Text = ?, Options_Text = ?, "
                        + "Image = ?, Option_a = ?, Option_b = ?, Option_c = ?, Option_d = ?, Correct_Option = ?, Exam = ? "
                        + "WHERE Content_ID = ?";
                try (Connection conn = DB.getConnectionA1();
                     PreparedStatement pstmt = conn.prepareStatement(queryLU)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, optionsTextLabel.getText());
                    pstmt.setString(8, imageLabel.getText());
                    pstmt.setString(9, optionALabel.getText());
                    pstmt.setString(10, optionBLabel.getText());
                    pstmt.setString(11, optionCLabel.getText());
                    pstmt.setString(12, optionDLabel.getText());
                    pstmt.setString(13, CorrectOptionLabel.getText());
                    pstmt.setString(14, exam.getValue());
                    pstmt.setInt(15, originalContentId);

                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("SQL Error: " + e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillLabel.getValue().equalsIgnoreCase("Reading comprehension")) {
                String queryR = "UPDATE a1_reading SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, Passage = ?, "
                        + "Options_text = ?, Image = ?, Option_a = ?, Option_b = ?, Option_c = ?, Option_d = ?, "
                        + "Correct_Option = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionA1();
                     PreparedStatement pstmt = conn.prepareStatement(queryR)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, passageLabel.getText());
                    pstmt.setString(8, optionsTextLabel.getText());
                    pstmt.setString(9, imageLabel.getText());
                    pstmt.setString(10, optionALabel.getText());
                    pstmt.setString(11, optionBLabel.getText());
                    pstmt.setString(12, optionCLabel.getText());
                    pstmt.setString(13, optionDLabel.getText());
                    pstmt.setString(14, CorrectOptionLabel.getText());
                    pstmt.setString(15, exam.getValue());
                    pstmt.setInt(16, originalContentId);

                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("SQL Error: " + e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillLabel.getValue().equalsIgnoreCase("Listening comprehension")) {
                String queryR = "UPDATE a1_listening SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, Passage = ?, "
                        + "Options_text = ?, Image = ?, Audio = ?, Option_a = ?, Option_b = ?, Option_c = ?, Option_d = ?, "
                        + "Correct_Option = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionA1();
                     PreparedStatement pstmt = conn.prepareStatement(queryR)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, passageLabel.getText());
                    pstmt.setString(8, optionsTextLabel.getText());
                    pstmt.setString(9, imageLabel.getText());
                    pstmt.setString(10, audioLabel.getText());
                    pstmt.setString(11, optionALabel.getText());
                    pstmt.setString(12, optionBLabel.getText());
                    pstmt.setString(13, optionCLabel.getText());
                    pstmt.setString(14, optionDLabel.getText());
                    pstmt.setString(15, CorrectOptionLabel.getText());
                    pstmt.setString(16, exam.getValue());
                    pstmt.setInt(17, originalContentId);

                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("SQL Error: " + e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (skillLabel.getValue().equalsIgnoreCase("Speaking")) {
                skillLabel.valueProperty().addListener((observable, oldValue, newValue ) ->{
                    if("Speaking".equals(newValue)){
                        passageLabel.setDisable(true);
                        audioLabel.setDisable(true);
                        
                    }
                });
                String queryS = "UPDATE a1_speaking SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, "
                        + "Passage = ?, Options_text = ?, Image = ?, Audio = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionA1(); PreparedStatement pstmt = conn.prepareStatement(queryS)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, passageLabel.getText());
                    pstmt.setString(8, optionsTextLabel.getText());
                    pstmt.setString(9, imageLabel.getText());
                    pstmt.setString(10, audioLabel.getText());
                    pstmt.setString(11, exam.getValue());
                    pstmt.setInt(12, originalContentId);
                    
                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillLabel.getValue().equalsIgnoreCase("Writing")) {
                skillLabel.valueProperty().addListener((observable, oldValue, newValue ) ->{
                    if("Writing".equals(newValue)){
                        passageLabel.setDisable(true);
                        audioLabel.setDisable(true);
                        
                    }
                });
                String queryS = "UPDATE a1_writing SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, "
                        + "Passage = ?, Options_text = ?, Image = ?, Audio = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionA1(); PreparedStatement pstmt = conn.prepareStatement(queryS)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, passageLabel.getText());
                    pstmt.setString(8, optionsTextLabel.getText());
                    pstmt.setString(9, imageLabel.getText());
                    pstmt.setString(10, audioLabel.getText());
                    pstmt.setString(11, exam.getValue());
                    pstmt.setInt(12, originalContentId);
                    
                     System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                        
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (difficultyLabel.getValue().equalsIgnoreCase("A2.1") || difficultyLabel.getValue().equalsIgnoreCase("A2.2")){
            if (skillLabel.getValue().equalsIgnoreCase("Language use")) {
                String queryLU = "UPDATE a2_language_use SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, Options_text = ?, "
                        + "Image = ?, Option_a = ?, Option_b = ?, Option_c = ?, Option_d = ?, Correct_Option = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionA2();
                     PreparedStatement pstmt = conn.prepareStatement(queryLU)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, optionsTextLabel.getText());
                    pstmt.setString(8, imageLabel.getText());
                    pstmt.setString(9, optionALabel.getText());
                    pstmt.setString(10, optionBLabel.getText());
                    pstmt.setString(11, optionCLabel.getText());
                    pstmt.setString(12, optionDLabel.getText());
                    pstmt.setString(13, CorrectOptionLabel.getText());
                    pstmt.setString(14, exam.getValue());
                    pstmt.setInt(15, originalContentId);

                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("SQL Error: " + e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillLabel.getValue().equalsIgnoreCase("Reading comprehension")) {
                String queryR = "UPDATE a2_reading SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, Passage = ?, "
                        + "Options_text = ?, Image = ?, Option_a = ?, Option_b = ?, Option_c = ?, Option_d = ?, "
                        + "Correct_Option = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionA2();
                    PreparedStatement pstmt = conn.prepareStatement(queryR)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, passageLabel.getText());
                    pstmt.setString(8, optionsTextLabel.getText());
                    pstmt.setString(9, imageLabel.getText());
                    pstmt.setString(10, optionALabel.getText());
                    pstmt.setString(11, optionBLabel.getText());
                    pstmt.setString(12, optionCLabel.getText());
                    pstmt.setString(13, optionDLabel.getText());
                    pstmt.setString(14, CorrectOptionLabel.getText());
                    pstmt.setString(15, exam.getValue());
                    pstmt.setInt(16, originalContentId);

                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("SQL Error: " + e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillLabel.getValue().equalsIgnoreCase("Listening comprehension")) {
                String queryR = "UPDATE a2_listening SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, Passage = ?, "
                        + "Options_text = ?, Image = ?, Audio = ?, Option_a = ?, Option_b = ?, Option_c = ?, Option_d = ?, "
                        + "Correct_Option = ?, Exam = ?"
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionA2(); 
                     PreparedStatement pstmt = conn.prepareStatement(queryR)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, passageLabel.getText());
                    pstmt.setString(8, optionsTextLabel.getText());
                    pstmt.setString(9, imageLabel.getText());
                    pstmt.setString(10, audioLabel.getText());
                    pstmt.setString(11, optionALabel.getText());
                    pstmt.setString(12, optionBLabel.getText());
                    pstmt.setString(13, optionCLabel.getText());
                    pstmt.setString(14, optionDLabel.getText());
                    pstmt.setString(15, CorrectOptionLabel.getText());
                    pstmt.setString(16, exam.getValue());
                    pstmt.setInt(17, originalContentId);

                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("SQL Error: " + e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (skillLabel.getValue().equalsIgnoreCase("Speaking")) {
                String queryS = "UPDATE a2_speaking SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, "
                        + "Passage = ?, Options_text = ?, Image = ?, Audio = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionA2(); PreparedStatement pstmt = conn.prepareStatement(queryS)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, passageLabel.getText());
                    pstmt.setString(8, optionsTextLabel.getText());
                    pstmt.setString(9, imageLabel.getText());
                    pstmt.setString(10, audioLabel.getText());
                    pstmt.setString(11, exam.getValue());
                    pstmt.setInt(12, originalContentId);
                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillLabel.getValue().equalsIgnoreCase("Writing")) {
                String queryS = "UPDATE a2_writing SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, "
                        + "Passage = ?, Options_text = ?, Image = ?, Audio = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionA2(); PreparedStatement pstmt = conn.prepareStatement(queryS)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, passageLabel.getText());
                    pstmt.setString(8, optionsTextLabel.getText());
                    pstmt.setString(9, imageLabel.getText());
                    pstmt.setString(10, audioLabel.getText());
                    pstmt.setString(11, exam.getValue());
                    pstmt.setInt(12, originalContentId);
                     System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (difficultyLabel.getValue().equalsIgnoreCase("B1.1") || difficultyLabel.getValue().equalsIgnoreCase("B1.2")){
            if (skillLabel.getValue().equalsIgnoreCase("Language use")) {
                String queryLU = "UPDATE b1_language_use SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, Options_text = ?, "
                        + "Image = ?, Option_a = ?, Option_b = ?, Option_c = ?, Option_d = ?, Correct_Option = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionB1();
                     PreparedStatement pstmt = conn.prepareStatement(queryLU)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, optionsTextLabel.getText());
                    pstmt.setString(8, imageLabel.getText());
                    pstmt.setString(9, optionALabel.getText());
                    pstmt.setString(10, optionBLabel.getText());
                    pstmt.setString(11, optionCLabel.getText());
                    pstmt.setString(12, optionDLabel.getText());
                    pstmt.setString(13, CorrectOptionLabel.getText());
                    pstmt.setString(14, exam.getValue());
                    pstmt.setInt(15, originalContentId);

                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("SQL Error: " + e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillLabel.getValue().equalsIgnoreCase("Reading comprehension")) {
                String queryR = "UPDATE b1_reading SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, Passage = ?, "
                        + "Options_text = ?, Image = ?, Option_a = ?, Option_b = ?, Option_c = ?, Option_d = ?, "
                        + "Correct_Option = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionB1();
                    PreparedStatement pstmt = conn.prepareStatement(queryR)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, passageLabel.getText());
                    pstmt.setString(8, optionsTextLabel.getText());
                    pstmt.setString(9, imageLabel.getText());
                    pstmt.setString(10, optionALabel.getText());
                    pstmt.setString(11, optionBLabel.getText());
                    pstmt.setString(12, optionCLabel.getText());
                    pstmt.setString(13, optionDLabel.getText());
                    pstmt.setString(14, CorrectOptionLabel.getText());
                    pstmt.setString(15, exam.getValue());
                    pstmt.setInt(16, originalContentId);

                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("SQL Error: " + e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillLabel.getValue().equalsIgnoreCase("Listening comprehension")) {
                
                String queryR = "UPDATE b1_listening SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, Passage = ?, "
                        + "Options_text = ?, Image = ?, Audio = ?, Option_a = ?, Option_b = ?, Option_c = ?, Option_d = ?, "
                        + "Correct_Option = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionB1(); 
                     PreparedStatement pstmt = conn.prepareStatement(queryR)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, passageLabel.getText());
                    pstmt.setString(8, optionsTextLabel.getText());
                    pstmt.setString(9, imageLabel.getText());
                    pstmt.setString(10, audioLabel.getText());
                    pstmt.setString(11, optionALabel.getText());
                    pstmt.setString(12, optionBLabel.getText());
                    pstmt.setString(13, optionCLabel.getText());
                    pstmt.setString(14, optionDLabel.getText());
                    pstmt.setString(15, CorrectOptionLabel.getText());
                    pstmt.setString(16, exam.getValue());
                    pstmt.setInt(17, originalContentId);

                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("SQL Error: " + e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (skillLabel.getValue().equalsIgnoreCase("Speaking")) {
                String queryS = "UPDATE b1_speaking SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, "
                        + "Passage = ?, Options_text = ?, Image = ?, Audio = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionB1(); PreparedStatement pstmt = conn.prepareStatement(queryS)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, passageLabel.getText());
                    pstmt.setString(8, optionsTextLabel.getText());
                    pstmt.setString(9, imageLabel.getText());
                    pstmt.setString(10, audioLabel.getText());
                    pstmt.setString(11, exam.getValue());
                    pstmt.setInt(12, originalContentId);
                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillLabel.getValue().equalsIgnoreCase("Writing")) {
                String queryS = "UPDATE b1_writing SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, "
                        + "Passage = ?, Options_text = ?, Image = ?, Audio = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionB1(); PreparedStatement pstmt = conn.prepareStatement(queryS)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, passageLabel.getText());
                    pstmt.setString(8, optionsTextLabel.getText());
                    pstmt.setString(9, imageLabel.getText());
                    pstmt.setString(10, audioLabel.getText());
                    pstmt.setString(11, exam.getValue());
                    pstmt.setInt(12, originalContentId);
                     System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (difficultyLabel.getValue().equalsIgnoreCase("B2.1") || difficultyLabel.getValue().equalsIgnoreCase("B2.2")) {
            if (skillLabel.getValue().equalsIgnoreCase("Language use")) {
                String queryLU = "UPDATE b2_language_use SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, Options_text = ?, "
                        + "Image = ?, Option_a = ?, Option_b = ?, Option_c = ?, Option_d = ?, Correct_Option = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionB2(); PreparedStatement pstmt = conn.prepareStatement(queryLU)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, optionsTextLabel.getText());
                    pstmt.setString(8, imageLabel.getText());
                    pstmt.setString(9, optionALabel.getText());
                    pstmt.setString(10, optionBLabel.getText());
                    pstmt.setString(11, optionCLabel.getText());
                    pstmt.setString(12, optionDLabel.getText());
                    pstmt.setString(13, CorrectOptionLabel.getText());
                    pstmt.setString(14, exam.getValue());
                    pstmt.setInt(15, originalContentId);

                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("SQL Error: " + e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillLabel.getValue().equalsIgnoreCase("Reading comprehension")) {
                String queryR = "UPDATE b2_reading SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, Passage = ?, "
                        + "Options_text = ?, Image = ?, Option_a = ?, Option_b = ?, Option_c = ?, Option_d = ?, "
                        + "Correct_Option = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionB2(); PreparedStatement pstmt = conn.prepareStatement(queryR)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, passageLabel.getText());
                    pstmt.setString(8, optionsTextLabel.getText());
                    pstmt.setString(9, imageLabel.getText());
                    pstmt.setString(10, optionALabel.getText());
                    pstmt.setString(11, optionBLabel.getText());
                    pstmt.setString(12, optionCLabel.getText());
                    pstmt.setString(13, optionDLabel.getText());
                    pstmt.setString(14, CorrectOptionLabel.getText());
                    pstmt.setString(15, exam.getValue());
                    pstmt.setInt(16, originalContentId);

                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("SQL Error: " + e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillLabel.getValue().equalsIgnoreCase("Listening comprehension")) {
                String queryR = "UPDATE b2_listening SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, Passage = ?, "
                        + "Options_text = ?, Image = ?, Audio = ?, Option_a = ?, Option_b = ?, Option_c = ?, Option_d = ?, "
                        + "Correct_Option = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionB2(); PreparedStatement pstmt = conn.prepareStatement(queryR)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, passageLabel.getText());
                    pstmt.setString(8, optionsTextLabel.getText());
                    pstmt.setString(9, imageLabel.getText());
                    pstmt.setString(10, audioLabel.getText());
                    pstmt.setString(11, optionALabel.getText());
                    pstmt.setString(12, optionBLabel.getText());
                    pstmt.setString(13, optionCLabel.getText());
                    pstmt.setString(14, optionDLabel.getText());
                    pstmt.setString(15, CorrectOptionLabel.getText());
                    pstmt.setString(16, exam.getValue());
                    pstmt.setInt(17, originalContentId);

                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("SQL Error: " + e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillLabel.getValue().equalsIgnoreCase("Speaking")) {
                String queryS = "UPDATE b2_speaking SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, "
                        + "Passage = ?, Options_text = ?, Image = ?, Audio = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionB2(); PreparedStatement pstmt = conn.prepareStatement(queryS)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, passageLabel.getText());
                    pstmt.setString(8, optionsTextLabel.getText());
                    pstmt.setString(9, imageLabel.getText());
                    pstmt.setString(10, audioLabel.getText());
                    pstmt.setString(11, exam.getValue());
                    pstmt.setInt(12, originalContentId);
                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillLabel.getValue().equalsIgnoreCase("Writing")) {
                String queryS = "UPDATE b2_writing SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, "
                        + "Passage = ?, Options_text = ?, Image = ?, Audio = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionB2(); PreparedStatement pstmt = conn.prepareStatement(queryS)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, passageLabel.getText());
                    pstmt.setString(8, optionsTextLabel.getText());
                    pstmt.setString(9, imageLabel.getText());
                    pstmt.setString(10, audioLabel.getText());
                    pstmt.setString(11, exam.getValue());
                    pstmt.setInt(12, originalContentId);
                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (difficultyLabel.getValue().equalsIgnoreCase("C1.1") || difficultyLabel.getValue().equalsIgnoreCase("C1.2")) {
            if (skillLabel.getValue().equalsIgnoreCase("Language use")) {
                String queryLU = "UPDATE c1_language_use SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, Options_text = ?, "
                        + "Image = ?, Option_a = ?, Option_b = ?, Option_c = ?, Option_d = ?, Correct_Option = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionC1(); PreparedStatement pstmt = conn.prepareStatement(queryLU)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, optionsTextLabel.getText());
                    pstmt.setString(8, imageLabel.getText());
                    pstmt.setString(9, optionALabel.getText());
                    pstmt.setString(10, optionBLabel.getText());
                    pstmt.setString(11, optionCLabel.getText());
                    pstmt.setString(12, optionDLabel.getText());
                    pstmt.setString(13, CorrectOptionLabel.getText());
                    pstmt.setString(14, exam.getValue());
                    pstmt.setInt(15, originalContentId);

                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("SQL Error: " + e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillLabel.getValue().equalsIgnoreCase("Reading comprehension")) {
                String queryR = "UPDATE c1_reading SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, Passage = ?, "
                        + "Options_text = ?, Image = ?, Option_a = ?, Option_b = ?, Option_c = ?, Option_d = ?, "
                        + "Correct_Option = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionC1(); PreparedStatement pstmt = conn.prepareStatement(queryR)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, passageLabel.getText());
                    pstmt.setString(8, optionsTextLabel.getText());
                    pstmt.setString(9, imageLabel.getText());
                    pstmt.setString(10, optionALabel.getText());
                    pstmt.setString(11, optionBLabel.getText());
                    pstmt.setString(12, optionCLabel.getText());
                    pstmt.setString(13, optionDLabel.getText());
                    pstmt.setString(14, CorrectOptionLabel.getText());
                    pstmt.setString(15, exam.getValue());
                    pstmt.setInt(16, originalContentId);

                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("SQL Error: " + e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillLabel.getValue().equalsIgnoreCase("Listening comprehension")) {
                String queryR = "UPDATE c1_listening SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, Passage = ?, "
                        + "Options_text = ?, Image = ?, Audio = ?, Option_a = ?, Option_b = ?, Option_c = ?, Option_d = ?, "
                        + "Correct_Option = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionC1(); PreparedStatement pstmt = conn.prepareStatement(queryR)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, passageLabel.getText());
                    pstmt.setString(8, optionsTextLabel.getText());
                    pstmt.setString(9, imageLabel.getText());
                    pstmt.setString(10, audioLabel.getText());
                    pstmt.setString(11, optionALabel.getText());
                    pstmt.setString(12, optionBLabel.getText());
                    pstmt.setString(13, optionCLabel.getText());
                    pstmt.setString(14, optionDLabel.getText());
                    pstmt.setString(15, CorrectOptionLabel.getText());
                    pstmt.setString(16, exam.getValue());
                    pstmt.setInt(17, originalContentId);

                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("SQL Error: " + e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillLabel.getValue().equalsIgnoreCase("Speaking")) {
                String queryS = "UPDATE c1_speaking SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, "
                        + "Passage = ?, Options_text = ?, Image = ?, Audio = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionC1(); PreparedStatement pstmt = conn.prepareStatement(queryS)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, passageLabel.getText());
                    pstmt.setString(8, optionsTextLabel.getText());
                    pstmt.setString(9, imageLabel.getText());
                    pstmt.setString(10, audioLabel.getText());
                    pstmt.setString(11, exam.getValue());
                    pstmt.setInt(12, originalContentId);
                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillLabel.getValue().equalsIgnoreCase("Writing")) {
                String queryS = "UPDATE c1_writing SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, "
                        + "Passage = ?, Options_text = ?, Image = ?, Audio = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionC1(); PreparedStatement pstmt = conn.prepareStatement(queryS)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, passageLabel.getText());
                    pstmt.setString(8, optionsTextLabel.getText());
                    pstmt.setString(9, imageLabel.getText());
                    pstmt.setString(10, audioLabel.getText());
                    pstmt.setString(11, exam.getValue());
                    pstmt.setInt(12, originalContentId);
                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        if (difficultyLabel.getValue().equalsIgnoreCase("C2.1") || difficultyLabel.getValue().equalsIgnoreCase("C2.2")) {
            if (skillLabel.getValue().equalsIgnoreCase("Language use")) {
                String queryLU = "UPDATE c2_language_use SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, Options_text = ?, "
                        + "Image = ?, Option_a = ?, Option_b = ?, Option_c = ?, Option_d = ?, Correct_Option = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionC2(); PreparedStatement pstmt = conn.prepareStatement(queryLU)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, optionsTextLabel.getText());
                    pstmt.setString(8, imageLabel.getText());
                    pstmt.setString(9, optionALabel.getText());
                    pstmt.setString(10, optionBLabel.getText());
                    pstmt.setString(11, optionCLabel.getText());
                    pstmt.setString(12, optionDLabel.getText());
                    pstmt.setString(13, CorrectOptionLabel.getText());
                    pstmt.setString(14, exam.getValue());
                    pstmt.setInt(15, originalContentId);

                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("SQL Error: " + e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillLabel.getValue().equalsIgnoreCase("Reading comprehension")) {
                String queryR = "UPDATE c2_reading SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, Passage = ?, "
                        + "Options_text = ?, Image = ?, Option_a = ?, Option_b = ?, Option_c = ?, Option_d = ?, "
                        + "Correct_Option = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionC2(); PreparedStatement pstmt = conn.prepareStatement(queryR)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, passageLabel.getText());
                    pstmt.setString(8, optionsTextLabel.getText());
                    pstmt.setString(9, imageLabel.getText());
                    pstmt.setString(10, optionALabel.getText());
                    pstmt.setString(11, optionBLabel.getText());
                    pstmt.setString(12, optionCLabel.getText());
                    pstmt.setString(13, optionDLabel.getText());
                    pstmt.setString(14, CorrectOptionLabel.getText());
                    pstmt.setString(15, exam.getValue());
                    pstmt.setInt(16, originalContentId);

                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("SQL Error: " + e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillLabel.getValue().equalsIgnoreCase("Listening comprehension")) {
                String queryR = "UPDATE c2_listening SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, Passage = ?, "
                        + "Options_text = ?, Image = ?, Audio = ?, Option_a = ?, Option_b = ?, Option_c = ?, Option_d = ?, "
                        + "Correct_Option = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionC2(); PreparedStatement pstmt = conn.prepareStatement(queryR)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, passageLabel.getText());
                    pstmt.setString(8, optionsTextLabel.getText());
                    pstmt.setString(9, imageLabel.getText());
                    pstmt.setString(10, audioLabel.getText());
                    pstmt.setString(11, optionALabel.getText());
                    pstmt.setString(12, optionBLabel.getText());
                    pstmt.setString(13, optionCLabel.getText());
                    pstmt.setString(14, optionDLabel.getText());
                    pstmt.setString(15, CorrectOptionLabel.getText());
                    pstmt.setString(16, exam.getValue());
                    pstmt.setInt(17, originalContentId);

                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("SQL Error: " + e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillLabel.getValue().equalsIgnoreCase("Speaking")) {
                String queryS = "UPDATE c2_speaking SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, "
                        + "Passage = ?, Options_text = ?, Image = ?, Audio = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionC2(); PreparedStatement pstmt = conn.prepareStatement(queryS)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, passageLabel.getText());
                    pstmt.setString(8, optionsTextLabel.getText());
                    pstmt.setString(9, imageLabel.getText());
                    pstmt.setString(10, audioLabel.getText());
                    pstmt.setString(11, exam.getValue());
                    pstmt.setInt(12, originalContentId);
                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillLabel.getValue().equalsIgnoreCase("Writing")) {
                String queryS = "UPDATE c2_writing SET "
                        + "Content_id = ?, Cfer_level = ?, Difficulty = ?, "
                        + "Skill = ?, Instruction_text = ?, Question_text = ?, "
                        + "Passage = ?, Options_text = ?, Image = ?, Audio = ?, Exam = ? "
                        + "WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionC2(); PreparedStatement pstmt = conn.prepareStatement(queryS)) {
                    pstmt.setInt(1, Integer.parseInt(contentIDLabel.getText()));
                    pstmt.setString(2, cferLevelTextField.getValue());
                    pstmt.setString(3, difficultyLabel.getValue());
                    pstmt.setString(4, skillLabel.getValue());
                    pstmt.setString(5, InstructionTextLabel.getText());
                    pstmt.setString(6, QuestionTextLabel.getText());
                    pstmt.setString(7, passageLabel.getText());
                    pstmt.setString(8, optionsTextLabel.getText());
                    pstmt.setString(9, imageLabel.getText());
                    pstmt.setString(10, audioLabel.getText());
                    pstmt.setString(11, exam.getValue());
                    pstmt.setInt(12, originalContentId);
                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        mostrarAlerta("Update successful!");
                        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/EditQuestion/editQuestion.fxml"));
                        Parent root = cargadorApp.load();
                        btnCancel.getScene().setRoot(root);
                    } else {
                        mostrarAlerta("Update failed or no rows affected.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

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
        btnCancel.getScene().setRoot(root);
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
    private void goSearchGenQuestion(ActionEvent event) {
    }

    @FXML
    private void goLog(ActionEvent event) {
    }

    
}
