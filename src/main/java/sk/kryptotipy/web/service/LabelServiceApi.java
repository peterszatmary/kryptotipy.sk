package sk.kryptotipy.web.service;

import sk.kryptotipy.web.data.db.Label;

import java.util.List;

public sealed interface LabelServiceApi permits LabelService {
    List<Label> getAllLabels();
}