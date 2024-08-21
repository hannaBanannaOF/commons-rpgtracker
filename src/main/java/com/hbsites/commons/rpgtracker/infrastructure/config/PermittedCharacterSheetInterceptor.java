package com.hbsites.commons.rpgtracker.infrastructure.config;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.ws.rs.ForbiddenException;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Objects;
import java.util.UUID;

@Interceptor
@PermittedCharacterSheet
public class PermittedCharacterSheetInterceptor {

    @Inject
    RPGTrackerUserProvider provider;

    @AroundInvoke
    public Object permittedCharacterSheet(InvocationContext context) throws Exception {
        Method method = context.getMethod();
        Parameter[] methodParameter = method.getParameters();
        Object[] parameters = context.getParameters();
        for (int i = 0; i < methodParameter.length; i++) {
            if (methodParameter[i].getAnnotation(PermittedCharacterSheet.class) != null &&
                    parameters[i] instanceof UUID characterSheet && !userCanSeeCharacterSheet(characterSheet)
            ) {
                throw new ForbiddenException("Você não tem permissão para ver a ficha de personagem!");
            }
        }
        return context.proceed();
    }

    private boolean userCanSeeCharacterSheet(UUID selectedCharacterSheetId) {
        return provider.get().getUserCharacterSheets().stream()
                .anyMatch(characterSheetId -> Objects.equals(characterSheetId, selectedCharacterSheetId)) ||
                provider.get().getUserDMedSessions().stream().anyMatch(sessions -> sessions.getSessionSheets().stream().anyMatch(characterSheet -> Objects.equals(characterSheet, selectedCharacterSheetId)));
    }
}
