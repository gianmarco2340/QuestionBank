/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package DeleteQuestion;

import DataBase.DataBaseUtil;
import Question.Question;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author brato
 */
public class DeleteQuestionController implements Initializable {

    private Button btnAddQuestion;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<Question> questionsTableView;
    @FXML
    private TableColumn<Question, String> content_id;
    @FXML
    private TableColumn<Question, String> cfer_level;
    @FXML
    private TableColumn<Question, String> difficultyColumn;
    @FXML
    private TableColumn<Question, String> skillColumn;
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
    private ComboBox<String> difficultyComboBox;
    @FXML
    private ComboBox<String> skillComboBox;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField contentIDLabel;
    
    private DataBaseUtil DB = new DataBaseUtil();
    private Question questions;
    @FXML
    private Text userIdDeleteQuestion;
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
    private Button btnLog1;
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
        userIdDeleteQuestion.setText(username);
        skillComboBox.getItems().setAll("Language use", "Listening comprehension", "Reading comprehension", "Writing", "Speaking");
        difficultyComboBox.getItems().setAll("Pre A1","A1", "A2", "B1", "B2", "C1", "C2");
        btnSearch.disableProperty().bind(difficultyComboBox.valueProperty().isNull()
                                        .or(skillComboBox.valueProperty().isNull()));
        
        btnDelete1.disableProperty().bind(difficultyComboBox.valueProperty().isNull()
                                            .or(skillComboBox.valueProperty().isNull())
                                            .or(questionsTableView.selectionModelProperty().isNull()));
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
    
