package edu.stanford.notify;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.google.common.collect.Lists;

/**
 * 
 * This test shows how to test that your controller maps the expected URLs and sets the models as expcted. It also use
 * Mockito to stub the behavior of the controller's dependencies
 * 
 * <p>
 * More details on Mockito can be found on the <a
 * href="http://docs.mockito.googlecode.com/hg/latest/org/mockito/Mockito.html"> website</a>
 * </p>
 * 
 */
// @RunWith says "run this test with Mockito and do some magic with those annotations"
@RunWith(value = MockitoJUnitRunner.class)
public class HomeControllerTest {

    /**
     * @Mock gives us a mock version of this class. We can then stub the behavior
     */
    @Mock
    NotifyQaManager manager;

    /**
     * Class under test. The @InjectMocks creates an instance of this class and resolves any dependencies using other
     * classes defined with @Mock
     */
    @InjectMocks
    HomeController homeController;

    /**
     * A mock webapp that will drive the controller we want to test
     */
    MockMvc webApp;

    /**
     * Setup the mock webapp
     */
    @Before
    public void setup() {
        this.webApp = standaloneSetup(this.homeController).build();
    }

    /**
     * Test that our url request mappings and model building are working
     * 
     * @throws Exception
     *             Thrown if errors running mock webapp
     */
    @Test
    public void testGetDetails() throws Exception {

        /*
         * Setup test: stub the behavior of NotifyQa manager
         */
        MessageDetails details = new MessageDetails();
        when(this.manager.loadMessage(1234)).thenReturn(details);

        /*
         * Run test: do a get and then we'll check that the message details is correctly retrieved
         */
        ResultActions ra = this.webApp.perform(get("/details/{id}", "1234"));

        /*
         * Assert results:
         */
        ra.andExpect(status().isOk());
        // we expect the message details to be in the model
        ra.andExpect(model().attribute("messageDetails", details));
        ra.andExpect(view().name("details"));
    }

    /**
     * Test that our url request mappings and model building are working with json
     * 
     * @throws Exception
     *             Thrown if errors running mock webapp
     */
    @Test
    public void testGetJsonDetails() throws Exception {

        /*
         * Setup test: stub the behavior of NotifyQa manager
         */
        MessageDetails details = new MessageDetails();
        details.setId(1234);
        when(this.manager.loadMessage(1234)).thenReturn(details);

        /*
         * Run test: do a get and then we'll check that the json is returned correctly
         */
        ResultActions ra = this.webApp.perform(get("/details/{id}", "1234").accept(MediaType.APPLICATION_JSON));

        /*
         * Assert results:
         */
        // for debugging you can print out what was returned
        ra.andDo(print());
        ra.andExpect(status().isOk());
        // we expect some json content
        ra.andExpect(content().contentType(MediaType.APPLICATION_JSON));
        ra.andExpect(jsonPath("$.id").value(1234));
    }

    /**
     * Test loading the home page
     * 
     * @throws Exception
     *             Thrown if errors running mock webapp
     */
    @Test
    public void testHomepage() throws Exception {

        /*
         * Setup test: stub the behavior of NotifyQa manager
         */

        List<MessageInfo> results = Lists.newArrayList(new MessageInfo());
        when(this.manager.listMessages()).thenReturn(results);

        /*
         * Run test: do a get and then we'll check list messages is called.
         */
        ResultActions ra = this.webApp.perform(get("/"));

        /*
         * Assert results:
         */
        ra.andExpect(status().isOk());
        // for debugging you can print out what was returned
        ra.andDo(print());
        // we expect the message details to be in the model
        ra.andExpect(model().attribute("results", results));
        ra.andExpect(view().name("home"));
    }

    /**
     * Test searching from the home page
     * 
     * @throws Exception
     *             Thrown if errors running mock webapp
     */
    @Test
    public void testSearch() throws Exception {

        /*
         * Setup test: stub the behavior of NotifyQa manager
         */

        List<MessageInfo> results = Lists.newArrayList(new MessageInfo());
        when(this.manager.searchMessages("I love nachos")).thenReturn(results);

        /*
         * Run test: do a get and then we'll check list messages is called.
         */
        ResultActions ra = this.webApp.perform(get("/?q=I love nachos"));

        /*
         * Assert results:
         */
        ra.andExpect(status().isOk());
        // for debugging you can print out what was returned
        ra.andDo(print());
        // we expect the message details to be in the model
        ra.andExpect(model().attribute("results", results));
        ra.andExpect(view().name("home"));
    }

    /**
     * Test behavior when getting details on a non existent resource
     * 
     * 
     * @throws Exception
     *             Thrown if errors running mock webapp
     */
    @Test
    public void testGetDetails404() throws Exception {
        /*
         * Setup test: stub the behavior of NotifyQa manager
         */
        when(this.manager.loadMessage(1234)).thenThrow(new ResourceNotFoundException("Not found!"));

        /*
         * Run test: do a get and then we'll check that the message details is correctly retrieved
         */
        ResultActions ra = this.webApp.perform(get("/details/{id}", "1234"));
        // for debugging, print out the result
        ra.andDo(print());

        /*
         * Assert results:
         */
        ra.andExpect(status().isNotFound());

    }

}
