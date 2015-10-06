package eu.elraro.ahorramas;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class FinishBuyActivity extends AppCompatActivity {

    private ArrayList<String> cart;
    private ListView cartListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_buy);

        Bundle b = getIntent().getExtras();
        cart = b.getStringArrayList("cart");

        cartListView = (ListView) findViewById(R.id.cartListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, cart);
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
