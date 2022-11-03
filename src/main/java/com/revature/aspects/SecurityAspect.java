package com.revature.aspects;

import com.revature.annotations.Secured;
import com.revature.dtos.PrincipalDTO;
import com.revature.exceptions.AuthorizationException;
import com.revature.services.TokenService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class SecurityAspect {

    private HttpServletRequest req;
    private TokenService ts;

    public SecurityAspect(HttpServletRequest req, TokenService ts){
        this.req = req;
        this.ts = ts;
    }

    @Around("@annotation(com.revature.annotations.Secured)")
    public Object secure(ProceedingJoinPoint pjp) throws Throwable {
        // retrieving methods with @Secured
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        // retrieving annotation
        Secured securedAnnotation = method.getAnnotation(Secured.class);
        // retrieving allowedRoles for this method
        List<String> allowedRoles = Arrays.asList(securedAnnotation.rolesAllowed());

        //retrieving Authorization header from the request, if none are found, throw a AuthorizationException
        String token = req.getHeader("Authorization");

        // parse token to retrieve user information
        PrincipalDTO principal = ts.extractTokenDetails(token);

        // check if principal role is present in the allowedRoles list
        if(!allowedRoles.contains(principal.getRole().toString())){
            throw new AuthorizationException();
        }
        return pjp.proceed();
    }
}
