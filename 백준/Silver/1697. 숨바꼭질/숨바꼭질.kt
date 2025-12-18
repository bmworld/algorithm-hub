import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 5
private const val OBS = 1 shl 4
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

private const val WS = 6
private val WB = ByteArray(WS)

private fun w(
  num: Int,
) {
  var x = num
  var end = WS - 1
  do {
    WB[end--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  val stt = end + 1
  O.write(WB, stt, WS - stt)
}

fun main() {
  val n = i()
  val k = i()
  w(
    when {
    n >= k -> n - k

    else -> {
      var min = k - n
      val timer = IntArray(k * 2 + 1) { min }
      val q = IntArray(k * 2 + 1)
      var head = 0
      var tail = 0
      q[tail++] = k

      var t = 0
      timer[k] = t
      while (head < tail) {
        val pos = q[head++]
        val ct = timer[pos]
        if (ct >= min) continue
        timer[pos] = ct

        when {
          pos == n -> min = ct

          else -> {
            val nt = ct + 1
            val pnp = pos + 1
            if (nt < timer[pnp]) {
              q[tail++] = pnp
              timer[pnp] = nt
            }

            val snp = pos - 1
            if (nt < timer[snp]) {
              q[tail++] = snp
              timer[snp] = nt
            }

            val mnp = pos / 2
            if (pos % 2 == 0 && pos >= n && nt < timer[mnp]) {
              q[tail++] = mnp
              timer[mnp] = nt
            }
          }
        }
        t++
      }
      min
    }
  })
  O.flush()
}