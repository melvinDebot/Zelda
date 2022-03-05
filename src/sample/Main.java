package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import utilitaires.Utilitaires;

import java.util.HashMap;
import java.util.Locale;

public class Main extends Application {

    Scene sceneMenu, sceneHighScore;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Game game = new Game();


        Utilitaires.fill2DArrayChar(game.data.getMap());
        Utilitaires.createAndPlaceEnemyOnMap(game.data);
        Utilitaires.createPerso(game.data);
        Utilitaires.fillMapWithElement(game.data);
        Utilitaires.createAndPlaceItemOnMap(game.data);



        Personnage p = game.data.getPlayer();
        Circle cp1 = new Circle(p.getX()*Data.RATIODISPLAY, p.getY()*Data.RATIODISPLAY, 20);
        cp1.setFill(Color.BLUE);
        DataView.groupGame.getChildren().add(cp1);

        HashMap<Enemy, Circle> hmEnemies = new HashMap<Enemy, Circle>();
        for (Enemy e :game.data.getEnemies()) {
            Circle c = new Circle(e.getX()*Data.RATIODISPLAY, e.getY()*Data.RATIODISPLAY, 20);
            // Text ct = new Text(""+e.getPdv());

            c.setFill(Color.RED);
            DataView.groupGame.getChildren().add(c);
            DataView.groupGame.getChildren().add(e.printPdv);

            hmEnemies.put(e, c);
        }

