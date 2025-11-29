import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 14
private const val OBS = 1 shl 10
private const val EOF = -1
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = DataInputStream(System.`in`)
private val IB = ByteArray(IBS)
private var Ii = 0
private var Il = 0

private fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

private fun i(): Int {
  var v = 0
  var c: Byte
  while (r().also { c = it } in 48..57) v = v * 10 + (c - 48)
  return v
}


private const val WB_MAX_LEN = 200
private val WB = ByteArray(WB_MAX_LEN).also {
  for (i in 1 until 200 step 2) it[i] = SPACE
}

private const val NL = 10.toByte()
private const val SPACE = 32.toByte()
private const val DISCONNECTED = 200

fun main() {
  val n = i()
  val WBS = n * 2
  WB[WBS - 1] = NL
  val g = Array(n) { IntArray(n) { DISCONNECTED } }
  repeat(n) { i ->
    repeat(n) { j ->
      if (i() == 1) g[i][j] = 1
    }
  }

  repeat(n) { m ->
    repeat(n) { f ->
      if (f != m) {
        repeat(n) { t ->
          val cur = g[f][t]
          val acc = g[f][m] + g[m][t]
          if (acc < cur) g[f][t] = acc
        }
      }
    }
  }

  repeat(n) { f ->
    repeat(n) { t ->
      WB[t * 2] = if (g[f][t] == DISCONNECTED) 48 else 49
    }
    O.write(WB, 0, WBS)
  }

  O.flush()
}