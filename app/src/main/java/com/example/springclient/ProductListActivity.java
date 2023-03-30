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
import android.widget.ListView;
import android.widget.Toast;

import com.example.springclient.adapter.ProductAdapter;
import com.example.springclient.model.Product;
import com.example.springclient.retrofit.ProductAPI;
import com.example.springclient.retrofit.RetrofitService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListActivity extends AppCompatActivity {


    ListView listProducts;
    List<Product> products;
    Product product= new Product();
    Intent intent = getIntent();


    ActivityResultLauncher<Intent> itemClickLauncher,AddPersonLauncher;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);


        FloatingActionButton floatingActionButton = findViewById(R.id.productList_fab);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        });

        listProducts = findViewById((R.id.lstProducts));
        //to get scrollbar in listview if the list doesn't fit on screen.
        Runnable fitsOnScreen = new Runnable() {
            @Override
            public void run() {
                int last = listProducts.getLastVisiblePosition();
                if(last == listProducts.getCount() - 1 && listProducts.getChildAt(last).getBottom()
                        <= listProducts.getHeight()) {
                    listProducts.setScrollContainer(false);
                }
                else {
                    listProducts.setScrollContainer(true);
                }
            }
        };
         listProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                product=products.get(i); //get the contents of products at position i
                int pnoClick = products.get(i).pNo;
                Log.d("Product: ", product.pName);
                Log.d("Product Type: ", product.pType);
                Log.d("Product Price: ",String.valueOf( product.pPrice));

                intent = new Intent(ProductListActivity.this,EditDeleteActivity.class);
                intent.putExtra("product",product);
               startActivity(intent);
            }
        });

        itemClickLauncher= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result)
                    {
                        if(result.getResultCode() == Activity.RESULT_OK)
                        {
                            startActivity(intent);

                        }
                    }
                });



        //For the connection with api
        ProductAPI productAPI= RetrofitService.buildService((ProductAPI.class));


        //GetProductById
       /* Call<Product> request= productAPI.getPersonById(1);
        request.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response)
            {
                //Product product= response.body();
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });*/

        //Get All Products
        Call<List<Product>> requestForAllProducts = productAPI.getAllProducts();
        requestForAllProducts.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = response.body();


                ProductAdapter productAdapter = new ProductAdapter(products, ProductListActivity.this);
                listProducts.setAdapter(productAdapter);

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("Products:", t.getMessage().toString());
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        LoadProducts();
    }


        //load all products
    private void LoadProducts() {
        RetrofitService retrofitService = new RetrofitService();
        ProductAPI productApi =  retrofitService.buildService((ProductAPI.class));
        productApi.getAllProducts()
                .enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        products =response.body();
                        ProductAdapter productAdapter= new ProductAdapter( products, ProductListActivity.this);
                        listProducts.setAdapter(productAdapter);

                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {
                        Toast.makeText(ProductListActivity.this, "Failed to load Products", Toast.LENGTH_SHORT).show();

                    }
                });
    }




}