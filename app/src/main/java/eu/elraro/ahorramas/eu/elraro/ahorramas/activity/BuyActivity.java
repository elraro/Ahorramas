package eu.elraro.ahorramas.eu.elraro.ahorramas.activity;


import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

import eu.elraro.ahorramas.GlobalVars;
import eu.elraro.ahorramas.Item;
import eu.elraro.ahorramas.R;
import eu.elraro.ahorramas.eu.elraro.ahorramas.adapter.AdapterBuyList;

public class BuyActivity extends AppCompatActivity {

    private ListView listView;
    private Button buyButton;
    private Map<Item, Integer> cart;
    private ArrayList<Item> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        // List of buyed items
        cart = new HashMap<Item, Integer>();

        // List of items
        list = new ArrayList<Item>();

        // Add the items
        list.add(new Item(R.drawable.cereales, "Cereales Frostis", 1, 1, 5.7));
        list.add(new Item(R.drawable.filetes, "Filete de ternera", 3, 1, 7.8));
        list.add(new Item(R.drawable.mermelada, "Mermelada de fresa", 2, 1, 3.2));
        list.add(new Item(R.drawable.pan_bimbo, "Pan Bimbo", 1.5, 1, 8.8));
        list.add(new Item(R.drawable.patatas_fritas, "Bolsa Patatas Fritas", 1.2, 1, 0.1));
        list.add(new Item(R.drawable.portatil, "Ordenador Portatil", 754, 1, 6.9));
        list.add(new Item(R.drawable.empanadillas, "Empanadilla", 2.5, 1, 5.2));
        list.add(new Item(R.drawable.espuma_afeitar, "Espuma de afeitar (2x1)", 3.2, 2, 7.1));
        list.add(new Item(R.drawable.maquinilla_afeitar, "Maquinilla de afeitar", 1.3, 1, 4.7));
        list.add(new Item(R.drawable.pelota_playa, "Pelota de playa", 0.9, 1, 5.0));
        list.add(new Item(R.drawable.pizza, "Pizza", 3.3, 1, 5.2));
        list.add(new Item(R.drawable.tele_plasma, "Televisor", 1000, 1, 2.8));
        list.add(new Item(R.drawable.platanos, "Platanos", 1, 1, 5.9));


        // Read order preference
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String orderSettings = prefs.getString("show_order", "0");

        if (orderSettings.equals("0")) {
            // Sorting price
            Collections.sort(list, new Comparator<Item>() {
                @Override
                public int compare(Item item1, Item item2) {
                    if (item1.getPrice() < item2.getPrice()) return -1;
                    if (item1.getPrice() > item2.getPrice()) return 1;
                    return 0;
                }
            });
        } else {
            // Sorting valuation
            Collections.sort(list, new Comparator<Item>() {
                @Override
                public int compare(Item item1, Item item2) {
                    if (item1.getValuation() < item2.getValuation()) return -1;
                    if (item1.getValuation() > item2.getValuation()) return 1;
                    return 0;
                }
            });
        }

        // Setup ActionBar
        setupActionBar();

        // Create the AdapterBuyList adapter
        AdapterBuyList adapter = new AdapterBuyList(BuyActivity.this, list);
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
                GlobalVars g = (GlobalVars) getApplication();
                g.setCart(cart);
                startActivity(intent);
                finish(); // We can't go back
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

    // Make HOME icon don't destroy MainActivity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Exit dialog
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