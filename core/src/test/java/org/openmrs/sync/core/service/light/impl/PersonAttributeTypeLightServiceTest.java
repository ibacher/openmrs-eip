package org.openmrs.sync.core.service.light.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openmrs.sync.core.entity.light.PersonAttributeTypeLight;
import org.openmrs.sync.core.repository.OpenMrsRepository;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;

public class PersonAttributeTypeLightServiceTest {

    @Mock
    private OpenMrsRepository<PersonAttributeTypeLight> repository;

    private PersonAttributeTypeLightService service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        service = new PersonAttributeTypeLightService(repository);
    }

    @Test
    public void getShadowEntity() {
        assertEquals(getExpectedLocation(), service.getShadowEntity("uuid"));
    }

    private PersonAttributeTypeLight getExpectedLocation() {
        PersonAttributeTypeLight personAttributeType = new PersonAttributeTypeLight();
        personAttributeType.setUuid("uuid");
        personAttributeType.setCreator(1L);
        personAttributeType.setDateCreated(LocalDateTime.of(1970, Month.JANUARY, 1, 0, 0));
        personAttributeType.setName("[Default]");
        return personAttributeType;
    }
}
