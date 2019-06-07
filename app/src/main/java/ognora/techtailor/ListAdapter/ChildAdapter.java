package ognora.techtailor.ListAdapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

import ognora.techtailor.Model.ProductModel;
import ognora.techtailor.R;

public class ChildAdapter extends  RecyclerView.Adapter<ChildAdapter.MyViewHolder >{

    ArrayList<ProductModel> arrayList  ;
    Context context ;

    public ChildAdapter(ArrayList<ProductModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v;
        v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_childrecycler, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        //Uri imageUri = Uri.fromFile(new File(arrayList.get(i).getImage_url()));

        Glide.with(context)
                .load(arrayList.get(i).getImage_url())
                .into(myViewHolder.imageView);

        myViewHolder.textView.setText(arrayList.get(i).getService_type());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView ;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img);
            textView = itemView.findViewById(R.id.imgname);
        }
    }
}
