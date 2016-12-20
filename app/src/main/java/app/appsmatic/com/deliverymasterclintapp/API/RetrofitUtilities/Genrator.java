package app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities;

import app.appsmatic.com.deliverymasterclintapp.URLS.BaseURL;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mido PC on 12/4/2016.
 */
public class Genrator {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BaseURL.WEBSERVICE)
                    .addConverterFactory(GsonConverterFactory.create());
    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
