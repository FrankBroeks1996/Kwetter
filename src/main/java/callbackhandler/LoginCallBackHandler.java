package callbackhandler;

import dto.UserDTO;

import javax.security.auth.callback.*;
import java.io.IOException;

public class LoginCallBackHandler implements CallbackHandler {

    private UserDTO userDTO;

    public LoginCallBackHandler(UserDTO userDTO){
        this.userDTO = userDTO;
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (int i = 0; i < callbacks.length; i++) {
            if (callbacks[i] instanceof TextOutputCallback) {
                System.out.println("a");
            } else if (callbacks[i] instanceof NameCallback) {
                NameCallback nc = (NameCallback)callbacks[i];
                nc.setName(userDTO.getUsername());
            } else if (callbacks[i] instanceof PasswordCallback) {
                PasswordCallback pc = (PasswordCallback)callbacks[i];
                pc.setPassword(userDTO.getPassword().toCharArray());
            } else {
                throw new UnsupportedCallbackException
                        (callbacks[i], "Unrecognized Callback");
            }
        }
    }
}
