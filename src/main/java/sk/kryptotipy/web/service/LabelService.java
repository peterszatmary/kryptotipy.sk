package sk.kryptotipy.web.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import sk.kryptotipy.web.data.db.Label;
import sk.kryptotipy.web.repository.db.LabelRepository;

import java.util.List;

@Data
@Service
public final class LabelService implements LabelServiceApi {

    private final LabelRepository labelRepository;

    @Override
    public List<Label> getAllLabels() {
        return labelRepository.getAllLabels();
    }
}
