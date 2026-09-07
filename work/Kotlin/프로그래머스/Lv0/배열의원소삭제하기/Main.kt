package 프로그래머스.Lv0.배열의원소삭제하기

import util.validate

class Solution {
  companion object {

    const val MAX = 1000
  }

  fun solution(arr: IntArray, delete_list: IntArray): IntArray {
    val del = BooleanArray(MAX + 1)
    for (x in delete_list) del[x] = true

    var tmp = IntArray(arr.size)
    var len = 0
    for (x in arr) if (!del[x]) tmp[len++] = x

    return tmp.copyOf(len)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59.4MB)
 * 테스트 2 〉	통과 (0.01ms, 58.6MB)
 * 테스트 3 〉	통과 (0.01ms, 60.3MB)
 * 테스트 4 〉	통과 (0.01ms, 59.3MB)
 * 테스트 5 〉	통과 (0.02ms, 61MB)
 * 테스트 6 〉	통과 (0.01ms, 60.6MB)
 * 테스트 7 〉	통과 (0.04ms, 59.4MB)
 * 테스트 8 〉	통과 (0.01ms, 59.6MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(arr: IntArray, deleteList: IntArray) = arr.toList() - deleteList.toSet()
 * }
 * 테스트 1 〉	통과 (13.71ms, 64.2MB)
 * 테스트 2 〉	통과 (16.15ms, 63.8MB)
 * 테스트 3 〉	통과 (13.50ms, 65.1MB)
 * 테스트 4 〉	통과 (13.67ms, 64MB)
 * 테스트 5 〉	통과 (13.71ms, 65.1MB)
 * 테스트 6 〉	통과 (13.61ms, 65.2MB)
 * 테스트 7 〉	통과 (13.57ms, 63.9MB)
 * 테스트 8 〉	통과 (14.46ms, 64.5MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    intArrayOf(293, 1000, 395, 678, 94),
    intArrayOf(94, 777, 104, 1000, 1, 12)
  ), intArrayOf(293, 395, 678))

  validate(s.solution(
    intArrayOf(110, 66, 439, 785, 1),
    intArrayOf(333, 444, 43)
  ), intArrayOf(110, 66, 439, 785, 1))
}
