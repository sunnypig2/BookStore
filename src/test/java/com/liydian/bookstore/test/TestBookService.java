package com.liydian.bookstore.test;

import com.liyidan.bookstore.service.BookService;
import com.liyidan.bookstore.entities.Book;

import org.junit.BeforeClass;
import org.junit.Test;
//import org.junit.Assert.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
/*
 * 所有的测试均通过，有一个想法就是能否测试完成后数据库的还原，如删除的数据在测试后不被真正删除
 */

public class TestBookService {
	static BookService bookservice;
	
	@BeforeClass
	public static void before(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		bookservice = ctx.getBean(BookService.class);
	}
	
	@Test
	public void testGetAllBooks(){
		List<Book> books = bookservice.getAllBooks();
		assertNotNull(books);
	}
	
	@Test
	public void testAdd(){
		Book entity = new Book(0,"Hibernate 第七版",78.1,new Date());
		try{
			assertEquals(1,bookservice.add(entity));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeletInt(){
		assertEquals(1,bookservice.delete(9));
	}
	
	@Test
	public void testDeleteStringArray(){
		String[] ids={"7","11","12"};
		assertEquals(3,bookservice.delete(ids));
	}
	
	@Test
	public void testUpdate(){
		Book entity = new Book(7,"Hibernate 第二版",79.1,new Date());
		try{
			assertEquals(1,bookservice.update(entity));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetBookById(){
		assertNotNull(bookservice.getBookById(1));
	}
	
	@Test
	public void testAddTrouble(){
		//因为书名相同，添加第二本会失败，用于测试事务
		Book entity1 = new Book(0,"Hibernate 第八版",78.1,new Date());
		Book entity2 = new Book(0,"Hibernate 第八版",78.1,new Date());
		assertEquals(2,bookservice.add(entity1,entity2));
	}
}
