package 프로그래머스.스택_큐.다리를지나는트럭

import util.validate

class Solution {

  fun solution(L: Int, W: Int, a: IntArray): Int {
    val size = a.size
    val wByTime = IntArray(L * W + 2)

    var ans = L
    var w = 0
    var i = 0
    var l = 0
    var r = l
    var truck = a[i]


    while (true) {
      if (r - l < L) r++ else {
        r++
        w -= wByTime[++l]
      }

      if (w + truck <= W) {
        w += truck.also { wByTime[r] = it }
        if (++i < size) truck = a[i]
        else {
          ans = r + L
          break
        }
      }
    }

    return ans
  }
}

//         println("[-] l=${l}")
//println("[$l, $r] => w=$w, cur=$i")
//println("[+] r=$r, truck = $truck")

/**
 * ```
 * ME:
 * 테스트 1 〉	통과 (15.93ms, 103MB)
 * 테스트 2 〉	통과 (72.96ms, 248MB)
 * 테스트 3 〉	통과 (0.21ms, 62.7MB)
 * 테스트 4 〉	통과 (2.76ms, 67.1MB)
 * 테스트 5 〉	실패 (런타임 에러)
 * 테스트 6 〉	통과 (7.27ms, 73.9MB)
 * 테스트 7 〉	통과 (0.20ms, 60.8MB)
 * 테스트 8 〉	통과 (0.21ms, 64MB)
 * 테스트 9 〉	통과 (0.38ms, 62.2MB)
 * 테스트 10 〉	통과 (0.18ms, 61.4MB)
 * 테스트 11 〉	통과 (0.18ms, 61.4MB)
 * 테스트 12 〉	실패 (런타임 에러)
 * 테스트 13 〉	실패 (런타임 에러)
 * 테스트 14 〉	통과 (0.11ms, 63.2MB)
 * ```
 *
 *
 * ```
 * RIVAL:
 * ```
 */
fun main() {
  val s = Solution()

  validate(s.solution(2, 10, intArrayOf(7, 4, 5, 6)), 8)
  validate(s.solution(100, 100, intArrayOf(10)), 101)
  validate(s.solution(100, 100, intArrayOf(10, 10, 10, 10, 10, 10, 10, 10, 10, 10)), 110)
  validate(s.solution(4, 3, intArrayOf(2, 1, 1, 1)), 10)
  validate(s.solution(2, 3, intArrayOf(1, 2, 1, 1)), 6)
  validate(s.solution(3, 6, intArrayOf(1, 2, 3, 1, 1)), 8)
  validate(s.solution(3, 4, intArrayOf(2, 2, 2, 2, 2, 2)), 11)
  validate(s.solution(4, 5, intArrayOf(1, 4, 1, 1, 1)), 11)
  validate(s.solution(5, 5, intArrayOf(5, 1, 1, 1, 1, 1)), 15)

}
