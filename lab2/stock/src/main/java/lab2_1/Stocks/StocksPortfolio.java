package lab2_1.Stocks;

import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio {
    private List<Stock> stocks;
    private IStockmarketService stockmarket;
    public StocksPortfolio(IStockmarketService i){
        this.stockmarket = i;
        this.stocks=new ArrayList<>();
    }
    public void addStock(Stock s){
        stocks.add(s);
    }
    public double getTotalValue(){
        double val = 0;
        for (int i=0; i<stocks.size();i++){
            val += (stocks.get(i).getQuantity() * stockmarket.lookUpPrice(stocks.get(i).getLabel()));
        }
        return val;
    }
}
