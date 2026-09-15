package 프로그래머스.Lv2.Summer_Winter_Coding_2019.멀쩡한사각형

import util.validate

class Solution {

  fun solution(W: Int, H: Int): Long {
    var cut = 0L

    val W = W.toDouble()
    var w1 = 0.0
    repeat(H) {
      var h = it + 1
      val w2 = h * W / H
      cut += (ceil(w2) - w1.toInt()).toLong()
      w1 = w2
    }

    return W.toLong() * H.toLong() - cut
  }

  private fun ceil(x: Double): Int {
    val int = x.toInt()
    return when {
      x == int.toDouble() || x < 0 -> int
      else -> int + 1
    }
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.91ms, 58.9MB)
 * 테스트 2 〉	통과 (0.55ms, 57.4MB)
 * 테스트 3 〉	통과 (0.36ms, 59.6MB)
 * 테스트 4 〉	통과 (0.12ms, 58.2MB)
 * 테스트 5 〉	통과 (0.37ms, 60.7MB)
 * 테스트 6 〉	통과 (2.74ms, 60.2MB)
 * 테스트 7 〉	통과 (0.50ms, 59.5MB)
 * 테스트 8 〉	통과 (0.49ms, 60.3MB)
 * 테스트 9 〉	통과 (0.02ms, 61.5MB)
 * 테스트 10 〉	통과 (0.02ms, 60MB)
 * 테스트 11 〉	통과 (0.02ms, 59.8MB)
 * 테스트 12 〉	통과 (147.76ms, 58.3MB)
 * 테스트 13 〉	통과 (172.37ms, 59.2MB)
 * 테스트 14 〉	통과 (97.34ms, 59.9MB)
 * 테스트 15 〉	통과 (47.84ms, 59.6MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun gcd(a: Int, b: Int): Long {
 *         if (a==0) return b.toLong()
 *         return gcd(b%a,a)
 *     }
 *
 *     fun solution(w: Int, h: Int): Long {
 *         var wl = w.toLong()
 *         var hl = h.toLong()
 *         return wl*hl-wl-hl+gcd(w,h)
 *     }
 * }
 * 테스트 1 〉	통과 (0.02ms, 59.8MB)
 * 테스트 2 〉	통과 (0.02ms, 59.4MB)
 * 테스트 3 〉	통과 (0.02ms, 60MB)
 * 테스트 4 〉	통과 (0.03ms, 59.7MB)
 * 테스트 5 〉	통과 (0.02ms, 60.3MB)
 * 테스트 6 〉	통과 (0.02ms, 59.6MB)
 * 테스트 7 〉	통과 (0.02ms, 59.6MB)
 * 테스트 8 〉	통과 (0.03ms, 58.7MB)
 * 테스트 9 〉	통과 (0.02ms, 60.3MB)
 * 테스트 10 〉	통과 (0.02ms, 59.8MB)
 * 테스트 11 〉	통과 (0.03ms, 59.6MB)
 * 테스트 12 〉	통과 (0.02ms, 59.4MB)
 * 테스트 13 〉	통과 (0.02ms, 60.9MB)
 * 테스트 14 〉	통과 (0.02ms, 60.2MB)
 * 테스트 15 〉	통과 (0.02ms, 60.8MB)
 *
 *
 * [RIVAL 2]
 * fun wrongRectanglesCount(w: Int, h: Int): Long {
 *     val g = gcd(w, h)
 *     return g * (w / g + h / g - 1).toLong()
 * }
 *
 * fun fineRectangle(w: Int, h: Int): Long = w.toLong() * h.toLong() - wrongRectanglesCount(w, h)
 *
 * fun maxCommon(numbers1: Array<Int>, numbers2: Array<Int>): Int {
 *     for (i in numbers1.reversedArray()) {
 *         if (i in numbers2) {
 *             return i
 *         }
 *     }
 *     return 1
 * }
 *
 * fun gcd(num1: Int, num2: Int): Int {
 *     return maxCommon(calculateFactors(num1), calculateFactors(num2))
 * }
 *
 * fun calculateFactors(num: Int): Array<Int> = (1..num).filter { num % it == 0 }.toTypedArray()
 * class Solution {
 *     fun solution(w: Int, h: Int): Long {
 *         return fineRectangle(w, h)
 *     }
 * }
 * 테스트 1 〉	통과 (11.68ms, 63.7MB)
 * 테스트 2 〉	통과 (11.37ms, 63.8MB)
 * 테스트 3 〉	통과 (10.59ms, 63.8MB)
 * 테스트 4 〉	통과 (10.51ms, 63.3MB)
 * 테스트 5 〉	통과 (11.74ms, 63.3MB)
 * 테스트 6 〉	통과 (13.14ms, 62.1MB)
 * 테스트 7 〉	통과 (11.65ms, 63.2MB)
 * 테스트 8 〉	통과 (10.66ms, 64.1MB)
 * 테스트 9 〉	통과 (10.18ms, 63.2MB)
 * 테스트 10 〉	통과 (9.98ms, 62.9MB)
 * 테스트 11 〉	통과 (300.14ms, 366MB)
 * 테스트 12 〉	통과 (494.94ms, 366MB)
 * 테스트 13 〉	통과 (386.30ms, 366MB)
 * 테스트 14 〉	통과 (309.16ms, 366MB)
 * 테스트 15 〉	통과 (159.93ms, 316MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(8, 12), 80)
  validate(s.solution(100_000_000, 100_000_000), 100_000_000L * 100_000_000L - 100_000_000L)
}
