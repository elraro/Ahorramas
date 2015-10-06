package eu.elraro.ahorramas;


import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class BuyActivity extends AppCompatActivity {
    private GridView grid;
    private Button buyButton;
    private String[] food = {
            "Plátano",
            "Filete ternera",
            "Pan Bimbo",
            "Mermelada",
            "Celeales",
            "Plátano",
            "Filete ternera",
            "Pan Bimbo",
            "Mermelada",
            "Celeales",
            "Plátano",
            "Filete ternera",
            "Pan Bimbo",
            "Mermelada",
            "Celeales",
            "Plátano",
            "Filete ternera",
            "Pan Bimbo",
            "Mermelada",
            "Celeales",
            "Plátano",
            "Filete ternera",
            "Pan Bimbo",
            "Mermelada",
            "Celeales",
            "Plátano",
            "Filete ternera",
            "Pan Bimbo",
            "Mermelada",
            "Celeales",
            "Plátano",
            "Filete ternera",
            "Pan Bimbo",
            "Mermelada",
            "Celeales",
            "Plátano",
            "Filete ternera",
            "Pan Bimbo",
            "Mermelada",
            "Celeales",
            "Plátano",
            "Filete ternera",
            "Pan Bimbo",
            "Mermelada",
            "Celeales",
            "Plátano",
            "Filete ternera",
            "Pan Bimbo",
            "Mermelada",
            "Celeales"


    } ;
    private int[] foodImageId = {
            R.drawable.platanos,
            R.drawable.filetes,
            R.drawable.pan_bimbo,
            R.drawable.mermelada,
            R.drawable.cereales,
            R.drawable.platanos,
            R.drawable.filetes,
            R.drawable.pan_bimbo,
            R.drawable.mermelada,
            R.drawable.cereales,
            R.drawable.platanos,
            R.drawable.filetes,
            R.drawable.pan_bimbo,
            R.drawable.mermelada,
            R.drawable.cereales,
            R.drawable.platanos,
            R.drawable.filetes,
            R.drawable.pan_bimbo,
            R.drawable.mermelada,
            R.drawable.cereales,
            R.drawable.platanos,
            R.drawable.filetes,
            R.drawable.pan_bimbo,
            R.drawable.mermelada,
            R.drawable.cereales,
            R.drawable.platanos,
            R.drawable.filetes,
            R.drawable.pan_bimbo,
            R.drawable.mermelada,
            R.drawable.cereales,
            R.drawable.platanos,
            R.drawable.filetes,
            R.drawable.pan_bimbo,
            R.drawable.mermelada,
            R.drawable.cereales,
            R.drawable.platanos,
            R.drawable.filetes,
            R.drawable.pan_bimbo,
            R.drawable.mermelada,
            R.drawable.cereales,
            R.drawable.platanos,
            R.drawable.filetes,
            R.drawable.pan_bimbo,
            R.drawable.mermelada,
            R.drawable.cereales,
            R.drawable.platanos,
            R.drawable.filetes,
            R.drawable.pan_bimbo,
            R.drawable.mermelada,
            R.drawable.cereales

    };

    private ArrayList<String> cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        cart = new ArrayList<String>();

        setupActionBar();

        CustomGrid adapter = new CustomGrid(BuyActivity.this, food, foodImageId);
        grid=(GridView)findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(BuyActivity.this, "Has comprado " + food[+position], Toast.LENGTH_SHORT).show();
                cart.add(food[+position]);
            }
        });

        buyButton = (Button) findViewById(R.id.buyButton);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyActivity.this, FinishBuyActivity.class);
                Bundle b = new Bundle();
                b.putStringArrayList("cart", cart);
                intent.putExtras(b);
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