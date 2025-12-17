package 백준.Gold.no14500

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 75_000
private const val OBS = 1_000
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = DataInputStream(System.`in`)
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

private const val WS = 10
private val WB = ByteArray(WS)
private fun w(
  num: Int,
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
  pos++
  O.write(WB, pos, WS - pos)
}

private const val MAX_NUM = 1_000

fun main() {
  val rs = i()
  val cs = i()
  val a = Array(rs) { IntArray(cs) }
  repeat(rs) { r ->
    repeat(cs) { c ->
      a[r][c] = i()
    }
  }

  var max = 0

  // 1x4
  repeat(rs) { r ->
    repeat(cs - 3) { c ->
      val sum = a[r][c] + a[r][c + 1] + a[r][c + 2] + a[r][c + 3]
      if (sum > max) max = sum
    }
  }

  // 4x1
  repeat(rs - 3) { r ->
    repeat(cs) { c ->
      val sum = a[r][c] + a[r + 1][c] + a[r + 2][c] + a[r + 3][c]
      if (sum > max) max = sum
    }
  }

  // 2x2
  repeat(rs - 1) { r ->
    repeat(cs - 1) { c ->
      val sum = a[r][c] + a[r][c + 1] + a[r + 1][c] + a[r + 1][c + 1]
      if (sum > max) max = sum
    }
  }

  // 2x3
  repeat(rs - 1) { r ->
    repeat(cs - 2) { c ->
      val six = a[r][c] + a[r][c + 1] + a[r][c + 2] + a[r + 1][c] + a[r + 1][c + 1] + a[r + 1][c + 2]
      var two = MAX_NUM * 2
      repeat(2) { tr ->
        repeat(3) { tc ->
          val sum = a[r + tr][c + tc] + a[r + tr][c + (tc + 1) % 3]
          if (sum < two) two = sum
        }
      }
      repeat(2) { tr ->
        val sum = a[r + tr][c] + a[r + 1 - tr][c + 2]
        if (sum < two) two = sum
      }
      val sum = six - two
      if (sum > max) max = sum
    }
  }

  // 3x2
  repeat(rs - 2) { r ->
    repeat(cs - 1) { c ->
      val six = a[r][c] + a[r + 1][c] + a[r + 2][c] + a[r][c + 1] + a[r + 1][c + 1] + a[r + 2][c + 1]
      var two = MAX_NUM * 2
      repeat(3) { tr ->
        repeat(2) { tc ->
          val sum = a[r + tr][c + tc] + a[r + (tr + 1) % 3][c + tc]
          if (sum < two) two = sum
        }
      }
      repeat(2) { tc ->
        val sum = a[r][c + tc] + a[r + 2][c + 1 - tc]
        if (sum < two) two = sum
      }
      val sum = six - two
      if (sum > max) max = sum
    }
  }

  w(max)
  O.flush()
}
