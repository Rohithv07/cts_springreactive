package com.examples.spring.security.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
   private String clientid = "empmgmtapp";
   private String clientSecret = "password";
   
   private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
   		"MIIEowIBAAKCAQEAz7JBuIx28ud1192Alnt4HMrQ18f1gfwSzx5KWKLT6yzEFC6q\r\n" + 
   		"GefowNJZjXy5BJQR1QYaFn9rlaFPP/6CkhXVcjG6v4R5eSEOPi0i/KNqFflAjVji\r\n" + 
   		"Izj48B2rNGw5MDiWuhfTDX/hVzcwaFrFSC4jsvnsnJNKvIT/C7YC6NlhKxoyzusI\r\n" + 
   		"xLQg9DgWpFcvOSmMJyhQnae2O1CR+Fcall6XKHHmVpk3UrIRdymV5eq/B8lEhI0W\r\n" + 
   		"WyItD/NJiSurjx9ZSAGKGdZlgN0IJ5Oyho40GVk9ngpvHbBRvqB+RDWEbNKj12oQ\r\n" + 
   		"bL/vhyf2Ve3QXFjgJnUGu+WAIbmF6DlP7DbGOwIDAQABAoIBAQDD/H+Lt/Bekg9f\r\n" + 
   		"XUMDvQrAkJm1pJiEyeqtyvV44l1648k7CjW1MGMv4M5kdrbYHSkejR188UwB7C+u\r\n" + 
   		"QWnQlA3QLiD8q/M8W/bmsQz8vO9UQNmrB56MgixV2Ik/dIgaequwet7LNErdU9S4\r\n" + 
   		"BD95NyC0uKWPsmWpsw5AbMYeF1Jbqi5Zs+//h8iSUtCHwCV5KP6N1HiK97TX+CfR\r\n" + 
   		"tbir8yoAZFuRWgoWcVpe6YaMjBtx5m9LMpmmnF3DSu5aS7T/V2I3m3Cje+Wwwe4p\r\n" + 
   		"PAebMwpN624JtZmB6QkLKkdge+vynU5an63NIyLe63sW2FeOQnRgmSjqVxkiG5Iu\r\n" + 
   		"i83MKE5hAoGBAPsUYwI+c7avIwUSHMLQ156B6NCE4jKmI1kRZxHwAm/+wax1q3bk\r\n" + 
   		"+uTfxCAfxnyVVLsWuwWLgrm1l6tgfOuHaJ+2f96TiBXZLXxacCIJanXE29uUwXL0\r\n" + 
   		"mvcJ5mlc1ew33E2LHrCVquHmpBE9u30bZ3BhA2ceLrkzIsF8AmfDYTuZAoGBANPE\r\n" + 
   		"OZ1hmcWOMriXuyau6h33sixr7Snnd6cfhxGor+WcOqdjaPKsPfje0BTTHuLsDUHy\r\n" + 
   		"vjNQRc+AX3gWFD9PRN0GINPfmkz8hh0Fc/fZXegaS/ChwXM3hSCaGpkPmLPS7oi8\r\n" + 
   		"J3DojkB6Nt+x9aVDu6+GVW07LOGrL3NNkKjsZVTzAoGAcq8yNkEue6Btv5AxYO8h\r\n" + 
   		"vf4ayLwZ0ga/GJzxVmkpw9w6vLqW0lzvGQH4xK+e6yMOq9JLJNI/qPIAUEBnbvHP\r\n" + 
   		"4TvsFk+PFmO3+hgZJgLlBNwhjJ/a6NY/vl1gSHomIJq3XxgPsZr7jenawSFQdWnC\r\n" + 
   		"CrvVYRGRR/jKhFRPiPPegukCgYApxguXgClVTzeyMjw45UhJoRMEsEWYfpCb5xw9\r\n" + 
   		"34jTQn5PhsytEk0nSszBTuRI30e+OqRKewtKY4hxp+lWk8yBzTOsl+9YQHg0QV8f\r\n" + 
   		"BUIBBy7zyPTgRZN9WujkSK9WycVEM9mT7Ewja/hnKDPM5g+gEF/JrGKuIZa6qBJh\r\n" + 
   		"QEC9AQKBgAamMzHh1XZ/37BxalRHM9JHd29VbRbpatPsrM9OkEb5qTqSmbhdNXwN\r\n" + 
   		"QjpKPSAURdTToIglldJ+QUQu+WKV74uGGboufRayhIv94Az3WL01TJlTwJcpHEaH\r\n" + 
   		"p2e0SVw+yEfM5a/CaefmD/eQKdNVKih3TTY+fOXLWsfX+Ck03AcK\r\n" + 
   		"-----END RSA PRIVATE KEY-----";
   
   private String publicKey = "-----BEGIN PUBLIC KEY-----\r\n" + 
   		"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAz7JBuIx28ud1192Alnt4\r\n" + 
   		"HMrQ18f1gfwSzx5KWKLT6yzEFC6qGefowNJZjXy5BJQR1QYaFn9rlaFPP/6CkhXV\r\n" + 
   		"cjG6v4R5eSEOPi0i/KNqFflAjVjiIzj48B2rNGw5MDiWuhfTDX/hVzcwaFrFSC4j\r\n" + 
   		"svnsnJNKvIT/C7YC6NlhKxoyzusIxLQg9DgWpFcvOSmMJyhQnae2O1CR+Fcall6X\r\n" + 
   		"KHHmVpk3UrIRdymV5eq/B8lEhI0WWyItD/NJiSurjx9ZSAGKGdZlgN0IJ5Oyho40\r\n" + 
   		"GVk9ngpvHbBRvqB+RDWEbNKj12oQbL/vhyf2Ve3QXFjgJnUGu+WAIbmF6DlP7DbG\r\n" + 
   		"OwIDAQAB\r\n" + 
   		"-----END PUBLIC KEY-----";

   @Autowired
   @Qualifier("authenticationManagerBean")
   private AuthenticationManager authenticationManager;
   
   @Bean
   public JwtAccessTokenConverter tokenEnhancer() {
      JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
      converter.setSigningKey(privateKey);
      converter.setVerifierKey(publicKey);
      return converter;
   }
   @Bean
   public JwtTokenStore tokenStore() {
      return new JwtTokenStore(tokenEnhancer());
   }
   @Override
   public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
      endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
      .accessTokenConverter(tokenEnhancer());
   }
   @Override
   public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
      security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
   }
   @Override
   public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
      clients.inMemory().withClient(clientid).secret(clientSecret).scopes("read", "write")
         .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
         .refreshTokenValiditySeconds(20000);

   }
}