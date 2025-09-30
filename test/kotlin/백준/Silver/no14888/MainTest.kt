package 백준.Silver.no14888

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {

  @Test
  fun solution() {
    assertEquals("30\n30", solution(getArr("5 6"), getOpArr("0 0 1 0")))
    assertEquals("35\n17", solution(getArr("3 4 5"), getOpArr("1 0 1 0")))
    assertEquals("54\n-24", solution(getArr("1 2 3 4 5 6"), getOpArr("2 1 1 1")))
  }

  fun getArr(v: String): IntArray {
    return v.trim().split(" ").map { it.toInt() }.toIntArray()
  }

  fun getOpArr(v: String): IntArray {
    val map = v.trim().split(" ").map { it.toInt() }
    map.toIntArray()
    val pl = map[0]
    val mi = map[1]
    val mu = map[2]
    val di = map[3]
    val ops = IntArray(pl + mi + mu + di)

    for (i in 0 until pl) ops[i] = 1
    for (i in pl until pl + mi) ops[i] = 2
    for (i in pl + mi until pl + mi + mu) ops[i] = 3
    for (i in pl + mi + mu until pl + mi + mu + di) ops[i] = 4
    return ops
  }
}
