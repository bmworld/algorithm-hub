package 백준.Silver.no25192

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 20
const val OBS = 10
val O = BufferedOutputStream(System.out, OBS)
val I = BufferedInputStream(System.`in`)
val IB = ByteArray(IBS)
var Ii = 0
var Il = 0
const val EOF = -1

fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

val NUM = 48..57
fun i(): Int {
  var v = 0
  var s = 1
  var b: Byte
  while (r().also { b = it } in NUM || b == 45.toByte()) {
    when (b) {
      in NUM -> v = v * 10 + b - 48
      else -> s = -1
    }
  }
  return s * v
}

const val WS = 10
val WB = ByteArray(WS)
fun w(
  num: Int
) {
  var v = if (num >= 0) num
  else {
    O.write(45)
    -num
  }
  var pos = WS - 1
  do {
    WB[pos--] = (v % 10 + 48).toByte()
    v /= 10
  } while (v > 0)
  O.write(WB, ++pos, WS - pos)
}

val reader = StringBuilder(20)
fun s(): String {
  var b: Byte
  while (r().also { b = it } >= 48) reader.append(b.toInt().toChar())
  val str = reader.toString()
  reader.setLength(0)
  return str
}

fun main() {
  var ans = 0
  val set = HashSet<String>()
  repeat(i()) {
    val str = s()
    when {
      str == "ENTER" -> {
        ans += set.size
        set.clear()
      }
      else -> set.add(str)
    }
  }

  w(ans + set.size)
  O.flush()
}
