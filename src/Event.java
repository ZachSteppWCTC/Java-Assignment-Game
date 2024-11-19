/**
 * This class contains properties of events that when completed, cannot be done again, and usually grant an item the first time attempted.
 * @author Zach Stepp
 */
public class Event {
    private Boolean isComplete = false;
    private final String eventMessage;
    private final String completeMessage;
    private final Item eventItem;

    public Event(String eventMessage, String completeMessage, Item eventItem) {
        this.eventMessage = eventMessage;
        this.completeMessage = completeMessage;
        this.eventItem = eventItem;
    }

    public String toString() {
        if (isComplete) {
            return completeMessage;
        }
        else {
            isComplete = true;
            return eventMessage;
        }
    }

    public Item getEventItem() {
        if (isComplete)
            return null;
        else
            return eventItem;
    }

    public Boolean getComplete() {
        return isComplete;
    }
}
