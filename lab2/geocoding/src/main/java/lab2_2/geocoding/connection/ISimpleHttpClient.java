package lab2_2.geocoding.connection;

import java.io.IOException;

public interface ISimpleHttpClient {
    public String doHttpGet(String url) throws IOException;
}
