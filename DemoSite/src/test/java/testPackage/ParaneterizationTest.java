package testPackage;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ParaneterizationTest {
	
	@Parameters
	public static Collection<Object[]> data(){
		return Arrays.asList(new Object[][] {{"a","b",0,false},{"a","b",0,true}});
	}
	
	private String a;
	private String b;
	private int c;
	private boolean d;
	
	public ParaneterizationTest(String a, String b, int c, boolean d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
	
	@Test
	public void test() {
		System.out.println(a+" "+b+" "+c+" "+d);
	}
}
