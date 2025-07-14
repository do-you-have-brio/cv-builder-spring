package dyhb.api.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import dyhb.api.database.models.EducationModel;
import dyhb.api.database.repository.jpa.EducationJpaRepository;
import dyhb.api.service.EducationService;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class EducationServiceTest {

    @Mock
    private EducationJpaRepository jpaRepository;

    @InjectMocks
    private EducationService service;

    private UUID uuid;
    private EducationModel model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        uuid = UUID.randomUUID();
        model = new EducationModel();
        model.setId(uuid);
        model.setName("Bachelor");
    }

    @Test
    void findById_shouldReturnModel() {
        when(jpaRepository.findById(uuid)).thenReturn(Optional.of(model));

        Optional<EducationModel> result = service.findById(uuid);

        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("Bachelor");
        verify(jpaRepository).findById(uuid);
    }

    @Test
    void save_shouldPersistModel() {
        when(jpaRepository.save(model)).thenReturn(model);

        EducationModel saved = service.save(model);

        assertThat(saved).isEqualTo(model);
        verify(jpaRepository).save(model);
    }

    @Test
    void delete_shouldReturnTrueWhenDeleted() {
        when(jpaRepository.deleteByUuid(uuid)).thenReturn(1);

        boolean result = service.delete(uuid);

        assertThat(result).isTrue();
        verify(jpaRepository).deleteByUuid(uuid);
    }

    @Test
    void findByUserId_shouldReturnList() {
        UUID userId = UUID.randomUUID();
        List<EducationModel> mockList = List.of(model);

        when(jpaRepository.findByUserId(userId)).thenReturn(mockList);

        List<EducationModel> result = service.findByUserId(userId);

        assertThat(result).hasSize(1).contains(model);
        verify(jpaRepository).findByUserId(userId);
    }
}
