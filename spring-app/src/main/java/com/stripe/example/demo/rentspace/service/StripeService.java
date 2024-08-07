package com.stripe.example.demo.rentspace.service;

import com.stripe.Stripe;
import com.stripe.model.Account;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Product;
import com.stripe.net.RequestOptions;
import com.stripe.param.AccountCreateParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.ProductCreateParams;
import org.springframework.stereotype.Service;

import static com.stripe.example.demo.rentspace.constants.Constants.STRIPE_API_KEY;

@Service
public class StripeService {

    public PaymentIntent createPaymentIntent(Long amount, String currency, String stripeAccountId) throws Exception {
        Stripe.apiKey = STRIPE_API_KEY;

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amount)
                .setCurrency(currency)
                .setTransferData(
                        PaymentIntentCreateParams.TransferData.builder()
                                .setDestination(stripeAccountId)
                                .build()
                )
                .build();

        return PaymentIntent.create(params);
    }
    public Account createConnectedAccount(String email, String country) throws Exception {
        Stripe.apiKey = STRIPE_API_KEY;

        AccountCreateParams params =
                AccountCreateParams.builder()
                        .setCountry("US")
                        .setEmail("jenny.rosen@example.com")
                        .setController(
                                AccountCreateParams.Controller.builder()
                                        .setFees(
                                                AccountCreateParams.Controller.Fees.builder()
                                                        .setPayer(AccountCreateParams.Controller.Fees.Payer.APPLICATION)
                                                        .build()
                                        )
                                        .setLosses(
                                                AccountCreateParams.Controller.Losses.builder()
                                                        .setPayments(AccountCreateParams.Controller.Losses.Payments.APPLICATION)
                                                        .build()
                                        )
                                        .setStripeDashboard(
                                                AccountCreateParams.Controller.StripeDashboard.builder()
                                                        .setType(AccountCreateParams.Controller.StripeDashboard.Type.EXPRESS)
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();

        return Account.create(params);
    }

    public Product createProduct(String stripeAccountId, String name, String description) throws Exception {
        Stripe.apiKey = STRIPE_API_KEY;

        ProductCreateParams params = ProductCreateParams.builder()
                .setName(name)
                .setDescription(description)
                .build();

        // Cria o RequestOptions com a chave da conta conectada
        RequestOptions requestOptions = RequestOptions.builder()
                .setStripeAccount(stripeAccountId)
                .build();

        // Cria e retorna o produto usando a chave da conta conectada
        return Product.create(params, requestOptions);
    }
}
