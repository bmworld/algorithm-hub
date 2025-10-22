package util

import java.io.BufferedInputStream

fun getStringArr(s: String): Array<String> {
  return s.trim().lines().toTypedArray()
}

fun getStringSet(s: String): LinkedHashSet<String> {
  val set = LinkedHashSet<String>()
  s.trim().lines().forEach { set.add(it) }
  return set
}

fun getIntArr(s: String): IntArray {
  return s.trim().split(" ").map { it.toInt() }.toIntArray()
}

fun readInt(IN: BufferedInputStream): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read()
  var sign = 1
  if (c == '-'.code) { // 부호 처리
    sign = -1
    c = IN.read()
  }
  var n = 0
  while (c in '0'.code..'9'.code) {
    n = n * 10 + (c - '0'.code)
    c = IN.read()
  }
  return n * sign
}

fun readString(IN: BufferedInputStream): String {
  val sb = StringBuilder()
  var c = IN.read()
  val isEnd = c != -1
  while (c <= 32 && isEnd) c = IN.read()
  while (c > 32) {
    sb.append(c.toChar())
    c = IN.read()
  }

  return sb.toString()
}
