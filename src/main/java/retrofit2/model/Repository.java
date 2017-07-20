package retrofit2.model;

/**
 * 仓库。
 *
 * @author dannong
 * @since 2017年07月21日 01:08
 */
public class Repository {

  private long id;

  private String name;

  private Owner owner;

  private String description;

  private boolean fork;

  private String url;

  private String language;

  private int forks;

  public long getId() {
    return id;
  }

  public Repository setId(long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Repository setName(String name) {
    this.name = name;
    return this;
  }

  public Owner getOwner() {
    return owner;
  }

  public Repository setOwner(Owner owner) {
    this.owner = owner;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Repository setDescription(String description) {
    this.description = description;
    return this;
  }

  public boolean isFork() {
    return fork;
  }

  public Repository setFork(boolean fork) {
    this.fork = fork;
    return this;
  }

  public String getUrl() {
    return url;
  }

  public Repository setUrl(String url) {
    this.url = url;
    return this;
  }

  public String getLanguage() {
    return language;
  }

  public Repository setLanguage(String language) {
    this.language = language;
    return this;
  }

  public int getForks() {
    return forks;
  }

  public Repository setForks(int forks) {
    this.forks = forks;
    return this;
  }
}
