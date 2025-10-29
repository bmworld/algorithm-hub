package 백준.Silver.no1181

import java.io.BufferedInputStream

const val EMPTY = ""

fun main() {
  var n = readInt()
  val sb = StringBuilder(50)
  val arr = Array(n) { EMPTY }

  var totalLen = n
  var uniqCnt = 0
  while (n-- > 0) {
    sb.clear()
    var aLen = 0
    var c = IN.read()
    while (c <= 32) c = IN.read()
    while (c in 97..122) {
      val toChar = c.toChar()
      sb.append(toChar)
      c = IN.read()
      aLen++
    }

    val cur = sb.toString()
    var search = true
    var nextIdx = uniqCnt
    var uniqWord = true
    for (i in 0..<uniqCnt) {
      if (!search) break
      val str = arr[i]
      val bLen = str.length
      if (cur == str) { // 동일문자
        uniqWord = false
        break
      } else if (aLen < bLen) { // 길이순
        nextIdx = i
        break
      } else if (aLen == bLen) { // 사전순
        for (j in 0..<aLen) {
          val a = cur[j]
          val b = str[j]
          if (a == b) continue
          else if (a > b) {
            nextIdx = i + 1
            break
          } else {
            nextIdx = i
            search = false //
            break
          }
        }
      }
    }

    if (!uniqWord) continue
    // SORT
    if (nextIdx == uniqCnt) arr[uniqCnt] = cur
    else {
      for (i in uniqCnt - 1 downTo nextIdx) arr[i + 1] = arr[i]
      arr[nextIdx] = cur
    }

    uniqCnt++
    totalLen += aLen
  }

  val r = StringBuilder(totalLen)
  for (i in 0..<uniqCnt) r.appendLine(arr[i])
  print(r)
}

val IN = BufferedInputStream(System.`in`)

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
