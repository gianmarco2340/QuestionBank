/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;
import Question.Question;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Session.SessionApp;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author brato
 */
public class DataBaseUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/questionbankapp";
    private static final String URL_PREA1 = "jdbc:mysql://localhost:3306/prea1questions";
    private static final String URL_A1 = "jdbc:mysql://localhost:3306/a1questions";
    private static final String URL_A2 = "jdbc:mysql://localhost:3306/a2questions";
    private static final String URL_B1 = "jdbc:mysql://localhost:3306/b1questions";
    private static final String URL_B2 = "jdbc:mysql://localhost:3306/b2questions";
    private static final String URL_C1 = "jdbc:mysql://localhost:3306/c1questions";
    private static final String URL_C2 = "jdbc:mysql://localhost:3306/c2questions";

    /*For privacy i didn't add the user and password*/
    
    
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    public Connection getConnectionPreA1() throws SQLException{
        return DriverManager.getConnection(URL_PREA1, USER, PASSWORD);
    }
    
    public Connection getConnectionA1() throws SQLException{
        return DriverManager.getConnection(URL_A1, USER, PASSWORD);
    }
    
    public Connection getConnectionA2() throws SQLException{
        return DriverManager.getConnection(URL_A2, USER, PASSWORD);
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
    
    public Connection getConnectionC2() throws SQLException{
        return DriverManager.getConnection(URL_C2, USER, PASSWORD );
    }
    
    public boolean registerQuestionInDataBase(Question question) throws IOException, SQLException {
        String query = "";
        
        //Case A1.1 A1.2
        if (question.getDifficulty().equals("Pre A1.1") || question.getDifficulty().equals("Pre A1.2") || question.getDifficulty().equals("Pre A1.3")){
            switch (question.getSkill()) {
                case "Language use":
                    query = "INSERT INTO prea1_language_use (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Options_text, Image, Option_a, Option_b, Option_c, Option_d, Correct_Option, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionPreA1(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getOptionsText());
                        pstmt.setString(8, question.getImage());
                        pstmt.setString(9, question.getOptionA());
                        pstmt.setString(10, question.getOptionB());
                        pstmt.setString(11, question.getOptionC());
                        pstmt.setString(12, question.getOptionD());
                        pstmt.setString(13, question.getCorrectOption());
                        pstmt.setString(14, question.getExam());

                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                case "Reading comprehension":
                    query = "INSERT INTO prea1_reading (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Passage, Options_text, Image, Option_a, Option_b, Option_c, Option_d, Correct_Option, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
                    try (Connection conn = getConnectionPreA1(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getPassage());
                        pstmt.setString(8, question.getOptionsText());
                        pstmt.setString(9, question.getImage());
                        pstmt.setString(10, question.getOptionA());
                        pstmt.setString(11, question.getOptionB());
                        pstmt.setString(12, question.getOptionC());
                        pstmt.setString(13, question.getOptionD());
                        pstmt.setString(14, question.getCorrectOption());
                        pstmt.setString(15, question.getExam());

                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                case "Listening comprehension":
                    query = "INSERT INTO prea1_listening (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Passage, Options_text, Image, Audio, Option_a, Option_b, Option_c, Option_d, Correct_Option, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionPreA1(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getPassage());
                        pstmt.setString(8, question.getOptionsText());
                        pstmt.setString(9, question.getImage());
                        pstmt.setString(10, question.getAudio());
                        pstmt.setString(11, question.getOptionA());
                        pstmt.setString(12, question.getOptionB());
                        pstmt.setString(13, question.getOptionC());
                        pstmt.setString(14, question.getOptionD());
                        pstmt.setString(15, question.getCorrectOption());
                        pstmt.setString(16, question.getExam());

                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                case "Speaking":
                    query = "INSERT into prea1_speaking (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Passage, Options_text, Image, Audio, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionPreA1(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getPassage());
                        pstmt.setString(8, question.getOptionsText());
                        pstmt.setString(9, question.getImage());
                        pstmt.setString(10, question.getAudio());
                        pstmt.setString(11, question.getExam());
                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                case "Writing":
                    query = "INSERT into prea1_writing (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Passage, Options_text, Image, Audio, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionPreA1(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getPassage());
                        pstmt.setString(8, question.getOptionsText());
                        pstmt.setString(9, question.getImage());
                        pstmt.setString(10, question.getAudio());
                        pstmt.setString(11, question.getExam());
                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                default:
                    return false; // skill desconocido
            }
        }
        
        //Case A1.1 A1.2
        if (question.getDifficulty().equals("A1.1") || question.getDifficulty().equals("A1.2")) {
            switch (question.getSkill()) {
                case "Language use":
                    query = "INSERT INTO a1_language_use (Content_ID, Cfer_level, Difficulty, Skill, Instruction_text, Question_Text, Options_Text, Image, Option_a, Option_b, Option_c, Option_d, Correct_Option, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionA1(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getOptionsText());
                        pstmt.setString(8, question.getImage());
                        pstmt.setString(9, question.getOptionA());
                        pstmt.setString(10, question.getOptionB());
                        pstmt.setString(11, question.getOptionC());
                        pstmt.setString(12, question.getOptionD());
                        pstmt.setString(13, question.getCorrectOption());
                        pstmt.setString(14, question.getExam());

                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                case "Reading comprehension":
                    query = "INSERT INTO a1_reading (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Passage, Options_text, Image, Option_a, Option_b, Option_c, Option_d, Correct_Option, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionA1(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getPassage());
                        pstmt.setString(8, question.getOptionsText());
                        pstmt.setString(9, question.getImage());
                        pstmt.setString(10, question.getOptionA());
                        pstmt.setString(11, question.getOptionB());
                        pstmt.setString(12, question.getOptionC());
                        pstmt.setString(13, question.getOptionD());
                        pstmt.setString(14, question.getCorrectOption());
                        pstmt.setString(15, question.getExam());

                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                case "Listening comprehension":
                    query = "INSERT INTO a1_listening (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Passage, Options_text, Image, Audio, Option_a, Option_b, Option_c, Option_d, Correct_Option, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionA1(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getPassage());
                        pstmt.setString(8, question.getOptionsText());
                        pstmt.setString(9, question.getImage());
                        pstmt.setString(10, question.getAudio());
                        pstmt.setString(11, question.getOptionA());
                        pstmt.setString(12, question.getOptionB());
                        pstmt.setString(13, question.getOptionC());
                        pstmt.setString(14, question.getOptionD());
                        pstmt.setString(15, question.getCorrectOption());
                        pstmt.setString(16, question.getExam());

                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                case "Speaking":
                    query = "INSERT into a1_speaking (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Passage, Options_text, Image, Audio, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionA1(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getPassage());
                        pstmt.setString(8, question.getOptionsText());
                        pstmt.setString(9, question.getImage());
                        pstmt.setString(10, question.getAudio());
                        pstmt.setString(11, question.getExam());
                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                case "Writing":
                    query = "INSERT into a1_writing (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Passage, Options_text, Image, Audio, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionA1(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getPassage());
                        pstmt.setString(8, question.getOptionsText());
                        pstmt.setString(9, question.getImage());
                        pstmt.setString(10, question.getAudio());
                        pstmt.setString(11, question.getExam());
                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                default:
                    return false; // skill desconocido
            }
        }
        
        //Case A2.1 A2.2
        if (question.getDifficulty().equals("A2.1") || question.getDifficulty().equals("A2.2")) {
            switch (question.getSkill()) {
                case "Language use":
                    query = "INSERT INTO a2_language_use (Content_ID, Cfer_level, Difficulty, Skill, Instruction_text, Question_Text, Options_Text, Image, Option_a, Option_b, Option_c, Option_d, Correct_Option, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionA2(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getOptionsText());
                        pstmt.setString(8, question.getImage());
                        pstmt.setString(9, question.getOptionA());
                        pstmt.setString(10, question.getOptionB());
                        pstmt.setString(11, question.getOptionC());
                        pstmt.setString(12, question.getOptionD());
                        pstmt.setString(13, question.getCorrectOption());
                        pstmt.setString(14, question.getExam());

                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        return false;
                    }
                
                //A2 Reading 
                case "Reading comprehension":
                    query = "INSERT INTO a2_reading (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Passage, Options_text, Image, Option_a, Option_b, Option_c, Option_d, Correct_Option, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionA2(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getPassage());
                        pstmt.setString(8, question.getOptionsText());
                        pstmt.setString(9, question.getImage());
                        pstmt.setString(10, question.getOptionA());
                        pstmt.setString(11, question.getOptionB());
                        pstmt.setString(12, question.getOptionC());
                        pstmt.setString(13, question.getOptionD());
                        pstmt.setString(14, question.getCorrectOption());
                        pstmt.setString(15, question.getExam());

                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                    
                //A2 Listening has a double for the set field
                case "Listening comprehension":
                    query = "INSERT INTO a2_listening (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Passage, Options_text, Image, Audio, Option_a, Option_b, Option_c, Option_d, Correct_Option, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionA2(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getPassage());
                        pstmt.setString(8, question.getOptionsText());
                        pstmt.setString(9, question.getImage());
                        pstmt.setString(10, question.getAudio());
                        pstmt.setString(11, question.getOptionA());
                        pstmt.setString(12, question.getOptionB());
                        pstmt.setString(13, question.getOptionC());
                        pstmt.setString(14, question.getOptionD());
                        pstmt.setString(15, question.getCorrectOption());
                        pstmt.setString(16, question.getExam());

                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                case "Speaking":
                    query = "INSERT into a2_speaking (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Passage, Options_text, Image, Audio, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionA2(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getPassage());
                        pstmt.setString(8, question.getOptionsText());
                        pstmt.setString(9, question.getImage());
                        pstmt.setString(10, question.getAudio());
                        pstmt.setString(11, question.getExam());
                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                case "Writing":
                    query = "INSERT into a2_writing (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Passage, Options_text, Image, Audio, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionA2(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getPassage());
                        pstmt.setString(8, question.getOptionsText());
                        pstmt.setString(9, question.getImage());
                        pstmt.setString(10, question.getAudio());
                        pstmt.setString(11, question.getExam());
                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                default:
                    return false; // skill desconocido
            }
        }
        
        //Case B1.1 B1.2
        if (question.getDifficulty().equalsIgnoreCase("B1.1") || question.getDifficulty().equalsIgnoreCase("B1.2")) {
            switch (question.getSkill()){
                case "Language use":
                    query = "INSERT INTO b1_language_use (Content_ID, Cfer_level, Difficulty, Skill, Instruction_text, Question_Text, Options_Text, Image, Option_a, Option_b, Option_c, Option_d, Correct_Option, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionB1(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getOptionsText());
                        pstmt.setString(8, question.getImage());
                        pstmt.setString(9, question.getOptionA());
                        pstmt.setString(10, question.getOptionB());
                        pstmt.setString(11, question.getOptionC());
                        pstmt.setString(12, question.getOptionD());
                        pstmt.setString(13, question.getCorrectOption());
                        pstmt.setString(14, question.getExam());

                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                case "Reading comprehension":
                    query = "INSERT INTO b1_reading (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Passage, Options_text, Image, Option_a, Option_b, Option_c, Option_d, Correct_Option, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionB1(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getPassage());
                        pstmt.setString(8, question.getOptionsText());
                        pstmt.setString(9, question.getImage());
                        pstmt.setString(10, question.getOptionA());
                        pstmt.setString(11, question.getOptionB());
                        pstmt.setString(12, question.getOptionC());
                        pstmt.setString(13, question.getOptionD());
                        pstmt.setString(14, question.getCorrectOption());
                        pstmt.setString(15, question.getExam());

                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                case "Listening comprehension":
                    query = "INSERT INTO b1_listening (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Passage, Options_text, Image, Audio, Option_a, Option_b, Option_c, Option_d, Correct_Option, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionB1(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getPassage());
                        pstmt.setString(8, question.getOptionsText());
                        pstmt.setString(9, question.getImage());
                        pstmt.setString(10, question.getAudio());
                        pstmt.setString(11, question.getOptionA());
                        pstmt.setString(12, question.getOptionB());
                        pstmt.setString(13, question.getOptionC());
                        pstmt.setString(14, question.getOptionD());
                        pstmt.setString(15, question.getCorrectOption());
                        pstmt.setString(16, question.getExam());

                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                case "Speaking":
                    query = "INSERT into b1_speaking (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Passage, Options_text, Image, Audio, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionB1(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getPassage());
                        pstmt.setString(8, question.getOptionsText());
                        pstmt.setString(9, question.getImage());
                        pstmt.setString(10, question.getAudio());
                        pstmt.setString(11, question.getExam());
                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                case "Writing":
                    query = "INSERT into b1_writing (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Passage, Options_text, Image, Audio, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionB1(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getPassage());
                        pstmt.setString(8, question.getOptionsText());
                        pstmt.setString(9, question.getImage());
                        pstmt.setString(10, question.getAudio());
                        pstmt.setString(11, question.getExam());
                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                default:
                    return false; // skill desconocido
            }
        }
        
        //Case B2.1 B2.2
        if (question.getDifficulty().equalsIgnoreCase("B2.1") || question.getDifficulty().equalsIgnoreCase("B2.2")) {
            switch (question.getSkill()){
                case "Language use":
                    query = "INSERT INTO b2_language_use (Content_ID, Cfer_level, Difficulty, Skill, Instruction_text, Question_Text, Options_Text, Image, Option_a, Option_b, Option_c, Option_d, Correct_Option, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionB2(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getOptionsText());
                        pstmt.setString(8, question.getImage());
                        pstmt.setString(9, question.getOptionA());
                        pstmt.setString(10, question.getOptionB());
                        pstmt.setString(11, question.getOptionC());
                        pstmt.setString(12, question.getOptionD());
                        pstmt.setString(13, question.getCorrectOption());
                        pstmt.setString(14, question.getExam());

                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                case "Reading comprehension":
                    query = "INSERT INTO b2_reading (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Passage, Options_text, Image, Option_a, Option_b, Option_c, Option_d, Correct_Option, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionB2(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getPassage());
                        pstmt.setString(8, question.getOptionsText());
                        pstmt.setString(9, question.getImage());
                        pstmt.setString(10, question.getOptionA());
                        pstmt.setString(11, question.getOptionB());
                        pstmt.setString(12, question.getOptionC());
                        pstmt.setString(13, question.getOptionD());
                        pstmt.setString(14, question.getCorrectOption());
                        pstmt.setString(15, question.getExam());

                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                case "Listening comprehension":
                    query = "INSERT INTO b2_listening (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Passage, Options_text, Image, Audio, Option_a, Option_b, Option_c, Option_d, Correct_Option, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionB2(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getPassage());
                        pstmt.setString(8, question.getOptionsText());
                        pstmt.setString(9, question.getImage());
                        pstmt.setString(10, question.getAudio());
                        pstmt.setString(11, question.getOptionA());
                        pstmt.setString(12, question.getOptionB());
                        pstmt.setString(13, question.getOptionC());
                        pstmt.setString(14, question.getOptionD());
                        pstmt.setString(15, question.getCorrectOption());
                        pstmt.setString(16, question.getExam());

                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                case "Speaking":
                    query = "INSERT into b2_speaking (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Passage, Options_text, Image, Audio, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionB2(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getPassage());
                        pstmt.setString(8, question.getOptionsText());
                        pstmt.setString(9, question.getImage());
                        pstmt.setString(10, question.getAudio());
                        pstmt.setString(11, question.getExam());
                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                case "Writing":
                    query = "INSERT into b2_writing (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Passage, Options_text, Image, Audio, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionB2(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getPassage());
                        pstmt.setString(8, question.getOptionsText());
                        pstmt.setString(9, question.getImage());
                        pstmt.setString(10, question.getAudio());
                        pstmt.setString(11, question.getExam());
                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                default:
                    return false; // skill desconocido
            }
        }
        
        //Case C1.1 C1.2
        if (question.getDifficulty().equalsIgnoreCase("C1.1") || question.getDifficulty().equalsIgnoreCase("C1.2")) {
            switch (question.getSkill()){
                case "Language use":
                    query = "INSERT INTO c1_language_use (Content_ID, Cfer_level, Difficulty, Skill, Instruction_text, Question_Text, Options_Text, Image, Option_a, Option_b, Option_c, Option_d, Correct_Option, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionC1(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getOptionsText());
                        pstmt.setString(8, question.getImage());
                        pstmt.setString(9, question.getOptionA());
                        pstmt.setString(10, question.getOptionB());
                        pstmt.setString(11, question.getOptionC());
                        pstmt.setString(12, question.getOptionD());
                        pstmt.setString(13, question.getCorrectOption());
                        pstmt.setString(14, question.getExam());

                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                case "Reading comprehension":
                    query = "INSERT INTO c1_reading (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Passage, Options_text, Image, Option_a, Option_b, Option_c, Option_d, Correct_Option, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionC1(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getPassage());
                        pstmt.setString(8, question.getOptionsText());
                        pstmt.setString(9, question.getImage());
                        pstmt.setString(10, question.getOptionA());
                        pstmt.setString(11, question.getOptionB());
                        pstmt.setString(12, question.getOptionC());
                        pstmt.setString(13, question.getOptionD());
                        pstmt.setString(14, question.getCorrectOption());
                        pstmt.setString(15, question.getExam());

                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                case "Listening comprehension":
                    query = "INSERT INTO c1_listening (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Passage, Options_text, Image, Audio, Option_a, Option_b, Option_c, Option_d, Correct_Option, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionC1(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getPassage());
                        pstmt.setString(8, question.getOptionsText());
                        pstmt.setString(9, question.getImage());
                        pstmt.setString(10, question.getAudio());
                        pstmt.setString(11, question.getOptionA());
                        pstmt.setString(12, question.getOptionB());
                        pstmt.setString(13, question.getOptionC());
                        pstmt.setString(14, question.getOptionD());
                        pstmt.setString(15, question.getCorrectOption());
                        pstmt.setString(16, question.getExam());

                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                case "Speaking":
                    query = "INSERT into c1_speaking (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Passage, Options_text, Image, Audio, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionC1(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getPassage());
                        pstmt.setString(8, question.getOptionsText());
                        pstmt.setString(9, question.getImage());
                        pstmt.setString(10, question.getAudio());
                        pstmt.setString(11, question.getExam());
                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                case "Writing":
                    query = "INSERT into c1_writing (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Passage, Options_text, Image, Audio, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionC1(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getPassage());
                        pstmt.setString(8, question.getOptionsText());
                        pstmt.setString(9, question.getImage());
                        pstmt.setString(10, question.getAudio());
                        pstmt.setString(11, question.getExam());
                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                default:
                    return false; // skill desconocido
            }
        }
        if (question.getDifficulty().equalsIgnoreCase("C2.1") || question.getDifficulty().equalsIgnoreCase("C2.2")) {
            switch (question.getSkill()){
                case "Language use":
                    query = "INSERT INTO c2_language_use (Content_ID, Cfer_level, Difficulty, Skill, Instruction_text, Question_Text, Options_Text, Image, Option_a, Option_b, Option_c, Option_d, Correct_Option, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionC2(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getOptionsText());
                        pstmt.setString(8, question.getImage());
                        pstmt.setString(9, question.getOptionA());
                        pstmt.setString(10, question.getOptionB());
                        pstmt.setString(11, question.getOptionC());
                        pstmt.setString(12, question.getOptionD());
                        pstmt.setString(13, question.getCorrectOption());
                        pstmt.setString(14, question.getExam());

                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                case "Reading comprehension":
                    query = "INSERT INTO c2_reading (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Passage, Options_text, Image, Option_a, Option_b, Option_c, Option_d, Correct_Option, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionC2(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getPassage());
                        pstmt.setString(8, question.getOptionsText());
                        pstmt.setString(9, question.getImage());
                        pstmt.setString(10, question.getOptionA());
                        pstmt.setString(11, question.getOptionB());
                        pstmt.setString(12, question.getOptionC());
                        pstmt.setString(13, question.getOptionD());
                        pstmt.setString(14, question.getCorrectOption());
                        pstmt.setString(15, question.getExam());

                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                case "Listening comprehension":
                    query = "INSERT INTO c2_listening (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Passage, Options_text, Image, Audio, Option_a, Option_b, Option_c, Option_d, Correct_Option, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionC2(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getPassage());
                        pstmt.setString(8, question.getOptionsText());
                        pstmt.setString(9, question.getImage());
                        pstmt.setString(10, question.getAudio());
                        pstmt.setString(11, question.getOptionA());
                        pstmt.setString(12, question.getOptionB());
                        pstmt.setString(13, question.getOptionC());
                        pstmt.setString(14, question.getOptionD());
                        pstmt.setString(15, question.getCorrectOption());
                        pstmt.setString(16, question.getExam());

                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                case "Speaking":
                    query = "INSERT into c2_speaking (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Passage, Options_text, Image, Audio, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionC2(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getPassage());
                        pstmt.setString(8, question.getOptionsText());
                        pstmt.setString(9, question.getImage());
                        pstmt.setString(10, question.getAudio());
                        pstmt.setString(11, question.getExam());
                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                case "Writing":
                    query = "INSERT into c2_writing (Content_id, Cfer_level, Difficulty, Skill, Instruction_text, Question_text, Passage, Options_text, Image, Audio, Exam) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = getConnectionC2(); PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, question.getContentId());
                        pstmt.setString(2, question.getCferlevel());
                        pstmt.setString(3, question.getDifficulty());
                        pstmt.setString(4, question.getSkill());
                        pstmt.setString(5, question.getInstructionText());
                        pstmt.setString(6, question.getQuestionText());
                        pstmt.setString(7, question.getPassage());
                        pstmt.setString(8, question.getOptionsText());
                        pstmt.setString(9, question.getImage());
                        pstmt.setString(10, question.getAudio());
                        pstmt.setString(11, question.getExam());
                        pstmt.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                default:
                    return false; // skill desconocido
            }
        }
        
        return false;
    }

    public boolean logInUser(String username, String password){
        String query = "SELECT * FROM users WHERE username = ? AND passwordu = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            return rs.next(); // Devuelve true si encuentra al menos una fila, es decir, el usuario y la contraseña coinciden.
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    } 
    
    
    public String getUserName(){
        String user = SessionApp.getInstance().getLoggedUser();
        System.out.println("User from session: " + user);
        return user;
    }
    
    public String getName(String user){
        String name = "";
        String query = "SELECT nameU FROM users WHERE username = ?";
        try(Connection con = getConnection(); 
            PreparedStatement ps = con.prepareStatement(query)){
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                name = rs.getString("nameU");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return name;
    }
    
    public String getPassword(String user) throws SQLException{
        String password = "";
        String query = "SELECT passwordU FROM users WHERE username = ?";
        try(Connection con = getConnection(); 
                PreparedStatement ps = con.prepareStatement(query)){
                ps.setString(1, user);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    password = rs.getString("passwordU");
                }
        }catch(Exception e){
            e.printStackTrace();
        }
        return password;
    }
    
    public String getLastName(String user){
        String name = "";
        String query = "SELECT lastName FROM users WHERE username = ?";
        try(Connection con = getConnection(); 
            PreparedStatement ps = con.prepareStatement(query)){
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                name = rs.getString("lastName");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return name;
    }
    
    public String getEmail(String user){
        String name = "";
        String query = "SELECT email FROM users WHERE username = ?";
        try(Connection con = getConnection(); 
            PreparedStatement ps = con.prepareStatement(query)){
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                name = rs.getString("email");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return name;
    }
    
    
    
    
    
    public String getUserRole(String user){
        String userRole = "";
        String query = "SELECT userRole FROM users WHERE username = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userRole = rs.getString("userRole");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userRole;
    }
    
    public boolean setUserNameActionDownload(String user){
        String query = "INSERT INTO actions (User, Download, Addition, Modified, MassUpload, LogIn, LogOut, SearchInd, SearchGeneral, `Delete`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(query)){
            pstmt.setString(1, user);
        
            // Obtener la fecha y hora actual
            LocalDateTime now = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(now);

            // Insertar la fecha y hora en la segunda columna
            pstmt.setTimestamp(2, timestamp);

            // Configurar las otras columnas como NULL o valores por defecto
            pstmt.setNull(3, java.sql.Types.TIMESTAMP);
            pstmt.setNull(4, java.sql.Types.TIMESTAMP);
            pstmt.setNull(5, java.sql.Types.TIMESTAMP);
            pstmt.setNull(6, java.sql.Types.TIMESTAMP);
            pstmt.setNull(7, java.sql.Types.TIMESTAMP);
            pstmt.setNull(8, java.sql.Types.TIMESTAMP);
            pstmt.setNull(9, java.sql.Types.TIMESTAMP);
            pstmt.setNull(10, java.sql.Types.TIMESTAMP);
            
            pstmt.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean setUserNameActionAdditionQuestion(String user){
        String query = "INSERT INTO actions (User, Download, Addition, Modified, MassUpload, LogIn, LogOut, SearchInd, SearchGeneral, `Delete`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(query)){
            pstmt.setString(1, user);
            pstmt.setNull(2, java.sql.Types.TIMESTAMP);
            LocalDateTime now = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(now);
            pstmt.setTimestamp(3, timestamp);
            pstmt.setNull(4, java.sql.Types.TIMESTAMP);
            pstmt.setNull(5, java.sql.Types.TIMESTAMP);
            pstmt.setNull(6, java.sql.Types.TIMESTAMP);
            pstmt.setNull(7, java.sql.Types.TIMESTAMP);
            pstmt.setNull(8, java.sql.Types.TIMESTAMP);
            pstmt.setNull(9, java.sql.Types.TIMESTAMP);
            pstmt.setNull(10, java.sql.Types.TIMESTAMP);
            pstmt.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean setUserNameActionEditQuestion(String user) throws SQLException{
        String query = "INSERT INTO actions (User, Download, Addition, Modified, MassUpload, LogIn, LogOut, SearchInd, SearchGeneral, `Delete`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(query)){
            pstmt.setString(1, user); //User
            pstmt.setNull(2, java.sql.Types.TIMESTAMP); //Download
            pstmt.setNull(3, java.sql.Types.TIMESTAMP); //Addition 
            LocalDateTime now = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(now);
            pstmt.setTimestamp(4, timestamp); //Modified
            pstmt.setNull(5, java.sql.Types.TIMESTAMP); //MassUpload           
            pstmt.setNull(6, java.sql.Types.TIMESTAMP); //LogIn
            pstmt.setNull(7, java.sql.Types.TIMESTAMP); //LogOut
            pstmt.setNull(8, java.sql.Types.TIMESTAMP); //LogOut
            pstmt.setNull(9, java.sql.Types.TIMESTAMP); //LogOut
            pstmt.setNull(10, java.sql.Types.TIMESTAMP); //LogOut
            pstmt.executeUpdate();
            return true;
            

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    public boolean setUserNameActionAdditionMassUpload(String user){
        String query = "INSERT INTO actions (USER, Download, Addition, Modified, MassUpload, LogIn, LogOut, SearchInd, SearchGeneral, `Delete`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(query)){
            pstmt.setString(1, user);
            pstmt.setNull(2, java.sql.Types.TIMESTAMP);
            pstmt.setNull(3, java.sql.Types.TIMESTAMP);
            pstmt.setNull(4, java.sql.Types.TIMESTAMP);
            LocalDateTime now = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(now);
            pstmt.setTimestamp(5, timestamp);
            pstmt.setNull(6, java.sql.Types.TIMESTAMP); //LogIn
            pstmt.setNull(7, java.sql.Types.TIMESTAMP); //LogOut
            pstmt.setNull(8, java.sql.Types.TIMESTAMP); //LogOut
            pstmt.setNull(9, java.sql.Types.TIMESTAMP); //LogOut
            pstmt.setNull(10, java.sql.Types.TIMESTAMP); //LogOut
            pstmt.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean setUserNameLogIn(String user){
        String query = "INSERT INTO actions (USER, Download, Addition, Modified, MassUpload, LogIn, LogOut, SearchInd, SearchGeneral, `Delete`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(query)){
            pstmt.setString(1, user);
            pstmt.setNull(2, java.sql.Types.TIMESTAMP);
            pstmt.setNull(3, java.sql.Types.TIMESTAMP);
            pstmt.setNull(4, java.sql.Types.TIMESTAMP);
            pstmt.setNull(5, java.sql.Types.TIMESTAMP);
            LocalDateTime now = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(now);
            pstmt.setTimestamp(6, timestamp); //LogIn
            pstmt.setNull(7, java.sql.Types.TIMESTAMP); //LogOut
            pstmt.setNull(8, java.sql.Types.TIMESTAMP); //LogOut
            pstmt.setNull(9, java.sql.Types.TIMESTAMP); //LogOut
            pstmt.setNull(10, java.sql.Types.TIMESTAMP); //LogOut
            pstmt.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean setUserNameLogOut(String user){
        String query = "INSERT INTO actions (USER, Download, Addition, Modified, MassUpload, LogIn, LogOut, SearchInd, SearchGeneral, `Delete`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(query)){
            pstmt.setString(1, user);
            pstmt.setNull(2, java.sql.Types.TIMESTAMP);
            pstmt.setNull(3, java.sql.Types.TIMESTAMP);
            pstmt.setNull(4, java.sql.Types.TIMESTAMP);
            pstmt.setNull(5, java.sql.Types.TIMESTAMP);
            pstmt.setNull(6, java.sql.Types.TIMESTAMP); //LogIn
            
            LocalDateTime now = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(now);
            pstmt.setTimestamp(7, timestamp); //LogOut
            pstmt.setNull(8, java.sql.Types.TIMESTAMP); //Search Ind
            pstmt.setNull(9, java.sql.Types.TIMESTAMP); //Search General
            pstmt.setNull(10, java.sql.Types.TIMESTAMP); //Delete 
            pstmt.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean setUserNameSearchInd(String user){
        String query = "INSERT INTO actions (USER, Download, Addition, Modified, MassUpload, LogIn, LogOut, SearchInd, SearchGeneral, `Delete`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(query)){
            pstmt.setString(1, user); //User
            pstmt.setNull(2, java.sql.Types.TIMESTAMP); //Download
            pstmt.setNull(3, java.sql.Types.TIMESTAMP); //Addition
            pstmt.setNull(4, java.sql.Types.TIMESTAMP); //Modified
            pstmt.setNull(5, java.sql.Types.TIMESTAMP); //MassUpload
            pstmt.setNull(6, java.sql.Types.TIMESTAMP); //LogIn
            pstmt.setNull(7, java.sql.Types.TIMESTAMP); //LogOut
            LocalDateTime now = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(now);
            pstmt.setTimestamp(8, timestamp); //Search Ind
            pstmt.setNull(9, java.sql.Types.TIMESTAMP); //Search General
            pstmt.setNull(10, java.sql.Types.TIMESTAMP); //Delete 
            pstmt.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean setUserNameSearchGen(String user){
        String query = "INSERT INTO actions (USER, Download, Addition, Modified, MassUpload, LogIn, LogOut, SearchInd, SearchGeneral, `Delete`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(query)){
            pstmt.setString(1, user); //User
            pstmt.setNull(2, java.sql.Types.TIMESTAMP); //Download
            pstmt.setNull(3, java.sql.Types.TIMESTAMP); //Addition
            pstmt.setNull(4, java.sql.Types.TIMESTAMP); //Modified
            pstmt.setNull(5, java.sql.Types.TIMESTAMP); //MassUpload
            pstmt.setNull(6, java.sql.Types.TIMESTAMP); //LogIn
            pstmt.setNull(7, java.sql.Types.TIMESTAMP); //LogOut
            pstmt.setNull(8, java.sql.Types.TIMESTAMP); //Search Ind
            LocalDateTime now = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(now);
            pstmt.setTimestamp(9, timestamp); //Search General
            pstmt.setNull(10, java.sql.Types.TIMESTAMP); //Delete 
            pstmt.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean setUserNameDelete(String user){
        String query = "INSERT INTO actions (USER, Download, Addition, Modified, MassUpload, LogIn, LogOut, SearchInd, SearchGeneral, `Delete`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(query)){
            pstmt.setString(1, user); //User
            pstmt.setNull(2, java.sql.Types.TIMESTAMP); //Download
            pstmt.setNull(3, java.sql.Types.TIMESTAMP); //Addition
            pstmt.setNull(4, java.sql.Types.TIMESTAMP); //Modified
            pstmt.setNull(5, java.sql.Types.TIMESTAMP); //MassUpload
            pstmt.setNull(6, java.sql.Types.TIMESTAMP); //LogIn
            pstmt.setNull(7, java.sql.Types.TIMESTAMP); //LogOut
            pstmt.setNull(8, java.sql.Types.TIMESTAMP); //Search Ind
            pstmt.setNull(9, java.sql.Types.TIMESTAMP); //Search General
            LocalDateTime now = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(now);
            pstmt.setTimestamp(10, timestamp); //Delete 
            pstmt.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    
    
    
    
}
