package treinamento.proxy;

import treinamento.excecao.AcessoNegadoException;
import treinamento.model.Usuario;
import treinamento.service.VideoReal;
import treinamento.service.VideoTreinamento;

/**
 * Proxy do video de treinamento.
 *
 * - Proxy de Protecao: verifica a permissao ANTES de tocar no objeto real.
 * - Proxy Virtual: o VideoReal so e criado na primeira exibicao autorizada
 *   e depois reutilizado (cache).
 */
public class VideoProxy implements VideoTreinamento {

    private final String titulo;
    private final String caminhoArquivo;
    private final boolean restritoAGestores;

    private VideoReal videoReal;

    public VideoProxy(String titulo, String caminhoArquivo, boolean restritoAGestores) {
        this.titulo = titulo;
        this.caminhoArquivo = caminhoArquivo;
        this.restritoAGestores = restritoAGestores;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public void exibir(Usuario usuario) {
        verificarPermissao(usuario);

        if (videoReal == null) {
            System.out.println("   [Proxy] Primeira exibicao: instanciando o video real.");
            videoReal = new VideoReal(titulo, caminhoArquivo);
        } else {
            System.out.println("   [Proxy] Video ja em memoria: reutilizando (cache).");
        }

        videoReal.exibir(usuario);
    }

    private void verificarPermissao(Usuario usuario) {
        if (restritoAGestores && !usuario.isGerente()) {
            throw new AcessoNegadoException("Acesso negado: '" + titulo
                    + "' e restrito a gestores (usuario: " + usuario.getNome() + ").");
        }
    }

    public boolean isCarregado() {
        return videoReal != null;
    }
}
