package ognora.techtailor.ListAdapter;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;

import ognora.techtailor.Model.CategoryModel;
import ognora.techtailor.Model.ProductModel;
import ognora.techtailor.R;
import ognora.techtailor.View.MainActivity;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.MyViewHolder > {

    ArrayList<CategoryModel> arrayList ;
    ArrayList<ProductModel> productarray= new ArrayList<>();
   // ArrayList<ProductModel> ar = new ArrayList<>();
    Context context;
    ChildAdapter adapter;

    public ParentAdapter(ArrayList<CategoryModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parentrecycler, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.categoryname.setText(arrayList.get(i).getName());


        //((MainActivity)context).viewModel.getAllproduct(arrayList.get(i).getName());

        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false);
        myViewHolder.childrecycler.setLayoutManager(layoutManager);
        adapter = new ChildAdapter(productarray, context);
        myViewHolder.childrecycler.setAdapter(adapter);

        ((MainActivity)context).viewModel.getAllproduct(arrayList.get(i).getName()).observe((MainActivity) context, new Observer<ArrayList<ProductModel>>() {
            @Override
            public void onChanged(@Nullable ArrayList<ProductModel> productModels) {
                productarray.addAll(productModels);
                adapter.notifyDataSetChanged();
            }
        });



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView categoryname;
        RecyclerView childrecycler;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryname = itemView.findViewById(R.id.categoryname);
            childrecycler = itemView.findViewById(R.id.childRecycler);
        }
    }
}
