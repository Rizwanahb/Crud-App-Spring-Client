package com.example.springclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.springclient.R;
import com.example.springclient.model.Product;

import java.util.List;

public class ProductAdapter extends BaseAdapter {

     List<Product> products;
     Context context;

    //constructor
    public ProductAdapter(List<Product> products,Context context){
        this.products=products;
        this.context= context;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View v =inflater.inflate(R.layout.product_list, null);

        Product product= products.get(position);

        TextView txtNavn= v.findViewById(R.id.txtProductNavn);
        txtNavn.setText(product.getpName());
        TextView txtType=v.findViewById(R.id.txtType);
        txtType.setText(product.getpType());
        TextView txtPrice=v.findViewById(R.id.txtPrice);
        String str = String.valueOf(product.getpPrice());
        txtPrice.setText(str+" kr.");


        return v;
    }
}
