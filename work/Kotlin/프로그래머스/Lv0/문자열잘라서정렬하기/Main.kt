package 프로그래머스.Lv0.문자열잘라서정렬하기

import util.validate

class Solution {

  fun solution(str: String): Array<String> {
    val N = str.length
    val strs = Array((N + 1) / 2) { "" }
    var len = 0
    val buf = CharArray(N)
    var ti = 0
    for (s in str) {
      when (s) {
        'x' -> if (ti > 0) {
          strs[len++] = String(buf, 0, ti)
          ti = 0
        }
        else -> buf[ti++] = s
      }
    }
    if (ti > 0) strs[len++] = String(buf, 0, ti)

    val ans = strs.copyOf(len) as Array<String>
    qs(ans, 0, len - 1)
    return ans
  }

  fun swap(a: Array<String>, i: Int, j: Int) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
  }

  fun qs(a: Array<String>, l: Int, r: Int) {
    if (l >= r) return
    val m = (l + r) shr 1
    val piv = a[m]
    swap(a, m, r)

    var pos = l
    for (i in l until r) if (a[i] < piv) swap(a, pos++, i)
    if (piv < a[pos]) swap(a, pos, r)

    qs(a, l, pos - 1)
    qs(a, pos + 1, r)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (12.65ms, 64.3MB)
 * 테스트 2 〉	통과 (8.52ms, 65.3MB)
 * 테스트 3 〉	통과 (2.63ms, 62.6MB)
 * 테스트 4 〉	통과 (3.32ms, 61.9MB)
 * 테스트 5 〉	통과 (5.32ms, 62MB)
 * 테스트 6 〉	통과 (7.26ms, 62.3MB)
 * 테스트 7 〉	통과 (5.44ms, 62MB)
 * 테스트 8 〉	통과 (10.45ms, 65MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(myString: String) = myString.split("x".toRegex()).filter(String::isNotEmpty).sorted()
 * }
 * 테스트 1 〉	통과 (31.24ms, 73.5MB)
 * 테스트 2 〉	통과 (30.70ms, 71.7MB)
 * 테스트 3 〉	통과 (22.30ms, 65.3MB)
 * 테스트 4 〉	통과 (18.41ms, 65.9MB)
 * 테스트 5 〉	통과 (24.41ms, 65.9MB)
 * 테스트 6 〉	통과 (24.86ms, 67.4MB)
 * 테스트 7 〉	통과 (21.82ms, 67.5MB)
 * 테스트 8 〉	통과 (28.44ms, 72.4MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(myString: String): Array<String> {
 *         var answer: Array<String>
 *         var tmp: String = ""
 *         val tmpList: MutableList<String> = mutableListOf()
 *         val trimmed = myString.trim('x')
 *         trimmed.forEachIndexed { index, ch ->
 *             if (ch != 'x') {
 *                 tmp += ch
 *             }
 *             else if (tmp.isNotEmpty()) {
 *                 tmpList.add(tmp)
 *                 println(tmp)
 *                 tmp = ""
 *             }
 *
 *             if (index == trimmed.lastIndex) tmpList.add(tmp)
 *         }
 *
 *         answer = tmpList.sorted().toTypedArray()
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (67.79ms, 83.4MB)
 * 테스트 2 〉	통과 (60.06ms, 83.4MB)
 * 테스트 3 〉	통과 (42.22ms, 67.2MB)
 * 테스트 4 〉	통과 (46.08ms, 66.6MB)
 * 테스트 5 〉	통과 (52.34ms, 68MB)
 * 테스트 6 〉	통과 (52.57ms, 74.9MB)
 * 테스트 7 〉	통과 (42.89ms, 67.2MB)
 * 테스트 8 〉	통과 (60.95ms, 84.6MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("xxzzzaxaaazaxxxxbxcxdxx"), arrayOf("aaaza", "b", "c", "d", "zzza"))
  validate(s.solution("axbxcxdx"), arrayOf("a", "b", "c", "d"))
  validate(s.solution("dxccxbbbxaaaa"), arrayOf("aaaa", "bbb", "cc", "d"))
  validate(s.solution("xabcxacaxazxaxaa"), arrayOf("a", "aa", "abc", "aca", "az"))
  validate(s.solution("zzz"), arrayOf("zzz"))
}
