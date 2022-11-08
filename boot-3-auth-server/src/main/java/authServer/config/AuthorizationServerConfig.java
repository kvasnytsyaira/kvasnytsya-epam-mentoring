//package authServer.config;
//import java.util.UUID;
//import java.util.function.Supplier;
//
//import authServer.jwt.JWKS;
//import java.util.UUID;
//import java.util.function.Supplier;
//
//import com.nimbusds.jose.jwk.JWKSet;
//import com.nimbusds.jose.jwk.RSAKey;
//import com.nimbusds.jose.jwk.source.JWKSource;
//import com.nimbusds.jose.proc.SecurityContext;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
//import org.springframework.security.oauth2.core.oidc.OidcScopes;
//import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
//import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
//
//@Configuration(proxyBeanMethods = false)
//@Import(OAuth2AuthorizationServerConfiguration.class)
//public class AuthorizationServerConfig {
//    @Bean
//    RegisteredClientRepository registeredClientRepository() {
//        RegisteredClient loginClient = RegisteredClient.withId(UUID.randomUUID().toString())
//                .clientId("login-client")
//                .clientSecret("secret")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .redirectUri("http://localhost:8080/login/oauth2/code/login-client")
//                .scope(OidcScopes.OPENID)
//                .build();
//
//        RegisteredClient clientA = RegisteredClient.withId(UUID.randomUUID().toString())
//                .clientId("client-a")
//                .clientSecret("secret")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                .redirectUri("http://localhost:8080/flow-a")
//                .scope("authority-a")
//                .clientSettings(clientSettings -> clientSettings.requireUserConsent(true))
//                .build();
//
//        RegisteredClient clientB = RegisteredClient.withId(UUID.randomUUID().toString())
//                .clientId("client-ab")
//                .clientSecret("secret")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                .redirectUri("http://localhost:8080/flow-ab")
//                .scope("authority-a")
//                .scope("authority-b")
//                .clientSettings(clientSettings -> clientSettings.requireUserConsent(true))
//                .build();
//
//
//        RegisteredClient clientC = RegisteredClient.withId(UUID.randomUUID().toString())
//                .clientId("client-c")
//                .clientSecret("secret")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
//                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//                .scope("authority-c")
//                .build();
//
//        return new InMemoryRegisteredClientRepository(
//                loginClient, clientA, clientB, clientC);
//    }
//    // @formatter:on
//
//    @Bean
//    Supplier<JWKSet> jwkSetProvider() {
//        RSAKey rsaKey = JWKS.generateRsa();
//        JWKSet jwkSet = new JWKSet(rsaKey);
//        return () -> jwkSet;
//    }
//
//    @Bean
//    JWKSource<SecurityContext> jwkSource(Supplier<JWKSet> jwkSetProvider) {
//        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSetProvider.get());
//    }
//
//    @Bean
//    ProviderSettings providerSettings() {
//        return new ProviderSettings().issuer("http://auth-server:9000");
//    }
//}
