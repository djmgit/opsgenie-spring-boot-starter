package com.djmgit.opsgeniespringboot.autoconfigure;

import com.djmgit.opsgeniespringboot.opsgenieinterceptor.Opsgenie;
import com.djmgit.opsgeniespringboot.opsgenieinterceptor.OpsgenieConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Opsgenie.class)
@EnableConfigurationProperties(OpsgenieProperties.class)
public class OpsgenieAutoconfiguration {

    @Autowired
    private OpsgenieProperties opsgenieProperties;

    @Bean
    @ConditionalOnMissingBean
    public OpsgenieConfig opsgenieConfig() {
        OpsgenieConfig opsgenieConfig = new Util(this.opsgenieProperties).getOpsgeneiConfig();
        return opsgenieConfig;
    }

    @Bean
    @ConditionalOnMissingBean
    public Opsgenie opsgenie(OpsgenieConfig opsgenieConfig) {

        return new Opsgenie(opsgenieConfig);
    }
}
