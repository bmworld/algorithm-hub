package 프로그래머스.Lv2._2025_카카오_하반기_1차.바이러스파이프

import util.validate

class Solution {
  companion object {

    const val SEP = 10
    const val A = 0
    const val B = 1
    const val C = 2
    const val TYPE_SIZE = 3
    const val BIN_LEN = 32
    const val DEF_INFECTED = 1
    const val EMPTY = -1
    const val INFECTED = 0
  }

  fun solution(n: Int, infection: Int, edges: Array<IntArray>, k: Int): Int {
    val virus = infection - 1
    val g = Array(n) { mutableListOf<Int>() }
    for (e in edges) {
      val n1 = e[0] - 1
      val n2 = e[1] - 1
      val type = e[2] - 1
      g[n1] += n2 * SEP + type
      g[n2] += n1 * SEP + type
    }

    val map = IntArray(n) { EMPTY }
    val q = Array(n) { Pair(0, 0) }
    var qh = 0
    var qt = 0
    q[qt++] = Pair(0, virus)
    map[virus] = INFECTED

    while (qh < qt) {
      val p = q[qh++]
      val path = p.first
      val n1 = p.second

      val pos = BIN_LEN - path.countLeadingZeroBits()
      val dep = (pos + TYPE_SIZE - 1) / TYPE_SIZE
      val t1 = pos - 1 - (dep - 1) * TYPE_SIZE

      for (e in g[n1]) {
        val n2 = e / SEP
        if (map[n2] != EMPTY) continue
        val t2 = e % SEP
        val np = if (t1 == t2 && dep > 0) path else path or (1 shl (dep * TYPE_SIZE + t2))
        q[qt++] = Pair(np, n2)
        map[n2] = np
      }
    }

    var ans = DEF_INFECTED

    fun dfs(dep: Int, type: Int, path: Int, map: IntArray) {
      for (node in 0 until n) {
        val p = map[node]
        if (p and (1 shl type) != 0) map[node] = p shr TYPE_SIZE
      }

      if (dep == k) {
        var infected = 0
        for (x in map) {
          if (x == INFECTED) infected++
        }
        if (infected > ans) ans = infected
        return
      }

      when (type) {
        A -> {
          dfs(dep + 1, B, path or (1 shl (dep * TYPE_SIZE + B)), map.clone())
          dfs(dep + 1, C, path or (1 shl (dep * TYPE_SIZE + C)), map.clone())
        }
        B -> {
          dfs(dep + 1, A, path or (1 shl (dep * TYPE_SIZE + A)), map.clone())
          dfs(dep + 1, C, path or (1 shl (dep * TYPE_SIZE + C)), map.clone())
        }
        C -> {
          dfs(dep + 1, A, path or (1 shl (dep * TYPE_SIZE + A)), map.clone())
          dfs(dep + 1, B, path or (1 shl (dep * TYPE_SIZE + B)), map.clone())
        }
      }
    }

    for (type in A..C) dfs(1, type, type, map.clone())

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.45ms, 61.2MB)
 * 테스트 2 〉	통과 (0.41ms, 61.1MB)
 * 테스트 3 〉	통과 (0.59ms, 60.4MB)
 * 테스트 4 〉	통과 (0.62ms, 61MB)
 * 테스트 5 〉	통과 (0.80ms, 61MB)
 * 테스트 6 〉	통과 (0.81ms, 61MB)
 * 테스트 7 〉	통과 (0.71ms, 60.2MB)
 * 테스트 8 〉	통과 (0.87ms, 60.1MB)
 * 테스트 9 〉	통과 (0.50ms, 60.4MB)
 * 테스트 10 〉	통과 (0.67ms, 59.9MB)
 * 테스트 11 〉	통과 (0.63ms, 60.3MB)
 * 테스트 12 〉	통과 (0.75ms, 60.6MB)
 * 테스트 13 〉	통과 (0.76ms, 60.7MB)
 * 테스트 14 〉	통과 (0.53ms, 59MB)
 * 테스트 15 〉	통과 (0.57ms, 59.7MB)
 *
 * [RIVAL 1]
 * import kotlin.math.max
 *
 *
 * class Solution {
 *
 *     fun recur(depth: Int, pipeType: Int, infections: MutableList<Int>, graph: Array<Array<MutableList<Int>>>): Int {
 *         if (depth == 0) return infections.size
 *         val infectionSize = infections.size
 *         var maxCount = 0
 *         (1..3).filter { it != pipeType }.forEach { type ->
 *             val curGraph = graph[type]
 *             val visited = BooleanArray(curGraph.size) { false }
 *             val dq = ArrayDeque<Int>()
 *             infections.forEach { dq.addLast(it); visited[it] = true }
 *             while (dq.isNotEmpty()) {
 *                 val curNode = dq.removeFirst()
 *                 curGraph[curNode].filter { !visited[it] }.forEach { nextNode ->
 *                     dq.addLast(nextNode)
 *                     visited[nextNode] = true
 *                     infections.add(nextNode)
 *                 }
 *             }
 *             val diff = infections.size - infectionSize
 *
 *             maxCount = max(maxCount, recur(depth-1, type, infections, graph))
 *
 *             repeat(diff) { infections.removeLast() }
 *         }
 *         return maxCount
 *     }
 *
 *     fun solution(n: Int, infection: Int, edges: Array<IntArray>, k: Int): Int {
 *         // 열린 파이프 타입별 그래프를 만든다
 *         // graph[i]: 파이프 i가 열렸을 때의 그래프 (i = 0, 1, 2)
 *         val graph = Array(4) { Array(n+1) { mutableListOf<Int>() } }
 *         edges.forEach { (x, y, type) ->
 *             graph[type][x].add(y)
 *             graph[type][y].add(x)
 *         }
 *
 *         // 재귀 함수 호출로 정답을 계산한다
 *         val answer = recur(k, -1, mutableListOf(infection), graph)
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (15.61ms, 64.7MB)
 * 테스트 2 〉	통과 (16.06ms, 64.2MB)
 * 테스트 3 〉	통과 (15.37ms, 64.7MB)
 * 테스트 4 〉	통과 (15.50ms, 64.8MB)
 * 테스트 5 〉	통과 (16.48ms, 65.4MB)
 * 테스트 6 〉	통과 (16.45ms, 65.7MB)
 * 테스트 7 〉	통과 (21.03ms, 65.2MB)
 * 테스트 8 〉	통과 (18.81ms, 65.4MB)
 * 테스트 9 〉	통과 (15.56ms, 64.6MB)
 * 테스트 10 〉	통과 (18.31ms, 64.9MB)
 * 테스트 11 〉	통과 (17.39ms, 64.4MB)
 * 테스트 12 〉	통과 (17.14ms, 63.6MB)
 * 테스트 13 〉	통과 (17.88ms, 65.2MB)
 * 테스트 14 〉	통과 (15.90ms, 64.1MB)
 * 테스트 15 〉	통과 (15.54ms, 64.1MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     private lateinit var graph: Array<MutableList<Pair<Int, Int>>>
 *     private var k = 0
 *
 *     fun solution(n: Int, infection: Int, edges: Array<IntArray>, k: Int): Int {
 *         this.k = k
 *
 *         graph = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }
 *         for ((x, y, type) in edges) {
 *             graph[x].add(y to type)
 *             graph[y].add(x to type)
 *         }
 *
 *         val isInfected = BooleanArray(n + 1)
 *         isInfected[infection] = true
 *
 *         return dfs(0, listOf(infection), isInfected)
 *     }
 *
 *     fun dfs(l: Int, infections: List<Int>, isInfected: BooleanArray): Int {
 *         if (l == k) return infections.size
 *
 *         var max = infections.size
 *
 *         for (i in 1..3) {
 *             val tmp = mutableListOf<Int>()
 *             val queue = ArrayDeque<Int>()
 *             queue.addAll(infections)
 *             while (queue.isNotEmpty()) {
 *                 val now = queue.removeFirst()
 *
 *                 for ((y, type) in graph[now]) {
 *                     if (type != i) continue
 *                     if (isInfected[y]) continue
 *                     isInfected[y] = true
 *                     queue.add(y)
 *                     tmp.add(y)
 *                 }
 *             }
 *             max = maxOf(max, dfs(l + 1, infections + tmp, isInfected))
 *             for (t in tmp) {
 *                 isInfected[t] = false
 *             }
 *         }
 *         return max
 *     }
 * }
 * 테스트 1 〉	통과 (16.51ms, 64.9MB)
 * 테스트 2 〉	통과 (19.16ms, 65.2MB)
 * 테스트 3 〉	통과 (14.47ms, 66MB)
 * 테스트 4 〉	통과 (16.58ms, 65.5MB)
 * 테스트 5 〉	통과 (18.26ms, 66MB)
 * 테스트 6 〉	통과 (16.95ms, 65.7MB)
 * 테스트 7 〉	통과 (17.05ms, 66MB)
 * 테스트 8 〉	통과 (16.71ms, 65.4MB)
 * 테스트 9 〉	통과 (16.57ms, 66MB)
 * 테스트 10 〉	통과 (18.25ms, 66.2MB)
 * 테스트 11 〉	통과 (18.73ms, 65.6MB)
 * 테스트 12 〉	통과 (18.50ms, 65.1MB)
 * 테스트 13 〉	통과 (18.72ms, 65.2MB)
 * 테스트 14 〉	통과 (16.62ms, 65MB)
 * 테스트 15 〉	통과 (16.66ms, 64.8MB)
 *
 * [RIVAL 3]
 * class Solution {
 *     fun solution(n: Int, infection: Int, edges: Array<IntArray>, k: Int): Int {
 *         // 파이프 열어서 감염
 *         fun f(pipe: Int, visited: BooleanArray) {
 *             for(edge in edges) {
 *                 val (x, y, p) = edge
 *                 if (pipe == p) {
 *                     if (!visited[x] && visited[y]) {
 *                         visited[x] = true
 *                         f(pipe, visited)
 *                     }
 *                     else if (visited[x] && !visited[y]) {
 *                         visited[y] = true
 *                         f(pipe, visited)
 *                     }
 *                 }
 *             }
 *         }
 *
 *         var result = -1
 *
 *         fun dfs(pipe: Int, visited: BooleanArray, cnt: Int) {
 *             f(pipe, visited)
 *             if (cnt == k) {
 *                 result = maxOf(result, visited.count { it })
 *                 return
 *             }
 *             listOf(
 *                 listOf(2, 3),
 *                 listOf(1, 3),
 *                 listOf(1, 2)
 *             )[pipe - 1].forEach {
 *                 dfs(it, visited.copyOf(), cnt + 1)
 *             }
 *         }
 *
 *         val visited = BooleanArray(n + 1)
 *         visited[infection] = true
 *         listOf(1, 2, 3).forEach {
 *             dfs(it, visited.copyOf(), 1)
 *         }
 *
 *         return result
 *     }
 * }
 * 테스트 1 〉	통과 (12.39ms, 63.3MB)
 * 테스트 2 〉	통과 (9.97ms, 62.9MB)
 * 테스트 3 〉	통과 (9.21ms, 63.7MB)
 * 테스트 4 〉	통과 (9.11ms, 63.7MB)
 * 테스트 5 〉	통과 (10.22ms, 63.7MB)
 * 테스트 6 〉	통과 (10.16ms, 65.5MB)
 * 테스트 7 〉	통과 (10.64ms, 64.5MB)
 * 테스트 8 〉	통과 (10.19ms, 64.1MB)
 * 테스트 9 〉	통과 (9.63ms, 64.4MB)
 * 테스트 10 〉	통과 (10.28ms, 64.7MB)
 * 테스트 11 〉	통과 (9.84ms, 64MB)
 * 테스트 12 〉	통과 (10.11ms, 64.2MB)
 * 테스트 13 〉	통과 (11.22ms, 64.1MB)
 * 테스트 14 〉	통과 (9.67ms, 64.1MB)
 * 테스트 15 〉	통과 (10.14ms, 62.5MB)
 *
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    10,
    1,
    arrayOf(
      intArrayOf(1, 2, 1),
      intArrayOf(1, 3, 1),
      intArrayOf(1, 4, 3),
      intArrayOf(1, 5, 2),
      intArrayOf(5, 6, 1),
      intArrayOf(5, 7, 1),
      intArrayOf(2, 8, 3),
      intArrayOf(2, 9, 2),
      intArrayOf(9, 10, 1),
    ),
    2
  ), 6)

  validate(s.solution(
    7,
    6,
    arrayOf(
      intArrayOf(1, 2, 3),
      intArrayOf(1, 4, 3),
      intArrayOf(4, 5, 1),
      intArrayOf(5, 6, 1),
      intArrayOf(3, 6, 2),
      intArrayOf(3, 7, 2),
    ),
    3
  ), 7)

  // CASE 1: 필요한 경로 AB, 조작 순서 A → C → B
  validate(s.solution(
    3,
    1,
    arrayOf(
      intArrayOf(1, 2, 1),
      intArrayOf(2, 3, 2),
    ),
    3
  ), 3)

