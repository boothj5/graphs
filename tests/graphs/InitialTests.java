package graphs;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class InitialTests {

	@Test
	public void testLoad() throws Exception {
		// given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/graph1.xml", null);

		// when
		rmp.loadRules();

		// then
		assertTrue(true);
	}

	@Test
	public void testNoInput() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/graph0.xml", null);
		rmp.loadRules();
		Set<String> props = new HashSet<String>();
		
		// when
		Set<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(0, ints.size());
	}	

	@Test
	public void testOneToOne() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/graph1.xml", null);
		rmp.loadRules();
		Set<String> props = new HashSet<String>();
		props.add("prop1");
		
		// when
		Set<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(1, ints.size());
		assertTrue(ints.contains("int1"));
	}

	@Test
	public void testOneToMany() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/graph2.xml", null);
		rmp.loadRules();
		Set<String> props = new HashSet<String>();
		props.add("prop1");
		
		// when
		Set<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(2, ints.size());
		assertTrue(ints.contains("int1"));
		assertTrue(ints.contains("int2"));
	}

	@Test
	public void testManyToOne() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/graph3.xml", null);
		rmp.loadRules();
		Set<String> props = new HashSet<String>();
		props.add("prop1");
		props.add("prop2");
		
		// when
		Set<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(1, ints.size());
		assertTrue(ints.contains("int1"));
	}

	@Test
	public void testManyToMany() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/graph4.xml", null);
		rmp.loadRules();
		Set<String> props = new HashSet<String>();
		props.add("prop1");
		props.add("prop2");
		props.add("prop3");
		props.add("prop4");
		
		// when
		Set<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(5, ints.size());
		assertTrue(ints.contains("int1"));
		assertTrue(ints.contains("int2"));
		assertTrue(ints.contains("int3"));
		assertTrue(ints.contains("int4"));
		assertTrue(ints.contains("int5"));
	}

	@Test
	public void testAndWhenNone() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/graph5.xml", null);
		rmp.loadRules();
		Set<String> props = new HashSet<String>();
		props.add("prop3");
		
		// when
		Set<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(0, ints.size());
	}

	@Test
	public void testAndWhenOne() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/graph5.xml", null);
		rmp.loadRules();
		Set<String> props = new HashSet<String>();
		props.add("prop1");
		props.add("prop3");
		
		// when
		Set<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(0, ints.size());
	}

	@Test
	public void testAndWhenAll() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/graph5.xml", null);
		rmp.loadRules();
		Set<String> props = new HashSet<String>();
		props.add("prop1");
		props.add("prop2");
		
		// when
		Set<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(1, ints.size());
		assertTrue(ints.contains("int1"));
	}

	@Test
	public void testOrWhenNone() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/graph6.xml", null);
		rmp.loadRules();
		Set<String> props = new HashSet<String>();
		props.add("prop3");
		
		// when
		Set<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(0, ints.size());
	}

	@Test
	public void testOrWhenOne() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/graph6.xml", null);
		rmp.loadRules();
		Set<String> props = new HashSet<String>();
		props.add("prop1");
		props.add("prop3");
		
		// when
		Set<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(1, ints.size());
		assertTrue(ints.contains("int1"));
	}

	@Test
	public void testOrWhenAll() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/graph6.xml", null);
		rmp.loadRules();
		Set<String> props = new HashSet<String>();
		props.add("prop1");
		props.add("prop2");
		props.add("prop3");
		
		// when
		Set<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(1, ints.size());
		assertTrue(ints.contains("int1"));
	}

	@Test
	public void testAndFollowedByOrWhenNone() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/graph7.xml", null);
		rmp.loadRules();
		Set<String> props = new HashSet<String>();
		
		// when
		Set<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(0, ints.size());
	}

	@Test
	public void testAndFollowedByOrWhenFirstSatisfiesOr() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/graph7.xml", null);
		rmp.loadRules();
		Set<String> props = new HashSet<String>();
		props.add("prop1");
		
		// when
		Set<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(1, ints.size());
		assertTrue(ints.contains("int1"));
	}

	@Test
	public void testAndFollowedByOrWhenAndSatisfied() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/graph7.xml", null);
		rmp.loadRules();
		Set<String> props = new HashSet<String>();
		props.add("prop2");
		props.add("prop3");
		
		// when
		Set<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(1, ints.size());
		assertTrue(ints.contains("int1"));
	}

}
