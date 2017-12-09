package junitTests;

import org.junit.Test;
import static org.junit.Assert.*;

import mvc.Controller;
import mvc.Model;
import mvc.View;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert.*;

public class ControllerTest {

	@Test
	public void ControllerConstructorTest() {
		List<View> viewList = new ArrayList<View>();
		List<Model> modelList = new ArrayList<Model>();
		viewList.add(new Controller().getView());
		modelList.add(new Controller().getModel());
		assertEquals(2, viewList.size() + modelList.size());
	}
	
}
