package 프로그래머스.Lv0.배열의길이에따라다른연산하기

import util.validate

class Solution {

  fun solution(arr: IntArray, n: Int): IntArray {
    val N = arr.size
    return if (N % 2 == 0) IntArray(N) { arr[it] + if (it % 2 == 1) n else 0 }
    else IntArray(N) { arr[it] + if (it % 2 == 0) n else 0 }
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 57.9MB)
 * 테스트 2 〉	통과 (0.01ms, 60.7MB)
 * 테스트 3 〉	통과 (0.01ms, 61MB)
 * 테스트 4 〉	통과 (0.01ms, 60.9MB)
 * 테스트 5 〉	통과 (0.01ms, 59.4MB)
 * 테스트 6 〉	통과 (0.01ms, 59.9MB)
 * 테스트 7 〉	통과 (0.01ms, 59.5MB)
 * 테스트 8 〉	통과 (0.01ms, 61.3MB)
 * 테스트 9 〉	통과 (0.02ms, 60.3MB)
 * 테스트 10 〉	통과 (0.02ms, 60.4MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(arr: IntArray, n: Int): List<Int> {
 *         return arr.indices.map { arr[it] + if (it % 2 == (if (arr.size % 2 == 0) 1 else 0)) n else 0 }
 *     }
 * }
 * 테스트 1 〉	통과 (13.32ms, 64.2MB)
 * 테스트 2 〉	통과 (12.63ms, 64.3MB)
 * 테스트 3 〉	통과 (12.48ms, 64.3MB)
 * 테스트 4 〉	통과 (12.68ms, 63.9MB)
 * 테스트 5 〉	통과 (12.72ms, 63.8MB)
 * 테스트 6 〉	통과 (12.48ms, 64.7MB)
 * 테스트 7 〉	통과 (12.79ms, 63.6MB)
 * 테스트 8 〉	통과 (12.75ms, 64.7MB)
 * 테스트 9 〉	통과 (13.85ms, 63.3MB)
 * 테스트 10 〉	통과 (13.39ms, 63.9MB)
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(49, 12, 100, 276, 33), 27), intArrayOf(76, 12, 127, 276, 60))
  validate(s.solution(intArrayOf(444, 555, 666, 777), 100), intArrayOf(444, 655, 666, 877))

}
