package eu.elraro.ahorramas.eu.elraro.ahorramas.activity;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

import eu.elraro.ahorramas.GlobalVars;
import eu.elraro.ahorramas.Item;
import eu.elraro.ahorramas.R;
import eu.elraro.ahorramas.eu.elraro.ahorramas.adapter.AdapterFinishBuyList;

public class FinishBuyActivity extends AppCompatActivity {

    private ListView cartListView;
    private TextView totalItems;
    private TextView totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_buy);

        int countTotalItems = 0;
        double countTotalPrince = 0;

        // Get the global var cart
        GlobalVars g = (GlobalVars) getApplication();
        Map<Item, Integer> cart = g.getCart();

        ArrayList<Item> finalCart = new ArrayList<Item>();

        // Read order preference
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = prefs.getString("user_name", "Prueba");

        // Convert HashMap to ArrayList
        for (Item key : cart.keySet()) {
            finalCart.add(new Item(key.getPhoto(), key.getName(), key.getPrice(), cart.get(key), key.getValuation()));
            countTotalItems = countTotalItems + cart.get(key);
            countTotalPrince = countTotalPrince + key.getPrice() * cart.get(key);
        }

        cartListView = (ListView) findViewById(R.id.cartListView);
        AdapterFinishBuyList adapter = new AdapterFinishBuyList(FinishBuyActivity.this, finalCart);
        cartListView.setAdapter(adapter);

        totalItems = (TextView) findViewById(R.id.totalItemsTextView);
        totalItems.setText("Objetos: " + countTotalItems);
        totalPrice = (TextView) findViewById(R.id.totalPriceTextView);
        totalPrice.setText("Total: " + countTotalPrince + " €");

        Toast.makeText(FinishBuyActivity.this, "Gracias por su compra " + userName, Toast.LENGTH_LONG).show();
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     * https://developer.android.com/training/basics/actionbar/setting-up.html
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    // We kill the activity if the user press back
    @Override
    public void onBackPressed() {
        finish();
    }
}
