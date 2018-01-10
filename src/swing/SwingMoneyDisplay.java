package swing;

import Model.Money;
import ui.MoneyDisplay;

import javax.swing.*;
import java.awt.*;

public class SwingMoneyDisplay extends JPanel implements MoneyDisplay{


    private Money money;

    @Override
    public void display(Money money) {
        this.money = money;
        this.removeAll();
        this.add(amount());
        this.add(currency());
        this.updateUI();
    }

    private Component currency() {
        return new JLabel(money.getCurrency().getCode());
    }

    private Component amount() {
        return new JLabel(String.valueOf(money.getAmount()));
    }
}
