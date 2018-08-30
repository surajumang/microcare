package com.care.service;

import com.care.beans.Member;

public class AuthenticationUtil {
    private static Member loggedInMember = null;

    public static void setLoggedInUser(Member member){
        loggedInMember = member;
    }

    public static Member getLoggedInUser(){
        if (loggedInMember == null)
            throw new NoUserLoggedInException("Not logged in");
        return loggedInMember;
    }

    public static void removeLoggedInUser(){
        if (loggedInMember == null)
            throw new NoUserLoggedInException("No user logged in");
        loggedInMember = null;
    }

    public static boolean isMemberLoggedIn(){
        return loggedInMember != null;
    }
}
