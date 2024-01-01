//package com.example.mongodb.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Component;
//
//import java.time.Duration;
//import java.time.Instant;
//
//@Component
//@RequiredArgsConstructor
//public class TokenInfoHandler {
//
//    private final CustomAuthProperty authProperty;
//    private final TokenRepository repository;
//
//    @Cacheable(value = "tokenInfoCache", key = "#userEmail")
//    public ExpirableEntry<String> GetTokenId(String userEmail) {
//        var tokenInfo = repository.findFirstTokenIdByUserEmail(userEmail);
//        return tokenInfo
//                .map(info -> ExpirableEntry.create(info.getTokenId(), info.getExpiredAt().toInstant()))
//                .orElseGet(
//                        () ->
//                                ExpirableEntry.create(
//                                        null, Instant.now().plus(Duration.ofDays(authProperty.getExpireTime()))));
//    }
//}
