package service;

import entities.User;
import exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import payloads.UserLoginDTO;
import security.JWTTools;

@Service
public class AuthService {

      @Autowired
      private UserService userService;//VEDI per profilo privato



        @Autowired
        private JWTTools jwtTools;

        @Autowired
        private PasswordEncoder bcrypt;

        public String authenticateUser(UserLoginDTO body) {
            // 1. Verifichiamo che l'email dell'utente sia nel db
            User user = userService.findByEmail(body.email());

            // 2. In caso affermativo, verifichiamo se la password fornita corrisponde a quella trovata nel db
            if (bcrypt.matches(body.password(),user.getPassword())) {
                // 3. Se le credenziali sono OK --> Genere un token JWT e lo ritorno
                return jwtTools.createToken(user);
            } else {
                // 4. Se le credenziali NON sono OK --> 401 (Unauthorized)
                throw new UnauthorizedException("Credenziali non valide!");
            }
        }
    }

