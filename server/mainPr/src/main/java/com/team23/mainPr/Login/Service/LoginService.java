package com.team23.mainPr.Login.Service;

import com.team23.mainPr.DefaultTimeZone;
import com.team23.mainPr.Dto.ChildCommonDto;
import com.team23.mainPr.Jwt.Service.JwtBuilder;
import com.team23.mainPr.Login.Dto.CreateLoginDto;
import com.team23.mainPr.Login.Entity.Login;
import com.team23.mainPr.Login.Mapper.LoginMapper;
import com.team23.mainPr.Login.Repository.LoginRepository;
import com.team23.mainPr.Member.Entity.Member;
import com.team23.mainPr.Member.Mapper.MemberMapper;
import com.team23.mainPr.Member.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.team23.mainPr.Enum.ChildCommonDtoMsgList.*;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JwtBuilder jwtService;
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final LoginRepository loginRepository;
    private final LoginMapper loginMapper;
    private final DefaultTimeZone defaultTimeZone;
    private final JwtBuilder jwtBuilder;

    public ChildCommonDto doLogin(CreateLoginDto dto) {

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
                        return new ChildCommonDto(token, HttpStatus.OK, loginMapper.doLoginMap(login));
                    } else {
                        existLogin.setLogouted(false);
                        existLogin.setToken(token);
                        existLogin.setLogoutDt(null);
                        loginRepository.flush();
                        return new ChildCommonDto(token, HttpStatus.OK, loginMapper.doLoginMap(existLogin));
                    }
                }

                return new ChildCommonDto(NOT_MATCH_PASSWORD.getMsg(), HttpStatus.BAD_REQUEST, null);
            }

            return new ChildCommonDto(NOT_MATCH_ID.getMsg(), HttpStatus.BAD_REQUEST, null);

        } catch (Exception e) {

            return new ChildCommonDto(ERROR.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    public ChildCommonDto doLogout(String authorization) {
        try {
            Login login = loginRepository.findByToken(authorization);

            if (login != null) {
                if (!login.getLogouted()) {
                    login.setLogouted(Boolean.TRUE);
                    login.setLogoutDt(defaultTimeZone.getNow());
                    loginRepository.flush();
                    return new ChildCommonDto(SUC.getMsg(), HttpStatus.OK, null);
                }

                return new ChildCommonDto(FAIL.getMsg(), HttpStatus.BAD_REQUEST, null);
            }

            return new ChildCommonDto(FALSE.getMsg(), HttpStatus.BAD_REQUEST, null);

        } catch (Exception e) {

            return new ChildCommonDto(ERROR.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

    }

    public ChildCommonDto refreshToken(String token) {

        try {
            Login login = loginRepository.findByToken(token);

            if (login != null) {
                if (login.getLogouted()) {
                    String newToken = jwtBuilder.buildJwt(memberRepository.findById(login.getMemberId()).orElseThrow());
                    login.setToken(newToken);
                    loginRepository.flush();
                    return new ChildCommonDto(newToken, HttpStatus.OK, loginMapper.doLoginMap(login));
                }

                return new ChildCommonDto(FAIL.getMsg(), HttpStatus.BAD_REQUEST, null);
            }

            return new ChildCommonDto(FALSE.getMsg(), HttpStatus.BAD_REQUEST, null);
        } catch (Exception e) {

            return new ChildCommonDto(ERROR.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
