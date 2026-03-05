package 백준.Silver.no8892

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 13
const val OBS = 1 shl 8
val O = BufferedOutputStream(System.`out`, OBS)
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

val NUM = 48..57
fun i(): Int {
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

const val NL: Byte = 10
const val IMPOSSIBLE = 48
const val MAX_COMB_CNT = 2
const val MAX_WORD_CNT = 100
const val MAX_WORD_LEN = 10_000

fun main() {
  var b: Byte
  val reader = ByteArray(MAX_WORD_LEN)
  val WORDS = mutableListOf<ByteArray>()
  val USED = BooleanArray(MAX_WORD_CNT)

  var found = false
  fun dfs(dep: Int, end: Int, pos: Int) {
    if (found) return
    if (dep == MAX_COMB_CNT) {
      var valid = true
      for (l in 0 until pos / 2) {
        val r = pos - 1 - l
        if (reader[l] != reader[r]) {
          valid = false
          break
        }
      }

      if (valid) {
        O.write(reader, 0, pos)
        found = true
      }
      return
    }

    for (i in 0..end) {
      if (USED[i]) continue
      val w = WORDS[i]
      val len = w.size
      System.arraycopy(w, 0, reader, pos, len)
      USED[i] = true
      dfs(dep + 1, end, pos + len)
      USED[i] = false
    }
  }

  repeat(i()) {
    val cnt = i()
    repeat(cnt) {
      var len = 0
      found = false
      while (r().also { b = it } >= NL) {
        if (b == NL) break
        reader[len++] = b
      }
      WORDS += reader.copyOfRange(0, len)
    }

    dfs(0, cnt - 1, 0)
    if (!found) O.write(IMPOSSIBLE)
    O.write(10)
    WORDS.clear()
  }

  O.flush()
}

//      println("[$dep] $i -> pos=$pos, len=$len")
//        println("${toChar(reader[l])} ($l) vs ${toChar(reader[r])}($r)")
