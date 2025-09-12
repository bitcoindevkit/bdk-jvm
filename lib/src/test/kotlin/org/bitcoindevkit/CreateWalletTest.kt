package org.bitcoindevkit

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertFails

class CreateWalletTest {
    private val conn: Persister = Persister.newInMemory()

    @Nested
    inner class Success {
        @Test
        fun `Create WPKH wallet`() {
            Wallet(
                descriptor = TEST_BIP84_DESCRIPTOR,
                changeDescriptor = TEST_BIP84_CHANGE_DESCRIPTOR,
                network = Network.TESTNET4,
                persister = conn
            )
        }

        @Test
        fun `Create TR wallet`() {
            Wallet(
                descriptor = TEST_BIP86_DESCRIPTOR,
                changeDescriptor = TEST_BIP86_CHANGE_DESCRIPTOR,
                network = Network.TESTNET4,
                persister = conn
            )
        }

        @Test
        fun `Create a wallet with a single descriptor`() {
            Wallet.createSingle(
                descriptor = TEST_BIP84_DESCRIPTOR,
                network = Network.TESTNET4,
                persister = conn
            )
        }

        @Test
        fun `Create a wallet with a non-extended descriptor`() {
            Wallet(
                descriptor = TEST_DEFINITE_DESCRIPTOR_0,
                changeDescriptor = TEST_DEFINITE_DESCRIPTOR_1,
                network = Network.TESTNET4,
                persister = conn
            )
        }

        @Test
        fun `Create a wallet with a public multipath descriptor`() {
            Wallet.createFromTwoPathDescriptor(
                twoPathDescriptor = TEST_MULTIPATH_DESCRIPTOR,
                network = Network.TESTNET4,
                persister = conn
            )
        }
    }

    @Nested
    inner class Failure {
        @Test
        fun `Descriptors do not match provided network`() {
            // The descriptors provided are for Testnet4, but the wallet attempts to build for mainnet
            assertFails {
                Wallet(
                    descriptor = TEST_DEFINITE_DESCRIPTOR_0,
                    changeDescriptor = TEST_DEFINITE_DESCRIPTOR_1,
                    network = Network.BITCOIN,
                    persister = conn
                )
            }
        }

        @Test
        fun `You cannot create a wallet with two identical descriptors`() {
            assertFails {
                Wallet(
                    descriptor = TEST_DEFINITE_DESCRIPTOR_0,
                    changeDescriptor = TEST_DEFINITE_DESCRIPTOR_0,
                    network = Network.TESTNET4,
                    persister = conn
                )
            }

            assertFails {
                Wallet(
                    descriptor = TEST_BIP84_DESCRIPTOR,
                    changeDescriptor = TEST_BIP84_DESCRIPTOR,
                    network = Network.TESTNET4,
                    persister = conn
                )
            }
        }
    }
}
