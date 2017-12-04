package br.com.marvelapi.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = AppWebconfiguration.class)
public class HomeControllerTest {
	
	 private static final int TEST_GET_COMIC_ID = 38002;

	@Mock
	HomeController homeControllerMock;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetComicsId() {
		
		HomeController homecontroller = new HomeController();
        //Mockito.when(homeControllerMock.getComics().theReturn(homecontroller);
		Assert.assertEquals(homecontroller.getComicsId(TEST_GET_COMIC_ID),"X-Men: Fall Of The Mutants (2010)");
	}

}
