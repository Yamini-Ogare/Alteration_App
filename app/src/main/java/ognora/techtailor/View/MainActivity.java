package ognora.techtailor.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import ognora.techtailor.ListAdapter.ChildAdapter;
import ognora.techtailor.ListAdapter.ParentAdapter;
import ognora.techtailor.Model.CategoryModel;
import ognora.techtailor.Model.ProductModel;
import ognora.techtailor.R;
import ognora.techtailor.ViewModel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ParentAdapter adapter;
  //  ChildAdapter childAdapter;
    ArrayList<CategoryModel> arrayList = new ArrayList<>();
//    ArrayList<ProductModel> productarray = new ArrayList<>();
    public MainActivityViewModel viewModel ;
    RecyclerView recyclerView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.parentRecycler);
        recyclerView2 = findViewById(R.id.childRecycler);

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter =new ParentAdapter(arrayList, MainActivity.this);
        recyclerView.setAdapter(adapter);

        viewModel.getAllcategories().observe(this, new Observer<ArrayList<CategoryModel>>() {
            @Override
            public void onChanged(@Nullable ArrayList<CategoryModel> categoryModels) {
                arrayList.addAll(categoryModels);
                adapter.notifyDataSetChanged();
            }
        });


        }


    }

