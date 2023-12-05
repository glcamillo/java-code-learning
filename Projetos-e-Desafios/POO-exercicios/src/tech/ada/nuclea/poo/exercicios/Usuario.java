package tech.ada.nuclea.poo.exercicios;

import java.util.ArrayList;
public class Usuario {
    private ArrayList<String> senhas;
    Usuario(String senha) {
        senhas = new ArrayList<>();
        senhas.add(senha);
    }
    String alterarSenha(String novaSenha) {
        for (String item : senhas) {
            if (item.equals(novaSenha))
                return "Senha não pode ser igual as três últimas utilizadas";
        }
        senhas.add(novaSenha);
        return "Senha alterada com sucesso";
    }
}
