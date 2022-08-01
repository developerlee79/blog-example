package `manachers-algorithm`

import kotlin.math.min

class ManachersAlgorithm {

    fun bruteForce(str: String): String {
        var maxLength = 1
        var start = 0

        for (i in str.indices) {
            for (j in i until str.length) {
                val range = j - i + 1
                var isPalindrome = true

                for (k in 0 until range / 2) {
                    if (str[i + k] != str[j - k]) {
                        isPalindrome = false
                        break
                    }
                }

                if (isPalindrome && range > maxLength) {
                    start = i
                    maxLength = range
                }
            }
        }

        return str.substring(start, start + maxLength)
    }

    fun dp(str: String): String {
        val len = str.length
        val table = Array(len) { BooleanArray(len) }

        for (i in str.indices) {
            table[i][i] = true
        }

        var start = 0
        var maxLength = 1
        for (i in 0 until len - 1) {
            if (str[i] == str[i + 1]) {
                table[i][i + 1] = true
                start = i
                maxLength = 2
            }
        }

        for (i in 3..len) {
            for (j in 0 until len - i + 1) {
                val range = j + i - 1

                if (table[j + 1][range - 1] && str[j] == str[range]) {
                    table[j][range] = true

                    if (i > maxLength) {
                        start = j
                        maxLength = i
                    }
                }
            }
        }

        return str.substring(start, start + maxLength)
    }

    fun manachersAlgorithm(str: String): String {
        val length = str.length * 2 + 3

        val charArray = CharArray(length)

        charArray[0] = '^'
        charArray[charArray.lastIndex] = '$'
        var p = 1
        for (c in str) {
            charArray[p++] = '#'
            charArray[p++] = c
        }
        charArray[p] = '#'

        var maxLength = 0
        var start = 0
        var center = 0
        var maxRight = 0
        val table = IntArray(length)

        for (i in 1 until length - 1) {
            if (i < maxRight) {
                table[i] = min(maxRight - i, table[center * 2 - i])
            }

            while (charArray[i + table[i] + 1] == charArray[i - table[i] - 1]) {
                table[i]++
            }

            if (i + table[i] > maxRight) {
                center = i
                maxRight = i + table[i]
            }

            if (table[i] > maxLength) {
                start = (i - table[i] - 1) / 2
                maxLength = table[i]
            }
        }

        return str.substring(start, start + maxLength)
    }
}

fun main() {
    val str = "abccba"
    println(ManachersAlgorithm().bruteForce(str))
    println(ManachersAlgorithm().dp(str))
    println(ManachersAlgorithm().manachersAlgorithm(str))
}
