package springtest;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"classpath*:/spring.xml","classpath*:/spring-mybatis.xml"}) 
public class BaseSpringUnitTest extends AbstractJUnit4SpringContextTests  {
}
