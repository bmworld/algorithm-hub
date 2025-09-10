import java.io.StreamTokenizer
import kotlin.math.*

fun main() =
    with(StreamTokenizer(System.`in`.bufferedReader())) {
      // 불필요한 문법 비활성화 + 숫자/공백만 활성화
      resetSyntax()
      whitespaceChars(0, 32) // 공백(스페이스 이하) 전부 공백 처리
      parseNumbers() // 숫자 토큰을 nval(double)로 바로 파싱
      slashSlashComments(false)
      slashStarComments(false)
      eolIsSignificant(false)

      fun nextInt(): Int {
        nextToken()
        return nval.toInt()
      }

      print(solution(IntArray(nextInt()) { nextInt() }))
    }

fun solution(a: IntArray): String {
  val arr = a.sorted()

  // 2
  val median = arr[round((arr.size / 2).toDouble()).toInt()]

  // 3
  var mode = Int.MIN_VALUE

  var sum = 0
  val map = mutableMapOf<Int, Int>()
  var maxFq = 0

  for (v in arr) {
    sum += v
    val fq = map.getOrDefault(v, 0) + 1
    map[v] = fq
    maxFq = max(maxFq, fq)
  }

  val modeArr = IntArray(2) { Int.MIN_VALUE } // 최빈값 (중복 시, 두 번째로 작은 값)
  for (entry in map) {
    if (maxFq == entry.value) {
      mode =
          if (modeArr[0] == Int.MIN_VALUE) {
            modeArr[0] = entry.key
            entry.key
          } else if (modeArr[1] == Int.MIN_VALUE) {
            modeArr[1] = entry.key
            entry.key
          } else continue
    }
  }

  // 4
  val min = arr[0]
  val max = arr[arr.size - 1]
  val range = max - min

  // 1
  val mean = (sum.toDouble() / arr.size.toDouble()).roundToInt()

  // ---------------------------------------------------------------------
  val sb = StringBuilder()
  // 산술평균
  sb.append(mean).append("\n")
  // 중앙값
  sb.append(median).append("\n")
  // 최빈값
  sb.append(mode).append("\n")
  // 범위
  sb.append(range)

  return sb.toString()
}
