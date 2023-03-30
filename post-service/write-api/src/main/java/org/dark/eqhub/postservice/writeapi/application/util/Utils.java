package org.dark.eqhub.postservice.writeapi.application.util;

import org.dark.eqhub.common.Constants;
import org.dark.eqhub.postservice.writeapi.domain.model.Outbox;

public class Utils {
    public static Outbox getNewPostCreatedEvent(String eventData) {
        Outbox outbox = new Outbox();
        outbox.setAggregateId(org.dark.eqhub.common.util.Utils.GetUUID());
        outbox.setEventTime(org.dark.eqhub.common.util.Utils.GetDate());
        outbox.setAggregateType(Constants.AGGREGATE_NAME);
        outbox.setEventName(Constants.POST_CREATED);
        outbox.setEventData(eventData);
        return outbox;
    }
}
