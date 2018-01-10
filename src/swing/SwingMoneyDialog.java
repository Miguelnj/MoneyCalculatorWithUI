package swing;

import Model.Currency;
import Model.Money;
import ui.MoneyDialog;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class SwingMoneyDialog extends JPanel implements MoneyDialog{
    private final Currency[] currencies;
    private Currency currency;
    private String amount;

    public SwingMoneyDialog(Currency[] currencies){
        this.currencies = currencies;
        this.add(amount());
        this.add(currency());
   }

    private Component currency() {
       JComboBox<Currency> combo = new JComboBox<>(currencies);
       combo.addItemListener(currencyChanged());
       currency = (Currency) combo.getSelectedItem();
        return combo;
    }

    private ItemListener currencyChanged() {
        return new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.DESELECTED) return;
                currency = (Currency) e.getItem();
            }
        };
    }


    private Component amount() {
       JTextField field = new JTextField("100");
       field.setColumns(10);
       field.getDocument().addDocumentListener(amountChanged());
       amount = field.getText();
       return field;
    }

    private DocumentListener amountChanged() {
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                amountChanged(e.getDocument());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                amountChanged(e.getDocument());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                amountChanged(e.getDocument());
            }

            private void amountChanged(Document document) {
                try {
                    amount = document.getText(0,document.getLength());
                } catch (BadLocationException ignored) {
                }
            }
        };
    }

    @Override
    public Money get() {
        return new Money(Double.parseDouble(amount),currency);
    }

}
