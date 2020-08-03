package one.block.eos.blocks.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*
import one.block.eos.blocks.R
import one.block.eos.blocks.databinding.MainFragmentBinding
import one.block.eos.blocks.models.Block
import one.block.eos.blocks.ui.details.BlockDetailsFragment
import one.block.eos.blocks.ui.main.adapter.BlockItemClickListener
import one.block.eos.blocks.ui.main.adapter.BlockListAdapter
@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var adapter: BlockListAdapter
    private lateinit var binding: MainFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = BlockListAdapter(object : BlockItemClickListener {
            override fun onBlockClicked(block: Block) {
                viewModel.selectBlock(block)
                findNavController().navigate(
                    R.id.action_mainFragment_to_blockDetailsFragment,
                    BlockDetailsFragment.newInstanceArguments(block.id)
                )
            }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = requireActivity()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        blocks.adapter = adapter

        viewModel.blocks.observe(requireActivity(), Observer {
            adapter.submitList(it)
        })
        load_blocks.setOnClickListener {
            viewModel.getRecentBlocks()
        }
    }
}