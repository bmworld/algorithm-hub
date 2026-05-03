package 프로그래머스.입문.Day6.직각삼각형출력하기

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 4
const val OBS = 1 shl 9
val O = BufferedOutputStream(System.out, OBS)
val I = BufferedInputStream(System.`in`)
val IB = ByteArray(IBS)
var Ii = 0
var Il = 0
const val EOF = -1

fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

val NUM = 48..57
fun i(): Int {
  var v = 0
  var b: Byte
  while (r().also { b = it } in NUM) v = v * 10 + b - 48
  return v
}

const val star: Byte = 42
val stars = byteArrayOf(star, star, star, star, star, star, star, star, star, star)

fun main(args: Array<String>) {
  val n = i()
  repeat(n) {
    O.write(stars, 0, it + 1)
    O.write(10)
  }
  O.flush()
}

//테스트 1 〉	통과 (349.81ms, 46.9MB)
//테스트 2 〉	통과 (249.17ms, 47.3MB)
//테스트 3 〉	통과 (402.25ms, 47MB)
//테스트 4 〉	통과 (338.52ms, 46.8MB)
//테스트 5 〉	통과 (235.76ms, 46.9MB)
//테스트 6 〉	통과 (221.70ms, 46.6MB)
//테스트 7 〉	통과 (151.37ms, 47.1MB)
//테스트 8 〉	통과 (142.62ms, 47.3MB)
//테스트 9 〉	통과 (203.94ms, 47.5MB)
//테스트 10 〉	통과 (167.83ms, 47.4MB)
