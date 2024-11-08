package io.liri.chatbot.security.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;

@Component
public class JwtVerifier {

    private final SecretKeyGenerator secretKeyGenerator;

    public JwtVerifier(SecretKeyGenerator secretKeyGenerator) {
        this.secretKeyGenerator = secretKeyGenerator;
    }

    public JWTClaimsSet jwtVerifier(String token) throws JOSEException, IOException {
        byte[] secret = secretKeyGenerator.getSecret();
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(secret);
            if (signedJWT.verify(verifier)) {
                return signedJWT.getJWTClaimsSet();
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (JOSEException e) {
            throw new BadCredentialsException(e.getMessage());
        }
        return null;
    }
}