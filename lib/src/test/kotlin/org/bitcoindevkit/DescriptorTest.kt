package org.bitcoindevkit

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertFails

class DescriptorTest {
    @Nested
    inner class Success {
        @Test
        fun `Create extended WPKH descriptors for all networks`() {
            Descriptor("wpkh($TEST_EXTENDED_PRIVKEY/$BIP84_TEST_RECEIVE_PATH/*)", Network.REGTEST)
            Descriptor("wpkh($TEST_EXTENDED_PRIVKEY/$BIP84_TEST_RECEIVE_PATH/*)", Network.TESTNET)
            Descriptor("wpkh($TEST_EXTENDED_PRIVKEY/$BIP84_TEST_RECEIVE_PATH/*)", Network.TESTNET4)
            Descriptor("wpkh($TEST_EXTENDED_PRIVKEY/$BIP84_TEST_RECEIVE_PATH/*)", Network.SIGNET)
            Descriptor("wpkh($MAIN_EXTENDED_PRIVKEY/$BIP84_MAIN_RECEIVE_PATH/*)", Network.BITCOIN)
        }

        @Test
        fun `Create extended TR descriptors for all networks`() {
            Descriptor("tr($TEST_EXTENDED_PRIVKEY/$BIP86_TEST_RECEIVE_PATH/*)", Network.REGTEST)
            Descriptor("tr($TEST_EXTENDED_PRIVKEY/$BIP86_TEST_RECEIVE_PATH/*)", Network.TESTNET)
            Descriptor("tr($TEST_EXTENDED_PRIVKEY/$BIP86_TEST_RECEIVE_PATH/*)", Network.TESTNET4)
            Descriptor("tr($TEST_EXTENDED_PRIVKEY/$BIP86_TEST_RECEIVE_PATH/*)", Network.SIGNET)
            Descriptor("tr($MAIN_EXTENDED_PRIVKEY/$BIP86_MAIN_RECEIVE_PATH/*)", Network.BITCOIN)
        }

        @Test
        fun `Create non-extended descriptors for all networks`() {
            Descriptor("tr($TEST_EXTENDED_PRIVKEY/$BIP86_TEST_RECEIVE_PATH/0)", Network.REGTEST)
            Descriptor("tr($TEST_EXTENDED_PRIVKEY/$BIP86_TEST_RECEIVE_PATH/0)", Network.TESTNET)
            Descriptor("tr($TEST_EXTENDED_PRIVKEY/$BIP86_TEST_RECEIVE_PATH/0)", Network.TESTNET4)
            Descriptor("tr($TEST_EXTENDED_PRIVKEY/$BIP86_TEST_RECEIVE_PATH/0)", Network.SIGNET)
            Descriptor("tr($MAIN_EXTENDED_PRIVKEY/$BIP86_MAIN_RECEIVE_PATH/0)", Network.BITCOIN)
        }

        @Test
        fun `Create descriptors from multipath public descriptor strings`() {
            Descriptor("tr($TEST_EXTENDED_PUBKEY/<0;1>/*)", Network.REGTEST)
            Descriptor("tr($TEST_EXTENDED_PUBKEY/<0;1>/*)", Network.TESTNET)
            Descriptor("tr($TEST_EXTENDED_PUBKEY/<0;1>/*)", Network.TESTNET4)
            Descriptor("tr($TEST_EXTENDED_PUBKEY/<0;1>/*)", Network.SIGNET)
            Descriptor("tr($MAIN_EXTENDED_PUBKEY/<0;1>/*)", Network.BITCOIN)
        }
    }


    @Nested
    inner class Failure {
        @Test
        fun `Cannot create addr() descriptor`() {
            assertFails {
                Descriptor(
                    "addr(tb1qhjys9wxlfykmte7ftryptx975uqgd6kcm6a7z4)",
                    Network.TESTNET4
                )
            }
        }

        @Test
        fun `Descriptor cannot be created from multipath private descriptor string`() {
            assertFails {
                Descriptor("tr($TEST_EXTENDED_PRIVKEY/$BIP86_TEST_MULTIPATH/*)", Network.REGTEST)
            }
        }
    }
}
