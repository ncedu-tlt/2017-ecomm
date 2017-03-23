package ru.ncedu.ecomm.servlets.services;

import ru.ncedu.ecomm.data.models.User;
import ru.ncedu.ecomm.utils.EncryptionUtils;
import ru.ncedu.ecomm.utils.UserValidationUtils;

import java.util.Objects;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

public class ProfileService {


    private ProfileService() {
    }

    private static ProfileService instance;

    public static synchronized ProfileService getInstance() {
        if (instance == null) {
            instance = new ProfileService();
        }
        return instance;
    }

    public String getAnswerAccordingValidation(String resultValidation, User userForChange) {
        String result = null;
        switch (resultValidation) {
            case "Success":
                result = getResultAfterUpdate(userForChange);
                break;
            case "ErrorInputEmail":
                result = "ErrorInputEmail";
                break;
            case "ErrorCompareEmail":
                result = "ErrorCompareEmail";
                break;
            case "ErrorInputPassword":
                result = "ErrorInputPassword";
                break;
            case "ErrorInputFirstName":
                result = "ErrorInputFirstName";
                break;
            case "ErrorInputLastName":
                result = "ErrorInputLastName";
                break;
        }
        return result;
    }

    private String getResultAfterUpdate(User userForChange) {
        if (updateUser(userForChange) != null)
            return "Success";
        else
            return "ErrorUpdate";
    }

    private User updateUser(User userByChange) {
        if (userByChange == null) {
            return null;
        } else {
            return getDAOFactory().getUserDAO().updateUser(userByChange);
        }
    }

    public String getResultAfterValidation(User userForChange, User userForCompare) {
        if (!isEmailCorrect(userForChange.getEmail()))
            return "ErrorInputEmail";
        if (!isNewEmailDiffers(userForChange.getEmail(), userForCompare))
            return "ErrorCompareEmail";
        if (!isPasswordCorrect(userForChange.getPassword(), userForCompare))
            return "ErrorInputPassword";
        if (!isFirstNameCorrect(userForChange.getFirstName()))
            return "ErrorInputFirstName";
        if (!isLastNameCorrect(userForChange.getLastName()))
            return "ErrorInputLastName";

        return "Success";
    }

    private boolean isEmailCorrect(String email) {
        if (!Objects.equals(email, "")) {
            if (!UserValidationUtils.checkEmail(email))
                return false;
        }
        return true;
    }

    private boolean isPasswordCorrect(String password, User userForCompare) {
        if (!Objects.equals(password, "")) {
            if (!UserValidationUtils.checkPassword(password) && !isNewPasswordDiffers(password, userForCompare))
                return false;
        }
        return true;
    }

    private boolean isFirstNameCorrect(String firstName) {
        if (!Objects.equals(firstName, "") && firstName != null) {
            if (!UserValidationUtils.checkName(firstName)) {
                return false;
            }
        }
        return true;
    }

    private boolean isLastNameCorrect(String lastName) {
        if (!Objects.equals(lastName, "") && lastName != null) {
            if (!UserValidationUtils.checkName(lastName)) {
                return false;
            }
        }
        return true;
    }

    private boolean isNewEmailDiffers(String newEmail, User userForCompare) {
        String oldEmail = userForCompare.getEmail();
        return !(oldEmail.equals(newEmail));
    }

    private boolean isNewPasswordDiffers(String newPassword, User userForCompare) {
        String newPasswordHash = EncryptionUtils.getMd5Digest(newPassword);
        String oldPassword = userForCompare.getPassword();
        return !(oldPassword.equals(newPasswordHash));
    }
}
