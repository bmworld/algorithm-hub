package 백준.Bronze.no25501

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {

  @Test
  fun test() {

    assertEquals("1 2", isPalindrome("AAA"))
    assertEquals("1 3", isPalindrome("ABBA"))
    assertEquals("1 3", isPalindrome("ABABA"))
    assertEquals("0 2", isPalindrome("ABCA"))
    assertEquals("0 1", isPalindrome("PALINDROME"))
  }
}
