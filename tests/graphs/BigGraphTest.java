package graphs;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class BigGraphTest {

	@Test
	public void testLoad() throws Exception {
		// given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/big-graph.xml", null);

		// when
		rmp.loadRules();

		// then
		assertTrue(true);
	}

	@Test
	public void testNoInput() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/big-graph.xml", null);
		rmp.loadRules();
		Set<String> props = new HashSet<String>();
		
		// when
		List<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(0, ints.size());
	}	

	@Test
	public void test1() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/big-graph.xml", null);
		rmp.loadRules();
		Set<String> props = new HashSet<String>();
		props.add("prop1");
		
		// when
		List<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(1, ints.size());
		assertTrue(ints.contains("int1"));
	}

	@Test
	public void test2() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/big-graph.xml", null);
		rmp.loadRules();
		Set<String> props = new HashSet<String>();
		props.add("prop1");
		props.add("prop2");
		
		// when
		List<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(1, ints.size());
		assertTrue(ints.contains("int1"));
	}

	@Test
	public void test3() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/big-graph.xml", null);
		rmp.loadRules();
		Set<String> props = new HashSet<String>();
		props.add("prop1");
		props.add("prop2");
		props.add("prop3");
		
		// when
		List<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(2, ints.size());
		assertTrue(ints.contains("int1"));
		assertTrue(ints.contains("int2"));
	}

	@Test
	public void test4() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/big-graph.xml", null);
		rmp.loadRules();
		Set<String> props = new HashSet<String>();
		props.add("prop1");
		props.add("prop2");
		props.add("prop3");
		props.add("prop4");
		
		// when
		List<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(2, ints.size());
		assertTrue(ints.contains("int1"));
		assertTrue(ints.contains("int2"));
	}

	@Test
	public void test5() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/big-graph.xml", null);
		rmp.loadRules();
		Set<String> props = new HashSet<String>();
		props.add("prop1");
		props.add("prop2");
		props.add("prop3");
		props.add("prop4");
		props.add("prop5");
		
		// when
		List<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(2, ints.size());
		assertTrue(ints.contains("int1"));
		assertTrue(ints.contains("int2"));
	}

	@Test
	public void test6() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/big-graph.xml", null);
		rmp.loadRules();
		Set<String> props = new HashSet<String>();
		props.add("prop1");
		props.add("prop2");
		props.add("prop3");
		props.add("prop4");
		props.add("prop5");
		props.add("prop6");
		
		// when
		List<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(3, ints.size());
		assertTrue(ints.contains("int1"));
		assertTrue(ints.contains("int2"));
		assertTrue(ints.contains("int3"));
	}

	@Test
	public void test7() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/big-graph.xml", null);
		rmp.loadRules();
		Set<String> props = new HashSet<String>();
		props.add("prop1");
		props.add("prop3");
		
		// when
		List<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(1, ints.size());
		assertTrue(ints.contains("int1"));
	}

	@Test
	public void test8() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/big-graph.xml", null);
		rmp.loadRules();
		Set<String> props = new HashSet<String>();
		props.add("prop1");
		props.add("prop4");
		
		// when
		List<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(2, ints.size());
		assertTrue(ints.contains("int1"));
		assertTrue(ints.contains("int2"));
	}

	@Test
	public void test9() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/big-graph.xml", null);
		rmp.loadRules();
		Set<String> props = new HashSet<String>();
		props.add("prop1");
		props.add("prop5");
		
		// when
		List<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(1, ints.size());
		assertTrue(ints.contains("int1"));
	}

	@Test
	public void test10() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/big-graph.xml", null);
		rmp.loadRules();
		Set<String> props = new HashSet<String>();
		props.add("prop1");
		props.add("prop6");
		
		// when
		List<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(1, ints.size());
		assertTrue(ints.contains("int1"));
	}

	@Test
	public void test11() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/big-graph.xml", null);
		rmp.loadRules();
		Set<String> props = new HashSet<String>();
		props.add("prop1");
		props.add("prop5");
		props.add("prop6");
		
		// when
		List<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(2, ints.size());
		assertTrue(ints.contains("int1"));
		assertTrue(ints.contains("int3"));
	}

	@Test
	public void test12() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/big-graph.xml", null);
		rmp.loadRules();
		Set<String> props = new HashSet<String>();
		props.add("prop2");
		props.add("prop3");
		
		// when
		List<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(2, ints.size());
		assertTrue(ints.contains("int1"));
		assertTrue(ints.contains("int2"));
	}
}
