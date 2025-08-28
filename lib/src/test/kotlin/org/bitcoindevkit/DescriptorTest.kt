package org.bitcoindevkit

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertFails

class DescriptorTest {
    @Nested
    inner class Success {
        @Test
        fun `Create extended WPKH descriptors for all networks`() {
            val descriptor1: Descriptor = Descriptor(
                "wpkh(tprv8ZgxMBicQKsPf2qfrEygW6fdYseJDDrVnDv26PH5BHdvSuG6ecCbHqLVof9yZcMoM31z9ur3tTYbSnr1WBqbGX97CbXcmp5H6qeMpyvx35B/86h/1h/1h/0/*)",
                Network.REGTEST
            )
            val descriptor2: Descriptor = Descriptor(
                "wpkh(tprv8ZgxMBicQKsPf2qfrEygW6fdYseJDDrVnDv26PH5BHdvSuG6ecCbHqLVof9yZcMoM31z9ur3tTYbSnr1WBqbGX97CbXcmp5H6qeMpyvx35B/86h/1h/1h/0/*)",
                Network.TESTNET
            )
            val descriptor3: Descriptor = Descriptor(
                "wpkh(tprv8ZgxMBicQKsPf2qfrEygW6fdYseJDDrVnDv26PH5BHdvSuG6ecCbHqLVof9yZcMoM31z9ur3tTYbSnr1WBqbGX97CbXcmp5H6qeMpyvx35B/86h/1h/1h/0/*)",
                Network.TESTNET4
            )
            val descriptor4: Descriptor = Descriptor(
                "wpkh(tprv8ZgxMBicQKsPf2qfrEygW6fdYseJDDrVnDv26PH5BHdvSuG6ecCbHqLVof9yZcMoM31z9ur3tTYbSnr1WBqbGX97CbXcmp5H6qeMpyvx35B/86h/1h/1h/0/*)",
                Network.SIGNET
            )
            val descriptor5: Descriptor = Descriptor(
                "wpkh(xprv9s21ZrQH143K3LRcTnWpaCSYb75ic2rGuSgicmJhSVQSbfaKgPXfa8PhnYszgdcyWLoc8n1E2iHUnskjgGTAyCEpJYv7fqKxUcRNaVngA1V/86h/1h/1h/0/*)",
                Network.BITCOIN
            )
        }

        @Test
        fun `Create extended TR descriptors for all networks`() {
            val descriptor1: Descriptor = Descriptor(
                "tr(tprv8ZgxMBicQKsPf2qfrEygW6fdYseJDDrVnDv26PH5BHdvSuG6ecCbHqLVof9yZcMoM31z9ur3tTYbSnr1WBqbGX97CbXcmp5H6qeMpyvx35B/86h/1h/1h/0/*)",
                Network.REGTEST
            )
            val descriptor2: Descriptor = Descriptor(
                "tr(tprv8ZgxMBicQKsPf2qfrEygW6fdYseJDDrVnDv26PH5BHdvSuG6ecCbHqLVof9yZcMoM31z9ur3tTYbSnr1WBqbGX97CbXcmp5H6qeMpyvx35B/86h/1h/1h/0/*)",
                Network.TESTNET
            )
            val descriptor3: Descriptor = Descriptor(
                "tr(tprv8ZgxMBicQKsPf2qfrEygW6fdYseJDDrVnDv26PH5BHdvSuG6ecCbHqLVof9yZcMoM31z9ur3tTYbSnr1WBqbGX97CbXcmp5H6qeMpyvx35B/86h/1h/1h/0/*)",
                Network.TESTNET4
            )
            val descriptor4: Descriptor = Descriptor(
                "tr(tprv8ZgxMBicQKsPf2qfrEygW6fdYseJDDrVnDv26PH5BHdvSuG6ecCbHqLVof9yZcMoM31z9ur3tTYbSnr1WBqbGX97CbXcmp5H6qeMpyvx35B/86h/1h/1h/0/*)",
                Network.SIGNET
            )
            val descriptor5: Descriptor = Descriptor(
                "tr(xprv9s21ZrQH143K3LRcTnWpaCSYb75ic2rGuSgicmJhSVQSbfaKgPXfa8PhnYszgdcyWLoc8n1E2iHUnskjgGTAyCEpJYv7fqKxUcRNaVngA1V/86h/1h/1h/0/*)",
                Network.BITCOIN
            )
        }

        @Test
        fun `Create non-extended descriptors for all networks`() {
            val descriptor1: Descriptor = Descriptor(
                "tr(tprv8ZgxMBicQKsPf2qfrEygW6fdYseJDDrVnDv26PH5BHdvSuG6ecCbHqLVof9yZcMoM31z9ur3tTYbSnr1WBqbGX97CbXcmp5H6qeMpyvx35B/86h/1h/1h/0/0)",
                Network.REGTEST
            )
            val descriptor2: Descriptor = Descriptor(
                "tr(tprv8ZgxMBicQKsPf2qfrEygW6fdYseJDDrVnDv26PH5BHdvSuG6ecCbHqLVof9yZcMoM31z9ur3tTYbSnr1WBqbGX97CbXcmp5H6qeMpyvx35B/86h/1h/1h/0/0)",
                Network.TESTNET
            )
            val descriptor3: Descriptor = Descriptor(
                "tr(tprv8ZgxMBicQKsPf2qfrEygW6fdYseJDDrVnDv26PH5BHdvSuG6ecCbHqLVof9yZcMoM31z9ur3tTYbSnr1WBqbGX97CbXcmp5H6qeMpyvx35B/86h/1h/1h/0/0)",
                Network.TESTNET4
            )
            val descriptor4: Descriptor = Descriptor(
                "tr(tprv8ZgxMBicQKsPf2qfrEygW6fdYseJDDrVnDv26PH5BHdvSuG6ecCbHqLVof9yZcMoM31z9ur3tTYbSnr1WBqbGX97CbXcmp5H6qeMpyvx35B/86h/1h/1h/0/0)",
                Network.SIGNET
            )
            val descriptor5: Descriptor = Descriptor(
                "tr(xprv9s21ZrQH143K3LRcTnWpaCSYb75ic2rGuSgicmJhSVQSbfaKgPXfa8PhnYszgdcyWLoc8n1E2iHUnskjgGTAyCEpJYv7fqKxUcRNaVngA1V/86h/1h/1h/0/0)",
                Network.BITCOIN
            )
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
