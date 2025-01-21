package logsDB;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import Actions.actions;
import DataBase.DataBaseUtil;
import Question.Question;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author brato
 */
public class LogsDBController implements Initializable {

    private Button btnAddQuestion;
    @FXML
    private TableView<actions> actionsTableView;
    @FXML
    private TableColumn<actions, String> user;
    @FXML
    private TableColumn<actions, String> download;
    @FXML
    private TableColumn<actions, String> addition;
    @FXML
    private TableColumn<actions, String> modified;
    @FXML
    private TableColumn<actions, String> massUpload;
    @FXML
    private TableColumn<actions, String> logIn;
    @FXML
    private TableColumn<actions, String> logOut;
    @FXML
    private TableColumn<actions, String> searchInd;
    @FXML
    private TableColumn<actions, String> searchGeneral;
    @FXML
    private TableColumn<actions, String> delete;
    @FXML
    private Button btnCancel;
    
    private DataBaseUtil DB = new DataBaseUtil();

    
    private static final String URL = "jdbc:mysql://localhost:3306/questionbankapp";
    
    private static final String USER = "root";  
    private static final String PASSWORD = "Habrat6714%";
    @FXML
    private Text userIDLogs;
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
    private Button btnLogOut11;
    @FXML
    private Button btnShow;
    @FXML
    private Button btnLog;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String username = DB.getUserName();
        userIDLogs.setText(username);
        user.setCellValueFactory(new PropertyValueFactory<>("user"));
        download.setCellValueFactory(new PropertyValueFactory<>("download"));
        addition.setCellValueFactory(new PropertyValueFactory<>("addition"));
        modified.setCellValueFactory(new PropertyValueFactory<>("modified"));
        massUpload.setCellValueFactory(new PropertyValueFactory<>("massUpload"));
        logIn.setCellValueFactory(new PropertyValueFactory<>("logIn"));
        logOut.setCellValueFactory(new PropertyValueFactory<>("logOut"));
        searchInd.setCellValueFactory(new PropertyValueFactory<>("searchInd"));
        searchGeneral.setCellValueFactory(new PropertyValueFactory<>("searchGeneral"));
        delete.setCellValueFactory(new PropertyValueFactory<>("delete"));
        
    }    
    
    public void loadActions(List<actions> action) {
        ObservableList<actions> data = FXCollections.observableArrayList(action);
        actionsTableView.setItems(data);
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
        Stage stage = (Stage) btnAddQuestion1.getScene().getWindow();
        stage.close();
        Platform.exit();
        System.exit(0);
    }


    @FXML
    private void cancel(ActionEvent event) throws IOException {
        FXMLLoader cargadorApp = new FXMLLoader(getClass().getResource("/InternApplication/InternApplication.fxml"));
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
    

    @FXML
    private void showLogs(ActionEvent event) {
        String user = DB.getUserName();
        System.out.println("User: " + user);
        List<actions> actions = new ArrayList<>();
        String query = "SELECT * FROM actions WHERE User = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            System.out.println("conn: " + conn.toString());
            pstmt.setString(1, user);
            ResultSet rs = pstmt.executeQuery();
            int rowCount = 0;
            System.out.println("Result: " + rs.toString());
            while (rs.next()) {
                rowCount++;
                String userDB = rs.getString("User");
                String downloadDB = rs.getString("Download");
                String additionDB = rs.getString("Addition");
                String modifiedDB = rs.getString("Modified");
                String massUploadDB = rs.getString("MassUpload");
                String logInDB = rs.getString("LogIn");
                String logOutDB = rs.getString("LogOut");
                String searchIndDB = rs.getString("SearchInd");
                String searchGenDB = rs.getString("SearchGeneral");
                String deleteDB = rs.getString("Delete");
                actions actionDB = new actions(userDB, downloadDB, additionDB, modifiedDB, massUploadDB, logInDB, logOutDB, searchIndDB, searchGenDB, deleteDB);
                actions.add(actionDB);
                System.out.println("Action: " + actionDB);
            }
            System.out.println("Number of rows retrieved: " + rowCount);

            loadActions(actions);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
}
