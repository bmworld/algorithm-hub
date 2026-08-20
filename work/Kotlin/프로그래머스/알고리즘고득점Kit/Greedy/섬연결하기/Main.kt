package 프로그래머스.알고리즘고득점Kit.Greedy.섬연결하기

import util.validate

class Solution {

  companion object {

    const val COST_IDX = 2
  }

  fun solution(n: Int, infos: Array<IntArray>): Int {
    qs(infos, 0, infos.size - 1)

    val tree = IntArray(n) { it }
    var total = 0
    var edges = 0
    for (info in infos) {
      val a = info[0]
      val b = info[1]
      val cost = info[COST_IDX]

      val ra = findRoot(a, tree)
      val rb = findRoot(b, tree)

      if (ra == rb) continue

      when {
        ra < rb -> tree[rb] = ra
        ra > rb -> tree[ra] = rb
      }

      total += cost
      if (++edges == n - 1) return total
    }

    return total
  }

  fun findRoot(n: Int, tree: IntArray): Int {
    val r = tree[n]
    return if (n == r) r
    else findRoot(r, tree).also {
      if (r != it) tree[n] = it
    }
  }

  fun swap(
    a: Array<IntArray>,
    i: Int,
    j: Int,
  ) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
  }

  fun qs(
    a: Array<IntArray>,
    l: Int,
    r: Int,
  ) {
    if (l >= r) return

    var pos = l
    var pl = l
    var pr = r
    val piv = a[(l + r) shr 1][COST_IDX]

    while (pos <= pr) {
      val x = a[pos][COST_IDX]
      when {
        x < piv -> swap(a, pos++, pl++)
        x > piv -> swap(a, pos, pr--)
        else -> pos++
      }
    }

    qs(a, l, pl - 1)
    qs(a, pr + 1, r)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59.6MB)
 * 테스트 2 〉	통과 (0.02ms, 60.1MB)
 * 테스트 3 〉	통과 (0.04ms, 59.9MB)
 * 테스트 4 〉	통과 (0.04ms, 58.9MB)
 * 테스트 5 〉	통과 (0.02ms, 60.3MB)
 * 테스트 6 〉	통과 (0.07ms, 61.5MB)
 * 테스트 7 〉	통과 (0.08ms, 61.6MB)
 * 테스트 8 〉	통과 (0.02ms, 59.9MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(n: Int, costs: Array<IntArray>): Int {
 *         val sortedCosts = costs.sortedBy { it[2] }
 *         val visited = mutableSetOf(0)
 *
 *         var answer = 0
 *         while (visited.size < n) {
 *             for ((s, e, c) in sortedCosts) {
 *                 if (visited.contains(s) or visited.contains(e)) {
 *                     if (visited.contains(s) and visited.contains(e)) {
 *                         continue
 *                     }
 *                     visited.add(s)
 *                     visited.add(e)
 *                     answer += c
 *                     break
 *                 }
 *             }
 *         }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (13.77ms, 63MB)
 * 테스트 2 〉	통과 (11.93ms, 61.8MB)
 * 테스트 3 〉	통과 (13.20ms, 62.3MB)
 * 테스트 4 〉	통과 (12.26ms, 63.9MB)
 * 테스트 5 〉	통과 (15.84ms, 62.1MB)
 * 테스트 6 〉	통과 (14.26ms, 64.4MB)
 * 테스트 7 〉	통과 (12.63ms, 63.2MB)
 * 테스트 8 〉	통과 (12.01ms, 63.5MB)
 *
 * [RIVAL2]
 * class Solution {
 *     fun solution(n: Int, costs: Array<IntArray>): Int {
 *         var answer = 0
 *         var root = IntArray(n,{i->i})
 *
 *         fun find(x : Int): Int {
 *             if (root[x] == x) {
 *                 return x
 *             }
 *             else{
 *                 return find(root[x])
 *             }
 *         }
 *         fun union(x: Int,y: Int)
 *         {
 *             var a = find(x)
 *             var b = find(y)
 *             root[b] = a
 *         }
 *         fun getCost(s: IntArray):Int
 *         {
 *             return s[2]
 *         }
 *         costs.sortBy{getCost(it)}
 *         for(item in costs)
 *         {
 *             if(find(item[0]) == find(item[1]))
 *             {
 *                 continue
 *             }
 *             else{
 *                 union(item[0],item[1])
 *                 answer+=item[2]
 *             }
 *
 *         }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (9.41ms, 63.2MB)
 * 테스트 2 〉	통과 (10.29ms, 63MB)
 * 테스트 3 〉	통과 (10.33ms, 63MB)
 * 테스트 4 〉	통과 (9.74ms, 64.2MB)
 * 테스트 5 〉	통과 (7.63ms, 63.2MB)
 * 테스트 6 〉	통과 (7.29ms, 63.1MB)
 * 테스트 7 〉	통과 (7.95ms, 63.9MB)
 * 테스트 8 〉	통과 (7.45ms, 62.9MB)
 * [RIVAL3]
 * import java.util.Arrays;
 *
 * class Solution {
 *     fun solution(n: Int, costs: Array<IntArray>): Int {
 *         var answer = 0
 *         val set = IntArray(n)
 *         for(i in 0 until set.size) {
 *             set[i] = i
 *         }
 *         Arrays.sort(costs) { a, b -> a[2].compareTo(b[2]) }
 *         for (i in 0 until costs.size) {
 *             if(getParent(set,costs[i][0])!=getParent(set,costs[i][1])) {
 *                 answer+=costs[i][2]
 *                 unionParent(set, costs[i][0], costs[i][1])
 *             }
 *         }
 *         return answer
 *     }
 *
 *     fun getParent(set: IntArray, x: Int): Int {
 *
 *         return if (set[x] == x) x
 *         else {
 *             set[x] = getParent(set, set[x])
 *             set[x]
 *         }
 *     }
 *
 *     fun unionParent(set: IntArray, a: Int, b: Int) {
 *         val num = getParent(set, a)
 *         val num2 = getParent(set, b)
 *
 *         if(num < num2) set[num2] =num
 *         else set[num] =num2
 *     }
 * }
 * 테스트 1 〉	통과 (2.53ms, 60.2MB)
 * 테스트 2 〉	통과 (1.89ms, 61.4MB)
 * 테스트 3 〉	통과 (2.37ms, 61.8MB)
 * 테스트 4 〉	통과 (2.87ms, 59MB)
 * 테스트 5 〉	통과 (2.36ms, 59MB)
 * 테스트 6 〉	통과 (2.10ms, 59.9MB)
 * 테스트 7 〉	통과 (1.97ms, 59.8MB)
 * 테스트 8 〉	통과 (2.42ms, 60.3MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(4, arrayOf(
      intArrayOf(2, 3, 8),
      intArrayOf(0, 1, 1),
      intArrayOf(0, 2, 2),
      intArrayOf(1, 2, 5),
      intArrayOf(1, 3, 1),
    )), 4
  )
}

//      println(" $a($ra) vs $b($rb) ---- cost = $cost")
