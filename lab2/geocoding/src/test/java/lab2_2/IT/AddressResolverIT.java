package lab2_2.IT;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import lab2_2.geocoding.connection.TqsBasicHttpClient;
import lab2_2.geocoding.geocoding.Address;
import lab2_2.geocoding.geocoding.AddressResolver;

public class AddressResolverIT {

    private AddressResolver addresolver;

    @BeforeEach
    public void setup(){
        TqsBasicHttpClient httpClient = new TqsBasicHttpClient();
        addresolver = new AddressResolver(httpClient);
    }

    @Test
    public void whenGoodCoordinates_returnAdress() throws IOException, URISyntaxException, org.json.simple.parser.ParseException{

        Optional<Address> res = addresolver.findAddressForLocation(40.6318, -8.658);
        assertEquals(new Address("Cais do Alboi", "Glória e Vera Cruz", "Centro", "3800-246", null), res);
    }

    @Test
    public void whenBadCoordinates_throwException() throws IOException, URISyntaxException, org.json.simple.parser.ParseException{
        
        assertThrows( NullPointerException.class, () -> {
            addresolver.findAddressForLocation(120,-600);
        });
    }
}
