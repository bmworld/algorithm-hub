package 프로그래머스.입문.Day13.배열원소의길이

class Solution {

  fun solution(arr: Array<String>): IntArray {
    val len = arr.size
    val ans = IntArray(len)
    for (i in 0 until len) ans[i] = arr[i].length
    return ans
  }
}

fun main() {
  val s = Solution()
  val orgn = arrayOf<String>("We", "are", "the", "world!")
  val ans = s.solution(orgn)
  for (i in 0 until orgn.size) check(orgn[i].length == ans[i])
}

/**
 * AS IS (map + toIntArray())
 * 테스트 1 〉	통과 (3.86ms, 62.4MB)
 * 테스트 2 〉	통과 (3.72ms, 61.5MB)
 * 테스트 3 〉	통과 (4.23ms, 63.6MB)
 * 테스트 4 〉	통과 (3.71ms, 61.9MB)
 * 테스트 5 〉	통과 (3.65ms, 64.8MB)
 * 테스트 6 〉	통과 (3.69ms, 64MB)
 *
 * TO BE (수동계산)
 * 테스트 1 〉	통과 (0.01ms, 61.4MB)
 * 테스트 2 〉	통과 (0.01ms, 61.8MB)
 * 테스트 3 〉	통과 (0.01ms, 62MB)
 * 테스트 4 〉	통과 (0.01ms, 61.8MB)
 * 테스트 5 〉	통과 (0.01ms, 61.7MB)
 * 테스트 6 〉	통과 (0.01ms, 61.2MB)
 */
