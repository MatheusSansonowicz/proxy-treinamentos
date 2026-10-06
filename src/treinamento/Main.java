package treinamento;

import treinamento.excecao.AcessoNegadoException;
import treinamento.model.Cargo;
import treinamento.model.Usuario;
import treinamento.proxy.VideoProxy;
import treinamento.service.CatalogoTreinamentos;
import treinamento.service.VideoTreinamento;

public class Main {

    public static void main(String[] args) {
        Usuario ana = new Usuario("Ana", Cargo.COLABORADOR);
        Usuario carlos = new Usuario("Carlos", Cargo.GERENTE);

        CatalogoTreinamentos catalogo = new CatalogoTreinamentos();
        catalogo.adicionar(new VideoProxy("Integracao de Novos Colaboradores", "videos/integracao.mp4", false));
        catalogo.adicionar(new VideoProxy("Seguranca da Informacao", "videos/seguranca.mp4", false));
        catalogo.adicionar(new VideoProxy("Gestao de Equipes", "videos/gestao-equipes.mp4", true));

        System.out.println("=== CENARIO 1: Pagina inicial (lista o catalogo, nenhum video e carregado) ===");
        for (VideoTreinamento v : catalogo.listar()) {
            System.out.println(" - " + v.getTitulo() + " | carregado em memoria? " + ((VideoProxy) v).isCarregado());
        }

        System.out.println("\n=== CENARIO 2 (sucesso): Ana assiste video livre pela 1a vez (lazy loading) ===");
        catalogo.buscarPorTitulo("Seguranca da Informacao").exibir(ana);

        System.out.println("\n=== CENARIO 3 (sucesso): Carlos assiste o mesmo video (reutiliza cache) ===");
        catalogo.buscarPorTitulo("Seguranca da Informacao").exibir(carlos);

        System.out.println("\n=== CENARIO 4 (falha): Ana tenta assistir video restrito a gestores ===");
        try {
            catalogo.buscarPorTitulo("Gestao de Equipes").exibir(ana);
        } catch (AcessoNegadoException e) {
            System.out.println("   [Main] " + e.getMessage());
        }
        VideoProxy restrito = (VideoProxy) catalogo.buscarPorTitulo("Gestao de Equipes");
        System.out.println("   [Main] Video restrito foi carregado apos a negativa? " + restrito.isCarregado());

        System.out.println("\n=== CENARIO 5 (sucesso): Carlos assiste video restrito (acesso permitido) ===");
        catalogo.buscarPorTitulo("Gestao de Equipes").exibir(carlos);
    }
}
