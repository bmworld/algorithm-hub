package 프로그래머스.Lv2.뒤에있는큰수찾기

import util.validate

class Solution {

  companion object {

    val EMPTY = -1

  }

  fun solution(numbers: IntArray): IntArray {
    val N = numbers.size
    var len = N

    val ans = IntArray(N) { EMPTY }
    val tracer = HashSet<Int>()

    repeat(N) {
      val i = N - (it + 1)
      val a = numbers[i]

      for (j in i + 1 until len) {
        val b = numbers[j]
        if (b > a) {
          ans[i] = b
          break
        }
      }

      if (tracer.contains(a)) len--
      else tracer.add(a)
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.03ms, 59.7MB)
 * 테스트 2 〉	통과 (0.03ms, 60.2MB)
 * 테스트 3 〉	통과 (0.02ms, 60.5MB)
 * 테스트 4 〉	통과 (0.11ms, 59.5MB)
 * 테스트 5 〉	통과 (0.48ms, 60.5MB)
 * 테스트 6 〉	통과 (3.86ms, 62.2MB)
 * 테스트 7 〉	통과 (3.85ms, 62.9MB)
 * 테스트 8 〉	실패 (6.23ms, 72MB)
 * 테스트 9 〉	실패 (5.24ms, 72.4MB)
 * 테스트 10 〉	실패 (9.36ms, 79.8MB)
 * 테스트 11 〉	실패 (7.69ms, 82.3MB)
 * 테스트 12 〉	실패 (9.91ms, 94.6MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(2, 3, 3, 5)), intArrayOf(3, 5, 5, -1))
  validate(s.solution(intArrayOf(9, 1, 5, 3, 6, 2)), intArrayOf(-1, 5, 6, 6, -1, -1))
}

//          println("x,j = $b, $j")
