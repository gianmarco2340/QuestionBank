/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package SearchQuestion;

import DataBase.DataBaseUtil;
import Question.Question;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author brato
 */
public class SearchQuestionGeneralController implements Initializable {

    @FXML
    private Button btnSearch;
    @FXML
    private Button btnCancel;
    @FXML
    private ComboBox<String> difficultyComboBox;
    @FXML
    private ComboBox<String> skillComboBox;
    @FXML
    private TableView<Question> questionsTableView;
    @FXML
    private TableColumn<Question, Integer> content_id;
    @FXML
    private TableColumn<Question,String> difficultyColumn;
    @FXML
    private TableColumn<Question,String> skillColumn;
    @FXML
    private TableColumn<Question,String> instruction_text;
    @FXML
    private TableColumn<Question,String> question_text;
    @FXML
    private TableColumn<Question,String> passage;
    @FXML
    private TableColumn<Question,String> options_text;
    @FXML
    private TableColumn<Question,String> image;
    @FXML
    private TableColumn<Question,String> audio;
    @FXML
    private TableColumn<Question,String> option_A;
    @FXML
    private TableColumn<Question,String> option_B;
    @FXML
    private TableColumn<Question,String> option_C;
    @FXML
    private TableColumn<Question,String> option_D;
    @FXML
    private TableColumn<Question,String> correct_option;
    
    private static final String URL_A1 = "jdbc:mysql://localhost:3306/a1questions";
    private static final String URL_A2 = "jdbc:mysql://localhost:3306/a2questions";
    private static final String URL_B1 = "jdbc:mysql://localhost:3306/b1questions";
    private static final String URL_B2 = "jdbc:mysql://localhost:3306/b2questions";
    private static final String URL_C1 = "jdbc:mysql://localhost:3306/c1questions";
    
    private static final String USER = "root";  
    private static final String PASSWORD = "Habrat6714%";
    
    private DataBaseUtil DB = new DataBaseUtil();
    private Button btnAddQuestion;
    @FXML
    private TableColumn<Question, String> cferLevel;
    @FXML
    private Text idSGQUserName;
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
    private Button btnProfile1;
    @FXML
    private Button btnLogOut1;
    @FXML
    private TableColumn<Question, String> exam;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String username = DB.getUserName();
        idSGQUserName.setText(username);
        skillComboBox.getItems().addAll("Listening comprehension", "Reading comprehension", "Language use", "Speaking", "Writing");
        difficultyComboBox.getItems().addAll("Pre A1","A1", "A2", "B1", "B2", "C1", "C2");  
        btnSearch.disableProperty().bind(difficultyComboBox.valueProperty().isNull()
                                            .or(skillComboBox.valueProperty().isNull()));
        
