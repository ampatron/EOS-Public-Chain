package one.block.eos.blocks.data

import one.block.eos.blocks.Block
import one.block.eos.blocks.Transaction

class BlocksRepository {
    fun getBlocks(): List<Block> {
        return listOf(mockBlock())
    }

    fun mockBlock(): Block {
        return Block("1234", 123, "producer 1", "56789", listOf(
            Transaction("status", "cpu usage", "netusageworkds", "sd")
        ))
    }
}