import config.DatasoureConfiguration;
import repository.model.Profile;
import services.ProfileServices;
import services.imple.ProfileServicesImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.spi.CalendarDataProvider;

public class Main {
    public static void main (String[] args) {

//        ProfileServices profileServices = new ProfileServicesImpl();
//        profileServices.test();
//        String sql = "select * from user_profile_profile";
//        try (ResultSet rs = DatasoureConfiguration.getDatasoure().executeQuery(sql)) {
//            while (rs.next()) {
//                if (rs.getDate(3) instanceof Date)
//                    System.out.println(rs.getDate(3));
//            }
//        } catch (SQLException sqlException) {
//            sqlException.printStackTrace();
//        }
        //Work
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate lc = LocalDate.parse("1994-10-23");
        //Not work
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        LocalDate lc = LocalDate.parse("23-10-1994", dateTimeFormatter);
//        System.out.println(lc);
//        System.out.println(dateTimeFormatter.format(lc));

//        try {
//            Date date = new SimpleDateFormat("YYYY-MM-dd").parse(dateTimeFormatter.format(lc));
//            System.out.println(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


        ProfileServices profileServices = new ProfileServicesImpl();
        List<Profile> profileList = profileServices.getAllProfileNames();
        Scanner sc = new Scanner(System.in);

        for (int i = 1; i<=profileList.size(); i++) {
            System.out.println(i + ". " + profileList.get(i-1).getDisplay_name());
        }
        profileList.clear();

        System.out.println("Enter keyword to search...");
        String input = sc.nextLine();
        List<Profile> profileListWithInput = profileServices.getProfileByDisplayName(input);
        List<Profile> listOfProfileSearchResult = new ArrayList<>();
        if (profileListWithInput.isEmpty()) {
            System.out.println("There is no results with your keyword!");
        } else {
            for (int i = 0; i < profileListWithInput.size(); i++) {
                listOfProfileSearchResult.add(profileListWithInput.get(i));
                System.out.print((i + 1) + " " + profileListWithInput.get(i).getDisplay_name() +
                        "\t" + profileListWithInput.get(i).getBirthday() +
                        "\t" + (profileListWithInput.get(i).getGender().equals("M") ? "Nam" : "Nu") +
                        "\t" + (profileListWithInput.get(i).isLecture() ? "GV" : "HV") +
                        "\t" + profileListWithInput.get(i).getPhone());
                System.out.println();
            }
        }

        int usersOption;
        while (true) {
            System.out.print("Choose record to modify, enter number: ");
            usersOption = Integer.parseInt(sc.nextLine()) - 1;
            if (usersOption >= 0 && usersOption <= listOfProfileSearchResult.size()) {
                break;
            } else {
                System.out.println("Your number is not valid, please choose again...");
                System.out.print("Enter number: ");
            }
        }

        Profile newProfile = new Profile();
        newProfile.setId(listOfProfileSearchResult.get(usersOption).getId());
        //Name
        System.out.println("Name: " + listOfProfileSearchResult.get(usersOption).getDisplay_name());
        System.out.print("New name: ");
        String newValue = sc.nextLine();
        newProfile.setDisplay_name(profileServices.checkDifferentValue(listOfProfileSearchResult.get(usersOption).getDisplay_name(), newValue));
        //DOB
        System.out.println("DOB: " + listOfProfileSearchResult.get(usersOption).getBirthday());
        System.out.print("New DOB (yyyy-mm-dd): ");
        newValue = sc.nextLine();
        newProfile.setBirthday(profileServices.checkDifferentValue(listOfProfileSearchResult.get(usersOption).getBirthday(), newValue));
        //Gender
        System.out.println("Gender: " + listOfProfileSearchResult.get(usersOption).getGender());
        System.out.print("New Gender (type F for Nu, M for Nam): ");
        newValue = sc.nextLine();
        newProfile.setGender(profileServices.checkDifferentValue(listOfProfileSearchResult.get(usersOption).getGender(), newValue));
        //Phone
        System.out.println("Phone: " + listOfProfileSearchResult.get(usersOption).getPhone());
        System.out.print("New Phone: ");
        newValue = sc.nextLine();
        newProfile.setPhone(profileServices.checkDifferentValue(listOfProfileSearchResult.get(usersOption).getPhone(), newValue));
        //isLecturer
        System.out.println("Is: " + listOfProfileSearchResult.get(usersOption).getPhone());
        System.out.print("Is Lecturer (True for GV, False for HV): ");
        newValue = sc.nextLine();
        newProfile.setLecture(Boolean.parseBoolean(profileServices.checkDifferentValue(listOfProfileSearchResult.get(usersOption).getPhone(), newValue)));
        profileServices.showModifiedProfile(newProfile);
        System.out.println("Press 1 to confirm or 0 to stop");
        String lastOption = sc.nextLine();
        if(lastOption.equals("1")) {
            if(profileServices.isUpdatedProfileName(newProfile))
                System.out.println("Updated");
            else
                System.out.println("Something wrong!");
        } else {
            System.out.println("Thank you!");
        }
    }
}
