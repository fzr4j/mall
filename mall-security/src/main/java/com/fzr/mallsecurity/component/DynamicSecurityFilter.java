package com.fzr.mallsecurity.component;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

/**
 * 动态权限过滤器，用于实现基于路径的动态权限过滤
 */
public class DynamicSecurityFilter extends AbstractSecurityInterceptor implements Filter {

    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return null;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return null;
    }
}
