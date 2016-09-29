package com.example.codetribe.coffeeorder;

import android.annotation.TargetApi;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
                public void increment(View view){

                    quantity = quantity + 1;
                    displayQuantity(quantity);
                }
        /**
         * This method decreases the quantity when button minus is clicked
         */
                public void decrement(View view){

                    quantity = quantity -1;
                    displayQuantity(quantity);
                }

        /**This method is called when order button is clicked.*/

                public void submitOrder(View view){

                        int price = 15;
                        double total = calculatePrice(quantity,price);
                        displayMessage(createOrderSummary(total));

                }

        /**
         *This method displays the given quantity value on screen
         */
                private void displayQuantity(int numberOfCoffees){
                    TextView quantityTextView = (TextView) findViewById(R.id.quantity_txt);
                    quantityTextView.setText(""+ numberOfCoffees);
                }

                public void displayMessage(String message){
                    TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
                    orderSummaryTextView.setText(message);
                    // displayPrice(quantity * 5);
                }

    /**
     * This method calculates the price for the coffee orders
     */
                private double calculatePrice(int quantity,int pricePerCup){
                    double total = quantity * pricePerCup;
                    return  total;
                }


    /**
     * Create a summary
     */
                private String createOrderSummary(double total){
                    String priceMessage = "\nName: Dineo Savage";
                    priceMessage +=  "\nQuantity: "+ quantity ;
                    priceMessage += "\nAmount due: R" + total;
                    priceMessage +="\nThank you!";
                    return priceMessage;

                }





}
