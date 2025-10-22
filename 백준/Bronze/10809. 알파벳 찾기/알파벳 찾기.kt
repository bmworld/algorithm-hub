import java.io.BufferedInputStream

val IN = BufferedInputStream(System.`in`)
const val a = 97
const val z = 122
const val EMPTY = -1
const val ALPHABET_SIZE = 26

fun main() {
  val arr = IntArray(ALPHABET_SIZE) { EMPTY }
  var char = IN.read()
  var pos = 0
  while (char in a..z) {
    if (arr[char - a] == EMPTY) arr[char - a] = pos

    char = IN.read()
    pos++
  }

  val s = StringBuilder(ALPHABET_SIZE * 2)
  for (v in arr) s.append(v).append(' ')
  print(s)
}