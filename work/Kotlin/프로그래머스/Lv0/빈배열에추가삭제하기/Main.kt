package 프로그래머스.Lv0.빈배열에추가삭제하기

import util.validate

class Solution {
  companion object {

    const val MAX_NUM = 9
    const val MTPLR = 2
  }

  fun solution(arr: IntArray, flag: BooleanArray): IntArray {
    val tmp = IntArray(MAX_NUM * MTPLR * arr.size)
    var len = 0
    var cnt = 0
    var x = 0
    for (i in arr.indices) {
      val n = arr[i]
      if (flag[i]) {
        repeat(cnt) { tmp[len++] = x }
        x = n.also { cnt = it * MTPLR }
      } else cnt = maxOf(0, cnt - n)
    }

    repeat(cnt) {
      tmp[len++] = x
    }

    return tmp.copyOf(len)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	실패 (0.02ms, 60.2MB)
 * 테스트 2 〉	실패 (0.02ms, 60.2MB)
 * 테스트 3 〉	통과 (0.02ms, 60.2MB)
 * 테스트 4 〉	실패 (0.02ms, 60.1MB)
 * 테스트 5 〉	실패 (0.02ms, 60.5MB)
 * 테스트 6 〉	실패 (0.02ms, 59.4MB)
 * 테스트 7 〉	실패 (0.02ms, 61MB)
 * 테스트 8 〉	실패 (0.02ms, 60.3MB)
 * 테스트 9 〉	실패 (0.02ms, 60MB)
 * 테스트 10 〉	실패 (0.03ms, 61.4MB)
 * 테스트 11 〉	실패 (0.03ms, 59.9MB)
 * 테스트 12 〉	실패 (0.03ms, 60.7MB)
 * 테스트 13 〉	실패 (0.03ms, 59MB)
 * 테스트 14 〉	실패 (0.03ms, 60.5MB)
 * 테스트 15 〉	실패 (0.03ms, 60.6MB)
 * 테스트 16 〉	실패 (0.03ms, 60.1MB)
 * 테스트 17 〉	실패 (0.04ms, 60.4MB)
 * 테스트 18 〉	통과 (0.05ms, 59.9MB)
 * 테스트 19 〉	통과 (0.03ms, 60.5MB)
 * 테스트 20 〉	통과 (0.01ms, 59.2MB)
 *
 * [RIVAL 1]
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    intArrayOf(3, 2, 4, 1, 3), booleanArrayOf(true, false, true, false, false)),
    intArrayOf(3, 3, 3, 3, 4, 4, 4, 4)
  )

  validate(s.solution(
    intArrayOf(1, 1, 1, 2, 2, 3), booleanArrayOf(true, false, false, true, false, true)),
    intArrayOf(2, 2, 3, 3, 3, 3, 3, 3)
  )
}
