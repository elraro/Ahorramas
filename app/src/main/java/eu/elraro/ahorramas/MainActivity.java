package eu.elraro.ahorramas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import eu.elraro.ahorramas.eu.elraro.ahorramas.activity.BuyActivity;
import eu.elraro.ahorramas.eu.elraro.ahorramas.activity.HelpActivity;
import eu.elraro.ahorramas.eu.elraro.ahorramas.activity.LoginActivity;
import eu.elraro.ahorramas.eu.elraro.ahorramas.activity.UserPreferenceActivity;

public class MainActivity extends AppCompatActivity {

    // Request code for startActivityForResult
    private static final int REQUEST_GET_USER = 1;
    // Buttons
    private Button helpButton, userButton, buyButton;
    // User variable
    private boolean logged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GlobalVars g = (GlobalVars) getApplication();
        //logged = g.getLogged();

        // Debug
        logged = true;

        if (logged) {
            setContentView(R.layout.activity_main_menu_logged);
        } else {
            setContentView(R.layout.activity_main_menu);
        }

        generate();
    }

    private void generate() {
        helpButton = (Button) findViewById(R.id.helpButton);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent helpActivity = new Intent().setClass(
                        MainActivity.this, HelpActivity.class);
                startActivity(helpActivity);
            }
        });

        userButton = (Button) findViewById(R.id.userButton);
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!logged) {
                    Intent loginActivity = new Intent().setClass(
                            MainActivity.this, LoginActivity.class);
                    startActivityForResult(loginActivity, REQUEST_GET_USER);
                } else {
                    Intent userPreferenceActivity = new Intent().setClass(
                            MainActivity.this, UserPreferenceActivity.class);
                    startActivity(userPreferenceActivity);
                }
            }
        });

        buyButton = (Button) findViewById(R.id.buyButton);
        buyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!logged) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Error")
                            .setMessage("Necesitas entrar en tu cuenta de usuario antes de comprar")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent loginActivity = new Intent().setClass(
                                            MainActivity.this, LoginActivity.class);
                                    startActivityForResult(loginActivity, REQUEST_GET_USER);
                                }
                            })
                            .setIconAttribute(android.R.attr.alertDialogIcon)
                            .show();
                } else {
                    // User is logged
                    Intent buyActivity = new Intent().setClass(
                            MainActivity.this, BuyActivity.class);
                    startActivity(buyActivity);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == REQUEST_GET_USER) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                boolean logged = data.getBooleanExtra("logged", false);
                if (logged) {
                    this.logged = true;
                    GlobalVars g = (GlobalVars) getApplication();
                    g.setLogged(true);
                    setContentView(R.layout.activity_main_menu_logged);
                    generate();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        new android.support.v7.app.AlertDialog.Builder(this)
                .setMessage("¿Deseas salir?")
                .setCancelable(false)
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .setIconAttribute(android.R.attr.alertDialogIcon)
                .show();
    }
}
