package teste;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

class Questao {
    private String pergunta;
    private String resposta;

    @JsonCreator
    public Questao(
        @JsonProperty("pergunta") String pergunta,
        @JsonProperty("resposta") String resposta
    ) {
        this.pergunta = pergunta;
        this.resposta = resposta;
    }

    public String getPergunta() {
        return pergunta;
    }

    public String getResposta() {
        return resposta;
    }
}

public class Locais {
    private String nome;
    private int id;
    private List<String> imagens;
    private List<Integer> personagens;
    private List<Questao> questoes;

    @JsonCreator
    private Locais(
        @JsonProperty("nome") String nome,
        @JsonProperty("id") int id,
        @JsonProperty("imagens") List<String> imagens,
        @JsonProperty("personagens") List<Integer> personagens,
        @JsonProperty("questoes") List<Questao> questoes
    ) {
        this.nome = nome;
        this.id = id;
        this.imagens = imagens;
        this.personagens = personagens;
        this.questoes = questoes;
    }

    public Locais(int id) {
        try {
            // Caminho para o arquivo JSON
            File file = new File("Game/src/main/resources/json/locais.json");
    
            // Criar o ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
    
            // Ler o JSON como um Map
            Map<String, Locais> locaisMap = objectMapper.readValue(
                file,
                objectMapper.getTypeFactory().constructMapType(Map.class, String.class, Locais.class)
            );
    
            // Procurar pelo local com o ID correspondente
            Locais local = locaisMap.values().stream()
                    .filter(l -> l.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Local com ID " + id + " não encontrado!"));
    
            // Inicializar os atributos do Local atual com os valores do JSON
            this.nome = local.nome;
            this.id = local.id;
            this.imagens = local.imagens;
            this.personagens = local.personagens;
            this.questoes = local.questoes;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao inicializar o Local com ID " + id, e);
        }
    }
    
    // Getters e Setters
    public int getId() { return id; }
}