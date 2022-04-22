package com.watkins.etherscan.client;

import com.watkins.etherscan.model.Balance;
import com.watkins.etherscan.model.QueryParameters;
import com.watkins.etherscan.service.HttpService;
import com.watkins.etherscan.util.ClientUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.util.StringUtils;

import java.net.URISyntaxException;

@Component
@RequiredArgsConstructor
@Log4j2
public class EtherscanAccountClient {

    private final RestTemplate restTemplate;
    private final HttpService httpService;
    private final ClientUtil clientUtil;

    public Balance getAccountBalance(QueryParameters parameters) {
        Balance balance = null;
        String builtUrl = clientUtil.buildUrl(parameters);
        String address = parameters.getAddress();
        log.info("Getting balance for address: {}", address);
        HttpEntity<String> httpEntity = new HttpEntity<>(null, httpService.getHttpHeaders());
        try {
            if (StringUtils.isEmpty(builtUrl)) {
                return null;
            }
            ResponseEntity<Balance> responseEntity = restTemplate.exchange(builtUrl, HttpMethod.GET, httpEntity, Balance.class);
            if (HttpStatus.OK == responseEntity.getStatusCode()) {
                balance = responseEntity.getBody();
                log.info("Balance retrieved for address: {}", address);
            } else {
                log.error("Http status {} received from endpoint {} for address {}",
                        responseEntity.getStatusCode(), builtUrl, address);
            }
        } catch (Exception e) {
            log.error("Error retrieving balance from url {} and address {}. Exception: {}", builtUrl, address, e);
        }
        return balance;
    }

}
