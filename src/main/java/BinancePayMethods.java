public enum BinancePayMethods {
    ROSBANK ("RosBankNew"),
    TINKOFF ("TinkoffNew");
//    QIWI ("QIWI"),
//    RAIFFEISEN ("RaiffeisenBank"),
//    YOUMONEY ("YandexMoneyNew"),
//    ABANK ("ABank");

    private final String payId;

    BinancePayMethods(String payId) {
        this.payId = payId;
    }

    public String getPayId() {
        return payId;
    }

    public static String getRate(String efg, String coin2) {
        return efg;
    }
}
