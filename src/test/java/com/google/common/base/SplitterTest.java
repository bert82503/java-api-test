package com.google.common.base;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for {@link Splitter}.
 *
 * @author xingle
 * @since 2016年05月20日 14:26
 */
public class SplitterTest {

    private static final Splitter splitter = Splitter.on(',');

    @Test
    public void splitToList() {
        String campaignIdsStr = "1,,2,3,";
        List<String> campaignIdStrList = splitter.splitToList(campaignIdsStr);

        List<Long> campaignIdList = new ArrayList<>(campaignIdStrList.size());
        for (String campaignIdStr : campaignIdStrList) {
            if (StringUtils.isEmpty(campaignIdStr)) {
                continue;
            }
            long campaignId = NumberUtils.toLong(campaignIdStr, 0L);
            if (campaignId > 0L) {
                campaignIdList.add(campaignId);
            }
        }

        assertThat(campaignIdList).isNotNull();
        assertThat(campaignIdList.size()).isEqualTo(3);
    }
}
