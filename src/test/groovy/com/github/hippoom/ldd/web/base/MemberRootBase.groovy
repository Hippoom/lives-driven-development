package com.github.hippoom.ldd.web.base

import com.github.hippoom.ldd.web.AbstractWebMvcTest
import com.github.hippoom.ldd.web.security.WithTyrandeWhisperwind
import org.springframework.security.test.context.support.WithMockUser

@WithTyrandeWhisperwind
abstract class MemberRootBase extends AbstractWebMvcTest {

}
