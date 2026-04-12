package 백준.Silver.no1343

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 50
const val OBS = 50
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

const val EOL: Byte = 10
const val DOT: Byte = 46
const val A: Byte = 65
const val B: Byte = 66
val ERROR = byteArrayOf(45, 49)
const val MAX_LEN = 50

fun main() {
  var b: Byte
  val a = ByteArray(MAX_LEN)
  var i = 0
  var polio = 0
  while (r().also { b = it } >= EOL) {
    when (b) {
      DOT, EOL -> {
        if (polio % 2 != 0) break
        val bCnt = polio % 4
        val aCnt = polio - bCnt
        repeat(aCnt) {
          a[i++] = A
        }
        repeat(bCnt) {
          a[i++] = B
        }

        polio = 0
        if (b == DOT) a[i++] = b
        else break

      }
      else -> polio++
    }
  }

  if (polio == 0)
    O.write(a, 0, i)
  else O.write(ERROR)
  O.flush()
}
