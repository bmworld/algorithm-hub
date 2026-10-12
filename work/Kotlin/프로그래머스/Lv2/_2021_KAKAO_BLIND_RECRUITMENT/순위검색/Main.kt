package 프로그래머스.Lv2._2021_KAKAO_BLIND_RECRUITMENT.순위검색

import util.validate
import java.util.HashMap

class Solution {
  companion object {

    const val ZERO = 48
    const val NA = -1
    val NUM = ZERO..ZERO + 9

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
    val M = query.size
    val ans = IntArray(M)
    val infoByCond = HashMap<Int, MutableList<Int>>()

    // Preprocessing
    for (l in intArrayOf(NA, CCP, JAVA, PTHN)) {
      for (p in intArrayOf(NA, BE, FE)) {
        for (c in intArrayOf(NA, JNR, SNR)) {
          for (f in intArrayOf(NA, CHKN, PZZA)) {
            infoByCond[
              (if (l == NA) 0 else 1 shl l) or
                (if (p == NA) 0 else 1 shl p) or
                (if (c == NA) 0 else 1 shl c) or
                (if (f == NA) 0 else 1 shl f)
            ] = mutableListOf()
          }
        }
      }
    }

    // Parse
    for (i in info.indices) {
      val str = info[i]
      var score = 0
      var cond = 0
      var type = LANG

      var j = 0
      while (j < str.length) {
        val c = str[j]
        when (c.code) {
          in NUM -> {
            score = c.code - ZERO
            for (k in j + 1 until str.length)
              score = score * 10 + str[k].code - ZERO
            break
          }
          else -> getBinPosAndJump(type++, c, false).also {
            val (pos, jump) = it
            cond = cond or (1 shl pos)
            j += jump
          }
        }
      }

      for (e in infoByCond) {
        val flag = e.key
        if (flag and cond == flag) e.value.add(score)
      }
    }

    // Sort
    for (e in infoByCond) {
      val arr = e.value
      qs(arr, 0, arr.size - 1)
    }

    // Start
    for (i in query.indices) {
      val str = query[i]

      var cond = 0
      var minScore = 0
      var j = 0
      var type = LANG
      while (j < str.length) {
        val c = str[j]
        when (c.code) {
          in NUM -> {
            minScore = c.code - ZERO
            for (k in j + 1 until str.length)
              minScore = minScore * 10 + str[k].code - ZERO
            break
          }
          '-'.code -> j += if (type++ < FOOD) 6 else 2
          else -> getBinPosAndJump(type++, c, true).also {
            val (pos, jump) = it
            cond = cond or (1 shl pos)
            j += jump
          }
        }
      }

      ans[i] = binSearch(infoByCond[cond]!!, minScore)
    }

    return ans
  }

  private fun binSearch(arr: MutableList<Int>, score: Int): Int {
    val all = arr.size
    var l = 0
    var r = all - 1
    while (l <= r) {
      val m = (l + r) shr 1
      val mv = arr[m]
      when {
        mv < score -> l = m + 1
        mv > score -> r = m - 1
        else -> return all - m
      }
    }

    return all - l - if (l < all && arr[l] < score) 1 else 0
  }

  private fun getBinPosAndJump(type: Int, c: Char, isQuery: Boolean): Pair<Int, Int> {
    var jump = 0
    val pos = when (type) {
      LANG -> when (c) {
        'c' -> {
          jump += 4
          CCP
        }
        'j' -> {
          jump += 5
          JAVA
        }
        'p' -> {
          jump += 7
          PTHN
        }
        else -> throw Exception()
      }
      PSTN -> when (c) {
        'b' -> {
          jump += 8
          BE
        }
        'f' -> {
          jump += 9
          FE
        }
        else -> throw Exception()
      }
      CRRE -> when (c) {
        'j' -> {
          jump += 7
          JNR
        }
        's' -> {
          jump += 7
          SNR
        }
        else -> throw Exception()
      }
      FOOD -> when (c) {
        'c' -> {
          jump += 8
          CHKN
        }
        'p' -> {
          jump += 6
          PZZA
        }
        else -> throw Exception()
      }
      else -> throw Exception()
    }
    return Pair(
      pos,
      jump + if (isQuery && type < FOOD) 4 else 0
    )
  }


  fun swap(
    a: MutableList<Int>,
    i: Int,
    j: Int,
  ) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
  }

  fun qs(
    a: MutableList<Int>,
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
 * v2:
 * 정확성  테스트
 * 테스트 1 〉	통과 (0.85ms, 60.1MB)
 * 테스트 2 〉	통과 (0.96ms, 60.2MB)
 * 테스트 3 〉	통과 (1.28ms, 59.2MB)
 * 테스트 4 〉	통과 (1.85ms, 61.6MB)
 * 테스트 5 〉	실패 (3.38ms, 62.2MB)
 * 테스트 6 〉	실패 (7.58ms, 63.2MB)
 * 테스트 7 〉	실패 (3.30ms, 62.9MB)
 * 테스트 8 〉	실패 (26.26ms, 69.3MB)
 * 테스트 9 〉	실패 (37.56ms, 78.9MB)
 * 테스트 10 〉	실패 (37.54ms, 81.9MB)
 * 테스트 11 〉	통과 (3.13ms, 62.3MB)
 * 테스트 12 〉	통과 (7.50ms, 66.5MB)
 * 테스트 13 〉	통과 (3.60ms, 61.3MB)
 * 테스트 14 〉	통과 (30.57ms, 73MB)
 * 테스트 15 〉	통과 (21.90ms, 74.3MB)
 * 테스트 16 〉	실패 (3.19ms, 61MB)
 * 테스트 17 〉	통과 (7.85ms, 66.3MB)
 * 테스트 18 〉	실패 (21.37ms, 75.2MB)
 * 효율성  테스트
 * 테스트 1 〉	실패 (380.20ms, 252MB)
 * 테스트 2 〉	실패 (392.49ms, 254MB)
 * 테스트 3 〉	실패 (362.90ms, 242MB)
 * 테스트 4 〉	실패 (393.08ms, 252MB)
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
      "- and - and - and - 150",
      "- and - and - and - 160"
    )
  ),
    intArrayOf(1, 1, 1, 1, 2, 4, 2))

  validate(s.solution(
    arrayOf(
      "java backend junior pizza 100"
    ),
    arrayOf(
      "java and backend and junior and pizza 101"
    )
  ), intArrayOf(0))
}
