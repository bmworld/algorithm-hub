package 백준.Silver.no10814

import java.io.BufferedInputStream

private const val MAX_AGE = 200

fun main() {
  val n = readInt()
  val arr = Array(MAX_AGE + 1) { mutableListOf<String>() }
  var minAge = MAX_AGE
  var maxAge = 0
  repeat(n) {
    val age = readInt()
    arr[age].add(readString())
    if (age > maxAge) maxAge = age
    if (age < minAge) minAge = age
  }

  val sb = StringBuilder(n * 20)
  val agePrefix = Array(MAX_AGE + 1) { i -> "$i " }

  for (age in minAge..maxAge) {
    val prefix = agePrefix[age]
    for (name in arr[age]) sb.append(prefix).append(name).append('\n')
  }

  print(sb)
}

val IN = BufferedInputStream(System.`in`, 1 shl 20)

private fun readInt(): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read()
  var n = 0
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = IN.read()
  }
  return n
}

val nb = StringBuilder(100)

fun readString(): String {
  nb.clear()
  var c = IN.read()
  while (c <= 32) c = IN.read()
  while (c > 32) {
    nb.append(c.toChar())
    c = IN.read()
  }

  return nb.toString()
}
