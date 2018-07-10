package org.seeker.common.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.seeker.common.util.SysConstant;
import org.seeker.entity.User;

@SuppressWarnings("serial")
public class LoginFilter extends HttpServlet implements Filter {
	@SuppressWarnings("unused")
	private Logger l=LoggerFactory.getLogger(LoginFilter.class);
	
	public void destroy() {
	}

	public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) sRequest;
		HttpServletResponse response = (HttpServletResponse) sResponse;
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
		User UserPo=(User) request.getSession().getAttribute(SysConstant.sessionUser);
		String url=request.getRequestURI();
		// 修改成可配置的url地址多条 , 后期可以修改成 spring ser安全验证
		if(url.indexOf("/login/handle.do")==-1){
			if(UserPo==null){
				response.setCharacterEncoding("utf-8");
				PrintWriter out=response.getWriter();
				StringBuilder sb=new StringBuilder();
				sb.append("<script type='text/javascript'>");
				sb.append("	top.window.location.href='"+basePath+"login.jsp';");
//				sb.append("	alert('请先登录,在进行其他操作!!!!!');");
				sb.append("</script>");
				out.print(sb.toString());
				
			}
		}
		
		filterChain.doFilter(sRequest, sResponse);
	}
	

	public void init(FilterConfig arg0) throws ServletException {
	}
	
	

}