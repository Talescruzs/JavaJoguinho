package teste;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;

public class Locais {
    private String nome;
    private int id;
    private List<String> imagens;
    private Integer personagem;
    private List<Questao> questoes;

    public static class Questao {
        private String pergunta;
        private String resposta;

        // Getters e Setters
        public String getPergunta() {
            return pergunta;
        }

        public void setPergunta(String pergunta) {
            this.pergunta = pergunta;
        }

        public String getResposta() {
            return resposta;
        }

        public void setResposta(String resposta) {
            this.resposta = resposta;
        }
    }

    // Classe para mapear a raiz do JSON
    public static class LocaisWrapper {
        private List<Locais> locais;

        public List<Locais> getLocais() {
            return locais;
        }

        public void setLocais(List<Locais> locais) {
            this.locais = locais;
        }
    }
    public Locais() {
        // Construtor vazio necessário para Jackson
    }

    // Construtor
    public Locais(int id) {
        try {
            // Caminho para o arquivo JSON
            File file = new File("Game/src/main/resources/json/locais.json");

            // Criar o ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Ler o JSON e deserializar para LocaisWrapper
            LocaisWrapper wrapper = objectMapper.readValue(file, LocaisWrapper.class);

            // Procurar o local com o ID correspondente
            Locais local = wrapper.getLocais().stream()
                    .filter(l -> l.id == id)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Local com ID " + id + " não encontrado!"));

            // Inicializar os atributos do Local atual com os valores do JSON
            this.nome = local.nome;
            this.id = local.id;
            this.imagens = local.imagens;
            this.personagem = local.personagem;
            this.questoes = local.questoes;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao inicializar o Local com ID " + id, e);
        }
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getImagens() {
        return imagens;
    }

    public void setImagens(List<String> imagens) {
        this.imagens = imagens;
    }

    public Integer getPersonagem() {
        return this.personagem;
    }

    public void setPersonagem(Integer personagem) {
        this.personagem = personagem;
    }

    public List<Questao> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<Questao> questoes) {
        this.questoes = questoes;
    }
}
