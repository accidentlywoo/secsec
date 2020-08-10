package myBatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.my.vo.Customer;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;
import com.my.vo.Product;

public class test {
	public static void main(String[] args) throws IOException {
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		String resource = "mybatisConf/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// Resources -> .class 경로에서 자원을 찾으려고 한다. "프로젝트"\bin
		SqlSessionFactory factory = builder.build(inputStream);
		SqlSession session = factory.openSession();

//		Product product = session.selectOne("ProductMapper.selectByNo", "C0001");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("prod_no", "C0001");
		Product product = session.selectOne("ProductMapper.selectByNo", map);
		System.out.println("selectByNo Product : " + product);

//		Product product2 = new Product("MY001", "마이바티스", 100); // 자바빈의 조건을 만족해야지만 parameter를 사용할 수 있따.
//		session.insert("ProductMapper.insert", product2);
//		// Auto Commit 이 아니다.
//		session.commit();
		Map<String, Object> map1 = session.selectOne("ProductMapper.selectByNoMap", "C0001");
		System.out.println("selectByNoMap Product : " + map1);
		// Alias가 대문자로 변경되서 표현된다.

		List<Product> list = session.selectList("ProductMapper.selectAll");
		for (Product item : list) {
			System.out.println("selectAll Product : " + item);
		}

		OrderInfo info = new OrderInfo();
		Customer order_c = new Customer();
		order_c.setId("id5");
		info.setOrder_c(order_c);

		List<OrderLine> lines = new ArrayList<OrderLine>();
		OrderLine line = new OrderLine();
		Product order_p = new Product();
		order_p.setProd_no("S0001");
		line.setOrder_p(order_p);
		line.setOrder_quantity(1);
		lines.add(line);

		OrderLine line2 = new OrderLine();
		Product order_p2 = new Product();
		order_p2.setProd_no("S0002");
		line2.setOrder_p(order_p2);
		line2.setOrder_quantity(2);
		lines.add(line2);

		info.setLines(lines);

		session.insert("OrderMapper.insertInfo", info.getOrder_c().getId());

		for (OrderLine item : info.getLines()) {
			session.insert("OrderMapper.insertLines", item);
		}
		session.commit();

		List<OrderInfo> list3 = session.selectList("OrderMapper.selectById", "id5");
		for(OrderInfo item : list3) {
			for(OrderLine item2 : item.getLines()) {
				System.out.println("orderLine : "+ item2);
				Product product2 = item2.getOrder_p();
				System.out.println("복잡한 select문 : " +
						product2.getProd_no() + ", "+
						product2.getProd_name() + ", " +
						product2.getProd_price() + ", " +
						item2.getOrder_quantity());
			}
//			System.out.println("Order selectById order_no  : " +item.getOrder_no());
		}
		session.close();
	}
}
