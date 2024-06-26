import android.content.Context
import com.tecsup.prototipo_proyecto.network.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient(private val context: Context) {
    private val baseUrl = "https://jellyfish-app-olbh8.ondigitalocean.app/api/"

    val retrofit: ApiService by lazy {
        val client = OkHttpClient.Builder().apply {
            // Solo agrega el interceptor si el token no está vacío
            if (getAuthToken().isNotEmpty()) {
                addInterceptor { chain ->
                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                        .header("Authorization", "Token ${getAuthToken()}")
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }
            }
        }.build()

        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    private fun getAuthToken(): String {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("auth_token", "") ?: ""
    }
}
