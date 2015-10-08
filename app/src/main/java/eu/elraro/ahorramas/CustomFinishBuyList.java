package eu.elraro.ahorramas;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomFinishBuyList extends BaseAdapter{
    private Activity activity;
    private final ArrayList<Item> cart;

    public CustomFinishBuyList(Activity activity, ArrayList<Item> cart) {
        this.activity = activity;
        this.cart = cart;
    }

    @Override
    public int getCount() {
        return cart.size();
    }

    @Override
    public Object getItem(int position) {
        return cart.get(position);
    }

    @Override
    public long getItemId(int position) {
        return cart.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;


        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_finish_buy, null);
        }

        Item item = cart.get(position);

        ImageView photo = (ImageView) view.findViewById(R.id.imageItem);
        photo.setImageDrawable(view.getResources().getDrawable(item.getPhoto()));

        TextView name = (TextView) view.findViewById(R.id.nameItem);
        name.setText(item.getName());

        TextView totalItems = (TextView) view.findViewById(R.id.totalItems);
        totalItems.setText("" + item.getQuantity() + " unidades");

        TextView totalPrice = (TextView) view.findViewById(R.id.totalPriceItems);
        totalPrice.setText("" + item.getPrice()*item.getQuantity() + " €");

        return view;
    }
}
