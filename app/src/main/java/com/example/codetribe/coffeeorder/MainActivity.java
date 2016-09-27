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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     *This method is called when order button is clicked.
     */
    public void submitOrder(View view){
        display(2);
        displayPrice(2 * 5);
    }

    /**
     *This method displays the given quantity value on screen
     */
    private void display(int number){
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_txt);
        quantityTextView.setText(""+ number);
    }

    /**
     * This method displays the given quantity value on the screen
     */
    @TargetApi(Build.VERSION_CODES.N)
    private void displayPrice(int number){

        TextView priceTextView = (TextView)findViewById(R.id.price_txt);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));

    }
}
