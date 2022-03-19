package com.djmgit.opsgeniespringboot.autoconfigure;

import com.djmgit.opsgeniespringboot.opsgenieinterceptor.Intercept;
import com.djmgit.opsgeniespringboot.opsgenieinterceptor.Opsgenie;
import com.djmgit.opsgeniespringboot.opsgenieinterceptor.OpsgenieConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.djmgit.opsgeniespringboot.opsgenieinterceptor.RequestInterceptor;
import static com.djmgit.opsgeniespringboot.opsgenieinterceptor.OpsgenieConfigParams.*;


@Configuration
@ConditionalOnClass(Opsgenie.class)
@EnableConfigurationProperties(OpsgenieProperties.class)
public class OpsgenieAutoconfiguration {

    @Autowired
    private OpsgenieProperties opsgenieProperties;
    
    @Bean
    @ConditionalOnMissingBean
    public Intercept interceptor() {
        return new Intercept();
    }

    @Bean
    @ConditionalOnMissingBean
    public OpsgenieConfig opsgenieConfig() {
        String opsgenieApiKey = this.opsgenieProperties.getOpsgenieApiKey() == null ? System.getenv(OPSGENIE_API_KEY) : this.opsgenieProperties.getOpsgenieApiKey();
        String opsgenieApiBase = this.opsgenieProperties.getOpsgenieApiBase() == null ? "https://api.opsgenie.com" : this.opsgenieProperties.getOpsgenieApiBase();

        OpsgenieConfig opsgenieConfig = new OpsgenieConfig();
        opsgenieConfig.put(OPSGENIE_API_KEY, opsgenieApiKey);
        opsgenieConfig.put(OPSGENIE_API_BASE, opsgenieApiBase);

        return opsgenieConfig;
    }

    @Bean
    @ConditionalOnMissingBean
    public Opsgenie opsgenie(OpsgenieConfig opsgenieConfig) {

        return new Opsgenie(opsgenieConfig);
    }
}
