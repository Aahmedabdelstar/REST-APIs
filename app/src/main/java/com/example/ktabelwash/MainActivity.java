package com.example.ktabelwash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView postTitle = findViewById(R.id.post_title_tv);

        //Post post = new Post(011,"My Complate Name","ahmed abdelstar") ;
        HashMap<Object,Object> map = new HashMap<Object, Object>();
        map.put("title","My Complate Name");
        map.put("body","Ahmed abdelstar");
        map.put("userId",11);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        /// tall Retrofit to fill interface by Data (converter)
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        // Call

        Call<Post> call = apiInterface.storePost(map);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                postTitle.setText(response.body().getBody());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                postTitle.setText(t.getMessage());
            }
        });


    }
}
