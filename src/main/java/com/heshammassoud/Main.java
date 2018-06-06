package com.heshammassoud;

import com.heshammassoud.models.HipChatRoom;
import com.heshammassoud.models.HipChatRoomBuilder;
import com.heshammassoud.utils.HipChatClientUtils;
import io.evanwong.oss.hipchat.v2.HipChatClient;
import io.evanwong.oss.hipchat.v2.rooms.MessageColor;
import io.evanwong.oss.hipchat.v2.rooms.SendRoomNotificationRequest;
import io.evanwong.oss.hipchat.v2.users.UserItem;
import io.evanwong.oss.hipchat.v2.users.Users;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.client.SphereClientConfig;
import io.sphere.sdk.client.SphereClientConfigBuilder;
import io.sphere.sdk.products.queries.ProductByIdGet;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static java.lang.String.format;

public class Main {
    private static final String HIPCHAT_ACCESS_TOKEN = "oFQL64U4tmjlN6l56UdwjV2xF9kaSvAibHEjdSQx";
    private static final String MAIN_MENU_MESSAGE = "1. Play around with commercetools project data.\n" +
            "2. Play table tennis.";
    private static final String GOOD_MORNING_MESSAGE =
            "Hi, good morning %s! What can I do for you today?:)\n" + MAIN_MENU_MESSAGE;
    private static final String WISH_IS_MY_COMMAND_MESSAGE = "Your wish is my command (obey) I just need a small " +
            "favour to completely obey your wishes.";
    private static final String ASK_CREDENTIALS = "Please provide me the credentials to be able to access your CTP " +
            "project (Those can be found here **link**).";
    private static final String ASK_CREDENTIALS_MESSAGE_FIRST_TIME =
            WISH_IS_MY_COMMAND_MESSAGE + "\n" + ASK_CREDENTIALS;
    private static final String ASK_CURRENT_PROJECT_KEY = "Please enter the project key";
    private static final String ASK_CURRENT_CLIENT_SECRET = "Please enter the client secret";
    private static final String ASK_CURRENT_CLIENT_ID = "Please enter the client id";
    private static final String SUCCESSFULLY_ACCESSED_PROJECT = "YAYY!! SUCESS!! GREEN GREEN GUGGY GUGGY!";
    private static final String WISH_IS_TRULY_MY_COMMAND = "Now, your wish is truly my command (geeny)";
    private static final String CTP_PLAYGROUND_MENU =
            "1. View products.\n" +
                    "2. View product types.\n" +
                    "3. View inventory entries.\n" +
                    "4. Delete all products.\n" +
                    "5. Delete all product types.\n" +
                    "6. Delete all inventory entries.\n" +
                    "7. Sync products to a target commercetools project. (Please make sure all the prerequisites are set: references)\n" +
                    "8. Sync product types to a target commercetools project. (Please make sure all the prerequisites are set: references)\n" +
                    "9. Sync categories to a target commercetools project. (Please make sure all the prerequisites are set: references)\n" +
                    "10. Sync inventory entries to a target commercetools project. (Please make sure all the prerequisites are set: references)\n" +
                    "11. Change context. (back to main menu)";
    private static final String FUN_FACT_1 = "Nice! Fun fact: the sync process uses a pretty cool java library (commercetools-sync-java) underneath\n" +
            "which is actually developed by the 'professional-services' library. Give them some Kudos for it!";


    private static void test() throws InterruptedException, ExecutionException {
        final HipChatClient client = HipChatClientUtils.getHipChatClient(HIPCHAT_ACCESS_TOKEN);

        final HipChatRoom room = HipChatRoomBuilder.ofName("ROOM_NAME")
                                                   .build();

        final String userEmail = "hesham.massoud@commercetools.de";

        client.preparePrivateMessageUserRequestBuilder(userEmail,
                format(GOOD_MORNING_MESSAGE, "Hesham"))
              .build()
              .execute().get();


        getUser(client, userEmail).ifPresent(user -> {
            final String userName = user.getName();

            client.preparePrivateMessageUserRequestBuilder(userEmail,
                    format(GOOD_MORNING_MESSAGE, userName))
                  .build()
                  .execute();

            // get answer
            final int mainMenuOptionRequested = 2;

            switch (mainMenuOptionRequested) {
                case 1:
                    break;//CTP
                case 2:
                    break;//TENNIS
            }


        });

        final SendRoomNotificationRequest notificationRequest =
                client.prepareSendRoomNotificationRequestBuilder(room.getName(), "Hello World!")
                      .setColor(MessageColor.YELLOW)
                      .setNotify(true)
                      .build();

        // execute send room notification request.
        notificationRequest.execute().get();
    }

    @Nonnull
    private static Optional<UserItem> getUser(@Nonnull final HipChatClient client,
                                              @Nonnull final String userEmail)
            throws InterruptedException, ExecutionException {
        final Users users = client.prepareGetAllUsersRequestBuilder()
                                  //.addExpansion(format("userEmail=%s", userEmail))
                                  .build()
                                  .execute()
                                  .get();
        return users.getItems().stream().findFirst();
    }

    private static void playgroundDialogue(@Nonnull final HipChatClient client, @Nonnull final UserItem user) {
        final String userId = user.getId() + "";
        final String userName = user.getName();

        client.preparePrivateMessageUserRequestBuilder(userId, ASK_CREDENTIALS_MESSAGE_FIRST_TIME)
              .build()
              .execute();

        client.preparePrivateMessageUserRequestBuilder(userId, ASK_CURRENT_PROJECT_KEY)
              .build()
              .execute();
        final String currentProjectKey = "PROJECT_KEY";

        client.preparePrivateMessageUserRequestBuilder(userId, ASK_CURRENT_CLIENT_ID)
              .build()
              .execute();
        final String currentClientId = "CLIENT_ID";

        client.preparePrivateMessageUserRequestBuilder(userId, ASK_CURRENT_CLIENT_SECRET)
              .build()
              .execute();
        final String currentClientSecret = "CLIENT_SECRET";


        final SphereClient ctpClient = getCtpClient(currentProjectKey, currentClientId, currentClientSecret);

        try {
            ctpClient.execute(ProductByIdGet.of(""))
                     .toCompletableFuture()
                     .get();


        } catch (InterruptedException | ExecutionException e) {
            // cound't connect to CTP
            e.printStackTrace();
        }


    }

    @Nonnull
    private static SphereClient getCtpClient(@Nonnull final String currentProjectKey,
                                             @Nonnull final String currentClientId,
                                             @Nonnull final String currentClientSecret) {

        final SphereClientConfig ctpClientConfig = SphereClientConfigBuilder
                .ofKeyIdSecret(currentProjectKey, currentClientId, currentClientSecret)
                .build();
        return SphereClient.of(ctpClientConfig, null, null);
    }
}
