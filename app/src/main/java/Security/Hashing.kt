package Security

import java.security.MessageDigest

fun String.hash (): String {
    val hexArray = "0123456789ABCDEF"
    val bytes = MessageDigest.getInstance("SHA-512").digest(this.toByteArray())

    val hash = StringBuilder(bytes.size * 2)
    bytes.forEach {
        val i = it.toInt()
        hash.append(hexArray[i shr 4 and 0x0f])
        hash.append(hexArray[i and 0x0f])
    }

    return hash.toString()
}