package sk.kryptotipy.web.api;

import sk.kryptotipy.web.data.db.Label;

import java.util.List;

public interface LabelServiceApi {
    List<Label> getAllLabels();
}