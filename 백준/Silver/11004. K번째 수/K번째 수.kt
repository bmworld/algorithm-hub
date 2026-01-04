import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 16
private const val OBS = 1 shl 3
private val O = BufferedOutputStream(System.out, OBS)
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
  O.write(WB, ++pos, WS - pos)
}

fun main() {
  val n = i()
  val k = i() - 1
  val a = IntArray(n)
  repeat(n) {
    a[it] = i()
  }

  fun swap(
    i: Int,
    j: Int,
  ) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
  }

  fun `3way_qs`(
    stt: Int,
    end: Int,
  ) {

    var l = stt
    var r = end
    
    while (l <= r) {
      var pos = l
      var pl = l
      var pr = r
      val piv = a[(l + r) shr 1]

      while (pos <= pr) {
        val v = a[pos]
        when {
          v < piv -> {
            swap(pos, pl)
            pl++
            pos++
          }

          v > piv -> {
            swap(pos, pr)
            pr--
          }

          else -> pos++
        }
      }

      when {
        k < pl -> r = pl - 1
        k > pr -> l = pr + 1
        else -> return
      }
    }
  }

  `3way_qs`(0, n - 1)

  w(a[k])
  O.flush()
}