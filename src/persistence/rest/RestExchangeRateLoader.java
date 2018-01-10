package persistence.rest;

import Model.Currency;
import Model.ExchangeRate;
import persistence.ExchangeRateLoader;

import java.io.InputStream;
import java.net.URL;

import java.io.IOException;

public class RestExchangeRateLoader implements ExchangeRateLoader{

    @Override
    public ExchangeRate load(Currency from, Currency to){
        try {
            return new ExchangeRate(from, to, read(from.getCode(), to.getCode()));
        } catch (IOException ignored) {
            return null;
        }
    }

    private double read(String from, String to) throws IOException {
        String line = read(new URL("http://api.fixer.io/latest?base="+from+"&symbols="+to)) ;
        return Double.parseDouble(line.substring(line.indexOf(to)+5, line.indexOf("}")));

    }

    private String read(URL url) throws IOException {
        InputStream is = url.openStream();
        byte[] buffer = new byte[1024];
        int length = is.read(buffer);
        return new String(buffer, 0, length);
    }
}
