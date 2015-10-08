package eu.elraro.ahorramas;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

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

        Globals g = (Globals)getApplication();
        Map<Item,Integer> cart = g.getCart();

        ArrayList<Item> finalCart = new ArrayList<Item>();

        for (Item key : cart.keySet()) {
            finalCart.add(new Item(key.getPhoto(), key.getName(), key.getPrice(), cart.get(key)));
            countTotalItems = countTotalItems + cart.get(key);
            countTotalPrince = countTotalPrince + key.getPrice()*cart.get(key);
        }

        cartListView = (ListView) findViewById(R.id.cartListView);
        CustomFinishBuyList adapter = new CustomFinishBuyList(FinishBuyActivity.this, finalCart);
        cartListView.setAdapter(adapter);

        totalItems = (TextView) findViewById(R.id.totalItemsTextView);
        totalItems.setText("Objetos: " + countTotalItems);
        totalPrice = (TextView) findViewById(R.id.totalPriceTextView);
        totalPrice.setText("Total: " + countTotalPrince + " €");

        Toast.makeText(FinishBuyActivity.this, "Gracias por su compra", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onBackPressed() {
        finish();
    }
}
