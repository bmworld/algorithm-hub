package 프로그래머스.Lv2._2024_KAKAO_WINTER_INTERNSHIP.도넛과막대그래프

import util.validate

class Solution {
  companion object {

    const val X = 0
    const val DNT = 1
    const val BAR = 2
    const val EHT = 3
    const val MAX_NUM = 1_000_000
  }

  fun solution(edges: Array<IntArray>): IntArray {
    val ans = IntArray(4)
    val end = IntArray(MAX_NUM + 1)
    val g = HashMap<Int, MutableList<Int>>()

    var max = 0
    for (edge in edges) {
      val a = edge[0]
      val b = edge[1]
      end[b]++

      if (g[a] == null) g[a] = mutableListOf(b)
      else g[a]!! += b

      if (a > max) max = a
      if (b > max) max = b
    }

    val used = BooleanArray(max + 1)
    for ((n, sib) in g) if (sib.size >= 2 && end[n] == 0) {
      ans[X] = n
      used[n] = true
      for (n2 in sib) if (--end[n2] == 0 && g[n2].isNullOrEmpty()) ans[BAR]++
      break
    }

    val q = IntArray(g.size + 1)
    for ((n, sib) in g) if (!used[n]) {
      val s = sib.size
      val e = end[n]
      when {
        s <= 1 && e == 0 -> {
          ans[BAR]++
          mark(q, n, used, g)
        }
        s == 2 && e >= 2 -> {
          ans[EHT]++
          mark(q, n, used, g)
        }
      }
    }

    for ((n, sib) in g) if (!used[n]) {
      ans[DNT]++
      mark(q, n, used, g)
    }

    return ans
  }

  fun mark(q: IntArray, node: Int, used: BooleanArray,
    g: HashMap<Int, MutableList<Int>>) {
    var qh = 0
    var qt = 0
    q[qt++] = node
    used[node] = true

    while (qh < qt) {
      val n = q[qh++]
      for (n2 in g[n] ?: continue) if (!used[n2]) {
        used[n2] = true
        q[qt++] = n2
      }
    }
  }
}

