package org.bitcoindevkit

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertFails

class CreatingWalletTest {
    private val conn: Persister = Persister.newInMemory()

    @Nested
    inner class Success {
        @Test
        fun `Create WPKH wallet`() {
            val wallet: Wallet = Wallet(
                descriptor = BIP84_DESCRIPTOR,
                changeDescriptor = BIP84_CHANGE_DESCRIPTOR,
                network = Network.TESTNET,
                persister = conn
            )
        }

        @Test
        fun `Create TR wallet`() {
            val wallet: Wallet = Wallet(
                descriptor = BIP86_DESCRIPTOR,
                changeDescriptor = BIP86_CHANGE_DESCRIPTOR,
                network = Network.TESTNET,
                persister = conn
            )
        }

        @Test
        fun `Create a wallet with a non-extended descriptor`() {
            val wallet: Wallet = Wallet(
                descriptor = NON_EXTENDED_DESCRIPTOR_0,
                changeDescriptor = NON_EXTENDED_DESCRIPTOR_1,
                network = Network.TESTNET,
                persister = conn
            )
        }
    }

    @Nested
    inner class Failure {
        @Test
        fun `Descriptors do not match provided network`() {
            // The descriptors provided are for Testnet 3, but the wallet attempts to build for mainnet
            assertFails {
                val wallet: Wallet = Wallet(
                    descriptor = NON_EXTENDED_DESCRIPTOR_0,
                    changeDescriptor = NON_EXTENDED_DESCRIPTOR_1,
                    network = Network.BITCOIN,
                    persister = conn
                )
            }
        }
    }
}
