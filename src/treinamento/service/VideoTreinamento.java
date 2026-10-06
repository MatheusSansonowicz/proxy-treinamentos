package treinamento.service;

import treinamento.model.Usuario;

/**
 * Interface comum (Subject) compartilhada pelo objeto real e pelo proxy.
 */
public interface VideoTreinamento {

    String getTitulo();

    void exibir(Usuario usuario);
}
