package 프로그래머스.Lv2.테이블해시함수

import util.validate

class Solution {
  companion object {

    var key1 = 0
    const val key2 = 0
  }

  fun solution(data: Array<IntArray>, col: Int, row_stt: Int, row_end: Int): Int {
    key1 = col - 1
    qs(data, 0, data.size - 1)

    var ans = 0
    for (odr in row_stt..row_end) {
      var hash = 0
      for (col in data[odr - 1]) hash += col % odr
      ans = ans xor hash
    }

    return ans
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

    val m = (l + r) shr 1
    val piv = a[m]
    swap(a, m, r)

    var pos = l
    for (i in l until r) if (comp(a[i], piv)) swap(a, pos++, i)
    if (comp(piv, a[pos])) swap(a, pos, r)

    qs(a, l, pos - 1)
    qs(a, pos + 1, r)
  }

  fun comp(a: IntArray, b: IntArray): Boolean {
    val av = a[key1]
    val bv = b[key1]
    return when {
      av == bv -> a[key2] > b[key2]
      else -> av < bv
    }
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 60.3MB)
 * 테스트 2 〉	통과 (0.07ms, 60.7MB)
 * 테스트 3 〉	통과 (0.18ms, 61.7MB)
 * 테스트 4 〉	통과 (0.05ms, 60.7MB)
 * 테스트 5 〉	통과 (0.89ms, 64.5MB)
 * 테스트 6 〉	통과 (2.83ms, 141MB)
 * 테스트 7 〉	통과 (3.33ms, 142MB)
 * 테스트 8 〉	통과 (3.35ms, 141MB)
 * 테스트 9 〉	통과 (4.50ms, 141MB)
 * 테스트 10 〉	통과 (3.88ms, 142MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(data: Array<IntArray>, col: Int, row_begin: Int, row_end: Int): Int {
 *         data.sortWith(compareBy({ it[col-1] }, { -it.first() }))
 *         return (row_begin-1..row_end-1)
 *             .map { m -> data[m].indices.fold(0) { acc, i -> acc + data[m][i]%(m+1) }}
 *             .fold(0) { acc, i -> acc xor i }
 *     }
 * }
 * 테스트 1 〉	통과 (17.59ms, 63.5MB)
 * 테스트 2 〉	통과 (15.76ms, 65.3MB)
 * 테스트 3 〉	통과 (15.73ms, 65.1MB)
 * 테스트 4 〉	통과 (17.40ms, 64MB)
 * 테스트 5 〉	통과 (17.43ms, 68MB)
 * 테스트 6 〉	통과 (23.05ms, 144MB)
 * 테스트 7 〉	통과 (27.43ms, 145MB)
 * 테스트 8 〉	통과 (26.12ms, 144MB)
 * 테스트 9 〉	통과 (31.56ms, 144MB)
 * 테스트 10 〉	통과 (28.45ms, 144MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(data: Array<IntArray>, col: Int, row_begin: Int, row_end: Int): Int {
 *         var answer: Int = 0
 *
 *         val sortedData = data.sortedWith(
 *             compareBy(
 *                 {it[col-1]},{-it[0]}
 *             )
 *         )
 *
 *         for(i in row_begin - 1 .. row_end - 1){
 *            answer = answer xor getHash(sortedData[i], i)
 *         }
 *
 *         return answer
 *     }
 *
 *     fun getHash(arr: IntArray, row: Int): Int {
 *         var s = 0
 *         for (n in arr){
 *             s += n % (row + 1)
 *         }
 *
 *         return s
 *
 *     }
 * }
 * 테스트 1 〉	통과 (12.31ms, 64.3MB)
 * 테스트 2 〉	통과 (15.41ms, 62.7MB)
 * 테스트 3 〉	통과 (13.10ms, 64.7MB)
 * 테스트 4 〉	통과 (12.76ms, 64.3MB)
 * 테스트 5 〉	통과 (13.69ms, 67.5MB)
 * 테스트 6 〉	통과 (16.11ms, 144MB)
 * 테스트 7 〉	통과 (17.66ms, 143MB)
 * 테스트 8 〉	통과 (21.17ms, 144MB)
 * 테스트 9 〉	통과 (20.54ms, 145MB)
 * 테스트 10 〉	통과 (17.96ms, 144MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf(
      intArrayOf(3, 8, 3),
      intArrayOf(2, 2, 6),
      intArrayOf(1, 5, 10),
      intArrayOf(4, 2, 9),

      ),
    2, // col
    2, // row stt
    3 // row end
  ),
    4
  )
}

//println("[$odr] ${row.contentToString()} -> $hash")
