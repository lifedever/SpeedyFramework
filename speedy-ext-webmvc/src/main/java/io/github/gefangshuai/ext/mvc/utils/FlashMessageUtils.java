package io.github.gefangshuai.ext.mvc.utils;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by gefangshuai on 2015/11/13.
 */
public class FlashMessageUtils {
    public static final String SUCCESS_ATTR = "flash_message_success";
    public static final String WARNING_ATTR = "flash_message_warning";
    public static final String ERROR_ATTR = "flash_message_error";

    public static void success(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute(SUCCESS_ATTR, message);
    }

    public static void warning(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute(WARNING_ATTR, message);
    }

    public static void error(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute(ERROR_ATTR, message);
    }
}
