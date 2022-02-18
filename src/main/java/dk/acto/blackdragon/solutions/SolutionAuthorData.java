package dk.acto.blackdragon.solutions;

import dk.acto.blackdragon.model.AuthorData;
import dk.acto.blackdragon.service.AuthorDataFactory;
import lombok.SneakyThrows;

import java.net.URL;

public class SolutionAuthorData implements AuthorDataFactory {

  @SneakyThrows
  @Override
  public AuthorData create()  {
    String linkIn = "https://www.linkedin.com/in/jonas-v-b-hein/";
    String gitHub = "https://github.com/Zenzus/black-dragon-java";

      AuthorData data = AuthorData.builder().
              name("Jonas").
              linkedInProfile(new URL(linkIn)).
              solutionRepository(new URL(gitHub)).
              build();

      return data;
  }


}
