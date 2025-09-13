package 백준.Bronze.no27433

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {

  @Test
  fun solution() {
    assertEquals(1, FACT[0])
    assertEquals(1, FACT[1])
    assertEquals(2, FACT[2])
    assertEquals(6, FACT[3])
    assertEquals(24, FACT[4])
    assertEquals(3628800, FACT[10])
    assertEquals(2432902008176640000, FACT[20])
  }
}
