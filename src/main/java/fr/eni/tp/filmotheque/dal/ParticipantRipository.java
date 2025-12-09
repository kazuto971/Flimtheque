package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Participant;

import java.util.List;

public interface ParticipantRipository {

    List<Participant> findAllParticipants();
    Participant findParticipantById(long id);
}
