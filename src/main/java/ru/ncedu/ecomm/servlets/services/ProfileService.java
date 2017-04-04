package ru.ncedu.ecomm.servlets.services;

import ru.ncedu.ecomm.data.models.UserDAOObject;
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

    public String getAnswerAccordingValidation(String resultValidation, UserDAOObject userForChange) {
        String result = null;
        switch (resultValidation) {
            case "Success":
                result = getResultAfterUpdate(userForChange);
                break;
            case "ErrorInputEmail":
                result = "ErrorInputEmail";
                break;
            case "ErrorDiffersEmail":
                result = "ErrorDiffersEmail";
                break;
            case "ErrorInputPassword":
                result = "ErrorInputPassword";
                break;
            case "ErrorInputPhone":
                result = "ErrorInputPhone";
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

    private String getResultAfterUpdate(UserDAOObject userForChange) {
        if (updateUser(userForChange) != null)
            return "Success";
        else
            return "ErrorUpdate";
    }

    private UserDAOObject updateUser(UserDAOObject userByChange) {
        if (userByChange == null) {
            return null;
        } else {
            return getDAOFactory().getUserDAO().updateUser(userByChange);
        }
    }

    public String getResultAfterValidation(UserDAOObject userForChange, UserDAOObject userForCompare) {
        if (!isEmailCorrect(userForChange.getEmail()))
            return "ErrorInputEmail";
        if (!isNewEmailDiffers(userForChange.getEmail(), userForCompare.getEmail()))
            return "ErrorDiffersEmail";
        if (!isPasswordCorrect(userForChange.getPassword(), userForCompare))
            return "ErrorInputPassword";
        if (!isPhoneCorrect(userForChange.getPhone()))
            return "ErrorInputPhone";
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

    private boolean isNewEmailDiffers(String newEmail, String oldEmail) {
        if (!Objects.equals(newEmail, "") && !Objects.equals(newEmail, oldEmail)) {
            UserDAOObject userByEmail = getDAOFactory().getUserDAO().getUserByEmail(newEmail);
            return userByEmail == null;
        }
        return true;
    }

    private boolean isPasswordCorrect(String password, UserDAOObject userForCompare) {
        if (!Objects.equals(password, "")) {
            if (!UserValidationUtils.checkPassword(password) && !isNewPasswordDiffers(password, userForCompare))
                return false;
        }
        return true;
    }

    private boolean isPhoneCorrect(String phone) {
        if (!Objects.equals(phone, "")) {
            if (!UserValidationUtils.checkPhone(phone))
                return false;
        }
        return true;
    }

    private boolean isNewPasswordDiffers(String newPassword, UserDAOObject userForCompare) {
        String newPasswordHash = EncryptionUtils.getMd5Digest(newPassword);
        String oldPassword = userForCompare.getPassword();
        return !(oldPassword.equals(newPasswordHash));
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
}
