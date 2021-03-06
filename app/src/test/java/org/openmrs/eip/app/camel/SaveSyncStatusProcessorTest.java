package org.openmrs.eip.app.camel;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openmrs.eip.app.management.entity.TableSyncStatus;
import org.openmrs.eip.app.management.repository.TableSyncStatusRepository;
import org.openmrs.eip.component.service.TableToSyncEnum;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SaveSyncStatusProcessorTest {

    @Mock
    private TableSyncStatusRepository repository;

    @Captor
    private ArgumentCaptor<TableSyncStatus> captor;

    private SaveSyncStatusProcessor processor;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        processor = new SaveSyncStatusProcessor(repository);
    }

    @Test
    public void process() {
        // Given
        Exchange exchange = new DefaultExchange(new DefaultCamelContext());
        exchange.getIn().setHeader("OpenmrsEntitySyncStatusId", 1L);
        TableSyncStatus status = new TableSyncStatus();
        status.setId(1L);
        status.setTableToSync(TableToSyncEnum.PERSON);
        when(repository.findById(1L)).thenReturn(Optional.of(status));

        // When
        processor.process(exchange);

        // Then
        verify(repository).save(captor.capture());
        assertEquals(1L, captor.getValue().getId().longValue());
        assertEquals(TableToSyncEnum.PERSON, captor.getValue().getTableToSync());
        assertEquals(LocalDate.now(), captor.getValue().getLastSyncDate().toLocalDate());
    }
}
