package 프로그래머스.알고리즘고득점Kit.DFS_BFS.단어변환

import util.validate

class Solution {

  fun solution(begin: String, target: String, words: Array<String>): Int {
    if (!words.contains(target)) return 0

    val N = words.size
    val len = begin.length
    val used = BooleanArray(N)
    val q = Array(N + 1) { Pair(begin, 0) }
    var qh = 0
    var qt = 1

    while (qh < qt) {
      val e = q[qh++]
      val a = e.first

      l@ for (i in words.indices) {
        if (used[i]) continue
        val b = words[i]
        var diff = 0
        for (j in 0 until len) if (a[j] != b[j]) if (++diff > 1) continue@l

        when {
          b == target -> return e.second + 1
          else -> {
            used[i] = true
            q[qt++] = Pair(b, e.second + 1)
          }
        }
      }
    }
    return 0
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (8.66ms, 61.9MB)
 * 테스트 2 〉	통과 (8.95ms, 62.8MB)
 * 테스트 3 〉	통과 (9.08ms, 63MB)
 * 테스트 4 〉	통과 (9.13ms, 61.2MB)
 * 테스트 5 〉	통과 (8.81ms, 63.4MB)
 *
 * v2: DFS -> BFS
 * 테스트 1 〉	통과 (8.99ms, 63.5MB)
 * 테스트 2 〉	통과 (8.82ms, 63.5MB)
 * 테스트 3 〉	통과 (11.31ms, 63.3MB)
 * 테스트 4 〉	통과 (10.11ms, 63.3MB)
 * 테스트 5 〉	통과 (8.99ms, 63.5MB)
 *
 * v3: BFS 자료구조 변경 (IntArray+HashMap -> Array<Pair>)
 * 테스트 1 〉	통과 (9.50ms, 63.3MB)
 * 테스트 2 〉	통과 (8.95ms, 63.5MB)
 * 테스트 3 〉	통과 (10.43ms, 63.2MB)
 * 테스트 4 〉	통과 (9.06ms, 63.8MB)
 * 테스트 5 〉	통과 (9.71ms, 63.8MB)
 *
 * [RIVAL 1]
 * import java.util.*
 *
 * class Solution {
 *     fun String.diff(other:String):Int {
 *         return this.filterIndexed { index, c -> c != other.get(index) }.map { 1 }.sum()
 *     }
 *
 *     class Word(val level:Int,val txt:String,val check:Array<Boolean>)
 *
 *     fun solution(begin: String, target: String, words: Array<String>): Int {
 *         val q: Queue<Word> = LinkedList()
 *         val checkArray = Array(words.size) { false }
 *         q.add(Word(0,begin,checkArray))
 *         var ret = 0
 *         while(!q.isEmpty()) {
 *             val w = q.poll()
 *             if(w.txt == target) {
 *                 ret = w.level
 *                 break
 *             }
 *             w.check.forEachIndexed { index, b ->
 *                 if(!b && (w.txt.diff(words[index]) == 1)) {
 *                     q.add(Word(w.level+1,words[index],w.check.clone().apply { set(index,true) }))
 *                 }
 *             }
 *         }
 *         return ret
 *     }
 * }
 * 테스트 1 〉	통과 (4.55ms, 59.9MB)
 * 테스트 2 〉	통과 (4.60ms, 61MB)
 * 테스트 3 〉	통과 (5.68ms, 60MB)
 * 테스트 4 〉	통과 (4.28ms, 60.7MB)
 *
 * [RIVAL 2]
 * import kotlin.math.min
 *
 * class Solution {
 *     fun solution(begin: String, target: String, words: Array<String>): Int {
 *         var answer = 0
 *         val n=words.size
 *         val adj=Array(n){BooleanArray(n)}
 *         var targetIdx=-1
 *         for(i in 0 until n){
 *             if(target==words[i]) targetIdx=i
 *         }
 *         fun checkString(str1:String,str2:String):Int{
 *             var count=0
 *             for(i in 0 until str1.length){
 *                 if(str1[i]!=str2[i])    count++
 *             }
 *             return count
 *         }
 *         for(i in 0 until n){
 *             for(j in 0 until n){
 *                 if(checkString(words[i],words[j])==1){
 *                     adj[i][j]=true
 *                 }
 *             }
 *         }
 *
 *         val visit=BooleanArray(n)
 *         val q=java.util.ArrayDeque<Pair<Int,Int>>()
 *         if(targetIdx!=-1){
 *             answer=Int.MAX_VALUE
 *             for(i in 0 until n){
 *                 val visit=BooleanArray(n)
 *                 if(checkString(begin,words[i])==1){
 *                     visit[i]=true
 *                     q.add(Pair(i,1))
 *                     while(!q.isEmpty()){
 *                         val p=q.poll()
 *                         if(p.first==targetIdx)  {
 *                             answer=min(answer,p.second)
 *                             break
 *                         }
 *                         for(j in 0 until n){
 *                             if(!visit[j]&&adj[p.first][j]){
 *                                 visit[j]=true
 *                                 q.add(Pair(j,p.second+1))
 *                             }
 *                         }
 *                     }
 *                 }
 *             }
 *         }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (0.26ms, 59.7MB)
 * 테스트 2 〉	통과 (0.46ms, 59.6MB)
 * 테스트 3 〉	통과 (0.85ms, 59.1MB)
 * 테스트 4 〉	통과 (0.35ms, 59.6MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("hit", "cog", arrayOf(
    "hot", "dot", "dog", "lot", "log", "cog"
  )), 4)

  validate(s.solution("hit", "cog", arrayOf(
    "hot", "dot", "dog", "lot", "log"
  )), 0)

  validate(s.solution("hit", "hot", arrayOf(
    "hot", "dot", "dog", "lot", "hit"
  )), 1)

  validate(s.solution("aaa", "bbb", arrayOf(
    "aab", "abb", "bbb"
  )), 3)
}
