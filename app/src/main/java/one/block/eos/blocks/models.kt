package one.block.eos.blocks

data class Transaction(
    val status: String,
    val cpuUsage: String,
    val netUsageWords: String,
    val trx: String
)

data class Block(
    val id: String,
    val number: Int,
    val producer: String,
    val producerSignature: String,
    val transactions: List<Transaction> = emptyList()
)
