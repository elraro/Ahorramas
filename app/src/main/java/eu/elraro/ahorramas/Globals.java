package eu.elraro.ahorramas;

import android.app.Application;

import java.util.Map;

public class Globals extends Application {
    private Map<Item,Integer> cart;

    public Map<Item, Integer> getCart(){
        return this.cart;
    }

    public void setCart(Map<Item, Integer> cart){
        this.cart = cart;
    }
}
