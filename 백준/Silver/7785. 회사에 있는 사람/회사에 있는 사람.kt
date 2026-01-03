import java.io.*

private const val IBS = 1 shl 15
private val O = BufferedWriter(OutputStreamWriter(System.out))
private val I = BufferedInputStream(System.`in`)
private val IB = ByteArray(IBS)
private var Ii = 0
private var Il = 0
private const val EOF = -1

private fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

private val NUM = 48..57
private fun i(): Int {
  var v = 0
  var s = 1
  var c: Byte
  while (r().also { c = it } in NUM || c == 45.toByte()) {
    when (c) {
      in NUM -> v = v * 10 + c - 48
      else -> s = -1
    }
  }
  return s * v
}


private const val SPACE: Byte = 32
private const val NL: Byte = 10
private const val E: Byte = 101
private const val L: Byte = 108
private val sb = StringBuilder(5)
private fun s(): String {
  var c: Byte
  while (r().also { c = it } >= 10) {
    when (c) {
      NL, SPACE -> break

      else -> {
        sb.append(
          c.toInt()
            .toChar()
        )
      }
    }
  }
  val str = sb.toString()
  sb.setLength(0)
  return str
}

private fun isEnter(): Boolean {
  var enter = true
  var c: Byte
  var done = false
  while (r().also { c = it } >= 10) {
    when (c) {
      NL, SPACE -> break
      E -> if (!done) done = true

      L -> if (!done) {
        done = true
        enter = false
      }
    }
  }
  return enter
}

fun main() {
  val map = sortedMapOf<String, Boolean>(Comparator.reverseOrder())
  repeat(i()) {
    map[s()] = isEnter()
  }
  for (e in map) if (e.value) {
    O.write(e.key)
    O.newLine()
  }
  O.flush()
}