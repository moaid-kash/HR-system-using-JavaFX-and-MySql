package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox vBox =new VBox();
        vBox.getChildren().add(getPane());
      //  Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene=new Scene(vBox, 400, 400);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    static BorderPane getPane() {


        Button bt = new Button("set");
        Button bexit = new Button("Exit");
        TextField f1 = new TextField();
        PasswordField pass = new PasswordField();

        Label l1 = new Label("USR_NAME");
        Label l2 = new Label("PASSWORD");


        Text te = new Text("HR_System");
        te.setFont(new Font("Bookman Old Style", 48));




        bt.setTranslateX(120);
        bt.setTranslateY(150);

        f1.setTranslateX(120);
        f1.setTranslateY(70);

        pass.setTranslateX(120);
        pass.setTranslateY(110);

        l1.setTranslateX(10);
        l1.setTranslateY(70);

        l2.setTranslateX(10);
        l2.setTranslateY(110);


        bexit.setPrefWidth(200);
        bt.setPrefWidth(200);
        f1.setPrefWidth(200);
        pass.setPrefWidth(200);
        l1.setPrefWidth(200);
        l2.setPrefWidth(200);


        Group g = new Group();

        g.getChildren().add(bt);
        g.getChildren().add(f1);
        g.getChildren().add(pass);
        g.getChildren().add(l1);
        g.getChildren().add(l2);


        BorderPane bp = new BorderPane();

        bp.setPadding(new Insets(50, 20, 34, 65));

        bexit.setOnAction(event ->  {
            System .exit(0);

        });

        bt.setOnAction(event -> {

            Connection conn = null;
            try {


                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();


                conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1 /test", "root", "");


                Statement stmt = conn.createStatement();

                // stmt .executeUpdate("insert into pas values (4,'kash','888')") ;

                ResultSet rs = stmt.executeQuery("select * from pas");

                int f = 0;
                while (rs.next()) {

                    if (f1.getText().equals(rs.getString(2)) && pass.getText().equals(rs.getString(3))) {
                        bp .setLeft(new Label( rs.getString(2)) ) ;
                        bp .setRight(new Label( rs.getString(3))) ;

                        System.out.println(rs.getString(1) + "  " + rs.getString(2) + " " + rs.getString(3));


                    }


                }


            } catch (SQLException e) {
                // handle any errors
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("VendorError: " + e.getErrorCode());
            } catch (Exception ex) {
                System.out.println(": " + ex.getMessage());
            }
        });
        bp.setTop(te);

        bp.setCenter(g);
        bp.setBottom(bexit);

        return bp;


    }


    public static void main(String[] args) {
        launch(args);
    }
}