/**
 * ```
 * [ME]
 * v1:
 * 테스트 1 〉	실패 (0.12ms, 60.1MB)
 * 테스트 2 〉	실패 (0.20ms, 61.1MB)
 * 테스트 3 〉	통과 (0.29ms, 61.1MB)
 * 테스트 4 〉	통과 (0.24ms, 59.4MB)
 * 테스트 5 〉	통과 (1.11ms, 60.3MB)
 * 테스트 6 〉	통과 (0.59ms, 61.6MB)
 * 테스트 7 〉	통과 (0.63ms, 59.7MB)
 * 테스트 8 〉	실패 (77.26ms, 142MB)
 * 테스트 9 〉	실패 (35.99ms, 133MB)
 * 테스트 10 〉	통과 (189.48ms, 191MB)
 * 테스트 11 〉	통과 (112.18ms, 148MB)
 * 테스트 12 〉	통과 (76.34ms, 144MB)
 * 테스트 13 〉	실패 (80.26ms, 143MB)
 * 테스트 14 〉	통과 (242.84ms, 244MB)
 * 테스트 15 〉	통과 (162.50ms, 143MB)
 * 테스트 16 〉	실패 (60.00ms, 133MB)
 * 테스트 17 〉	통과 (124.64ms, 176MB)
 * 테스트 18 〉	통과 (93.77ms, 142MB)
 * 테스트 19 〉	통과 (75.23ms, 142MB)
 * 테스트 20 〉	실패 (74.61ms, 142MB)
 * 테스트 21 〉	통과 (166.96ms, 231MB)
 * 테스트 22 〉	실패 (123.42ms, 207MB)
 * 테스트 23 〉	실패 (125.63ms, 200MB)
 * 테스트 24 〉	실패 (150.65ms, 204MB)
 * 테스트 25 〉	실패 (161.91ms, 206MB)
 * 테스트 26 〉	실패 (156.20ms, 202MB)
 * 테스트 27 〉	통과 (0.06ms, 59.4MB)
 * 테스트 28 〉	통과 (0.05ms, 59.5MB)
 * 테스트 29 〉	통과 (0.06ms, 60.9MB)
 * 테스트 30 〉	통과 (0.07ms, 59.3MB)
 * 테스트 31 〉	통과 (0.05ms, 60.9MB)
 * 테스트 32 〉	통과 (0.05ms, 59.1MB)
 * 테스트 33 〉	통과 (0.05ms, 59.1MB)
 * 테스트 34 〉	통과 (0.05ms, 60.2MB)
 * 테스트 35 〉	실패 (0.08ms, 58.7MB)
 * v2:
 * 테스트 1 〉	통과 (0.14ms, 59.5MB)
 * 테스트 2 〉	통과 (0.46ms, 60.4MB)
 * 테스트 3 〉	통과 (0.37ms, 59.1MB)
 * 테스트 4 〉	통과 (0.11ms, 60.9MB)
 * 테스트 5 〉	통과 (0.72ms, 59.7MB)
 * 테스트 6 〉	통과 (0.55ms, 61.6MB)
 * 테스트 7 〉	통과 (0.77ms, 60.9MB)
 * 테스트 8 〉	통과 (101.80ms, 151MB)
 * 테스트 9 〉	통과 (58.33ms, 139MB)
 * 테스트 10 〉	통과 (144.29ms, 204MB)
 * 테스트 11 〉	통과 (110.82ms, 158MB)
 * 테스트 12 〉	통과 (68.92ms, 153MB)
 * 테스트 13 〉	통과 (70.01ms, 153MB)
 * 테스트 14 〉	통과 (167.67ms, 263MB)
 * 테스트 15 〉	통과 (93.02ms, 151MB)
 * 테스트 16 〉	통과 (66.39ms, 136MB)
 * 테스트 17 〉	통과 (159.68ms, 191MB)
 * 테스트 18 〉	통과 (76.95ms, 152MB)
 * 테스트 19 〉	통과 (72.65ms, 153MB)
 * 테스트 20 〉	통과 (61.28ms, 145MB)
 * 테스트 21 〉	통과 (226.58ms, 256MB)
 * 테스트 22 〉	통과 (158.37ms, 219MB)
 * 테스트 23 〉	통과 (161.37ms, 216MB)
 * 테스트 24 〉	통과 (139.20ms, 220MB)
 * 테스트 25 〉	통과 (160.27ms, 215MB)
 * 테스트 26 〉	통과 (154.84ms, 226MB)
 * 테스트 27 〉	통과 (0.07ms, 59.8MB)
 * 테스트 28 〉	통과 (0.05ms, 61.3MB)
 * 테스트 29 〉	통과 (0.05ms, 59.9MB)
 * 테스트 30 〉	통과 (0.06ms, 60.1MB)
 * 테스트 31 〉	통과 (0.08ms, 60.4MB)
 * 테스트 32 〉	통과 (0.05ms, 59.2MB)
 * 테스트 33 〉	통과 (0.06ms, 60.2MB)
 * 테스트 34 〉	통과 (0.05ms, 60.7MB)
 * 테스트 35 〉	실패 (0.06ms, 60.7MB)
 * v3:
 * 테스트 1 〉	통과 (4.91ms, 65.3MB)
 * 테스트 2 〉	통과 (5.23ms, 64.5MB)
 * 테스트 3 〉	통과 (5.77ms, 63.7MB)
 * 테스트 4 〉	통과 (5.20ms, 65.3MB)
 * 테스트 5 〉	통과 (6.15ms, 64.4MB)
 * 테스트 6 〉	통과 (7.24ms, 64.1MB)
 * 테스트 7 〉	통과 (6.69ms, 65.2MB)
 * 테스트 8 〉	통과 (160.51ms, 176MB)
 * 테스트 9 〉	통과 (84.01ms, 136MB)
 * 테스트 10 〉	통과 (225.38ms, 225MB)
 * 테스트 11 〉	통과 (148.58ms, 178MB)
 * 테스트 12 〉	통과 (145.30ms, 176MB)
 * 테스트 13 〉	통과 (127.87ms, 172MB)
 * 테스트 14 〉	통과 (243.80ms, 280MB)
 * 테스트 15 〉	통과 (132.43ms, 176MB)
 * 테스트 16 〉	통과 (107.49ms, 141MB)
 * 테스트 17 〉	통과 (214.81ms, 227MB)
 * 테스트 18 〉	통과 (134.51ms, 172MB)
 * 테스트 19 〉	통과 (133.78ms, 178MB)
 * 테스트 20 〉	통과 (151.00ms, 165MB)
 * 테스트 21 〉	통과 (280.50ms, 280MB)
 * 테스트 22 〉	통과 (248.16ms, 247MB)
 * 테스트 23 〉	통과 (294.63ms, 237MB)
 * 테스트 24 〉	통과 (292.77ms, 245MB)
 * 테스트 25 〉	통과 (220.74ms, 245MB)
 * 테스트 26 〉	통과 (222.92ms, 242MB)
 * 테스트 27 〉	통과 (4.49ms, 63.2MB)
 * 테스트 28 〉	통과 (4.53ms, 64.4MB)
 * 테스트 29 〉	통과 (4.83ms, 63.6MB)
 * 테스트 30 〉	통과 (4.71ms, 64.9MB)
 * 테스트 31 〉	통과 (4.82ms, 63.2MB)
 * 테스트 32 〉	통과 (4.62ms, 64.6MB)
 * 테스트 33 〉	통과 (4.75ms, 64.6MB)
 * 테스트 34 〉	통과 (4.72ms, 63.7MB)
 * 테스트 35 〉	통과 (4.70ms, 65.4MB)
 *
 *
 * [RIVAL 1]
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  // CASE 1
  validate(s.solution(
    arrayOf(
      intArrayOf(2, 3),
      intArrayOf(4, 3),
      intArrayOf(1, 1),
      intArrayOf(2, 1),
    )
  ), intArrayOf(2, 1, 1, 0))

// CASE 2
  validate(s.solution(
    arrayOf(
      intArrayOf(4, 11),
      intArrayOf(1, 12),
      intArrayOf(8, 3),
      intArrayOf(12, 7),
      intArrayOf(4, 2),
      intArrayOf(7, 11),
      intArrayOf(4, 8),
      intArrayOf(9, 6),
      intArrayOf(10, 11),
      intArrayOf(6, 10),
      intArrayOf(3, 5),
      intArrayOf(11, 1),
      intArrayOf(5, 3),
      intArrayOf(11, 9),
      intArrayOf(3, 8),
    )
  ), intArrayOf(4, 0, 1, 2))

  // CASE 1: 막대 시작점에 생성 정점이 연결됨
  validate(s.solution(arrayOf(
    intArrayOf(1, 2),
    intArrayOf(2, 3),
    intArrayOf(1, 4),
    intArrayOf(4, 4)
  )), intArrayOf(1, 1, 1, 0))
// 예상 실제 결과: [1, 2, 0, 0]

// CASE 2: 정점 번호에 빈 구간 존재
  validate(s.solution(arrayOf(
    intArrayOf(2, 1),
    intArrayOf(1, 1),
    intArrayOf(2, 4)
  )), intArrayOf(2, 1, 1, 0))
// 예상 실제 결과: [2, 1, 2, 0]

// CASE 3: 간선 수보다 정점 번호가 큼
  validate(s.solution(arrayOf(
    intArrayOf(10, 11),
    intArrayOf(11, 11),
    intArrayOf(10, 12)
  )), intArrayOf(10, 1, 1, 0))
// 실제 결과: ArrayIndexOutOfBoundsException

  // CASE 4: 막대 끝점을 세면서 used 처리하지 않음
  validate(s.solution(arrayOf(
    intArrayOf(1, 3),
    intArrayOf(3, 2),
    intArrayOf(4, 4),
    intArrayOf(1, 4)
  )), intArrayOf(1, 1, 1, 0))

}
