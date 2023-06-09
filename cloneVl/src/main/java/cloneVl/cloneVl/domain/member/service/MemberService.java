package cloneVl.cloneVl.domain.member.service;

import cloneVl.cloneVl.domain.member.dto.MemberInfoDto;
import cloneVl.cloneVl.domain.member.dto.MemberSignUpDto;
import cloneVl.cloneVl.domain.member.dto.MemberUpdateDto;

public interface MemberService {

    void signUp(MemberSignUpDto memberSignUpDto) throws Exception;

    void update(MemberUpdateDto memberUpdateDto) throws Exception;

    void updatePassword(String checkPassword, String toBePassword) throws Exception;

    void withdraw(String checkPassword) throws Exception;

    MemberInfoDto getInfo(Long id) throws Exception;

    MemberInfoDto getMyInfo() throws Exception;


}