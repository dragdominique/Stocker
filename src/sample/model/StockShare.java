package sample.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StockShare {

    private SimpleStringProperty companyName;
    private SimpleStringProperty stockIndex;
    private SimpleDoubleProperty sharePrice;
    private SimpleIntegerProperty numberOfShares;
    private SimpleDoubleProperty totalValueOfShares;

    public StockShare(String companyName, String stockIndex, double sharePrice, int numberOfShares){
        this.companyName = new SimpleStringProperty(companyName);
        this.stockIndex = new SimpleStringProperty(stockIndex);
        this.sharePrice = new SimpleDoubleProperty(sharePrice);
        this.numberOfShares = new SimpleIntegerProperty(numberOfShares);
        this.totalValueOfShares = new SimpleDoubleProperty(numberOfShares * sharePrice);
    }


    public String getCompanyName(){
        return this.companyName.get();
    }

    public void setCompanyName(String companyName){
        this.companyName = new SimpleStringProperty(companyName);
    }


    public String getStockIndex(){
        return this.stockIndex.get();
    }

    public void setStockIndex(String stockIndex){
        this.stockIndex = new SimpleStringProperty(stockIndex);
    }

  //  private double sharePrice;
   // private int numberOfShares;
    //private double totalValueOfShares;

    public double getSharePrice(){
        return this.sharePrice.get();
    }

    public void setSharePrice(double sharePrice){
        this.sharePrice = new SimpleDoubleProperty(sharePrice);
    }

    public int getNumberOfShares(){
        return this.numberOfShares.get();
    }

    public void setNumberOfShares(int numberOfShares){
        this.numberOfShares = new SimpleIntegerProperty(numberOfShares);
    }

    public void setTotalValueOfShares(int numberOfShares, double sharePrice){
        this.totalValueOfShares = new SimpleDoubleProperty(numberOfShares*sharePrice);
    }

    public double getTotalValueOfShares(){
        return this.totalValueOfShares.get();
    }

}