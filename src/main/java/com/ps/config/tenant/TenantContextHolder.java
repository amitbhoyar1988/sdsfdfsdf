package com.ps.config.tenant;

import org.jboss.logging.Logger;

public class TenantContextHolder {

  static Logger logger = Logger.getLogger(TenantContextHolder.class);
  
    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    public static void setTenantId(String tenant) {     
      if(logger.isDebugEnabled()) logger.debug("In TenantContextHolder setting current tenantId-> "+tenant);
        CONTEXT.set(tenant);
    }

    public static String getTenant() {
      if(logger.isDebugEnabled()) logger.debug("In TenantContextHolder getTenant current tenant is-> "+CONTEXT.get());
        return CONTEXT.get();
    }

    public static void clear() {
      if(logger.isDebugEnabled()) logger.debug("In TenantContextHolder clearing tenant info from context");
        CONTEXT.remove();
    }
}