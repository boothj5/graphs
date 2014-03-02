package graphs;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.stream.file.FileSource;
import org.graphstream.stream.file.FileSourceFactory;
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
		List<String> props = new ArrayList<String>();
		
		// when
		List<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(0, ints.size());
	}	

	@Test
	public void testOneToOne() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/graph1.xml", null);
		rmp.loadRules();
		List<String> props = new ArrayList<String>();
		props.add("prop1");
		
		// when
		List<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(1, ints.size());
		assertTrue(ints.contains("int1"));
	}

	@Test
	public void testOneToMany() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/graph2.xml", null);
		rmp.loadRules();
		List<String> props = new ArrayList<String>();
		props.add("prop1");
		
		// when
		List<String> ints = rmp.getInts(props);
		
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
		List<String> props = new ArrayList<String>();
		props.add("prop1");
		props.add("prop2");
		
		// when
		List<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(1, ints.size());
		assertTrue(ints.contains("int1"));
	}

	@Test
	public void testManyToMany() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/graph4.xml", null);
		rmp.loadRules();
		List<String> props = new ArrayList<String>();
		props.add("prop1");
		props.add("prop2");
		props.add("prop3");
		props.add("prop4");
		
		// when
		List<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(5, ints.size());
		assertTrue(ints.contains("int1"));
		assertTrue(ints.contains("int2"));
		assertTrue(ints.contains("int3"));
		assertTrue(ints.contains("int4"));
		assertTrue(ints.contains("int5"));
	}

	@Test
	public void testAllWhenNone() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/graph5.xml", null);
		rmp.loadRules();
		List<String> props = new ArrayList<String>();
		props.add("prop3");
		
		// when
		List<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(0, ints.size());
	}

	@Test
	public void testAllWhenOne() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/graph5.xml", null);
		rmp.loadRules();
		List<String> props = new ArrayList<String>();
		props.add("prop1");
		props.add("prop3");
		
		// when
		List<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(0, ints.size());
	}

	@Test
	public void testAllWhenAll() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/graph5.xml", null);
		rmp.loadRules();
		List<String> props = new ArrayList<String>();
		props.add("prop1");
		props.add("prop2");
		
		// when
		List<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(1, ints.size());
		assertTrue(ints.contains("int1"));
	}

	@Test
	public void testAnyWhenNone() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/graph6.xml", null);
		rmp.loadRules();
		List<String> props = new ArrayList<String>();
		props.add("prop3");
		
		// when
		List<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(0, ints.size());
	}

	@Test
	public void testAnyWhenOne() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/graph6.xml", null);
		rmp.loadRules();
		List<String> props = new ArrayList<String>();
		props.add("prop1");
		props.add("prop3");
		
		// when
		List<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(1, ints.size());
		assertTrue(ints.contains("int1"));
	}

	@Test
	public void testAnyWhenAll() throws Exception {
		//given
		RoleMappingProcessor rmp = new RoleMappingProcessor("rules/graph6.xml", null);
		rmp.loadRules();
		List<String> props = new ArrayList<String>();
		props.add("prop1");
		props.add("prop2");
		props.add("prop3");
		
		// when
		List<String> ints = rmp.getInts(props);
		
		// then
		assertEquals(1, ints.size());
		assertTrue(ints.contains("int1"));
	}

}
