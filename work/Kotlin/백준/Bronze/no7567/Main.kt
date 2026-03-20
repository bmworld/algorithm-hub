package 백준.Bronze.no7567

import java.io.BufferedInputStream

const val IBS = 50
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


fun main() {
  val UP: Byte = 40
  var b: Byte

  var ans = 0
  var prv = true
  var cur = true
  while (r().also { b = it } >= UP) {
    cur = (b == UP)
    ans += if (ans == 0 || cur != prv) 10 else 5
    prv = cur
  }

  print(ans)
}
