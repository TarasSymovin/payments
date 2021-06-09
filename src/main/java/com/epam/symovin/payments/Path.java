package com.epam.symovin.payments;

public class Path {
    public static final String PAGE_LOGIN = "/jsp/login.jsp";
    public static final String CARD_MENU = "/jsp/card_menu.jsp";
    public static final String REG_PAGE = "/jsp/registration.jsp";
    public static final String PAYMENTS_MENU = "/jsp/payments_menu.jsp";
    public static final String ADD_PAYMENT_PAGE = "/jsp/create_payment.jsp";
    public static final String SUBMIT_PAYMENT_PAGE = "/jsp/submit_page.jsp";
    public static final String SUCCESS_PAYMENT_PAGE = "/jsp/success_page.jsp";
    public static final String FAILED_PAGE = "/jsp/failed_page.jsp";
    public static final String ADMIN_MENU = "/jsp/admin/admin_page.jsp";
    public static final String ADMIN_CARD_PAGE = "/jsp/admin/admin_card_page.jsp";
    public static final String REQUESTS_PAGE = "/jsp/admin/admin_request_page.jsp";

    private final String pageUrl;
    private final boolean isRedirect;


    public Path(String pageUrl, boolean isRedirect) {
        this.pageUrl = pageUrl;
        this.isRedirect = isRedirect;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public boolean isRedirect() {
        return isRedirect;
    }
}
