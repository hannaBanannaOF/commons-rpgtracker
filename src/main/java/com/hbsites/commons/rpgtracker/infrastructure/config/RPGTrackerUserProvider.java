package com.hbsites.commons.rpgtracker.infrastructure.config;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Provider;

@RequestScoped
public class RPGTrackerUserProvider implements Provider<RPGTrackerUser> {

    RPGTrackerUser current;

    public void set(RPGTrackerUser user) {
        this.current = user;
    }

    @Override
    public RPGTrackerUser get() {
        return this.current;
    }
}
