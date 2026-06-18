package 프로그래머스.Lv1.나누어떨어지는숫자배열

import util.validate

class Solution {

  fun solution(arr: IntArray, divisor: Int): IntArray {
    val tmp = IntArray(arr.size)
    var len = 0
    for (x in arr) if (x % divisor == 0) tmp[len++] = x
    if (len == 0) return intArrayOf(-1)

    var ans = IntArray(len)
    System.arraycopy(tmp, 0, ans, 0, len)
    qs(ans, 0, len - 1)

    return ans
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

    var pos = l
    var pl = l
    var pr = r
    val piv = a[(l + r) shr 1]

    loop@ while (pos <= pr) {
      val x = a[pos]
      when {
        x < piv -> swap(a, pos++, pl++)
        x > piv -> swap(a, pos, pr--)
        else -> pos++
      }
    }

    qs(a, l, pl - 1)
    qs(a, pr + 1, r)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 60.1MB)
 * 테스트 2 〉	통과 (0.01ms, 59.5MB)
 * 테스트 3 〉	통과 (0.02ms, 59.8MB)
 * 테스트 4 〉	통과 (0.03ms, 58.3MB)
 * 테스트 5 〉	통과 (0.05ms, 58.8MB)
 * 테스트 6 〉	통과 (1.26ms, 63.5MB)
 * 테스트 7 〉	통과 (0.06ms, 59.7MB)
 * 테스트 8 〉	통과 (0.01ms, 59MB)
 * 테스트 9 〉	통과 (0.06ms, 59.5MB)
 * 테스트 10 〉	통과 (0.15ms, 60.1MB)
 * ```
 *
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(arr: IntArray, divisor: Int): IntArray {
 *         var answer = intArrayOf()
 *
 *         arr.forEach { if (it % divisor == 0) answer += it }
 *         answer.sort()
 *
 *         if (answer.size == 0) answer += -1
 *
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (7.18ms, 62.4MB)
 * 테스트 2 〉	통과 (7.15ms, 63.2MB)
 * 테스트 3 〉	통과 (7.44ms, 62.6MB)
 * 테스트 4 〉	통과 (7.31ms, 62.2MB)
 * 테스트 5 〉	통과 (6.45ms, 62.3MB)
 * 테스트 6 〉	통과 (9.30ms, 67.3MB)
 * 테스트 7 〉	통과 (6.97ms, 62MB)
 * 테스트 8 〉	통과 (7.44ms, 63MB)
 * 테스트 9 〉	통과 (6.33ms, 63.6MB)
 * 테스트 10 〉	통과 (8.83ms, 62.1MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(5, 8, 7, 10), 5), intArrayOf(5, 10))
  validate(s.solution(intArrayOf(5, 8, 7, 10), 1), intArrayOf(5, 7, 8, 10))
  validate(s.solution(intArrayOf(9, 3, 9), 3), intArrayOf(3, 9, 9))
  validate(s.solution(intArrayOf(3, 2, 6), 10), intArrayOf(-1))
}
