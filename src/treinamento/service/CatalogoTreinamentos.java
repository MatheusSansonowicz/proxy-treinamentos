package treinamento.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Catalogo exibido na pagina inicial. Guarda apenas as referencias
 * (proxies), sem carregar nenhum video pesado.
 */
public class CatalogoTreinamentos {

    private final List<VideoTreinamento> videos = new ArrayList<>();

    public void adicionar(VideoTreinamento video) {
        videos.add(video);
    }

    public List<VideoTreinamento> listar() {
        return Collections.unmodifiableList(videos);
    }

    public VideoTreinamento buscarPorTitulo(String titulo) {
        for (VideoTreinamento v : videos) {
            if (v.getTitulo().equalsIgnoreCase(titulo)) {
                return v;
            }
        }
        throw new IllegalArgumentException("Treinamento nao encontrado: " + titulo);
    }
}
