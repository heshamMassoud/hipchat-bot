package com.heshammassoud.controller;

import com.atlassian.stride.spring.auth.AuthorizeJwtHeader;
import com.heshammassoud.models.ActionTargetRequest;
import com.heshammassoud.service.stride.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Nonnull;

import static com.heshammassoud.util.stride.MessageUtil.confirmProductsDelete;
import static com.heshammassoud.util.stride.MessageUtil.deleteMenu;
import static com.heshammassoud.util.stride.MessageUtil.mainMenu;

@Controller
public class DeleteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteController.class);

    private final MessageService messageService;

    public DeleteController(@Nonnull final MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * Whenever the delete option in the main menu is chosen, this controller is called.
     *
     * @param actionTargetRequest the payload sent from the action.
     */
    @AuthorizeJwtHeader
    @PostMapping(path = "/delete-menu", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void menu(@RequestBody @Nonnull final ActionTargetRequest actionTargetRequest) {

        LOGGER.info("Got delete-menu callback with payload {}", actionTargetRequest.toString());
        messageService.sendPrivatley(actionTargetRequest.getContext(), deleteMenu());
    }

    /**
     * Whenever the products option in the delete is chosen, this controller is called.
     *
     * @param actionTargetRequest the payload sent from the action.
     */
    @AuthorizeJwtHeader
    @PostMapping(path = "/products-delete-menu", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void products(@RequestBody @Nonnull final ActionTargetRequest actionTargetRequest) {

        LOGGER.info("Got products-delete-menu callback with payload {}", actionTargetRequest.toString());
        messageService.sendPrivatley(actionTargetRequest.getContext(), confirmProductsDelete("project-x-key", 3000));
    }

    /**
     * Whenever the yes option in the products confirm delete is chosen, this controller is called.
     *
     * @param actionTargetRequest the payload sent from the action.
     */
    @AuthorizeJwtHeader
    @PostMapping(path = "/products-delete-yes", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void productsConfirmYes(@RequestBody @Nonnull final ActionTargetRequest actionTargetRequest) {

        LOGGER.info("Got products-delete-yes callback with payload {}", actionTargetRequest.toString());
        messageService.sendPrivatley(actionTargetRequest.getContext(),
                mainMenu("Deleting products from  \"project-x-key\" ..... Fasten your seat belt, "
                        + "this may take some time. Deleted 230/3000. "));
    }

    /**
     * Whenever the no option in the products confirm delete is chosen, this controller is called.
     *
     * @param actionTargetRequest the payload sent from the action.
     */
    @AuthorizeJwtHeader
    @PostMapping(path = "/products-delete-no", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void productsConfirmNo(@RequestBody @Nonnull final ActionTargetRequest actionTargetRequest) {

        LOGGER.info("Got products-delete-no callback with payload {}", actionTargetRequest.toString());
        messageService.sendPrivatley(actionTargetRequest.getContext(),
                mainMenu("It seems that you are not ready yet for that. Anything else I can do for you?"));
    }

    /**
     * Whenever the categories option in the delete is chosen, this controller is called.
     *
     * @param actionTargetRequest the payload sent from the action.
     */
    @AuthorizeJwtHeader
    @PostMapping(path = "/categories-delete-menu", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void categories(@RequestBody @Nonnull final ActionTargetRequest actionTargetRequest) {

        LOGGER.info("Got categories-delete-menu callback with payload {}", actionTargetRequest.toString());
        messageService.sendPrivatley(actionTargetRequest.getContext(), confirmProductsDelete("NOT YET IMPLEMENTED", 0));
    }

    /**
     * Whenever the inventories option in the delete is chosen, this controller is called.
     *
     * @param actionTargetRequest the payload sent from the action.
     */
    @AuthorizeJwtHeader
    @PostMapping(path = "/inventories-delete-menu", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inventories(@RequestBody @Nonnull final ActionTargetRequest actionTargetRequest) {

        LOGGER.info("Got inventories-delete-menu callback with payload {}", actionTargetRequest.toString());
        messageService.sendPrivatley(actionTargetRequest.getContext(), confirmProductsDelete("NOT YET IMPLEMENTED", 0));
    }

}