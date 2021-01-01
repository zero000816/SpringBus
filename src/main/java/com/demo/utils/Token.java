package com.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.impl.ClaimsHolder;
import com.auth0.jwt.interfaces.Claim;
import com.demo.entity.Student;
import com.demo.entity.User;
import com.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;


public class Token {

    public static String getToken(User user){

        String token = JWT.create().withClaim("uid",user.getUid())
                .withClaim("name",user.getName())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

    public static String getToken(Student student){
        String token = JWT.create().withClaim("studentID",student.getStudentID())
                .sign(Algorithm.HMAC256(student.getVerifyCode()));
        return token;
    }

    public static String getUsernameFromToken(String token){
        Claim claim=JWT.decode(token).getClaim("name");
        String name=claim.asString();
        return name;
    }

    public static String getStudentIDFromToken(String token){
        Claim claim=JWT.decode(token).getClaim("studentID");
        String name=claim.asString();
        return name;
    }

}
