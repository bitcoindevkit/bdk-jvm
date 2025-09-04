package org.bitcoindevkit

// 1. Extended Keys
// These are generated from the MNEMONIC_AWESOME mnemonic. The keys with the TEST prefix are valid for Regtest, Signet,
// Testnet3, and Testnet4. The keys with the MAIN prefix are valid for Mainnet.
const val TEST_EXTENDED_PRIVKEY = "tprv8ZgxMBicQKsPdWAHbugK2tjtVtRjKGixYVZUdL7xLHMgXZS6BFbFi1UDb1CHT25Z5PU1F9j7wGxwUiRhqz9E3nZRztikGUV6HoRDYcqPhM4"
const val TEST_EXTENDED_PUBKEY  = "tpubD6NzVbkrYhZ4WyC5VZLuSJQ14uwfUbus7oAFurAFkZA5N3groeQqtW65m8pG1TT1arPpfWu9RbBsc5rSBncrX2d84BAwJJHQfaRjnMCQwuT"
const val MAIN_EXTENDED_PRIVKEY = "xprv9s21ZrQH143K2gvkwLposF7uBm1X5kgxCweMkuhVrJsCjxh1BtFWCG6mfq2dSehEhwwEF47MmvP91rsximoHEjHqUFWSc7m3Nhfo6yAmTtq"
const val MAIN_EXTENDED_PUBKEY  = "xpub661MyMwAqRbcFB1E3NMpEP4djnr1VDQoaAZxZJ77QeQBcm29jRZkk4RFX6jUUxVmnwrv3wPGtVMpuuMpnvmcdbApzaRdcmdB5cqL2Q1yjam"

// 2. Mnemonics
const val MNEMONIC_AWESOME = "awesome awesome awesome awesome awesome awesome awesome awesome awesome awesome awesome awesome"
const val MNEMONIC_ALL = "all all all all all all all all all all all all"

// 3. Derivation Paths
const val BIP84_TEST_RECEIVE_PATH = "84h/1h/0h/0"
const val BIP84_TEST_CHANGE_PATH  = "84h/1h/0h/1"
const val BIP86_TEST_RECEIVE_PATH = "86h/1h/0h/0"
const val BIP86_TEST_CHANGE_PATH  = "86h/1h/0h/1"
const val BIP86_TEST_MULTIPATH    = "86h/1h/0h/<0;1>"

const val BIP84_MAIN_RECEIVE_PATH = "84h/0h/0h/0"
const val BIP84_MAIN_CHANGE_PATH  = "84h/0h/0h/1"
const val BIP86_MAIN_RECEIVE_PATH = "86h/0h/0h/0"
const val BIP86_MAIN_CHANGE_PATH  = "86h/0h/0h/1"
const val BIP86_MAIN_MULTIPATH    = "86h/0h/0h/<0;1>"

// 4. Descriptors
// These are also generated from the MNEMONIC_AWESOME mnemonic. The descriptors with the TEST prefix are valid for
// Regtest, Signet, Testnet3, and Testnet4. The descriptors with the MAIN prefix are valid for Mainnet.
val TEST_BIP84_DESCRIPTOR        = Descriptor("wpkh($TEST_EXTENDED_PRIVKEY/$BIP84_TEST_RECEIVE_PATH/*)", Network.TESTNET4)
val TEST_BIP84_CHANGE_DESCRIPTOR = Descriptor("wpkh($TEST_EXTENDED_PRIVKEY/$BIP84_TEST_CHANGE_PATH/*)", Network.TESTNET4)
val TEST_BIP86_DESCRIPTOR        = Descriptor("tr($TEST_EXTENDED_PRIVKEY/$BIP86_TEST_RECEIVE_PATH/*)", Network.TESTNET4)
val TEST_BIP86_CHANGE_DESCRIPTOR = Descriptor("tr($TEST_EXTENDED_PRIVKEY/$BIP86_TEST_CHANGE_PATH/*)", Network.TESTNET4)
val TEST_DEFINITE_DESCRIPTOR_0   = Descriptor("wpkh($TEST_EXTENDED_PRIVKEY/$BIP84_TEST_RECEIVE_PATH/0)", Network.TESTNET4)
val TEST_DEFINITE_DESCRIPTOR_1   = Descriptor("wpkh($TEST_EXTENDED_PRIVKEY/$BIP84_TEST_RECEIVE_PATH/1)", Network.TESTNET4)

val MAIN_BIP86_DESCRIPTOR        = Descriptor("tr($MAIN_EXTENDED_PRIVKEY/$BIP86_TEST_RECEIVE_PATH/*)", Network.BITCOIN)
val MAIN_BIP86_CHANGE_DESCRIPTOR = Descriptor("tr($MAIN_EXTENDED_PRIVKEY/$BIP86_TEST_CHANGE_PATH/*)", Network.BITCOIN)
