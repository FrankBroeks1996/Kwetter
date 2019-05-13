package callbackhandler;

import dto.RegisterDTO;
import dto.UserDTO;

import javax.security.auth.callback.*;
import java.io.IOException;

public class LoginCallBackHandler implements CallbackHandler {

    private RegisterDTO registerDTO;

    public LoginCallBackHandler(RegisterDTO registerDTO){
        this.registerDTO = registerDTO;
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (int i = 0; i < callbacks.length; i++) {
            if (callbacks[i] instanceof NameCallback) {
                NameCallback nc = (NameCallback)callbacks[i];
                nc.setName(registerDTO.getUsername());
            } else if (callbacks[i] instanceof PasswordCallback) {
                PasswordCallback pc = (PasswordCallback)callbacks[i];
                pc.setPassword(registerDTO.getPassword().toCharArray());
            } else {
                throw new UnsupportedCallbackException
                        (callbacks[i], "Unrecognized Callback");
            }
        }
    }
}
