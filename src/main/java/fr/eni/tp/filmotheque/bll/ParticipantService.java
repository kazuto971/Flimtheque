package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Participant;

import java.util.List;

public interface ParticipantService {

    List<Participant> findAllParticipants();

    Participant findParticipantById(long id);
}
