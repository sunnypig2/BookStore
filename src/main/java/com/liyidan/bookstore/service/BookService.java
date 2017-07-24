package com.liyidan.bookstore.service;

/*
 * 创建bookstores服务类，完成图书馆里业务，有些项目中也叫业务层，这里我们叫服务层。
 */
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.liyidan.bookstore.mapper.*;
import com.liyidan.bookstore.entities.*;
import javax.annotation.Resource;
import java.util.List;

@Service
public class BookService {
	@Resource
	BookDAO bookdao;
	
	public List<Book> getAllBooks(){
		return bookdao.getAllBooks();
	}
	
	public Book getBookById(int id){
		return bookdao.getBookById(id);
	}
	
	public int add(Book entity)throws Exception {
		if(entity.getTitle() == null || entity.getTitle().equals("")){
			throw new Exception("书名必须不为空");
		}
		
		return bookdao.add(entity);
	}
	
	@Transactional
	public int add(Book entity1,Book entityBak){
		int rows = 0;
		rows=bookdao.add(entity1);
		rows=bookdao.add(entityBak);
		return rows;
	}
	
	public int delete(int id){
		return bookdao.delete(id);
	}
	
	/*
	 * 多删除
	 */
	public int delete(String[] ids){
		int rows = 0;
		for(String idStr:ids){
			int id=Integer.parseInt(idStr);
			rows+=delete(id);
		}
		return rows;
	}
	
	public int update(Book entity){
		return bookdao.update(entity);
	}
}
