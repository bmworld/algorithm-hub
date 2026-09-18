package 프로그래머스.Lv2.점찍기

import util.validate

class Solution {

  fun solution(k: Int, d: Int): Long {
    var ans = 0L

    val dd = d.toLong() * d
    val k = k.toLong()

    var a = d - d % k
    var b = 0L
    l@ while (a > b) {
      while (dd >= a * a + (b + k) * (b + k)) {
        if (a == b + k) break@l
        b += k
      }
      ans += (b / k + 1) * 2
      a -= k
    }

    ans += (a / k + 1) * (a / k + 1)
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.21ms, 58.6MB)
 * 테스트 2 〉	통과 (0.22ms, 59.5MB)
 * 테스트 3 〉	통과 (0.24ms, 60.7MB)
 * 테스트 4 〉	통과 (0.24ms, 60.7MB)
 * 테스트 5 〉	통과 (0.27ms, 61.1MB)
 * 테스트 6 〉	통과 (0.27ms, 60.5MB)
 * 테스트 7 〉	통과 (0.22ms, 59.9MB)
 * 테스트 8 〉	통과 (0.59ms, 60.7MB)
 * 테스트 9 〉	통과 (0.23ms, 61.5MB)
 * 테스트 10 〉	통과 (0.29ms, 59MB)
 * 테스트 11 〉	통과 (3.76ms, 60.9MB)
 * 테스트 12 〉	통과 (0.20ms, 59.7MB)
 * v2:
 * 테스트 1 〉	통과 (0.02ms, 61.6MB)
 * 테스트 2 〉	통과 (0.02ms, 60.2MB)
 * 테스트 3 〉	통과 (0.10ms, 60.4MB)
 * 테스트 4 〉	통과 (0.08ms, 60.6MB)
 * 테스트 5 〉	통과 (0.14ms, 61.1MB)
 * 테스트 6 〉	통과 (0.13ms, 60.8MB)
 * 테스트 7 〉	통과 (0.11ms, 59MB)
 * 테스트 8 〉	통과 (1.31ms, 60.9MB)
 * 테스트 9 〉	통과 (0.15ms, 59.7MB)
 * 테스트 10 〉	통과 (0.29ms, 60MB)
 * 테스트 11 〉	통과 (3.41ms, 59.6MB)
 * 테스트 12 〉	통과 (0.02ms, 61.1MB)
 *
 *
 * [RIVAL 1]
 * import kotlin.math.*
 * import java.util.*
 * class Solution {
 *
 *     fun solution(kk: Int, dd: Int): Long {
 *         var answer = 0L
 *         var y = 0L
 *         var k = kk.toLong()
 *         var d = dd.toLong()
 *         var ddd = d*d
 *         var x = d - d % k
 *
 *         while(y <= d){
 *             var yyy = y*y
 *             while(x > 0 && x*x + yyy > ddd  ){
 *                 x -= k
 *             }
 *             answer += (x / k) +1
 *             y += k
 *         }
 *         return answer
 *     }
 *
 * }
 * 테스트 1 〉	통과 (0.02ms, 60.9MB)
 * 테스트 2 〉	통과 (0.02ms, 59.5MB)
 * 테스트 3 〉	통과 (0.18ms, 58.7MB)
 * 테스트 4 〉	통과 (0.13ms, 58MB)
 * 테스트 5 〉	통과 (0.27ms, 59.5MB)
 * 테스트 6 〉	통과 (0.23ms, 61.4MB)
 * 테스트 7 〉	통과 (0.11ms, 59.9MB)
 * 테스트 8 〉	통과 (1.94ms, 60.2MB)
 * 테스트 9 〉	통과 (0.27ms, 59.9MB)
 * 테스트 10 〉	통과 (0.47ms, 60.1MB)
 * 테스트 11 〉	통과 (5.45ms, 60.6MB)
 * 테스트 12 〉	통과 (0.02ms, 60.3MB)
 *
 * [RIVAL 2]
 * import kotlin.math.sqrt
 * class Solution {
 *     fun solution(k: Int, d: Int): Long {
 *         var answer: Long = 0
 *         for (i in 0..d step k) {
 *             answer += (sqrt((d.toDouble() * d - i.toDouble() * i)) / k).toLong() + 1
 *         }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (0.21ms, 59.1MB)
 * 테스트 2 〉	통과 (0.21ms, 60.3MB)
 * 테스트 3 〉	통과 (0.31ms, 61MB)
 * 테스트 4 〉	통과 (0.25ms, 60.8MB)
 * 테스트 5 〉	통과 (0.35ms, 60.6MB)
 * 테스트 6 〉	통과 (0.39ms, 59.3MB)
 * 테스트 7 〉	통과 (0.25ms, 61MB)
 * 테스트 8 〉	통과 (1.46ms, 60.1MB)
 * 테스트 9 〉	통과 (0.33ms, 60.8MB)
 * 테스트 10 〉	통과 (0.51ms, 59.5MB)
 * 테스트 11 〉	통과 (5.66ms, 59.3MB)
 * 테스트 12 〉	통과 (0.24ms, 59.3MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(2, 1), 1)
  validate(s.solution(1, 1), 3)
  validate(s.solution(1, 10), 90)
  validate(s.solution(2, 4), 6)
  validate(s.solution(1, 5), 26)
  validate(s.solution(1, 1_000_000), 785_399_162_407)

  // 아주 작은 수
  validate(s.solution(2, 5), 8)

// 적당한 수
  validate(s.solution(3, 10), 13)

// 큰 수
  validate(s.solution(1000, 1_000_000), 786_388)
}
