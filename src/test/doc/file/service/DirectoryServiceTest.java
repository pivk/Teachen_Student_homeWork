package test.doc.file.service;

import static org.junit.Assert.*;

import org.junit.Test;

import doc.file.entity.Directory;
import doc.file.service.DirectoryService;

public class DirectoryServiceTest {

	@Test
	public void testAdd() {
		Directory entity=new Directory();
		entity.setMingCheng("²âÊÔÄ¿Â¼");
		entity.setParentId("#");
		DirectoryService service=new DirectoryService();
		boolean result=service.add(entity);
		
		assertEquals(true, result);
	}

}
