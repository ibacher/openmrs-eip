package org.openmrs.sync.component.service.light.impl;

import org.openmrs.sync.component.entity.light.ConceptLight;
import org.openmrs.sync.component.entity.light.OrderFrequencyLight;
import org.openmrs.sync.component.repository.OpenmrsRepository;
import org.openmrs.sync.component.service.light.AbstractLightService;
import org.openmrs.sync.component.service.light.LightService;
import org.springframework.stereotype.Service;

@Service
public class OrderFrequencyLightService extends AbstractLightService<OrderFrequencyLight> {

    private LightService<ConceptLight> conceptService;

    public OrderFrequencyLightService(final OpenmrsRepository<OrderFrequencyLight> repository,
                                      final LightService<ConceptLight> conceptService) {
        super(repository);
        this.conceptService = conceptService;
    }

    @Override
    protected OrderFrequencyLight createPlaceholderEntity(final String uuid) {
        OrderFrequencyLight orderFrequency = new OrderFrequencyLight();
        orderFrequency.setCreator(DEFAULT_USER_ID);
        orderFrequency.setDateCreated(DEFAULT_DATE);
        orderFrequency.setConcept(conceptService.getOrInitPlaceholderEntity());

        return orderFrequency;
    }
}