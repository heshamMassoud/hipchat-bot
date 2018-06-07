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

import static com.heshammassoud.Messages.*;
import static java.lang.String.format;

public class Main {
    private static final String HIPCHAT_ACCESS_TOKEN = "oFQL64U4tmjlN6l56UdwjV2xF9kaSvAibHEjdSQx";


    private static void test() throws InterruptedException, ExecutionException {
        final HipChatClient client = HipChatClientUtils.getHipChatClient(HIPCHAT_ACCESS_TOKEN);

        final HipChatRoom room = HipChatRoomBuilder.ofName("ROOM_NAME")
                                                   .build();

        final String userEmail = "hesham.massoud@commercetools.de";

        client.preparePrivateMessageUserRequestBuilder(userEmail,
                format(WELCOME_MESSAGE, "Hesham"))
              .build()
              .execute().get();


        getUser(client, userEmail).ifPresent(user -> {
            final String userName = user.getName();

            client.preparePrivateMessageUserRequestBuilder(userEmail,
                    format(WELCOME_MESSAGE, userName))
                  .build()
                  .execute();

            // get answer
            final int mainMenuOptionRequested = 2;

            switch (mainMenuOptionRequested) {
                case 1:
                    break;//CTP
                case 2:
                    break;//TENNIS
                default: break;
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
        final String userName = user.getName();
        final String userId = user.getId() + userName;

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


        } catch (InterruptedException | ExecutionException exception) {
            // cound't connect to CTP
            exception.printStackTrace();
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
