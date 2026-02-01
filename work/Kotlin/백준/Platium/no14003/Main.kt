package 백준.Platium.no14003

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 15
const val OBS = 1 shl 15
val O = BufferedOutputStream(System.`out`, OBS)
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

const val WS = 10
val WB = ByteArray(WS + 1)
fun w(
  num: Int,
  end: Boolean = false
) {
  WB[WS] = if (end) 10 else 32
  var v = if (num >= 0) num
  else {
    O.write(45)
    -num
  }
  var pos = WS - 1
  do {
    WB[pos--] = (v % 10 + 48).toByte()
    v /= 10
  } while (v > 0)
  O.write(WB, ++pos, WS - pos + 1)
}

const val EMPTY = -1
const val MIN = Int.MIN_VALUE
const val MAX = Int.MAX_VALUE
fun main() {
  val N = i()
  val lis = IntArray(N)
  val a = IntArray(N)
  var li = EMPTY
  val tracer = IntArray(N)
  val midPos = IntArray(N)
  var mi = 0

  repeat(N) { i ->
    val v = i()
    a[i] = v
    tracer[i] = insert(lis, v, 0, li).also {
      if (it > li) {
        li = it
        midPos[mi++] = i
      }
    }
  }

  val cnt = li + 1
  w(cnt, true)

  var fr = midPos[--mi]
  var to = fr
  var nxtFr = to
  var limit = MAX
  var traced = MIN
  var tracedIdx = tracer[to]
  while (fr >= 0 && fr >= to) {
    val v = a[fr]
    if (tracer[fr] != tracedIdx) {
      fr--
      continue
    }

    if (v < limit && v > traced) {
      traced = v
      nxtFr = fr
    }

    if (fr == to) {
      lis[li--] = traced
      fr = nxtFr
      to = if (mi > 0) midPos[--mi] else EMPTY
      limit = traced
      traced = MIN
      tracedIdx--
    } else fr--
  }

  repeat(cnt) {
    w(lis[it])
  }

  O.flush()
}

fun insert(
  lis: IntArray,
  v: Int,
  stt: Int,
  end: Int,
): Int {

  var l = stt
  var r = end

  if (end < 0 || v > lis[end]) l = end + 1
  else {
    while (l < r) {
      val m = (l + r) shr 1
      var mv = lis[m]
      when {
        mv < v -> l = m + 1
        else -> r = m
      }
    }
  }

  return l.also { lis[it] = v }
}

//println("--- $fr -> $to / $traced vs $v")
//println("selected = ${tracer[nxtFr]} (idx=$nxtFr)")

//repeat(N) {
//  println("--- tracer[$it] = ${tracer[it]}")
//}
//repeat(mi) {
//  println("midPos = ${midPos[it]}")
//}

/**
 *
 * --- IN
18
22448907 166821549 -83447507 548458133 -35744170 -148118802 642912516 -500883862 -988050853 723862044 -724439865 319238538 -50345820 -751794198 -631599996 -476656461 -643499516 -133603860

--- OUT
5
-988050853 -751794198 -631599996 -476656461 -133603860

--- IN
45
-664087968 556858983 861230103 407638927 635072576 -341706136 333083618 61385743 -887588018 -181148234 -971649277 -13854244 413163559 -417677486 -8219921 -523330250 40345353 698637398 -672304152 -569308564 -939676071 270523181 -502069821 -24512649 518918609 242717808 646652986 717340628 -176611508 605238332 985438874 -379719218 644844815 -666926334 -428319596 -974037906 -692942420 554103995 959078808 -187313575 569432338 280915504 834414226 -681427240 41115783

---OUT
11
-664087968 -341706136 -181148234 -13854244 -8219921 40345353 270523181 518918609 554103995 569432338 834414226

--- IN
42
547610954 -599209725 -448887194 86719718 789641334
924273149 -144630028 606157741 -323552542 711508452
-749170304 639555717 484089500 -190385175 186161042
-906612695 970986145 967423762 -827029388 246503929
847031324 946562978 550118347 -980276663 -685326576
88352876 -218064096 -716725641 -925691708 713406603
502518134 -434903109 -412243339 953953886 -646703882
-694645309 167992215 522202765 -128154427 978221315
622098657 581576684

--- OUT
10
-599209725 -448887194 -323552542 -190385175 186161042
246503929 550118347 713406603 953953886 978221315

 */
