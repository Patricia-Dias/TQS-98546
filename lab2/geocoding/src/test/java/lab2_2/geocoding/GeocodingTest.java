package lab2_2.geocoding;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import org.junit.Test;
import org.mockito.Mockito;

import lab2_2.geocoding.connection.TqsBasicHttpClient;
import lab2_2.geocoding.geocoding.Address;
import lab2_2.geocoding.geocoding.AddressResolver;

/**
 * Unit test for simple App.
 */
public class GeocodingTest 
{

    @Test
    public void whenGoodCoordinates_returnAdress() throws IOException, URISyntaxException, org.json.simple.parser.ParseException{

        TqsBasicHttpClient httpClient = Mockito.mock(TqsBasicHttpClient.class);

        String json = "{\"info\":{\"statuscode\":0,\"copyright\":{\"text\":\"\u00A9 2022 MapQuest, Inc.\",\"imageUrl\":\"http://api.mqcdn.com/res/mqlogo.gif\",\"imageAltText\":\"\u00A9 2022 MapQuest, Inc.\"},\"messages\":[]},\"options\":{\"maxResults\":1,\"thumbMaps\":true,\"ignoreLatLngInput\":false},\"results\":[{\"providedLocation\":{\"latLng\":{\"lat\":40.640661,\"lng\":-8.656688}},\"locations\":[{\"street\":\"Cais do Alboi\",\"adminArea6\":\"\",\"adminArea6Type\":\"Neighborhood\",\"adminArea5\":\"Gl\u00F3ria e Vera Cruz\",\"adminArea5Type\":\"City\",\"adminArea4\":\"\",\"adminArea4Type\":\"County\",\"adminArea3\":\"Centro\",\"adminArea3Type\":\"State\",\"adminArea1\":\"PT\",\"adminArea1Type\":\"Country\",\"postalCode\":\"3800-246\",\"geocodeQualityCode\":\"B1AAA\",\"geocodeQuality\":\"STREET\",\"dragPoint\":false,\"sideOfStreet\":\"N\",\"linkId\":\"0\",\"unknownInput\":\"\",\"type\":\"s\",\"latLng\":{\"lat\":40.640524,\"lng\":-8.656713},\"displayLatLng\":{\"lat\":40.640524,\"lng\":-8.656713},\"mapUrl\":\"http://open.mapquestapi.com/staticmap/v5/map?key=uXSAVwYWbf9tJmsjEGHKKAo0gOjZfBLQ&type=map&size=225,160&locations=40.64052368145179,-8.656712986761146|marker-sm-50318A-1&scalebar=true&zoom=15&rand=1773580644\",\"roadMetadata\":null}]}]}";

        AddressResolver addresolver = new AddressResolver(httpClient);

        Mockito.when(httpClient.doHttpGet(contains("location=40.631800%2C-8.658000"))).thenReturn(json);

        //expected 40.6318,-8.658
        Optional<Address> expected;
        expected = addresolver.findAddressForLocation(40.6318,-8.658);
        //address res
        Address res = new Address("Cais do Alboi", "Glória e Vera Cruz", "Centro", "3800-246", null);
        Optional<Address> result = Optional.of(res);

        assertEquals (expected, result);
    }

    @Test
    public void whenBadCoordinates_throwException() throws IOException, URISyntaxException, org.json.simple.parser.ParseException{

        TqsBasicHttpClient httpClient = Mockito.mock(TqsBasicHttpClient.class);

        String json = "{\"info\":{\"statuscode\":0,\"copyright\":{\"text\":\"\u00A9 2022 MapQuest, Inc.\",\"imageUrl\":\"http://api.mqcdn.com/res/mqlogo.gif\",\"imageAltText\":\"\u00A9 2022 MapQuest, Inc.\"},\"messages\":[]},\"options\":{\"maxResults\":1,\"thumbMaps\":true,\"ignoreLatLngInput\":false},\"results\":[{\"providedLocation\":{\"latLng\":{\"lat\":120.0,\"lng\":-600.0}},\"locations\":[]}]}";

        AddressResolver addresolver = new AddressResolver(httpClient);

        Mockito.when(httpClient.doHttpGet(contains("location=120.000000%2C-600.000000"))).thenReturn(json);

        //expected 120.000000, -600.000000
        Optional<Address> expected;
        expected = addresolver.findAddressForLocation(120,-600);
        
        assertThrows( IndexOutOfBoundsException.class, () -> {
            addresolver.findAddressForLocation(120,-600);
        });
    }
}
