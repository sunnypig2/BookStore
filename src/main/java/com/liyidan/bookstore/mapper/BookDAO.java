package com.liyidan.bookstore.mapper;

import java.util.List;
import com.liyidan.bookstore.entities.*;
import org.apache.ibatis.annotations.*;

/*
 * 这个项目我们采用接口与xml结束的形式完成关系与对象间的映射，在接口中定义一些数据访问的方法，在xml文件中定义实现数据访问需要的sql脚本。
 * 图书访问映射接口如下：
 */
public interface BookDAO {
	public List<Book> getAllBooks();
	
	public Book getBookById(@Param("id") int id);
	
	public int add(Book entity);
	
	public int delete(int id);
	
	public int update(Book entity);
}
