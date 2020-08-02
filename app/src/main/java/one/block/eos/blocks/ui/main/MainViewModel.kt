package one.block.eos.blocks.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import one.block.eos.blocks.data.BlocksRepository
import one.block.eos.blocks.models.Block

class MainViewModel : ViewModel() {
    private var _blocks = BlocksRepository().getBlocks()
    val blocks: LiveData<List<Block>>
        get() = _blocks

    var block: Block? = null
    fun getRecentBlocks() {
        _blocks = BlocksRepository().getBlocks()
    }
}