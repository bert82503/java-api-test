package retrofit2;

import static org.assertj.core.api.Assertions.assertThat;

import io.netty.handler.codec.http.HttpResponseStatus;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.model.Repository;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

/**
 * Test of {@link GitHubService}。
 *
 * @author dannong
 * @since 2017年07月21日 02:02
 */
public class GitHubServiceTest {

  @Test
  public void ListRepository() throws IOException {
    // 1. Create a very simple REST adapter which points the GitHub API.
    Retrofit retrofit = new Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    // 2. Create an instance of our GitHub API interface.
    GitHubService service = retrofit.create(GitHubService.class);

    // 3. Create a call instance for looking up Retrofit contributors.
    Call<List<Repository>> repositoryListCall = service.listRepository("EdwardLee03");

    // 4. Fetch and print a list of the contributors to the library.
    Response<List<Repository>> response = repositoryListCall.execute();
    assertThat(response.isSuccessful()).isEqualTo(true);
    assertThat(response.code()).isEqualTo(HttpResponseStatus.OK.code());
    List<Repository> repositoryList = response.body();
    assertThat(repositoryList.size()).isEqualTo(30);
  }

}