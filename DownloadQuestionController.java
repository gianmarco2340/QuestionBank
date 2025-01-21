/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package DownloadQuestion;

import DataBase.DataBaseUtil;
import Question.Question;

import java.sql.Connection;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * FXML Controller class
 *
 * @author brato
 */
public class DownloadQuestionController implements Initializable {

    private ComboBox<String> difficulty;
    private ComboBox<String> skill;
    
    @FXML
    private Button btnCancel;
    
    private static final String URL_A1 = "jdbc:mysql://localhost:3306/a1questions";
    private static final String URL_A2 = "jdbc:mysql://localhost:3306/a2questions";
    private static final String URL_B1 = "jdbc:mysql://localhost:3306/b1questions";
    private static final String URL_B2 = "jdbc:mysql://localhost:3306/b2questions";
    private static final String URL_C1 = "jdbc:mysql://localhost:3306/c1questions";
    
    private static final String USER = "root";  
    private static final String PASSWORD = "Habrat6714%";
    
    private DataBaseUtil DB = new DataBaseUtil();
    @FXML
    private TableView<Question> questionsTableView;
    @FXML
    private TableColumn<Question, Integer> content_id;
    @FXML
    private TableColumn<Question, String> instruction_text;
    @FXML
    private TableColumn<Question, String> question_text;
    @FXML
    private TableColumn<Question, String> passage;
    @FXML
    private TableColumn<Question, String> options_text;
    @FXML
    private TableColumn<Question, String> image;
    @FXML
    private TableColumn<Question, String> audio;
    @FXML
    private TableColumn<Question, String> option_A;
    @FXML
    private TableColumn<Question, String> option_B;
    @FXML
    private TableColumn<Question, String> option_C;
    @FXML
    private TableColumn<Question, String> option_D;
    @FXML
    private TableColumn<Question, String> correct_option;
    @FXML
    private TableColumn<Question, String> difficultyColumn;
    @FXML
    private TableColumn<Question, String> skillColumn;
    
    private ObservableList<Question> questionList = FXCollections.observableArrayList();
    @FXML
    private Button btnShow;
    @FXML
    private Button btnDownload;
    private Button btnAddQuestion;
    @FXML
    private ComboBox<String> difficultyComboBox;
    @FXML
    private ComboBox<String> skillComboBox;
    @FXML
    private TableColumn<Question, String> cfer_level;
    @FXML
    private Button btnEditQuestion1;
    @FXML
    private Text userIdDownload;
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
    private Button btnEditQuestion11;
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
    private TableColumn<Question, String> exam;

    /**
     * Initializes the controller class.
     */
    
    public Connection getConnectionA2() throws SQLException{
        return DriverManager.getConnection(URL_A2, USER, PASSWORD);
    }
    
    public Connection getConnectionA1() throws SQLException{
        return DriverManager.getConnection(URL_A1, USER, PASSWORD);
    }
    
    public Connection getConnectionB1() throws SQLException{
        return DriverManager.getConnection(URL_B1, USER, PASSWORD);
    }
    
