package teste;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class Quiz {
    private Integer id;
    private String pergunta;
    private ArrayList<String> respostas; // Lista com todas as respostas (correta e erradas)
    private Integer respostaCorretaIndex; // Índice da resposta correta na lista

    @JsonCreator
    public Quiz(
        @JsonProperty("id") Integer id,
        @JsonProperty("pergunta") String pergunta,
        @JsonProperty("respostas") ArrayList<String> respostas,
        @JsonProperty("respostaCorretaIndex") Integer respostaCorretaIndex
    ) {
        this.id = id;
        this.pergunta = pergunta;
        this.respostas = respostas;
        this.respostaCorretaIndex = respostaCorretaIndex;
    }

    public Quiz(Integer id) {
        try {
            // Caminho para o arquivo JSON
            File file = new File("Game/src/main/resources/json/quiz.json");

            // Criar o ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Ler o JSON como um Map
            Map<String, Quiz> quizzes = objectMapper.readValue(
                file,
                objectMapper.getTypeFactory().constructMapType(Map.class, String.class, Quiz.class)
            );

            // Procurar a questão correspondente ao ID
            Quiz quiz = quizzes.values().stream()
                .filter(q -> q.id.equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Quiz com ID " + id + " não encontrado!"));

            // Inicializar os atributos com os dados do JSON
            this.id = quiz.id;
            this.pergunta = quiz.pergunta;
            this.respostas = quiz.respostas;
            this.respostaCorretaIndex = quiz.respostaCorretaIndex;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao inicializar o Quiz com ID " + id, e);
        }
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public ArrayList<String> getRespostas() {
        return respostas;
    }

    public Integer getRespostaCorretaIndex() {
        return respostaCorretaIndex;
    }

    // Método para verificar se uma resposta está correta
    public boolean isRespostaCorreta(int index) {
        return index == respostaCorretaIndex;
    }
}