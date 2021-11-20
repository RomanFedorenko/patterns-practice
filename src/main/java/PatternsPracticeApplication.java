import handlers.BlacklistRequestHandler;
import handlers.EmailRequestHandler;
import handlers.MobileNumberRequestHandler;
import handlers.RequestHandler;
import mocks.DatabaseMock;
import models.Request;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

import static constants.PatternsConstants.BLACKLISTED_LOGIN;
import static constants.PatternsConstants.SUCCESSFULL_LOGIN;

public class PatternsPracticeApplication {
    public static void main(String[] args) {
        List<Request> requests = getRequests();

        RequestHandler headOfHandlersChain = getHeadOfHandlersChain();

        for (Request request : requests) {
            if (requestCanBeProcessed(request, headOfHandlersChain)) {
                System.out.println(MessageFormat.format("Business logic is applied to request with login \"{0}\" and id \"{1}\"", request.getUserLogin(), request.getRequestId()));
            }
        }
    }

    private static RequestHandler getHeadOfHandlersChain() {
        BlacklistRequestHandler blacklistRequestHandler = new BlacklistRequestHandler();
        MobileNumberRequestHandler mobileNumberRequestHandler = new MobileNumberRequestHandler();
        EmailRequestHandler emailRequestHandler = new EmailRequestHandler();

        blacklistRequestHandler.setNextHandler(mobileNumberRequestHandler);
        mobileNumberRequestHandler.setNextHandler(emailRequestHandler);

        return blacklistRequestHandler;
    }

    private static boolean requestCanBeProcessed(Request request, RequestHandler headOfCoR) {
        if (headOfCoR.handle(request.getUserData())) return true;
        System.out.println(MessageFormat.format("Request with login \"{0}\" and id \"{1}\" won't be processed due to handler failure", request.getUserLogin(), request.getRequestId()));
        return false;
    }

    private static List<Request> getRequests() {
        //REQUESTS CREATION
        Request succesfullRequest = Request.builder()
                .requestId(UUID.randomUUID().toString())
                .userLogin(SUCCESSFULL_LOGIN)
                .userData(DatabaseMock.getUserDataByLogin(SUCCESSFULL_LOGIN))
                .build();

        Request blacklistedRequest = Request.builder()
                .requestId(UUID.randomUUID().toString())
                .userLogin(BLACKLISTED_LOGIN)
                .userData(DatabaseMock.getUserDataByLogin(BLACKLISTED_LOGIN))
                .build();

        return List.of(succesfullRequest, blacklistedRequest);
    }
}
