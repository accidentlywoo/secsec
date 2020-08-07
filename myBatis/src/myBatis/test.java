package myBatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class test {
	public static void main(String[] args) throws IOException {
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		String resource = "mybatisConf/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// Resources -> .class 경로에서 자원을 찾으려고 한다. "프로젝트"\bin
		SqlSessionFactory factory = builder.build(inputStream);
	}
}
