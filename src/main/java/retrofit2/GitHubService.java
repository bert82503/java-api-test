package retrofit2;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.model.Repository;

import java.util.List;

/**
 * GitHub服务。
 *
 * @author dannong
 * @since 2017年07月21日 01:07
 */
public interface GitHubService {

  @GET("users/{user}/repos")
  Call<List<Repository>> listRepository(@Path("user") String user);

}
