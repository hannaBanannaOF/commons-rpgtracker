package com.hbsites.commons.rpgtracker.infrastructure.config;

import com.hbsites.commons.config.BaseUser;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class RPGTrackerUser extends BaseUser {
    private List<UUID> userCharacterSheets;
    private List<DMedSessions> userDMedSessions;

    @Getter
    @Setter
    public static class DMedSessions {
        private List<UUID> sessionSheets;
    }
}
