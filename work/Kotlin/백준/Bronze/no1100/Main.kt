package 백준.Bronze.no1100

import java.io.BufferedInputStream

const val IBS = 72
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

const val SIZE = 8
const val PIECE = 70.toByte()
fun main() {
  var ans = 0
  repeat(SIZE) { r ->
    val evenR = r % 2 == 0
    repeat(SIZE) { c ->
      val evenC = c % 2 == 0
      if (r() == PIECE && (evenR && evenC || !evenR && !evenC)) ans++
    }
    r()
  }
  print(ans)
}
