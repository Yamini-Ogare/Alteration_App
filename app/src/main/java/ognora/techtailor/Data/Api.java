package ognora.techtailor.Data;

import java.util.ArrayList;

import ognora.techtailor.Model.CategoryModel;
import ognora.techtailor.Model.ProductModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api  {

  String BASE_URL = "https://tech-tailor.herokuapp.com/";

  @GET("product_by_category/{gender}")
  Call<ArrayList<ProductModel>> getAllProduct(@Path("gender") String gender);

  @GET("get_category")
  Call<ArrayList<CategoryModel>> getAllCategory();
}
