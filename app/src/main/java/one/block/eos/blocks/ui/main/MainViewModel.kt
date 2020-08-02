package one.block.eos.blocks.ui.main

import androidx.lifecycle.ViewModel
import one.block.eos.blocks.data.BlocksRepository

class MainViewModel : ViewModel() {
    val blocks = BlocksRepository().getBlocks()
}