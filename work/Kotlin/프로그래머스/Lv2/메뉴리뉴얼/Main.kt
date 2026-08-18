package 프로그래머스.Lv2.메뉴리뉴얼

import util.validate

class Solution {
  companion object {

    const val A = 65
    const val INT = 1
    const val MAX_COMBINATION = 1_024
  }

  fun solution(orders: Array<String>, course: IntArray): Array<String> {
    val N = orders.size
    val bins = IntArray(N)

    for (i in orders.indices) {
      var flag = 0
      for (order in orders[i]) flag = flag or (INT shl order.code - A)
      bins[i] = flag
    }

    val ans = Array(MAX_COMBINATION) { "" }
    val top = IntArray(MAX_COMBINATION)
    val used = HashSet<Int>()

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
 * 테스트 1 〉	통과 (0.38ms, 59.6MB)
 * 테스트 2 〉	통과 (0.78ms, 59.7MB)
 * 테스트 3 〉	통과 (0.48ms, 60.8MB)
 * 테스트 4 〉	통과 (0.44ms, 60.3MB)
 * 테스트 5 〉	통과 (0.46ms, 60.1MB)
 * 테스트 6 〉	통과 (0.60ms, 60.2MB)
 * 테스트 7 〉	통과 (0.94ms, 59MB)
 * 테스트 8 〉	통과 (1.72ms, 63MB)
 * 테스트 9 〉	통과 (1.72ms, 63.7MB)
 * 테스트 10 〉	통과 (2.74ms, 64.6MB)
 * 테스트 11 〉	통과 (1.83ms, 65.2MB)
 * 테스트 12 〉	통과 (2.69ms, 62.8MB)
 * 테스트 13 〉	통과 (2.88ms, 65.3MB)
 * 테스트 14 〉	통과 (1.91ms, 64.9MB)
 * 테스트 15 〉	통과 (2.85ms, 64.8MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(orders: Array<String>, course: IntArray): Array<String> {
 *         val candidateList = mutableListOf<String>()
 *
 *     // r = 뽑을 갯수
 *     fun combination(list: List<Char>, r: Int, startIndex: Int = 0, candidates: String = ""): Unit = when (r) {
 *         0 -> {
 *             candidateList.add(candidates)
 *             Unit
 *         }
 *         else -> {
 *             for (i in startIndex until list.count()) {
 *                 combination(list, r - 1, i + 1, candidates + list[i])
 *             }
 *         }
 *     }
 *
 *     orders.forEach { order ->
 *         course.forEach { course ->
 *             combination(order.toList().sorted(), course)
 *         }
 *     }
 *
 *
 *     val answer = mutableListOf<String>()
 *
 *     candidateList.groupingBy { it }.eachCount().toList().groupBy { it.first.length }.forEach { _, pair ->
 *         val maxCount = pair.maxBy { it.second }?.second ?: 0
 *         answer.addAll(pair.filter { it.second >= 2 && it.second == maxCount }.map { it.first })
 *     }
 *     return answer.sorted().toTypedArray()
 *     }
 * }
 * 테스트 1 〉	통과 (20.20ms, 66.3MB)
 * 테스트 2 〉	통과 (26.62ms, 65.8MB)
 * 테스트 3 〉	통과 (24.51ms, 64.7MB)
 * 테스트 4 〉	통과 (21.77ms, 64.6MB)
 * 테스트 5 〉	통과 (27.81ms, 65.5MB)
 * 테스트 6 〉	통과 (22.93ms, 66MB)
 * 테스트 7 〉	통과 (23.37ms, 65.4MB)
 * 테스트 8 〉	통과 (25.39ms, 69.2MB)
 * 테스트 9 〉	통과 (26.85ms, 70.7MB)
 * 테스트 10 〉	통과 (28.15ms, 67.9MB)
 * 테스트 11 〉	통과 (25.42ms, 67.6MB)
 * 테스트 12 〉	통과 (24.41ms, 67.8MB)
 * 테스트 13 〉	통과 (26.89ms, 69.8MB)
 * 테스트 14 〉	통과 (29.20ms, 69.5MB)
 * 테스트 15 〉	통과 (31.93ms, 67.9MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(orders: Array<String>, course: IntArray): Array<String> {
 *         val answer = mutableListOf<String>()
 *         val courseMap = mutableMapOf<Int, MutableMap<String, Int>>()
 *
 *         for(order in orders) {
 *             val menu = order.toCharArray().sortedArray()
 *
 *             for(i in 0 until (1 shl menu.size)) {
 *                 val com = StringBuilder()
 *                 for(j in menu.indices) {
 *                     if((i and (1 shl j) != 0)) com.append(menu[j])
 *                 }
 *                 val key = com.toString()
 *                 courseMap[key.length] = (courseMap[key.length] ?: mutableMapOf()).also { it[key] = (it[key] ?: 0) + 1 }
 *             }
 *         }
 *
 *         for(count in course) {
 *             val max = courseMap[count]?.values?.max() ?: 0
 *             if(max < 2) continue
 *
 *             courseMap[count]!!.entries.forEach { if(it.value == max) answer.add(it.key) }
 *         }
 *
 *         answer.sort()
 *         return answer.toTypedArray()
 *     }
 * }
 * 테스트 1 〉	통과 (18.16ms, 62.1MB)
 * 테스트 2 〉	통과 (17.66ms, 63MB)
 * 테스트 3 〉	통과 (14.56ms, 63.2MB)
 * 테스트 4 〉	통과 (14.49ms, 63MB)
 * 테스트 5 〉	통과 (13.17ms, 63.4MB)
 * 테스트 6 〉	통과 (13.68ms, 64MB)
 * 테스트 7 〉	통과 (14.17ms, 63.6MB)
 * 테스트 8 〉	통과 (25.75ms, 65.8MB)
 * 테스트 9 〉	통과 (18.91ms, 65.9MB)
 * 테스트 10 〉	통과 (21.82ms, 65.9MB)
 * 테스트 11 〉	통과 (16.28ms, 64.8MB)
 * 테스트 12 〉	통과 (17.02ms, 65.6MB)
 * 테스트 13 〉	통과 (18.80ms, 65.7MB)
 * 테스트 14 〉	통과 (27.17ms, 67.3MB)
 * 테스트 15 〉	통과 (19.46ms, 65.7MB)
 *
 *
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
