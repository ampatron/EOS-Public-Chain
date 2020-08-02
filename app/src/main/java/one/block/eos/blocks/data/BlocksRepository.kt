package one.block.eos.blocks.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import one.block.eos.blocks.api.Chain
import one.block.eos.blocks.models.Block
import one.block.eosiojava.models.rpcProvider.request.GetBlockRequest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BlocksRepository {
    val retrofit: Retrofit
    val api: Chain

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        retrofit = Retrofit.Builder()
            .baseUrl("https://eos.greymass.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        api = retrofit.create(Chain::class.java)
    }

    suspend fun getChainHead() = api.getChainInfo().headBlockId
    suspend fun getBlock(blockId: String? = null): Block {
        return if (blockId.isNullOrBlank()) api.getBlock(GetBlockRequest(getChainHead()))
        else api.getBlock(GetBlockRequest(blockId))
    }
}