package 백준.Silver.no1764

private val B: ByteArray = System.`in`.readBytes()
private var Bi = 0
private const val EOF = -1

private fun r(): Int = if (Bi < B.size) (B[Bi++].toInt() and 0xFF) else EOF

private fun i(): Int {
  var c = r()
  while (c != EOF && c <= 32) c = r()

  var neg = false
  if (c == '-'.code) {
    neg = true
    c = r()
  }
  var n = 0
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = r()
  }
  return if (neg) -n else n
}

private val SEP =
    BooleanArray(256).also {
      it[10] = true
      it[13] = true
      it[32] = true
    }

private const val WORD_MAX_LEN = 20
private val sb = StringBuilder(WORD_MAX_LEN)

fun s(): String {
  sb.clear()
  var c = r()
  while (c != EOF && SEP[c]) c = r()
  while (c > 32) {
    sb.append(c.toChar())
    c = r()
  }
  return sb.toString()
}

private const val NL = '\n'

fun main() {
  val n = i()
  val m = i()
  val minSize = if (n > m) m else n
  val a = Array(n) { s() }
  val b = Array(m) { s() }
  a.sort()
  b.sort()
  val arr = ArrayList<String>(minSize)

  var ai = 0
  var bi = 0
  while (ai < n && bi < m) {
    val av = a[ai]
    val bv = b[bi]
    val cmp = av.compareTo(bv)
    if (cmp == 0) {
      arr.add(bv)
      ai++
      bi++
    } else if (cmp < 0) ai++ else bi++
  }

  val size = arr.size
  val OUT = StringBuilder(7 + (WORD_MAX_LEN + 1) * size)
  OUT.append(size).append(NL)
  for (s in arr) OUT.append(s).append(NL)
  print(OUT)
}
