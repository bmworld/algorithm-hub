package 프로그래머스.알고리즘고득점Kit.Sorting.K번째수

import util.validate

class Solution {

  fun solution(a: IntArray, cmds: Array<IntArray>): IntArray {
    val tmp = IntArray(a.size)
    var ans = IntArray(cmds.size)

    var i = 0
    for (cmd in cmds) {
      val fr = cmd[0] - 1
      val to = cmd[1] - 1
      val k = cmd[2] - 1
      val len = to - fr + 1
      System.arraycopy(a, fr, tmp, 0, len)

      qs(tmp, 0, len - 1)
      ans[i++] = tmp[k]
    }
    return ans
  }

  fun swap(
    a: IntArray,
    i: Int,
    j: Int,
  ) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
  }

  fun sort(
    a: IntArray,
    l: Int,
    r: Int,
  ): Int {
    val m = (l + r) shr 1
    val mv = a[m]
    swap(a, m, r)

    var pos = l
    for (i in l until r) if (a[i] < mv) swap(a, pos++, i)
    if (mv < a[pos]) swap(a, pos, r)
    return pos
  }

  fun qs(
    a: IntArray,
    l: Int,
    r: Int,
  ) {
    if (l >= r) return
    val m = sort(a, l, r)
    qs(a, l, m - 1)
    qs(a, m + 1, r)
  }
}

/**
 * ```
 * ME:
 * 테스트 1 〉	통과 (0.01ms, 65.5MB)
 * 테스트 2 〉	통과 (0.02ms, 65.1MB)
 * 테스트 3 〉	통과 (0.02ms, 62.7MB)
 * 테스트 4 〉	통과 (0.02ms, 62.9MB)
 * 테스트 5 〉	통과 (0.02ms, 63.7MB)
 * 테스트 6 〉	통과 (0.04ms, 63.7MB)
 * 테스트 7 〉	통과 (0.01ms, 64.6MB)
 * ```
 *
 *
 * ```
 * RIVAL:
 * class Solution {
 *         fun solution(array: IntArray, commands: Array<IntArray>): IntArray {
 *             return commands.map { command ->
 *                 array.slice(IntRange(command[0] - 1, command[1] - 1)).sorted()[command[2] - 1]
 *             }
 *                 .toIntArray()
 *         }
 *     }
 * 테스트 1 〉	통과 (15.05ms, 67.1MB)
 * 테스트 2 〉	통과 (15.14ms, 67.3MB)
 * 테스트 3 〉	통과 (15.10ms, 65.9MB)
 * 테스트 4 〉	통과 (15.53ms, 66.7MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(
      intArrayOf(1, 5, 2, 6, 3, 7, 4),
      arrayOf(
        intArrayOf(2, 5, 3),
        intArrayOf(4, 4, 1),
        intArrayOf(1, 7, 3),
      ),
    ),
    intArrayOf(5, 6, 3)
  )
}

//println("[$i] tmp[$k] = ${tmp[k]}")
