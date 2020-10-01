package services;

import repository.model.Profile;

import java.util.List;

public interface ProfileServices {
    List<Profile> getAllProfileNames();
    List<Profile> getProfileNamesByInput(String input);
    void updateProfileName();
}