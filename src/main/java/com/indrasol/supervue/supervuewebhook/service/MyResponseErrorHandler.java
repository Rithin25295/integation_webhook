package com.indrasol.supervue.supervuewebhook.service;

import com.indrasol.supervue.supervuewebhook.util.RestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
@Slf4j
public class MyResponseErrorHandler implements ResponseErrorHandler {
    

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        log.error("Response error: {} {}", response.getStatusCode(), response.getStatusText());
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return RestUtils.isError(response.getStatusCode());
    }
}