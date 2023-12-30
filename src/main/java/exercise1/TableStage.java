package exercise1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class TableStage {

    private List<Goods> goodsForTable;

    public void setGoodsForTable(List<Goods> goodsForTable) {
        this.goodsForTable = goodsForTable;
    }

    public Stage init(){
        BorderPane root = new BorderPane();
        root.setCenter(getTable());
        Stage stage = new Stage();
        Scene scene = new Scene(root,600,400);
        stage.setTitle("Table");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        return stage;

    }

    public TableView<Goods> getTable(){
        TableView<Goods> table = new TableView<>();

        TableColumn<Goods,String> vendorCodeColumn = new TableColumn<>();
        vendorCodeColumn.setCellValueFactory(new PropertyValueFactory<>("vendorCode"));

        TableColumn<Goods,String> nameColumn = new TableColumn<>();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Goods,String> unitColumn = new TableColumn<>();
        unitColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));

        TableColumn<Goods,String> quantityColumn = new TableColumn<>();
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Goods,String> priceColumn = new TableColumn<>();
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.getColumns().addAll(vendorCodeColumn,nameColumn,unitColumn,quantityColumn,priceColumn);

        ObservableList<Goods> goods = FXCollections.observableArrayList(goodsForTable);
        table.setItems(goods);

        return table;
    }
}
