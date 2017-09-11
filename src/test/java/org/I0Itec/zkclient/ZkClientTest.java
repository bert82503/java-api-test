package org.I0Itec.zkclient;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * Test of {@link ZkClient}.
 *
 * @author dannong
 * @since 2017年09月11日
 */
public class ZkClientTest {

  @Test
  public void create() {
    String path = "/zipkin/kafka/send";
    String data = "http://172.16.48.198:8080/kafka/publish";

    ZkClient zkClient = new ZkClient("127.0.0.1:2181", 3000, 1000);

    if (!zkClient.exists(path)) {
      zkClient.createPersistent(path, true);
      zkClient.writeData(path, data);
    }

    Object kafkaSendUrl = zkClient.readData(path);
    assertThat(kafkaSendUrl).isNotNull();
    assertThat(kafkaSendUrl).isEqualTo(data);
  }

}
