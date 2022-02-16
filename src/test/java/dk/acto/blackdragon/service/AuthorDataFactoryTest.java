package dk.acto.blackdragon.service;

import dk.acto.blackdragon.model.AuthorData;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class AuthorDataFactoryTest {

    @Test(groups = "transform")
    public void testCreate(ITestContext context) {

        ImAuthorData subject = new ImAuthorData() {

            @Override
            public AuthorData create() {
                String linkIn = "https://www.linkedin.com/in/jonas-v-b-hein/";
                String gitHub = "https://github.com/Zenzus/black-dragon-java";

                AuthorData data = null;
                try {
                    data = AuthorData.builder().
                            name("Jonas").
                            linkedInProfile(new URL(linkIn)).
                            solutionRepository(new URL(gitHub)).
                            build();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                System.out.println(data);
                return data;
            }
        };

        AuthorData result = subject.create();
        assertNotNull(result);
        assertNotNull(result.getLinkedInProfile());
        assertNotNull(result.getSolutionRepository());
        assertTrue(result.getName().length() > 0);
        context.setAttribute("author", result);
    }
}