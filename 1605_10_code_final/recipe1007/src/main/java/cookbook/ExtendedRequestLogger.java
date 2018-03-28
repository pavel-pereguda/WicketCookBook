package cookbook;

import org.apache.wicket.RequestCycle;
import org.apache.wicket.protocol.http.RequestLogger;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.util.string.AppendingStringBuffer;

public class ExtendedRequestLogger extends RequestLogger {
	@Override
	protected AppendingStringBuffer createLogString(RequestData rd,
			SessionData sd, boolean includeRuntimeInfo) {
		AppendingStringBuffer buffer = super.createLogString(rd, sd,
				includeRuntimeInfo);

		RequestCycle rc = RequestCycle.get();
		WebRequest request = (WebRequest) rc.getRequest();

		buffer.append(",ip: " + request.getHttpServletRequest().getRemoteAddr());
		return buffer;
	}
}
