package org.bitcoindevkit

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.nio.file.Files
import java.nio.file.Paths
import org.slf4j.Logger
import org.slf4j.LoggerFactory



fun main() {
    // Regtest environment must have Compact block filter enabled to run this
    // Setup your environment according to the https://github.com/thunderbiscuit/podman-regtest-infinity-pro documentation.

    // Add your regtest IP address
    val ip: IpAddress = IpAddress.fromIpv4(127u, 0u, 0u, 1u)
    val peer: Peer = Peer(ip, 18444u, false)
    val peers: List<Peer> = listOf(peer)
    val currentPath = Paths.get("examples/data").toAbsolutePath().normalize()
    println("Current path: $currentPath")
    val persistenceFilePath = Files.createTempDirectory(currentPath, "kyoto-data_")

    val wallet = getNewWallet(ActiveWalletScriptType.P2WPKH, Network.REGTEST)
    val address = wallet.revealNextAddress(KeychainKind.EXTERNAL)

    // Fund this address. Send coins from your regtest to this address
    println("Receiving address. Send funds to this address: ${address.address}")

    // Wait 70 seconds for funds to arrive before syncing
    // Ensure you mine a block after sending funds to the address. This enables kyoto pickup the transaction.
    println("Waiting 70 seconds for funds to arrive here ${address.address} before kyoto (compact block filter syncing) ...")
    Thread.sleep(70000)

    // Create CBF node and client
    runBlocking {
        val lightClient = CbfBuilder()
            .peers(peers)
            .connections(1u)
            .scanType(ScanType.Sync)
            .dataDir(persistenceFilePath.toString())
            .build(wallet)
        val client = lightClient.client
        val node = lightClient.node

        val logWarningJob = launchLogCollector(this, client::nextWarning, "WARNING")
        val logInfoJob = launchLogCollector(this, client::nextInfo, "INFO")

        //Start CBF node
        node.run()
        println("Node running")

        //Update wallet
        val update: Update = client.update()
        wallet.applyUpdate(update)
        println("Wallet balance: ${wallet.balance().total.toSat()} sats")

        if(wallet.balance().total.toSat() > 0uL) {
            println("Wallet is synced and ready for use!")
            println("Test completed successfully")
        }else{
            println("Wallet balance is 0. Try sending funds to ${address.address} and try again.")
        }
        logWarningJob.cancelAndJoin()
        logInfoJob.cancelAndJoin()
        client.shutdown()
    }
}

fun <T> launchLogCollector(scope: CoroutineScope, collector: suspend () -> T, logType: String) = scope.launch {
    val logger: Logger = LoggerFactory.getLogger("LogCollector")

    while (true) {
        val log = collector()
        when (logType) {
            "WARNING" -> logger.warn("$log")
            "INFO" -> logger.info("$log")
            else -> logger.debug("[$logType]: $log")
        }
    }
}

