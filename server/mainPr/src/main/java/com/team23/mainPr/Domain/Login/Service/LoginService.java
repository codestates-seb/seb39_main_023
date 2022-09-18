package com.team23.mainPr.Domain.Login.Service;

import com.team23.mainPr.Domain.Login.Dto.DoLoginDto;
import com.team23.mainPr.Domain.Login.Dto.DoLoginResponseDto;
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

        try {
            Member member = memberRepository.findByLoginId(dto.getLoginId());

            if (member != null) {
                if (member.getPassword().equals(dto.getPassword())) {

                    String token = jwtService.buildJwt(member);
                    Login existLogin = loginRepository.findByMemberId(member.getMemberId());

                    if (existLogin == null) {

                        Login login = new Login();
                        login.setLastLoginDt(defaultTimeZone.getNow());
                        login.setToken(token);
                        login.setLogouted(false);
                        login.setMemberId(member.getMemberId());
                        login.setLogoutDt(null);
                        loginRepository.save(login);

                        return new ChildCommonDto<>(token, HttpStatus.OK, loginMapper.doLoginMap(login));
                    } else {
                        existLogin.setLogouted(false);
                        existLogin.setToken(token);
                        existLogin.setLogoutDt(null);
                        loginRepository.flush();

                        return new ChildCommonDto<>(token, HttpStatus.OK, loginMapper.doLoginMap(existLogin));
                    }
                }

                return new ChildCommonDto<>(NOT_MATCH_PASSWORD.getMsg(), HttpStatus.BAD_REQUEST, null);
            }

            return new ChildCommonDto<>(NOT_MATCH_ID.getMsg(), HttpStatus.BAD_REQUEST, null);

        } catch (Exception e) {

            return new ChildCommonDto<>(ERROR.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    public ChildCommonDto<ParentCommonDto> doLogout(String authorization) {
        try {
            Login login = loginRepository.findByToken(authorization);

            if (login != null) {
                if (!login.getLogouted()) {
                    login.setLogouted(Boolean.TRUE);
                    login.setLogoutDt(defaultTimeZone.getNow());
                    loginRepository.flush();
                    return new ChildCommonDto<>(SUCSESS.getMsg(), HttpStatus.OK, null);
                }

                return new ChildCommonDto<>(FAIL.getMsg(), HttpStatus.BAD_REQUEST, null);
            }

            return new ChildCommonDto<>(FALSE.getMsg(), HttpStatus.BAD_REQUEST, null);

        } catch (Exception e) {

            return new ChildCommonDto<>(ERROR.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

    }

    public ChildCommonDto<ParentCommonDto> refreshToken(String token) {

        try {
            Login login = loginRepository.findByToken(token);

            if (login != null) {
                if (Boolean.FALSE.equals(login.getLogouted())) {
                    String newToken = jwtBuilder.buildJwt(memberRepository.findById(login.getMemberId()).orElseThrow());
                    login.setToken(newToken);
                    loginRepository.flush();

                    return new ChildCommonDto<>(newToken, HttpStatus.OK, loginMapper.doLoginMap(login));
                }

                return new ChildCommonDto<>(FAIL.getMsg(), HttpStatus.BAD_REQUEST, null);
            }

            return new ChildCommonDto<>(FALSE.getMsg(), HttpStatus.BAD_REQUEST, null);
        } catch (Exception e) {

            return new ChildCommonDto<>(ERROR.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
