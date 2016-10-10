package com.example.codetribe.coffeeorder;

import android.annotation.TargetApi;
import android.content.Intent;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int quantity = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method increases the quantity when button plus is clicked
     */
    public void increment(View view) {
        //Exit the method early so that it does not go above 100
        if(quantity == 100){
            Toast.makeText(this,"You cannot have more than 100 coffees",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);

    }

    /**
     * This method decreases the quantity when button minus is clicked
     */
    public void decrement(View view) {
        //Exit the method early so that it does not go below 1
        if(quantity == 1){
            Toast.makeText(this,"You cannot have less than 1 coffees",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when order button is clicked.
     * And sends the order summary to via email
     */

    public void submitOrder(View view) {
       CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateCheckBox =(CheckBox)findViewById(R.id.chocolate_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
       boolean hasChocolate =chocolateCheckBox.isChecked();
        Log.v("MainActivity", "Has whipped cream: " + hasWhippedCream);

        EditText edtName = (EditText) findViewById(R.id.edt_Name);
        String name = edtName.getText().toString();
        double price = calculatePrice(hasWhippedCream,hasChocolate);
      displayMessage(createOrderSummary(price, hasWhippedCream,hasChocolate, name));
        //Intent to open email
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));//only emails appshould handle this
        intent.putExtra(Intent.EXTRA_SUBJECT,"Just Java order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT,createOrderSummary(price, hasWhippedCream,hasChocolate, name));
        //checks if theres an activity to handle activity
        if(intent.resolveActivity(getPackageManager())!= null){
            startActivity(intent);
        }

    }

    /**
     * This method displays the given quantity value on screen
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_txt);
        quantityTextView.setText("" + numberOfCoffees);
    }

    public void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
        // displayPrice(quantity * 5);
    }

    /**
     * This method calculates the price for the coffee orders
     */
    private double calculatePrice(boolean addWhippedCream,boolean addChocolate) {
        double pricePerCup = 10.00;

        //add R2 if user wants whipped cream
        if(addWhippedCream){
            pricePerCup = pricePerCup + 1.00;
        }
        //add R2 if user wants chocolate
        if(addChocolate){
            pricePerCup = pricePerCup + 2.00;
        }
        double total = quantity * pricePerCup;
        return total;
    }


    /**
     * Create a summary
     */
    private String createOrderSummary(double total, boolean addWhippedCream,boolean addChocolate, String Name) {
        String priceMessage = "\nName: " + Name;
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd chocolate? " + addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nAmount due: R" + total;
        priceMessage += "\nThank you!";
        return priceMessage;

    }


}
