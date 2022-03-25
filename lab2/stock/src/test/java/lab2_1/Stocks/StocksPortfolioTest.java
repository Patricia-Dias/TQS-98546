package lab2_1.Stocks;

import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;


@ExtendWith(MockitoExtension.class)
public class StocksPortfolioTest {
    
    @Mock
    private IStockmarketService market;
    
    @InjectMocks
    private StocksPortfolio portfolio;

    @Test
    public void getTotalValueTest(){
        IStockmarketService market = Mockito.mock(IStockmarketService.class);
        StocksPortfolio portfolio = new StocksPortfolio(market);

        Mockito.when(market.lookUpPrice("TESLA")).thenReturn(4.0);
        Mockito.when(market.lookUpPrice("YIKES")).thenReturn(1.5);

        portfolio.addStock(new Stock("TESLA", 2));
        portfolio.addStock(new Stock("YIKES", 4));
        double res = portfolio.getTotalValue();

        assertEquals( 14.0, res);
        Mockito.verify(market, Mockito.times(2)).lookUpPrice(Mockito.anyString());
    }

    @Test
    public void getTotalValueHamcrestTest(){
        IStockmarketService market = Mockito.mock(IStockmarketService.class);
        StocksPortfolio portfolio = new StocksPortfolio(market);

        Mockito.when(market.lookUpPrice("TESLA")).thenReturn(4.0);
        Mockito.when(market.lookUpPrice("YIKES")).thenReturn(1.5);

        portfolio.addStock(new Stock("TESLA", 2));
        portfolio.addStock(new Stock("YIKES", 4));
        double res = portfolio.getTotalValue();

        assertThat(res, is(14.0));

        Mockito.verify(market, Mockito.times(2)).lookUpPrice(Mockito.anyString());
    }
}
