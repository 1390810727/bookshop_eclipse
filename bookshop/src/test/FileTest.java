package test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileTest  extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String img=request.getSession().getServletContext().getRealPath("img");
		System.out.println(request.getSession().getServletContext().getRealPath("") );
		PrintWriter pw=response.getWriter();
		
		
		File file=new File(img+"\\we.txt");
		System.out.println(file.exists());
		file.createNewFile();
	
		pw.println("<img src='"+"/bookshop/img/4.jpg"+
				"'"
				+ " alt='²âÊÔ'"
				+ ">");
	}
	
}
