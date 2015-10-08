package eu.elraro.ahorramas;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Map;

public class FinishBuyActivity extends AppCompatActivity {

    private ListView cartListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_buy);

        Globals g = (Globals)getApplication();
        Map<Item,Integer> cart = g.getCart();

        ArrayList<Item> finalCart = new ArrayList<Item>();

        for (Item key : cart.keySet()) {
            finalCart.add(new Item(key.getPhoto(), key.getName(), key.getPrice(), key.getQuantity()));
        }

        cartListView = (ListView) findViewById(R.id.cartListView);
        CustomFinishBuyList adapter = new CustomFinishBuyList(FinishBuyActivity.this, finalCart);
        cartListView.setAdapter(adapter);
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
}
