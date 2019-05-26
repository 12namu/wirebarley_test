package com.example.wirebarley.service;

import com.example.wirebarley.dto.WireInfo;
import com.example.wirebarley.dto.WireInfoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class WirebarleyService {

    private static final String ACCESS_KEY = "808afadff733f5dc7b5facc0416671ae";
    private static final String BASE_URL = "http://apilayer.net/api/live?access_key=";

    private static Logger logger = LoggerFactory.getLogger(WirebarleyService.class);

    /**
     * 환율정보 조회
     */
    public static WireInfoDto getWireInfoToApi() {
        WireInfoDto wireInfoDto = new WireInfoDto();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<WireInfo> response = restTemplate.getForEntity(BASE_URL + ACCESS_KEY, WireInfo.class);
        WireInfo result = response.getBody();
        if (response.getStatusCodeValue() == 200 && result.getSuccess()) {
            Map<String, Double> quotes = result.getQuotes();
            wireInfoDto.setUSDKRW(quotes.get("USDKRW"));
            wireInfoDto.setUSDJPY(quotes.get("USDJPY"));
            wireInfoDto.setUSDPHP(quotes.get("USDPHP"));
            return wireInfoDto;
        }

        logger.info("----------->getWireInfoToApi error: {}", response);
        return null;
    }
}

