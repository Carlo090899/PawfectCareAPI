package PawfectCareApi.pawfectcareapi.authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Base64;

public class BasicAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization");

        if (request.getRequestURI().contains("report") || request.getRequestURI().contains("documents")) {

            return true;
        } else {
            if (authHeader != null) {
                String usernameAndPassHash = authHeader.split(" ")[1];
                String usernameAndPass = new String(Base64.getDecoder().decode(usernameAndPassHash));
                String username = usernameAndPass.split(":")[0];
                String password = usernameAndPass.split(":")[1];
                if (username.equalsIgnoreCase("test") && password.equals("test")) {
                    return true;
                } else {
                    response.sendError(401, "Unauthorized");
                    return false;
                }
            } else {
                response.sendError(401, "Unauthorized");
                return false;
            }
        }
    }
}