    private void goSearchGeneralQuestion(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/SearchQuestion/searchQuestionGeneral.fxml"));
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
    private void searchQuestion(ActionEvent event) {
        if(difficultyComboBox.getValue().equals("Pre A1")){
            if(skillComboBox.getValue().equals("Language use")){
                List<Question> questions = new ArrayList<>();
                String queryLU = "SELECT * FROM prea1_language_use WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionPreA1(); PreparedStatement pstmt = conn.prepareStatement(queryLU)) {
                    pstmt.setString(1, contentIDLabel.getText());
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

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (skillComboBox.getValue().equals("Reading comprehension")) {
                List<Question> questions = new ArrayList<>();
                String queryR = "SELECT * FROM prea1_reading WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionPreA1(); PreparedStatement pstmt = conn.prepareStatement(queryR)) {
                    pstmt.setString(1, contentIDLabel.getText());
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
                        Question question = new Question(contentIdR, cferLevelR, difficultyR, skillR, instructionTextR, questionTextR, passageR, optionsTextR,
                                imageR, optionAR, optionBR, optionCR, optionDR, correctOptionR, examR);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (skillComboBox.getValue().equals("Listening comprehension")) {
                List<Question> questions = new ArrayList<>();
                String queryL = "SELECT * FROM prea1_listening WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionPreA1(); PreparedStatement pstmt = conn.prepareStatement(queryL)) {
                    pstmt.setString(1, contentIDLabel.getText());
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdL = rs.getInt("Content_id");
                        String cferLevelL = rs.getString("Cfer_level");
                        String difficultyL = rs.getString("Difficulty");
                        String skillL = rs.getString("Skill");
                        String instructionTextL = rs.getString("Instruction_text");
                        String questionTextL = rs.getString("Question_text");
                        String passageL = rs.getString("Passage");
                        String optionsTextL = rs.getString("Options_text");
                        String imageL = rs.getString("Image");
                        String audioL = rs.getString("Audio");
                        String optionAL = rs.getString("Option_a");
                        String optionBL = rs.getString("Option_b");
                        String optionCL = rs.getString("Option_c");
                        String optionDL = rs.getString("Option_d");
                        String correctOptionL = rs.getString("Correct_Option");
                        String examL = rs.getString("Exam");
                        Question question = new Question(contentIdL, cferLevelL, difficultyL, skillL, instructionTextL, questionTextL, passageL, optionsTextL,
                                imageL, audioL, optionAL, optionBL, optionCL, optionDL, correctOptionL, examL);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(skillComboBox.getValue().equals("Writing")){
                List<Question> questions = new ArrayList<>();
                String queryL = "SELECT * FROM prea1_writing WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionPreA1(); PreparedStatement pstmt = conn.prepareStatement(queryL)) {
                    pstmt.setString(1, contentIDLabel.getText());
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdL = rs.getInt("Content_id");
                        String cferLevelL = rs.getString("Cfer_level");
                        String difficultyL = rs.getString("Difficulty");
                        String skillL = rs.getString("Skill");
                        String instructionTextL = rs.getString("Instruction_text");
                        String questionTextL = rs.getString("Question_text");
                        String passageL = rs.getString("Passage");
                        String optionsTextL = rs.getString("Options_text");
                        String imageL = rs.getString("Image");
                        String audioL = rs.getString("Audio");
                        String examW = rs.getString("Exam");
                        Question question = new Question(contentIdL, cferLevelL, difficultyL, skillL, instructionTextL, questionTextL, passageL, optionsTextL,
                                imageL, audioL, examW);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(skillComboBox.getValue().equals("Speaking")){
                List<Question> questions = new ArrayList<>();
                String queryL = "SELECT * FROM prea1_speaking WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionPreA1(); PreparedStatement pstmt = conn.prepareStatement(queryL)) {
                    pstmt.setString(1, contentIDLabel.getText());
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdL = rs.getInt("Content_id");
                        String cferLevelL = rs.getString("Cfer_level");
                        String difficultyL = rs.getString("Difficulty");
                        String skillL = rs.getString("Skill");
                        String instructionTextL = rs.getString("Instruction_text");
                        String questionTextL = rs.getString("Question_text");
                        String passageL = rs.getString("Passage");
                        String optionsTextL = rs.getString("Options_text");
                        String imageL = rs.getString("Image");
                        String audioL = rs.getString("Audio");
                        String examS = rs.getString("Exam");
                        Question question = new Question(contentIdL, cferLevelL, difficultyL, skillL, instructionTextL, questionTextL, passageL, optionsTextL,
                                imageL, audioL, examS);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        if (difficultyComboBox.getValue().equals("A1")) {
            //Caso language use
            if (skillComboBox.getValue().equals("Language use")) {
                //Buscar pregunta que tenga el mismo content ID
                List<Question> questions = new ArrayList<>();
                String queryLU = "SELECT * FROM a1_language_use WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionA1(); PreparedStatement pstmt = conn.prepareStatement(queryLU)) {
                    pstmt.setString(1, contentIDLabel.getText());
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

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (skillComboBox.getValue().equals("Reading comprehension")) {
                List<Question> questions = new ArrayList<>();
                String queryR = "SELECT * FROM a1_reading WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionA1(); PreparedStatement pstmt = conn.prepareStatement(queryR)) {
                    pstmt.setString(1, contentIDLabel.getText());
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
                        Question question = new Question(contentIdR, cferLevelR, difficultyR, skillR, instructionTextR, questionTextR, passageR, optionsTextR,
                                imageR, optionAR, optionBR, optionCR, optionDR, correctOptionR, examR);
                        questions.add(question);
                    }
                    loadQuestions(questions);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (skillComboBox.getValue().equals("Listening comprehension")) {
                List<Question> questions = new ArrayList<>();
                String queryL = "SELECT * FROM a1_listening WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionA1(); PreparedStatement pstmt = conn.prepareStatement(queryL)) {
                    pstmt.setString(1, contentIDLabel.getText());
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdL = rs.getInt("Content_id");
                        String cferLevelL = rs.getString("Cfer_level");
                        String difficultyL = rs.getString("Difficulty");
                        String skillL = rs.getString("Skill");
                        String instructionTextL = rs.getString("Instruction_text");
                        String questionTextL = rs.getString("Question_text");
                        String passageL = rs.getString("Passage");
                        String optionsTextL = rs.getString("Options_text");
                        String imageL = rs.getString("Image");
                        String audioL = rs.getString("Audio");
                        String optionAL = rs.getString("Option_a");
                        String optionBL = rs.getString("Option_b");
                        String optionCL = rs.getString("Option_c");
                        String optionDL = rs.getString("Option_d");
                        String correctOptionL = rs.getString("Correct_Option");
                        String examL = rs.getString("Exam");
                        Question question = new Question(contentIdL, cferLevelL, difficultyL, skillL, instructionTextL, questionTextL, passageL, optionsTextL,
                                imageL, audioL, optionAL, optionBL, optionCL, optionDL, correctOptionL, examL);
                        questions.add(question);
                    }
                    loadQuestions(questions);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(skillComboBox.getValue().equals("Writing")){
                List<Question> questions = new ArrayList<>();
                String queryL = "SELECT * FROM a1_writing WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionA1(); PreparedStatement pstmt = conn.prepareStatement(queryL)) {
                    pstmt.setString(1, contentIDLabel.getText());
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdL = rs.getInt("Content_id");
                        String cferLevelL = rs.getString("Cfer_level");
                        String difficultyL = rs.getString("Difficulty");
                        String skillL = rs.getString("Skill");
                        String instructionTextL = rs.getString("Instruction_text");
                        String questionTextL = rs.getString("Question_text");
                        String passageL = rs.getString("Passage");
                        String optionsTextL = rs.getString("Options_text");
                        String imageL = rs.getString("Image");
                        String audioL = rs.getString("Audio");
                        String examW = rs.getString("Exam");
                        Question question = new Question(contentIdL, cferLevelL, difficultyL, skillL, instructionTextL, questionTextL, passageL, optionsTextL,
                                imageL, audioL, examW);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(skillComboBox.getValue().equals("Speaking")){
                List<Question> questions = new ArrayList<>();
                String queryL = "SELECT * FROM a1_speaking WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionA1(); PreparedStatement pstmt = conn.prepareStatement(queryL)) {
                    pstmt.setString(1, contentIDLabel.getText());
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdL = rs.getInt("Content_id");
                        String cferLevelL = rs.getString("Cfer_level");
                        String difficultyL = rs.getString("Difficulty");
                        String skillL = rs.getString("Skill");
                        String instructionTextL = rs.getString("Instruction_text");
                        String questionTextL = rs.getString("Question_text");
                        String passageL = rs.getString("Passage");
                        String optionsTextL = rs.getString("Options_text");
                        String imageL = rs.getString("Image");
                        String audioL = rs.getString("Audio");
                        String examS = rs.getString("Exam");
                        Question question = new Question(contentIdL, cferLevelL, difficultyL, skillL, instructionTextL, questionTextL, passageL, optionsTextL,
                                imageL, audioL, examS);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (difficultyComboBox.getValue().equals("A2")) {
            //Caso language use
            if (skillComboBox.getValue().equals("Language use")) {
                //Buscar pregunta que tenga el mismo primary key
                List<Question> questions = new ArrayList<>();
                String queryLU = "SELECT * FROM a2_language_use WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionA2(); PreparedStatement pstmt = conn.prepareStatement(queryLU)) {
                    pstmt.setString(1, contentIDLabel.getText());
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

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillComboBox.getValue().equals("Reading comprehension")) {
                List<Question> questions = new ArrayList<>();
                String queryR = "SELECT * FROM a2_reading WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionA2(); PreparedStatement pstmt = conn.prepareStatement(queryR)) {
                    pstmt.setString(1, contentIDLabel.getText());
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
                        
                        Question question = new Question(contentIdR, cferLevelR,difficultyR, skillR, instructionTextR, questionTextR, passageR, optionsTextR,
                                imageR, optionAR, optionBR, optionCR, optionDR, correctOptionR, examR);
                        questions.add(question);
                    }
                    loadQuestions(questions);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillComboBox.getValue().equals("Listening comprehension")) {
                List<Question> questions = new ArrayList<>();
                String queryL = "SELECT * FROM a2_listening WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionA2(); PreparedStatement pstmt = conn.prepareStatement(queryL)) {
                    pstmt.setString(1, contentIDLabel.getText());
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdL = rs.getInt("Content_id");
                        String cferLevelL = rs.getString("Cfer_level");
                        String difficultyL = rs.getString("Difficulty");
                        String skillL = rs.getString("Skill");
                        String instructionTextL = rs.getString("Instruction_text");
                        String questionTextL = rs.getString("Question_text");
                        String passageL = rs.getString("Passage");
                        String optionsTextL = rs.getString("Options_text");
                        String imageL = rs.getString("Image");
                        String audioL = rs.getString("Audio");
                        String optionAL = rs.getString("Option_a");
                        String optionBL = rs.getString("Option_b");
                        String optionCL = rs.getString("Option_c");
                        String optionDL = rs.getString("Option_d");
                        String correctOptionL = rs.getString("Correct_Option");
                        String examL = rs.getString("Exam");
                        Question question = new Question(contentIdL, cferLevelL,difficultyL, skillL, instructionTextL, questionTextL, passageL, optionsTextL,
                                imageL, audioL, optionAL, optionBL, optionCL, optionDL, correctOptionL, examL);
                        questions.add(question);
                    }
                    loadQuestions(questions);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(skillComboBox.getValue().equals("Writing")){
                List<Question> questions = new ArrayList<>();
                String queryW = "SELECT * FROM a2_writing WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionA2(); PreparedStatement pstmt = conn.prepareStatement(queryW)) {
                    pstmt.setString(1, contentIDLabel.getText());
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdL = rs.getInt("Content_id");
                        String cferLevelL = rs.getString("Cfer_level");
                        String difficultyL = rs.getString("Difficulty");
                        String skillL = rs.getString("Skill");
                        String instructionTextL = rs.getString("Instruction_text");
                        String questionTextL = rs.getString("Question_text");
                        String passageL = rs.getString("Passage");
                        String optionsTextL = rs.getString("Options_text");
                        String imageL = rs.getString("Image");
                        String audioL = rs.getString("Audio");
                        String examW = rs.getString("Exam");
                        Question question = new Question(contentIdL, cferLevelL, difficultyL, skillL, instructionTextL, questionTextL, passageL, optionsTextL,
                                imageL, audioL, examW);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(skillComboBox.getValue().equals("Speaking")){
                List<Question> questions = new ArrayList<>();
                String queryS = "SELECT * FROM a2_speaking WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionA2(); PreparedStatement pstmt = conn.prepareStatement(queryS)) {
                    pstmt.setString(1, contentIDLabel.getText());
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdL = rs.getInt("Content_id");
                        String cferLevelL = rs.getString("Cfer_level");
                        String difficultyL = rs.getString("Difficulty");
                        String skillL = rs.getString("Skill");
                        String instructionTextL = rs.getString("Instruction_text");
                        String questionTextL = rs.getString("Question_text");
                        String passageL = rs.getString("Passage");
                        String optionsTextL = rs.getString("Options_text");
                        String imageL = rs.getString("Image");
                        String audioL = rs.getString("Audio");
                        String examS = rs.getString("Exam");
                        Question question = new Question(contentIdL, cferLevelL, difficultyL, skillL, instructionTextL, questionTextL, passageL, optionsTextL,
                                imageL, audioL, examS);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
            
        if (difficultyComboBox.getValue().equals("B1")) {
            //Caso language use
            if (skillComboBox.getValue().equals("Language use")) {
                //Buscar pregunta que tenga el mismo primary key
                List<Question> questions = new ArrayList<>();
                String queryLU = "SELECT * FROM b1_language_use WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionB1(); PreparedStatement pstmt = conn.prepareStatement(queryLU)) {
                    pstmt.setString(1, contentIDLabel.getText());
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

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillComboBox.getValue().equals("Reading comprehension")) {
                List<Question> questions = new ArrayList<>();
                String queryR = "SELECT * FROM b1_reading WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionB1(); PreparedStatement pstmt = conn.prepareStatement(queryR)) {
                    pstmt.setString(1, contentIDLabel.getText());
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLU = rs.getInt("Content_id");
                        String cferLevelR = rs.getString("Cfer_level");
                        String difficultyLU = rs.getString("Difficulty");
                        String skillLU = rs.getString("Skill");
                        String instructionTextLU = rs.getString("Instruction_text");
                        String questionTextLU = rs.getString("Question_text");
                        String passageR = rs.getString("Passage");
                        String optionsTextLU = rs.getString("Options_text");
                        String imageLU = rs.getString("Image");
                        String optionALU = rs.getString("Option_a");
                        String optionBLU = rs.getString("Option_b");
                        String optionCLU = rs.getString("Option_c");
                        String optionDLU = rs.getString("Option_d");
                        String correctOptionLU = rs.getString("Correct_Option");
                        String examR = rs.getString("Exam");
                        Question question = new Question(contentIdLU, cferLevelR,difficultyLU, skillLU, instructionTextLU, questionTextLU, passageR, optionsTextLU,
                                imageLU, optionALU, optionBLU, optionCLU, optionDLU, correctOptionLU, examR);
                        questions.add(question);
                    }
                    loadQuestions(questions);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillComboBox.getValue().equals("Listening comprehension")) {
                List<Question> questions = new ArrayList<>();
                String queryL = "SELECT * FROM b1_listening WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionB1(); PreparedStatement pstmt = conn.prepareStatement(queryL)) {
                    pstmt.setString(1, contentIDLabel.getText());
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdL = rs.getInt("Content_id");
                        String cferLevelL = rs.getString("Cfer_level");
                        String difficultyL = rs.getString("Difficulty");
                        String skillL = rs.getString("Skill");
                        String instructionTextL = rs.getString("Instruction_text");
                        String questionTextL = rs.getString("Question_text");
                        String passageL = rs.getString("Passage");
                        String optionsTextL = rs.getString("Options_text");
                        String imageL = rs.getString("Image");
                        String audioL = rs.getString("Audio");
                        String optionAL = rs.getString("Option_a");
                        String optionBL = rs.getString("Option_b");
                        String optionCL = rs.getString("Option_c");
                        String optionDL = rs.getString("Option_d");
                        String correctOptionL = rs.getString("Correct_Option");
                        String examL = rs.getString("Exam");
                        Question question = new Question(contentIdL, cferLevelL,difficultyL, skillL, instructionTextL, questionTextL, passageL, optionsTextL,
                                imageL, audioL, optionAL, optionBL, optionCL, optionDL, correctOptionL, examL);
                        questions.add(question);
                    }
                    loadQuestions(questions);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(skillComboBox.getValue().equals("Writing")){
                List<Question> questions = new ArrayList<>();
                String queryW = "SELECT * FROM b1_writing WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionB1(); PreparedStatement pstmt = conn.prepareStatement(queryW)) {
                    pstmt.setString(1, contentIDLabel.getText());
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdL = rs.getInt("Content_id");
                        String cferLevelL = rs.getString("Cfer_level");
                        String difficultyL = rs.getString("Difficulty");
                        String skillL = rs.getString("Skill");
                        String instructionTextL = rs.getString("Instruction_text");
                        String questionTextL = rs.getString("Question_text");
                        String passageL = rs.getString("Passage");
                        String optionsTextL = rs.getString("Options_text");
                        String imageL = rs.getString("Image");
                        String audioL = rs.getString("Audio");
                        String examW = rs.getString("Exam");
                        Question question = new Question(contentIdL, cferLevelL, difficultyL, skillL, instructionTextL, questionTextL, passageL, optionsTextL,
                                imageL, audioL, examW);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(skillComboBox.getValue().equals("Speaking")){
                List<Question> questions = new ArrayList<>();
                String queryS = "SELECT * FROM b1_speaking WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionB1(); PreparedStatement pstmt = conn.prepareStatement(queryS)) {
                    pstmt.setString(1, contentIDLabel.getText());
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdL = rs.getInt("Content_id");
                        String cferLevelL = rs.getString("Cfer_level");
                        String difficultyL = rs.getString("Difficulty");
                        String skillL = rs.getString("Skill");
                        String instructionTextL = rs.getString("Instruction_text");
                        String questionTextL = rs.getString("Question_text");
                        String passageL = rs.getString("Passage");
                        String optionsTextL = rs.getString("Options_text");
                        String imageL = rs.getString("Image");
                        String audioL = rs.getString("Audio");
                        String examS = rs.getString("Exam");
                        Question question = new Question(contentIdL, cferLevelL, difficultyL, skillL, instructionTextL, questionTextL, passageL, optionsTextL,
                                imageL, audioL, examS);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        if (difficultyComboBox.getValue().equals("B2")) {
            //Caso language use
            if (skillComboBox.getValue().equals("Language use")) {
                //Buscar pregunta que tenga el mismo primary key
                List<Question> questions = new ArrayList<>();
                String queryLU = "SELECT * FROM b2_language_use WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionB2(); PreparedStatement pstmt = conn.prepareStatement(queryLU)) {
                    pstmt.setString(1, contentIDLabel.getText());
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

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillComboBox.getValue().equals("Reading comprehension")) {
                List<Question> questions = new ArrayList<>();
                String queryR = "SELECT * FROM b2_reading WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionB2(); PreparedStatement pstmt = conn.prepareStatement(queryR)) {
                    pstmt.setString(1, contentIDLabel.getText());
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLU = rs.getInt("Content_id");
                        String cferLevelR = rs.getString("Cfer_level");
                        String difficultyLU = rs.getString("Difficulty");
                        String skillLU = rs.getString("Skill");
                        String instructionTextLU = rs.getString("Instruction_text");
                        String questionTextLU = rs.getString("Question_text");
                        String passageR = rs.getString("Passage");
                        String optionsTextLU = rs.getString("Options_text");
                        String imageLU = rs.getString("Image");
                        String optionALU = rs.getString("Option_a");
                        String optionBLU = rs.getString("Option_b");
                        String optionCLU = rs.getString("Option_c");
                        String optionDLU = rs.getString("Option_d");
                        String correctOptionLU = rs.getString("Correct_Option");
                        String examR = rs.getString("Exam");
                        Question question = new Question(contentIdLU, cferLevelR, difficultyLU, skillLU, instructionTextLU, questionTextLU, passageR, optionsTextLU,
                                imageLU, optionALU, optionBLU, optionCLU, optionDLU, correctOptionLU, examR);
                        questions.add(question);
                    }
                    loadQuestions(questions);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillComboBox.getValue().equals("Listening comprehension")) {
                List<Question> questions = new ArrayList<>();
                String queryL = "SELECT * FROM b2_listening WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionB2(); PreparedStatement pstmt = conn.prepareStatement(queryL)) {
                    pstmt.setString(1, contentIDLabel.getText());
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdL = rs.getInt("Content_id");
                        String difficultyL = rs.getString("Difficulty");
                        String skillL = rs.getString("Skill");
                        String instructionTextL = rs.getString("Instruction_text");
                        String questionTextL = rs.getString("Question_text");
                        String passageL = rs.getString("Passage");
                        String optionsTextL = rs.getString("Options_text");
                        String imageL = rs.getString("Image");
                        String audioL = rs.getString("Audio");
                        String optionAL = rs.getString("Option_a");
                        String optionBL = rs.getString("Option_b");
                        String optionCL = rs.getString("Option_c");
                        String optionDL = rs.getString("Option_d");
                        String correctOptionL = rs.getString("Correct_Option");
                        String examL = rs.getString("Exam");
                        Question question = new Question(contentIdL, difficultyL, skillL, instructionTextL, questionTextL, passageL, optionsTextL,
                                imageL, audioL, optionAL, optionBL, optionCL, optionDL, correctOptionL, examL);
                        questions.add(question);
                    }
                    loadQuestions(questions);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(skillComboBox.getValue().equals("Writing")){
                List<Question> questions = new ArrayList<>();
                String queryW = "SELECT * FROM b2_writing WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionB2(); PreparedStatement pstmt = conn.prepareStatement(queryW)) {
                    pstmt.setString(1, contentIDLabel.getText());
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdL = rs.getInt("Content_id");
                        String cferLevelL = rs.getString("Cfer_level");
                        String difficultyL = rs.getString("Difficulty");
                        String skillL = rs.getString("Skill");
                        String instructionTextL = rs.getString("Instruction_text");
                        String questionTextL = rs.getString("Question_text");
                        String passageL = rs.getString("Passage");
                        String optionsTextL = rs.getString("Options_text");
                        String imageL = rs.getString("Image");
                        String audioL = rs.getString("Audio");
                        String examW = rs.getString("Exam");
                        Question question = new Question(contentIdL, cferLevelL, difficultyL, skillL, instructionTextL, questionTextL, passageL, optionsTextL,
                                imageL, audioL, examW);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(skillComboBox.getValue().equals("Speaking")){
                List<Question> questions = new ArrayList<>();
                String queryS = "SELECT * FROM b2_speaking WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionB2(); PreparedStatement pstmt = conn.prepareStatement(queryS)) {
                    pstmt.setString(1, contentIDLabel.getText());
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdL = rs.getInt("Content_id");
                        String cferLevelL = rs.getString("Cfer_level");
                        String difficultyL = rs.getString("Difficulty");
                        String skillL = rs.getString("Skill");
                        String instructionTextL = rs.getString("Instruction_text");
                        String questionTextL = rs.getString("Question_text");
                        String passageL = rs.getString("Passage");
                        String optionsTextL = rs.getString("Options_text");
                        String imageL = rs.getString("Image");
                        String audioL = rs.getString("Audio");
                        String examS = rs.getString("Exam");
                        Question question = new Question(contentIdL, cferLevelL, difficultyL, skillL, instructionTextL, questionTextL, passageL, optionsTextL,
                                imageL, audioL, examS);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }

        if (difficultyComboBox.getValue().equals("C1")) {
            //Caso language use
            if (skillComboBox.getValue().equals("Language use")) {
                //Buscar pregunta que tenga el mismo primary key
                List<Question> questions = new ArrayList<>();
                String queryLU = "SELECT * FROM c1_language_use WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionC1(); PreparedStatement pstmt = conn.prepareStatement(queryLU)) {
                    pstmt.setString(1, contentIDLabel.getText());
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

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillComboBox.getValue().equals("Reading comprehension")) {
                List<Question> questions = new ArrayList<>();
                String queryLU = "SELECT * FROM c1_reading WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionC1(); PreparedStatement pstmt = conn.prepareStatement(queryLU)) {
                    pstmt.setString(1, contentIDLabel.getText());
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLU = rs.getInt("Content_id");
                        String cferLevelR = rs.getString("Cfer_level");
                        String difficultyLU = rs.getString("Difficulty");
                        String skillLU = rs.getString("Skill");
                        String instructionTextLU = rs.getString("Instruction_text");
                        String questionTextLU = rs.getString("Question_text");
                        String passageR = rs.getString("Passage");
                        String optionsTextLU = rs.getString("Options_text");
                        String imageLU = rs.getString("Image");
                        String optionALU = rs.getString("Option_a");
                        String optionBLU = rs.getString("Option_b");
                        String optionCLU = rs.getString("Option_c");
                        String optionDLU = rs.getString("Option_d");
                        String correctOptionLU = rs.getString("Correct_Option");
                        String examR = rs.getString("Exam");
                        Question question = new Question(contentIdLU, cferLevelR, difficultyLU, skillLU, instructionTextLU, questionTextLU, passageR, optionsTextLU,
                                imageLU, optionALU, optionBLU, optionCLU, optionDLU, correctOptionLU, examR);
                        questions.add(question);
                    }
                    loadQuestions(questions);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillComboBox.getValue().equals("Listening comprehension")) {
                List<Question> questions = new ArrayList<>();
                String queryLU = "SELECT * FROM c1_listening WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionC1(); PreparedStatement pstmt = conn.prepareStatement(queryLU)) {
                    pstmt.setString(1, contentIDLabel.getText());
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdL = rs.getInt("Content_id");
                        String cferLevelL = rs.getString("Cfer_level");
                        String difficultyL = rs.getString("Difficulty");
                        String skillL = rs.getString("Skill");
                        String instructionTextL = rs.getString("Instruction_text");
                        String questionTextL = rs.getString("Question_text");
                        String passageL = rs.getString("Passage");
                        String optionsTextL = rs.getString("Options_text");
                        String imageL = rs.getString("Image");
                        String audioL = rs.getString("Audio");
                        String optionAL = rs.getString("Option_a");
                        String optionBL = rs.getString("Option_b");
                        String optionCL = rs.getString("Option_c");
                        String optionDL = rs.getString("Option_d");
                        String correctOptionL = rs.getString("Correct_Option");
                        String examL = rs.getString("Exam");
                        Question question = new Question(contentIdL, cferLevelL,difficultyL, skillL, instructionTextL, questionTextL, passageL, optionsTextL,
                                imageL, audioL, optionAL, optionBL, optionCL, optionDL, correctOptionL, examL);
                        questions.add(question);
                    }
                    loadQuestions(questions);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(skillComboBox.getValue().equals("Writing")){
                List<Question> questions = new ArrayList<>();
                String queryW = "SELECT * FROM c1_writing WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionC1(); PreparedStatement pstmt = conn.prepareStatement(queryW)) {
                    pstmt.setString(1, contentIDLabel.getText());
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdL = rs.getInt("Content_id");
                        String cferLevelL = rs.getString("Cfer_level");
                        String difficultyL = rs.getString("Difficulty");
                        String skillL = rs.getString("Skill");
                        String instructionTextL = rs.getString("Instruction_text");
                        String questionTextL = rs.getString("Question_text");
                        String passageL = rs.getString("Passage");
                        String optionsTextL = rs.getString("Options_text");
                        String imageL = rs.getString("Image");
                        String audioL = rs.getString("Audio");
                        String examW = rs.getString("Exam");
                        Question question = new Question(contentIdL, cferLevelL, difficultyL, skillL, instructionTextL, questionTextL, passageL, optionsTextL,
                                imageL, audioL, examW);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(skillComboBox.getValue().equals("Speaking")){
                List<Question> questions = new ArrayList<>();
                String queryS = "SELECT * FROM c1_speaking WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionC1(); PreparedStatement pstmt = conn.prepareStatement(queryS)) {
                    pstmt.setString(1, contentIDLabel.getText());
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdL = rs.getInt("Content_id");
                        String cferLevelL = rs.getString("Cfer_level");
                        String difficultyL = rs.getString("Difficulty");
                        String skillL = rs.getString("Skill");
                        String instructionTextL = rs.getString("Instruction_text");
                        String questionTextL = rs.getString("Question_text");
                        String passageL = rs.getString("Passage");
                        String optionsTextL = rs.getString("Options_text");
                        String imageL = rs.getString("Image");
                        String audioL = rs.getString("Audio");
                        String examS = rs.getString("Exam");
                        Question question = new Question(contentIdL, cferLevelL, difficultyL, skillL, instructionTextL, questionTextL, passageL, optionsTextL,
                                imageL, audioL, examS);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        if (difficultyComboBox.getValue().equals("C2")) {
            //Caso language use
            if (skillComboBox.getValue().equals("Language use")) {
                //Buscar pregunta que tenga el mismo primary key
                List<Question> questions = new ArrayList<>();
                String queryLU = "SELECT * FROM c2_language_use WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionC2(); PreparedStatement pstmt = conn.prepareStatement(queryLU)) {
                    pstmt.setString(1, contentIDLabel.getText());
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

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillComboBox.getValue().equals("Reading comprehension")) {
                List<Question> questions = new ArrayList<>();
                String queryLU = "SELECT * FROM c2_reading WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionC2(); PreparedStatement pstmt = conn.prepareStatement(queryLU)) {
                    pstmt.setString(1, contentIDLabel.getText());
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdLU = rs.getInt("Content_id");
                        String cferLevelR = rs.getString("Cfer_level");
                        String difficultyLU = rs.getString("Difficulty");
                        String skillLU = rs.getString("Skill");
                        String instructionTextLU = rs.getString("Instruction_text");
                        String questionTextLU = rs.getString("Question_text");
                        String passageR = rs.getString("Passage");
                        String optionsTextLU = rs.getString("Options_text");
                        String imageLU = rs.getString("Image");
                        String optionALU = rs.getString("Option_a");
                        String optionBLU = rs.getString("Option_b");
                        String optionCLU = rs.getString("Option_c");
                        String optionDLU = rs.getString("Option_d");
                        String correctOptionLU = rs.getString("Correct_Option");
                        String examR = rs.getString("Exam");
                        Question question = new Question(contentIdLU, cferLevelR, difficultyLU, skillLU, instructionTextLU, questionTextLU, passageR, optionsTextLU,
                                imageLU, optionALU, optionBLU, optionCLU, optionDLU, correctOptionLU, examR);
                        questions.add(question);
                    }
                    loadQuestions(questions);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (skillComboBox.getValue().equals("Listening comprehension")) {
                List<Question> questions = new ArrayList<>();
                String queryLU = "SELECT * FROM c2_listening WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionC2(); PreparedStatement pstmt = conn.prepareStatement(queryLU)) {
                    pstmt.setString(1, contentIDLabel.getText());
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdL = rs.getInt("Content_id");
                        String cferLevelL = rs.getString("Cfer_level");
                        String difficultyL = rs.getString("Difficulty");
                        String skillL = rs.getString("Skill");
                        String instructionTextL = rs.getString("Instruction_text");
                        String questionTextL = rs.getString("Question_text");
                        String passageL = rs.getString("Passage");
                        String optionsTextL = rs.getString("Options_text");
                        String imageL = rs.getString("Image");
                        String audioL = rs.getString("Audio");
                        String optionAL = rs.getString("Option_a");
                        String optionBL = rs.getString("Option_b");
                        String optionCL = rs.getString("Option_c");
                        String optionDL = rs.getString("Option_d");
                        String correctOptionL = rs.getString("Correct_Option");
                        String examL = rs.getString("Exam");
                        Question question = new Question(contentIdL, cferLevelL,difficultyL, skillL, instructionTextL, questionTextL, passageL, optionsTextL,
                                imageL, audioL, optionAL, optionBL, optionCL, optionDL, correctOptionL, examL);
                        questions.add(question);
                    }
                    loadQuestions(questions);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(skillComboBox.getValue().equals("Writing")){
                List<Question> questions = new ArrayList<>();
                String queryW = "SELECT * FROM c2_writing WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionC2(); PreparedStatement pstmt = conn.prepareStatement(queryW)) {
                    pstmt.setString(1, contentIDLabel.getText());
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdL = rs.getInt("Content_id");
                        String cferLevelL = rs.getString("Cfer_level");
                        String difficultyL = rs.getString("Difficulty");
                        String skillL = rs.getString("Skill");
                        String instructionTextL = rs.getString("Instruction_text");
                        String questionTextL = rs.getString("Question_text");
                        String passageL = rs.getString("Passage");
                        String optionsTextL = rs.getString("Options_text");
                        String imageL = rs.getString("Image");
                        String audioL = rs.getString("Audio");
                        String examW = rs.getString("Exam");
                        Question question = new Question(contentIdL, cferLevelL, difficultyL, skillL, instructionTextL, questionTextL, passageL, optionsTextL,
                                imageL, audioL, examW);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(skillComboBox.getValue().equals("Speaking")){
                List<Question> questions = new ArrayList<>();
                String queryS = "SELECT * FROM c2_speaking WHERE Content_id = ?";
                try (Connection conn = DB.getConnectionC2(); PreparedStatement pstmt = conn.prepareStatement(queryS)) {
                    pstmt.setString(1, contentIDLabel.getText());
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int contentIdL = rs.getInt("Content_id");
                        String cferLevelL = rs.getString("Cfer_level");
                        String difficultyL = rs.getString("Difficulty");
                        String skillL = rs.getString("Skill");
                        String instructionTextL = rs.getString("Instruction_text");
                        String questionTextL = rs.getString("Question_text");
                        String passageL = rs.getString("Passage");
                        String optionsTextL = rs.getString("Options_text");
                        String imageL = rs.getString("Image");
                        String audioL = rs.getString("Audio");
                        String examS = rs.getString("Exam");
                        Question question = new Question(contentIdL, cferLevelL, difficultyL, skillL, instructionTextL, questionTextL, passageL, optionsTextL,
                                imageL, audioL, examS);
                        questions.add(question);
                    }
                    loadQuestions(questions);
                } catch (Exception e) {
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
    private void deleteQuestion(ActionEvent event) {
        Question questionSelected = questionsTableView.getSelectionModel().getSelectedItem();
        if(questionSelected != null){
             deleteQuestionFromTable(questionSelected);
        }
    }
    
    private void deleteQuestionFromTable(Question question){
        String difficultyOpt = difficultyComboBox.getValue();
        String skillOpt = skillComboBox.getValue();
        String contentId = contentIDLabel.getText();
        if(difficultyOpt.equals("Pre A1")){
            if(skillOpt.equals("Language use")){
                String query = "DELETE FROM prea1_language_use WHERE Content_ID = ?";
                try(Connection conn = DB.getConnectionPreA1(); 
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE

                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    
                }
            }else if(skillOpt.equals("Listening comprehension")){
                String query = "DELETE FROM prea1_listening WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionPreA1(); 
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE

                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    
                }
            }else if(skillOpt.equals("Reading comprehension")){
                String query = "DELETE FROM prea1_reading WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionPreA1(); 
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE

                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    
                }
            }else if(skillOpt.equals("Speaking")){
                String query = "DELETE FROM prea1_speaking WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionPreA1();
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE

                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else if(skillOpt.equals("Writing")){
                String query = "DELETE FROM prea1_writing WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionPreA1();
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE

                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        if(difficultyOpt.equals("A1")){
            if(skillOpt.equals("Language use")){
                String query = "DELETE FROM a1_language_use WHERE Content_ID = ?";
                try(Connection conn = DB.getConnectionA1(); 
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE

                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    
                }
            }else if(skillOpt.equals("Listening comprehension")){
                String query = "DELETE FROM a1_listening WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionA1(); 
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE

                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    
                }
            }else if(skillOpt.equals("Reading comprehension")){
                String query = "DELETE FROM a1_reading WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionA1(); 
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE

                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    
                }
            }else if(skillOpt.equals("Speaking")){
                String query = "DELETE FROM a1_speaking WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionA1();
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE

                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else if(skillOpt.equals("Writing")){
                String query = "DELETE FROM a1_writing WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionA1();
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE

                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        if(difficultyOpt.equals("A2")){
            if(skillOpt.equals("Language use")){
                String query = "DELETE FROM a2_language_use WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionA2(); 
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE

                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    
                }
            }else if(skillOpt.equals("Listening comprehension")){
                String query = "DELETE FROM a2_listening WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionA2(); 
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE

                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    
                }
            }else if(skillOpt.equals("Reading comprehension")){
                String query = "DELETE FROM a2_reading WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionA2(); 
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE

                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    
                }
            }else if(skillOpt.equals("Speaking")){
                String query = "DELETE FROM a2_speaking WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionA2();
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE

                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else if(skillOpt.equals("Writing")){
                String query = "DELETE FROM a2_writing WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionA2();
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE

                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        if(difficultyOpt.equals("B1")){
            if(skillOpt.equals("Language use")){
                String query = "DELETE FROM b1_language_use WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionB1(); 
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE

                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    
                }
            }else if(skillOpt.equals("Listening comprehension")){
                String query = "DELETE FROM b1_listening WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionB1(); 
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE

                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    
                }
            }else if(skillOpt.equals("Reading comprehension")){
                String query = "DELETE FROM b1_reading WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionB1(); 
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE

                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    
                }
            }else if(skillOpt.equals("Speaking")){
                String query = "DELETE FROM b1_speaking WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionB1();
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE

                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else if(skillOpt.equals("Writing")){
                String query = "DELETE FROM b1_writing WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionB1();
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE

                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        if(difficultyOpt.equals("B2")){
            if(skillOpt.equals("Language use")){
                String query = "DELETE FROM b2_language_use WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionB2(); 
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE
                   
                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    
                }
            }else if(skillOpt.equals("Listening comprehension")){
                String query = "DELETE FROM b2_listening WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionB2(); 
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE
                    
                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    
                }
            }else if(skillOpt.equals("Reading comprehension")){
                String query = "DELETE FROM b2_reading WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionB2(); 
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE
                    
                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    
                }
            }else if(skillOpt.equals("Speaking")){
                String query = "DELETE FROM b2_speaking WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionB2();
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE

                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else if(skillOpt.equals("Writing")){
                String query = "DELETE FROM b2_writing WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionB2();
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE

                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        if(difficultyOpt.equals("C1")){
            if(skillOpt.equals("Language use")){
                String query = "DELETE FROM c1_language_use WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionC1(); 
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE
                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    
                }
            }else if(skillOpt.equals("Listening comprehension")){
                String query = "DELETE FROM c1_listening WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionC1(); 
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE
                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    
                }
            }else if(skillOpt.equals("Reading comprehension")){
                String query = "DELETE FROM c1_reading WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionC1(); 
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE
                    
                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    
                }
            }else if(skillOpt.equals("Speaking")){
                String query = "DELETE FROM c1_speaking WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionC1();
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE

                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else if(skillOpt.equals("Writing")){
                String query = "DELETE FROM c1_writing WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionC1();
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE

                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        if(difficultyOpt.equals("C2")){
            if(skillOpt.equals("Language use")){
                System.out.println("Entro en language use");
                String query = "DELETE FROM c2_language_use WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionC2(); 
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE
                    System.out.println("Row affected: " + affectedRows);
                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    
                }
            }else if(skillOpt.equals("Listening comprehension")){
                String query = "DELETE FROM c2_listening WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionC2(); 
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE
                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    
                }
            }else if(skillOpt.equals("Reading comprehension")){
                String query = "DELETE FROM c2_reading WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionC2(); 
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE
                    
                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    
                }
            }else if(skillOpt.equals("Speaking")){
                String query = "DELETE FROM c2_speaking WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionC2();
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE

                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else if(skillOpt.equals("Writing")){
                String query = "DELETE FROM c2_writing WHERE Content_id = ?";
                try(Connection conn = DB.getConnectionC2();
                    PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, contentId);
                    int affectedRows = pstmt.executeUpdate();  // Cambia executeQuery() a executeUpdate() para operaciones DELETE

                    if (affectedRows > 0) {
                        String user = DB.getUserName();
                        DB.setUserNameDelete(user);
                        // Eliminación exitosa de la base de datos, ahora eliminar de la tabla JavaFX
                        ObservableList<Question> tableData = questionsTableView.getItems();
                        for (int i = 0; i < tableData.size(); i++) {
                            if (tableData.get(i).getContentId() == Integer.parseInt(contentId)) {
                                tableData.remove(i);
                                mostrarAlerta("Question deleted");
                                break;
                            }
                        }
                    }
                }catch(Exception e){
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

    private void goSearchQuestionGeneral(ActionEvent event) throws IOException {
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

    @FXML
    private void goSearchGenQuestion(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/SearchQuestion/searchQuestionGeneral.fxml"));
        Parent root = cargadorApp.load();
        btnCancel.getScene().setRoot(root);
    }
    
    
}
