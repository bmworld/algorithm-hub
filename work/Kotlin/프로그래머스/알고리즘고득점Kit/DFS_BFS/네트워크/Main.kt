package 프로그래머스.알고리즘고득점Kit.DFS_BFS.네트워크

import util.validate

class Solution {

  fun solution(n: Int, computers: Array<IntArray>): Int {
    var ans = 0
    val g = Array(n) { mutableListOf<Int>() }
    for (i in 0 until n) {
      val linked = computers[i]
      for (j in 0 until n) if (i != j && linked[j] == 1) g[i] += j
    }

    val ch = BooleanArray(n)
    val q = IntArray(n)
    for (i in 0 until n) {
      if (ch[i]) continue
      ans++
      ch[i] = true
      var qh = 0
      var qt = 0
      q[qt++] = i
      while (qh < qt) {
        val x = q[qh++]
        val linked = g[x]
        for (j in linked) {
          if (ch[j]) continue
          ch[j] = true
          q[qt++] = j
        }
      }
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.04ms, 59.8MB)
 * 테스트 2 〉	통과 (0.05ms, 58.7MB)
 * 테스트 3 〉	통과 (0.06ms, 59.5MB)
 * 테스트 4 〉	통과 (0.06ms, 59.4MB)
 * 테스트 5 〉	통과 (0.03ms, 58.9MB)
 * 테스트 6 〉	통과 (0.32ms, 59.3MB)
 * 테스트 7 〉	통과 (0.09ms, 59.1MB)
 * 테스트 8 〉	통과 (0.11ms, 59.8MB)
 * 테스트 9 〉	통과 (0.15ms, 58.9MB)
 * 테스트 10 〉	통과 (0.09ms, 58.5MB)
 * 테스트 11 〉	통과 (0.40ms, 59.1MB)
 * 테스트 12 〉	통과 (0.35ms, 59.6MB)
 * 테스트 13 〉	통과 (0.15ms, 59.6MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(n: Int, computers: Array<IntArray>): Int {
 *         var answer = 0
 *         val q=java.util.ArrayDeque<Int>()
 *         val visit=BooleanArray(n)
 *
 *         for(i in 0 until n){
 *             if(!visit[i]){
 *                 visit[i]=true
 *                 answer++
 *                 q.add(i)
 *                 while(!q.isEmpty()){
 *                     val p=q.poll()
 *                     for(j in 0 until n){
 *                         if(p==j)    continue
 *                         if(!visit[j]&&computers[p][j]==1){
 *                             q.add(j)
 *                             visit[j]=true
 *                         }
 *                     }
 *                 }
 *             }
 *         }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (0.05ms, 59.8MB)
 * 테스트 2 〉	통과 (0.02ms, 59.3MB)
 * 테스트 3 〉	통과 (0.04ms, 59.6MB)
 * 테스트 4 〉	통과 (0.04ms, 58.3MB)
 * 테스트 5 〉	통과 (0.02ms, 59.1MB)
 * 테스트 6 〉	통과 (0.11ms, 59.7MB)
 * 테스트 7 〉	통과 (0.04ms, 59.7MB)
 * 테스트 8 〉	통과 (0.09ms, 59.4MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(3, arrayOf(
    intArrayOf(1, 1, 0),
    intArrayOf(1, 1, 0),
    intArrayOf(0, 0, 1)
  )), 2)

  validate(s.solution(3, arrayOf(
    intArrayOf(1, 1, 0),
    intArrayOf(1, 1, 1),
    intArrayOf(0, 1, 1)
  )), 1)

}

//println("p = $p, cnt=$cnt, rmn=$rmn / price = $price")
