package one.block.eos.blocks.api

import one.block.eos.blocks.models.Account
import one.block.eos.blocks.models.Block
import one.block.eos.blocks.models.ChainInfo
import one.block.eosiojava.models.rpcProvider.request.GetBlockRequest
import one.block.eosiojava.models.rpcProvider.response.GetBlockResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ChainService {
    @Headers("Content-Type: application/json")
    @GET("/")
    suspend fun getChainInfo(): ChainInfo

    @Headers("Content-Type: application/json")
    @POST("/v1/chain/get_account")
    fun getAccount(@Body accountRequest: GetAccountRequest): Call<Account>

    @Headers("Content-Type: application/json")
    @POST("/v1/chain/get_block")
    suspend fun getBlock(@Body blockRequest: GetBlockRequest): Block
}