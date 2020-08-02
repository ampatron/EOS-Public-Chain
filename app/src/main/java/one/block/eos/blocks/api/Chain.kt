package one.block.eos.blocks.api

import one.block.eos.blocks.models.Account
import one.block.eos.blocks.models.Block
import one.block.eosiojava.models.rpcProvider.request.GetBlockRequest
import one.block.eosiojava.models.rpcProvider.response.GetBlockResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface Chain {
    @Headers("Content-Type: application/json")
    @POST("/v1/chain/get_account")
    fun getAccount(@Body accountRequest: GetAccountRequest): Call<Account>

    @Headers("Content-Type: application/json")
    @POST("/v1/chain/get_block")
    fun getBlock(@Body blockRequest: GetBlockRequest): Call<Block>
}