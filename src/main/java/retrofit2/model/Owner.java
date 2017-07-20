package retrofit2.model;

/**
 * 拥有者。
 *
 * @author dannong
 * @since 2017年07月21日 01:09
 */
public class Owner {

  private String login;

  private long id;

  private String avatarUrl;

  private String url;

  private String type;


  public String getLogin() {
    return login;
  }

  public Owner setLogin(String login) {
    this.login = login;
    return this;
  }

  public long getId() {
    return id;
  }

  public Owner setId(long id) {
    this.id = id;
    return this;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public Owner setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
    return this;
  }

  public String getUrl() {
    return url;
  }

  public Owner setUrl(String url) {
    this.url = url;
    return this;
  }

  public String getType() {
    return type;
  }

  public Owner setType(String type) {
    this.type = type;
    return this;
  }
}
