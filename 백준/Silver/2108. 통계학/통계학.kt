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

      val size = nextInt()
      val nums = IntArray(size) { nextInt() }

      print(solution(nums))
    }

fun solution(a: IntArray): String {
  val offset = 4000
  val cnt = IntArray(offset * 2 + 1)
  var sum = 0
  var minIdx = offset * 2 + 1
  var maxIdx = -1

  // 중복 개수 카운팅
  for (v in a) {
    val idx = v + offset
    cnt[idx]++
    sum += v
    if (idx < minIdx) minIdx = idx
    if (idx > maxIdx) maxIdx = idx
  }

  // 1) 산술평균
  // e.g) avg = 1.6 → 1.6 + 0.5 = 2.1 → floor(2.1) = 2
  // e.g) avg = 1.4 → 1.4 + 0.5 = 1.9 → floor(1.9) = 1
  val avg = sum.toDouble() / a.size
  val mean = if (avg >= 0) floor(avg + 0.5).toInt() else ceil(avg - 0.5).toInt()

  // 2) 중앙값
  val targetIdx = (a.size + 1) / 2
  var median = 0
  var acc = 0

  run {
    for (idx in minIdx..maxIdx) {
      acc += cnt[idx]
      if (acc >= targetIdx) {
        median = idx - offset
        break
      }
    }
  }

  // 3) 최빈값 (동률 시, 두 번째 작은 값)
  var maxFreq = 0
  for (i in minIdx..maxIdx) if (cnt[i] > maxFreq) maxFreq = cnt[i]
  var seen = 0
  var first = 0
  var second = 0
  for (idx in minIdx..maxIdx) {
    if (cnt[idx] == maxFreq) {
      seen++
      if (seen == 1) first = idx - offset
      else if (seen == 2) {
        second = idx - offset
        break
      }
    }
  }
  val mode = if (seen >= 2) second else first
  // 4) 범위
  val range = maxIdx - minIdx

  return buildString {
    append(mean).append('\n')
    append(median).append('\n')
    append(mode).append('\n')
    append(range)
  }
}
