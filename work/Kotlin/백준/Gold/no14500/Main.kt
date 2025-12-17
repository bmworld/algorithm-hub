package 백준.Gold.no14500

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 80_000
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
  fun updateMax(sum: Int) {
    if (sum > max) max = sum
  }

  // 1x4
  repeat(rs) { r ->
    repeat(cs - 3) { c ->
      updateMax(a[r][c] + a[r][c + 1] + a[r][c + 2] + a[r][c + 3])
    }
  }

  // 4x1
  repeat(rs - 3) { r ->
    repeat(cs) { c ->
      updateMax(a[r][c] + a[r + 1][c] + a[r + 2][c] + a[r + 3][c])
    }
  }

  // 2x2
  repeat(rs - 1) { r ->
    repeat(cs - 1) { c ->
      updateMax(a[r][c] + a[r][c + 1] + a[r + 1][c] + a[r + 1][c + 1])
    }
  }

  // 2x3
  repeat(rs - 1) { r ->
    repeat(cs - 2) { c ->
      repeat(2) { tr ->
        updateMax(a[r + tr][c] + a[r + tr][c + 1] + a[r + tr][c + 2] + a[r + 1 - tr][c + 1])
      }
      repeat(2) { lr ->
        repeat(2) { lc ->
          updateMax(a[r + lr][c] + a[r + lr][c + 1] + a[r + lr][c + 2] + a[r + 1 - lr][c + 2 * lc])
        }
      }
      repeat(2) { sr ->
        updateMax(a[r + sr][c] + a[r][c + 1] + a[r + 1][c + 1] + a[r + 1 - sr][c + 2])
      }
    }
  }

  // 3x2
  repeat(rs - 2) { r ->
    repeat(cs - 1) { c ->
      repeat(2) { tc ->
        updateMax(a[r][c + tc] + a[r + 1][c + tc] + a[r + 2][c + tc] + a[r + 1][c + 1 - tc])
      }
      repeat(2) { lr ->
        repeat(2) { lc ->
          updateMax(a[r][c + lc] + a[r + 1][c + lc] + a[r + 2][c + lc] + a[r + 2 * lr][c + 1 - lc])
        }
      }
      repeat(2) { sc ->
        updateMax(a[r][c + sc] + a[r + 1][c] + a[r + 1][c + 1] + a[r + 2][c + 1 - sc])
      }
    }
  }

  w(max)
  O.flush()
}
