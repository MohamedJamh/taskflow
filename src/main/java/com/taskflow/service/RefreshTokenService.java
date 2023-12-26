package com.taskflow.service;

import com.taskflow.domain.entity.RefreshToken;
import com.taskflow.exception.customexceptions.InValidRefreshTokenException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface RefreshTokenService {
    RefreshToken createRefreshToken(String email) throws UsernameNotFoundException;
    Optional<RefreshToken> findByToken(String token);
    RefreshToken verifyExpiration(RefreshToken token) throws InValidRefreshTokenException;
    void throwInValidRefreshTokenException(String message) throws InValidRefreshTokenException;
    void delete(RefreshToken token);
}
