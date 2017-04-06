package ru.ncedu.ecomm.servlets.services;

import ru.ncedu.ecomm.data.models.UserDAOObject;
import ru.ncedu.ecomm.utils.EncryptionUtils;
import ru.ncedu.ecomm.utils.UserValidationUtils;

import java.util.Objects;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

public class ProfileService {


    private ProfileService() {
    }

    private final static String SUCCESS = "Success";
    private final static String ERROR_UPDATE = "ErrorUpdate";
    private final static String ERROR_INPUT_EMAIL = "ErrorInputEmail";
    private final static String ERROR_DIFFERS_EMAIL = "ErrorDiffersEmail";
    private final static String ERROR_INPUT_PASSWORD = "ErrorInputPassword";
    private final static String ERROR_INPUT_PHONE = "ErrorInputPhone";
    private final static String ERROR_INPUT_FIRST_NAME = "ErrorInputFirstName";
    private final static String ERROR_INPUT_LAST_NAME = "ErrorInputLastName";

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
            case SUCCESS:
                result = getResultUpdate(userForChange);
                break;
            case ERROR_INPUT_EMAIL:
                result = ERROR_INPUT_EMAIL;
                break;
            case ERROR_DIFFERS_EMAIL:
                result = ERROR_DIFFERS_EMAIL;
                break;
            case ERROR_INPUT_PASSWORD:
                result = ERROR_INPUT_PASSWORD;
                break;
            case ERROR_INPUT_PHONE:
                result = ERROR_INPUT_PHONE;
                break;
            case ERROR_INPUT_FIRST_NAME:
                result = ERROR_INPUT_FIRST_NAME;
                break;
            case ERROR_INPUT_LAST_NAME:
                result = ERROR_INPUT_LAST_NAME;
                break;
        }
        return result;
    }

    private String getResultUpdate(UserDAOObject userForChange) {
        if (updateUser(userForChange) != null)
            return SUCCESS;
        else
            return ERROR_UPDATE;
    }

    private UserDAOObject updateUser(UserDAOObject userByChange) {
        if (userByChange == null) {
            return null;
        } else {
            return getDAOFactory().getUserDAO().updateUser(userByChange);
        }
    }

    public String getResultValidation(UserDAOObject userForChange, UserDAOObject userForCompare) {
        if (!isEmailCorrect(userForChange.getEmail()))
            return ERROR_INPUT_EMAIL;
        if (!isNewEmailDiffers(userForChange.getEmail(), userForCompare.getEmail()))
            return ERROR_DIFFERS_EMAIL;
        if (!isPasswordCorrect(userForChange.getPassword(), userForCompare))
            return ERROR_INPUT_PASSWORD;
        if (!isPhoneCorrect(userForChange.getPhone()))
            return ERROR_INPUT_PHONE;
        if (!isNameCorrect(userForChange.getFirstName()))
            return ERROR_INPUT_FIRST_NAME;
        if (!isNameCorrect(userForChange.getLastName()))
            return ERROR_INPUT_LAST_NAME;

        return SUCCESS;
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

    private boolean isNameCorrect(String name){
        if (!Objects.equals(name, "") && name != null) {
            if (!UserValidationUtils.checkName(name)) {
                return false;
            }
        }
        return true;
    }
}
