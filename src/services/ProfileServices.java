package services;

import repository.model.Profile;

import java.util.List;

public interface ProfileServices {
    List<Profile> getAllProfileNames();
    List<Profile> getProfileByDisplayName(String displayName);
    void showModifiedProfile(Profile editedProfile);
    boolean isUpdatedProfileName(Profile modifiedProfile);
    String checkDifferentValue (String oldValue, String newValue);
}
