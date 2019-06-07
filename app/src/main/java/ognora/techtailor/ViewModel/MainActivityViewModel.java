package ognora.techtailor.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;

import ognora.techtailor.Data.Api;
import ognora.techtailor.Model.CategoryModel;
import ognora.techtailor.Model.ProductModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityViewModel extends AndroidViewModel {

   public MutableLiveData<ArrayList<ProductModel>> arrayList;
    MutableLiveData<ArrayList<CategoryModel>> categoryarrayList;
    ArrayList<ProductModel> array = new ArrayList<>();


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<ArrayList<ProductModel>> getAllproduct(String categoryname){

        // error here

        if(arrayList==null)
        { arrayList = new MutableLiveData<>();
            //we will load it asynchronously from server in this method
            loadProducts(categoryname);
        } else if(arrayList.getValue().get(arrayList.getValue().size()).getGender()!=categoryname)
             loadProducts(categoryname);

        return arrayList;
    }

    private void loadProducts(final String categoryname) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<ArrayList<ProductModel>> call = api.getAllProduct();

        call.enqueue(new Callback<ArrayList<ProductModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductModel>> call, Response<ArrayList<ProductModel>> response) {

                for (ProductModel pro : response.body()) {
                        if(pro.getGender()==categoryname)
                            array.add(pro);
                }

                arrayList.setValue(array);
            }

            @Override
            public void onFailure(Call<ArrayList<ProductModel>> call, Throwable t) {
                Log.d("msg", t.getMessage());

            }
        });
    }



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
