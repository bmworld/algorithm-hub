package 프로그래머스.Lv0.a와b출력하기

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
  val token = StringTokenizer(BufferedReader(InputStreamReader(System.`in`)).readLine())
  val a = token.nextToken()
  val b = token.nextToken()
  print("a = $a\nb = $b")
}

/**
 * ```
 * [ME]
 * v1:
 * 테스트 1 〉	통과 (140.50ms, 51.3MB)
 * 테스트 2 〉	통과 (127.60ms, 51MB)
 * 테스트 3 〉	통과 (142.12ms, 51.4MB)
 * 테스트 4 〉	통과 (136.34ms, 50.8MB)
 * 테스트 5 〉	통과 (158.01ms, 50.8MB)
 *
 * v2:
 * 테스트 1 〉	통과 (79.96ms, 51.4MB)
 * 테스트 2 〉	통과 (109.01ms, 51.1MB)
 * 테스트 3 〉	통과 (74.18ms, 51.4MB)
 * 테스트 4 〉	통과 (85.78ms, 52.4MB)
 * 테스트 5 〉	통과 (73.96ms, 51.5MB)
 *
 * v3:
 * 테스트 1 〉	통과 (72.96ms, 51.7MB)
 * 테스트 2 〉	통과 (65.40ms, 51.2MB)
 * 테스트 3 〉	통과 (59.79ms, 51.2MB)
 * 테스트 4 〉	통과 (75.42ms, 51.7MB)
 * 테스트 5 〉	통과 (64.50ms, 51.3MB)
 *
 * [RIVAL 1]
 *
 * [RIVAL 2]
 * ```
 */
