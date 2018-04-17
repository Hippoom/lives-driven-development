package com.github.hippoom.ldd.hashids

import org.hashids.Hashids
import spock.lang.Specification

class HashIdsLearningTest extends Specification {

    Hashids subject = new Hashids("this is my salt")

    def "it should convert numeric to short hash id"() {

        when:
        String hash = subject.encode(12345)

        then:
        println(hash)
        //noinspection GrEqualsBetweenInconvertibleTypes
        assert subject.decode(hash) == [12345]
    }
}
