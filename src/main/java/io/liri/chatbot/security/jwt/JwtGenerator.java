package io.liri.chatbot.security.jwt;


import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

@Component
public class JwtGenerator {

    private final SecretKeyGenerator secretKeyGenerator;
    Logger logger = LoggerFactory.getLogger(JwtGenerator.class);

    public JwtGenerator(SecretKeyGenerator secretKeyGenerator) {
        this.secretKeyGenerator = secretKeyGenerator;
    }

    public String jwtGenerator(String username, Collection<? extends GrantedAuthority> authorities) {
        try {
            byte[] secretKey = secretKeyGenerator.getSecret();
            GrantedAuthority grantedAuthority = authorities.stream().findFirst().get();
            JWTClaimsSet claimsSet = buildClaimSet(username, grantedAuthority.getAuthority());
            MACSigner signer = new MACSigner(secretKey);
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(signer);
            String jwt = signedJWT.serialize();
            logger.info("jwt: {}", jwt);
            return jwt;
        } catch (JOSEException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    private JWTClaimsSet buildClaimSet(String username, String role) {
        return new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("weather chatbot")
                .expirationTime(new Date(System.currentTimeMillis() + 3600 * 1000)) // one hour
                .issueTime(new Date())
                .claim("role", role)
                .build();
    }
}
