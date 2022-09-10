package by.rom.flightservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Currency {
    @JsonProperty("usd")
    USD("USD"),
    @JsonProperty("eur")
    EUR("EUR"),
    @JsonProperty("chf")
    CHF("CHF"),
    @JsonProperty("gbp")
    GBP("GBP"),
    @JsonProperty("pln")
    PLN("PLN"),
    @JsonProperty("rub")
    RUB("RUB"),
    @JsonProperty("jpy")
    JPY("JPY"),
    @JsonProperty("hkd")
    HKD("HKD"),
    @JsonProperty("cny")
    CNY("CNY");

    private String currency;

    Currency(String currency) {
        this.currency = currency;
    }
}
