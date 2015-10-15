package eu.elraro.ahorramas.eu.elraro.ahorramas.activity;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;

import eu.elraro.ahorramas.R;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        setupActionBar();

        ImageView menu_principal = (ImageView) findViewById(R.id.menu_principal);
        menu_principal.setImageBitmap(
                decodeSampledBitmapFromResource(getResources(), R.drawable.menu_principal, 150, 150));

        ImageView error_usuario = (ImageView) findViewById(R.id.error_usuario);
        error_usuario.setImageBitmap(
                decodeSampledBitmapFromResource(getResources(), R.drawable.error_usuario, 150, 150));

        ImageView login_usuario = (ImageView) findViewById(R.id.login_usuario);
        login_usuario.setImageBitmap(
                decodeSampledBitmapFromResource(getResources(), R.drawable.login_usuario, 150, 150));

        ImageView menu_comprar = (ImageView) findViewById(R.id.menu_comprar);
        menu_comprar.setImageBitmap(
                decodeSampledBitmapFromResource(getResources(), R.drawable.menu_comprar, 150, 150));

        ImageView finalizar_compra = (ImageView) findViewById(R.id.finalizar_compra);
        finalizar_compra.setImageBitmap(
                decodeSampledBitmapFromResource(getResources(), R.drawable.finalizar_compra, 150, 150));

        ImageView info_ayuda = (ImageView) findViewById(R.id.info_ayuda);
        info_ayuda.setImageBitmap(
                decodeSampledBitmapFromResource(getResources(), R.drawable.info_ayuda, 150, 150));
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

    // Make HOME icon dont destroy MainActivity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // http://stackoverflow.com/questions/477572/strange-out-of-memory-issue-while-loading-an-image-to-a-bitmap-object

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
