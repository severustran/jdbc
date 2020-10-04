package services.imple;

import config.DatasoureConfiguration;
import repository.model.Profile;
import services.ProfileServices;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfileServicesImpl implements ProfileServices {
    private final Connection conn;

    public ProfileServicesImpl() {
        conn = DatasoureConfiguration.getDatasoure().getConnection();
    }

    @Override
    public List<Profile> getAllProfileNames() {
        List<Profile> profileList = new ArrayList<>();
        String sql = "select * from user_profile_profile";
        try (Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Profile pr = new Profile();
                pr.setDisplay_name(rs.getString(2));
                profileList.add(pr);
            }
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return profileList;
    }

    @Override
    public List<Profile> getProfileByDisplayName(final String displayName) {
        List<Profile> profileList = new ArrayList<>();
        String sql = "select * from user_profile_profile where upper(display_name) like upper(?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + displayName + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Profile pr = new Profile();
                pr.setId(rs.getInt(1));
                pr.setDisplay_name(rs.getString(2));
                pr.setBirthday(rs.getString(3));
                pr.setGender(rs.getString(4));
                pr.setPhone(rs.getString(5));
                pr.setLecture(rs.getBoolean("is_lecturer"));
                profileList.add(pr);
            }
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return profileList;
    }

    @Override
    public boolean isUpdatedProfileName(final Profile modifiedProfile) {
            String sql = "update user_profile_profile set display_name = ? where id = ?";
            int result = 0;
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, modifiedProfile.getDisplay_name());
                preparedStatement.setInt(2, modifiedProfile.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException sqlE) {
                sqlE.printStackTrace();
            }
            return true;
    }

    @Override
    public void showModifiedProfile(final Profile preEditedProfile) {
        System.out.println("Name: " + preEditedProfile.getDisplay_name());
        System.out.println("DOB: " + preEditedProfile.getBirthday());
        System.out.println("Gender: " + preEditedProfile.getGender() == "F" ? "Nam" : "Nu");
        System.out.println("Is: " + (preEditedProfile.isLecture() ? "GV" : "HV"));
        System.out.println("Phone: " + preEditedProfile.getPhone());
    }

    @Override
    public String checkDifferentValue (String oldValue, String newValue) {
        return newValue.equals("") ? oldValue : newValue;
    }
}
