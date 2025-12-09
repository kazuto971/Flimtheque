package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.dal.ParticipantRipository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ParticipantServiceImplTest {

    @Mock
    private ParticipantRipository participantRepository;

    @InjectMocks
    private ParticipantServiceImpl participantService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllParticipants() {
        // GIVEN
        List<Participant> fakeParticipants = Arrays.asList(
                new Participant(1, "Steven", "Spielberg"),
                new Participant(2, "Jeff", "Goldblum")
        );

        when(participantRepository.findAllParticipants()).thenReturn(fakeParticipants);

        // WHEN
        List<Participant> result = participantService.findAllParticipants();

        // THEN
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(participantRepository, times(1)).findAllParticipants();
    }

    @Test
    void testFindParticipantById() {
        // GIVEN
        Participant spielberg = new Participant(1, "Steven", "Spielberg");
        when(participantRepository.findParticipantById(1)).thenReturn(spielberg);

        // WHEN
        Participant result = participantService.findParticipantById(1);

        // THEN
        assertNotNull(result);
        assertEquals("Steven", result.getPrenom());
        verify(participantRepository, times(1)).findParticipantById(1);
    }
}
