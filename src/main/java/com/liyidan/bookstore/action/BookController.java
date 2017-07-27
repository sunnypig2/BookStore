package com.liyidan.bookstore.action;
/*
 * 该控制其继承baseController, 中间每一个参数为(HttpServletRequest request,HttpServletResponse response) 
 * 的方法都充当一个action
 */
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

import com.liyidan.bookstore.service.BookService;
import com.liyidan.bookstore.entities.*;

@WebServlet("/BookController.do")
public class BookController extends BaseController{
	private static final long serialVersionUID = 1L;
	
	BookService bookservice;
	
	
	@Override
	public void init()throws ServletException{
		bookservice = CtxUtil.getBean(BookService.class);   //???
	}
	
	//图书列表action
	public String ListBook(HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("books", bookservice.getAllBooks());
		return "ListBook.jsp";
	}
	
	//删除图书
	public String Delete(HttpServletRequest request,HttpServletResponse response){
		int id=Integer.parseInt(request.getParameter("id"));
		request.setAttribute("message", bookservice.delete(id)>0?"删除成功！":"删除失败！");
		request.setAttribute("books", bookservice.getAllBooks());
		return "ListBook.jsp";
	}
	
	//多删除图书Action
	public String Deletes(HttpServletRequest request,HttpServletResponse response){
		String[] ids = request.getParameterValues("id");
		if(ids!=null&&ids.length>0){
			request.setAttribute("message", bookservice.delete(ids)>0?"删除成功！":"删除失败！");
		}else{
			request.setAttribute("message", "请选择删除项！");
		}
		request.setAttribute("books",bookservice.getAllBooks());
		return "listBook.jsp";
		
	}
	
	//添加图书
	public String AddBook(HttpServletRequest request,HttpServletResponse response){
		return "AddBook.jsp";
	}
	
	//保存添加图书Action
	public String AddBookPost(HttpServletRequest request,HttpServletResponse response){
		try{
			String title = request.getParameter("title");
			double price = Double.parseDouble(request.getParameter("price"));
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date publishDate = simpleDateFormat.parse(request.getParameter("publishDate"));
			
			Book entity = new Book(0,title,price,publishDate);
			request.setAttribute("message", bookservice.update(entity)>0?"更新成功！":"更新失败！");
			request.setAttribute("model", entity);
		}catch(Exception exp){
			request.setAttribute("message", exp.getMessage());
			exp.printStackTrace();
		}
		return "EditBook.jsp";
	}

}


