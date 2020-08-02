package one.block.eos.blocks.ui.details

import androidx.lifecycle.ViewModel
import one.block.eos.blocks.Block
import one.block.eos.blocks.data.BlocksRepository

class BlockDetailsViewModel : ViewModel() {
    var block: Block = BlocksRepository().getBlocks()[0]
}