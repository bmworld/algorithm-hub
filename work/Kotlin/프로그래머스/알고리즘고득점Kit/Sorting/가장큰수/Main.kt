package 프로그래머스.알고리즘고득점Kit.Sorting.가장큰수

import util.validate

class Solution {

  val ZERO = 48
  val DIGITS = 10
  val GREATER = 1
  val LOWER = -1
  val EQUAL = 0
  fun solution(nums: IntArray): String {
    val digits = Array(DIGITS) { mutableListOf<String>() }
    var totalLen = 0
    for (n in nums) {
      val str = n.toString()
      digits[str[0].code - ZERO] += str
      totalLen += str.length
    }

    val ans = CharArray(totalLen)
    var ai = 0
    for (x in 9 downTo 1) {
      val arr = digits[x]
      val len = arr.size
      if (len == 0) continue

      qs(arr, 0, len - 1)

      for (i in 0 until len) arr[i]
        .forEach { c -> ans[ai++] = c }
    }

    if (ai == 0) {
      ai++
      ans[0] = ZERO.toChar()
    } else {
      val zeros = digits[0]
      val len = zeros.size
      repeat(len) {
        ans[ai++] = ZERO.toChar()
      }
    }

    return ans.concatToString(0, ai)
  }

  fun swap(
    a: MutableList<String>,
    i: Int,
    j: Int,
  ) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
  }

  fun qs(
    a: MutableList<String>,
    l: Int,
    r: Int,
  ) {
    if (l >= r) return

    var pos = l
    var pl = l
    var pr = r
    val piv = a[(l + r) shr 1]

    loop@ while (pos <= pr) {
      val x = a[pos]
      val result = comp(x, piv)
      when (result) {
        GREATER -> swap(a, pos++, pl++)
        LOWER -> swap(a, pos, pr--)
        else -> pos++
      }
    }

    qs(a, l, pl - 1)
    qs(a, pr + 1, r)
  }

  fun comp(x: String, piv: String): Int {
    val xLen = x.length
    val pLen = piv.length
    val len = pLen + xLen

    for (i in 0 until len) {
      val xChar = if (i < xLen) x[i] else piv[i - xLen]
      val pChar = if (i < pLen) piv[i] else x[i - pLen]
      when {
        xChar > pChar -> return GREATER
        xChar < pChar -> return LOWER
      }
    }

    return EQUAL
  }
}

/**
 * ```
 * ME v1:
 * 테스트 1 〉	통과 (60.34ms, 75.9MB)
 * 테스트 2 〉	통과 (37.51ms, 73.4MB)
 * 테스트 3 〉	통과 (81.51ms, 79.6MB)
 * 테스트 4 〉	통과 (10.14ms, 63.6MB)
 * 테스트 5 〉	통과 (54.90ms, 76.1MB)
 * 테스트 6 〉	통과 (49.34ms, 75.6MB)
 * 테스트 7 〉	통과 (4.98ms, 63.9MB)
 * 테스트 8 〉	통과 (5.12ms, 64.3MB)
 * 테스트 9 〉	통과 (4.97ms, 63.7MB)
 * 테스트 10 〉	통과 (5.10ms, 64.3MB)
 * 테스트 11 〉	통과 (4.92ms, 62.9MB)
 * 테스트 12 〉	통과 (7.08ms, 63.5MB)
 * 테스트 13 〉	통과 (4.89ms, 64.6MB)
 * 테스트 14 〉	통과 (5.29ms, 64.2MB)
 * 테스트 15 〉	통과 (4.82ms, 64.5MB)
 *
 * ME v2:
 * 테스트 1 〉	통과 (47.50ms, 81MB)
 * 테스트 2 〉	통과 (30.22ms, 78.1MB)
 * 테스트 3 〉	통과 (44.18ms, 86.2MB)
 * 테스트 4 〉	통과 (8.53ms, 64.1MB)
 * 테스트 5 〉	통과 (41.65ms, 73.3MB)
 * 테스트 6 〉	통과 (45.01ms, 80.1MB)
 * 테스트 7 〉	통과 (6.18ms, 64.2MB)
 * 테스트 8 〉	통과 (5.21ms, 63.5MB)
 * 테스트 9 〉	통과 (4.83ms, 65.6MB)
 * 테스트 10 〉	통과 (4.73ms, 64.6MB)
 * 테스트 11 〉	통과 (5.64ms, 64.3MB)
 * 테스트 12 〉	통과 (4.81ms, 63.6MB)
 * 테스트 13 〉	통과 (4.69ms, 64.5MB)
 * 테스트 14 〉	통과 (4.67ms, 63.9MB)
 * 테스트 15 〉	통과 (5.13ms, 63MB)
 * ```
 *
 *
 * ```
 * RIVAL:
 * class Solution {
 *     fun solution(numbers: IntArray): String {
 *         var answer = ""
 *         numbers.sortedWith(Comparator({num1: Int, num2: Int -> "$num2$num1".compareTo("$num1$num2")})).forEach { answer += it }
 *         if ("(0*)".toRegex().replace(answer, "").isEmpty()) {
 *             answer = "0"
 *         }
 *         return answer
 *     }
 * }
 *
 * 테스트 1 〉	통과 (1032.24ms, 376MB)
 * 테스트 2 〉	통과 (389.75ms, 373MB)
 * 테스트 3 〉	통과 (1446.74ms, 416MB)
 * 테스트 4 〉	통과 (32.22ms, 77.9MB)
 * 테스트 5 〉	통과 (862.17ms, 371MB)
 * 테스트 6 〉	통과 (681.74ms, 370MB)
 * 테스트 7 〉	통과 (21.38ms, 65.8MB)
 * 테스트 8 〉	통과 (20.15ms, 66.3MB)
 * 테스트 9 〉	통과 (19.79ms, 66.5MB)
 * 테스트 10 〉	통과 (26.29ms, 65MB)
 * 테스트 11 〉	통과 (20.48ms, 66.3MB)
 * 테스트 12 〉	통과 (19.74ms, 65.9MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(
      intArrayOf(9, 99, 999, 90, 89, 98, 90)
    ),
    "99999998909089"
  )

  validate(
    s.solution(
      intArrayOf(0, 0, 0)
    ),
    "0"
  )

  validate(
    s.solution(
      intArrayOf(3, 30, 34, 5, 9)
    ),
    "9534330"
  )

  validate(
    s.solution(
      intArrayOf(1, 0, 0)
    ),
    "100"
  )

  validate(
    s.solution(
      intArrayOf(24, 2, 21, 0, 0)
    ),
    "2422100"
  )

  validate(
    s.solution(
      intArrayOf(1, 112, 1)
    ),
    "11211"
  )

  validate(
    s.solution(
      intArrayOf(403, 40)
    ),
    "40403"
  )

  validate(
    s.solution(
      intArrayOf(1000, 100, 1000)
    ),
    "10010001000"
  )
  // 10001000100
  // 10010001000

  validate(
    s.solution(
      intArrayOf(12, 122, 12)
    ),
    "1221212"
  )

  validate(
    s.solution(
      intArrayOf(121, 1210)
    ),
    "1211210"
  )

  validate(
    s.solution(
      intArrayOf(122, 1222)
    ),
    "1222122"
  )

  var a = charArrayOf()
  a += (32).toChar()

  validate(
    s.solution(
      intArrayOf(432, 4324324)
    ),
    "4324324432"
  )

}
//      println("x=$x vs p=$p")
//        println("pChar = ${pChar}, vs x=$xChar")
