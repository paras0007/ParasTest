package com.parastest.activies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.parastest.R;
import com.parastest.adapters.CartListAdapter;
import com.parastest.api.ApiHelper;
import com.parastest.api.RetrofitClientInstance;
import com.parastest.model.Product;
import com.parastest.model.ProductListResponse;
import com.parastest.utils.Constant;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private ArrayList<Product> productList;
    private CartListAdapter cartListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControls();
    }

    private void initControls(){

        productList = new ArrayList<>();

        cartListAdapter = new CartListAdapter(MainActivity.this,productList);
        RecyclerView rcvCart = (RecyclerView) findViewById(R.id.rcvCart);
        rcvCart.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
        rcvCart.setAdapter(cartListAdapter);


        ApiHelper service = RetrofitClientInstance.getRetrofitInstance().create(ApiHelper.class);
        Call<ProductListResponse> call = service.getTrends(Constant.TOKEN);
        call.enqueue(new Callback<ProductListResponse>() {
            @Override
            public void onResponse(Call<ProductListResponse> call, Response<ProductListResponse> response) {

                if (response.body().products != null) {
                    Log.w("Data Response: ", "" + response.body().products.size());
                    productList = response.body().products;
                    Log.w("productList: ", "" + productList.size());
                    cartListAdapter.setData(productList);
                }

            }

            @Override
            public void onFailure(Call<ProductListResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
