package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Participant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ParticipantRepositoryImplTest {

    @Autowired
    private ParticipantRepositoryImpl participantRepository;

    @Test
    void testFindAllParticipants() {
        List<Participant> participants = participantRepository.findAllParticipants();

        assertNotNull(participants);
        assertFalse(participants.isEmpty());
    }

    @Test
    void testFindParticipantById_existingId() {
        Participant participant = participantRepository.findParticipantById(1);

        assertNotNull(participant);
        assertEquals(1, participant.getId());
    }

}
