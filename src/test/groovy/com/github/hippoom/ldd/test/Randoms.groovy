package com.github.hippoom.ldd.test

class Randoms {
    private static Long current = 1

    static String randomString() {
        UUID.randomUUID().toString().replaceAll("-", "")
    }

    static Long nextLong() {
        current++
    }
}
