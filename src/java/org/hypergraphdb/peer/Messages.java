package org.hypergraphdb.peer;
import static org.hypergraphdb.peer.HGDBOntology.*;
import static org.hypergraphdb.peer.Structs.*;

import java.util.Map;
import java.util.UUID;

import org.hypergraphdb.peer.protocol.Performative;
import org.hypergraphdb.peer.workflow.Activity;


/**
 * @author Cipri Costa
 *
 * Class with static methods to help manipulate messages.
 */
public class Messages
{
    public static Message createMessage(Performative performative, Activity activity)
    {
        return createMessage(performative, activity.getType(), activity.getId());
    }
    
	public static Message createMessage(Performative performative, String type, UUID activityId)
	{
		return new Message(struct(Messages.PERFORMATIVE, performative, 
		                          Messages.ACTIVITY_TYPE, type, 
		                          Messages.CONVERSATION_ID, activityId));
	}

    public static Message getReply(Message msg, Performative performative, Object content)
    {
        return (Message)combine(getReply(msg, performative), struct(Messages.CONTENT, content));
    }
	
	public static Message getReply(Message msg, Performative performative)
	{
	    return (Message)combine(getReply(msg), struct(Messages.PERFORMATIVE, performative));
	}
	
	public static Message getReply(Message msg)
	{
		Map<String, Object> s = struct(Messages.ACTIVITY_TYPE, getPart(msg, Messages.ACTIVITY_TYPE),
		                               Messages.CONVERSATION_ID, getPart(msg, Messages.CONVERSATION_ID));
		String replyWith = getPart(msg, Messages.REPLY_WITH);
		if (replyWith != null)
		    return new Message(combine(s, struct(Messages.IN_REPLY_TO, replyWith)));
		else
		    return new Message(s);
	}
	
	public static Object getSender(Message msg)
	{
	    return getPart(msg, Messages.REPLY_TO);	  
	}

    public static final String PERFORMATIVE = "performative";
    public static final String IN_REPLY_TO = "in-reply-to";
    public static final String REPLY_WITH = "reply-with";
    public static final String CONTENT = "content";
    public static final String OPERATION = "operation";
    public static final String REPLY_TO = "reply-to";
    public static final String CONVERSATION_ID = "conversation-id";
    public static final String PARENT_SCOPE = "x-parent-scope";
    public static final String ACTIVITY_TYPE = "x-activity-type";
}