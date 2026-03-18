package 백준.Silver.no3986

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

const val EOL: Byte = 10
const val EMPTY: Byte = -1
fun main() {

  var ans = 0

  val ch = HashMap<Int, Boolean>()

  repeat(i()) {
    var prv: Byte = EMPTY
    var cur: Byte

    var cnt = 0
    while (r().also { cur = it; if (prv == EMPTY) prv = it } >= EOL) {
      if (cur != prv) {
        if (ch[cnt] == true) ch.remove(cnt)
        else ch[cnt] = true
        cnt = 0
      }

      when (cur) {
        EOL -> break
        else -> cnt++
      }

      prv = cur
    }

    if (ch.isEmpty()) ans++
    else ch.clear()
  }

  print(ans)
}
