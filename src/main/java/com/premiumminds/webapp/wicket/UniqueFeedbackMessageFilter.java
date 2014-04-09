package com.premiumminds.webapp.wicket;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;

public class UniqueFeedbackMessageFilter implements IFeedbackMessageFilter {
	private static final long serialVersionUID = 1230282488271953295L;
	
	private List<FeedbackMessage> messages = new ArrayList<FeedbackMessage>();
	private IFeedbackMessageFilter innerFilter;
	
	public UniqueFeedbackMessageFilter(IFeedbackMessageFilter innerFilter){
		this.innerFilter = innerFilter;
	}
	
	public void clearMessages(){
		messages.clear();
	}

	@Override
	public boolean accept(FeedbackMessage message) {
		if(innerFilter!=null && !innerFilter.accept(message)) return false;
		// too bad that FeedbackMessage doesnt have an equals implementation
		for(FeedbackMessage m : messages){
			if(m.getMessage().toString().equals(message.getMessage())) return false;
		}
		return true;
	}

}