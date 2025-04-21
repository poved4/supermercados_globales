package com.ucentral.software.auth.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.ucentral.software.auth.model.dto.SignInRequest;
import com.ucentral.software.auth.model.entity.Account;
import com.ucentral.software.auth.model.entity.Session;
import com.ucentral.software.auth.repository.AccountRepository;
import com.ucentral.software.auth.repository.SessionRepository;
import com.ucentral.software.exc.model.UnauthorizedException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final JwtService jwtService;
  private final SessionRepository sessionRepository;
  private final AccountRepository accountRepository;

  private String getAccessToken(String authorizationHeader) {

    if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
      throw new IllegalArgumentException("Invalid Bearer Token");
    }

    return authorizationHeader.substring(7).trim();

  }

  private Session getCurrentSession(String accessToken) {
    return sessionRepository
        .findByAccessToken(accessToken)
        .orElse(null);
  }

  public String signIn(SignInRequest request) {

    Account account = accountRepository
        .findByEmail(request.getEmail())
        .orElseThrow(
          () -> new UnauthorizedException("Invalid credentials")
        );

    if (!request.getPassword().equals(account.getPassword())) {
      throw new UnauthorizedException("Invalid credentials");
    }

    String accessToken = jwtService.generateToken(account);

    Session session = Session.builder()
      .accountId(account.getId())
      .loginStatus((byte) 1)
      .accessToken(accessToken)
      .ipAddress(request.getIpAddress())
      .deviceUsed(request.getDeviceUsed())
      .loginTime(LocalDateTime.now())
      .build();

    sessionRepository.save(session);

    return accessToken;

  }

  public Boolean logOut(String authorizationHeader) {

    final String accessToken = getAccessToken(authorizationHeader);
    Session currentSession = getCurrentSession(accessToken);

    if (currentSession == null) {
      throw new UnauthorizedException("Invalid or expired token");
    }

    if (currentSession.getLoginStatus() == 0) {
      return Boolean.TRUE;
    }

    currentSession.setLoginStatus((byte) 0);
    currentSession.setLogoutTime(LocalDateTime.now());
    sessionRepository.save(currentSession);

    // "Logged out successfully"
    return Boolean.TRUE;

  }

  public Boolean validateToken(String authorizationHeader) {

    final String accessToken = getAccessToken(authorizationHeader);

    if (jwtService.isTokenExpired(accessToken)) {
      throw new UnauthorizedException("Invalid or expired token");
    }

    final String userEmail = jwtService.extractUsername(accessToken);
    var account = accountRepository.findByEmail(userEmail);

    if (!account.isPresent()) {
      throw new IllegalArgumentException("Invalid Token");
    }

    Session currentSession = getCurrentSession(accessToken);
    if (currentSession == null) {
      throw new UnauthorizedException("Invalid or expired token");
    }

    if (account.get().getId() != currentSession.getAccountId()) {
      throw new UnauthorizedException("token does not match the account");
    }

    return Boolean.TRUE;

  }

}