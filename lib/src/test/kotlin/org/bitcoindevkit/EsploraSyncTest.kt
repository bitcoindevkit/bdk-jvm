package org.bitcoindevkit

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Nested
import org.kotlinbitcointools.regtesttoolbox.regenv.RegEnv
import kotlin.test.Test
import kotlin.time.Duration.Companion.seconds

class EsploraSyncTest {
    private val conn: Persister = Persister.newInMemory()

    @Nested
    inner class Success {
        @Test
        fun `Successful sync using a Esplora client`() {
            runBlocking {
                val regtestEnv = RegEnv.connectTo(walletName = "faucet", username = "regtest", password = "password")

                val wallet: Wallet = Wallet.createSingle(
                    descriptor = TEST_BIP86_DESCRIPTOR,
                    network = Network.REGTEST,
                    persister = conn
                )
                val newAddress = wallet.revealNextAddress(KeychainKind.EXTERNAL).address

                regtestEnv.send(newAddress.toString(), 0.12345678, 2.0)
                regtestEnv.mine(2)

                //Wait 8 second for mining to complete and for esplora to index the new blocks before scanning
                delay(8.seconds)

                val esploraClient = EsploraClient(ESPLORA_REGTEST_URL)
                val fullScanRequest: FullScanRequest = wallet.startFullScan().build()
                val update = esploraClient.fullScan(fullScanRequest, 10uL, 1uL)


                wallet.applyUpdate(update)
                wallet.persist(conn)

                val balance = wallet.balance().total.toSat()
                assert(balance > 0uL){
                    "Balance should be greater than zero, but was $balance"
                }
            }
        }
    }
}
