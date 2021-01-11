package com.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.demo.entity.User;


public class Token {

    public static String getToken(User user){

        String token = JWT.create().withClaim("uid",user.getUid())
                .withClaim("workID",user.getWorkID())
                .withClaim("name",user.getName())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

/*    public static String getToken(Student student){
        String token = JWT.create().withClaim("studentID",student.getStudentID())
                .sign(Algorithm.HMAC256(student.getVerifyCode()));
        return token;
    }*/

    public static String getUsernameFromToken(String token){
        Claim claim=JWT.decode(token).getClaim("name");
        String name=claim.asString();
        return name;
    }

    public static String getWorkIDFromToken(String token){
        Claim claim=JWT.decode(token).getClaim("workID");
        String workID=claim.asString();
        return workID;
    }

    public static String getStudentIDFromToken(String token){
        Claim claim=JWT.decode(token).getClaim("studentID");
        String name=claim.asString();
        return name;
    }

}
