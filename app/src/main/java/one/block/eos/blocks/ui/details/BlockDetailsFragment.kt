package one.block.eos.blocks.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.block_details_fragment.*
import one.block.eos.blocks.databinding.BlockDetailsFragmentBinding
import one.block.eos.blocks.ui.main.MainViewModel

const val BLOCK_ID_KEY = "one.block.eos.blocks.ui.key.blockId"

@AndroidEntryPoint
class BlockDetailsFragment : Fragment() {

    companion object {
        fun newInstanceArguments(blockId: String) =
            bundleOf(BLOCK_ID_KEY to blockId)
    }

    private lateinit var blockId: String
    val viewModel by activityViewModels<MainViewModel>()
    private lateinit var binding: BlockDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BlockDetailsFragmentBinding.inflate(inflater, container, false)
        binding.scrollView.isSmoothScrollingEnabled = true
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        savedInstanceState?.getString(BLOCK_ID_KEY)?.let {
            blockId = it
        }
        binding.viewModel = viewModel
        viewModel.blockRawData.observe(requireActivity(), Observer {
            binding.rawContent.text = it
        })

        raw_content_toggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.convertBlockToRaw(viewModel.block!!)
                binding.rawContent.visibility = View.VISIBLE
            } else {
                binding.rawContent.visibility = View.GONE
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString(BLOCK_ID_KEY, blockId)
        }
        super.onSaveInstanceState(outState)
    }
}