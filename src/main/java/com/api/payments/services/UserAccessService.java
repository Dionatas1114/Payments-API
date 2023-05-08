package com.api.payments.services;

import com.api.payments.dto.DigitCodeDto;
import com.api.payments.dto.LoginDto;
import com.api.payments.dto.TokenDto;

public interface UserAccessService {

    TokenDto findUserByEmail(LoginDto loginDto) throws Exception;

    TokenDto getDigitCode(DigitCodeDto digitCodeDto) throws Exception;

}
