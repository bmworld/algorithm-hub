package 프로그래머스.Lv2.메뉴리뉴얼

import util.validate

class Solution {
  companion object {

    const val BINARY_INT_SIZE = 32
    const val A = 65
    const val Z = 90
    const val INT = 1
  }

  fun solution(orders: Array<String>, course: IntArray): Array<String> {

    val map = HashMap<Int, MutableList<Int>>()
    var maxLen = 0
    for (order in orders) {
      var flag = 0
      for (menu in order) flag = flag or (INT shl (Z - menu.code))

      val len = order.length
      if (map[len] == null) map[len] = mutableListOf(flag)
      else map[len]!! += flag

      if (len > maxLen) maxLen = len
    }

    val ans = IntArray(orders.size)
    var ansLen = 0
    for (len1 in course) {
      val odrs1 = map[len1] ?: continue

      val top = IntArray(odrs1.size)
      var topCnt = 0
      var maxCnt = 0
      for (o1 in odrs1) {
        var cnt = 0
        for (len2 in len1 + 1..maxLen) for (o2 in map[len2] ?: continue) if (o1 and o2 == o1) cnt++

        println("[${o1.toString(2)}] ->cnt = ${cnt}")
        if (cnt == 0) continue
        else if (cnt == maxCnt) top[topCnt++] = o1
        else if (cnt > maxCnt) {
          maxCnt = cnt
          top[0] = o1
          topCnt = 1
        }
      }

      repeat(topCnt) {
        ans[ansLen++] = top[it]
      }
    }

    qs(ans, 0, ansLen - 1)
    println("ansLen = ${ansLen}")

    return Array(ansLen) {
      var x = ans[it]
      println("x = ${x}")
      val order = CharArray(x.countOneBits())
      var i = 0
      while (x > 0) {
        val alpbt = BINARY_INT_SIZE - (x.countLeadingZeroBits() + 1)
        order[i++] = (Z - alpbt).toChar()
        x = x xor (INT shl alpbt)
      }

      String(order)
    }
  }


  fun swap(
    a: IntArray,
    i: Int,
    j: Int,
  ) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
  }

  fun qs(
    a: IntArray,
    l: Int,
    r: Int,
  ) {
    if (l >= r) return

    val m = (l + r) shr 1
    val piv = a[m]
    println("[$l~$r] -> a[$m] = ${a[m].toString(2)}")
    swap(a, m, r)

    var pos = l
    for (i in l until r) if (a[i] < piv) swap(a, pos++, i)
    if (piv < a[pos]) swap(a, pos, r)
    println("a[$pos] = ${a[pos].toString(2)}")


    qs(a, l, pos - 1)
    qs(a, pos + 1, r)
  }
}

/**
 * ```
 * [ME]
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(arrayOf("ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"),
    intArrayOf(2, 3, 4)),
    arrayOf("AC", "ACDE", "BCFG", "CDE")
  )
//
//  validate(s.solution(arrayOf("ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"),
//    intArrayOf(2, 3, 5)),
//    arrayOf("ACD", "AD", "ADE", "CD", "XYZ")
//  )
//
//  validate(s.solution(arrayOf("XYZ", "XWY", "WXA"),
//    intArrayOf(2, 3, 4)),
//    arrayOf("WX", "XY")
//  )

}

//      println("[$len] ${map[len]!!.size} -> ${flag.toString(2)}")
//  println("[$len1 -> $len2] ${o1.toString(2)} and ${o2.toString(2)} -> ${
//              (o1 and o2).toString(2)
//            }")