    public Connection getConnectionB2() throws SQLException{
        return DriverManager.getConnection(URL_B2, USER, PASSWORD);
    }
    public Connection getConnectionC1() throws SQLException{
        return DriverManager.getConnection(URL_C1, USER, PASSWORD);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String username =  DB.getUserName();
        userIdDownload.setText(username);
        skillComboBox.getItems().addAll("Listening comprehension", "Reading comprehension", "Language use", "Writing", "Speaking");
        difficultyComboBox.getItems().addAll("Pre A1","A1", "A2", "B1", "B2", "C1", "C2");
        btnShow.disableProperty().bind(difficultyComboBox.valueProperty().isNull()
                                            .or(skillComboBox.valueProperty().isNull()));
        
        content_id.setCellValueFactory(new PropertyValueFactory<>("contentId"));
        cfer_level.setCellValueFactory(new PropertyValueFactory<>("cferlevel"));
        difficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
        skillColumn.setCellValueFactory(new PropertyValueFactory<>("skill"));
        instruction_text.setCellValueFactory(new PropertyValueFactory<>("instructionText"));
        question_text.setCellValueFactory(new PropertyValueFactory<>("questionText"));
        passage.setCellValueFactory(new PropertyValueFactory<>("passage"));
        options_text.setCellValueFactory(new PropertyValueFactory<>("optionsText"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        audio.setCellValueFactory(new PropertyValueFactory<>("audio"));
        option_A.setCellValueFactory(new PropertyValueFactory<>("optionA"));
        option_B.setCellValueFactory(new PropertyValueFactory<>("optionB"));
        option_C.setCellValueFactory(new PropertyValueFactory<>("optionC"));
        option_D.setCellValueFactory(new PropertyValueFactory<>("optionD"));
        correct_option.setCellValueFactory(new PropertyValueFactory<>("correctOption"));
        exam.setCellValueFactory(new PropertyValueFactory<>("exam"));
        
        questionsTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
          
    }  
    
    //Consulta SQL para traer todas las preguntas de la BD dependiendo del skill y la dificultad
    @FXML
    private void showQuestions(ActionEvent event) {
        List<Question> questions = new ArrayList<>();
        String user = DB.getUserName();
        String difficultyOpt = this.difficultyComboBox.getValue();
        String skillOpt = this.skillComboBox.getValue();
        if(difficultyOpt.equals("Pre A1")){
            if(skillOpt.equals("Language use")){
                String queryLU = "SELECT * FROM prea1_language_use";
                try(Connection conn = DB.getConnectionPreA1(); 
                    PreparedStatement pstmt = conn.prepareStatement(queryLU)){
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLU = rs.getInt("Content_id");
                        String cferLevelLU = rs.getString("Cfer_level");
                        String difficultyLU = rs.getString("Difficulty");
                        String skillLU = rs.getString("Skill");
                        String instructionTextLU = rs.getString("Instruction_Text");
                        String questionTextLU = rs.getString("Question_Text");
                        String optionsTextLU = rs.getString("Options_Text");
                        String imageLU = rs.getString("Image");
                        String optionALU = rs.getString("Option_a");
                        String optionBLU = rs.getString("Option_b");
                        String optionCLU = rs.getString("Option_c");
                        String optionDLU = rs.getString("Option_d");
                        String correctOptionLU = rs.getString("Correct_Option");
                        String examLU = rs.getString("Exam");
                        Question question = new Question(contentIdLU, cferLevelLU, difficultyLU, skillLU, instructionTextLU, questionTextLU, optionsTextLU,
                        imageLU, optionALU, optionBLU, optionCLU, optionDLU, correctOptionLU, examLU);
                        questions.add(question);
                        
                    }
                    loadQuestions(questions);
                    
                    
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Reading comprehension")) {
                String queryR = "SELECT * FROM prea1_reading";
                try (Connection conn = DB.getConnectionPreA1(); PreparedStatement pstmt = conn.prepareStatement(queryR)) {
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdR = rs.getInt("Content_id");
                        String cferLevelR = rs.getString("Cfer_level");
                        String difficultyR = rs.getString("Difficulty");
                        String skillR = rs.getString("Skill");
                        String instructionTextR = rs.getString("Instruction_text");
                        String questionTextR = rs.getString("Question_text");
                        String passageR = rs.getString("Passage");
                        String optionsTextR = rs.getString("Options_text");
                        String imageR = rs.getString("Image");
                        String optionAR = rs.getString("Option_a");
                        String optionBR = rs.getString("Option_b");
                        String optionCR = rs.getString("Option_c");
                        String optionDR = rs.getString("Option_d");
                        String correctOptionR = rs.getString("Correct_Option");
                        String examR = rs.getString("Exam");
                        Question question = new Question(contentIdR, cferLevelR, difficultyR, skillR,
                                instructionTextR, questionTextR, passageR, optionsTextR, imageR,
                                optionAR, optionBR, optionCR, optionDR, correctOptionR, examR);
                        questions.add(question);
                    }
                    
                    loadQuestions(questions);
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (skillOpt.equals("Listening comprehension")) {
                String queryL = "SELECT * FROM prea1_listening";
                try (Connection conn = DB.getConnectionPreA1(); PreparedStatement pstmt = conn.prepareStatement(queryL)) {
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLC = rs.getInt("Content_id");
                        String cferLevelLC = rs.getString("Cfer_level");
                        String difficultyLC = rs.getString("Difficulty");
                        String skillLC = rs.getString("Skill");
                        String instructionTextLC = rs.getString("Instruction_text");
                        String questionTextLC = rs.getString("Question_text");
                        String passageLC = rs.getString("Passage");
                        String optionsTextLC = rs.getString("Options_text");
                        String imageLC = rs.getString("Image");
                        String audioLC = rs.getString("Audio");
                        String optionALC = rs.getString("Option_a");
                        String optionBLC = rs.getString("Option_b");
                        String optionCLC = rs.getString("Option_c");
                        String optionDLC = rs.getString("Option_d");
                        String correctOptionLC = rs.getString("Correct_Option");
                        String examLC = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC, difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC,
                                optionALC, optionBLC, optionCLC, optionDLC, correctOptionLC, examLC);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Writing")) {
                String queryW = "SELECT * FROM prea1_writing";
                try (Connection conn = DB.getConnectionPreA1(); PreparedStatement pstmt = conn.prepareStatement(queryW)) {
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLC = rs.getInt("Content_id");
                        String cferLevelLC = rs.getString("Cfer_level");
                        String difficultyLC = rs.getString("Difficulty");
                        String skillLC = rs.getString("Skill");
                        String instructionTextLC = rs.getString("Instruction_text");
                        String questionTextLC = rs.getString("Question_text");
                        String passageLC = rs.getString("Passage");
                        String optionsTextLC = rs.getString("Options_text");
                        String imageLC = rs.getString("Image");
                        String audioLC = rs.getString("Audio");
                        String examW = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC, difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC, examW);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Speaking")) {
                String queryS = "SELECT * FROM prea1_speaking";
                try (Connection conn = DB.getConnectionPreA1(); PreparedStatement pstmt = conn.prepareStatement(queryS)) {
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLC = rs.getInt("Content_id");
                        String cferLevelLC = rs.getString("Cfer_level");
                        String difficultyLC = rs.getString("Difficulty");
                        String skillLC = rs.getString("Skill");
                        String instructionTextLC = rs.getString("Instruction_text");
                        String questionTextLC = rs.getString("Question_text");
                        String passageLC = rs.getString("Passage");
                        String optionsTextLC = rs.getString("Options_text");
                        String imageLC = rs.getString("Image");
                        String audioLC = rs.getString("Audio");
                        String examS = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC, difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC, examS);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if(difficultyOpt.equals("A1")){
            if(skillOpt.equals("Language use")){
                String queryLU = "SELECT * FROM a1_language_use";
                try(Connection conn = getConnectionA1(); 
                    PreparedStatement pstmt = conn.prepareStatement(queryLU)){
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLU = rs.getInt("Content_id");
                        String cferLevelLU = rs.getString("Cfer_level");
                        String difficultyLU = rs.getString("Difficulty");
                        String skillLU = rs.getString("Skill");
                        String instructionTextLU = rs.getString("Instruction_Text");
                        String questionTextLU = rs.getString("Question_Text");
                        String optionsTextLU = rs.getString("Options_Text");
                        String imageLU = rs.getString("Image");
                        String optionALU = rs.getString("Option_a");
                        String optionBLU = rs.getString("Option_b");
                        String optionCLU = rs.getString("Option_c");
                        String optionDLU = rs.getString("Option_d");
                        String correctOptionLU = rs.getString("Correct_Option");
                        String examLU = rs.getString("Exam");
                        Question question = new Question(contentIdLU, cferLevelLU, difficultyLU, skillLU, instructionTextLU, questionTextLU, optionsTextLU,
                        imageLU, optionALU, optionBLU, optionCLU, optionDLU, correctOptionLU, examLU);
                        questions.add(question);
                        
                    }
                    loadQuestions(questions);
                    
                    
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Reading comprehension")) {
                String queryR = "SELECT * FROM a1_reading";
                try (Connection conn = getConnectionA1(); PreparedStatement pstmt = conn.prepareStatement(queryR)) {
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdR = rs.getInt("Content_id");
                        String cferLevelR = rs.getString("Cfer_level");
                        String difficultyR = rs.getString("Difficulty");
                        String skillR = rs.getString("Skill");
                        String instructionTextR = rs.getString("Instruction_text");
                        String questionTextR = rs.getString("Question_text");
                        String passageR = rs.getString("Passage");
                        String optionsTextR = rs.getString("Options_text");
                        String imageR = rs.getString("Image");
                        String optionAR = rs.getString("Option_a");
                        String optionBR = rs.getString("Option_b");
                        String optionCR = rs.getString("Option_c");
                        String optionDR = rs.getString("Option_d");
                        String correctOptionR = rs.getString("Correct_Option");
                        String examR = rs.getString("Exam");
                        Question question = new Question(contentIdR, cferLevelR, difficultyR, skillR,
                                instructionTextR, questionTextR, passageR, optionsTextR, imageR,
                                optionAR, optionBR, optionCR, optionDR, correctOptionR, examR);
                        questions.add(question);
                    }
                    
                    loadQuestions(questions);
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (skillOpt.equals("Listening comprehension")) {
                String queryL = "SELECT * FROM a1_listening";
                try (Connection conn = getConnectionA1(); PreparedStatement pstmt = conn.prepareStatement(queryL)) {
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLC = rs.getInt("Content_id");
                        String cferLevelLC = rs.getString("Cfer_level");
                        String difficultyLC = rs.getString("Difficulty");
                        String skillLC = rs.getString("Skill");
                        String instructionTextLC = rs.getString("Instruction_text");
                        String questionTextLC = rs.getString("Question_text");
                        String passageLC = rs.getString("Passage");
                        String optionsTextLC = rs.getString("Options_text");
                        String imageLC = rs.getString("Image");
                        String audioLC = rs.getString("Audio");
                        String optionALC = rs.getString("Option_a");
                        String optionBLC = rs.getString("Option_b");
                        String optionCLC = rs.getString("Option_c");
                        String optionDLC = rs.getString("Option_d");
                        String correctOptionLC = rs.getString("Correct_Option");
                        String examLC = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC, difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC,
                                optionALC, optionBLC, optionCLC, optionDLC, correctOptionLC, examLC);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Writing")) {
                String queryW = "SELECT * FROM a1_writing";
                try (Connection conn = getConnectionA1(); PreparedStatement pstmt = conn.prepareStatement(queryW)) {
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLC = rs.getInt("Content_id");
                        String cferLevelLC = rs.getString("Cfer_level");
                        String difficultyLC = rs.getString("Difficulty");
                        String skillLC = rs.getString("Skill");
                        String instructionTextLC = rs.getString("Instruction_text");
                        String questionTextLC = rs.getString("Question_text");
                        String passageLC = rs.getString("Passage");
                        String optionsTextLC = rs.getString("Options_text");
                        String imageLC = rs.getString("Image");
                        String audioLC = rs.getString("Audio");
                        String examW = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC, difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC, examW);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Speaking")) {
                String queryS = "SELECT * FROM a1_speaking";
                try (Connection conn = getConnectionA1(); PreparedStatement pstmt = conn.prepareStatement(queryS)) {
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLC = rs.getInt("Content_id");
                        String cferLevelLC = rs.getString("Cfer_level");
                        String difficultyLC = rs.getString("Difficulty");
                        String skillLC = rs.getString("Skill");
                        String instructionTextLC = rs.getString("Instruction_text");
                        String questionTextLC = rs.getString("Question_text");
                        String passageLC = rs.getString("Passage");
                        String optionsTextLC = rs.getString("Options_text");
                        String imageLC = rs.getString("Image");
                        String audioLC = rs.getString("Audio");
                        String examS = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC, difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC, examS);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if (difficultyOpt.equals("A2")) {
            if (skillOpt.equals("Language use")) {
                String queryLU = "SELECT * FROM a2_language_use";
                try (Connection conn = getConnectionA2(); PreparedStatement pstmt = conn.prepareStatement(queryLU)) {
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLU = rs.getInt("Content_id");
                        String cferLevelLU = rs.getString("Cfer_level");
                        String difficultyLU = rs.getString("Difficulty");
                        String skillLU = rs.getString("Skill");
                        String instructionTextLU = rs.getString("Instruction_text");
                        String questionTextLU = rs.getString("Question_text");
                        String optionsTextLU = rs.getString("Options_text");
                        String imageLU = rs.getString("Image");
                        String optionALU = rs.getString("Option_a");
                        String optionBLU = rs.getString("Option_b");
                        String optionCLU = rs.getString("Option_c");
                        String optionDLU = rs.getString("Option_d");
                        String correctOptionLU = rs.getString("Correct_Option");
                        String examLU = rs.getString("Exam");
                        Question question = new Question(contentIdLU, cferLevelLU, difficultyLU, skillLU, instructionTextLU, questionTextLU, optionsTextLU,
                        imageLU, optionALU, optionBLU, optionCLU, optionDLU, correctOptionLU, examLU);
                        questions.add(question);
                        
                    }
                    
                    loadQuestions(questions);
                   
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (skillOpt.equals("Reading comprehension")) {
                String queryR = "SELECT * FROM a2_reading";
                try (Connection conn = getConnectionA2(); PreparedStatement pstmt = conn.prepareStatement(queryR)) {
                    
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdR = rs.getInt("Content_id");
                        String cferLevelR = rs.getString("Cfer_level");
                        String difficultyR = rs.getString("Difficulty");
                        String skillR = rs.getString("Skill");
                        String instructionTextR = rs.getString("Instruction_text");
                        String questionTextR = rs.getString("Question_text");
                        String passageR = rs.getString("Passage");
                        String optionsTextR = rs.getString("Options_text");
                        String imageR = rs.getString("Image");
                        String optionAR = rs.getString("Option_a");
                        String optionBR = rs.getString("Option_b");
                        String optionCR = rs.getString("Option_c");
                        String optionDR = rs.getString("Option_d");
                        String correctOptionR = rs.getString("Correct_Option");
                        String examR = rs.getString("Exam");
                        Question question = new Question(contentIdR, cferLevelR,difficultyR, skillR,
                                instructionTextR, questionTextR, passageR, optionsTextR, imageR,
                                optionAR, optionBR, optionCR, optionDR, correctOptionR, examR);
                        questions.add(question);
                    }
                    
                    loadQuestions(questions);
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (skillOpt.equals("Listening comprehension")) {
                String queryL = "SELECT * FROM a2_listening";
                try (Connection conn = getConnectionA2(); PreparedStatement pstmt = conn.prepareStatement(queryL)) {
                    
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLC = rs.getInt("Content_id");
                        String cferLevelLC = rs.getString("Cfer_level");
                        String difficultyLC = rs.getString("Difficulty");
                        String skillLC = rs.getString("Skill");
                        String instructionTextLC = rs.getString("Instruction_text");
                        String questionTextLC = rs.getString("Question_text");
                        String passageLC = rs.getString("Passage");
                        String optionsTextLC = rs.getString("Options_text");
                        String imageLC = rs.getString("Image");
                        String audioLC = rs.getString("Audio");
                        String optionALC = rs.getString("Option_a");
                        String optionBLC = rs.getString("Option_b");
                        String optionCLC = rs.getString("Option_c");
                        String optionDLC = rs.getString("Option_d");
                        String correctOptionLC = rs.getString("Correct_Option");
                        String examLC = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC,difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC,
                                optionALC, optionBLC, optionCLC, optionDLC, correctOptionLC, examLC);
                        questions.add(question);
                    }
                    
                    loadQuestions(questions);
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Writing")) {
                String queryW = "SELECT * FROM a2_writing";
                try (Connection conn = getConnectionA2(); PreparedStatement pstmt = conn.prepareStatement(queryW)) {
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLC = rs.getInt("Content_id");
                        String cferLevelLC = rs.getString("Cfer_level");
                        String difficultyLC = rs.getString("Difficulty");
                        String skillLC = rs.getString("Skill");
                        String instructionTextLC = rs.getString("Instruction_text");
                        String questionTextLC = rs.getString("Question_text");
                        String passageLC = rs.getString("Passage");
                        String optionsTextLC = rs.getString("Options_text");
                        String imageLC = rs.getString("Image");
                        String audioLC = rs.getString("Audio");
                        String examLC = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC, difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC, examLC);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Speaking")) {
                String queryS = "SELECT * FROM a2_speaking";
                try (Connection conn = getConnectionA2(); PreparedStatement pstmt = conn.prepareStatement(queryS)) {
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLC = rs.getInt("Content_id");
                        String cferLevelLC = rs.getString("Cfer_level");
                        String difficultyLC = rs.getString("Difficulty");
                        String skillLC = rs.getString("Skill");
                        String instructionTextLC = rs.getString("Instruction_text");
                        String questionTextLC = rs.getString("Question_text");
                        String passageLC = rs.getString("Passage");
                        String optionsTextLC = rs.getString("Options_text");
                        String imageLC = rs.getString("Image");
                        String audioLC = rs.getString("Audio");
                        String examS = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC, difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC, examS);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if (difficultyOpt.equals("B1")) {
            if (skillOpt.equals("Language use")) {
                String queryLU = "SELECT * FROM b1_language_use";
                try (Connection conn = getConnectionB1(); PreparedStatement pstmt = conn.prepareStatement(queryLU)) {
                    
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLU = rs.getInt("Content_id");
                        String cferLevelLU = rs.getString("Cfer_level");
                        String difficultyLU = rs.getString("Difficulty");
                        String skillLU = rs.getString("Skill");
                        String instructionTextLU = rs.getString("Instruction_text");
                        String questionTextLU = rs.getString("Question_text");
                        String optionsTextLU = rs.getString("Options_text");
                        String imageLU = rs.getString("Image");
                        String optionALU = rs.getString("Option_a");
                        String optionBLU = rs.getString("Option_b");
                        String optionCLU = rs.getString("Option_c");
                        String optionDLU = rs.getString("Option_d");
                        String correctOptionLU = rs.getString("Correct_Option");
                        String examLU = rs.getString("Exam");
                        Question question = new Question(contentIdLU, cferLevelLU, difficultyLU, skillLU, instructionTextLU, questionTextLU, optionsTextLU,
                        imageLU, optionALU, optionBLU, optionCLU, optionDLU, correctOptionLU, examLU);
                        questions.add(question);
                        
                    }
                    loadQuestions(questions);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (skillOpt.equals("Reading comprehension")) {
                String queryR = "SELECT * FROM b1_reading";
                try (Connection conn = getConnectionB1(); PreparedStatement pstmt = conn.prepareStatement(queryR)) {
                    
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdR = rs.getInt("Content_id");
                        String cferLevelR = rs.getString("Cfer_level");
                        String difficultyR = rs.getString("Difficulty");
                        String skillR = rs.getString("Skill");
                        String instructionTextR = rs.getString("Instruction_text");
                        String questionTextR = rs.getString("Question_text");
                        String passageR = rs.getString("Passage");
                        String optionsTextR = rs.getString("Options_text");
                        String imageR = rs.getString("Image");
                        String optionAR = rs.getString("Option_a");
                        String optionBR = rs.getString("Option_b");
                        String optionCR = rs.getString("Option_c");
                        String optionDR = rs.getString("Option_d");
                        String correctOptionR = rs.getString("Correct_Option");
                        String examR = rs.getString("Exam");
                        Question question = new Question(contentIdR, cferLevelR, difficultyR, skillR,
                                instructionTextR, questionTextR, passageR, optionsTextR, imageR,
                                optionAR, optionBR, optionCR, optionDR, correctOptionR, examR);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (skillOpt.equals("Listening comprehension")) {
                String queryL = "SELECT * FROM b1_listening";
                try (Connection conn = getConnectionB1(); PreparedStatement pstmt = conn.prepareStatement(queryL)) {
                    
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLC = rs.getInt("Content_id");
                        String cferLevelLC = rs.getString("Cfer_level");
                        String difficultyLC = rs.getString("Difficulty");
                        String skillLC = rs.getString("Skill");
                        String instructionTextLC = rs.getString("Instruction_text");
                        String questionTextLC = rs.getString("Question_text");
                        String passageLC = rs.getString("Passage");
                        String optionsTextLC = rs.getString("Options_text");
                        String imageLC = rs.getString("Image");
                        String audioLC = rs.getString("Audio");
                        String optionALC = rs.getString("Option_a");
                        String optionBLC = rs.getString("Option_b");
                        String optionCLC = rs.getString("Option_c");
                        String optionDLC = rs.getString("Option_d");
                        String correctOptionLC = rs.getString("Correct_Option");
                        String examLC = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC,difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC,
                                optionALC, optionBLC, optionCLC, optionDLC, correctOptionLC, examLC);
                        questions.add(question);
                    }
                    
                    loadQuestions(questions);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Writing")) {
                String queryW = "SELECT * FROM b1_writing";
                try (Connection conn = getConnectionB1(); PreparedStatement pstmt = conn.prepareStatement(queryW)) {
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLC = rs.getInt("Content_id");
                        String cferLevelLC = rs.getString("Cfer_level");
                        String difficultyLC = rs.getString("Difficulty");
                        String skillLC = rs.getString("Skill");
                        String instructionTextLC = rs.getString("Instruction_text");
                        String questionTextLC = rs.getString("Question_text");
                        String passageLC = rs.getString("Passage");
                        String optionsTextLC = rs.getString("Options_text");
                        String imageLC = rs.getString("Image");
                        String audioLC = rs.getString("Audio");
                        String examW = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC, difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC, examW);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Speaking")) {
                String queryS = "SELECT * FROM b1_speaking";
                try (Connection conn = getConnectionB1(); PreparedStatement pstmt = conn.prepareStatement(queryS)) {
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLC = rs.getInt("Content_id");
                        String cferLevelLC = rs.getString("Cfer_level");
                        String difficultyLC = rs.getString("Difficulty");
                        String skillLC = rs.getString("Skill");
                        String instructionTextLC = rs.getString("Instruction_text");
                        String questionTextLC = rs.getString("Question_text");
                        String passageLC = rs.getString("Passage");
                        String optionsTextLC = rs.getString("Options_text");
                        String imageLC = rs.getString("Image");
                        String audioLC = rs.getString("Audio");
                        String examS = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC, difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC, examS);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if (difficultyOpt.equals("B2")) {
            if (skillOpt.equals("Language use")) {
                String queryLU = "SELECT * FROM b2_language_use";
                try (Connection conn = getConnectionB2(); PreparedStatement pstmt = conn.prepareStatement(queryLU)) {
                    
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLU = rs.getInt("Content_id");
                        String cferLevelLU = rs.getString("Cfer_level");
                        String difficultyLU = rs.getString("Difficulty");
                        String skillLU = rs.getString("Skill");
                        String instructionTextLU = rs.getString("Instruction_text");
                        String questionTextLU = rs.getString("Question_text");
                        String optionsTextLU = rs.getString("Options_text");
                        String imageLU = rs.getString("Image");
                        String optionALU = rs.getString("Option_a");
                        String optionBLU = rs.getString("Option_b");
                        String optionCLU = rs.getString("Option_c");
                        String optionDLU = rs.getString("Option_d");
                        String correctOptionLU = rs.getString("Correct_Option");
                        String examLU = rs.getString("Exam");
                        Question question = new Question(contentIdLU, cferLevelLU, difficultyLU, skillLU, instructionTextLU, questionTextLU, optionsTextLU,
                        imageLU, optionALU, optionBLU, optionCLU, optionDLU, correctOptionLU, examLU);
                        questions.add(question);
                        
                    }
                    loadQuestions(questions);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (skillOpt.equals("Reading comprehension")) {
                String queryR = "SELECT * FROM b2_reading";
                try (Connection conn = getConnectionB2(); PreparedStatement pstmt = conn.prepareStatement(queryR)) {
                    
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdR = rs.getInt("Content_id");
                        String cferLevelR = rs.getString("Cfer_level");
                        String difficultyR = rs.getString("Difficulty");
                        String skillR = rs.getString("Skill");
                        String instructionTextR = rs.getString("Instruction_text");
                        String questionTextR = rs.getString("Question_text");
                        String passageR = rs.getString("Passage");
                        String optionsTextR = rs.getString("Options_text");
                        String imageR = rs.getString("Image");
                        String optionAR = rs.getString("Option_a");
                        String optionBR = rs.getString("Option_b");
                        String optionCR = rs.getString("Option_c");
                        String optionDR = rs.getString("Option_d");
                        String correctOptionR = rs.getString("Correct_Option");
                        String examR = rs.getString("Exam");
                        Question question = new Question(contentIdR, cferLevelR, difficultyR, skillR,
                                instructionTextR, questionTextR, passageR, optionsTextR, imageR,
                                optionAR, optionBR, optionCR, optionDR, correctOptionR, examR);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (skillOpt.equals("Listening comprehension")) {
                String queryL = "SELECT * FROM b2_listening";
                try (Connection conn = getConnectionB2(); PreparedStatement pstmt = conn.prepareStatement(queryL)) {
                    
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLC = rs.getInt("Content_id");
                        String cferLevelLC = rs.getString("Cfer_level");
                        String difficultyLC = rs.getString("Difficulty");
                        String skillLC = rs.getString("Skill");
                        String instructionTextLC = rs.getString("Instruction_text");
                        String questionTextLC = rs.getString("Question_text");
                        String passageLC = rs.getString("Passage");
                        String optionsTextLC = rs.getString("Options_text");
                        String imageLC = rs.getString("Image");
                        String audioLC = rs.getString("Audio");
                        String optionALC = rs.getString("Option_a");
                        String optionBLC = rs.getString("Option_b");
                        String optionCLC = rs.getString("Option_c");
                        String optionDLC = rs.getString("Option_d");
                        String correctOptionLC = rs.getString("Correct_Option");
                        String examLC = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC, difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC,
                                optionALC, optionBLC, optionCLC, optionDLC, correctOptionLC, examLC);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Writing")) {
                String queryW = "SELECT * FROM b2_writing";
                try (Connection conn = getConnectionB2(); PreparedStatement pstmt = conn.prepareStatement(queryW)) {
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLC = rs.getInt("Content_id");
                        String cferLevelLC = rs.getString("Cfer_level");
                        String difficultyLC = rs.getString("Difficulty");
                        String skillLC = rs.getString("Skill");
                        String instructionTextLC = rs.getString("Instruction_text");
                        String questionTextLC = rs.getString("Question_text");
                        String passageLC = rs.getString("Passage");
                        String optionsTextLC = rs.getString("Options_text");
                        String imageLC = rs.getString("Image");
                        String audioLC = rs.getString("Audio");
                        String examLC = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC, difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC, examLC);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Speaking")) {
                String queryS = "SELECT * FROM b2_speaking";
                try (Connection conn = getConnectionB2(); PreparedStatement pstmt = conn.prepareStatement(queryS)) {
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLC = rs.getInt("Content_id");
                        String cferLevelLC = rs.getString("Cfer_level");
                        String difficultyLC = rs.getString("Difficulty");
                        String skillLC = rs.getString("Skill");
                        String instructionTextLC = rs.getString("Instruction_text");
                        String questionTextLC = rs.getString("Question_text");
                        String passageLC = rs.getString("Passage");
                        String optionsTextLC = rs.getString("Options_text");
                        String imageLC = rs.getString("Image");
                        String audioLC = rs.getString("Audio");
                        String examS = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC, difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC, examS);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if (difficultyOpt.equals("C1")) {
            if (skillOpt.equals("Language use")) {
                String queryLU = "SELECT * FROM c1_language_use";
                try (Connection conn = getConnectionC1(); PreparedStatement pstmt = conn.prepareStatement(queryLU)) {
                    
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLU = rs.getInt("Content_id");
                        String cferLevelLU = rs.getString("Cfer_level");
                        String difficultyLU = rs.getString("Difficulty");
                        String skillLU = rs.getString("Skill");
                        String instructionTextLU = rs.getString("Instruction_text");
                        String questionTextLU = rs.getString("Question_text");
                        String optionsTextLU = rs.getString("Options_text");
                        String imageLU = rs.getString("Image");
                        String optionALU = rs.getString("Option_a");
                        String optionBLU = rs.getString("Option_b");
                        String optionCLU = rs.getString("Option_c");
                        String optionDLU = rs.getString("Option_d");
                        String correctOptionLU = rs.getString("Correct_Option");
                        String examLU = rs.getString("Exam");
                        Question question = new Question(contentIdLU, cferLevelLU, difficultyLU, skillLU, instructionTextLU, questionTextLU, optionsTextLU,
                        imageLU, optionALU, optionBLU, optionCLU, optionDLU, correctOptionLU, examLU);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (skillOpt.equals("Reading comprehension")) {
                String queryR = "SELECT * FROM c1_reading";
                try (Connection conn = getConnectionC1(); PreparedStatement pstmt = conn.prepareStatement(queryR)) {
                    
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdR = rs.getInt("Content_id");
                        String cferLevelR = rs.getString("Cfer_level");
                        String difficultyR = rs.getString("Difficulty");
                        String skillR = rs.getString("Skill");
                        String instructionTextR = rs.getString("Instruction_text");
                        String questionTextR = rs.getString("Question_text");
                        String passageR = rs.getString("Passage");
                        String optionsTextR = rs.getString("Options_text");
                        String imageR = rs.getString("Image");
                        String optionAR = rs.getString("Option_a");
                        String optionBR = rs.getString("Option_b");
                        String optionCR = rs.getString("Option_c");
                        String optionDR = rs.getString("Option_d");
                        String correctOptionR = rs.getString("Correct_Option");
                        String examR = rs.getString("Exam");
                        Question question = new Question(contentIdR, cferLevelR,difficultyR, skillR,
                                instructionTextR, questionTextR, passageR, optionsTextR, imageR,
                                optionAR, optionBR, optionCR, optionDR, correctOptionR, examR);
                        questions.add(question);
                        
                    }
                    loadQuestions(questions);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (skillOpt.equals("Listening comprehension")) {
                String queryL = "SELECT * FROM c1_listening";
                try (Connection conn = getConnectionC1(); PreparedStatement pstmt = conn.prepareStatement(queryL)) {
                    
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLC = rs.getInt("Content_id");
                        String cferLevelLC = rs.getString("Cfer_level");
                        String difficultyLC = rs.getString("Difficulty");
                        String skillLC = rs.getString("Skill");
                        String instructionTextLC = rs.getString("Instruction_text");
                        String questionTextLC = rs.getString("Question_text");
                        String passageLC = rs.getString("Passage");
                        String optionsTextLC = rs.getString("Options_text");
                        String imageLC = rs.getString("Image");
                        String audioLC = rs.getString("Audio");
                        String optionALC = rs.getString("Option_a");
                        String optionBLC = rs.getString("Option_b");
                        String optionCLC = rs.getString("Option_c");
                        String optionDLC = rs.getString("Option_d");
                        String correctOptionLC = rs.getString("Correct_Option");
                        String examLC = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC,difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC,
                                optionALC, optionBLC, optionCLC, optionDLC, correctOptionLC, examLC);
                        questions.add(question);
                    }
                    
                    loadQuestions(questions);
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Writing")) {
                String queryW = "SELECT * FROM c1_writing";
                try (Connection conn = getConnectionC1(); PreparedStatement pstmt = conn.prepareStatement(queryW)) {
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLC = rs.getInt("Content_id");
                        String cferLevelLC = rs.getString("Cfer_level");
                        String difficultyLC = rs.getString("Difficulty");
                        String skillLC = rs.getString("Skill");
                        String instructionTextLC = rs.getString("Instruction_text");
                        String questionTextLC = rs.getString("Question_text");
                        String passageLC = rs.getString("Passage");
                        String optionsTextLC = rs.getString("Options_text");
                        String imageLC = rs.getString("Image");
                        String audioLC = rs.getString("Audio");
                        String examW = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC, difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC, examW);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Speaking")) {
                String queryS = "SELECT * FROM c1_speaking";
                try (Connection conn = getConnectionC1(); PreparedStatement pstmt = conn.prepareStatement(queryS)) {
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLC = rs.getInt("Content_id");
                        String cferLevelLC = rs.getString("Cfer_level");
                        String difficultyLC = rs.getString("Difficulty");
                        String skillLC = rs.getString("Skill");
                        String instructionTextLC = rs.getString("Instruction_text");
                        String questionTextLC = rs.getString("Question_text");
                        String passageLC = rs.getString("Passage");
                        String optionsTextLC = rs.getString("Options_text");
                        String imageLC = rs.getString("Image");
                        String audioLC = rs.getString("Audio");
                        String examS = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC, difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC, examS);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if (difficultyOpt.equals("C2")) {
            if (skillOpt.equals("Language use")) {
                String queryLU = "SELECT * FROM c2_language_use";
                try (Connection conn = DB.getConnectionC2(); PreparedStatement pstmt = conn.prepareStatement(queryLU)) {
                    
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLU = rs.getInt("Content_id");
                        String cferLevelLU = rs.getString("Cfer_level");
                        String difficultyLU = rs.getString("Difficulty");
                        String skillLU = rs.getString("Skill");
                        String instructionTextLU = rs.getString("Instruction_text");
                        String questionTextLU = rs.getString("Question_text");
                        String optionsTextLU = rs.getString("Options_text");
                        String imageLU = rs.getString("Image");
                        String optionALU = rs.getString("Option_a");
                        String optionBLU = rs.getString("Option_b");
                        String optionCLU = rs.getString("Option_c");
                        String optionDLU = rs.getString("Option_d");
                        String correctOptionLU = rs.getString("Correct_Option");
                        String examLU = rs.getString("Exam");
                        Question question = new Question(contentIdLU, cferLevelLU, difficultyLU, skillLU, instructionTextLU, questionTextLU, optionsTextLU,
                        imageLU, optionALU, optionBLU, optionCLU, optionDLU, correctOptionLU, examLU);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (skillOpt.equals("Reading comprehension")) {
                String queryR = "SELECT * FROM c2_reading";
                try (Connection conn = DB.getConnectionC2(); PreparedStatement pstmt = conn.prepareStatement(queryR)) {
                    
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdR = rs.getInt("Content_id");
                        String cferLevelR = rs.getString("Cfer_level");
                        String difficultyR = rs.getString("Difficulty");
                        String skillR = rs.getString("Skill");
                        String instructionTextR = rs.getString("Instruction_text");
                        String questionTextR = rs.getString("Question_text");
                        String passageR = rs.getString("Passage");
                        String optionsTextR = rs.getString("Options_text");
                        String imageR = rs.getString("Image");
                        String optionAR = rs.getString("Option_a");
                        String optionBR = rs.getString("Option_b");
                        String optionCR = rs.getString("Option_c");
                        String optionDR = rs.getString("Option_d");
                        String correctOptionR = rs.getString("Correct_Option");
                        String examR = rs.getString("Exam");
                        Question question = new Question(contentIdR, cferLevelR,difficultyR, skillR,
                                instructionTextR, questionTextR, passageR, optionsTextR, imageR,
                                optionAR, optionBR, optionCR, optionDR, correctOptionR, examR);
                        questions.add(question);
                        
                    }
                    loadQuestions(questions);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (skillOpt.equals("Listening comprehension")) {
                String queryL = "SELECT * FROM c2_listening";
                try (Connection conn = DB.getConnectionC2(); PreparedStatement pstmt = conn.prepareStatement(queryL)) {
                    
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLC = rs.getInt("Content_id");
                        String cferLevelLC = rs.getString("Cfer_level");
                        String difficultyLC = rs.getString("Difficulty");
                        String skillLC = rs.getString("Skill");
                        String instructionTextLC = rs.getString("Instruction_text");
                        String questionTextLC = rs.getString("Question_text");
                        String passageLC = rs.getString("Passage");
                        String optionsTextLC = rs.getString("Options_text");
                        String imageLC = rs.getString("Image");
                        String audioLC = rs.getString("Audio");
                        String optionALC = rs.getString("Option_a");
                        String optionBLC = rs.getString("Option_b");
                        String optionCLC = rs.getString("Option_c");
                        String optionDLC = rs.getString("Option_d");
                        String correctOptionLC = rs.getString("Correct_Option");
                        String examLC = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC,difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC,
                                optionALC, optionBLC, optionCLC, optionDLC, correctOptionLC, examLC);
                        questions.add(question);
                    }
                    
                    loadQuestions(questions);
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Writing")) {
                String queryW = "SELECT * FROM c2_writing";
                try (Connection conn = DB.getConnectionC2(); PreparedStatement pstmt = conn.prepareStatement(queryW)) {
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLC = rs.getInt("Content_id");
                        String cferLevelLC = rs.getString("Cfer_level");
                        String difficultyLC = rs.getString("Difficulty");
                        String skillLC = rs.getString("Skill");
                        String instructionTextLC = rs.getString("Instruction_text");
                        String questionTextLC = rs.getString("Question_text");
                        String passageLC = rs.getString("Passage");
                        String optionsTextLC = rs.getString("Options_text");
                        String imageLC = rs.getString("Image");
                        String audioLC = rs.getString("Audio");
                        String examW = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC, difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC, examW);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Speaking")) {
                String queryS = "SELECT * FROM c2_speaking";
                try (Connection conn = DB.getConnectionC2(); PreparedStatement pstmt = conn.prepareStatement(queryS)) {
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLC = rs.getInt("Content_id");
                        String cferLevelLC = rs.getString("Cfer_level");
                        String difficultyLC = rs.getString("Difficulty");
                        String skillLC = rs.getString("Skill");
                        String instructionTextLC = rs.getString("Instruction_text");
                        String questionTextLC = rs.getString("Question_text");
                        String passageLC = rs.getString("Passage");
                        String optionsTextLC = rs.getString("Options_text");
                        String imageLC = rs.getString("Image");
                        String audioLC = rs.getString("Audio");
                        String examS = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC, difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC, examS);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    //Metodo para poner las preguntas en la tabla 
    public void loadQuestions(List<Question> questions) {
        ObservableList<Question> data = FXCollections.observableArrayList(questions);
        questionsTableView.setItems(data);
    }
    
    //Metodo para generar el archivo con las preguntas en excel
    private void writeQuestionsToFile(List<Question> questions, String filename) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Questions");
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Content ID", "Cfer Level", "Difficulty", "Skill", "Instruction text", "Question text", "Passage", "Options text", "Image", "Audio", "Option A", "Option B", "Option C", "Option D", "Correct Option"};

        // Crear la cabecera
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Rellenar las filas con datos
        int rowNum = 1;
        for (Question question : questions) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(question.getContentId());
            row.createCell(1).setCellValue(question.getCferlevel());
            row.createCell(2).setCellValue(question.getDifficulty());
            row.createCell(3).setCellValue(question.getSkill());
            row.createCell(4).setCellValue(question.getInstructionText());
            row.createCell(5).setCellValue(question.getQuestionText());
            row.createCell(6).setCellValue(question.getPassage());
            row.createCell(7).setCellValue(question.getOptionsText());
            row.createCell(8).setCellValue(question.getImage());
            row.createCell(9).setCellValue(question.getAudio());
            row.createCell(10).setCellValue(question.getOptionA());
            row.createCell(11).setCellValue(question.getOptionB());
            row.createCell(12).setCellValue(question.getOptionC());
            row.createCell(13).setCellValue(question.getOptionD());
            row.createCell(14).setCellValue(question.getCorrectOption());
        }
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Archivo");
        fileChooser.setInitialFileName("Questions.xlsx");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos Excel", "*.xlsx"));

        // Mostrar el cuadro de diálogo de guardado
        Stage stage = (Stage) btnShow.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            // Asegurarse de que el archivo tenga la extensión correcta
            if (!file.getPath().endsWith(".xlsx")) {
                file = new File(file.getPath() + ".xlsx");
            }

            // Guardar el archivo Excel
            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
            } finally {
                workbook.close();
            }

            mostrarAlerta("Excel file download successful");
        } else {
            mostrarAlerta("El archivo no fue guardado.");
        }
        
        
    }
    
    private List<Question> returnQuestion(List<Question> q){
        return q;
    }
    
    //Metodo para generar el archivo que recibe moodle
    private void generateMoodleXMLFile(List <Question> questions, String filePath){
        try {
            // Mostrar el cuadro de diálogo para guardar el archivo
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Guardar Archivo");
            fileChooser.setInitialFileName("Questions.xml");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos XML", "*.xml"));

            // Mostrar el cuadro de diálogo de guardado
            Stage stage = (Stage) btnShow.getScene().getWindow();
            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                // Asegurarse de que el archivo tenga la extensión correcta
                if (!file.getPath().endsWith(".xml")) {
                    file = new File(file.getPath() + ".xml");
                }

                // Crear el documento XML
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("Questions");
                doc.appendChild(rootElement);

                for (Question question : questions) {
                    Element questionElement = doc.createElement("question");
                    questionElement.setAttribute("type", "multichoice");
                    rootElement.appendChild(questionElement);

                    Element contentIdElement = doc.createElement("Content_ID");
                    questionElement.appendChild(contentIdElement);
                    contentIdElement.appendChild(doc.createTextNode(String.valueOf(question.getContentId())));

                    Element cferLevelElement = doc.createElement("Cfer_level");
                    questionElement.appendChild(cferLevelElement);
                    cferLevelElement.appendChild(doc.createTextNode(String.valueOf(question.getCferlevel())));

                    Element difficultyElement = doc.createElement("Difficulty");
                    questionElement.appendChild(difficultyElement);
                    difficultyElement.appendChild(doc.createTextNode(question.getDifficulty()));

                    Element skillElement = doc.createElement("Skill");
                    questionElement.appendChild(skillElement);
                    skillElement.appendChild(doc.createTextNode(question.getSkill()));

                    Element instructionTextElement = doc.createElement("Instruction_Text");
                    questionElement.appendChild(instructionTextElement);
                    instructionTextElement.appendChild(doc.createTextNode(question.getInstructionText()));

                    Element questionTextElement = doc.createElement("Question_Text");
                    questionElement.appendChild(questionTextElement);
                    questionTextElement.appendChild(doc.createTextNode(question.getQuestionText()));

                    Element passageElement = doc.createElement("Passage");
                    questionElement.appendChild(passageElement);
                    passageElement.appendChild(doc.createTextNode(question.getPassage()));

                    Element optionsTextElement = doc.createElement("Options_text");
                    questionElement.appendChild(optionsTextElement);
                    optionsTextElement.appendChild(doc.createTextNode(question.getOptionsText()));

                    Element imageElement = doc.createElement("Image");
                    questionElement.appendChild(imageElement);
                    imageElement.appendChild(doc.createTextNode(question.getImage()));

                    Element audioElement = doc.createElement("Audio");
                    questionElement.appendChild(audioElement);
                    audioElement.appendChild(doc.createTextNode(question.getAudio()));

                    Element optionAElement = doc.createElement("OptionA");
                    questionElement.appendChild(optionAElement);
                    optionAElement.appendChild(doc.createTextNode(question.getOptionA()));

                    Element optionBElement = doc.createElement("OptionB");
                    questionElement.appendChild(optionBElement);
                    optionBElement.appendChild(doc.createTextNode(question.getOptionB()));

                    Element optionCElement = doc.createElement("OptionC");
                    questionElement.appendChild(optionCElement);
                    optionCElement.appendChild(doc.createTextNode(question.getOptionC()));

                    Element optionDElement = doc.createElement("OptionD");
                    questionElement.appendChild(optionDElement);
                    optionDElement.appendChild(doc.createTextNode(question.getOptionD()));

                    Element correctOptionElement = doc.createElement("Correct_option");
                    questionElement.appendChild(correctOptionElement);
                    correctOptionElement.appendChild(doc.createTextNode(question.getCorrectOption()));
                    
                    Element examElement = doc.createElement("Exam");
                    questionElement.appendChild(examElement);
                    examElement.appendChild(doc.createTextNode(question.getExam()));
                }

                // Guardar el archivo XML
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(file);
                transformer.transform(source, result);

                mostrarAlerta("XML Moodle file download successful");
            } else {
                mostrarAlerta("El archivo no fue guardado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
    private void downloadQuestion(ActionEvent event) throws IOException {
        ObservableList<Question> selectedQuestions = questionsTableView.getSelectionModel().getSelectedItems();
    
        if (selectedQuestions.isEmpty()) {
            mostrarAlerta("No se han seleccionado preguntas.");
            return;
        }
        
        List<Question> questionsToDownload = new ArrayList<>(selectedQuestions);
        writeQuestionsToFile(questionsToDownload, "Questions");
        generateMoodleXMLFile(questionsToDownload, "Questions XML");
        String user = DB.getUserName();
        DB.setUserNameActionDownload(user);
        
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
        Stage stage = (Stage) btnSearchGenQuestion.getScene().getWindow();
        stage.close();
        Platform.exit();
        System.exit(0);
    }

    private void goSearchQuestionGeneral(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/SearchQuestion/searchQuestionGeneral.fxml"));
        Parent root = cargadorApp.load();
        btnCancel.getScene().setRoot(root);

    }

    private void goLogs(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/logsDB/logsDB.fxml"));
        Parent root = cargadorApp.load();
        btnCancel.getScene().setRoot(root);
    }

    @FXML
    private void goSearchGenQuestion(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/SearchQuestion/searchQuestionGeneral.fxml"));
        Parent root = cargadorApp.load();
        btnCancel.getScene().setRoot(root);
    }

    @FXML
    private void goLog(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/logsDB/logsDB.fxml"));
        Parent root = cargadorApp.load();
        btnCancel.getScene().setRoot(root);
    }

    
    
}
