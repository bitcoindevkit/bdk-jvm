package org.bitcoindevkit

import org.junit.jupiter.api.Nested
import org.kotlinbitcointools.regtesttoolbox.regenv.RegEnv
import kotlinx.coroutines.runBlocking
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.test.Test

class CbfSyncTest {
    private val conn: Persister = Persister.newInMemory()

    @Nested
    inner class Success {
        @Test
        fun `Successful sync using a Kyoto client`() {
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

                val persistenceDir: Path = Paths.get("src/test/data").toAbsolutePath().normalize()
                Files.createDirectories(persistenceDir)
                val persistenceFilePath: Path = Files.createTempDirectory(persistenceDir, "kyoto_data_")

                val ip: IpAddress = IpAddress.fromIpv4(127u, 0u, 0u, 1u)
                val peer1: Peer = Peer(ip, 18444u, false)
                val peers: List<Peer> = listOf(peer1)
                val (client, node) = CbfBuilder()
                    .peers(peers)
                    .connections(1u)
                    .scanType(ScanType.Sync)
                    .dataDir(persistenceFilePath.toString())
                    .build(wallet)

                node.run()
                val update: Update = client.update()
                wallet.applyUpdate(update)

                val balance = wallet.balance().total.toSat()
                assert(balance > 0uL)
                client.shutdown()
            }
        }
    }
}