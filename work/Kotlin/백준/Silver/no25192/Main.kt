package 백준.Silver.no25192

import java.io.BufferedInputStream

const val IBS = 1 shl 20
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

val reader = StringBuilder(20)
fun s(): String {
  var b: Byte
  while (r().also { b = it } >= ZERO) reader.append(b.toInt().toChar())

  val str = reader.toString()
  reader.setLength(0)
  return str
}

const val NL: Byte = 10
const val ZERO: Byte = 48
fun main() {

  val ch = HashMap<String, Boolean>()
  var ans = 0
  repeat(i()) {
    val str = s()
    when {
      str == "ENTER" -> ch.clear()
      ch[str] == null -> {
        ch[str] = true
        ans++
      }
    }
  }

  print(ans)
}
