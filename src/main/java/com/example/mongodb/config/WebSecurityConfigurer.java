package com.example.mongodb.config;

import com.example.mongodb.config.matcher.CustomAuthMatcher;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jose.jws.JwsAlgorithms;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.web.HeaderBearerTokenResolver;
import org.springframework.security.web.util.matcher.RequestMatcher;

import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    //private TokenInfoHandler tokenInfoHandler;
    //private UmsIntegrationConverter umsIntegrationConverter;

    @Bean("customAuthMatcher")
    public RequestMatcher customMatcher() {
        return new CustomAuthMatcher();
    }

    @Bean("customAuthBearerResolver")
    public BearerTokenResolver customBearerResolver() {
        return new HeaderBearerTokenResolver("X-Authorization");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(customMatcher())
                .authorizeRequests()
                .antMatchers("/actuator/**")
                .anonymous()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .bearerTokenResolver(customBearerResolver())
                .jwt(
                        jwt -> jwt.decoder(customDecoder())
                                .jwtAuthenticationConverter(null))
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean("customAuthDecoder")
    public JwtDecoder customDecoder() {
        var secretKey = convertKey();
        var decoder =
                NimbusJwtDecoder.withSecretKey(secretKey)
                        .macAlgorithm(MacAlgorithm.HS256).build();
        var customValidator =
                new DelegatingOAuth2TokenValidator<>(
                        new JwtTimestampValidator(),
                        //new JwtIssuerValidator(customAuthProperty.getIssuerUri()),
                        new JwtIssuerValidator(""),
                        audienceValidator(),
                        tokenIdValidator());
        decoder.setJwtValidator(customValidator);
        return decoder;
    }

    private String base64Secret;

    private SecretKey convertKey() {
        return new SecretKeySpec(Base64.getDecoder()
                .decode(base64Secret),
                JwsAlgorithms.HS512);
    }

    private OAuth2TokenValidator<Jwt> audienceValidator() {
        return new JwtClaimValidator<List<String>>(
                //JwtClaimNames.AUD, aud -> aud != null && aud.contains(customAuthProperty.getAudience()));
                JwtClaimNames.AUD, aud -> aud != null && aud.contains("audience"));
    }

    private OAuth2TokenValidator<Jwt> tokenIdValidator() {

        //return new TokenIdValidator(tokenInfoHandler);
        return new TokenIdValidator();
    }

    @AllArgsConstructor
    private static class TokenIdValidator implements OAuth2TokenValidator<Jwt> {

        //private final TokenInfoHandler tokenInfoHandler;

        @Override
        public OAuth2TokenValidatorResult validate(Jwt jwt) {
            var email = jwt.getSubject();
            //var tokenIdInfo = tokenInfoHandler.GetTokenId(email);

            if (email == null)
            //if (tokenIdInfo == null)
//                    || tokenIdInfo.getData() == null
//                    || !StringUtils.equals(tokenIdInfo.getData(), jwt.getId()))
                return OAuth2TokenValidatorResult.failure(
                        new OAuth2Error("The " + JwtClaimNames.JTI + " claim is not valid"));
            return OAuth2TokenValidatorResult.success();
        }
    }

//
////   Autowired @Autowired
////    UserDetailsServiceImpl userDetailsService;
////    @Autowired
////    private AuthEntryPointJwt unauthorizedHandler;
////    @Autowired
////    CustomAuthenticationProvider customAuthenticationProvider;
//    @Override
//    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder.userDetailsService(userDetailsService::loadUserByUsername)
//                .passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    public AuthTokenFilter authenticationJwtTokenFilter() {
//        return new AuthTokenFilter();
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//    @Value("${yourapp.http.auth-token-header-name}")
//    private String principalRequestHeader;
//    @Value("${yourapp.http.auth-token}")
//    private String principalRequestValue;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.cors().and().csrf().disable();
//
//        // don't authenticate this particular request
//        http.authorizeRequests().antMatchers(
//                "/", "/auth"
//        ).permitAll();
//
//        //skip for swagger ui
//        http.authorizeRequests().antMatchers(
//                "/auth/**",
//                "/swagger-ui/**",
//                "/api/**",
//                "/swagger",
//                "/swagger/**",
//                "/swagger-resources/**",
//                "/v2/**",
//                "/v3/**"
//        ).permitAll();
//
//
//        // all other requests need to be authenticated
//        http.authorizeRequests().anyRequest().authenticated()
//                .and()
//                // make sure we use stateless session; session won't be used to
//                // store user's state.
//                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        //http.addFilter(filter);
//        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//    }
}
