import config.DatasoureConfiguration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main (String[] args) throws SQLException {
        String sql = "select * from user_profile_profile";
        DatasoureConfiguration dt1 = new DatasoureConfiguration();
        ResultSet rs = dt1.executeQuery(sql);
        List<String> results = new ArrayList<>();
        while (rs.next()) {
            results.add(rs.getString("display_name"));
        }
        System.out.println(results);
    }
}
