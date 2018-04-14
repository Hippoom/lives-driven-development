package com.github.hippoom.ldd.web.security;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import lombok.Setter;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpMethod.POST;

@Setter
public class WeChatMiniAppLoginFilter extends AbstractAuthenticationProcessingFilter {

    private WxMaService wxMaService;

    public WeChatMiniAppLoginFilter() {
        super(new AntPathRequestMatcher("/loginViaWeChatMiniApp", POST.name()));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            return toAuthentication(this.wxMaService.getUserService().getSessionInfo(request.getParameter("code")));
        } catch (WxErrorException err) {
            throw new AuthenticationServiceException(err.getMessage(), err);
        }
    }

    private Authentication toAuthentication(WxMaJscode2SessionResult session) {
        WeChatMiniAppAuthentication authentication = new WeChatMiniAppAuthentication();
        authentication.setUnionId(session.getUnionid());
        authentication.setOpenId(session.getOpenid());
        authentication.setSessionKey(session.getSessionKey());
        authentication.setAuthenticated(true);
        return authentication;
    }
}
