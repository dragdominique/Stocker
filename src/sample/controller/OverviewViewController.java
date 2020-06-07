package sample.controller;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import sample.model.StockShare;
import sample.model.Stocks;
import sample.model.UserData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class OverviewViewController {
    private UserData userData;

    public TableView<StockShare> overviewTable = new TableView<StockShare>();


    public Stocks stocks = new Stocks(
            new StockShare("CDR", "WIG20", 346, 10)
    );

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

   public void setStocks(Stocks stocks) {
        this.stocks = stocks;
    }

    /**
     * Method to load table view, overview of stocks
     * @return overviewTable
     */
    public VBox loadOverviewView () {

        Label overviewLabel = new Label("Overview");
        overviewTable.setEditable(true);


        TableColumn<StockShare, String> companyName = new TableColumn<>("Company Name");
        TableColumn<StockShare, String> stockIndex = new TableColumn<>("Stock Index");
        TableColumn<StockShare, Double> boughtPrice = new TableColumn("Bought Price");
        TableColumn<StockShare, Double> actualPrice = new TableColumn("Actual Price");
        TableColumn<StockShare, Double> change = new TableColumn("Change");
        TableColumn<StockShare, Double> changePercent = new TableColumn("Change (%)");
        TableColumn<StockShare, Integer> numberOfShares = new TableColumn("Number Of Shares");
        TableColumn<StockShare, Double> totalValueOfShares = new TableColumn("Total Value");

        companyName.setCellValueFactory(new PropertyValueFactory<>("stockName"));
        stockIndex.setCellValueFactory(new PropertyValueFactory<>("stockIndex"));
        boughtPrice.setCellValueFactory(new PropertyValueFactory<>("boughtPrice"));
        actualPrice.setCellValueFactory(new PropertyValueFactory<>("actualPrice"));
        change.setCellValueFactory(new PropertyValueFactory<>("change"));
        changePercent.setCellValueFactory(new PropertyValueFactory<>("changePercent"));
        numberOfShares.setCellValueFactory(new PropertyValueFactory<>("numberOfShares"));
        totalValueOfShares.setCellValueFactory(new PropertyValueFactory<>("totalValueOfShares"));

        companyName.setPrefWidth(116);
        stockIndex.setPrefWidth(116);
        boughtPrice.setPrefWidth(116);
        actualPrice.setPrefWidth(116);
        change.setPrefWidth(116);
        changePercent.setPrefWidth(116);
        numberOfShares.setPrefWidth(116);
        totalValueOfShares.setPrefWidth(116);

        setItemsInTable();

        overviewTable.getColumns().addAll(companyName, stockIndex, boughtPrice,actualPrice ,change, changePercent,numberOfShares,totalValueOfShares);
        mousePressed();
        VBox overviewBox = new VBox();
        overviewBox.setSpacing(5);
        overviewBox.setPadding(new Insets(10,10,10,10));
        overviewBox.getChildren().addAll(overviewLabel, overviewTable);

        return overviewBox;
    }


    public void setItemsInTable(){
        overviewTable.setItems(stocks.getStocksList());
    }

    public void deleteItems(){
        overviewTable.getItems().clear();
    }


    public void mousePressed(){
        overviewTable.setRowFactory( tv -> {
            TableRow<StockShare> row = new TableRow<>();
            row.setOnMouseClicked( event -> {
                if(event.getClickCount() == 2 && (!row.isEmpty())){
                    StockShare selectedStock = overviewTable.getSelectionModel().getSelectedItem();
                    setUpDetails(selectedStock);
                }
            });
            return row;
        });
    }

    public void setUpDetails(StockShare selectedStock) {
        DetailsViewController detailsViewController = new DetailsViewController(selectedStock);
        detailsViewController.setUserData(userData);
        detailsViewController.setUpDetails();
    }


    public void insertStocks(){
        String line = "";
        String splitBy = ";";

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("stocks.csv"));
            System.out.println("Jest Dobrze Mordo");
            while((line = bufferedReader.readLine()) != null){
                String[] stock = line.split(splitBy,-1);

                stocks.addStock(new StockShare(stock[0],stock[1],Double.parseDouble(stock[2]),Integer.parseInt(stock[3])));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("spierdalaj z mojej ziemi");
        }
    }
}

