package ognora.techtailor.Data;

import java.util.ArrayList;

import ognora.techtailor.Model.CategoryModel;
import ognora.techtailor.Model.ProductModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api  {

  String BASE_URL = "https://tech-tailor.herokuapp.com/";

  @GET("products")
  Call<ArrayList<ProductModel>> getAllProduct();

  @GET("get_category")
  Call<ArrayList<CategoryModel>> getAllCategory();
}
