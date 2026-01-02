import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 128
private const val OBS = 20_000
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = BufferedInputStream(System.`in`)
private val IB = ByteArray(IBS)
private var Ii = 0
private var Il = 0
private const val EOF = -1

private fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

private val NUM = 48..57
private fun i(): Int {
  var v = 0
  var s = 1
  var c: Byte
  while (r().also { c = it } in NUM || c == 45.toByte()) {
    when (c) {
      in NUM -> v = v * 10 + c - 48
      else -> s = -1
    }
  }
  return s * v
}

private val narrative = "어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n".toByteArray()
private val question = "\"재귀함수가 뭔가요?\"\n".toByteArray()
private val vbs1 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n".toByteArray()
private val vbs2 = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n".toByteArray()
private val vbs3 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n".toByteArray()
private val answer = "\"재귀함수는 자기 자신을 호출하는 함수라네\"\n".toByteArray()
private val end = "라고 답변하였지.\n".toByteArray()
private val indent = "____".toByteArray()

fun main() {

  val n = i()

  fun w(
    dep: Int,
    text: ByteArray,
  ) {
    repeat(dep) {
      O.write(indent)
    }
    O.write(text)
  }

  fun rcsv(dep: Int) {
    w(dep, question)
    if (dep == n) {
      w(dep, answer)
    } else {
      w(dep, vbs1)
      w(dep, vbs2)
      w(dep, vbs3)
      rcsv(dep + 1)
    }
    w(dep, end)
  }

  O.write(narrative)
  rcsv(0)
  O.flush()
}