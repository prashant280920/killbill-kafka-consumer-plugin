package org.killbill.billing.kafkaConsumerPlugin.json;

import java.math.BigDecimal;

import org.joda.time.DateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConsumerUsageRecord {
    private DateTime recordDate;
    private BigDecimal amount;
}
