package tipcalcapp;

import java.math.*;
import java.text.NumberFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;

public class TipCalculatorController {
	
	private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
	private static final NumberFormat percent = NumberFormat.getPercentInstance();
	
	private BigDecimal tipPercentage = new BigDecimal(0.15);

    @FXML
    private ColumnConstraints GridPane;

    @FXML
    private Label amountLabel;

    @FXML
    private Label tipPercentageLabel;

    @FXML
    private Label tipAmountLabel;

    @FXML
    private Label totalAmountLabel;

    @FXML
    private TextField amountTextField;

    @FXML
    private TextField tipTextField;

    @FXML
    private TextField totalTextField;

    @FXML
    private Slider tipPercentageSlider;

    @FXML
    private Button calcButton;

    @FXML
    void calculateButtonPressed(ActionEvent event) {
    	try {
    	BigDecimal amount = new BigDecimal(amountTextField.getText());  //get input from user 
    	BigDecimal tip = amount.multiply(tipPercentage);                
    	BigDecimal total = amount.add(tip);
    	tipTextField.setText(currency.format(tip));      //return currency formated tip
    	totalTextField.setText(currency.format(total));  //return currency formated amount 
    	}
    	catch (NumberFormatException e) {
    		amountTextField.setText("Enter amount");    //Message appear to prompt user to enter valid amount 
    		amountTextField.selectAll();                //Select the Text Field's text 
    		amountTextField.requestFocus();             //Give User the focus to reenter amount in text field
    	}
    	
    }
    // Called by FXMLLoader to initialize the controller
    public void initialize() {
    	// 0-4 rounds down, 5-9 rounds up
    	currency.setRoundingMode(RoundingMode.HALF_UP);
    	
    	// listener for changes to tipPercentage slider value
    	tipPercentageSlider.valueProperty().addListener(new ChangeListener<Number>() {
    		@Override
    		public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
    			tipPercentage = BigDecimal.valueOf(newValue.intValue()/100.0);
    			tipPercentageLabel.setText(percent.format(tipPercentage));
    		  }
    		
    	   }
    			
    			
    			
        );
    	
    }

}
