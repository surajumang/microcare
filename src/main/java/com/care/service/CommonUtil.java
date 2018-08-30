package com.care.service;

import com.care.beans.Member;
import com.care.exception.*;
import javax.servlet.http.HttpServletRequest;

public class CommonUtil {

    public static void setLoggedInUser(Member member, HttpServletRequest request)throws IncompatibleUserTypeException{
        if (member == null)
            throw new IncompatibleUserTypeException("User can't be null");
        request.getSession().setAttribute("loggedInMember", member);
    }

    public static Member getLoggedInUser(HttpServletRequest request) throws NoUserLoggedInException, IncompatibleUserTypeException{
        Object member = request.getSession().getAttribute("loggedInUser");
        if (member == null)
            throw new NoUserLoggedInException("No user logged in");
        if(!(member instanceof Member))
            throw new IncompatibleUserTypeException("Incompatible type");
        return (Member)member;
    }

    public static void removeLoggedInUser(HttpServletRequest request) throws NoUserLoggedInException{
        Object member = request.getSession().getAttribute("loggedInUser");
        if (member == null)
            throw new NoUserLoggedInException("No user logged in");
        member = null;
    }

    public static boolean isMemberLoggedIn(HttpServletRequest request)throws NoUserLoggedInException, IncompatibleUserTypeException{
        return getLoggedInUser(request) instanceof Member;

    }

    public boolean isLoggedInMemberEquals(HttpServletRequest request, int userId)throws NoUserLoggedInException, IncompatibleUserTypeException{
        return  (getLoggedInUser(request).getId() == userId);
    }
}
