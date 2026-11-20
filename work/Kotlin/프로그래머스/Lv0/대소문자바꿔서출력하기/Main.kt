package 프로그래머스.Lv0.대소문자바꿔서출력하기

private const val A = 65
private const val a = 97
fun main(args: Array<String>) {
  val s1 = readLine()!!
  print(s1.map { c ->
    val code = c.code
    (if (code >= a) {
      code - a + A
    } else {
      code - A + a
    }).toChar()
  }.joinToString(""))
}

/**
 * [ME]
 * 테스트 1 〉	통과 (157.84ms, 53MB)
 * 테스트 2 〉	통과 (101.44ms, 53.3MB)
 * 테스트 3 〉	통과 (68.27ms, 53.9MB)
 * 테스트 4 〉	통과 (2486.50ms, 53.1MB)
 *
 * [RIVAL 1]
 * 테스트 1 〉	통과 (77.84ms, 53.2MB)
 * 테스트 2 〉	통과 (114.09ms, 53.3MB)
 * 테스트 3 〉	통과 (150.83ms, 53.3MB)
 * 테스트 4 〉	통과 (162.64ms, 53.2MB)
 */
