# Trabalho de Implementação: Padrao de Projeto Proxy

## O problema
A plataforma de treinamentos corporativos tinha dois problemas:
1. **Desempenho:** a pagina inicial carregava todos os videos de uma vez, consumindo memoria e banda.
2. **Seguranca:** treinamentos restritos a gestores podiam ser acessados por qualquer colaborador.

## Como o Proxy resolveu
A interface `VideoTreinamento` e compartilhada pelo objeto real (`VideoReal`) e pelo proxy (`VideoProxy`),
garantindo polimorfismo: o cliente (`Main`/`CatalogoTreinamentos`) so conhece a interface.

- **Proxy Virtual (desempenho):** o catalogo guarda apenas `VideoProxy`, que sao leves. O `VideoReal`
  (carga pesada) so e instanciado na **primeira exibicao** solicitada. Nas exibicoes seguintes o proxy
  **reutiliza** a instancia ja carregada (cache).
- **Proxy de Protecao (seguranca):** antes de qualquer acao, o proxy verifica o cargo do usuario. Se o video
  e restrito a gestores e o usuario e colaborador, lanca `AcessoNegadoException` **sem instanciar o
  objeto real**, evitando custo e vazamento de conteudo.

## Estrutura
```
src/treinamento/
  Main.java                          # classe de teste/cenarios
  model/Cargo.java, Usuario.java     # dados do usuario
  service/VideoTreinamento.java      # interface comum
  service/VideoReal.java             # objeto real (carga pesada)
  service/CatalogoTreinamentos.java  # catalogo da pagina inicial
  proxy/VideoProxy.java              # proxy virtual + protecao
  excecao/AcessoNegadoException.java
```

## Como compilar e executar (Java 11+)
Na raiz do projeto:
```bash
mkdir out
javac -d out $(find src -name "*.java")      # Linux/Mac
java -cp out treinamento.Main
```
No Windows (PowerShell):

## Cenarios demonstrados no Main
1. Listagem do catalogo: nenhum video carregado.
2. Colaborador assiste video livre: carga lazy (1a vez).
3. Gerente assiste o mesmo video: reutiliza a instancia (cache).
4. Colaborador tenta video restrito: **acesso negado**, objeto real nao e criado.
5. Gerente assiste video restrito: acesso permitido.
