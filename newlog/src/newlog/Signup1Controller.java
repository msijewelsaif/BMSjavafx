
package newlog;

    

//package newlog;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Signup1Controller implements Initializable {

    Connection connect;
    PreparedStatement pst;
    ResultSet rs;

    
    
    
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
    private Button btn_create;

    @FXML
    private PasswordField password;

    @FXML
    private TextField txt_address;

    @FXML
    private TextField txt_bloodgroup;

    @FXML
    private DatePicker txt_date_of_birth;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_phone;

    @FXML
    private TextField txt_username;

    @FXML
    void create(ActionEvent event) throws SQLException {
        if (txt_email.getText().isEmpty() || txt_username.getText().isEmpty() || password.getText().isEmpty()
                || txt_phone.getText().isEmpty() || txt_name.getText().isEmpty() || txt_address.getText().isEmpty()
                || txt_bloodgroup.getText().isEmpty() || txt_date_of_birth.getEditor().getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR MESSAGE");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();

        } else if (password.getText().length() < 8) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR MESSAGE");
            alert.setHeaderText(null);
            alert.setContentText("Password must be at least 8 characters");
            alert.showAndWait();
        } else {
            String checktxt_username = "SELECT * FROM registerpanel WHERE txt_username = ?";
            Connection connectDB = null;
            connect = connectDB;
            /*
            String insertData = "INSERT INTO registerpanel (txt_name, txt_username, txt_email,txt_phone, txt_address,txt_bloodgroup, txt_date_of_birth,password)"
                    + "VALUES (?,?,?,?,?,?,?,?)";
                  /
                    PreparedStatement prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, txt_name.getText());
                    prepare.setString(2,txt_username.getText());
                    prepare.setString(3, txt_email.getText());
                    prepare.setString(4, txt_phone.getText());
                    prepare.setString(5, txt_bloodgroup.getText());
                    prepare.setString(6, txt_address.getText());
                    prepare.setString(7, txt_date_of_birth.getEditor().getText());
                    prepare.setString(8,password.getText());

                    prepare.executeUpdate();
*/
//copied
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bloodadmin", "root", "");
                
                PreparedStatement statement = connect.prepareStatement(checktxt_username);
                statement.setString(1, txt_username.getText());
                ResultSet result = statement.executeQuery();
                if (result.next()) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR MESSAGE");
                    alert.setHeaderText(null);
                    alert.setContentText("Username is taken");
                    alert.showAndWait();
                } else {
                   String insertData = "insert into registerpanel (txt_name, txt_username, txt_email,txt_phone, txt_address,txt_bloodgroup, txt_date_of_birth,password)"
                           + " VALUES (?,?,?,?,?,?,?,?)";
                  
                    PreparedStatement prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, txt_name.getText());
                    prepare.setString(2,txt_username.getText());
                    prepare.setString(3, txt_email.getText());
                    prepare.setString(4, txt_phone.getText());
                    prepare.setString(5, txt_bloodgroup.getText());
                    prepare.setString(6, txt_address.getText());
                    prepare.setString(7, txt_date_of_birth.getEditor().getText());
                    prepare.setString(8,password.getText());
                    prepare.executeUpdate();
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("NEW ACCOUNT");
                    alert.setHeaderText(null);
                    alert.setContentText("Account created successfully");
                    alert.showAndWait();
                    
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
