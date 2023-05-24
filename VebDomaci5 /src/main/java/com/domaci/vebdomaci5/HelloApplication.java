package com.domaci.vebdomaci5;

import com.domaci.vebdomaci5.repository.ClanakRepository;
import com.domaci.vebdomaci5.repository.implementation.InMemoryClanakRepository;
import com.domaci.vebdomaci5.service.ClanakService;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig {
    public HelloApplication(){
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(InMemoryClanakRepository.class).to(ClanakRepository.class).in(Singleton.class);

                this.bindAsContract(ClanakService.class);
            }
        };
        register(binder);
        packages("com.domaci.vebdomaci5.resources");
    }
}