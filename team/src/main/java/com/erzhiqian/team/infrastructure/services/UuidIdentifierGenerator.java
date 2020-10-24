package com.erzhiqian.team.infrastructure.services;

import com.erzhiqian.team.domain.services.UniqueIdentifierGenerator;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UuidIdentifierGenerator implements UniqueIdentifierGenerator {
    @Override
    public String generateUniqueIdentifier() {
        return UUID.randomUUID().toString();
    }
}
