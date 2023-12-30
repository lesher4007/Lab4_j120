package exercise1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainClass extends Application {

    private Scene scene;
    private String text;
    private String[] words;
    private TextArea textArea;
    private List<Goods> goodsForTable;
    private TableStage tableStage;


    @Override
    public void start(Stage stage) throws IOException {

        EventHandler<ActionEvent> openHandler = event -> openFile();
        tableStage =new TableStage();

        BorderPane root = new BorderPane();
        MenuBar bar = new MenuBar();
        root.setTop(bar);

        Menu menuFile = new Menu("File");
        bar.getMenus().addAll(menuFile);

        MenuItem openItem = new MenuItem("Open");
        openItem.setOnAction(openHandler);
        MenuItem clearItem = new MenuItem("Clear");
        clearItem.setOnAction(event -> {
            textArea.clear();
            text = "";
            goodsForTable.clear();
        });
        menuFile.getItems().addAll(openItem,clearItem);

        Button button = new Button("Open Table");
        button.setOnAction((e) -> {
            tableStage.init().show();
        });
        textArea=new TextArea();
        scene = new Scene(root, 600, 500);
        root.setCenter(textArea);
        root.setBottom(button);
        stage.setTitle("CSV-File");
        stage.setScene(scene);
        stage.show();
    }

    public void openFile(){
        FileChooser chooser= new FileChooser();
        File file = chooser.showOpenDialog(scene.getWindow());
        StringBuilder sb = new StringBuilder();
        if (file !=null && file.canRead()){
            try(BufferedReader reader = new BufferedReader(new FileReader(file))){
                String temp;
                while ((temp= reader.readLine())!=null){
                    sb.append(temp);
                    sb.append("\n");
                }
                sb.delete(sb.length()-1,sb.length());
            }catch (IOException ioe){}
            text = sb.toString();
            textArea.setText(text);
            textReader();
        }
    }

    public void textReader(){
        goodsForTable = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        char[]arrayChar=text.toCharArray();
            for (int i = 0; i < arrayChar.length; i++) {
                if (arrayChar[i] == '"') {
                    i++;
                    sb.append("\"");
                    for (int k = i; k < arrayChar.length; k++) {
                        if (arrayChar[k] != '"') {
                            if (arrayChar[k] == ',') {
                                i++;
                                sb.append("&&");
                            }else {
                                i++;
                                sb.append(arrayChar[k]);
                            }
                        } else {
                            sb.append("\"");
                            break;
                        }
                    }
                }else {
                    sb.append(arrayChar[i]);
                }
            }
            text = sb.toString();
            words = text.split("\n");
            for (String s: words){
                String[] split =  s.split(",");
                goodsForTable.add(new Goods(split[0],split[1],split[2],split[3],split[4]));
            }
            tableStage.setGoodsForTable(goodsForTable);
    }

    public static void main(String[] args) {
        launch();
    }
}