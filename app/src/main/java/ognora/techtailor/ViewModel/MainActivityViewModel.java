package ognora.techtailor.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import ognora.techtailor.Data.Api;
import ognora.techtailor.Model.CategoryModel;
import ognora.techtailor.Model.ProductModel;
import ognora.techtailor.View.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityViewModel extends AndroidViewModel {

     MutableLiveData<ArrayList<ProductModel>> arrayList;
     MutableLiveData<ArrayList<CategoryModel>> categoryarrayList;
     Context context;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = getApplication().getBaseContext();
    }


    public LiveData<ArrayList<ProductModel>> getAllproduct(String categoryname){

        // error here

        if(arrayList==null)
        { arrayList = new MutableLiveData<>();
            //we will load it asynchronously from server in this method
            loadProducts(categoryname);
        }

        return arrayList;
    }

    private void loadProducts(final String categoryname) {

     //   ((MainActivity)context).progressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<ArrayList<ProductModel>> call = api.getAllProduct(categoryname);

        call.enqueue(new Callback<ArrayList<ProductModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductModel>> call, Response<ArrayList<ProductModel>> response) {
                 arrayList.setValue(response.body());
            //    ((MainActivity)context).progressBar.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<ArrayList<ProductModel>> call, Throwable t) {
                Log.d("msg", t.getMessage());

            }
        });
    }


    // to get the number of categories


    public LiveData<ArrayList<CategoryModel>> getAllcategories(){

        if(categoryarrayList==null)
        { categoryarrayList = new MutableLiveData<>();
            //we will load it asynchronously from server in this method
            loadCategories();
        }


        return categoryarrayList;
    }

    private void loadCategories() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<ArrayList<CategoryModel>> call = api.getAllCategory();

        call.enqueue(new Callback<ArrayList<CategoryModel>>() {
            @Override
            public void onResponse(Call<ArrayList<CategoryModel>> call, Response<ArrayList<CategoryModel>> response) {

                categoryarrayList.setValue(response.body());

            }

            @Override
            public void onFailure(Call<ArrayList<CategoryModel>> call, Throwable t) {

                Log.d("msg", t.getMessage());

            }
        });

    }

}
