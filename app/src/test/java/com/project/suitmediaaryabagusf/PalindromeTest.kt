package com.project.suitmediaaryabagusf

import org.junit.Test

class PalindromeTest {

    private fun isPalindrome(text: String): Boolean {
        val spaceRemoved = text.replace("\\s+".toRegex(), "").lowercase()
        return spaceRemoved == spaceRemoved.reversed()
    }

    @Test
    fun palindromeTest(): Unit {
        assert(isPalindrome("kasur rusak"))
        assert(isPalindrome("step on no pets"))
        assert(isPalindrome("put it up"))
        assert(isPalindrome("dammit im mad"))
        assert(!isPalindrome("suitmedia"))
    }

}