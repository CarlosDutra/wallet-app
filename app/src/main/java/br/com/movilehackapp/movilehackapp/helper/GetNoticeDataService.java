package br.com.movilehackapp.movilehackapp.helper;

import java.util.List;

import br.com.movilehackapp.movilehackapp.model.Compra;
import br.com.movilehackapp.movilehackapp.model.Produto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Path;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetNoticeDataService {

    @GET("products")
    Call<List<Produto>> listaProdutos();

    @POST("products/{id}/orders")
    Call<Compra> criarCompra(@Path("id") String id, @Body Compra compra);

}
