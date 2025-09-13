import java.io.StreamTokenizer

fun main() =
    with(StreamTokenizer(System.`in`.bufferedReader())) {
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

      print(FACT[nextInt()])
    }

val FACT =
    LongArray(21).apply {
      this[0] = 1
      for (i in 1..20) this[i] = this[i - 1] * i
    }
