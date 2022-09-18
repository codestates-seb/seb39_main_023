package com.team23.mainPr.Domain.Login.Service;

import com.team23.mainPr.Domain.Login.Dto.Request.DoLoginDto;
import com.team23.mainPr.Domain.Login.Dto.Response.DoLoginResponseDto;
import com.team23.mainPr.Domain.Login.Entity.Login;
import com.team23.mainPr.Domain.Login.Mapper.LoginMapper;
import com.team23.mainPr.Domain.Login.Repository.LoginRepository;
import com.team23.mainPr.Domain.Member.Entity.Member;
import com.team23.mainPr.Domain.Member.Repository.MemberRepository;
import com.team23.mainPr.Global.DefaultTimeZone;
import com.team23.mainPr.Global.Dto.ChildCommonDto;
import com.team23.mainPr.Global.Dto.ParentCommonDto;
import com.team23.mainPr.Global.Jwt.Service.JwtBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.team23.mainPr.Global.Enum.ChildCommonDtoMsgList.*;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JwtBuilder jwtService;
    private final MemberRepository memberRepository;
    private final LoginRepository loginRepository;
    private final LoginMapper loginMapper;
    private final DefaultTimeZone defaultTimeZone;
    private final JwtBuilder jwtBuilder;

    public ChildCommonDto<DoLoginResponseDto> doLogin(DoLoginDto dto) {

        Member member = memberRepository.findByLoginId(dto.getLoginId());

        if (member == null)
            return new ChildCommonDto<>(NOT_MATCH_ID.getMsg(), HttpStatus.BAD_REQUEST, null);

        if (!member.getPassword().equals(dto.getPassword()))
            return new ChildCommonDto<>(NOT_MATCH_PASSWORD.getMsg(), HttpStatus.BAD_REQUEST, null);

        String token = jwtService.buildJwt(member);
        Login existLogin = loginRepository.findByMemberId(member.getMemberId());

        if (existLogin == null) {

            Login login = new Login();
            login.setLastLoginDate(defaultTimeZone.getNow());
            login.setToken(token);
            login.setLogouted(false);
            login.setMemberId(member.getMemberId());
            login.setLogoutDate(null);
            loginRepository.save(login);

        } else {

            existLogin.setLogouted(false);
            existLogin.setToken(token);
            existLogin.setLogoutDate(null);
            loginRepository.flush();
        }

        return new ChildCommonDto<>(token, HttpStatus.OK, loginMapper.doLoginMap(existLogin));
    }

    public ChildCommonDto<ParentCommonDto> doLogout(String authorization) {

        Login login = loginRepository.findByToken(authorization);

        if (login == null)
            return new ChildCommonDto<>(FALSE.getMsg(), HttpStatus.BAD_REQUEST, null);

        if (login.getLogouted())
            return new ChildCommonDto<>(FAIL.getMsg(), HttpStatus.BAD_REQUEST, null);

        login.setLogouted(Boolean.TRUE);
        login.setLogoutDate(defaultTimeZone.getNow());
        loginRepository.flush();

        return new ChildCommonDto<>(SUC.getMsg(), HttpStatus.OK, null);
    }

    public ChildCommonDto<ParentCommonDto> refreshToken(String token) {

        Login login = loginRepository.findByToken(token);

        if (login == null)
            return new ChildCommonDto<>(FALSE.getMsg(), HttpStatus.BAD_REQUEST, null);

        if (Boolean.FALSE.equals(login.getLogouted()))
            return new ChildCommonDto<>(FAIL.getMsg(), HttpStatus.BAD_REQUEST, null);

        String newToken = jwtBuilder.buildJwt(memberRepository.findById(login.getMemberId()).orElseThrow());
        login.setToken(newToken);
        loginRepository.flush();

        return new ChildCommonDto<>(newToken, HttpStatus.OK, loginMapper.doLoginMap(login));
    }
}
