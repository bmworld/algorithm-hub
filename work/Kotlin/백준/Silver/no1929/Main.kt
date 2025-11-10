package 백준.Silver.no1929

import java.io.BufferedOutputStream

private val OUT = BufferedOutputStream(System.`out`, 1 shl 20)
private val B: ByteArray = System.`in`.readBytes()
private var Bi = 0
private const val EOF = -1

private fun read(): Int = if (Bi < B.size) (B[Bi++].toInt() and 0xFF) else EOF

private fun readInt(): Int {
  var c = read()
  while (c != EOF && c <= 32) c = read()

  var neg = false
  if (c == '-'.code) {
    neg = true
    c = read()
  }
  var n = 0
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = read()
  }
  return if (neg) -n else n
}

private const val MAX_NUM_LEN = 6
private val buf = ByteArray(MAX_NUM_LEN + 1).also { it[MAX_NUM_LEN] = '\n'.code.toByte() }

private fun writeln(num: Int) {
  var x = num
  //  var neg = false
  //  if (x < 0) {
  //    neg = true
  //    x = -x
  //  }
  var end = MAX_NUM_LEN - 1
  do {
    buf[end--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  //  if (neg) buf[end--] = 45
  val stt = end + 1
  OUT.write(
      buf,
      stt,
      MAX_NUM_LEN - stt + 1, // 개행 포함
  )
}

fun main() {
  val from = readInt()
  val to = readInt()
  for (n in from..to) {
    if (isPrime(n)) writeln(n)
  }

  OUT.flush()
}

private fun isPrime(v: Int): Boolean {
  if (v < 2) return false
  var i = 2
  while (i * i <= v) { // 4 ~
    if (v % i == 0) return false
    i++
  }
  return true
}
