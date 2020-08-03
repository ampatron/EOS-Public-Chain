package one.block.eos.blocks.ui.details

import android.os.Bundle
import android.util.Log
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

const val TAG = "blocks"
@AndroidEntryPoint
class BlockDetailsFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>()
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
}