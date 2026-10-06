package treinamento.service;

import treinamento.model.Usuario;

/**
 * Objeto real (RealSubject). Carregar o arquivo de video e uma operacao
 * pesada, por isso so deve ser instanciado quando realmente necessario.
 */
public class VideoReal implements VideoTreinamento {

    private final String titulo;
    private final String caminhoArquivo;

    public VideoReal(String titulo, String caminhoArquivo) {
        this.titulo = titulo;
        this.caminhoArquivo = caminhoArquivo;
        carregarDoDisco();
    }

    private void carregarDoDisco() {
        System.out.println("   [VideoReal] Carregando '" + caminhoArquivo + "' na memoria (operacao pesada)...");
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("   [VideoReal] Video '" + titulo + "' carregado.");
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public void exibir(Usuario usuario) {
        System.out.println("   [VideoReal] Exibindo '" + titulo + "' para " + usuario.getNome() + ".");
    }
}
