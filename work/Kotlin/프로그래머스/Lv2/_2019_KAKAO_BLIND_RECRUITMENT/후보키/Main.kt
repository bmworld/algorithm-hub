package 프로그래머스.Lv2._2019_KAKAO_BLIND_RECRUITMENT.후보키

import util.validate
import java.util.HashMap

class Solution {

  fun solution(relation: Array<Array<String>>): Int {

    val R = relation.size
    val C = relation[0].size
    val rel = LongArray(R * C)
    fun pos(r: Int, c: Int): Int = r * C + c

    var standard = 1L
    val strMapper = HashMap<String, Long>()
    for (c in 0 until C) {
      var uniqN = standard
      for (r in 0 until R) {
        val str = relation[r][c]
        val n = strMapper[str]
        if (n == null) {
          strMapper[str] = uniqN.also { rel[pos(r, c)] = it }
          uniqN += standard
        } else rel[pos(r, c)] = n
      }

      strMapper.clear()
      standard *= 100
    }

    val uniqCombs = HashSet<Int>()
    val combined = BooleanArray(C)
    val combCh = HashSet<Long>()
    fun dfs(dep: Int, stt: Int, len: Int) {
      if (dep == len) {

        var cand = 0
        for (c in 0 until C) if (combined[c]) cand = cand or (1 shl c)

        for (comn in uniqCombs) if (cand and comn == comn) return


        combCh.clear()
        for (r in 0 until R) {
          var comb = 0L
          for (c in 0 until C) if (combined[c]) comb += rel[pos(r, c)]

          if (combCh.contains(comb)) return
          else combCh.add(comb)
        }

        uniqCombs.add(cand)
        return
      }

      for (i in stt until C - (len - 1) + dep) {
        combined[i] = true
        dfs(dep + 1, i + 1, len)
        combined[i] = false
      }
    }

    for (comb in 1 until C) dfs(0, 0, comb)

    return uniqCombs.size
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.15ms, 59.9MB)
 * 테스트 2 〉	통과 (0.18ms, 57.5MB)
 * 테스트 3 〉	통과 (0.23ms, 60.6MB)
 * 테스트 4 〉	통과 (0.15ms, 59.2MB)
 * 테스트 5 〉	실패 (0.14ms, 59.4MB)
 * 테스트 6 〉	실패 (0.05ms, 58.9MB)
 * 테스트 7 〉	통과 (0.08ms, 58.8MB)
 * 테스트 8 〉	실패 (0.11ms, 59.1MB)
 * 테스트 9 〉	통과 (0.26ms, 60.6MB)
 * 테스트 10 〉	통과 (0.22ms, 59.5MB)
 * 테스트 11 〉	통과 (0.22ms, 58.6MB)
 * 테스트 12 〉	통과 (0.86ms, 59.2MB)
 * 테스트 13 〉	통과 (0.49ms, 59.7MB)
 * 테스트 14 〉	통과 (0.13ms, 57.1MB)
 * 테스트 15 〉	통과 (0.11ms, 59.9MB)
 * 테스트 16 〉	통과 (0.17ms, 59.5MB)
 * 테스트 17 〉	통과 (0.23ms, 58.3MB)
 * 테스트 18 〉	통과 (2.33ms, 59MB)
 * 테스트 19 〉	통과 (0.96ms, 60.3MB)
 * 테스트 20 〉	통과 (1.10ms, 59.4MB)
 * 테스트 21 〉	실패 (0.99ms, 59.2MB)
 * 테스트 22 〉	통과 (0.83ms, 59.8MB)
 * 테스트 23 〉	통과 (0.15ms, 60.3MB)
 * 테스트 24 〉	통과 (0.63ms, 59.3MB)
 * 테스트 25 〉	통과 (1.05ms, 59.8MB)
 * 테스트 26 〉	통과 (0.89ms, 59.6MB)
 * 테스트 27 〉	통과 (0.24ms, 59.6MB)
 * 테스트 28 〉	통과 (0.36ms, 60.1MB)
 *
 * [RIVAL 1]
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf(
      arrayOf("100", "ryan", "music", "2"),
      arrayOf("200", "apeach", "math", "2"),
      arrayOf("300", "tube", "computer", "3"),
      arrayOf("400", "con", "computer", "4"),
      arrayOf("500", "muzi", "music", "3"),
      arrayOf("600", "apeach", "music", "2"),
    )
  ), 2)
}

//println("relation[$r][$c] = ${relation[r][c]} -> x=$x")
//repeat(R) { r ->
//  repeat(C) { c ->
//    println("rel[pos($r,$c)] = ${rel[pos(r, c)]}")
//  }
//}
//        println("[$len] ${cand.toString(2)}")
