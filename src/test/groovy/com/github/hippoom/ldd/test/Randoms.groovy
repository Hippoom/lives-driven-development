package com.github.hippoom.ldd.test

class Randoms {
    static String randomString() {
        UUID.randomUUID().toString().replaceAll("-", "")
    }
}
