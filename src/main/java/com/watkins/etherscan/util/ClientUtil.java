package com.watkins.etherscan.util;

import com.watkins.etherscan.model.QueryParameters;
import lombok.extern.log4j.Log4j2;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import java.net.URISyntaxException;

@Component
@Log4j2
public class ClientUtil {

    @Value("${etherscan.api.url}")
    private String etherscanApiUrl;

    public String buildUrl(QueryParameters parameters) {
        try {
            URIBuilder uriBuilder = new URIBuilder(etherscanApiUrl);
            if (!StringUtils.isEmpty(parameters.getModule())) {
                uriBuilder.addParameter("module", parameters.getModule());
            }
            if (!StringUtils.isEmpty(parameters.getAction())) {
                uriBuilder.addParameter("action", parameters.getAction());
            }
            if (!StringUtils.isEmpty(parameters.getAddress())) {
                uriBuilder.addParameter("address", parameters.getAddress());
            }
            if (!StringUtils.isEmpty(parameters.getContractAddress())) {
                uriBuilder.addParameter("contractaddress", parameters.getContractAddress());
            }
            if (!StringUtils.isEmpty(parameters.getTxHash())) {
                uriBuilder.addParameter("txhash", parameters.getTxHash());
            }
            if (!StringUtils.isEmpty(parameters.getTimeStamp())) {
                uriBuilder.addParameter("timestamp", parameters.getTimeStamp());
            }
            if (!StringUtils.isEmpty(parameters.getStartDate())) {
                uriBuilder.addParameter("startdate", parameters.getStartDate());
            }
            if (!StringUtils.isEmpty(parameters.getEndDate())) {
                uriBuilder.addParameter("enddate", parameters.getEndDate());
            }
            if (!StringUtils.isEmpty(parameters.getClosest())) {
                uriBuilder.addParameter("closest", parameters.getClosest());
            }
            if (!StringUtils.isEmpty(parameters.getBlockNo())) {
                uriBuilder.addParameter("blockno", parameters.getBlockNo());
            }
            if (!StringUtils.isEmpty(parameters.getTag())) {
                uriBuilder.addParameter("tag", parameters.getTag());
            }
            if (!StringUtils.isEmpty(parameters.getStartBlock())) {
                uriBuilder.addParameter("startblock", parameters.getStartBlock());
            }
            if (!StringUtils.isEmpty(parameters.getEndBlock())) {
                uriBuilder.addParameter("endblock", parameters.getEndBlock());
            }
            if (!StringUtils.isEmpty(parameters.getTo())) {
                uriBuilder.addParameter("to", parameters.getTo());
            }
            if (!StringUtils.isEmpty(parameters.getValue())) {
                uriBuilder.addParameter("value", parameters.getValue());
            }
            if (!StringUtils.isEmpty(parameters.getGas())) {
                uriBuilder.addParameter("gas", parameters.getGas());
            }
            if (!StringUtils.isEmpty(parameters.getGasPrice())) {
                uriBuilder.addParameter("gasprice", parameters.getGasPrice());
            }
            if (!StringUtils.isEmpty(parameters.getApiKey())) {
                uriBuilder.addParameter("apikey", parameters.getApiKey());
            }
            return uriBuilder.build().toString();
        } catch (URISyntaxException e) {
            log.error("Error building URL, Exception: {}", e.getMessage());
            return null;
        }
    }
}
