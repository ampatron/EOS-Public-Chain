package one.block.eos.blocks.data

import one.block.eos.blocks.api.ChainService
import one.block.eos.blocks.models.Block
import one.block.eosiojava.models.rpcProvider.request.GetBlockRequest
import javax.inject.Inject

class BlocksRepository @Inject constructor(private val api: ChainService) {
    private suspend fun getChainHead() = api.getChainInfo().headBlockId

    suspend fun getBlock(blockId: String? = null): Block {
        return if (blockId.isNullOrBlank()) api.getBlock(GetBlockRequest(getChainHead()))
        else api.getBlock(GetBlockRequest(blockId))
    }
}