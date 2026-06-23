package 프로그래머스.Lv1.문자열내마음대로정렬하기

import util.validate

class Solution {

  fun solution(strings: Array<String>, n: Int): Array<String> {
    qs(strings, 0, strings.size - 1, n)
    return strings
  }

  fun swap(a: Array<String>, i: Int, j: Int) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
  }

  fun qs(a: Array<String>, l: Int, r: Int, n: Int) {
    if (l >= r) return
    val m = sort(a, l, r, n)
    qs(a, l, m - 1, n)
    qs(a, m + 1, r, n)
  }

  fun sort(a: Array<String>, l: Int, r: Int, n: Int): Int {
    val m = (l + r) shr 1
    val piv = a[m]
    swap(a, m, r)

    var pos = l
    for (i in l until r) if (comp(a[i], piv, n)) swap(a, pos++, i)
    if (comp(piv, a[pos], n)) swap(a, pos, r)

    return pos
  }

  fun comp(a: String, b: String, n: Int): Boolean {
    val ac = a[n]
    val bc = b[n]
    return when {
      ac == bc -> a < b
      else -> ac < bc
    }
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 58.7MB)
 * 테스트 2 〉	통과 (0.04ms, 59MB)
 * 테스트 3 〉	통과 (0.09ms, 58MB)
 * 테스트 4 〉	통과 (0.10ms, 59.9MB)
 * 테스트 5 〉	통과 (0.03ms, 58.8MB)
 * 테스트 6 〉	통과 (0.08ms, 57.9MB)
 * 테스트 7 〉	통과 (0.04ms, 59.3MB)
 * 테스트 8 〉	통과 (0.05ms, 59.7MB)
 * 테스트 9 〉	통과 (0.03ms, 58.3MB)
 * 테스트 10 〉	통과 (0.16ms, 58.3MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(strings: Array<String>, n: Int): Array<String> {
 *         return strings.also {
 *             it.sort()
 *             it.sortBy { it[n] }
 *         }
 *     }
 * }
 * 테스트 1 〉	통과 (8.65ms, 61.6MB)
 * 테스트 2 〉	통과 (7.37ms, 63.6MB)
 * 테스트 3 〉	통과 (7.44ms, 62.3MB)
 * 테스트 4 〉	통과 (7.34ms, 63.7MB)
 * 테스트 5 〉	통과 (7.15ms, 62.6MB)
 * 테스트 6 〉	통과 (7.30ms, 62.1MB)
 * 테스트 7 〉	통과 (7.14ms, 62.3MB)
 * 테스트 8 〉	통과 (7.72ms, 61.7MB)
 * 테스트 9 〉	통과 (7.39ms, 63.5MB)
 * 테스트 10 〉	통과 (8.20ms, 61.8MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(arrayOf("sun", "bed", "car"), 1), arrayOf("car", "bed", "sun"))
  validate(s.solution(arrayOf("abce", "abcd", "cdx"), 2), arrayOf("abcd", "abce", "cdx"))
  validate(s.solution(arrayOf("ab", "aa", "za", "zb", "aa", "ac", "ba"), 1),
    arrayOf("aa", "aa", "ba", "za", "ab", "zb", "ac"))
}
