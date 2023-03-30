package com.example.springclient;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.springclient.model.Product;
import com.example.springclient.model.ProductResponse;
import com.example.springclient.retrofit.ProductAPI;
import com.example.springclient.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditDeleteActivity extends AppCompatActivity  {

    EditText txtName, txtPrice,txtType;
    Button btnCancel, btnUpdate, btnDelete;
   // Spinner spnProductType;
    Intent intent = getIntent();

    Product product = new Product();
    int productType = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdelete);

        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
        txtType = findViewById(R.id.txtType);

        btnCancel = findViewById(R.id.btnCancel);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);


        //listen for incoming data

        product = (Product) getIntent().getSerializableExtra("product");

        Log.i("product:", product.toString());

        //Setting the ArrayAdapter data on the Spinner
    //    spnProductType = findViewById(R.id.spnProductType);
     //  productType = (spnProductType.getSelectedItemPosition());



        //fill in the form
        txtName.setText(product.pName);
        txtType.setText(product.pType);
        txtPrice.setText(String.valueOf(product.pPrice));








        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }

        });



        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(EditDeleteActivity.this);
                alert.setTitle("Delete Product?");
                alert.setMessage("Are you sure you want to delete this Product?");

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        int id = product.pNo;
                        deleteProduct(id);
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });

                alert.show();
            }
        });

        //update the record
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.pName = txtName.getText().toString();
                if (product.pName.matches("")) {
                    Toast.makeText(EditDeleteActivity.this, "You did not enter product name", Toast.LENGTH_SHORT).show();
                    return;
                }

                product.pType= txtType.getText().toString();
                if (product.pType.matches("")) {
                    Toast.makeText(EditDeleteActivity.this, "You did not enter product name", Toast.LENGTH_SHORT).show();
                    return;
                }


                product.pPrice = Float.parseFloat(txtPrice.getText().toString());
                String str = String.valueOf(product.pPrice);
                if (str.matches("")) {
                    Toast.makeText(EditDeleteActivity.this, "You did not enter persons telephone number", Toast.LENGTH_SHORT).show();
                    return;
                }

                updateProduct(product);
            }
        });
    }

        private void updateProduct(Product product) {

        ProductAPI productAPI = RetrofitService.buildService(ProductAPI.class);
        productAPI.updateProduct(product)
                    .enqueue(new Callback<ProductResponse>() {
                    @Override
                    public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response)
                    {
                    ProductResponse respo = response.body();
                        if(respo.getStatus().equals("true")) {
                               Toast.makeText(EditDeleteActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                        }
                        Log.d("updated: ", product.pName);
                        finish();

                    }

                    @Override
                    public void onFailure(Call<ProductResponse> call, Throwable t) {
                        Toast.makeText(EditDeleteActivity.this, "Cannot be updated! ", Toast.LENGTH_SHORT).show();
                    }
                    });


        //delete the record

    }


    private void deleteProduct(int pNo) {
        ProductAPI productAPI = RetrofitService.buildService(ProductAPI.class);
        Call<ProductResponse> request = productAPI.deleteProduct(pNo);

        request.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {


                    finish();


            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {


            }
        });
    }



}