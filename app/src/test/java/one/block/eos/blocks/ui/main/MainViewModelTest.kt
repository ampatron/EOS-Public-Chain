package one.block.eos.blocks.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import one.block.eos.blocks.api.ChainService
import one.block.eos.blocks.api.GetAccountRequest
import one.block.eos.blocks.data.BlocksRepository
import one.block.eos.blocks.di.BlocksModule
import one.block.eos.blocks.models.Account
import one.block.eos.blocks.models.Block
import one.block.eos.blocks.models.ChainInfo
import one.block.eos.blocks.ui.SharedViewModel
import one.block.eos.blocks.utils.CoroutinesTestRule
import one.block.eosiojava.models.rpcProvider.request.GetBlockRequest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val chainApi = ChainServiceImpl()
    private val repository = BlocksRepository(chainApi)
    private val viewModel = SharedViewModel(repository, BlocksModule.provideGson())

    inline fun <reified T> mock(): T = mock(T::class.java)

    private val blocksObserver: Observer<List<Block>> = mock()

    @Before
    fun setup() {
        viewModel.blocks.observeForever(blocksObserver)
    }

    @Test
    fun getRecentBlocks_shouldPostTwice() {
        // given getRecentBlocks() is run twice
        viewModel.getRecentBlocks(2)

        val captor = ArgumentCaptor.forClass(List::class.java)
        captor.run {
            verify(blocksObserver, times(2)).onChanged(capture() as List<Block>?)
            // assert that final list size is 2
            Assert.assertEquals(2, value.size)
        }
    }

    @Test
    fun getRecentBlocks_shouldPost20Times() {
        // given getRecentBlocks() w no parameter
        viewModel.getRecentBlocks()
        val captor = ArgumentCaptor.forClass(List::class.java)
        captor.run {
            verify(blocksObserver, times(20)).onChanged(capture() as List<Block>?)
            // assert that final list size is 20
            Assert.assertEquals(20, value.size)
        }
    }
}

class ChainServiceImpl : ChainService {
    override suspend fun getChainInfo(): ChainInfo {
        return ChainInfo("1.2.0", "123", "1234")
    }

    override suspend fun getAccount(accountRequest: GetAccountRequest): Account {
        TODO("Not yet implemented")
    }

    override suspend fun getBlock(blockRequest: GetBlockRequest): Block {
        return Block(
            "2020-08-01T02:02:12.000",
            "dev1",
            24,
            "07ffdd6712ef8343070d6db1391d2fa9851e6612195eff5887111937f16478e5"
        )
    }
}

