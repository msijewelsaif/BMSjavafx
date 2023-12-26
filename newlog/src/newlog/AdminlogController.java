/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package newlog;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Md Saiful Islam
 */
public class AdminlogController implements Initializable {
    
   Connection connect;
    PreparedStatement pst;
    ResultSet rs;  
    
    
    
 @FXML
    private PasswordField a_password;

    @FXML
    private TextField a_username;

    @FXML
    private Button admin_signin;
 @FXML
    private Button back;
    
    @FXML
    void backpage(ActionEvent event) throws IOException{
        Stage currentStage = (Stage) back.getScene().getWindow();
                      currentStage.hide();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
    }
    @FXML
            
            
    void signin(ActionEvent event) throws IOExceptions, IOException {
         String uname = a_username.getText();
        String pass = a_password.getText();
         if (uname.equals("") || pass.equals("")) {
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR MESSAGE");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the blanks");
                alert.showAndWait();

    }
           else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bloodadmin", "root", "");
                pst = con.prepareStatement("select * from adminpanel where a_username=? and a_password =?");
                pst.setString(1, uname);
                pst.setString(2, pass);
                rs = pst.executeQuery();
                if (rs.next()) {
                  //JOptionPane.showMessageDialog(null, "Login success");
                  Alert alert;
                  alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("INFORMATION MESSAGE");
                    alert.setHeaderText(null);
                    alert.setContentText("Login Successful");
                       alert.showAndWait();
                       
                       Stage currentStage = (Stage) admin_signin.getScene().getWindow();
                      currentStage.hide();

                    Parent root = FXMLLoader.load(getClass().getResource("adminhomepage.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.show();
                    
                    
                    
                       
                } else {
                    Alert alert;
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR MESSAGE");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong username or password!");
                       alert.showAndWait();
                 // JOptionPane.showMessageDialog(null, "Login failed");
                    a_username.setText("");
                    a_password.setText("");
                    a_username.requestFocus();
                }
            } catch (ClassNotFoundException | SQLException e) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, e);
            }
        }

   
    }
        
    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

