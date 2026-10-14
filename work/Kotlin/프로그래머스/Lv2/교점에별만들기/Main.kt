package 프로그래머스.Lv2.교점에별만들기

import util.validate

class Solution {
  companion object {

    const val PAIR = 2
  }

  fun solution(line: Array<IntArray>): Array<String> {
    val N = line.size
    val points = LongArray(N * (N - 1))

    var minX = Long.MAX_VALUE
    var maxX = Long.MIN_VALUE

    var maxY = Long.MIN_VALUE
    var minY = Long.MAX_VALUE

    var pairs = 0
    for (i in 0 until N - 1) {
      val l1 = line[i]
      val a1 = l1[0].toLong()
      val b1 = l1[1].toLong()
      val c1 = l1[2].toLong()

      for (j in i + 1 until N) {
        val l2 = line[j]
        val a2 = l2[0].toLong()
        val b2 = l2[1].toLong()
        val c2 = l2[2].toLong()

        val d = a1 * b2 - a2 * b1
        if (d == 0L) continue

        val xn = b1 * c2 - b2 * c1
        if (xn % d != 0L) continue
        val x = xn / d

        val yn = a1 * c2 - a2 * c1
        if (yn % d != 0L) continue
        val y = -(yn / d)

        points[pairs] = x.also {
          if (it < minX) minX = it
          if (it > maxX) maxX = it
        }
        points[pairs + 1] = y.also {
          if (it < minY) minY = it
          if (it > maxY) maxY = it
        }

        pairs += PAIR
      }
    }

    val R = (maxY - minY + 1).toInt()
    val C = (maxX - minX + 1).toInt()
    val canvas = Array(R) { CharArray(C) { '.' } }
    repeat(pairs / PAIR) {
      val i = it * PAIR
      val x = (points[i] - minX).toInt()
      val y = (-points[i + 1] + maxY).toInt()
      canvas[y][x] = '*'
    }

    return Array(R) { String(canvas[it]) }
  }
}

