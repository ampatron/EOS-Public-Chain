package one.block.eos.blocks.models

import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("account_name")
    val accountName: String,
    @SerializedName("head_block_num")
    val headBlockNum: Int,
    @SerializedName("head_block_time")
    val headBlockTime: String,
    @SerializedName("last_code_update")
    val lastCodeUpdate: String,
    val created: String
)

// TODO accept nullable for safety, separate dto from do
data class Block(
    val timestamp: String = "",
    val producer: String = "",
    val confirmed: Int = 0,
    val previous: String = "",
    @SerializedName("transaction_mroot")
    val transactionMroot: String = "",
    @SerializedName("action_mroot")
    val actionMroot: String = "",
    @SerializedName("schedule_version")
    val scheduleVersion: Int = 0,
    @SerializedName("new_producers")
    val newProducers: ProducerSchedule? = null,
    @SerializedName("header_extensions")
    val headerExtensions: String = "",
    @SerializedName("new_protocol_features")
    val newProtocolFeatures: List<Any> = emptyList(),
    @SerializedName("producer_signature")
    val producerSignature: String = "",
    val transactions: List<TransactionReceipt> = emptyList(),
    @SerializedName("block_extensions")
    val blockExtensions: List<String> = emptyList(),
    val id: String = "",
    @SerializedName("block_num")
    val blockNum: Int = 0,
    @SerializedName("ref_block_prefix")
    val refBlockPrefix: Long = 0
)

data class TransactionReceipt(
    val status: String = "",
    @SerializedName("cpu_usage_us")
    val cpuUsageUs: Int = 0,
    @SerializedName("net_usage_words")
    val netUsageWords: Int = 0,
    @SerializedName("trx")
    val transactions: Any? = null
)

data class ProducerSchedule(
    val version: Int = 0,
    val producers: List<ProducerSigningKey> = emptyList()
)

data class ProducerSigningKey(
    @SerializedName("producer_name")
    val producerName: String = "",
    @SerializedName("block_signing_key")
    val blockSigningKey: String = ""
)