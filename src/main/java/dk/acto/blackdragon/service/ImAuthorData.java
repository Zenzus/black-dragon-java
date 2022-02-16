package dk.acto.blackdragon.service;

import dk.acto.blackdragon.model.AuthorData;
import lombok.SneakyThrows;

import java.net.MalformedURLException;
import java.net.URL;

public class ImAuthorData implements AuthorDataFactory{

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
