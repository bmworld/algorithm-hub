package 프로그래머스.Lv0.접미사배열

import util.validate

class Solution {

  fun solution(my_string: String): Array<String> {
    val N = my_string.length
    val ans = Array(N) { i -> String(CharArray(N - i) { j -> my_string[i + j] }) }
    qs(ans, 0, N - 1)
    return ans
  }

  fun swap(a: Array<String>, i: Int, j: Int) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
  }

  fun qs(a: Array<String>, l: Int, r: Int) {
    if (l >= r) return
    val m1 = (l + r) shr 1
    val piv = a[m1]
    swap(a, m1, r)
    var m = l
    for (i in l until r) if (a[i] < piv) swap(a, m++, i)
    if (piv < a[m]) swap(a, m, r)
    qs(a, l, m - 1)
    qs(a, m + 1, r)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 60.2MB)
 * 테스트 2 〉	통과 (0.03ms, 60.2MB)
 * 테스트 3 〉	통과 (0.02ms, 59.2MB)
 * 테스트 4 〉	통과 (0.12ms, 59.8MB)
 * 테스트 5 〉	통과 (0.06ms, 58.6MB)
 * 테스트 6 〉	통과 (0.11ms, 59.6MB)
 * 테스트 7 〉	통과 (0.11ms, 60MB)
 * 테스트 8 〉	통과 (0.13ms, 60.4MB)
 *
 * [RIVAL 1]
 *
 * class Solution {
 *     fun solution(myString: String) = myString.indices.map(myString::substring).sorted()
 * }
 * 테스트 1 〉	통과 (15.64ms, 64.2MB)
 * 테스트 2 〉	통과 (15.89ms, 65.4MB)
 * 테스트 3 〉	통과 (15.13ms, 65.9MB)
 * 테스트 4 〉	통과 (15.07ms, 65.8MB)
 * 테스트 5 〉	통과 (16.09ms, 66.1MB)
 * 테스트 6 〉	통과 (14.97ms, 65.8MB)
 * 테스트 7 〉	통과 (16.69ms, 65.8MB)
 * 테스트 8 〉	통과 (16.19ms, 65.8MB)
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution("banana"),
    arrayOf(
      "a", "ana", "anana", "banana", "na", "nana"
    )
  )
  validate(
    s.solution("programmers"),
    arrayOf(
      "ammers", "ers", "grammers", "mers", "mmers", "ogrammers", "programmers", "rammers",
      "rogrammers", "rs", "s"
    )
  )
}
