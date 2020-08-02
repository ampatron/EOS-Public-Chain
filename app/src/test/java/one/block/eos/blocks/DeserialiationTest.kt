package one.block.eos.blocks

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.junit.Assert
import org.junit.Test

class DeserialiationTest {
    data class Trans(
        @SerializedName("block_extensions")
        val blockExtensions: String)
    @Test
    fun intIsDeserializedAsString() {
        val jsonString = "{\"block_extensions\":9}"
        val trans = Gson().fromJson(jsonString, Trans::class.java)
//        Assert.assertEquals(9, trans.block_extensions)
        Assert.assertEquals("9", trans.blockExtensions)
    }
}