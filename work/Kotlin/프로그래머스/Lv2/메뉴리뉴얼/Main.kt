package 프로그래머스.Lv2.메뉴리뉴얼

import util.validate

class Solution {
  companion object {

    const val A = 65
    const val INT = 1
    const val MAX_COMBINATION = 1012
  }

  fun solution(orders: Array<String>, course: IntArray): Array<String> {
    val N = orders.size
    val bins = IntArray(N)

    for (i in 0 until N) {
      var flag = 0
      for (order in orders[i]) flag = flag or (INT shl order.code - A)
      bins[i] = flag
    }

    val ans = Array(MAX_COMBINATION) { "" }
    val used = HashSet<Int>()
    val top = IntArray(N)

    var ansCnt = 0

    var topCnt = 0
    var maxCnt = 0
    fun dfs(dep: Int, stt: Int, len: Int, o1: String, combined: Int) {
      if (dep == len) {

        if (combined in used) return
        else used.add(combined)

        var cnt = 0
        for (comp in bins)
          if (comp.countOneBits() >= len && combined and comp == combined) cnt++

        if (cnt < 2) return
        else if (cnt == maxCnt) top[topCnt++] = combined
        else if (cnt > maxCnt) {
          maxCnt = cnt
          top[0] = combined
          topCnt = 1
        }
        return
      }

      for (i in stt until o1.length) {
        val x = 1 shl (o1[i].code - A)
        dfs(dep + 1, i + 1, len, o1, combined or x)
      }
    }

    for (len in course) {
      topCnt = 0
      maxCnt = 0

      for (i in 0 until N) {
        val o1 = orders[i]
        val o1Len = o1.length
        if (o1Len < len) continue

        dfs(0, 0, len, o1, 0)
      }

      repeat(topCnt) {
        var x = top[it]
        val order = CharArray(x.countOneBits())
        var i = 0
        while (x > 0) {
          val alpbt = x.countTrailingZeroBits()
          order[i++] = (alpbt + A).toChar()
          x = x xor (INT shl alpbt)
        }

        ans[ansCnt++] = String(order)
      }

      used.clear()
    }

    qs(ans, 0, ansCnt - 1)

    return Array(ansCnt) { ans[it] }
  }


  fun swap(
    a: Array<String>,
    i: Int,
    j: Int,
  ) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
  }

  fun qs(
    a: Array<String>,
    l: Int,
    r: Int,
  ) {
    if (l >= r) return

    val m = (l + r) shr 1
    val piv = a[m]
    swap(a, m, r)

    var pos = l
    for (i in l until r) if (comp(a[i], piv)) swap(a, pos++, i)
    if (comp(piv, a[pos])) swap(a, pos, r)

    qs(a, l, pos - 1)
    qs(a, pos + 1, r)
  }

  fun comp(a: String, b: String): Boolean {

    val aLen = a.length
    val bLen = b.length

    for (i in 0 until minOf(aLen, bLen)) {
      val ax = a[i]
      val bx = b[i]
      if (ax < bx) return true
      if (ax > bx) return false
    }

    return aLen < bLen
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.52ms, 61MB)
 * 테스트 2 〉	통과 (0.34ms, 59.9MB)
 * 테스트 3 〉	실패 (런타임 에러)
 * 테스트 4 〉	실패 (런타임 에러)
 * 테스트 5 〉	통과 (0.39ms, 60.7MB)
 * 테스트 6 〉	통과 (0.66ms, 59.8MB)
 * 테스트 7 〉	통과 (0.72ms, 59.2MB)
 * 테스트 8 〉	통과 (1.95ms, 62.6MB)
 * 테스트 9 〉	통과 (1.68ms, 63.2MB)
 * 테스트 10 〉	실패 (런타임 에러)
 * 테스트 11 〉	통과 (2.03ms, 64.6MB)
 * 테스트 12 〉	통과 (2.27ms, 64.5MB)
 * 테스트 13 〉	실패 (런타임 에러)
 * 테스트 14 〉	통과 (1.93ms, 64.5MB)
 * 테스트 15 〉	실패 (런타임 에러)
 * 테스트 16 〉	통과 (1.36ms, 62.7MB)
 * 테스트 17 〉	통과 (1.37ms, 62.7MB)
 * 테스트 18 〉	통과 (1.40ms, 62.6MB)
 * 테스트 19 〉	통과 (0.67ms, 59.8MB)
 * 테스트 20 〉	통과 (1.44ms, 62.4MB)
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

  validate(s.solution(arrayOf("XYZ", "XWY", "WXA"),
    intArrayOf(2, 3, 4)),
    arrayOf("WX", "XY")
  )

  validate(s.solution(arrayOf("ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"),
    intArrayOf(2, 3, 5)),
    arrayOf("ACD", "AD", "ADE", "CD", "XYZ")
  )


}

//         println("[$len] ${combined.toString(2)} in $o1 ---> cnt = $cnt")
//  println(
//          "ㄴ [$dep] -> $i -> ${combined.toString(2)} or ${x.toString(2)} -> ${nxt.toString(2)}")
//      println("[$len] -> topCnt=$topCnt, maxCnt=$maxCnt")
