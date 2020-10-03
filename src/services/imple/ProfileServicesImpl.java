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
                pr.setId(rs.getString(1));
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
    public boolean isUpdatedProfileName() {

        return isUpdatedProfileName();
    }
}
