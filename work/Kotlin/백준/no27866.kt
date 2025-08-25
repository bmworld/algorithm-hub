package 백준

import java.util.Scanner
import kotlin.text.toInt

fun main() {

    val sc = Scanner(System.`in`)

    val m = sc.nextLine()
    val pos = sc.nextLine()
    println(solution(m, pos.toInt()))


}


fun solution(v: String, pos: Int): Char {
    if(v.isEmpty()) throw NullPointerException("필수값입니다.")

    val chars = v.toCharArray()

    val index = pos - 1

    return chars[index];

}
