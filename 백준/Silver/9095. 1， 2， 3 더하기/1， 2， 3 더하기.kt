private val IN: ByteArray = System.`in`.readBytes()
private var inPos = 0
private const val EOF = -1

private fun r(): Int = if (inPos < IN.size) (IN[inPos++].toInt() and 0xFF) else EOF

private fun i(): Int {
  var n = 0
  var c = r()
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = r()
  }
  return n
}

fun main() {
  repeat(i()) {
    println(CNT[i() - 1])
  }
}

private val CNT = IntArray(11).also {
  it[0] = 1
  it[1] = 2
  it[2] = 4
  for (i in 3..10) it[i] = it[i - 3] + it[i - 2] + it[i - 1]
}