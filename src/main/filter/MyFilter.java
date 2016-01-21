package main.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter("/rest/*")
public class MyFilter implements Filter {

	@Override
	public void destroy() {
		System.out.println("MyFilter:destroy");

	}

	@Override
	public void doFilter(ServletRequest sReq, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		//System.out.println("MyFilter:doFiltery");
		HttpServletRequest request = (HttpServletRequest)sReq;
		String uri = request.getRequestURI();
		//check if url is login continue (chain.dofilter..)
		if (!uri.equals("/CoponProject_Phase2/rest/login"))
		{
			//it is no login
			//check if there is session and facade
			HttpSession session = request.getSession(false);
			if (session == null || session.getAttribute("facade") == null )
			{
				//write an error
				HttpServletResponse response =(HttpServletResponse) res;
				response.setContentType("application/json");
				response.setStatus(403);
				PrintWriter write =  response.getWriter();
				write.println("{\"error\":\"you are not logged in\"}");
				write.close();
				return;
			}				
			
		}
		
		//pass the request along the filter chain
		chain.doFilter(sReq, res);
		
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("MyFilter:init");

	}

}
