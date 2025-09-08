package org.bitcoindevkit

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class WalletTest {
    val conn: Persister = Persister.newInMemory()

    @Test
    fun `Wallet produces valid addresses for its network`() {
        val wallet: Wallet = Wallet(
            descriptor = TEST_BIP84_DESCRIPTOR,
            changeDescriptor = TEST_BIP84_CHANGE_DESCRIPTOR,
            network = Network.TESTNET4,
            persister = conn
        )
        val addressInfo: AddressInfo = wallet.revealNextAddress(KeychainKind.EXTERNAL)

        assertTrue(addressInfo.address.isValidForNetwork(Network.TESTNET), "Address is not valid for Testnet 3 network but it should be")
        assertTrue(addressInfo.address.isValidForNetwork(Network.TESTNET4), "Address is not valid for Testnet 4 network but it should be")
        assertTrue(addressInfo.address.isValidForNetwork(Network.SIGNET), "Address is not valid for Signet network but it should be")

        assertFalse(addressInfo.address.isValidForNetwork(Network.REGTEST), "Address is valid for Regtest network, but it should not be")
        assertFalse(addressInfo.address.isValidForNetwork(Network.BITCOIN), "Address is valid for Mainnet network, but it should not be")
    }

    @Test
    fun `Wallet has 0 balance prior to sync`() {
        val wallet: Wallet = Wallet(
            descriptor = TEST_BIP84_DESCRIPTOR,
            changeDescriptor = TEST_BIP84_CHANGE_DESCRIPTOR,
            network = Network.TESTNET4,
            persister = conn
        )

        assertEquals(
            expected = 0uL,
            actual = wallet.balance().total.toSat()
        )
    }

    // Single-descriptor wallets return an address on the external keychain even if a change descriptor is not provided
    // and the wallet.revealNextAddress(KeychainKind.INTERNAL) or wallet.peekAddress(KeychainKind.EXTERNAL, 0u) APIs are
    // used.
    @Test
    fun `Single-descriptor wallets can create addresses`() {
        val wallet: Wallet = Wallet.createSingle(
            descriptor = TEST_BIP84_DESCRIPTOR,
            network = Network.TESTNET4,
            persister = conn
        )
        val address1 = wallet.peekAddress(KeychainKind.EXTERNAL, 0u)
        val address2 = wallet.peekAddress(KeychainKind.INTERNAL, 0u)

        assertEquals(
            expected = address1.address,
            actual = address2.address,
            message = "Addresses should be the same"
        )
    }
}
