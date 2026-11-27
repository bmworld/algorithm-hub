package util

fun getIntArr(s: String): IntArray {
  return s.trim()
    .split(" ")
    .map { it.toInt() }
    .toIntArray()
}

fun toChar(c: Byte): Char = toChar(c.toInt())
fun toChar(c: Int): Char = c.toChar()

/**
 * 용도: 알고리즘 실행시간 측정
 */
class Timer(
  private val unit: Double = 1_000_000_000.0
) {

  private var sttAt: Long = 0L
  private var endAt: Long = 0L
  private var lastLapAt: Long = 0L
  private var done: Boolean = false

  init {
    start()
  }

  private fun start() {
    sttAt = System.nanoTime()
    lastLapAt = sttAt
    done = false
    printTime("start")
  }

  fun lap() {
    check(!done) { "[Timer] Already stopped" }
    val now = System.nanoTime()
    val t = (now - lastLapAt) / unit
    lastLapAt = now
    printTime("lap", t)
  }

  fun stop() {
    check(!done) { "[Timer] Already stopped" }
    endAt = System.nanoTime()
    done = true
    printTime("stop", (endAt - sttAt) / unit)
  }

  private fun printTime(
    title: String,
    time: Double = 0.0
  ) {
    println(String.format("[Timer] $title: %.7f sec", time))
  }
}


fun validate(actual: IntArray, expect: IntArray) {
  check(actual.size == expect.size) {
    "size mismatch: actual=${actual.size}, expect=${expect.size}\n" +
      "actual=${actual.contentToString()}\n" +
      "expect=${expect.contentToString()}"
  }

  for (i in actual.indices) {
    check(actual[i] == expect[i]) {
      "index[$i] mismatch: actual=${actual[i]}, expect=${expect[i]}\n" +
        "actual=${actual.contentToString()}\n" +
        "expect=${expect.contentToString()}"
    }
  }
}

fun validate(actual: LongArray, expect: LongArray) {
  check(actual.size == expect.size) {
    "size mismatch: actual=${actual.size}, expect=${expect.size}\n" +
      "actual=${actual.contentToString()}\n" +
      "expect=${expect.contentToString()}"
  }

  for (i in actual.indices) {
    check(actual[i] == expect[i]) {
      "index[$i] mismatch: actual=${actual[i]}, expect=${expect[i]}\n" +
        "actual=${actual.contentToString()}\n" +
        "expect=${expect.contentToString()}"
    }
  }
}

fun validate(actual: DoubleArray, expect: DoubleArray) {
  check(actual.size == expect.size) {
    "size mismatch: actual=${actual.size}, expect=${expect.size}\n" +
      "actual=${actual.contentToString()}\n" +
      "expect=${expect.contentToString()}"
  }

  for (i in actual.indices) {
    check(actual[i] == expect[i]) {
      "index[$i] mismatch: actual=${actual[i]}, expect=${expect[i]}\n" +
        "actual=${actual.contentToString()}\n" +
        "expect=${expect.contentToString()}"
    }
  }
}


fun <T> validate(actual: Array<T>, expect: Array<T>) {
  check(actual.contentEquals(
    expect)) { "actual=${actual.contentToString()}, expect=${expect.contentToString()}" }
}


fun validate(actual: Array<IntArray>, expect: Array<IntArray>) {
  check(actual.size == expect.size) {
    "row size mismatch: actual=${actual.size}, expect=${expect.size}"
  }

  for (i in actual.indices) {
    check(actual[i].contentEquals(expect[i])) {
      "row[$i] mismatch: actual=${actual[i].contentToString()}, expect=${expect[i].contentToString()}"
    }
  }
}


fun <T> validate(actual: T, expect: T) {
  check(actual == expect) { "actual=${actual}, expect=$expect" }
}

/**
 * 3way quick sort
 */
fun swap(
  a: IntArray,
  i: Int,
  j: Int,
) {
  val tmp = a[i]
  a[i] = a[j]
  a[j] = tmp
}

fun qs(
  a: IntArray,
  l: Int,
  r: Int,
) {
  if (l >= r) return

  var pos = l
  var pl = l
  var pr = r
  val piv = a[(l + r) shr 1]

  while (pos <= pr) {
    val x = a[pos]
    when {
      x < piv -> swap(a, pos++, pl++)
      x > piv -> swap(a, pos, pr--)
      else -> pos++
    }
  }
  qs(a, l, pl - 1)
  qs(a, pr + 1, r)
}


class HEAP(size: Int) {

  val heap = LongArray(size + 1)
  val root = 1
  var len = 0

  fun clear() {
    len = 0
  }

  fun isNotEmpty() = len > 0

  fun push(v: Long) {
    var ci = ++len
    heap[len] = v
    while (ci > root) {
      val pi = ci shr 1
      val p = heap[pi]
      val c = heap[ci]
      if (p > c) {
        heap[pi] = c
        heap[ci] = p
        ci = pi
      } else break
    }
  }

  fun pop(): Long {
    if (len == 0) return 0
    val v = heap[root]
    val x = heap[len--]

    var pi = root
    var ci = root shl 1
    while (ci <= len) {
      val ri = ci + 1
      if (ri <= len && heap[ri] < heap[ci]) ci++
      if (heap[ci] >= x) break
      heap[pi] = heap[ci]
      pi = ci
      ci = pi shl 1
    }
    heap[pi] = x
    return v
  }
}
