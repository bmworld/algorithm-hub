package 프로그래머스.Lv1.택배상자꺼내기

import util.validate

class Solution {

  fun solution(n: Int, w: Int, num: Int): Int {
    var top = getFloor(n, w)
    val cur = getFloor(num, w)

    val gap = top - cur
    var topX = num + (gap / 2) * 2 * w + if (gap % 2 == 0) 0 else (cur * w - num) * 2 + 1

    if (topX > n) top--

    return top - cur + 1
  }

  fun getFloor(x: Int, w: Int): Int = (x + w - 1) / w
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 59.6MB)
 * 테스트 2 〉	통과 (0.01ms, 58.7MB)
 * 테스트 3 〉	통과 (0.01ms, 59.4MB)
 * 테스트 4 〉	통과 (0.01ms, 59.8MB)
 * 테스트 5 〉	통과 (0.01ms, 59.3MB)
 * 테스트 6 〉	통과 (0.01ms, 59.4MB)
 * 테스트 7 〉	통과 (0.01ms, 59.8MB)
 * 테스트 8 〉	통과 (0.01ms, 58.6MB)
 * 테스트 9 〉	통과 (0.01ms, 58.5MB)
 * 테스트 10 〉	통과 (0.02ms, 57.9MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(n: Int, w: Int, num: Int): Int {
 *         if (w == 1) return n - num + 1 // 한 줄로 쌓일 경우 단순 계산
 *         if (n <= w) return 1 // 한 층만 존재할 경우 해당 상자만 꺼내면 됨
 *
 *         val totalHeight = (n - 1) / w // 전체 층 수
 *         val targetHeight = (num - 1) / w // 찾으려는 상자의 층 수
 *         val topDirection = totalHeight % 2 == 0 // 최상단이 정방향이면 true
 *         val topPosition = (n - 1) % w + 1 // 최상단의 좌우 위치 (1-based)
 *
 *         val myDirection = targetHeight % 2 == 0 // 찾으려는 상자의 방향
 *         val myPosition = (num - 1) % w + 1 // 찾으려는 상자의 좌우 위치 (1-based)
 *
 *         val extraBox = when {
 *             myDirection == topDirection -> if (myPosition + (w - topPosition) <= w) 1 else 0
 *             myDirection != topDirection -> if (myPosition + topPosition > w) 1 else 0
 *             else -> 0
 *         }
 *
 *         return totalHeight - targetHeight + extraBox
 *     }
 * }
 * 테스트 1 〉	통과 (0.01ms, 60.3MB)
 * 테스트 2 〉	통과 (0.01ms, 60MB)
 * 테스트 3 〉	통과 (0.02ms, 57.4MB)
 * 테스트 4 〉	통과 (0.01ms, 59.4MB)
 * 테스트 5 〉	통과 (0.01ms, 58.5MB)
 * 테스트 6 〉	통과 (0.01ms, 58.4MB)
 * 테스트 7 〉	통과 (0.01ms, 59.6MB)
 * 테스트 8 〉	통과 (0.01ms, 57.7MB)
 * 테스트 9 〉	통과 (0.01ms, 59.1MB)
 * 테스트 10 〉	통과 (0.01ms, 59.1MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(22, 6, 8), 3)
  validate(s.solution(13, 3, 6), 4)
  validate(s.solution(13, 3, 5), 3)

}

//    println("top = $top, cur=$cur (gap=$gap), topX= $topX")
