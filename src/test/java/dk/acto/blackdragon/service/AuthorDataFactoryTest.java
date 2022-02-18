package dk.acto.blackdragon.service;

import dk.acto.blackdragon.model.AuthorData;
import dk.acto.blackdragon.solutions.SolutionAuthorData;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class AuthorDataFactoryTest {

    @Test(groups = "transform")
    public void testCreate(ITestContext context) {

        SolutionAuthorData subject = new SolutionAuthorData() {
        };

        AuthorData result = subject.create();
        assertNotNull(result);
        assertNotNull(result.getLinkedInProfile());
        assertNotNull(result.getSolutionRepository());
        assertTrue(result.getName().length() > 0);
        context.setAttribute("author", result);
    }
}