package Controller;

import Model.Currency;
import Model.Money;
import persistence.ExchangeRateLoader;
import ui.MoneyDialog;
import ui.MoneyDisplay;

import java.io.IOException;

public class CalculateCommand implements Command {

    private final MoneyDisplay moneyDisplay;
    private final MoneyDialog moneyDialog;
    private final ExchangeRateLoader loader;
    private Currency eur = new Currency("EUR","Euro","€");

    public CalculateCommand(MoneyDialog moneyDialog, MoneyDisplay moneyDisplay, ExchangeRateLoader loader) {
        this.moneyDisplay = moneyDisplay;
        this.moneyDialog = moneyDialog;
        this.loader = loader;
    }

    @Override
    public String name() {
        return "calculate";
    }

    @Override
    public void execute(){
        moneyDisplay.display(exchange(moneyDialog.get()));
    }

    private Money exchange(Money money){
        return new Money(money.getAmount() * rateOf(money.getCurrency()), eur);
    }

    private double rateOf(Currency currency){
        return loader.load(currency,eur).getAmount();
    }
}
