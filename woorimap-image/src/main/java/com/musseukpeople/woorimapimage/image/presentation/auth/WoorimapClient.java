package com.musseukpeople.woorimapimage.image.presentation.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.musseukpeople.woorimapimage.common.model.ApiResponse;
import com.musseukpeople.woorimapimage.image.presentation.auth.login.MemberResponse;

@Component
public class WoorimapClient {

    private final String woorimapUrl;

    public WoorimapClient(
        @Value("${woorimap.api.host}") String woorimapHost,
        @Value("${woorimap.api.port}") String woorimapPort
    ) {
        this.woorimapUrl = woorimapHost + woorimapPort;
    }

    public ApiResponse<MemberResponse> getMemberResponse(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, accessToken);
        HttpEntity<Void> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<ApiResponse<MemberResponse>> response = new RestTemplate().exchange(
            woorimapUrl + "/api/members", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<>() {
            });
        return response.getBody();
    }
}
