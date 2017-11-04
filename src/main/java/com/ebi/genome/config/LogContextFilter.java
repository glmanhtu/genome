package com.ebi.genome.config;

import com.ebi.genome.utils.Constants;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LogContextFilter implements Filter {

    public void init(FilterConfig config) {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String transactionId = UUID.randomUUID().toString();

        MDC.put(Constants.TRANS_ID, transactionId);

        try {
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}
