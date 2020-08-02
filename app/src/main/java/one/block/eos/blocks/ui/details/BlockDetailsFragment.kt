package one.block.eos.blocks.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import one.block.eos.blocks.databinding.BlockDetailsFragmentBinding

const val BLOCK_ID_KEY = "one.block.eos.blocks.ui.key.blockId"

class BlockDetailsFragment : Fragment() {

    companion object {
        fun newInstanceArguments(blockId: String) =
            bundleOf(BLOCK_ID_KEY to blockId)
    }


    private lateinit var blockId: String
    val viewModel by viewModels<BlockDetailsViewModel>()
    private lateinit var binding: BlockDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BlockDetailsFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        savedInstanceState?.getString(BLOCK_ID_KEY)?.let {
            blockId = it
        }
        binding.viewModel = viewModel
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString(BLOCK_ID_KEY, blockId)
        }
        super.onSaveInstanceState(outState)
    }
}