        for (Item i :game.data.getItems()) {
            Circle c = new Circle(i.getX()*Data.RATIODISPLAY, i.getY()*Data.RATIODISPLAY, 20);
            c.setFill(Color.YELLOW);
            DataView.groupGame.getChildren().add(c);
            DataView.hmItems.put(i, c);
        }



        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), ev -> {
            game.handleEnemyMouvement(game.data);

            for (Enemy e :hmEnemies.keySet()) {
                hmEnemies.get(e).setCenterX(e.getX() * Data.RATIODISPLAY);
                hmEnemies.get(e).setCenterY(e.getY() * Data.RATIODISPLAY);
                e.printPdv.setX(e.getX() * Data.RATIODISPLAY);
                e.printPdv.setY(e.getY() * Data.RATIODISPLAY - 40);

            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);//or indefinitely




        /*Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {

            for (Element[] c1 :game.data.getMap() ) {
                for (Element c2: c1) {
                    if(c2 instanceof Enemy){
                        Enemy e = (Enemy) c2;
                        Circle c = new Circle(e.getX()*Data.RATIODISPLAY, e.getY()*Data.RATIODISPLAY, 20);
                        c.setFill(Color.RED);
                        group.getChildren().add(c);
                    }
                    else if(c2 instanceof Player){
                        Player p = (Player) c2;
                        Circle c = new Circle(p.getX()*Data.RATIODISPLAY, p.getY()*Data.RATIODISPLAY, 20);
                        c.setFill(Color.BLUE);
                        group.getChildren().add(c);
                    }
                    else if(c2 instanceof Item){
                        Item i = (Item) c2;
                        Circle c = new Circle(i.getX()*Data.RATIODISPLAY, i.getY()*Data.RATIODISPLAY, 20);
                        c.setFill(Color.YELLOW);
                        group.getChildren().add(c);
                    }
                }
            }
        }));
       // timeline.setCycleCount(1);//do it x times
        timeline.setCycleCount(Animation.INDEFINITE);//or indefinitely
        timeline.play();
*/


/*
        for (Personnage ennemy: game.data.getEnemies()) {
            Circle c = new Circle(ennemy.getX()*40, ennemy.getY()* Data.RATIODISPLAY, 20);
            c.setFill(Color.RED);
            group.getChildren().add(c);
        }

        Personnage player = game.data.getPlayer();
        Circle cP = new Circle(player.getX()*40, player.getY()*40, 20);
        cP.setFill(Color.BLUE);
        group.getChildren().add(cP);

        for(Element item: game.data.getItems()){
            Circle cI = new Circle(item.getX()*40, item.getY()*40, 20);
            cI.setFill(Color.VIOLET);
            group.getChildren().add(cI);
        }
*/

        Rectangle r1 = new Rectangle(0, 0,10, Data.GAMESCENEHEIGHT);
        Rectangle r2 = new Rectangle(0, 0,Data.GAMESCENEWIDHT, 10);
        Rectangle r3 = new Rectangle(Data.GAMESCENEWIDHT-10, 0, 10, Data.GAMESCENEWIDHT);
        Rectangle r4 = new Rectangle(0, Data.GAMESCENEWIDHT-10,Data.GAMESCENEHEIGHT, 10);
        r1.setFill(Color.GREEN);
        r2.setFill(Color.GREEN);
        r3.setFill(Color.GREEN);
        r4.setFill(Color.GREEN);
        DataView.groupGame.getChildren().add(r1);
        DataView.groupGame.getChildren().add(r2);
        DataView.groupGame.getChildren().add(r3);
        DataView.groupGame.getChildren().add(r4);

        // r1.setFill(Color.PURPLE);

        // group.getChildren().add(r1);

        Scene sceneGame = new Scene(DataView.groupGame, Data.GAMESCENEWIDHT, Data.GAMESCENEHEIGHT, Color.BLACK);

        sceneGame.setOnKeyPressed((KeyEvent event) -> {
            if (Data.gameStarted) {
                if(event.getText().toLowerCase().equals("p")){
                    timeline.pause();
                    Data.gameStarted = false;
                    primaryStage.setScene(sceneMenu);
                } else if(event.getText().toLowerCase().equals("o")){

                }
                else {
                    game.handleMouvablePersonnageMouvement(game.data, event.getText(), game.data.getPlayer());

                    cp1.setCenterX(p.getX() * Data.RATIODISPLAY);
                    cp1.setCenterY(p.getY() * Data.RATIODISPLAY);
                }
            }
            // cP.setCenterX(game.data.getPlayer().getX()*40);
            // cP.setCenterY(game.data.getPlayer().getY()*40);

        });


        // creation sceneMenu
        Button buttonStart = new Button("Start");
        buttonStart.setLayoutX(Data.GAMESCENEHEIGHT/2);
        buttonStart.setLayoutY(Data.GAMESCENEWIDHT/2);

        Button buttonHighScore = new Button("HighScore");
        buttonStart.setLayoutX(Data.GAMESCENEHEIGHT/2);
        buttonStart.setLayoutY(Data.GAMESCENEWIDHT/2 + 100);

        TextField textFieldNamePlayer = new TextField();
        textFieldNamePlayer.setLayoutX(Data.GAMESCENEHEIGHT/2);
        textFieldNamePlayer.setLayoutY(Data.GAMESCENEWIDHT/2 + 50);

        TextField textFieldTestHighScore = new TextField();
        textFieldTestHighScore.setLayoutX(Data.GAMESCENEHEIGHT/2);
        textFieldTestHighScore.setLayoutY(Data.GAMESCENEWIDHT/2);


        Group groupMenuStart = new Group();
        groupMenuStart.getChildren().add(buttonStart);
        groupMenuStart.getChildren().add(textFieldNamePlayer);
        groupMenuStart.getChildren().add(buttonHighScore);

        sceneMenu = new Scene(groupMenuStart, Data.GAMESCENEWIDHT, Data.GAMESCENEHEIGHT, Color.BLACK);

        //creation sceneHighScore
        Group groupHighScore = new Group();
        sceneHighScore = new Scene(groupHighScore, Data.GAMESCENEWIDHT, Data.GAMESCENEHEIGHT, Color.BLACK);
        groupHighScore.getChildren().add(textFieldTestHighScore);
        buttonStart.setOnMouseClicked(event -> {
            primaryStage.setScene(sceneGame);
            timeline.play();
            Data.gameStarted = true;

            if(textFieldNamePlayer.getText().equals("")){
                p.setName("Default");
            }
            else{
                p.setName(textFieldNamePlayer.getText());
            }
        });

        buttonHighScore.setOnMouseClicked(event -> {
                    if (textFieldNamePlayer.getText().equals("")) {
                        p.setName("Default");
                    } else {
                        p.setName(textFieldNamePlayer.getText());
                    }
                    textFieldTestHighScore.setText(p.getName());
                    primaryStage.setScene(sceneHighScore);
                }
        );



        primaryStage.setScene(sceneMenu);



        primaryStage.show();
    }


    public static void main(String[] args) {
        /*Game game = new Game();
        game.game();*/

        //  game.initializeData();


        launch(args);
    }

}