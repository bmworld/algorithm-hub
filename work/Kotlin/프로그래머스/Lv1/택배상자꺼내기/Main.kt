package 프로그래머스.Lv1.택배상자꺼내기

import util.validate

class Solution {

  fun solution(n: Int, w: Int, num: Int): Int {
    var topFloor = getFloor(n, w)
    val curFloor = getFloor(num, w)

    val gap = topFloor - curFloor
    var topX = (gap / 2) * 2 * w + if (gap % 2 == 0) 0 else abs(curFloor * w - num) * 2 + 1

    if (topX > n) topFloor--

    return topFloor - curFloor + 1
  }

  fun abs(x: Int): Int = if (x < 0) -x else x
  fun getFloor(x: Int, w: Int): Int = (x + w - 1) / w
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 58.8MB)
 * 테스트 2 〉	통과 (0.01ms, 58.6MB)
 * 테스트 3 〉	통과 (0.02ms, 58.1MB)
 * 테스트 4 〉	통과 (0.01ms, 58.7MB)
 * 테스트 5 〉	통과 (0.01ms, 59.5MB)
 * 테스트 6 〉	통과 (0.01ms, 58.4MB)
 * 테스트 7 〉	통과 (0.01ms, 59.1MB)
 * 테스트 8 〉	통과 (0.02ms, 58.1MB)
 * 테스트 9 〉	통과 (0.01ms, 59.2MB)
 * 테스트 10 〉	통과 (0.03ms, 59.1MB)
 * 테스트 11 〉	실패 (0.01ms, 58.3MB)
 * 테스트 12 〉	통과 (0.01ms, 59.9MB)
 * 테스트 13 〉	통과 (0.01ms, 58MB)
 * 테스트 14 〉	실패 (0.01ms, 58.4MB)
 * 테스트 15 〉	통과 (0.01ms, 58.5MB)
 * 테스트 16 〉	통과 (0.01ms, 59.8MB)
 * 테스트 17 〉	통과 (0.01ms, 58.4MB)
 * 테스트 18 〉	통과 (0.01ms, 57.8MB)
 * 테스트 19 〉	통과 (0.01ms, 57.5MB)
 * 테스트 20 〉	통과 (0.01ms, 58.3MB)
 * 테스트 21 〉	실패 (0.01ms, 59.8MB)
 * 테스트 22 〉	통과 (0.02ms, 57.9MB)
 * 테스트 23 〉	통과 (0.01ms, 58.3MB)
 * 테스트 24 〉	실패 (0.01ms, 58MB)
 * 테스트 25 〉	통과 (0.01ms, 59.6MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(22, 6, 8), 3)
  validate(s.solution(13, 3, 6), 4)

}
