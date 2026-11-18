package com.mycompany.mattresssalesgui;

// Calculates the VAT, commission and total for one sale
public class ProductCalculations implements IProductCalculations {
    // VAT is 15% and commission is 5% of the product price
    private static final double VAT_PERCENT = 15;
    private static final double COMMISSION_PERCENT = 5;

    // The price is passed in from the form
    private final double price;

    public ProductCalculations(double price) {
        this.price = price;
    }

    // 15% of the price
    @Override
    public double GetVat() {
        return price * VAT_PERCENT / 100;
    }

    // 5% of the price
    @Override
    public double GetCommission() {
        return price * COMMISSION_PERCENT / 100;
    }

    // Price + VAT + commission
    @Override
    public double GetTotal() {
        return price + GetVat() + GetCommission();
    }
}