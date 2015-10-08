package eu.elraro.ahorramas;


import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class BuyActivity extends AppCompatActivity {
    private ListView listView;
    private Button buyButton;

    private Map<Item,Integer> cart;
    private ArrayList<Item> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        cart = new HashMap<Item, Integer>();
        list = new ArrayList<Item>();

        list.add(new Item(R.drawable.cereales, "Cereales Frostis", 1, 1));
        list.add(new Item(R.drawable.filetes, "Filete de ternera", 3, 1));
        list.add(new Item(R.drawable.mermelada, "Mermelada de fresa", 2, 1));
        list.add(new Item(R.drawable.pan_bimbo, "Pan Bimbo", 1.5, 1));

        //Sorting price
        Collections.sort(list, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                if (item1.getPrice() < item2.getPrice()) return -1;
                if (item1.getPrice() > item2.getPrice()) return 1;
                return 0;
            }
        });

        setupActionBar();

        CustomBuyList adapter = new CustomBuyList(BuyActivity.this, list);
        listView = (ListView) findViewById(R.id.grid);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(BuyActivity.this, "Has comprado " + list.get(position).getName(), Toast.LENGTH_SHORT).show();
                if (cart.containsKey(list.get(position))) {
                    cart.put(list.get(position), cart.get(list.get(position)) + 1);
                } else {
                    cart.put(list.get(position), 1);
                }
            }
        });

        buyButton = (Button) findViewById(R.id.buyButton);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyActivity.this, FinishBuyActivity.class);
                Globals g = (Globals)getApplication();
                g.setCart(cart);
                startActivity(intent);
            }
        });

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

    // Make HOME icon dont destroy MainMenuActivity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Si no realizas la compra perderás todo lo añadido a la cesta")
                .setCancelable(false)
                .setPositiveButton("Sí, salir", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        BuyActivity.this.finish();
                    }
                })
                .setNegativeButton("No, continuar comprando", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .setIconAttribute(android.R.attr.alertDialogIcon)
                .show();
    }

}