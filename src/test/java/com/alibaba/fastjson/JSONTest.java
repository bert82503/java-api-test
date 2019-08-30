package com.alibaba.fastjson;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.ParserConfig;
import com.google.common.collect.Maps;
import org.testng.annotations.Test;

/**
 * Test of {@link JSON}.
 *
 * @author dannong
 * @since 2017年07月12日 15:38
 */
public class JSONTest {

  static {
    // 全局缺省的命名策略
//        ParserConfig.getGlobalInstance().propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
  }

  @Test
  public void toJSONString() {
    BankParam bankParam = new BankParam();
    bankParam.setEntryName("37236638385647");
    Map<String, Object> map = Maps.newHashMapWithExpectedSize(1);
    map.put("bankJob", bankParam);
    String jsonString = JSON.toJSONString(map);
    assertThat(jsonString).isEqualTo("{\"bankJob\":{\"entryName\":\"37236638385647\"}}");

    jsonString = JSON.toJSONString(bankParam);
    assertThat(jsonString).isEqualTo("{\"entryName\":\"37236638385647\"}");
  }

  @Test
  public void toJSON() {
    BankParam bankParam = new BankParam();
    bankParam.setEntryName("xxx");
    // 公开的getter/setter方法
    Object obj = JSON.toJSON(bankParam);
    assertThat(obj).isInstanceOf(JSONObject.class);
    JSONObject jsonObject = (JSONObject) obj;
    assertThat(jsonObject.getString("entry_name")).isEqualTo("xxx");
  }

  @Test
  public void parseObject() {
    String text = "{\"entry_name\":\"37236638385647\"}";
    BankParam bankParam = JSON.parseObject(text, BankParam.class, ParserConfig.getGlobalInstance());
    assertThat(bankParam).isNotNull();
    assertThat(bankParam.getEntryName()).isEqualTo("37236638385647");

    text = "{\"entryName\":\"37236638385647\"}";
    bankParam = JSON.parseObject(text, BankParam.class, ParserConfig.getGlobalInstance());
    assertThat(bankParam).isNotNull();
    assertThat(bankParam.getEntryName()).isEqualTo("37236638385647");
  }

//  @JSONType
  private static class BankParam {
    @JSONField(name = "entry_name")
    private String entryName;

    public String getEntryName() {
      return entryName;
    }

    public void setEntryName(String entryName) {
      this.entryName = entryName;
    }

    @Override
    public String toString() {
      return "BankParam{" +
          "entryName='" + entryName + '\'' +
          '}';
    }
  }
}
