package com.app.APFinal.infrastructure.spring.config.security;

public enum ERole {

  ADMIN, USER;

  private static final String ROLE_PREFIX = "ROLE_";

  public String getFullRoleName() {
    return ROLE_PREFIX + this.name();
  }

}
