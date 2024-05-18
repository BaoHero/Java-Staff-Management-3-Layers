package BLL;

import DAL.LoginDAL;
import DTO.LoginDTO;

public class LoginBLL {
    LoginDAL userDAL = new LoginDAL();

    public boolean login(LoginDTO user) {
        return userDAL.LoginUser(user);
    }
}

