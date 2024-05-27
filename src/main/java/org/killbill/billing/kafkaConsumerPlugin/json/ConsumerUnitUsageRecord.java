package org.killbill.billing.kafkaConsumerPlugin.json;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConsumerUnitUsageRecord {
    private String unitType;

    private List<ConsumerUsageRecord> usageRecords;
}
