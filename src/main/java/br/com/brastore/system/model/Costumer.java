package br.com.brastore.system.model;

import br.com.brastore.system.enums.PaymentMethodEnum;

import java.util.Objects;

public class Costumer {
    private String name;
    private String cpf;
    private String paymentMethod;
    private String paymentData;

    public Costumer(String name, String cpf, Integer paymentMethod, String paymentData) {
        this.name = name;
        this.cpf = cpf;
        this.paymentMethod = PaymentMethodEnum.getByPaymentMethodId(paymentMethod).getPaymentMethod();
        this.paymentData = paymentData;
    }

    public Costumer(String name, Integer paymentMethod, String paymentData) {
        this.name = name;
        this.paymentMethod = PaymentMethodEnum.getByPaymentMethodId(paymentMethod).getPaymentMethod();
        this.paymentData = paymentData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = Objects.requireNonNull(PaymentMethodEnum.getByPaymentMethodId(paymentMethod)).getPaymentMethod();
    }

    public String getPaymentData() {
        return paymentData;
    }

    public void setPaymentData(String paymentData) {
        this.paymentData = paymentData;
    }

    @Override
    public String toString() {
        return (cpf == null) ? hasNoCpf() : hasCpf();
    }

    public String hasCpf() {
        // Verificar se o paymentData é vazio ou nulo
        if (paymentData != null && !paymentData.isEmpty()) {
            return name + " com o cpf " + cpf + "\n" + paymentData;
        }

        return name + " com o cpf " + cpf;
    }

    public String hasNoCpf() {
        // Verificar se o paymentData é vazio, string vazia ou nulo
        if (paymentData != null && !paymentData.isEmpty()) {
            return name + "\n" + paymentData;
        }
        return name + "\n" + paymentData;
    }
}
