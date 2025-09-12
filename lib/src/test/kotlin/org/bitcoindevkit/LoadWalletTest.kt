package org.bitcoindevkit

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class LoadWalletTest {
    private val PERSISTENCE_FILE_PATH = run {
        val currentDirectory = System.getProperty("user.dir")
        val dbFileName = "awesome_wallet_1.sqlite3"
        "$currentDirectory/src/test/resources/$dbFileName"
    }
    val sqlitePersister: Persister = Persister.newSqlite(PERSISTENCE_FILE_PATH)

    @Nested
    inner class Success {
        @Test
        fun `Wallet can be loaded from persistence`() {
            Wallet.load(
                descriptor = TEST_BIP86_DESCRIPTOR,
                changeDescriptor = TEST_BIP86_CHANGE_DESCRIPTOR,
                persister = sqlitePersister
            )
        }

        @Test
        fun `Loaded wallet is at the right revealed index`() {
            val wallet = Wallet.load(
                descriptor = TEST_BIP86_DESCRIPTOR,
                changeDescriptor = TEST_BIP86_CHANGE_DESCRIPTOR,
                persister = sqlitePersister
            )
            val addressInfo: AddressInfo = wallet.revealNextAddress(KeychainKind.EXTERNAL)

            assertEquals(
                expected = 26u,
                actual = addressInfo.index,
            )
            assertEquals(
                expected = "bcrt1pqhqlr5hxya35pmr0en5s3w9jyy9cmj90qwev0d36d6787jm27jzqg9kxse",
                actual = addressInfo.address.toString(),
            )
        }
    }

    @Nested
    inner class Failure {
        @Test
        fun `Loading a wallet with mismatched descriptors should fail`() {
            assertFails {
                Wallet.load(
                    descriptor = TEST_BIP84_DESCRIPTOR,
                    changeDescriptor = TEST_BIP84_CHANGE_DESCRIPTOR,
                    persister = sqlitePersister
                )
            }
        }
    }
}
