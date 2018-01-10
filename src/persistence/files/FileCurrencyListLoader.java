package persistence.files;

import Model.Currency;
import persistence.CurrencyListLoader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileCurrencyListLoader implements CurrencyListLoader {

    private final String filename;

    public FileCurrencyListLoader(String filename){
        this.filename = filename;
    }

    @Override
    public Currency[] currencies() {
        List<Currency> list = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filename)));
            while(true){
                String line = bufferedReader.readLine();
                if(line == null) break;
                list.add(currencyOf(line));
            }
        } catch (IOException ignored) {
        }
        return list.toArray(new Currency[0]);
    }

    private Currency currencyOf(String line) {
        String[] split = line.split(",");
        return new Currency(split[0],split[1],split[2]);
    }
}
