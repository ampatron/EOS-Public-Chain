package one.block.eos.blocks.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList
import one.block.eos.blocks.api.ApiSuccessResponse
import one.block.eos.blocks.data.BlocksRepository
import one.block.eos.blocks.models.Block

class MainViewModel @ViewModelInject constructor(private val repository: BlocksRepository) :
    ViewModel() {
    private var _blocks = MutableLiveData<List<Block>>()
    val blocks: LiveData<List<Block>>
        get() = _blocks

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private var _blockRawData = MutableLiveData<String>()
    val blockRawData: LiveData<String>
        get() = _blockRawData

    var block: Block? = null
        private set

    fun getRecentBlocks() {
        _isLoading.postValue(true)
        val blocksList = mutableListOf<Block>()
        GlobalScope.launch {
            var blockId: String? = null
            for (i in 1..20) {
                val block = repository.getBlock(blockId)
                if (block is ApiSuccessResponse) {
                    blocksList.add(block.response)
                    _blocks.postValue(blocksList.toImmutableList())
                    blockId = block.response.previous
                }
            }
            _isLoading.postValue(false)
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
        _blockRawData.postValue(null)
    }
}

