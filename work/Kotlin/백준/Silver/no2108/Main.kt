import java.io.StreamTokenizer
import kotlin.math.max
import kotlin.math.roundToInt

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

      val size = nextInt()
      val nums = IntArray(size) { nextInt() }

      print(solution(nums))
    }

fun solution(a: IntArray): String {
  val arr = a.sorted()

  // 2
  val median = arr[arr.size / 2]

  // 3
  var sum = 0
  val map = mutableMapOf<Int, Int>()
  var maxFq = 0
  for (v in arr) {
    sum += v
    val fq = map.getOrDefault(v, 0) + 1
    map[v] = fq
    maxFq = max(maxFq, fq)
  }

  val modeCandidates = map.filter { it.value == maxFq }.keys.sorted()
  val mode = if (modeCandidates.size >= 2) modeCandidates[1] else modeCandidates[0]

  // 4
  val range = arr.last() - arr.first()

  // 1
  val mean = (sum.toDouble() / arr.size).roundToInt()

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
