package org.bitcoindevkit

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertFails

class DescriptorTest {
    @Nested
    inner class Success {
        @Test
        fun `Create extended WPKH descriptors for all networks 2`() {
            Descriptor("wpkh($TEST_EXTENDED_PRIVKEY/$BIP84_TEST_RECEIVE_PATH/*)", Network.REGTEST)
            Descriptor("wpkh($TEST_EXTENDED_PRIVKEY/$BIP84_TEST_RECEIVE_PATH/*)", Network.TESTNET)
            Descriptor("wpkh($TEST_EXTENDED_PRIVKEY/$BIP84_TEST_RECEIVE_PATH/*)", Network.TESTNET4)
            Descriptor("wpkh($TEST_EXTENDED_PRIVKEY/$BIP84_TEST_RECEIVE_PATH/*)", Network.SIGNET)
            Descriptor("wpkh($MAINNET_EXTENDED_PRIVKEY/$BIP84_MAINNET_RECEIVE_PATH/*)", Network.BITCOIN)
        }

        @Test
        fun `Create extended TR descriptors for all networks`() {
            Descriptor("tr($TEST_EXTENDED_PRIVKEY/$BIP86_TEST_RECEIVE_PATH/*)", Network.REGTEST)
            Descriptor("tr($TEST_EXTENDED_PRIVKEY/$BIP86_TEST_RECEIVE_PATH/*)", Network.TESTNET)
            Descriptor("tr($TEST_EXTENDED_PRIVKEY/$BIP86_TEST_RECEIVE_PATH/*)", Network.TESTNET4)
            Descriptor("tr($TEST_EXTENDED_PRIVKEY/$BIP86_TEST_RECEIVE_PATH/*)", Network.SIGNET)
            Descriptor("tr($MAINNET_EXTENDED_PRIVKEY/$BIP86_MAINNET_RECEIVE_PATH/*)", Network.BITCOIN)
        }

        @Test
        fun `Create non-extended descriptors for all networks`() {
            Descriptor("tr($TEST_EXTENDED_PRIVKEY/$BIP86_TEST_RECEIVE_PATH/0)", Network.REGTEST)
            Descriptor("tr($TEST_EXTENDED_PRIVKEY/$BIP86_TEST_RECEIVE_PATH/0)", Network.TESTNET)
            Descriptor("tr($TEST_EXTENDED_PRIVKEY/$BIP86_TEST_RECEIVE_PATH/0)", Network.TESTNET4)
            Descriptor("tr($TEST_EXTENDED_PRIVKEY/$BIP86_TEST_RECEIVE_PATH/0)", Network.SIGNET)
            Descriptor("tr($MAINNET_EXTENDED_PRIVKEY/$BIP86_MAINNET_RECEIVE_PATH/0)", Network.BITCOIN)
        }
    }

    @Nested
    inner class Failure {
        @Test
        fun `Cannot create addr() descriptor`() {
            assertFails {
                val descriptor: Descriptor = Descriptor(
                    "addr(tb1qhjys9wxlfykmte7ftryptx975uqgd6kcm6a7z4)",
                    Network.TESTNET
                )
            }
        }
    }
}
