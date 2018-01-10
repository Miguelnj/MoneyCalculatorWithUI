import Controller.CalculateCommand;
import persistence.CurrencyListLoader;
import persistence.ExchangeRateLoader;
import persistence.files.FileCurrencyListLoader;
import persistence.rest.RestExchangeRateLoader;

public class Main {
    public static void main(String[] args) {
        CurrencyListLoader currencyLoader = new FileCurrencyListLoader("currencies");
        ExchangeRateLoader restExchangeRateLoader = new RestExchangeRateLoader();

        MainFrame mainFrame = new MainFrame(currencyLoader.currencies());
        mainFrame.add(new CalculateCommand(mainFrame.getMoneyDialog(), mainFrame.getMoneyDisplay(), restExchangeRateLoader));

    }
}
