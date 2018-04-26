package com.me.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectionFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("filter");
		// TODO Auto-generated method stub
		HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse res=(HttpServletResponse)response;
        
        Enumeration params = req.getParameterNames();
        String sql = "";
        while (params.hasMoreElements()) {
            String name = params.nextElement().toString();
            String[] value = req.getParameterValues(name);
           
            for (int i = 0; i < value.length; i++) {
            	System.out.println(value[i]);
                sql = sql + value[i];
            }
        }

        if (sqlValidate(sql)) {
        	res.sendRedirect("error");
        } else {
            chain.doFilter(request,response);
        }
		
	}
	
	
    protected static boolean sqlValidate(String str) {
        str = str.toLowerCase();
        String badStr = "'|script|and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|" +
                "char|declare|sitename|xp_cmdshell|;|-|+|,|like'|and|exec|execute|insert|drop|" +
                "table|from|grant|use|group_concat|column_name|" +
                "information_schema.columns|table_schema|union|where|select|delete|order|count|*|" +
                "chr|mid|master|truncate|char|declare|;|<|>|-|--|+|,|like|//|/|%|#";
        
        String[] badStrs = badStr.split("\\|");
        for (int i = 0; i < badStrs.length; i++) {
            if (str.indexOf(badStrs[i])>= 0) {
            	System.out.println(badStrs[i]);
                return true;
            }
        }
        return false;
    }
    

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
