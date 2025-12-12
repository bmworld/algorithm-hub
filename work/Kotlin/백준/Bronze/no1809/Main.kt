package 백준.Bronze.no1809

import java.io.BufferedOutputStream

private const val OBS = 58
private val O = BufferedOutputStream(System.`out`, OBS)
private const val ln = 10.toByte()
private val MOO = byteArrayOf(
  40, 95, 95, 95, 41, ln,
  40, 111, 32, 111, 41, 95, 95, 95, 95, 47, ln,
  32, 64, 64, 32, 32, 32, 32, 32, 32, 92, ln,
  32, 32, 92, 32, 95, 95, 95, 95, 44, 47, ln,
  32, 32, 47, 47, 32, 32, 32, 47, 47, ln,
  32, 94, 94, 32, 32, 32, 94, 94, ln,
)

fun main() {
  O.write(MOO)
  O.flush()
}

/** [*] 제출언어 `GOLFSCRIPT` > 정답
'(___)
(o o)____/
@@      \
\ ____,/
//   //
^^   ^^'
 */
