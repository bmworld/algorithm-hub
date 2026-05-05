package 프로그래머스.입문.Day10.이차원으로만들기

class Solution {

  fun solution(orgn: IntArray, d2Size: Int): Array<IntArray> {
    val d1Size = orgn.size / d2Size
    return Array(d1Size) { IntArray(d2Size) }.also {
      repeat(d1Size) { i ->
        repeat(d2Size) { j ->
          val stt = i * d2Size
          it[i][j] = orgn[stt + j]
        }
      }
    }
  }
}

fun main() {
  val s = Solution()
  val orgn = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
  val d2Size = 2
  val d1Size = orgn.size / d2Size
  val ans = s.solution(orgn, d2Size)
  repeat(d1Size) { i ->
    val d2 = ans[i]
    check(d2.size == d2Size)
    repeat(d2Size) { j ->
      val stt = i * d2Size
      check(orgn[stt + j] == d2[j])
    }
  }

  val orgn2 = intArrayOf(100, 95, 2, 4, 5, 6, 18, 33, 948)
  val d2Size2 = 3
  val d1Size2 = orgn2.size / d2Size2
  val ans2 = s.solution(orgn2, d2Size2)
  repeat(d1Size2) { i ->
    val d2 = ans2[i]
    check(d2.size == d2Size2)
    repeat(d2Size2) { j ->
      val stt = i * d2Size2
      check(orgn2[stt + j] == d2[j])
    }
  }

}
