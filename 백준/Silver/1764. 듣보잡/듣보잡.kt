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

fun main() {
  val n = i()
  val m = i()
  val map = HashSet<String>(n)
  val arr = ArrayList<String>()
  val OUT = StringBuilder((WORD_MAX_LEN + 1) * m)
  repeat(n) { map.add(s()) }
  repeat(m) {
    val s = s()
    if (map.contains(s)) arr.add(s)
  }
  arr.sort()
  OUT.append(arr.size).append('\n')
  for (a in arr) OUT.append(a).append('\n')
  print(OUT.toString())
}