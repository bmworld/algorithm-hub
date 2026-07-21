package 프로그래머스.Lv2.튜플

import util.validate

class Solution {

  companion object {

    const val op = '{'.code
    const val sep = ','.code
    const val close = '}'.code
    const val ZERO = 48
    val NUM = ZERO..ZERO + 9

    const val SKIP_DIST = 3
    const val MAX_ELEMS = 500
  }

  fun solution(s: String): IntArray {
    var total = 0
    val map = HashMap<Int, IntArray>()
    val buf = IntArray(MAX_ELEMS)
    var maxX = 0

    var fr = 1
    val to = s.length - 2
    var x = 0
    var i = 0
    var elems = 0


    while (fr <= to) {
      val c = s[fr].code
      when (c) {
        close -> {
          map[elems] = IntArray(elems) { buf[it] }
          total++
          i = 0
          fr += SKIP_DIST
        }
        sep -> {
          buf[i++] = x.also { if (it > maxX) maxX = it }
          x = 0
          elems++
        }
        in NUM -> x = x * 10 + (ZERO - c)
      }
    }

    var ans = IntArray(total)
    val used = BooleanArray(maxX + 1)
    repeat(total) {
      for (x in map[it + 1]!!) {
        if (used[x]) continue
        used[x] = true
        ans[it] = x
        break
      }
    }
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	실패 (시간 초과)
 * 테스트 2 〉	실패 (시간 초과)
 * 테스트 3 〉	실패 (시간 초과)
 * 테스트 4 〉	실패 (시간 초과)
 * 테스트 5 〉	실패 (시간 초과)
 * 테스트 6 〉	실패 (시간 초과)
 * 테스트 7 〉	실패 (시간 초과)
 * 테스트 8 〉	실패 (시간 초과)
 * 테스트 9 〉	실패 (시간 초과)
 * 테스트 10 〉	실패 (시간 초과)
 * 테스트 11 〉	실패 (시간 초과)
 * 테스트 12 〉	실패 (시간 초과)
 * 테스트 13 〉	실패 (시간 초과)
 * 테스트 14 〉	실패 (시간 초과)
 * 테스트 15 〉	실패 (시간 초과)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("{{2},{2,1},{2,1,3},{2,1,3,4}}"), intArrayOf(2, 1, 3, 4))
  validate(s.solution("{{1,2,3},{2,1},{1,2,4,3},{2}}"), intArrayOf(2, 1, 3, 4))
  validate(s.solution("{{20,111},{111}}"), intArrayOf(111, 20))
  validate(s.solution("{{123}}"), intArrayOf(123))
  validate(s.solution("{{4,2,3},{3},{2,3,4,1},{2,3}}"), intArrayOf(3, 2, 4, 1))
}
