package one.block.eos.blocks.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import one.block.eos.blocks.Block
import one.block.eos.blocks.R
import one.block.eos.blocks.databinding.ItemBlockBinding

class BlockListAdapter(var navigator: BlockItemClickListener? = null) :
    ListAdapter<Block, ViewHolder>(BlockDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_block, parent, false)
    )

//    override fun getItemCount(): Int {
//        return 5
//    }

//    override fun getItem(position: Int): Block {
//        return BlocksRepository().mockBlock()
//    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position), navigator)
    }
}

interface BlockItemClickListener {
    fun onBlockClicked(block: Block)
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindTo(block: Block, navigator: BlockItemClickListener?) {
        binding.block = block

        with(itemView) {
            tag = block
            setOnClickListener {
                navigator?.onBlockClicked(block)
            }
        }
    }

    val binding = ItemBlockBinding.bind(itemView)
}

class BlockDiffCallback : DiffUtil.ItemCallback<Block>() {
    override fun areItemsTheSame(oldItem: Block, newItem: Block) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Block, newItem: Block) = oldItem == newItem
}