package 백준.Silver.no15663

import java.io.DataInputStream

private const val IBS = 64
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

fun main() {
  val n = i()
  val m = i()
  val a = IntArray(n)
  val ch = BooleanArray(n)
  val out = IntArray(m)

  repeat(n) {
    val v = i()
    var j = it
    for (i in it - 1 downTo 0) {
      if (a[i] > v) {
        a[i + 1] = a[i]
        j = i
        continue
      }
      j = i + 1
      break
    }
    a[j] = v
  }

  val outCh = HashMap<String, Boolean>()
  val archive = StringBuilder(2 * m)

  fun dfs(
    dep: Int,
  ) {
    if (dep == m) {

      archive.setLength(0)
      repeat(m) { i ->
        archive.append(out[i])
          .append(' ')
      }

      val result = archive.toString()
      if (outCh[result] == true) return
      outCh[result] = true
      println(result)
      return
    }

    repeat(n) { i ->
      if (ch[i]) return@repeat
      ch[i] = true
      out[dep] = a[i]
      dfs(dep + 1)
      ch[i] = false
    }
  }

  dfs(0)
}