// CASE 2: 필요한 경로 AB, 조작 순서 앞에 C가 끼는 경우까지 고려
  validate(s.solution(
    4,
    1,
    arrayOf(
      intArrayOf(1, 2, 1),
      intArrayOf(2, 3, 2),
      intArrayOf(1, 4, 3),
    ),
    3
  ), 4)

// CASE 3: 동일 타입 연속 파이프 압축 + 분기 후 다시 다른 타입으로 진행
  validate(s.solution(
    6,
    1,
    arrayOf(
      intArrayOf(1, 2, 1),
      intArrayOf(2, 3, 1),
      intArrayOf(3, 4, 2),
      intArrayOf(2, 5, 3),
      intArrayOf(5, 6, 2),
    ),
    2
  ), 4)

// CASE 4: 시작점 기준 양방향으로 서로 다른 타입 순서가 필요한 경우
  validate(s.solution(
    7,
    4,
    arrayOf(
      intArrayOf(4, 3, 1),
      intArrayOf(3, 2, 2),
      intArrayOf(2, 1, 3),
      intArrayOf(4, 5, 2),
      intArrayOf(5, 6, 1),
      intArrayOf(6, 7, 3),
    ),
    3
  ), 5)

  // CASE 5: 분기점에서 A → B, A → C 경로가 갈라지는 경우
  validate(s.solution(
    4,
    2,
    arrayOf(
      intArrayOf(1, 2, 1),
      intArrayOf(1, 3, 2),
      intArrayOf(1, 4, 3),
    ),
    3
  ), 4)

// CASE 6: 감염 시작점 변경 시에도 동일한 오류 발생
  validate(s.solution(
    4,
    3,
    arrayOf(
      intArrayOf(1, 2, 1),
      intArrayOf(1, 3, 2),
      intArrayOf(1, 4, 3),
    ),
    3
  ), 4)
}

////    for (x in pathMap) {
////      println("x.toString(2) = ${x.toString(2)}")
////    }
//        println("[$dep][$type] map[$node]= ${nodePath.toString(2)}")
