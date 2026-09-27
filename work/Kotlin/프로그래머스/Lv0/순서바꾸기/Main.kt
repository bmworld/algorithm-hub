package 프로그래머스.Lv0.순서바꾸기

import util.validate

class Solution {

  fun solution(num_list: IntArray, n: Int): IntArray {
    val N = num_list.size
    return IntArray(N) {
      val i = it + n
      num_list[if (i < N) i else i - N]
    }
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 60.2MB)
 * 테스트 2 〉	통과 (0.01ms, 57.8MB)
 * 테스트 3 〉	통과 (0.01ms, 59.6MB)
 * 테스트 4 〉	통과 (0.01ms, 60MB)
 * 테스트 5 〉	통과 (0.01ms, 59.9MB)
 * 테스트 6 〉	통과 (0.01ms, 60.3MB)
 * 테스트 7 〉	통과 (0.01ms, 60.4MB)
 * 테스트 8 〉	통과 (0.04ms, 57.4MB)
 * 테스트 9 〉	통과 (0.01ms, 61.3MB)
 * 테스트 10 〉	통과 (0.01ms, 59.4MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(numList: IntArray, n: Int) = (numList + numList).copyOfRange(n, n + numList.size)
 * }
 * 테스트 1 〉	통과 (6.29ms, 64.2MB)
 * 테스트 2 〉	통과 (6.27ms, 62.8MB)
 * 테스트 3 〉	통과 (6.23ms, 64MB)
 * 테스트 4 〉	통과 (6.27ms, 63.8MB)
 * 테스트 5 〉	통과 (6.26ms, 63.7MB)
 * 테스트 6 〉	통과 (6.77ms, 62.9MB)
 * 테스트 7 〉	통과 (7.30ms, 62.9MB)
 * 테스트 8 〉	통과 (7.85ms, 62.8MB)
 * 테스트 9 〉	통과 (6.22ms, 63.3MB)
 * 테스트 10 〉	통과 (6.06ms, 63.2MB)
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(2, 1, 6), 1), intArrayOf(1, 6, 2))
  validate(s.solution(intArrayOf(5, 2, 1, 7, 5), 3), intArrayOf(7, 5, 5, 2, 1))
}
