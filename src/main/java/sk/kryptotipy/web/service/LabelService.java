package sk.kryptotipy.web.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import sk.kryptotipy.web.api.LabelServiceApi;
import sk.kryptotipy.web.data.Label;
import sk.kryptotipy.web.repository.LabelRepository;

import java.util.List;

@Data
@Service
public class LabelService implements LabelServiceApi {

    private final LabelRepository labelRepository;

    @Override
    public List<Label> getAllLabels() {
        return labelRepository.getAllLabels();
    }
}