        content_id.setCellValueFactory(new PropertyValueFactory<>("contentId"));
        cferLevel.setCellValueFactory(new PropertyValueFactory<>("cferlevel"));
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
        
    }   
    
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

    @FXML
    private void searchQuestion(ActionEvent event) {
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
                    DB.setUserNameSearchInd(user);
                    
                    
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else if(skillOpt.equals("Listening comprehension")){
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
                        Question question = new Question(contentIdLC, cferLevelLC,difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC,
                                optionALC, optionBLC, optionCLC, optionDLC, correctOptionLC, examLC);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                    DB.setUserNameSearchGen(user);
                } catch (SQLException e) {
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
                        Question question = new Question(contentIdR, cferLevelR,difficultyR, skillR,
                                instructionTextR, questionTextR, passageR, optionsTextR, imageR,
                                optionAR, optionBR, optionCR, optionDR, correctOptionR, examR);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                    DB.setUserNameSearchGen(user);
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Speaking")) {
                String queryL = "SELECT * FROM prea1_speaking";
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
                        String examS = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC,difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC, examS);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                    DB.setUserNameSearchGen(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Writing")) {
                String queryL = "SELECT * FROM prea1_writing";
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
                        String examW = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC,difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC, examW);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                    DB.setUserNameSearchGen(user);
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
                    DB.setUserNameSearchInd(user);
                    
                    
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
                        Question question = new Question(contentIdR, cferLevelR,difficultyR, skillR,
                                instructionTextR, questionTextR, passageR, optionsTextR, imageR,
                                optionAR, optionBR, optionCR, optionDR, correctOptionR, examR);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                    DB.setUserNameSearchGen(user);
                    
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
                        Question question = new Question(contentIdLC, cferLevelLC,difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC,
                                optionALC, optionBLC, optionCLC, optionDLC, correctOptionLC, examLC);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                    DB.setUserNameSearchGen(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Speaking")) {
                String queryL = "SELECT * FROM a1_speaking";
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
                        String examS = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC,difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC, examS);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                    DB.setUserNameSearchGen(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Writing")) {
                String queryL = "SELECT * FROM a1_writing";
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
                        String examW = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC,difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC, examW);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                    DB.setUserNameSearchGen(user);
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
                        String cferLevel = rs.getString("Cfer_level");
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
                        Question question = new Question(contentIdLU, cferLevel,difficultyLU, skillLU, instructionTextLU, questionTextLU, optionsTextLU,
                        imageLU, optionALU, optionBLU, optionCLU, optionDLU, correctOptionLU, examLU);
                        questions.add(question);
                        
                    }
                    loadQuestions(questions);
                    DB.setUserNameSearchGen(user);
                   
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (skillOpt.equals("Reading comprehension")) {
                String queryR = "SELECT * FROM a2_reading";
                try (Connection conn = getConnectionA2(); PreparedStatement pstmt = conn.prepareStatement(queryR)) {
                    
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdR = rs.getInt("Content_id");
                        String cferLevel = rs.getString("Cfer_level");
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
                        Question question = new Question(contentIdR, cferLevel,  difficultyR, skillR,
                                instructionTextR, questionTextR, passageR, optionsTextR, imageR,
                                optionAR, optionBR, optionCR, optionDR, correctOptionR, examR);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                    DB.setUserNameSearchGen(user);
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (skillOpt.equals("Listening comprehension")) {
                String queryL = "SELECT * FROM a2_listening";
                try (Connection conn = getConnectionA2(); PreparedStatement pstmt = conn.prepareStatement(queryL)) {
                    
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLC = rs.getInt("Content_id");
                        String cferLevel = rs.getString("Cfer_level");
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
                        Question question = new Question(contentIdLC, cferLevel,difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC,
                                optionALC, optionBLC, optionCLC, optionDLC, correctOptionLC, examLC);
                        questions.add(question);
                    }
                    
                    loadQuestions(questions);
                    DB.setUserNameSearchGen(user);
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Speaking")) {
                String queryL = "SELECT * FROM a2_speaking";
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
                        String examS = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC,difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC, examS);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                    DB.setUserNameSearchGen(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Writing")) {
                String queryL = "SELECT * FROM a2_writing";
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
                        String examW = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC,difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC, examW);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                    DB.setUserNameSearchGen(user);
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
                        String cferLevel = rs.getString("Cfer_level");
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
                        Question question = new Question(contentIdLU, cferLevel,difficultyLU, skillLU, instructionTextLU, questionTextLU, optionsTextLU,
                        imageLU, optionALU, optionBLU, optionCLU, optionDLU, correctOptionLU, examLU);
                        questions.add(question);
                        
                    }
                    loadQuestions(questions);
                    DB.setUserNameSearchGen(user);
                    
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
                    DB.setUserNameSearchGen(user);
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
                        Question question = new Question(contentIdLC, cferLevelLC, difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC,
                                optionALC, optionBLC, optionCLC, optionDLC, correctOptionLC, examLC);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                    DB.setUserNameSearchGen(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Speaking")) {
                String queryL = "SELECT * FROM b1_speaking";
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
                        String examS = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC,difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC, examS);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                    DB.setUserNameSearchGen(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Writing")) {
                String queryL = "SELECT * FROM b1_writing";
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
                        String examW = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC,difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC, examW);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                    DB.setUserNameSearchGen(user);
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
                    DB.setUserNameSearchGen(user);
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
                    DB.setUserNameSearchGen(user);
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
                    DB.setUserNameSearchGen(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Speaking")) {
                String queryL = "SELECT * FROM b2_speaking";
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
                        String examS = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC,difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC, examS);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                    DB.setUserNameSearchGen(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Writing")) {
                String queryL = "SELECT * FROM b2_writing";
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
                        String examW = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC,difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC, examW);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                    DB.setUserNameSearchGen(user);
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
                    DB.setUserNameSearchGen(user);
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
                        Question question = new Question(contentIdR, cferLevelR, difficultyR, skillR,
                                instructionTextR, questionTextR, passageR, optionsTextR, imageR,
                                optionAR, optionBR, optionCR, optionDR, correctOptionR, examR);
                        questions.add(question);
                        
                    }
                    loadQuestions(questions);
                    DB.setUserNameSearchGen(user);
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
                    DB.setUserNameSearchGen(user);
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Speaking")) {
                String queryL = "SELECT * FROM c1_speaking";
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
                        String examS = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC,difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC, examS);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                    DB.setUserNameSearchGen(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Writing")) {
                String queryL = "SELECT * FROM c1_writing";
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
                        String examW = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC,difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC, examW);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                    DB.setUserNameSearchGen(user);
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
                    DB.setUserNameSearchGen(user);
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
                        Question question = new Question(contentIdR, cferLevelR, difficultyR, skillR,
                                instructionTextR, questionTextR, passageR, optionsTextR, imageR,
                                optionAR, optionBR, optionCR, optionDR, correctOptionR, examR);
                        questions.add(question);
                        
                    }
                    loadQuestions(questions);
                    DB.setUserNameSearchGen(user);
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
                    DB.setUserNameSearchGen(user);
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Speaking")) {
                String queryL = "SELECT * FROM c2_speaking";
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
                        String examS = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC,difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC, examS);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                    DB.setUserNameSearchGen(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (skillOpt.equals("Writing")) {
                String queryL = "SELECT * FROM c2_writing";
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
                        String examW = rs.getString("Exam");
                        Question question = new Question(contentIdLC, cferLevelLC,difficultyLC, skillLC,
                                instructionTextLC, questionTextLC, passageLC, optionsTextLC, imageLC, audioLC, examW);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                    DB.setUserNameSearchGen(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void loadQuestions(List<Question> questions) {
        ObservableList<Question> data = FXCollections.observableArrayList(questions);
        questionsTableView.setItems(data);
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
    private void goDownloadQuestion(ActionEvent event) throws IOException{
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
        Stage stage = (Stage) btnDownloadQuestion1.getScene().getWindow();
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
