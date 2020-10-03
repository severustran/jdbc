import repository.model.Profile;
import services.ProfileServices;
import services.imple.ProfileServicesImpl;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        ProfileServices profileServices = new ProfileServicesImpl();
        List<Profile> profileList = profileServices.getAllProfileNames();
        for (int i = 1; i<=profileList.size(); i++) {
            System.out.println(i + ". " + profileList.get(i-1).getDisplay_name());
        }
        profileList.clear();
        System.out.println("Enter keyword to search...");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        List<Profile> profileListWithInput = profileServices.getProfileByDisplayName(input);
        if (profileListWithInput.isEmpty()) {
            System.out.println("There is no results with your keyword!");
        } else {
            for (int i = 1; i <= profileListWithInput.size(); i++) {
                System.out.print(i + " " + profileListWithInput.get(i - 1).getDisplay_name() +
                        "\t" + profileListWithInput.get(i - 1).getBirthday() +
                        "\t" + (profileListWithInput.get(i - 1).getGender() == "F" ? "Nam" : "Nu") +
                        "\t" + (profileListWithInput.get(i - 1).isLecture() ? "GV" : "HV") +
                        "\t" + profileListWithInput.get(i - 1).getPhone());
                System.out.println();
            }
        }
    }
}
