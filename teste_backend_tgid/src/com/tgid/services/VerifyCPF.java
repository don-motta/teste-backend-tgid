package com.tgid.services;

public class VerifyCPF {
    private String CPF;

    public VerifyCPF(String CPF){
        this.CPF= CPF;
    }

    public String getCPF() {
        return this.CPF;
    }

    public boolean isCPF (){
        int sm = 0;
        int peso = 10;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(this.CPF.charAt(i));
            sm += digito * peso;
            peso--;
        }

        int primeiroDigitoVerificador = 11 - (sm % 11);
        if (primeiroDigitoVerificador >= 10) {
            primeiroDigitoVerificador = 0;
        }

        // Verifica o primeiro dígito verificador
        if (Character.getNumericValue(this.CPF.charAt(9)) != primeiroDigitoVerificador) {
            return false;
        }else {

            // Calcula o segundo dígito verificador
            sm = 0;
            peso = 11;
            for (int i = 0; i < 10; i++) {
                int digito = Character.getNumericValue(this.CPF.charAt(i));
                sm += digito * peso;
                peso--;
            }

            int segundoDigitoVerificador = 11 - (sm % 11);
            if (segundoDigitoVerificador >= 10) {
                segundoDigitoVerificador = 0;
            }

            // Verifica o segundo dígito verificador
            return Character.getNumericValue(this.CPF.charAt(10)) == segundoDigitoVerificador;
        }
    }
}
