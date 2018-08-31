package com.care.service;

import com.care.beans.Member;
import com.care.exception.*;
import javax.servlet.http.HttpServletRequest;

public class CommonUtil {

    public static void setLoggedInUser(Member member, HttpServletRequest request)throws IncompatibleUserTypeException{
        if (member == null)
            throw new IncompatibleUserTypeException("User can't be null");
        request.getSession().setAttribute("loggedInUser", member);
    }

    public static Member getLoggedInUserFromSession(HttpServletRequest request) throws NoUserLoggedInException, IncompatibleUserTypeException{
        return (Member)request.getSession().getAttribute("loggedInUser");
    }

    public static void removeLoggedInUser(HttpServletRequest request) throws NoUserLoggedInException{
        Object member = request.getSession().getAttribute("loggedInUser");
        if (member == null)
            throw new NoUserLoggedInException("No user logged in");
        member = null;
    }

    public static boolean isMemberLoggedIn(HttpServletRequest request)throws NoUserLoggedInException, IncompatibleUserTypeException{
        return getLoggedInUserFromSession(request) instanceof Member;

    }

    public boolean isLoggedInMemberEquals(HttpServletRequest request, int userId)throws NoUserLoggedInException, IncompatibleUserTypeException{
        return  (getLoggedInUserFromSession(request).getId() == userId);
    }
}
