/*
package newlog;
import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import static oracle.net.aso.C00.x;

public class FXMLDocumentController implements Initializable {
        @FXML
    private Button btn_login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    
    
    
    Connection connect;
    PreparedStatement pst;
    ResultSet rs;
    
    
      @FXML
            
   void login(ActionEvent event) {
    String uname= username.getText();    
    String pass = password.getText();
    if(uname.equals("")&& pass.equals("")){
        JoptionPane.showMessageDialog(null,"username or pass is blank");
    }
    else{
        try{
             class.forName("com.mysql.jdbc.Driver");
            
                con = DriverManager.getConnection("jdbc:mysql://localhost/bloodadmin","root","");
             
                pst=con.prepareStatement("select * from bloodadmin where username=? and password =?");
                pst.setString(1,username);
                pst.setString(2, password);
                rs=pst.executeQuery();
                if(rs.next()){
                     JoptionPane.showMessageDialog(null,"login success");
                }
                else{
                     JoptionPane.showMessageDialog(null,"log inb failed");
                     username.setText("");
                     password.setText("");
                     username.requestFocus();
                }
            } 
        
        
        catch(ClassNotFoundException e){
            //handler 
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, x);
            
        }
        catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
             
       
        
        
    }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
  
    
    
    
}
*/

package newlog;

import java.io.IOException;
import java.sql.*;
import java.net.URL;
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
import javax.swing.JOptionPane;

public class FXMLDocumentController implements Initializable {
   
    
   
     @FXML
    private Button btn_admin;
    @FXML
    private Button btn_login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    Connection connect;
    PreparedStatement pst;
    ResultSet rs;
    @FXML
    private Button exit;
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    
    
    private Button btn_signup;
      @FXML
    void close(ActionEvent event) {
        Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Platform Close");
                alert.setHeaderText(null);
                alert.setContentText("Never hesitate to donate blood");
                alert.showAndWait();
        Stage currentStage = (Stage) exit.getScene().getWindow();
        currentStage.close();

    }
    
    //sigup
     @FXML
    private void signup(ActionEvent event) {
        try{
            Stage currentStage = (Stage) btn_signup.getScene().getWindow();
                      currentStage.hide();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signup1.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
        }
        catch(Exception e){
            System.out.println("Page cant load");
            
        }
        
    }
    
    @FXML
    void admin_page(ActionEvent event) {
         try{
            Stage currentStage = (Stage) btn_admin.getScene().getWindow();
                      currentStage.hide();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminlog.fxml"));
            
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
        }
        catch(Exception e){
            System.out.println("Page cant load");
            
        }
        

    }



    @FXML
    void login(ActionEvent event) throws IOException {
        String uname = username.getText();
        String pass = password.getText();
        if (uname.equals("") || pass.equals("")) {
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR MESSAGE");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the blanks");
                alert.showAndWait();

      //    JOptionPane.showMessageDialog(null, "Username or password is blank");
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bloodadmin", "root", "");
                pst = con.prepareStatement("select * from admin where username=? and password =?");
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
                       
                       Stage currentStage = (Stage) btn_login.getScene().getWindow();
                      currentStage.hide();

                    Parent root = FXMLLoader.load(getClass().getResource("homepage1.fxml"));
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
                    username.setText("");
                    password.setText("");
                    username.requestFocus();
                }
            } catch (ClassNotFoundException | SQLException e) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     
    }

   /*FXML
    private void close(ActionEvent event) {
        
    } */

   }
