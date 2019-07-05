package com.vinay.expandablelist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import com.vinay.expandablelist.Model.DataModel;
import com.vinay.expandablelist.Model.MyData;
import com.vinay.expandablelist.R;


public class Anemia extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private static ArrayList<DataModel> dataModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        //initialize
        recyclerView=findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        dataModels=new ArrayList<DataModel>();
        for(int i=0;i< MyData.titleArray.length;i++)
        {
            dataModels.add(new com.vinay.expandablelist.Model.DataModel(
                    MyData.titleArray[i],
                    MyData.Anemia[i],
                    MyData.id[i]
            ));
        }
        //attach to the adapter
        adapter=new com.vinay.expandablelist.Adapter.CustomAdapter(dataModels);
        recyclerView.setAdapter(adapter);
    }
}