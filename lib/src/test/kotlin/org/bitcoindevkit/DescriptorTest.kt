package org.bitcoindevkit

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertFails

class DescriptorTest {
    @Nested
    inner class Success {
        @Test
        fun `Create extended WPKH descriptors for all networks`() {
            Descriptor("wpkh($TEST_EXTENDED_PRIVKEY/$BIP84_TEST_RECEIVE_PATH/*)", NetworkKind.TEST)
            Descriptor("wpkh($TEST_EXTENDED_PRIVKEY/$BIP84_TEST_RECEIVE_PATH/*)", NetworkKind.TEST)
            Descriptor("wpkh($TEST_EXTENDED_PRIVKEY/$BIP84_TEST_RECEIVE_PATH/*)", NetworkKind.TEST)
            Descriptor("wpkh($TEST_EXTENDED_PRIVKEY/$BIP84_TEST_RECEIVE_PATH/*)", NetworkKind.TEST)
            Descriptor("wpkh($MAIN_EXTENDED_PRIVKEY/$BIP84_MAIN_RECEIVE_PATH/*)", NetworkKind.MAIN)
        }

        @Test
        fun `Create extended TR descriptors for all networks`() {
            Descriptor("tr($TEST_EXTENDED_PRIVKEY/$BIP86_TEST_RECEIVE_PATH/*)", NetworkKind.TEST)
            Descriptor("tr($TEST_EXTENDED_PRIVKEY/$BIP86_TEST_RECEIVE_PATH/*)", NetworkKind.TEST)
            Descriptor("tr($TEST_EXTENDED_PRIVKEY/$BIP86_TEST_RECEIVE_PATH/*)", NetworkKind.TEST)
            Descriptor("tr($TEST_EXTENDED_PRIVKEY/$BIP86_TEST_RECEIVE_PATH/*)", NetworkKind.TEST)
            Descriptor("tr($MAIN_EXTENDED_PRIVKEY/$BIP86_MAIN_RECEIVE_PATH/*)", NetworkKind.MAIN)
        }

        @Test
        fun `Create non-extended descriptors for all networks`() {
            Descriptor("tr($TEST_EXTENDED_PRIVKEY/$BIP86_TEST_RECEIVE_PATH/0)", NetworkKind.TEST)
            Descriptor("tr($TEST_EXTENDED_PRIVKEY/$BIP86_TEST_RECEIVE_PATH/0)", NetworkKind.TEST)
            Descriptor("tr($TEST_EXTENDED_PRIVKEY/$BIP86_TEST_RECEIVE_PATH/0)", NetworkKind.TEST)
            Descriptor("tr($TEST_EXTENDED_PRIVKEY/$BIP86_TEST_RECEIVE_PATH/0)", NetworkKind.TEST)
            Descriptor("tr($MAIN_EXTENDED_PRIVKEY/$BIP86_MAIN_RECEIVE_PATH/0)", NetworkKind.MAIN)
        }

        @Test
        fun `Create descriptors from multipath public descriptor strings`() {
            Descriptor("tr($TEST_EXTENDED_PUBKEY/<0;1>/*)", NetworkKind.TEST)
            Descriptor("tr($TEST_EXTENDED_PUBKEY/<0;1>/*)", NetworkKind.TEST)
            Descriptor("tr($TEST_EXTENDED_PUBKEY/<0;1>/*)", NetworkKind.TEST)
            Descriptor("tr($TEST_EXTENDED_PUBKEY/<0;1>/*)", NetworkKind.TEST)
            Descriptor("tr($MAIN_EXTENDED_PUBKEY/<0;1>/*)", NetworkKind.MAIN)
        }
    }


    @Nested
    inner class Failure {
        @Test
        fun `Cannot create addr() descriptor`() {
            assertFails {
                Descriptor(
                    "addr(tb1qhjys9wxlfykmte7ftryptx975uqgd6kcm6a7z4)",
                    NetworkKind.TEST
                )
            }
        }

        @Test
        fun `Descriptor cannot be created from multipath private descriptor string`() {
            assertFails {
                Descriptor("tr($TEST_EXTENDED_PRIVKEY/$BIP86_TEST_MULTIPATH/*)", NetworkKind.TEST)
            }
        }
    }
}
