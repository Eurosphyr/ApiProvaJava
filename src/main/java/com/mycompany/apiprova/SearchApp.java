package com.mycompany.apiprova;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import com.mycompany.apiprova.service.StarWars;
import com.mycompany.apiprova.service.StarWarsApi;
import com.mycompany.apiprova.model.SWModelList;
import com.mycompany.apiprova.model.People;
import com.mycompany.apiprova.model.Film;
import com.mycompany.apiprova.model.Planet;
import com.mycompany.apiprova.model.Starship;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchApp extends JFrame {

    private final StarWars starWarsApi;

    public SearchApp() {
        setTitle("Star Wars Search App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null); // Centraliza o frame na tela

        starWarsApi = StarWarsApi.getApi();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        add(panel);

        JButton peopleButton = new JButton("Buscar Personagens");
        JButton filmsButton = new JButton("Buscar Filmes");
        JButton planetsButton = new JButton("Buscar Planetas");
        JButton starshipsButton = new JButton("Buscar Naves");

        panel.add(peopleButton);
        panel.add(filmsButton);
        panel.add(planetsButton);
        panel.add(starshipsButton);

        peopleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = JOptionPane.showInputDialog(SearchApp.this, "Digite o nome do personagem:");
                if (searchTerm != null) {
                    buscarPersonagens(searchTerm);
                }
            }
        });

        filmsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = JOptionPane.showInputDialog(SearchApp.this, "Digite o nome do filme:");
                if (searchTerm != null) {
                    buscarFilmes(searchTerm);
                }
            }
        });

        planetsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = JOptionPane.showInputDialog(SearchApp.this, "Digite o nome do planeta:");
                if (searchTerm != null) {
                    buscarPlanetas(searchTerm);
                }
            }
        });

        starshipsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = JOptionPane.showInputDialog(SearchApp.this, "Digite o nome da nave:");
                if (searchTerm != null) {
                    buscarNaves(searchTerm);
                }
            }
        });
    }

    private void buscarPersonagens(String termoBusca) {
        Call<SWModelList<People>> chamada = starWarsApi.getAllPeople(1);
        chamada.enqueue(new Callback<SWModelList<People>>() {
            @Override
            public void onResponse(Call<SWModelList<People>> chamada, Response<SWModelList<People>> resposta) {
                if (resposta.isSuccessful() && resposta.body() != null) {
                    List<People> listaPersonagens = resposta.body().getResults();
                    StringBuilder resultado = new StringBuilder();
                    for (People personagem : listaPersonagens) {
                        if (termoBusca == null || termoBusca.isEmpty()
                                || personagem.getName().equalsIgnoreCase(termoBusca)) {
                            resultado.append("Nome: ").append(personagem.getName()).append("\n");
                            resultado.append("Altura: ").append(personagem.getHeight()).append("\n");
                            resultado.append("Peso: ").append(personagem.getMass()).append("\n");
                            resultado.append("Cor do cabelo: ").append(personagem.getHairColor()).append("\n");

                            resultado.append("Ano de nascimento: ").append(personagem.getBirthYear()).append("\n");
                            resultado.append("Gênero: ").append(personagem.getGender()).append("\n");
                            resultado.append("\n");
                        }
                    }
                    if (resultado.length() == 0) {
                        JOptionPane.showMessageDialog(SearchApp.this, "Nenhum personagem encontrado.");
                    } else {
                        exibirMensagemComBarraDeRolagem(resultado.toString());
                    }
                } else {
                    JOptionPane.showMessageDialog(SearchApp.this, "Falha ao buscar personagens.");
                }
            }

            @Override
            public void onFailure(Call<SWModelList<People>> chamada, Throwable t) {
                JOptionPane.showMessageDialog(SearchApp.this, "Falha ao buscar personagens: " + t.getMessage());
            }
        });
    }

    private void buscarFilmes(String termoBusca) {
        Call<SWModelList<Film>> chamada = starWarsApi.getAllFilms(1);
        chamada.enqueue(new Callback<SWModelList<Film>>() {
            @Override
            public void onResponse(Call<SWModelList<Film>> chamada, Response<SWModelList<Film>> resposta) {
                if (resposta.isSuccessful() && resposta.body() != null) {
                    List<Film> listaFilmes = resposta.body().getResults();
                    StringBuilder resultado = new StringBuilder();
                    for (Film filme : listaFilmes) {
                        if (termoBusca == null || termoBusca.isEmpty()
                                || filme.getTitle().toLowerCase().contains(termoBusca.toLowerCase())) {
                            resultado.append("Título: ").append(filme.getTitle()).append("\n");
                            resultado.append("Diretor: ").append(filme.getDirector()).append("\n");
                            resultado.append("Produtor: ").append(filme.getProducer()).append("\n");
                            resultado.append("Data de Lançamento: ").append(filme.getRelease_date()).append("\n");
                            resultado.append("\n");
                        }
                    }
                    if (resultado.length() == 0) {
                        JOptionPane.showMessageDialog(SearchApp.this, "Nenhum filme encontrado.");
                    } else {
                        exibirMensagemComBarraDeRolagem(resultado.toString());
                    }
                } else {
                    JOptionPane.showMessageDialog(SearchApp.this, "Falha ao buscar filmes.");
                }
            }
    
            @Override
            public void onFailure(Call<SWModelList<Film>> chamada, Throwable t) {
                JOptionPane.showMessageDialog(SearchApp.this, "Falha ao buscar filmes: " + t.getMessage());
            }
        });
    }
    

    private void buscarPlanetas(String termoBusca) {
        Call<SWModelList<Planet>> chamada = starWarsApi.getAllPlanets(1);
        chamada.enqueue(new Callback<SWModelList<Planet>>() {
            @Override
            public void onResponse(Call<SWModelList<Planet>> chamada, Response<SWModelList<Planet>> resposta) {
                if (resposta.isSuccessful() && resposta.body() != null) {
                    List<Planet> listaPlanetas = resposta.body().getResults();
                    StringBuilder resultado = new StringBuilder();
                    for (Planet planeta : listaPlanetas) {
                        if (termoBusca == null || termoBusca.isEmpty()
                                || planeta.getName().equalsIgnoreCase(termoBusca)) {
                            resultado.append("Nome: ").append(planeta.getName()).append("\n");
                            resultado.append("Período de Rotação: ").append(planeta.getRotationPeriod()).append("\n");
                            resultado.append("Período Orbital: ").append(planeta.getOrbitalPeriod()).append("\n");
                            resultado.append("Diâmetro: ").append(planeta.getDiameter()).append("\n");
                            resultado.append("Clima: ").append(planeta.getClimate()).append("\n");
                            resultado.append("Gravidade: ").append(planeta.getGravity()).append("\n");
                            resultado.append("Terreno: ").append(planeta.getTerrain()).append("\n");
                            resultado.append("Água da Superfície: ").append(planeta.getSurfaceWater()).append("\n");
                            resultado.append("População: ").append(planeta.getPopulation()).append("\n");
                            resultado.append("\n");
                        }
                    }
                    if (resultado.length() == 0) {
                        JOptionPane.showMessageDialog(SearchApp.this, "Nenhum planeta encontrado.");
                    } else {
                        exibirMensagemComBarraDeRolagem(resultado.toString());
                    }
                } else {
                    JOptionPane.showMessageDialog(SearchApp.this, "Falha ao buscar planetas.");
                }
            }

            @Override
            public void onFailure(Call<SWModelList<Planet>> chamada, Throwable t) {
                JOptionPane.showMessageDialog(SearchApp.this, "Falha ao buscar planetas: " + t.getMessage());
            }
        });
    }

    private void buscarNaves(String termoBusca) {
        Call<SWModelList<Starship>> chamada = starWarsApi.getAllStarships(1);
        chamada.enqueue(new Callback<SWModelList<Starship>>() {
            @Override
            public void onResponse(Call<SWModelList<Starship>> chamada, Response<SWModelList<Starship>> resposta) {
                if (resposta.isSuccessful() && resposta.body() != null) {
                    List<Starship> listaNaves = resposta.body().getResults();
                    StringBuilder resultado = new StringBuilder();
                    for (Starship nave : listaNaves) {
                        if (termoBusca == null || termoBusca.isEmpty() || nave.getName().equalsIgnoreCase(termoBusca)) {
                            resultado.append("Nome: ").append(nave.getName()).append("\n");
                            resultado.append("Modelo: ").append(nave.getModel()).append("\n");
                            resultado.append("Fabricante: ").append(nave.getManufacturer()).append("\n");
                            resultado.append("Comprimento: ").append(nave.getLength()).append("\n");
                            resultado.append("Tripulação: ").append(nave.getCrew()).append("\n");
                            resultado.append("Passageiros: ").append(nave.getPassengers()).append("\n");
                            resultado.append("Capacidade de Carga: ").append(nave.getCargoCapacity()).append("\n");
                            resultado.append("Classe da Nave: ").append(nave.getStarshipClass()).append("\n");
                            resultado.append("\n");
                        }
                    }
                    if (resultado.length() == 0) {
                        JOptionPane.showMessageDialog(SearchApp.this, "Nenhuma nave encontrada.");
                    } else {
                        exibirMensagemComBarraDeRolagem(resultado.toString());
                    }
                } else {
                    JOptionPane.showMessageDialog(SearchApp.this, "Falha ao buscar naves.");
                }
            }

            @Override
            public void onFailure(Call<SWModelList<Starship>> chamada, Throwable t) {
                JOptionPane.showMessageDialog(SearchApp.this, "Falha ao buscar naves: " + t.getMessage());
            }
        });
    }

    private void exibirMensagemComBarraDeRolagem(String mensagem) {
        JTextArea textArea = new JTextArea(mensagem);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(SearchApp.this, scrollPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SearchApp().setVisible(true);
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    // </editor-fold>

    // Variables declaration - do not modify
    // End of variables declaration

    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">
    // </editor-fold>

    // Variables declaration - do not modify
    // End of variables declaration

    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}