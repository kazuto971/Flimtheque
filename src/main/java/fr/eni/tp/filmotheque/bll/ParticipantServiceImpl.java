package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.dal.ParticipantRipository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRipository participantRepository;

    public ParticipantServiceImpl(ParticipantRipository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public List<Participant> findAllParticipants() {
        return participantRepository.findAllParticipants();
    }

    @Override
    public Participant findParticipantById(long id) {
        return participantRepository.findParticipantById(id);
    }
}
