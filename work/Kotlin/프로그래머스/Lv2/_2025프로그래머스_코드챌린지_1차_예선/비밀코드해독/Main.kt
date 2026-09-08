package 프로그래머스.Lv2._2025프로그래머스_코드챌린지_1차_예선.비밀코드해독

import util.validate

class Solution {
  companion object {

    const val CODE_LEN = 5
  }

  fun solution(n: Int, req: Array<IntArray>, res: IntArray): Int {
    if (res.max() == CODE_LEN) return 1

    var ans = 0
    val N = res.size
    val masks = IntArray(N) { i ->
      val input = req[i]
      var mask = 0
      for (x in input) mask = mask or (1 shl x)
      mask
    }

    for (a in 1..n - CODE_LEN + 1)
      for (b in a + 1..n - CODE_LEN + 2)
        for (c in b + 1..n - CODE_LEN + 3)
          for (d in c + 1..n - CODE_LEN + 4)
            l@ for (e in d + 1..n - CODE_LEN + 5) {
              val code = (1 shl a) or (1 shl b) or (1 shl c) or (1 shl d) or (1 shl e)
              for (i in masks.indices)
                if ((masks[i] and code).countOneBits() != res[i]) continue@l
              ans++
            }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (11.16ms, 61.7MB)
 * 테스트 2 〉	통과 (11.28ms, 63MB)
 * 테스트 3 〉	통과 (10.31ms, 63.5MB)
 * 테스트 4 〉	통과 (8.93ms, 64.5MB)
 * 테스트 5 〉	통과 (9.18ms, 62.6MB)
 * 테스트 6 〉	통과 (8.88ms, 64.1MB)
 * 테스트 7 〉	통과 (9.53ms, 63.7MB)
 * 테스트 8 〉	통과 (9.23ms, 64.2MB)
 * 테스트 9 〉	통과 (9.49ms, 64.4MB)
 * 테스트 10 〉	통과 (12.57ms, 64MB)
 * 테스트 11 〉	통과 (9.49ms, 63.9MB)
 * 테스트 12 〉	통과 (11.98ms, 64.9MB)
 * 테스트 13 〉	통과 (11.04ms, 64.4MB)
 * 테스트 14 〉	통과 (10.88ms, 63.9MB)
 * 테스트 15 〉	통과 (11.26ms, 63.6MB)
 * 테스트 16 〉	통과 (10.96ms, 64.1MB)
 * 테스트 17 〉	통과 (11.36ms, 63.4MB)
 * 테스트 18 〉	통과 (11.87ms, 63.6MB)
 * 테스트 19 〉	통과 (10.95ms, 64.2MB)
 * 테스트 20 〉	통과 (11.78ms, 64.2MB)
 * v2:
 * 테스트 1 〉	통과 (12.07ms, 63.8MB)
 * 테스트 2 〉	통과 (9.49ms, 63.6MB)
 * 테스트 3 〉	통과 (8.86ms, 64.3MB)
 * 테스트 4 〉	통과 (9.13ms, 63.8MB)
 * 테스트 5 〉	통과 (8.97ms, 63.6MB)
 * 테스트 6 〉	통과 (9.10ms, 64.1MB)
 * 테스트 7 〉	통과 (9.29ms, 64.8MB)
 * 테스트 8 〉	통과 (9.29ms, 63.2MB)
 * 테스트 9 〉	통과 (9.64ms, 62.9MB)
 * 테스트 10 〉	통과 (9.58ms, 64.5MB)
 * 테스트 11 〉	통과 (9.46ms, 63.9MB)
 * 테스트 12 〉	통과 (11.02ms, 63.2MB)
 * 테스트 13 〉	통과 (16.34ms, 63.3MB)
 * 테스트 14 〉	통과 (12.99ms, 64.9MB)
 * 테스트 15 〉	통과 (16.04ms, 61.9MB)
 * 테스트 16 〉	통과 (19.16ms, 62.5MB)
 * 테스트 17 〉	통과 (11.33ms, 64.2MB)
 * 테스트 18 〉	통과 (11.80ms, 63.4MB)
 * 테스트 19 〉	통과 (10.93ms, 62.5MB)
 * 테스트 20 〉	통과 (11.04ms, 64MB)
 * v3:
 * 테스트 1 〉	통과 (8.95ms, 63.6MB)
 * 테스트 2 〉	통과 (9.89ms, 63MB)
 * 테스트 3 〉	통과 (8.93ms, 63.8MB)
 * 테스트 4 〉	통과 (8.76ms, 63.6MB)
 * 테스트 5 〉	통과 (8.95ms, 63.7MB)
 * 테스트 6 〉	통과 (12.97ms, 62.5MB)
 * 테스트 7 〉	통과 (11.20ms, 62.6MB)
 * 테스트 8 〉	통과 (9.49ms, 62.3MB)
 * 테스트 9 〉	통과 (10.32ms, 62.4MB)
 * 테스트 10 〉	통과 (10.82ms, 64.2MB)
 * 테스트 11 〉	통과 (9.74ms, 64.5MB)
 * 테스트 12 〉	통과 (18.05ms, 63.7MB)
 * 테스트 13 〉	통과 (17.00ms, 64.2MB)
 * 테스트 14 〉	통과 (13.09ms, 64.1MB)
 * 테스트 15 〉	통과 (17.84ms, 61.5MB)
 * 테스트 16 〉	통과 (14.35ms, 63.9MB)
 * 테스트 17 〉	통과 (12.91ms, 64.4MB)
 * 테스트 18 〉	통과 (13.51ms, 63.6MB)
 * 테스트 19 〉	통과 (12.52ms, 63.3MB)
 * 테스트 20 〉	통과 (12.53ms, 63.9MB)
 *
 * [RIVAL 1]
 * import kotlin.math.*
 *
 * class Solution {
 *     fun solution(n: Int, q: Array<IntArray>, ans: IntArray): Int {
 *         // 모든 5개 숫자 조합 생성
 *         val allCombinations = generateCombinations(n)
 *
 *         // 가능한 비밀 코드 조합 필터링
 *         return allCombinations.count { candidate ->
 *             q.indices.all { i -> candidate.intersect(q[i].toSet()).size == ans[i] }
 *         }
 *     }
 *
 *     // 1부터 n까지의 숫자로 이루어진 모든 5개 조합 생성
 *     private fun generateCombinations(n: Int): List<Set<Int>> {
 *         val result = mutableListOf<Set<Int>>()
 *         val numbers = (1..n).toList()
 *
 *         fun combine(start: Int, combination: MutableList<Int>) {
 *             if (combination.size == 5) {
 *                 result.add(combination.toSet())
 *                 return
 *             }
 *             for (i in start until n) {
 *                 combination.add(numbers[i])
 *                 combine(i + 1, combination)
 *                 combination.removeAt(combination.lastIndex)
 *             }
 *         }
 *
 *         combine(0, mutableListOf())
 *         return result
 *     }
 * }
 * 테스트 1 〉	통과 (21.80ms, 66.3MB)
 * 테스트 2 〉	통과 (38.78ms, 83.9MB)
 * 테스트 3 〉	통과 (16.99ms, 65.4MB)
 * 테스트 4 〉	통과 (23.90ms, 65.1MB)
 * 테스트 5 〉	통과 (19.68ms, 65.2MB)
 * 테스트 6 〉	통과 (18.47ms, 65.4MB)
 * 테스트 7 〉	통과 (26.37ms, 68.9MB)
 * 테스트 8 〉	통과 (27.74ms, 69.1MB)
 * 테스트 9 〉	통과 (45.84ms, 87.8MB)
 * 테스트 10 〉	통과 (43.97ms, 88.5MB)
 * 테스트 11 〉	통과 (40.73ms, 85.6MB)
 * 테스트 12 〉	통과 (136.71ms, 219MB)
 * 테스트 13 〉	통과 (126.24ms, 226MB)
 * 테스트 14 〉	통과 (138.10ms, 236MB)
 * 테스트 15 〉	통과 (131.54ms, 234MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     lateinit var q :Array<IntArray>
 *     lateinit var ans: IntArray
 *
 *     fun check(target: IntArray) :Boolean {
 *         for(i in 0 until ans.size) {
 *             var count = 0
 *             for(j in 0 until 5) {
 *                 if(q[i].contains(target[j])){
 *                     count++
 *                 }
 *             }
 *             if(count != ans[i] ){
 *                 return false
 *             }
 *         }
 *         return true
 *     }
 *
 *     fun solve(n: Int) :Int {
 *         var target = IntArray(5) { 0 }
 *         var answer = 0
 *         for(i in 1..(n)){
 *             for(j in (i+1)..(n)){
 *                 for(k in (j+1)..(n)){
 *                     for(l in (k+1)..(n)){
 *                         for(m in (l+1)..(n)){
 *                             target[0] = i
 *                             target[1] = j
 *                             target[2] = k
 *                             target[3] = l
 *                             target[4] = m
 *                             if(check(target)) {
 *                                 answer++
 *                             }
 *                         }
 *                     }
 *                 }
 *             }
 *         }
 *         return answer
 *     }
 *
 *
 *     fun solution(n: Int, q: Array<IntArray>, ans: IntArray): Int {
 *         this.q = q
 *         this.ans = ans
 *         return solve(n)
 *     }
 * }
 * 테스트 1 〉	통과 (11.20ms, 64.1MB)
 * 테스트 2 〉	통과 (11.36ms, 64.3MB)
 * 테스트 3 〉	통과 (9.56ms, 64.4MB)
 * 테스트 4 〉	통과 (9.43ms, 62.1MB)
 * 테스트 5 〉	통과 (9.71ms, 63.7MB)
 * 테스트 6 〉	통과 (9.93ms, 62.1MB)
 * 테스트 7 〉	통과 (13.48ms, 65MB)
 * 테스트 8 〉	통과 (10.14ms, 64.1MB)
 * 테스트 9 〉	통과 (11.77ms, 64MB)
 * 테스트 10 〉	통과 (12.44ms, 63MB)
 * 테스트 11 〉	통과 (12.16ms, 63.9MB)
 * 테스트 12 〉	통과 (16.41ms, 61.4MB)
 * 테스트 13 〉	통과 (20.00ms, 66.3MB)
 * 테스트 14 〉	통과 (22.06ms, 64.5MB)
 * 테스트 15 〉	통과 (19.22ms, 63.1MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(
      10,
      arrayOf(
        intArrayOf(1, 2, 3, 4, 5),
        intArrayOf(6, 7, 8, 9, 10),
        intArrayOf(3, 7, 8, 9, 10),
        intArrayOf(2, 5, 7, 9, 10),
        intArrayOf(3, 4, 5, 6, 7)
      ),
      intArrayOf(2, 3, 4, 3, 3)
    ), 3
  )

  validate(
    s.solution(
      15,
      arrayOf(
        intArrayOf(2, 3, 9, 12, 13),
        intArrayOf(1, 4, 6, 7, 9),
        intArrayOf(1, 2, 8, 10, 12),
        intArrayOf(6, 7, 11, 13, 15),
        intArrayOf(1, 4, 10, 11, 14)
      ),
      intArrayOf(2, 1, 3, 0, 1)
    ), 5
  )


}
