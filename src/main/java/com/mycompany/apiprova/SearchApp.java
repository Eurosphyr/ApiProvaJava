/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.apiprova;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        setLocationRelativeTo(null); // Center the frame on the screen

        starWarsApi = StarWarsApi.getApi();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        add(panel);

        JButton peopleButton = new JButton("Search People");
        JButton filmsButton = new JButton("Search Films");
        JButton planetsButton = new JButton("Search Planets");
        JButton starshipsButton = new JButton("Search Starships");

        panel.add(peopleButton);
        panel.add(filmsButton);
        panel.add(planetsButton);
        panel.add(starshipsButton);

        peopleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchPeople();
            }
        });

        filmsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFilms();
            }
        });

        planetsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchPlanets();
            }
        });

        starshipsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStarships();
            }
        });
    }

    private void searchPeople() {
        Call<SWModelList<People>> call = starWarsApi.getAllPeople(1);
        call.enqueue(new Callback<SWModelList<People>>() {
            @Override
            public void onResponse(Call<SWModelList<People>> call, Response<SWModelList<People>> response) {
                if (response.isSuccessful()) {
                    SWModelList<People> peopleList = response.body();
                    JOptionPane.showMessageDialog(SearchApp.this, "Found " + peopleList.getCount() + " people.");
                } else {
                    JOptionPane.showMessageDialog(SearchApp.this, "Failed to search for people.");
                }
            }

            @Override
            public void onFailure(Call<SWModelList<People>> call, Throwable t) {
                JOptionPane.showMessageDialog(SearchApp.this, "Failed to search for people: " + t.getMessage());
            }
        });
    }

    private void searchFilms() {
        Call<SWModelList<Film>> call = starWarsApi.getAllFilms(1);
        call.enqueue(new Callback<SWModelList<Film>>() {
            @Override
            public void onResponse(Call<SWModelList<Film>> call, Response<SWModelList<Film>> response) {
                if (response.isSuccessful()) {
                    SWModelList<Film> filmList = response.body();
                    JOptionPane.showMessageDialog(SearchApp.this, "Found " + filmList.getCount() + " films.");
                } else {
                    JOptionPane.showMessageDialog(SearchApp.this, "Failed to search for films.");
                }
            }

            @Override
            public void onFailure(Call<SWModelList<Film>> call, Throwable t) {
                JOptionPane.showMessageDialog(SearchApp.this, "Failed to search for films: " + t.getMessage());
            }
        });
    }

    private void searchPlanets() {
        Call<SWModelList<Planet>> call = starWarsApi.getAllPlanets(1);
        call.enqueue(new Callback<SWModelList<Planet>>() {
            @Override
            public void onResponse(Call<SWModelList<Planet>> call, Response<SWModelList<Planet>> response) {
                if (response.isSuccessful()) {
                    SWModelList<Planet> planetList = response.body();
                    JOptionPane.showMessageDialog(SearchApp.this, "Found " + planetList.getCount() + " planets.");
                } else {
                    JOptionPane.showMessageDialog(SearchApp.this, "Failed to search for planets.");
                }
            }

            @Override
            public void onFailure(Call<SWModelList<Planet>> call, Throwable t) {
                JOptionPane.showMessageDialog(SearchApp.this, "Failed to search for planets: " + t.getMessage());
            }
        });
    }

    private void searchStarships() {
        Call<SWModelList<Starship>> call = starWarsApi.getAllStarships(1);
        call.enqueue(new Callback<SWModelList<Starship>>() {
            @Override
            public void onResponse(Call<SWModelList<Starship>> call, Response<SWModelList<Starship>> response) {
                if (response.isSuccessful()) {
                    SWModelList<Starship> starshipList = response.body();
                    JOptionPane.showMessageDialog(SearchApp.this, "Found " + starshipList.getCount() + " starships.");
                } else {
                    JOptionPane.showMessageDialog(SearchApp.this, "Failed to search for starships.");
                }
            }

            @Override
            public void onFailure(Call<SWModelList<Starship>> call, Throwable t) {
                JOptionPane.showMessageDialog(SearchApp.this, "Failed to search for starships: " + t.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SearchApp().setVisible(true);
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
