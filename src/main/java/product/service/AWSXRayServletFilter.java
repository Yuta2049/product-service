package product.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;


/**
 * @author Iuliia Tararueva
 */
@Slf4j
@Component
public class AWSXRayServletFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("AWSXRayServletFilter is invoked");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
