package com.main_dir;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    protected Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        mainWindow();
    }

    // --- root windows ---
    public void mainWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("../../FXMLfiles/mainWindow.fxml"));
            AnchorPane pane = loader.load();
            MainController controller = loader.getController();
            Scene scene = new Scene(pane);
            controller.setMain(this, primaryStage);

            pane.requestFocus();

            primaryStage.setTitle("Calculator");
            primaryStage.getIcons().add(new Image("/images/icon.png"));
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void integralWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("../../FXMLfiles/integralWindow.fxml"));
            AnchorPane pane = loader.load();

            IntegralController controller = loader.getController();

            Scene scene = new Scene(pane) ;
            controller.setMain(this);

            primaryStage.setTitle("Integral Calculator");
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void graphWindow(){
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader()
                    .getResource("FXMLfiles/graphWindow.fxml")));

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.initOwner(primaryStage);

            stage.getIcons().add(new Image("images/icon.png"));

            stage.setTitle("Graph");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
