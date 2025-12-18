import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val MAX_AGE = 200
private const val MAX_NAME = 100

fun main() {
  val n = readInt()
  val arr = Array(MAX_AGE + 1) { ArrayList<ByteArray>() }
  var minAge = MAX_AGE
  var maxAge = 0
  repeat(n) {
    val age = readInt()

    arr[age].add(readWordAsByte())
    if (age > maxAge) maxAge = age
    if (age < minAge) minAge = age
  }

  val agePrefix = Array(MAX_AGE + 1) { age -> ("$age ").toByteArray() }
  for (age in minAge..maxAge) {
    val prefix = agePrefix[age]
    for (name in arr[age]) {
      OUT.write(prefix)
      OUT.write(name)
      OUT.write('\n'.code)
    }
  }

  OUT.flush()
}

val IN = BufferedInputStream(System.`in`, 1 shl 20)
val OUT = BufferedOutputStream(System.`out`, 1 shl 20)

private fun readInt(): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read()
  var n = 0
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = IN.read()
  }
  return n
}

private val byteBuf = ByteArray(MAX_NAME)
fun readWordAsByte(): ByteArray {
  var c = IN.read()
  while (c <= 32) c = IN.read()
  var len = 0
  while (c > 32) {
    byteBuf[len++] = c.toByte()
    c = IN.read()
  }
  return byteBuf.copyOf(len)
}