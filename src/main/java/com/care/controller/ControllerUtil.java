package com.care.controller;

import com.care.beans.Member;

import javax.servlet.http.HttpServletRequest;

public class ControllerUtil {
    public static Member getCurrentUser(HttpServletRequest request){
        Member member = null;
        return (Member) request.getSession().getAttribute("currentUser");
    }
}
