package org.bitcoindevkit

import kotlin.test.Test
import kotlin.test.assertEquals

class MnemonicTest {
    @Test
    fun `Mnemonics create valid descriptors`() {
        val mnemonic: Mnemonic = Mnemonic.fromString(MNEMONIC_AWESOME)
        val descriptorSecretKey: DescriptorSecretKey = DescriptorSecretKey(Network.TESTNET4, mnemonic, null)
        val descriptor: Descriptor = Descriptor.newBip86(descriptorSecretKey, KeychainKind.EXTERNAL, Network.TESTNET4)

        assertEquals(
            expected = "tr([5bc5d243/86'/1'/0']tpubDC72NVP1RK5qwy2QdEfWphDsUBAfBu7oiV6jEFooHP8tGQGFVUeFxhgZxuk1j6EQRJ1YsS3th2RyDgReRqCL4zqp4jtuV2z7gbiqDH2iyUS/0/*)#xh44xwsp",
            actual = descriptor.toString()
        )
    }
}
