package com.example.Dukkan;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

@Configuration
public class PaypalConfig {
	
	@Value("${paypal.client.Id}")
	private String clientId;
	@Value("${paypal.client.secret}")
	private String clientSecret;
	@Value("${paypal.mode}")
	private String clientMode;
	
	@Bean
	public Map<String,String> paypalSdkconfig(){
		Map<String,String> map=new HashMap<String, String>();
		map.put("mode", clientMode);
		return map;
		
	}
	
	@Bean
	public OAuthTokenCredential oAuthTokenCredential() {
		return new OAuthTokenCredential(clientId, clientSecret,paypalSdkconfig());
	}
	
	@Bean
	public APIContext apiContext() throws PayPalRESTException {
		@SuppressWarnings("deprecation")
		APIContext context=new APIContext(oAuthTokenCredential().getAccessToken());
		context.setConfigurationMap(paypalSdkconfig());
		return context;
	}

}