/**
 * ```
 * [ME]
 * v1:
 * 테스트 1 〉	통과 (0.27ms, 60MB)
 * 테스트 2 〉	통과 (3.79ms, 62.3MB)
 * 테스트 3 〉	실패 (런타임 에러)
 * 테스트 4 〉	통과 (6.32ms, 64.8MB)
 * 테스트 5 〉	실패 (런타임 에러)
 * 테스트 6 〉	통과 (1.27ms, 60.9MB)
 * 테스트 7 〉	통과 (3.25ms, 61.9MB)
 * 테스트 8 〉	통과 (0.23ms, 59.8MB)
 * 테스트 9 〉	통과 (8.16ms, 64.7MB)
 * 테스트 10 〉	통과 (8.32ms, 65.9MB)
 * 테스트 11 〉	통과 (8.67ms, 67.3MB)
 * 테스트 12 〉	통과 (10.93ms, 67.5MB)
 * 테스트 13 〉	통과 (10.05ms, 69MB)
 * 테스트 14 〉	통과 (9.63ms, 67.7MB)
 * 테스트 15 〉	통과 (9.27ms, 67.7MB)
 * 테스트 16 〉	통과 (8.68ms, 66.1MB)
 * 테스트 17 〉	통과 (11.78ms, 68.2MB)
 * 테스트 18 〉	통과 (10.56ms, 67.7MB)
 * 테스트 19 〉	통과 (8.31ms, 67.2MB)
 * 테스트 20 〉	통과 (10.37ms, 64.1MB)
 * 테스트 21 〉	통과 (10.10ms, 66.9MB)
 * 테스트 22 〉	통과 (0.16ms, 61.4MB)
 * 테스트 23 〉	통과 (0.17ms, 60.7MB)
 * 테스트 24 〉	통과 (0.18ms, 59.7MB)
 * 테스트 25 〉	통과 (0.18ms, 59.7MB)
 * 테스트 26 〉	통과 (0.25ms, 59.2MB)
 * 테스트 27 〉	실패 (런타임 에러)
 * 테스트 28 〉	실패 (런타임 에러)
 * 테스트 29 〉	통과 (0.17ms, 58.9MB)
 *
 * v2: AC
 * 테스트 1 〉	통과 (0.30ms, 57.3MB)
 * 테스트 2 〉	통과 (3.97ms, 60.8MB)
 * 테스트 3 〉	통과 (0.18ms, 59.7MB)
 * 테스트 4 〉	통과 (5.40ms, 65.4MB)
 * 테스트 5 〉	통과 (2.75ms, 62.4MB)
 * 테스트 6 〉	통과 (1.47ms, 59.7MB)
 * 테스트 7 〉	통과 (3.89ms, 61.1MB)
 * 테스트 8 〉	통과 (0.25ms, 58.5MB)
 * 테스트 9 〉	통과 (9.19ms, 64.9MB)
 * 테스트 10 〉	통과 (11.37ms, 63.8MB)
 * 테스트 11 〉	통과 (10.36ms, 65.9MB)
 * 테스트 12 〉	통과 (9.68ms, 67.8MB)
 * 테스트 13 〉	통과 (14.20ms, 69.5MB)
 * 테스트 14 〉	통과 (11.73ms, 65MB)
 * 테스트 15 〉	통과 (8.93ms, 68MB)
 *
 * v3: 수식 간결화
 * 테스트 1 〉	통과 (0.27ms, 60.4MB)
 * 테스트 2 〉	통과 (4.23ms, 62.6MB)
 * 테스트 3 〉	통과 (0.28ms, 57.9MB)
 * 테스트 4 〉	통과 (5.46ms, 65MB)
 * 테스트 5 〉	통과 (2.77ms, 61.9MB)
 * 테스트 6 〉	통과 (1.28ms, 61MB)
 * 테스트 7 〉	통과 (3.19ms, 61.4MB)
 * 테스트 8 〉	통과 (0.18ms, 59.3MB)
 * 테스트 9 〉	통과 (8.43ms, 65.4MB)
 * 테스트 10 〉	통과 (8.34ms, 66.9MB)
 * 테스트 11 〉	통과 (8.56ms, 66MB)
 * 테스트 12 〉	통과 (9.53ms, 68MB)
 * 테스트 13 〉	통과 (11.33ms, 68.2MB)
 * 테스트 14 〉	통과 (10.94ms, 67.5MB)
 * 테스트 15 〉	통과 (8.39ms, 67.6MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(line: Array<IntArray>): Array<String> {
 *         val crossList = mutableListOf<DoubleArray>()
 *
 *         line.forEachIndexed { index, ints ->
 *             val a = ints[0].toDouble()
 *             val b = ints[1].toDouble()
 *             val c = ints[2].toDouble()
 *
 *             if (index < line.size - 1) {
 *                 for (i in (index + 1) until line.size) {
 *                     val check = check(
 *                         a,
 *                         b,
 *                         c,
 *                         line[i][0].toDouble(),
 *                         line[i][1].toDouble(),
 *                         line[i][2].toDouble()
 *                     )
 *                     if (check != null) {
 *                         crossList.add(check)
 *                     }
 *                 }
 *             }
 *         }
 *
 *         val xList = crossList.map { it[0] }
 *         val yList = crossList.map { it[1] }
 *         val xMax = xList.max()!!
 *         val xMin = xList.min()!!
 *         val yMax = yList.max()!!
 *         val yMin = yList.min()!!
 *         val xDiff = xMax - xMin
 *         val yDiff = yMax - yMin
 *         var stringX = ""
 *
 *         var answer = mutableListOf<String>()
 *
 *         for (i in 0 until (xDiff + 1.0).toInt()) {
 *             stringX += "."
 *         }
 *
 *         for (j in 0 until (yDiff + 1).toInt())
 *             answer.add(stringX)
 *
 *         crossList.forEach {
 *             val charArray = answer[(it[1] - yMin).toInt()].toCharArray()
 *             charArray[(it[0] - xMin).toInt()] = '*'
 *             answer[(it[1] - yMin).toInt()] = String(charArray)
 *         }
 *
 *         return answer.toTypedArray().reversedArray()
 *     }
 *
 *     fun check(a: Double, b: Double, e: Double, c: Double, d: Double, f: Double) : DoubleArray? {
 *         return if ((a*d - b*c) == 0.0) {
 *             null
 *         } else {
 *             val x = (b*f - e*d) / (a*d - b*c)
 *             val y = (e*c - a*f) / (a*d - b*c)
 *
 *             if (x % 1 == 0.0 && y % 1 == 0.0) {
 *                 doubleArrayOf(x, y)
 *             } else {
 *                 null
 *             }
 *         }
 *     }
 * }
 * 테스트 1 〉	통과 (17.62ms, 64.7MB)
 * 테스트 2 〉	통과 (15.01ms, 64.9MB)
 * 테스트 3 〉	통과 (16.18ms, 63.8MB)
 * 테스트 4 〉	통과 (15.30ms, 65.8MB)
 * 테스트 5 〉	통과 (15.14ms, 65MB)
 * 테스트 6 〉	통과 (14.25ms, 64.9MB)
 * 테스트 7 〉	통과 (15.08ms, 65.4MB)
 * 테스트 8 〉	통과 (13.79ms, 64.2MB)
 * 테스트 9 〉	통과 (20.20ms, 65.5MB)
 * 테스트 10 〉	통과 (25.51ms, 64MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     lateinit var line: Array<IntArray>
 *
 *     fun findDotOrNull(i: Int, j: Int): List<Double>? {
 *         val xI = line[i][0]
 *         val yI = line[i][1]
 *         val cI = line[i][2]
 *         val xJ = line[j][0]
 *         val yJ = line[j][1]
 *         val cJ = line[j][2]
 *
 *         val bottom = xI * yJ.toDouble() - xJ * yI.toDouble()
 *         if(bottom == 0.0) return null
 *
 *         val topX = cJ * yI.toDouble() - cI * yJ.toDouble()
 *         val resultX = topX / bottom
 *         if(resultX != Math.floor(resultX)) return null
 *
 *         val topY = xI * cJ.toDouble() - xJ * cI.toDouble()
 *         val resultY = topY / bottom
 *         if(resultY != Math.floor(resultY)) return null
 *
 *         return listOf(resultX, resultY)
 *     }
 *
 *     fun solution(line: Array<IntArray>): Array<String> {
 *         val set = HashSet<List<Double>>()
 *         this.line = line
 *         for(i in 0 until line.size) {
 *             for(j in i + 1 until line.size) {
 *                 findDotOrNull(i, j)?.let { set.add(it) }
 *             }
 *         }
 *
 *         val minX = set.minBy { it[0] }!![0]
 *         val maxX = set.maxBy { it[0] }!![0]
 *         val minY = set.minBy { it[1] }!![1]
 *         val maxY = set.maxBy { it[1] }!![1]
 *
 *         val answer = Array((maxY - minY + 1).toInt()) {
 *             StringBuilder().apply {
 *                 for(i in 0 until (maxX - minX + 1).toInt()) append('.')
 *             }
 *         }
 *
 *         set.forEach {
 *             val i = (it[1] - minY).toInt()
 *             val j = (it[0] - minX).toInt()
 *             answer[i][j] = '*'
 *         }
 *
 *         return answer.map { it.toString() }.toTypedArray()
 *     }
 * }
 * 테스트 1 〉	통과 (12.40ms, 63.9MB)
 * 테스트 2 〉	통과 (15.29ms, 67.1MB)
 * 테스트 3 〉	통과 (9.21ms, 62.7MB)
 * 테스트 4 〉	통과 (16.89ms, 69MB)
 * 테스트 5 〉	통과 (15.35ms, 64.5MB)
 * 테스트 6 〉	통과 (11.31ms, 63.9MB)
 * 테스트 7 〉	통과 (14.67ms, 66.4MB)
 * 테스트 8 〉	통과 (9.83ms, 63.1MB)
 * 테스트 9 〉	통과 (24.57ms, 65.6MB)
 * 테스트 10 〉	통과 (19.62ms, 65.4MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf(
      intArrayOf(2, -1, 4),
      intArrayOf(-2, -1, 4),
      intArrayOf(0, -1, 1),
      intArrayOf(5, -8, -12),
      intArrayOf(5, 8, 12),
    )),
    arrayOf(
      "....*....",
      ".........",
      ".........",
      "*.......*",
      ".........",
      ".........",
      ".........",
      ".........",
      "*.......*"
    )
  )

  validate(s.solution(
    arrayOf(
      intArrayOf(0, 1, -1),
      intArrayOf(1, 0, -1),
      intArrayOf(1, 0, 1),
    )),
    arrayOf(
      "*.*"
    )
  )

  validate(s.solution(
    arrayOf(
      intArrayOf(1, -1, 0),
      intArrayOf(2, -1, 0),
    )),
    arrayOf(
      "*"
    )
  )

  validate(s.solution(
    arrayOf(
      intArrayOf(1, -1, 0),
      intArrayOf(2, -1, 0),
      intArrayOf(4, -1, 0),
    )),
    arrayOf(
      "*"
    )
  )
}

// 가장 바깥쪽(경계) 좌표(LONG) -200000<=x<=200000, -19_999_900_000 <=y<= 19999900000
// 100000x + y + 100000
// 99999x + y - 100000\
