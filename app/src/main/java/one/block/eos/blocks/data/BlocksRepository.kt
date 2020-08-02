package one.block.eos.blocks.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import one.block.eos.blocks.api.Chain
import one.block.eos.blocks.models.Block
import one.block.eos.blocks.models.TransactionReceipt
import one.block.eosiojava.models.rpcProvider.request.GetBlockRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BlocksRepository {
    lateinit var retrofit: Retrofit
    fun getBlocks(): MutableLiveData<List<Block>> {
        val mutableLiveData = MutableLiveData<List<Block>>()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        retrofit = Retrofit.Builder()
            .baseUrl("https://eos.greymass.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        val api = retrofit.create(Chain::class.java)

        val callback = object: Callback<Block> {
            override fun onFailure(call: Call<Block>, t: Throwable) {
                Log.d("testing", "onfailure" +t.localizedMessage)


            }

            override fun onResponse(
                call: Call<Block>,
                response: Response<Block>
            ) {
                Log.d("testing", "responded" +response.isSuccessful)
                response.body()?.let {
                    mutableLiveData.postValue(listOf(it))
                }


            }
        }
        api.getBlock(GetBlockRequest("134376810")).enqueue(callback)

        return mutableLiveData
    }

    fun mockBlock(): Block {
        return Block(blockNum = 1234, id = "12345", transactions = listOf(
            TransactionReceipt()
        ))
    }
}