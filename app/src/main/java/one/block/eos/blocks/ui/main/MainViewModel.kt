package one.block.eos.blocks.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList
import one.block.eos.blocks.data.BlocksRepository
import one.block.eos.blocks.models.Block

class MainViewModel : ViewModel() {
    private var _blocks = MutableLiveData<List<Block>>()
    val blocks: LiveData<List<Block>>
        get() = _blocks

    private var _blockRawData = MutableLiveData<String>()
    val blockRawData: LiveData<String>
        get() = _blockRawData

    var block: Block? = null
        private set

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

    fun convertBlockToRaw(block: Block) {
        viewModelScope.launch(Dispatchers.IO) {
            val raw = GsonBuilder().setPrettyPrinting().create().toJson(block)
            _blockRawData.postValue(raw)
        }

    }

    fun selectBlock(block: Block) {
        this.block = block

    }

}