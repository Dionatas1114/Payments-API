package com.api.payments.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Getter
@RefreshScope
@Configuration
public class Properties {

    @Value("${PROJ.TITLE}")
    public String PROJ_TITLE;

    @Value("${JWT.VALIDITY}")
    public String JWT_VALIDITY;

    @Value("${JWT.SECRET}")
    public String JWT_SECRET;

    @Value("${secure.paths}")
    public String[] securePaths;

    @Value("${public.paths}")
    public String[] publicPaths;

}
