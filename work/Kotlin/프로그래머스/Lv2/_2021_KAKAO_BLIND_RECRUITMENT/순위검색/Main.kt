package 프로그래머스.Lv2._2021_KAKAO_BLIND_RECRUITMENT.순위검색

import util.validate

class Solution {
  companion object {

    const val SEP = 1_000

    const val ZERO = 48
    val NUM = ZERO..ZERO + 9

    const val SPACE = ' '
    const val EMPTY = '-'.code
    const val AND = 'a'.code

    const val LANG = 0
    const val PSTN = 1
    const val CRRE = 2
    const val FOOD = 3

    const val CCP = 0
    const val JAVA = 1
    const val PTHN = 2

    const val BE = 3
    const val FE = 4

    const val JNR = 5
    const val SNR = 6

    const val CHKN = 7
    const val PZZA = 8
  }

  fun solution(info: Array<String>, query: Array<String>): IntArray {
    val N = info.size
    val M = query.size
    val cnds = IntArray(N)
    val answer = IntArray(M)

    for (i in info.indices) {
      val str = info[i]
      var score = 0
      var flag = 0
      var kind = LANG

      var pos = 0
      while (pos < str.length) {
        val c = str[pos++]
        when (c.code) {
          in NUM -> {
            score = c.code - ZERO
            for (k in pos until str.length)
              score = score * 10 + str[k].code - ZERO
            break
          }
          else -> flag = flag or (1 shl getBinaryPos(c, kind++))
        }

        while (str[pos++] != SPACE) {
        }
      }
      cnds[i] = score * SEP + flag
    }

    qs(cnds, 0, N - 1)


    for (i in query.indices) {
      val str = query[i]

      var condition = 0
      var minScore = 0
      var pos = 0
      var kind = LANG
      while (pos < str.length) {
        val c = str[pos++]
        when (c.code) {
          in NUM -> {
            minScore = c.code - ZERO
            for (k in pos until str.length)
              minScore = minScore * 10 + str[k].code - ZERO
            break
          }
          EMPTY -> kind++
          AND -> {}
          else -> condition = condition or (1 shl getBinaryPos(c, kind++))
        }

        while (str[pos++] != SPACE) {
        }
      }

      var selected = 0
      for (j in N - 1 downTo 0) {
        val cnd = cnds[j]
        if (cnd / SEP < minScore) break
        if (cnd % SEP and condition == condition) selected++
      }

      answer[i] = selected
    }

    return answer
  }

  private fun getBinaryPos(c: Char, kind: Int): Int =
    when (kind) {
      LANG -> when (c) {
        'c' -> CCP
        'j' -> JAVA
        'p' -> PTHN
        else -> throw Exception()
      }
      PSTN -> when (c) {
        'b' -> BE
        'f' -> FE
        else -> throw Exception()
      }
      CRRE -> when (c) {
        'j' -> JNR
        's' -> SNR
        else -> throw Exception()
      }
      FOOD -> when (c) {
        'c' -> CHKN
        'p' -> PZZA
        else -> throw Exception()
      }
      else -> throw Exception()
    }


  fun swap(
    a: IntArray,
    i: Int,
    j: Int,
  ) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
  }

  fun qs(
    a: IntArray,
    l: Int,
    r: Int,
  ) {
    if (l >= r) return

    var pos = l
    var pl = l
    var pr = r
    val piv = a[(l + r) shr 1]

    while (pos <= pr) {
      val x = a[pos]
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
 * 정확성 테스트
 * 테스트 1 〉	통과 (0.06ms, 59.9MB)
 * 테스트 2 〉	통과 (0.06ms, 58.7MB)
 * 테스트 3 〉	통과 (0.21ms, 60.5MB)
 * 테스트 4 〉	통과 (1.23ms, 61MB)
 * 테스트 5 〉	통과 (2.51ms, 60.1MB)
 * 테스트 6 〉	통과 (3.98ms, 59.9MB)
 * 테스트 7 〉	통과 (3.11ms, 61.8MB)
 * 테스트 8 〉	통과 (7.11ms, 61.8MB)
 * 테스트 9 〉	통과 (8.34ms, 62.6MB)
 * 테스트 10 〉	통과 (8.00ms, 63.8MB)
 * 테스트 11 〉	통과 (2.30ms, 59.6MB)
 * 테스트 12 〉	통과 (3.88ms, 60.8MB)
 * 테스트 13 〉	통과 (3.80ms, 61.7MB)
 * 테스트 14 〉	통과 (7.70ms, 62.1MB)
 * 테스트 15 〉	통과 (7.68ms, 61.3MB)
 * 테스트 16 〉	통과 (2.50ms, 61.5MB)
 * 테스트 17 〉	통과 (4.51ms, 59.9MB)
 * 테스트 18 〉	통과 (7.25ms, 62.2MB)
 * 효용성 테스트
 * 테스트 1 〉	실패 (시간 초과)
 * 테스트 2 〉	실패 (시간 초과)
 * 테스트 3 〉	실패 (시간 초과)
 * 테스트 4 〉	실패 (시간 초과)
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
      "java backend junior pizza 150",
      "python frontend senior chicken 210",
      "python frontend senior chicken 150",
      "cpp backend senior pizza 260",
      "java backend junior chicken 80",
      "python backend senior chicken 50",
//      "python frontend senior pizza 100000" // <-- 테스트용
    ),
    arrayOf(
      "java and backend and junior and pizza 100",
      "python and frontend and senior and chicken 200",
      "cpp and - and senior and pizza 250",
      "- and backend and senior and - 150",
      "- and - and - and chicken 100",
      "- and - and - and - 150"
    )
  ),
    intArrayOf(1, 1, 1, 1, 2, 4))
}

////    for (x in user) println("$x = ${x / SEP} && ${(x % SEP).toString(2)}")
//        println("[$j] ${cnd / SEP}, ${(cnd % SEP).toString(2)} -> $selected")
