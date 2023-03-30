package com.example.springclient;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.springclient.model.Product;
import com.example.springclient.model.ProductResponse;
import com.example.springclient.retrofit.ProductAPI;
import com.example.springclient.retrofit.RetrofitService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ListView listProducts;
    List<Product> products;
    Product product= new Product();
    Intent intent = getIntent();

    Spinner spnProductType;
    int productType = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
    }


    private void initializeComponents(){
        TextInputEditText inputEditName = findViewById(R.id.form_textFieldName);
        TextInputEditText inputEditPrice = findViewById(R.id.form_textFieldPrice);

        MaterialButton buttonSave = findViewById((R.id.form_buttonSave));
        MaterialButton buttonGetAll = findViewById((R.id.form_buttonGetAll));

        //fil the adapter with Product type values
        ArrayAdapter<ProductType> ProductTypeAdapter =
                new ArrayAdapter<ProductType>(this, android.R.layout.simple_spinner_item, ProductType.values());


        spnProductType = findViewById(R.id.spnProductType);
        spnProductType.setAdapter(ProductTypeAdapter);  //fill the spinner with adapter




        RetrofitService retrofitService = new RetrofitService();
        ProductAPI productAPI= RetrofitService.buildService(ProductAPI.class);

        buttonSave.setOnClickListener(view -> {
            String name = String.valueOf(inputEditName.getText());
            Float price = Float.valueOf(inputEditPrice.getText().toString());

            Product product = new Product();
            product.setpName(name);
            productType = (spnProductType.getSelectedItemPosition());
            String type;

            if (productType== 0) {
                product.setpType("Toys");
            }
            if (productType== 1) {
                product.setpType("Medicine");
            }

            if (productType== 2) {
               product.setpType("Computer_Accessories");;
            }
            if (productType== 3) {
                product.setpType("Books");;
            }

            product.setpPrice(price);


            productAPI.save(product)
                    .enqueue((new Callback<ProductResponse>() {
                        @Override
                        public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                            ProductResponse respo=response.body();
                            if(respo.getStatus().equals("true")) {
                                Toast.makeText(MainActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();

                            }
                            else Toast.makeText(MainActivity.this, "Not saved", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<ProductResponse> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Cannot be saved! ", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"Error is found",t);
                        }
                    }));


        });



        buttonGetAll.setOnClickListener(view -> {
            Intent productListIntent = new Intent(
                    getApplicationContext(),ProductListActivity.class
            );
            startActivity(productListIntent);
        });




}}