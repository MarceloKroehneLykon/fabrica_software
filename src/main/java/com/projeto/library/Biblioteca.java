package com.projeto.library;

import com.projeto.bases.exceptions.ValidacoesException;
import com.projeto.entities.models.Funcionario;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

public class Biblioteca {

    private static final Pattern EMAIL_REGEX = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$",
            Pattern.CASE_INSENSITIVE
    );

    public static boolean isCpfValido(String cpf){
        if (cpf == null) return false;

        cpf = cpf.replaceAll("[^\\d]", "");

        if(cpf.length() != 11)
            return false;

        int[] numeros1 = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] numeros2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

        int digito1 = 0;
        int digito2 = 0;

        for (int i = 0; i < numeros1.length; i++)
            digito1 += Character.getNumericValue(cpf.charAt(i)) * numeros1[i];

        for (int i = 0; i < numeros2.length; i++)
            digito2 += Character.getNumericValue(cpf.charAt(i)) * numeros2[i];

        int mod1 = digito1 % 11;
        int mod2 = digito2 % 11;

        digito1 = mod1 < 2 ? 0 : 11 - mod1;
        digito2 = mod2 < 2 ? 0 : 11 - mod2;

        return cpf.charAt(9) == digito1 + '0' && cpf.charAt(10) == digito2 + '0';

    }

    public static boolean isCnpjValido(String cnpj) {
        if (cnpj == null) return false;

        cnpj = cnpj.replaceAll("[^\\d]", "");

        if (cnpj.length() != 14) return false;

        if (cnpj.matches("(\\d)\\1{13}")) return false;

        int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        int soma = 0;
        for (int i = 0; i < 12; i++) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * pesos1[i];
        }

        int digito1 = soma % 11 < 2 ? 0 : 11 - (soma % 11);

        soma = 0;
        for (int i = 0; i < 13; i++) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * pesos2[i];
        }

        int digito2 = soma % 11 < 2 ? 0 : 11 - (soma % 11);

        return digito1 == Character.getNumericValue(cnpj.charAt(12)) &&
                digito2 == Character.getNumericValue(cnpj.charAt(13));

    }

    public static boolean StringMaiorIgual(String text, int tamanho){
        return text != null && text.length() >= tamanho;
    }

    public static String numeros(String s){
        StringBuilder r = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)))
                r.append(s.charAt(i));
        }

        return r.toString();
    }

    public static boolean isTelefoneValido(String telefone){
        return StringMaiorIgual(numeros(telefone), 11);
    }

    public static boolean isCamposVazios(String... campos) {
        for(String campo : campos){
            if(isStringVazia(campo))
                return true;
        }

        return false;
    }

    public static boolean isStringVazia(String texto){
        return texto == null || texto.isEmpty();
    }

    public static boolean isCpfCnpjValido(String cpfCnpj) {
        return isCnpjValido(cpfCnpj) || isCpfValido(cpfCnpj);
    }

    public static boolean isEmailValido(String email) {
        if (email == null || email.isBlank()) return false;
        return EMAIL_REGEX.matcher(email).matches();
    }

    public static boolean em(Object objeto, Object ... obsjetoComparar) {
        for(Object obj : obsjetoComparar){
            if(objeto.equals(obj))
                return true;
        }

        return false;
    }

    public static void validarDadosUsuario(Funcionario pessoa) {

        validarDadosSimples(pessoa);
        if(!isCpfValido(pessoa.getCpf()))
            throw new ValidacoesException("O CPF está incorreto");

    }

    public static void validarDadosSimples(Funcionario pessoa) {

        if(!StringMaiorIgual(pessoa.getNome(), 3))
            throw new ValidacoesException("O nome está incorreto");

        if(!isEmailValido(pessoa.getEmail()))
            throw new ValidacoesException("O e-mail está incorreto");
    }

    public static boolean isDataMaior(Timestamp data1, Timestamp data2, int dias) {
        GregorianCalendar dataLimite = new GregorianCalendar();
        dataLimite.setTime(data1);

        GregorianCalendar dataComparar = new GregorianCalendar();
        dataComparar.add(Calendar.DAY_OF_MONTH, dias);
        dataComparar.setTime(data2);

        return dataLimite.compareTo(dataComparar) > 0;
    }
}
