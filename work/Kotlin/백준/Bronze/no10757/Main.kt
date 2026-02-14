package 백준.Bronze.no10757

import java.util.*

fun main() {
  val str = StringTokenizer(readlnOrNull())
  print(str.nextToken().toBigInteger()
    .plus(str.nextToken().toBigInteger())
  )
}
