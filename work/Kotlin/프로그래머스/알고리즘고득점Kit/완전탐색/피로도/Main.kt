package 프로그래머스.알고리즘고득점Kit.완전탐색.피로도

import util.validate

class Solution {

  fun solution(k: Int, a: Array<IntArray>): Int {
    var ans = 0

    val len = a.size
    val ch = BooleanArray(len)

    fun dfs(dep: Int, hp: Int) {
      if (dep > ans) ans = dep

      for (i in 0 until len) {
        if (ch[i]) continue
        val d = a[i]
        val threshold = d[0]
        if (threshold > hp) continue

        ch[i] = true
        dfs(dep + 1, hp - d[1])
        ch[i] = false
      }
    }

    dfs(0, k)
    return ans
  }
}

/**
 * ```
 * ME: v1
 * 테스트 1 〉	통과 (0.12ms, 63.8MB)
 * 테스트 2 〉	통과 (0.11ms, 65.3MB)
 * 테스트 3 〉	통과 (0.11ms, 63MB)
 * 테스트 4 〉	통과 (0.29ms, 62.2MB)
 * 테스트 5 〉	통과 (0.29ms, 64.2MB)
 * 테스트 6 〉	통과 (0.41ms, 64.1MB)
 * 테스트 7 〉	통과 (1.03ms, 63.7MB)
 * 테스트 8 〉	통과 (1.79ms, 63.5MB)
 * 테스트 9 〉	통과 (0.11ms, 64MB)
 * 테스트 10 〉	통과 (0.40ms, 63.7MB)
 * ```
 *
 *
 * ```
 * RIVAL:
 * class Solution {
 *     private var answer = 0
 *
 *     fun solution(k: Int, dungeons: Array<IntArray>): Int {
 *         val visited = BooleanArray(dungeons.size) { false }
 *         findAnswer(k, dungeons, visited, 0)
 *         return answer
 *     }
 *
 *     fun findAnswer(k: Int, dungeons: Array<IntArray>, visited: BooleanArray, count: Int) {
 *         for (i in dungeons.indices) {
 *             if (visited[i] == false && k >= dungeons[i][0]) {
 *                 visited[i] = true
 *                 findAnswer(k - dungeons[i][1], dungeons, visited, count + 1)
 *                 visited[i] = false
 *             }
 *         }
 *
 *         if (count > answer) {
 *             answer = count
 *         }
 *     }
 * }
 * 테스트 1 〉	통과 (0.02ms, 62.5MB)
 * 테스트 2 〉	통과 (0.03ms, 64.4MB)
 * 테스트 3 〉	통과 (0.03ms, 63.8MB)
 * 테스트 4 〉	통과 (0.11ms, 64.8MB)
 * 테스트 5 〉	통과 (0.37ms, 62.3MB)
 * 테스트 6 〉	통과 (0.59ms, 64.1MB)
 * 테스트 7 〉	통과 (1.23ms, 62.8MB)
 * 테스트 8 〉	통과 (2.18ms, 65.2MB)
 * 테스트 9 〉	통과 (0.02ms, 63.7MB)
 * 테스트 10 〉	통과 (0.28ms, 63.1MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(80, arrayOf(
    intArrayOf(80, 20),
    intArrayOf(50, 40),
    intArrayOf(30, 10),
  )), 3)

  // 이거... GREEDY 로는 풀수가 없는건지..?
}

//        println("[$dep] = $i ->  $hp vs ${d[0]}, ${d[1]}")
