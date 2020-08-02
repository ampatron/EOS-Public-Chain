package one.block.eos.blocks.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList
import one.block.eos.blocks.data.BlocksRepository
import one.block.eos.blocks.models.Block

class MainViewModel : ViewModel() {
    private var _blocks = MutableLiveData<List<Block>>()
    val blocks: LiveData<List<Block>>
        get() = _blocks

    var block: Block? = null
    fun getRecentBlocks() {
        val blocksList = mutableListOf<Block>()
        GlobalScope.launch {
            var blockId: String? = null
            for (i in 1..20) {
                val block = BlocksRepository().getBlock(blockId)
                blocksList.add(block)
                _blocks.postValue(blocksList.toImmutableList())
                blockId = block.previous
            }
        }
    }
}